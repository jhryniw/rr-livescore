package ca.ftcalberta.rrlivescore.data;

import com.google.firebase.database.DatabaseReference;

import java.util.Locale;

import ca.ftcalberta.rrlivescore.models.Alliance;
import ca.ftcalberta.rrlivescore.models.Cryptobox;
import ca.ftcalberta.rrlivescore.models.Glyph;


public class SyncedCryptobox extends Cryptobox {

    private DatabaseReference cryptoboxRef;
    private int cryptoboxId;

    public SyncedCryptobox(Alliance alliance, int id) {
        super(alliance);

        this.cryptoboxId = id;
        this.cryptoboxRef = getRootRef();
    }

    public SyncedCryptobox(Alliance alliance, int keyColumn, Glyph[][] box, int id) {
        super(alliance, keyColumn, box);

        this.cryptoboxId = id;
        this.cryptoboxRef = getRootRef();
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
                .setValue(null);
    }

    private DatabaseReference getRootRef() {
        String strAlliance = getAlliance().toString().toLowerCase();
        String root = String.format(Locale.CANADA, "cryptobox-%s-%d", strAlliance, cryptoboxId);

        return FirebaseUtil.getCurrentMatchReference().child(root);
    }

    private String getGlyphId(int row, int col) {
        return String.format(Locale.CANADA, "glyph-%d%d", row, col);
    }
}
