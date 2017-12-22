package ca.ftcalberta.rrlivescore.models;


public class Cryptobox {
    public final static int ROWS = 4;
    public final static int COLS = 3;

    private Alliance alliance;
    private int keyColumn;
    private Glyph[][] box = new Glyph[ROWS][COLS];

    public Cryptobox(Alliance alliance) {
        this.alliance = alliance;
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

    public int getScore() {
        return 0;
    }

    private boolean rowIsFull(int row) {
        return false;
    }

    public boolean colIsFull(int col) {
        return false;
    }
}
