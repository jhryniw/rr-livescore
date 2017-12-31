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

    public void updateScore(int value){

        buttonRef.child("score").setValue(value);
        buttonRef.child("count").setValue(value == 0 ? 0 : 1);
    }

    public void reset(){
        buttonRef.removeValue();
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

        String root = String.format(Locale.CANADA, "%s/%s/%s",strAlliance,  strId, buttonTag);

        return FirebaseUtil.getCurrentMatchReference().child(root);
    }

}
