package ca.ftcalberta.rrlivescore.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

import ca.ftcalberta.rrlivescore.models.Alliance;
import ca.ftcalberta.rrlivescore.models.JewelSet;


public class SyncedJewelSet extends JewelSet {

    private DatabaseReference jewelSetRef;
    private int jewelSetId;

    public SyncedJewelSet(Alliance alliance, int id) {
        jewelSetRef = getRootRef();

        int allianceOffset = alliance.isRed() ? 2 : 0;
        jewelSetId = id + allianceOffset;
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

        jewelSetRef.child("blue-score").setValue(getBlueScore());
        jewelSetRef.child("red-score").setValue(getRedScore());
    }

    private DatabaseReference getRootRef() {
        String root = String.format(Locale.CANADA, "jewelset-%d", jewelSetId);
        return FirebaseUtil.getCurrentMatchReference().child(root);
    }

    private String getJewelId(Alliance alliance) {
        return alliance.toString();
    }
}
