package ca.ftcalberta.rrlivescore.models;


public class Glyph {
    public enum GlyphColor { GRAY, BROWN }

    private GlyphColor color;

    public Glyph(GlyphColor color) {
        this.color = color;
    }

    public GlyphColor getColor() {
        return color;
    }

    public void setColor(GlyphColor color) {
        this.color = color;
    }
}
