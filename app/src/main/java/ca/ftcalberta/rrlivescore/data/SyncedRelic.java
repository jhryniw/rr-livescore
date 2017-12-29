package ca.ftcalberta.rrlivescore.data;

import com.google.firebase.database.DatabaseReference;

import java.util.Locale;

import ca.ftcalberta.rrlivescore.models.Alliance;
import ca.ftcalberta.rrlivescore.models.Relic;
import ca.ftcalberta.rrlivescore.models.Settings;


public class SyncedRelic extends Relic {

    private DatabaseReference relicRef;
    private DatabaseReference uprightRef;
    private int relicId;


    public SyncedRelic(Alliance alliance)
    {
        super(alliance);
        relicRef = getRootRef("relic");
        uprightRef = getRootRef("upright");
    }

    @Override
    public void setZone(int zone) {
        super.setZone(zone);
        relicRef.child("zone").setValue(getZone());
    }

    @Override
    public void setUpright(boolean upright) {
        super.setUpright(upright);
        uprightRef.child("upright").setValue(isUpright());
    }

    @Override
    public void updateScore() {
        super.updateScore();

        relicRef.child("score").setValue(getZoneScore());
        uprightRef.child("score").setValue(getUprightScore());
    }


    private DatabaseReference getRootRef(String type) {
        int id = Settings.getInstance().getCryptoboxId();
        String strAlliance = Settings.getInstance().getAlliance().toString().toLowerCase();

        String strId;
        if (id == Settings.CRYPTOBOX_BACK) {
            strId = "back";
        }
        else {
            strId = "front";
        }
        String root = String.format(Locale.CANADA, "%s/%s/%s", strAlliance,  strId, type);

        return FirebaseUtil.getCurrentMatchReference().child(root);
    }


}
