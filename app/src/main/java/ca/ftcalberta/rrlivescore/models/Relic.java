package ca.ftcalberta.rrlivescore.models;


public class Relic {
    private Alliance alliance;
    private boolean upright = false;
    private int zone = 0;
    private int zoneScore = 0;
    private int zone1Count = 0;
    private int zone2Count = 0;
    private int zone3Count = 0;
    private int uprightScore = 0;
    private int uprightCount = 0;

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
    public int getZone1Count() {
        return zone1Count;
    }
    public int getZone2Count() {
        return zone2Count;
    }
    public int getZone3Count() {
        return zone3Count;
    }

    public int getUprightScore() {
        return uprightScore;
    }
    public int getUprightCount() {
        return uprightCount;
    }

    public int getScore() {
        return zoneScore + uprightScore;
    }

    public void updateScore() {
        uprightScore = isUpright() ? 15 : 0;
        uprightCount = isUpright() ? 1 : 0;
        zone1Count = 0;
        zone2Count = 0;
        zone3Count = 0;

        switch(zone) {
            case 1:
                zoneScore = 10;
                zone1Count = 1;
                break;
            case 2:
                zoneScore = 20;
                zone2Count = 1;
                break;
            case 3:
                zoneScore = 40;
                zone3Count = 1;
                break;
            default:
                zoneScore = 0;
                uprightScore = 0;
                break;
        }
    }

    public void reset(){
        zone = 0;
        upright = false;
        uprightScore = 0;
        uprightCount = 0;
        zoneScore = 0;
        zone1Count = 0;
        zone2Count = 0;
        zone3Count = 0;
    }
}
