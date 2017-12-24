package ca.ftcalberta.rrlivescore.data;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtil {

    public static DatabaseReference getCurrentMatchReference() {
        return FirebaseDatabase.getInstance().getReference("current-match");
    }

}
