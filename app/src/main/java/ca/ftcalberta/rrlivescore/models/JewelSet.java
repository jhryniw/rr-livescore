package ca.ftcalberta.rrlivescore.models;


import ca.ftcalberta.rrlivescore.utils.Resetable;

public class JewelSet implements Resetable {

    private static final int JEWEL_SCORE = 30;

    private boolean redIsOnPlatform;
    private boolean blueIsOnPlatform;

    private int redScore = 0;
    private int blueScore = 0;

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
    }

    public int getRedScore() {
        return redScore;
    }

    public int getBlueScore() {
        return blueScore;
    }

    @Override
    public void reset() {
        redIsOnPlatform = true;
        blueIsOnPlatform = true;
        updateScore();
    }
}
