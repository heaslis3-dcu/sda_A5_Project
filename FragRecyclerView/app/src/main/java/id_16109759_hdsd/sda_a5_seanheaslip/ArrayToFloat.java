package id_16109759_hdsd.sda_a5_seanheaslip;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by seanh on 08/04/2018.
 */

public class ArrayToFloat
{
    private DatabaseReference mDatabaseRef;
    private String userID; //userID
    private FirebaseAuth mAuth; //firebaseAuth


    public ArrayToFloat()
    {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
    }
}
