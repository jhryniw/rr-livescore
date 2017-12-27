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

    private int autonomousScore = 0;
    private int teleopScore = 0;

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
        int glyphCount = getGlyphCount();
        int autonomousGlyphScore = glyphCount * GLYPH_AUTONOMOUS_SCORE;
        int teleopGlyphScore = glyphCount * GLYPH_TELEOP_SCORE;

        int rowBonus = getCompleteRows() * ROW_BONUS;
        int colBonus = getCompleteColumns() * COL_BONUS;
        int cipherBonus = isCipherComplete() ? CIPHER_BONUS : 0;

        autonomousScore = autonomousGlyphScore + keyColumnBonus;
        teleopScore = teleopGlyphScore + rowBonus + colBonus + cipherBonus;
    }

    public int getGlyphCount() {
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
        if (getGlyphCount() != ROWS * COLS) return false;

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
