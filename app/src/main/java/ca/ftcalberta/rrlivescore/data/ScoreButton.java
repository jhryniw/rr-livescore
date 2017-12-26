package ca.ftcalberta.rrlivescore.data;

import com.google.firebase.database.DatabaseReference;

import java.util.Locale;

import ca.ftcalberta.rrlivescore.models.OpMode;
import ca.ftcalberta.rrlivescore.models.Settings;

/**
 * Created by a on 26/12/2017.
 */

public class ScoreButton {




    private DatabaseReference buttonRef;

    public void ScoreButton(String buttonTag, int value){
        buttonRef = getRootRef(buttonTag);
        buttonRef.child(buttonTag).setValue(value);

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
