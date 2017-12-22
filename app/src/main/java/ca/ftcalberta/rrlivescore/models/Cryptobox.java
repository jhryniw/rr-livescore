package ca.ftcalberta.rrlivescore.models;


import android.support.annotation.VisibleForTesting;

public class Cryptobox {
    public final static int ROWS = 4;
    public final static int COLS = 3;

    private Alliance alliance;
    private int keyColumn = -1;
    private Glyph[][] box = new Glyph[ROWS][COLS];

    public Cryptobox(Alliance alliance) {
        this.alliance = alliance;
    }

    public Cryptobox(Alliance alliance, int keyColumn, Glyph[][] box) {
        this.alliance = alliance;
        this.keyColumn = keyColumn;
        this.box = box;
    }

    public Alliance getAlliance() {
        return alliance;
    }

    public int getKeyColumn() {
        return keyColumn;
    }

    public void setKeyColumn(int keyColumn) {
        this.keyColumn = keyColumn;
    }

    public void addGlyph(int row, int col, Glyph.Color color) {

    }

    public void removeGlyph(int row, int col) {

    }

    public int getGlyphCount() {
        return 0;
    }

    public int getCompleteRows() {
        return 0;
    }

    public int getCompleteColumns() {
        return 0;
    }

    public boolean cipherComplete() {
        return false;
    }

    public int getAutonomousScore() {
        return 0;
    }

    public int getTeleopScore() {
        return 0;
    }

    private boolean rowIsFull(int row) {
        return false;
    }

    public boolean colIsFull(int col) {
        return false;
    }

    @VisibleForTesting
    public void setBox(Glyph[][] newBox) {
        this.box = newBox;
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
