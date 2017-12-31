package ca.ftcalberta.rrlivescore.models;


import android.support.annotation.VisibleForTesting;

public class Cryptobox {
    public static final int ROWS = 4;
    public static final int COLS = 3;

    private static final int GLYPH_AUTONOMOUS_SCORE = 15;
    private static final int GLYPH_TELEOP_SCORE = 2;
    private static final int ROW_BONUS = 10;
    private static final int COL_BONUS = 20;
    private static final int CIPHER_BONUS = 30;
    private static final int KEY_COLUMN_BONUS = 30;

    private static final Glyph grayGlyph = new Glyph(Glyph.Color.GRAY);
    private static final Glyph brownGlyph = new Glyph(Glyph.Color.BROWN);

    private static final Glyph[][] frogCipher = new Glyph[][]
            {
                    new Glyph[] {brownGlyph, grayGlyph, brownGlyph},
                    new Glyph[] {grayGlyph, brownGlyph, grayGlyph},
                    new Glyph[] {brownGlyph, grayGlyph, brownGlyph},
                    new Glyph[] {grayGlyph, brownGlyph, grayGlyph}
            };

    private static final Glyph[][] birdCipher = new Glyph[][]
            {
                    new Glyph[] {brownGlyph, grayGlyph, brownGlyph},
                    new Glyph[] {grayGlyph, brownGlyph, grayGlyph},
                    new Glyph[] {grayGlyph, brownGlyph, grayGlyph},
                    new Glyph[] {brownGlyph, grayGlyph, brownGlyph},
            };

    private static final Glyph[][] snakeCipher = new Glyph[][]
            {
                    new Glyph[] {brownGlyph, brownGlyph, grayGlyph},
                    new Glyph[] {brownGlyph, grayGlyph, grayGlyph},
                    new Glyph[] {grayGlyph, grayGlyph, brownGlyph},
                    new Glyph[] {grayGlyph, brownGlyph, brownGlyph},
            };

    private Alliance alliance;
    private int keyColumn = 0;
    private Glyph[][] box = new Glyph[ROWS][COLS];

    private boolean isFirstGlyph = true;
    private int keyColumnBonus = 0;
    private int keyColumnCount = 0;

    private int glyphCount = 0;
    private int autonomousScore = 0;
    private int teleopScore = 0;
    private int autonomousGlyphScore = 0;
    private int teleopGlyphScore = 0;
    private int rowBonus = 0;
    private int colBonus = 0;
    private int cipherBonus = 0;
    private int rowCount = 0;
    private int colCount = 0;
    private int cipherCount = 0;

    public Cryptobox(Alliance alliance) {
        this.alliance = alliance;
        reset();
    }

    public Cryptobox(Alliance alliance, int keyColumn, Glyph[][] box) {
        this.alliance = alliance;
        this.keyColumn = keyColumn;
        this.box = box;
        updateScore();
    }

    public Alliance getAlliance() {
        return alliance;
    }

    public int getKeyColumn() {
        return keyColumn;
    }

    public void setKeyColumn(int keyColumn) {
        if (keyColumn >= 0 && keyColumn <= 2) {
            this.keyColumn = keyColumn;
        }
    }

    public boolean isFirstGlyphPlaced() {
        return !isFirstGlyph;
    }

    public void toggleGlyph(int row, int col) {
        Glyph currentGlyph = getGlyph(row, col);

        if (currentGlyph != null) {
            if (currentGlyph.getColor() == Glyph.Color.GRAY) {
                addGlyph(row, col, Glyph.Color.BROWN);
            }
            else {
                addGlyph(row, col, Glyph.Color.GRAY);
            }
        }
        else {
            addGlyph(row, col, Glyph.Color.GRAY);
        }
    }

    public void addGlyph(int row, int col, Glyph.Color color) {
        if (isFirstGlyph) {
            if (col == keyColumn) {
                keyColumnBonus = KEY_COLUMN_BONUS;
                keyColumnCount = 1;
            }
            isFirstGlyph = false;
        }

        box[row][col] = new Glyph(color);
        updateScore();
    }

    public void removeGlyph(int row, int col) {
        box[row][col] = null;
        updateScore();
    }

    public Glyph getGlyph(int row, int col) {
        return box[row][col];
    }

    protected void updateScore() {
        glyphCount = getTotalGlyphCount();

        autonomousGlyphScore = glyphCount * GLYPH_AUTONOMOUS_SCORE;
        teleopGlyphScore = glyphCount * GLYPH_TELEOP_SCORE;

        rowCount = getCompleteRows();
        rowBonus = rowCount * ROW_BONUS;
        colCount = getCompleteColumns();
        colBonus = colCount * COL_BONUS;
        cipherCount = isCipherComplete() ? 1 : 0;
        cipherBonus = cipherCount * CIPHER_BONUS;

        autonomousScore = autonomousGlyphScore + keyColumnBonus;
        teleopScore = teleopGlyphScore + rowBonus + colBonus + cipherBonus;
    }

    public int getTotalGlyphCount() {
        int count = 0;
        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLS; j++) {
                if (getGlyph(i, j) != null) count++;
            }
        }
        return count;
    }

    public int getCompleteRows() {
        int completeRows = 0;
        for (int i = 0; i < ROWS; i++) {
            if (rowIsFull(i)) completeRows++;
        }
        return completeRows;
    }

    public int getCompleteColumns() {
        int completeCols = 0;
        for (int i = 0; i < COLS; i++) {
            if (colIsFull(i)) completeCols++;
        }
        return completeCols;
    }

    public boolean isCipherComplete() {
        if (getTotalGlyphCount() != ROWS * COLS) return false;

        return equalsCipher(frogCipher) ||
                equalsCipher(birdCipher) ||
                equalsCipher(snakeCipher);
    }

    private boolean equalsCipher(Glyph[][] cipher) {
        // Check the first one to see if we are looking for the inverse cipher
        boolean inverseCipher = !getGlyph(0, 0).equals(cipher[0][0]);

        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLS; j++) {
                if (getGlyph(i, j).equals(cipher[i][j]) == inverseCipher) return false;
            }
        }

        return true;
    }

    public int getAutonomousScore() {
        return autonomousScore;
    }

    public int getTeleopScore() {
        return teleopScore;
    }

    public int getGlyphCount() { return glyphCount;}

    public int getAutonomousGlyphScore() {
        return autonomousGlyphScore;
    }

    public int getKeyColumnBonus() {
        return keyColumnBonus;
    }
    public int getKeyColumnCount() {
        return keyColumnCount;
    }

    public int getTeleopGlyphScore() {
        return teleopGlyphScore;
    }

    public int getRowBonus() {
        return rowBonus;
    }
    public int getRowCount() {
        return rowCount;
    }

    public int getColBonus() {
        return colBonus;
    }
    public int getColCount() {
        return colCount;
    }

    public int getCipherBonus() {
        return cipherBonus;
    }
    public int getCipherCount() {
        return cipherCount;
    }


    private boolean rowIsFull(int row) {
        for (int i = 0; i < COLS; i++) {
            if(getGlyph(row, i) == null) return false;
        }
        return true;
    }

    private boolean colIsFull(int col) {
        for (int i = 0; i < ROWS; i++) {
            if(getGlyph(i, col) == null) return false;
        }
        return true;
    }

    public void reset() {
        keyColumn = 0;
        isFirstGlyph = true;
        keyColumnBonus = 0;
        keyColumnCount = 0;

        for (int i = 0; i < ROWS; i++) {
            box[i] = new Glyph[COLS];
        }

        autonomousScore = 0;
        teleopScore = 0;
    }

    @VisibleForTesting
    public void setBox(Glyph[][] newBox) {
        this.box = newBox;
        updateScore();
    }

    @VisibleForTesting
    public Cryptobox swapGlyphColors() {
        Cryptobox swappedCryptoBox = new Cryptobox(alliance, keyColumn, new Glyph[ROWS][COLS]);

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                Glyph.Color currentColor = box[i][j].getColor();
                Glyph.Color swappedColor;

                if (currentColor == Glyph.Color.GRAY) {
                    swappedColor = Glyph.Color.BROWN;
                }
                else {
                    swappedColor = Glyph.Color.GRAY;
                }

                swappedCryptoBox.addGlyph(i, j, swappedColor);
            }
        }

        return swappedCryptoBox;
    }
}
