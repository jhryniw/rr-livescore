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
    public void updateScore() {
        super.updateScore();

        relicRef.child("relic_zone").setValue(getZone());
        relicRef.child("score").setValue(getZoneScore());
        uprightRef.child("relic_upright").setValue(isUpright());
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
        String root = String.format(Locale.CANADA, "%s-%s-%s", type, strAlliance,  strId);

        return FirebaseUtil.getCurrentMatchReference().child(root);
    }


}
