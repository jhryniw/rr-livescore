package ca.ftcalberta.rrlivescore.data;

import com.google.firebase.database.DatabaseReference;

import java.util.Locale;

import ca.ftcalberta.rrlivescore.models.OpMode;
import ca.ftcalberta.rrlivescore.models.Settings;

public class ScoreButton {

    private DatabaseReference buttonRef;
    private String buttonTag;

    public ScoreButton(String tag){
        buttonTag = tag;
        buttonRef = getRootRef(buttonTag);
    }

    public void updateScore(String buttonTag, int value){
        buttonRef.child("score").setValue(value);
    }

    private DatabaseReference getRootRef(String buttonTag) {
        int id = Settings.getInstance().getCryptoboxId();
        String strAlliance = Settings.getInstance().getAlliance().toString().toLowerCase();

        String strId;
        if (id == Settings.CRYPTOBOX_BACK) {
            strId = "back";
        }
        else {
            strId = "front";
        }

        String root = String.format(Locale.CANADA, "%s-%s-%s",buttonTag, strAlliance,  strId);

        return FirebaseUtil.getCurrentMatchReference().child(root);
    }

}
