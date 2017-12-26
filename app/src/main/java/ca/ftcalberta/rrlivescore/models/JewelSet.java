package ca.ftcalberta.rrlivescore.models;


public class JewelSet {

    private boolean redIsOnPlatform;
    private boolean blueIsOnPlatform;

    public JewelSet() {
        redIsOnPlatform = true;
        blueIsOnPlatform = true;
    }

    public JewelSet(boolean redOnPlatform, boolean blueOnPlatform) {
        this.redIsOnPlatform = redOnPlatform;
        this.blueIsOnPlatform = blueOnPlatform;
    }

    public boolean isOnPlatform(Alliance alliance) {
        if (alliance.isRed()) {
            return redIsOnPlatform;
        }
        else if (alliance.isBlue()) {
            return blueIsOnPlatform;
        }
        return false;
    }

    public void setOnPlatform(Alliance alliance, boolean onPlatform) {
        if (alliance.isRed()) {
            redIsOnPlatform = onPlatform;
        }
        else if (alliance.isBlue()) {
            blueIsOnPlatform = onPlatform;
        }
    }

    public void toggleJewel(Alliance alliance) {

    }

    public int getScoreForAlliance(Alliance alliance) {
        return 0;
    }
}
