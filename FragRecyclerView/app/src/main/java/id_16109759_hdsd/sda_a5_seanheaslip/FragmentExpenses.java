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

    private DatabaseReference mDatabaseRef;
    private List<Expense> mExpense;

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
    public interface OnItemSelectedInterface {
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
     * @param savedInstanceState
     */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Note - if time consider adding a loading/progress element

        mExpense = new ArrayList<>();
//  This shows ALL Database references loaded during testing - replacing with Specific User location
//        mDatabaseRef = FirebaseDatabase.getInstance().getReference("expenses"); // mDatabaseRef

        //Adding User specific database location:
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("expenses/users"); // mDatabaseRef
        mDatabaseRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Expense expense = postSnapshot.getValue(Expense.class);
                    mExpense.add(expense);
                }

                mAdapter = new RecyclerViewAdapter(getContext(),mExpense);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


// https://www.youtube.com/watch?v=T_QfRU-A3s4
        // 15:34 of 23:05
        // Hardcoded values using class "Expenses" not "Expense"
//        mExpense = new ArrayList<>();
//        mExpense.add(new Expenses("Sean Heaslip", "Travel", 3.55,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "01/12/2017", R.drawable.ic_android_black_24dp));
//        mExpense.add(new Expenses("Sean Heaslip", "Accomodation",40.99,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "23/01/2018", R.drawable.ic_android_black_24dp));
//        mExpense.add(new Expenses("Sean Heaslip","Food", 82.29,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "24/03/2018", R.drawable.ic_android_black_24dp));
//        mExpense.add(new Expenses("Sean Heaslip","Beverage",12.95,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "01/12/2017", R.drawable.ic_android_black_24dp));
//        mExpense.add(new Expenses("Sean Heaslip","Beverage & Food",65.45,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "01/12/2017", R.drawable.ic_android_black_24dp));
//        mExpense.add(new Expenses("Sean Heaslip","Accomodation",39.00,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "01/12/2017", R.drawable.ic_android_black_24dp));
//        mExpense.add(new Expenses("Sean Heaslip","Travel",35.51,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "24/03/2018", R.drawable.ic_android_black_24dp));
//        mExpense.add(new Expenses("Sean Heaslip","Food",15.50,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "01/12/2017", R.drawable.ic_android_black_24dp));
//        mExpense.add(new Expenses("Sean Heaslip","Beverage",9,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "01/12/2017", R.drawable.ic_android_black_24dp));
//        mExpense.add(new Expenses("Sean Heaslip","Travel",24.50,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "23/01/2018", R.drawable.ic_android_black_24dp));
//        mExpense.add(new Expenses("Sean Heaslip","Accomodation",79.58,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "24/03/2018", R.drawable.ic_android_black_24dp));
//        mExpense.add(new Expenses("Sean Heaslip","Food",25.01,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "01/12/2017", R.drawable.ic_android_black_24dp));
//        mExpense.add(new Expenses("Sean Heaslip","Travel",165.98,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "01/12/2017", R.drawable.ic_android_black_24dp));
//        mExpense.add(new Expenses("Sean Heaslip","Beverage",15,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "24/03/2018", R.drawable.ic_android_black_24dp));
//        mExpense.add(new Expenses("Sean Heaslip","Travel",255,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "01/12/2017", R.drawable.ic_android_black_24dp));



    }
}
