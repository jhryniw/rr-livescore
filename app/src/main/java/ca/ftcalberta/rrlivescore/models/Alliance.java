package ca.ftcalberta.rrlivescore.models;


public enum Alliance {

    RED,
    BLUE,
    NONE;

    @Override
    public String toString() {
        switch (this) {
            case RED:
                return "Red";
            case BLUE:
                return "Blue";
            default:
                return super.toString();
        }
    }
}
