package ca.ftcalberta.rrlivescore.models;


public class Relic {
    private Alliance alliance;
    private boolean upright = false;
    public int zone = 0;

    public Relic(Alliance alliance) {
        this.alliance = alliance;
    }

    public Alliance getAlliance() {
        return alliance;
    }

    public boolean isUpright() {
        return upright;
    }

    public void setUpright(boolean upright) {
        this.upright = upright;
    }

    public int getZone() {
        return zone;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }

    public int getScore() {
        return 0;
    }
}
