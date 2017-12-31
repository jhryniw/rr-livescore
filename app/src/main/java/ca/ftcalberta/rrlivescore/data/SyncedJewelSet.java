package ca.ftcalberta.rrlivescore.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

import ca.ftcalberta.rrlivescore.models.Alliance;
import ca.ftcalberta.rrlivescore.models.JewelSet;
import ca.ftcalberta.rrlivescore.models.Settings;


public class SyncedJewelSet extends JewelSet {

    private DatabaseReference jewelSetRef;
    private int jewelSetId;

    public SyncedJewelSet(Alliance alliance, int id) {
        int allianceOffset = alliance.isRed() ? 2 : 0;
        jewelSetId = id + allianceOffset;
        jewelSetRef = getRootRef();
    }

    @Override
    public void toggleJewel(Alliance alliance) {
        super.toggleJewel(alliance);

        jewelSetRef.child(getJewelId(alliance))
                .setValue(isOnPlatform(alliance));
    }

    @Override
    protected void updateScore() {
        super.updateScore();

        jewelSetRef.child("blueScore").setValue(getBlueScore());
        jewelSetRef.child("redScore").setValue(getRedScore());
        jewelSetRef.child("blueCount").setValue(getBlueCount());
        jewelSetRef.child("redCount").setValue(getRedCount());
    }

    @Override
    public void reset() {
        super.reset();

        jewelSetRef.removeValue();
    }

    private DatabaseReference getRootRef() {
        int id = Settings.getInstance().getCryptoboxId();
        String strAlliance = Settings.getInstance().getAlliance().toString().toLowerCase();

        String strId;
        if (id == Settings.CRYPTOBOX_BACK) {
            strId = "back";
        }
        else {
            strId = "front";
        }
        String root = String.format(Locale.CANADA, "%s/%s/jewel", strAlliance,  strId);

        return FirebaseUtil.getCurrentMatchReference().child(root);
    }

    private String getJewelId(Alliance alliance) {
        return alliance.toString();
    }
}
