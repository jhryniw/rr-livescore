package ca.ftcalberta.rrlivescore.models;


import android.graphics.Color;

public class Glyph {
    public enum Color {
        GRAY,
        BROWN
    }

    private Color color;

    public Glyph(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
