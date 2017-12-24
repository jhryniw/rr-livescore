package ca.ftcalberta.rrlivescore.data;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtil {

    private static FirebaseDatabase getDatabase() {
        return FirebaseDatabase.getInstance();
    }

    public static DatabaseReference getCurrentMatchReference() {
        return getDatabase().getReference("current-match");
    }
}
