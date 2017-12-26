package ca.ftcalberta.rrlivescore.models;


import android.support.annotation.NonNull;

public class Glyph {
    public enum Color {
        GRAY,
        BROWN
    }

    @NonNull
    private Color color;

    public Glyph(@NonNull Color color) {
        this.color = color;
    }

    @NonNull
    public Color getColor() {
        return color;
    }

    public void setColor(@NonNull Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Glyph glyph = (Glyph) o;

        return color == glyph.color;
    }

    @Override
    public int hashCode() {
        return color.hashCode();
    }
}
