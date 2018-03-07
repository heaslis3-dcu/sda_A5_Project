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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seanh on 05/03/2018.
 */

public class FragmentContact extends Fragment
{
    private RecyclerView myRecyclerView;
    private List<Contact> listContact;

    String TAG = "Assign5";
    public FragmentContact()
    {
        Log.d(TAG, "Entering FragmentContact Constructor!");
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

        View view = inflater.inflate(R.layout.contact_fragment, container, false);
//        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.contact_recyclerview);
//        RecyclerViewAdapter listAdapter = new RecyclerViewAdapter(listener);

        myRecyclerView = view.findViewById(R.id.contact_recyclerview);
        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getContext(),listContact);
        myRecyclerView.setLayoutManager(new LinearLayoutManager((getActivity())));
        myRecyclerView.setAdapter(recyclerAdapter);

        return view;

    }

    /**
     * This is where the data is added to the RecyclerView List
     * Hardcoded arrayList<> contains the data
     * @param savedInstanceState
     */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
// https://www.youtube.com/watch?v=T_QfRU-A3s4
        // 15:34 of 23:05
        listContact = new ArrayList<>();
        listContact.add(new Contact("Sean Heaslip","(353) 876888229", R.drawable.ic_android_black_24dp));
        listContact.add(new Contact("Sean Heaslip","(353) 876888229", R.drawable.ic_android_black_24dp));
        listContact.add(new Contact("Sean Heaslip","(353) 876888229", R.drawable.ic_android_black_24dp));
        listContact.add(new Contact("Sean Heaslip","(353) 876888229", R.drawable.ic_android_black_24dp));
        listContact.add(new Contact("Sean Heaslip","(353) 876888229", R.drawable.ic_android_black_24dp));
        listContact.add(new Contact("Sean Heaslip","(353) 876888229", R.drawable.ic_android_black_24dp));
        listContact.add(new Contact("Sean Heaslip","(353) 876888229", R.drawable.ic_android_black_24dp));
        listContact.add(new Contact("Sean Heaslip","(353) 876888229", R.drawable.ic_android_black_24dp));
        listContact.add(new Contact("Sean Heaslip","(353) 876888229", R.drawable.ic_android_black_24dp));
        listContact.add(new Contact("Sean Heaslip","(353) 876888229", R.drawable.ic_android_black_24dp));
        listContact.add(new Contact("Sean Heaslip","(353) 876888229", R.drawable.ic_android_black_24dp));
        listContact.add(new Contact("Sean Heaslip","(353) 876888229", R.drawable.ic_android_black_24dp));
        listContact.add(new Contact("Sean Heaslip","(353) 876888229", R.drawable.ic_android_black_24dp));
        listContact.add(new Contact("Sean Heaslip","(353) 876888229", R.drawable.ic_android_black_24dp));
        listContact.add(new Contact("Sean Heaslip","(353) 876888229", R.drawable.ic_android_black_24dp));



    }
}