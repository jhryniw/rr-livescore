package ca.ftcalberta.rrlivescore.models;


public class Relic {
    private Alliance alliance;
    private boolean upright = false;
    public int zone = 0;
    private int zoneScore;
    private int uprightScore;

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
        updateScore();
    }

    public int getZone() {
        return zone;
    }

    public void setZone(int zone) {
        this.zone = zone;
        updateScore();
    }

    public int getZoneScore() {
        return zoneScore;
    }

    public int getUprightScore() {
        return uprightScore;
    }

    public int getScore() {
        return zoneScore + uprightScore;
    }

    public void updateScore() {
        uprightScore = isUpright() ? 15 : 0;

        switch(zone) {
            case 1:
                zoneScore = 10;
                break;
            case 2:
                zoneScore = 20;
                break;
            case 3:
                zoneScore = 40;
                break;
            default:
                zoneScore = 0;
                uprightScore = 0;
                break;
        }
    }
}
