package ca.ftcalberta.rrlivescore.models;


public class Jewel {
    private Alliance alliance;
    private boolean onPlatform = true;

    public Jewel(Alliance alliance) {
        this.alliance = alliance;
    }

    public Alliance getAlliance() {
        return alliance;
    }

    public boolean isOnPlatform() {
        return onPlatform;
    }

    public void setOnPlatform(boolean onPlatform) {
        this.onPlatform = onPlatform;
    }
}
