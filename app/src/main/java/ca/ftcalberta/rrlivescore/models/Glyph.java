package ca.ftcalberta.rrlivescore.models;


import android.graphics.Color;

public class Glyph {
    public enum Color {
        GRAY,
        BROWN;

        private static int GRAY_COLOR = android.graphics.Color.rgb(198, 198, 198);
        private static int BROWN_COLOR = android.graphics.Color.rgb(153, 96, 96);

        public int toColor() {
            switch (this) {
                case GRAY:
                    return GRAY_COLOR;
                case BROWN:
                    return BROWN_COLOR;
            }

            return 0;
        }
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
