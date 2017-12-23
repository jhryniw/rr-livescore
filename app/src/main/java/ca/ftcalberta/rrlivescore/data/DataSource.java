package ca.ftcalberta.rrlivescore.data;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Observable;

public abstract class DataSource extends Observable
    implements ValueEventListener {

    private DatabaseReference mDatabase;
    private Query mQuery;

    public DataSource() {
        this.mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void open() {
        mQuery = getQuery(mDatabase);
        mQuery.addValueEventListener(this);
    }

    abstract Query getQuery(DatabaseReference databaseReference);

    public void close() {
        mQuery.removeEventListener(this);
    }

    protected void datasetChanged() {
        setChanged();
        notifyObservers();
    }
}
