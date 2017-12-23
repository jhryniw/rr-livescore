package ca.ftcalberta.rrlivescore.models;


public class JewelSet {

    private Jewel redJewel;
    private Jewel blueJewel;

    public JewelSet(Jewel redJewel, Jewel blueJewel) {
        this.redJewel = redJewel;
        this.blueJewel = blueJewel;
    }

    public Jewel getRedJewel() {
        return redJewel;
    }

    public Jewel getBlueJewel() {
        return blueJewel;
    }

    public int getScoreForAlliance(Alliance alliance) {
        return 0;
    }
}
