package ca.ftcalberta.rrlivescore.models;


public class JewelSet {

    private static final int JEWEL_SCORE = 30;

    private boolean redIsOnPlatform;
    private boolean blueIsOnPlatform;

    private int redScore = 0;
    private int blueScore = 0;
    private int redCount = 0;
    private int blueCount = 0;

    public JewelSet() {
        redIsOnPlatform = true;
        blueIsOnPlatform = true;
    }

    public JewelSet(boolean redOnPlatform, boolean blueOnPlatform) {
        this.redIsOnPlatform = redOnPlatform;
        this.blueIsOnPlatform = blueOnPlatform;
        updateScore();
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

    public void toggleJewel(Alliance alliance) {
        if (alliance.isRed()) {
            redIsOnPlatform = !redIsOnPlatform;
        }
        else if (alliance.isBlue()) {
            blueIsOnPlatform = !blueIsOnPlatform;
        }

        updateScore();
    }

    protected void updateScore() {
        redScore = redIsOnPlatform && !blueIsOnPlatform ? JEWEL_SCORE : 0;
        blueScore = blueIsOnPlatform && !redIsOnPlatform ? JEWEL_SCORE : 0;
        redCount = redIsOnPlatform && !blueIsOnPlatform ? 1 : 0;
        blueCount = blueIsOnPlatform && !redIsOnPlatform ? 1 : 0;
    }

    public int getRedScore() {
        return redScore;
    }

    public int getBlueScore() {
        return blueScore;
    }


    public int getRedCount() {
        return redCount;
    }

    public int getBlueCount() {
        return blueCount;
    }

    public void reset() {
        redIsOnPlatform = true;
        blueIsOnPlatform = true;
        updateScore();
    }
}
