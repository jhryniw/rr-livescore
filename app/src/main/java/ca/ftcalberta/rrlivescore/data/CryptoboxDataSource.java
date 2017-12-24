package ca.ftcalberta.rrlivescore.data;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.Locale;

import ca.ftcalberta.rrlivescore.models.Alliance;
import ca.ftcalberta.rrlivescore.models.Cryptobox;

public class CryptoboxDataSource extends DataSource {

    private Cryptobox mCryptobox;

    // We cannot mutate the alliance and id
    private Alliance alliance;
    private int id;

    public CryptoboxDataSource(Cryptobox cryptobox, int id) {
        this.mCryptobox = cryptobox;

        alliance = cryptobox.getAlliance();
        this.id = id;
    }

    @Override
    Query getQuery() {
        String strAlliance = alliance.toString().toLowerCase();
        String root = String.format(Locale.CANADA, "cryptobox-%s-%d", strAlliance, id);

        return FirebaseUtil.getCurrentMatchReference().child(root);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
