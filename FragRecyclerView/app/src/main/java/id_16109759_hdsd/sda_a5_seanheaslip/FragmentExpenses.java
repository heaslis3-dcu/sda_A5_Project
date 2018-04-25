package id_16109759_hdsd.sda_a5_seanheaslip;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seanh on 05/03/2018.
 */

public class FragmentExpenses extends Fragment
{
    //private
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;

    private DatabaseReference mDatabaseRef; //mDatabaseRef
    private List<Expense> mExpense;
    private String userID; //userID
    private FirebaseAuth mAuth; //firebaseAuth

    String TAG = "Assign5";

    public FragmentExpenses()
    {
        Log.d(TAG, "Entering FragmentExpenses Constructor!");
    }


    /**
     * Ref: https://developer.android.com/guide/components/fragments.html#Transactions
     * Section - Creating event callbacks to the activity
     * Interface added to allow List Fragment notify the Activity when a list item is
     * selected, this ten tells the next Fragment to display the details in full.
     */
    //Adding interface to access full screen view of list item
    //Per
    public interface OnItemSelectedInterface
    {
        void onListItemSelected(int index);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {

        //  OnItemSelectedInterface listener = (OnItemSelectedInterface) getActivity();

        View view = inflater.inflate(R.layout.expense_fragment_recycler, container, false);
//        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.contact_recyclerview);
//        RecyclerViewAdapter listAdapter = new RecyclerViewAdapter(listener);

        //Hide Keyboard if visible
        Keyboard.hideSoftKeyBoardOnTabClicked(getContext());
        //Moving to OnCreate
//            mAuth = FirebaseAuth.getInstance();
//            FirebaseUser user = mAuth.getCurrentUser();
//        userID = user.getUid();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.expenses_recyclerview);
        mRecyclerView.setHasFixedSize(true); // increases performance
        //  RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getContext(), mExpense);
        mRecyclerView.setLayoutManager(new LinearLayoutManager((getActivity())));
        //mRecyclerView.setAdapter(recyclerAdapter);

        return view;

    }

    /**
     * This is where the data is added to the RecyclerView List
     * Originally hardcoded data for testing purposes,
     * Below OnCreate has been updated to display data from "Expense" Class
     * Reference:
     * https://codinginflow.com/code-examples/android/
     * firebase-storage-upload-and-retrieve-images/part-6
     * Date: 05/04/2018
     * Hardcoded arrayList<> contains the data
     *
     * @param savedInstanceState
     */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Note - if time consider adding a loading/progress element

        //Moved authentication to OnCreate - as it was returning NULL when entered in OnCreateView
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
        mExpense = new ArrayList<>();
//  This shows ALL Database references loaded during testing - replacing with Specific User location
//        mDatabaseRef = FirebaseDatabase.getInstance().getReference("expenses"); // mDatabaseRef

        //Adding User specific database location - "expenses/users/...userID/
        //This will permit users to ONLY view their data.
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("expenses/users/" + userID + "/"); // mDatabaseRef
        Log.d(TAG, "Check mDatabaseRed ExpFrag: " + mDatabaseRef);
        mDatabaseRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                //Clears ArrayList prior to adding new image
                //Issue with temporary duplication of previously loaded data resolved.
                mExpense.clear();
                //showData(dataSnapshot);

                // Working when all items in Expense are String
                // Updating due to double in Expense Class
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    Expense expense = postSnapshot.getValue(Expense.class);
                    mExpense.add(expense);
                }
                mAdapter = new RecyclerViewAdapter(getContext(), mExpense);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });



    }


}
