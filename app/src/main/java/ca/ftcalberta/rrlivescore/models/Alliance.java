package ca.ftcalberta.rrlivescore.models;


public enum Alliance {

    RED,
    BLUE,
    NONE;

    public boolean isRed() {
        return this == RED;
    }

    public boolean isBlue() {
        return this == BLUE;
    }

    @Override
    public String toString() {
        switch (this) {
            case RED:
                return "red";
            case BLUE:
                return "blue";
            case NONE:
                return "none";
            default:
                return super.toString();
        }
    }

    public static Alliance fromString(String s) {
        switch (s) {
            case "red":
                return RED;
            case "blue":
                return BLUE;
            default:
                return NONE;
        }
    }
}
