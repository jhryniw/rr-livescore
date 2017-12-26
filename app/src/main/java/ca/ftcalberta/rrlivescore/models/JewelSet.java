package ca.ftcalberta.rrlivescore.models;


public class JewelSet {

    private Jewel redJewel;
    private Jewel blueJewel;

    public JewelSet(Jewel redJewel, Jewel blueJewel) {
        this.redJewel = redJewel;
        this.blueJewel = blueJewel;
    }

    public JewelSet() {
        this.redJewel = new Jewel(Alliance.RED);
        this.blueJewel = new Jewel(Alliance.BLUE);
    }

    public Jewel getRedJewel() {
        return redJewel;
    }

    public Jewel getBlueJewel() {
        return blueJewel;
    }

    public Jewel getJewelByColour(String colour){
        if(colour.equals("blue")){
            return getBlueJewel();
        } else {
            return getRedJewel();
        }
    }

    public int getScoreForAlliance(Alliance alliance) {
        return 0;
    }
}
