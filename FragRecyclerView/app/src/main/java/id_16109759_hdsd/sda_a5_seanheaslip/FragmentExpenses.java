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

public class FragmentExpenses extends Fragment
{
    private RecyclerView myRecyclerView;
    private List<Expenses> listExpenses;

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

        myRecyclerView = (RecyclerView) view.findViewById(R.id.expenses_recyclerview);
        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getContext(), listExpenses);
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
        listExpenses = new ArrayList<>();
        listExpenses.add(new Expenses("Sean Heaslip", "Travel", 3.55,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "01/12/2017", R.drawable.ic_android_black_24dp));
        listExpenses.add(new Expenses("Sean Heaslip", "Accomodation",40.99,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "23/01/2018", R.drawable.ic_android_black_24dp));
        listExpenses.add(new Expenses("Sean Heaslip","Food", 82.29,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "24/03/2018", R.drawable.ic_android_black_24dp));
        listExpenses.add(new Expenses("Sean Heaslip","Beverage",12.95,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "01/12/2017", R.drawable.ic_android_black_24dp));
        listExpenses.add(new Expenses("Sean Heaslip","Beverage & Food",65.45,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "01/12/2017", R.drawable.ic_android_black_24dp));
        listExpenses.add(new Expenses("Sean Heaslip","Accomodation",39.00,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "01/12/2017", R.drawable.ic_android_black_24dp));
        listExpenses.add(new Expenses("Sean Heaslip","Travel",35.51,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "24/03/2018", R.drawable.ic_android_black_24dp));
        listExpenses.add(new Expenses("Sean Heaslip","Food",15.50,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "01/12/2017", R.drawable.ic_android_black_24dp));
        listExpenses.add(new Expenses("Sean Heaslip","Beverage",9,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "01/12/2017", R.drawable.ic_android_black_24dp));
        listExpenses.add(new Expenses("Sean Heaslip","Travel",24.50,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "23/01/2018", R.drawable.ic_android_black_24dp));
        listExpenses.add(new Expenses("Sean Heaslip","Accomodation",79.58,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "24/03/2018", R.drawable.ic_android_black_24dp));
        listExpenses.add(new Expenses("Sean Heaslip","Food",25.01,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "01/12/2017", R.drawable.ic_android_black_24dp));
        listExpenses.add(new Expenses("Sean Heaslip","Travel",165.98,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "01/12/2017", R.drawable.ic_android_black_24dp));
        listExpenses.add(new Expenses("Sean Heaslip","Beverage",15,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "24/03/2018", R.drawable.ic_android_black_24dp));
        listExpenses.add(new Expenses("Sean Heaslip","Travel",255,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "01/12/2017", R.drawable.ic_android_black_24dp));



    }
}
