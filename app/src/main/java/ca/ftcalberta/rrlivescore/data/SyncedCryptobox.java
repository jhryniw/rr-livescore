package ca.ftcalberta.rrlivescore.data;

import com.google.firebase.database.DatabaseReference;

import java.util.Locale;

import ca.ftcalberta.rrlivescore.models.Alliance;
import ca.ftcalberta.rrlivescore.models.Cryptobox;
import ca.ftcalberta.rrlivescore.models.Glyph;
import ca.ftcalberta.rrlivescore.models.OpMode;
import ca.ftcalberta.rrlivescore.models.Settings;


public class SyncedCryptobox extends Cryptobox {

    private DatabaseReference cryptoboxRef;
    private int cryptoboxId;
    private OpMode opMode;

    public SyncedCryptobox(Alliance alliance, OpMode opMode, int id) {
        super(alliance);

        this.cryptoboxId = id;
        this.opMode = opMode;
        this.cryptoboxRef = getRootRef();
        cryptoboxRef.removeValue();
    }

    public SyncedCryptobox(Alliance alliance, OpMode opMode, int keyColumn, Glyph[][] box, int id) {
        super(alliance, keyColumn, box);

        this.cryptoboxId = id;
        this.opMode = opMode;
        this.cryptoboxRef = getRootRef();
        cryptoboxRef.removeValue();
    }

    @Override
    public void addGlyph(int row, int col, Glyph.Color color) {
        super.addGlyph(row, col, color);

        cryptoboxRef.child(getGlyphId(row, col))
                .setValue(color);
    }

    @Override
    public void removeGlyph(int row, int col) {
        super.removeGlyph(row, col);

        cryptoboxRef.child(getGlyphId(row, col))
                .removeValue();
    }

    @Override
    protected void updateScore() {
        super.updateScore();

        if (opMode == OpMode.AUTONOMOUS) {
            cryptoboxRef.child("autonomousGlyphScore").setValue(getAutonomousGlyphScore());
            cryptoboxRef.child("keyColumnBonus").setValue(getKeyColumnBonus());
            cryptoboxRef.child("score").setValue(getAutonomousScore());
        }
        else {
            cryptoboxRef.child("score").setValue(getTeleopScore());
            cryptoboxRef.child("teleopGlyphScore").setValue(getTeleopGlyphScore());
            cryptoboxRef.child("rowBonus").setValue(getRowBonus());
            cryptoboxRef.child("colBonus").setValue(getColBonus());
            cryptoboxRef.child("cipherBonus").setValue(getCipherBonus());
        }
    }

    @Override
    public void reset() {
        super.reset();

        if (cryptoboxRef != null) {
            cryptoboxRef.removeValue();
        }
    }

    private DatabaseReference getRootRef() {
        String strAlliance = getAlliance().toString().toLowerCase();
        String strOpmode = opMode.toString().toLowerCase();

        String strId;
        if (cryptoboxId == Settings.CRYPTOBOX_BACK) {
            strId = "back";
        }
        else {
            strId = "front";
        }

        String root = String.format(Locale.CANADA, "/%s/%s/%sCryptobox", strAlliance, strId, strOpmode);

        return FirebaseUtil.getCurrentMatchReference().child(root);
    }

    private String getGlyphId(int row, int col) {
        return String.format(Locale.CANADA, "glyph%d%d", row, col);
    }
}
