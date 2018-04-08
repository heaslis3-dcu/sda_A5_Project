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
import android.widget.Switch;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by seanh on 05/03/2018.
 */

public class FragmentGraph extends Fragment
{
    /**
     * Reference for working example graph:
     * Modified code from:
     * https://github.com/mitchtabian/Pie-Chart-Tutorial/blob/master/
     * PieChartTutorial/app/src/main/java/com/example/user/piecharttutorial/MainActivity.java
     * Date: 11/03/2018
     */
//    String str = "20.05";
//    float fl = Float.parseFloat(str.toString());
   // private List<String> expenType = new ArrayList<String>();
    // private List<Float> expenAmount = new ArrayList<Float>();
    private List<String> expenType;
    private List<Float> expenAmount;
    //NB - Check if this will help to populate the data:
//  https://stackoverflow.com/questions/4837568/java-convert-arraylistfloat-to-float

    private float[] floatArray;
    private float[] newFloatArray;
 //   private float[] yData = {200.5f, 150.99f, 35.5f, 28.02f, 129.58f, 86.78f, 65.50f, 98.99f, 300f};

    private String[] stringArray;
    private String[] newStringArray;
//    private String[] xData = {"Flights", "Accomodation", "Beverages", "Food", "Client",
//            "Other", "Ground Transport", "Entertainment", "Taxi"};
    PieChart pieChart;
    String TAG = "Assign5";
    //private
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;

    private DatabaseReference mDatabaseRef;
    private List<Expense> mExpType;
    private List<Expense> mExpAmount;

//    String[] amounts;
//    String[] expenseTypes;

//    private DatabaseReference mDatabaseRef; //mDatabaseRef
   // private List<Expense> mExpense;
    private String userID; //userID
    private FirebaseAuth mAuth; //firebaseAuth

    private float sumAccomodation;
    private float sumFlights;
    private float sumMeals;
    private float sumOther;
    private float sumTransport;
    //Array lists
//    private List<String[]> expenseType = new ArrayList<String[]>();
//    private List<String[]> expenseAmount = new ArrayList<String[]>();

    //Testing FLOAT
  //  private float[] floatExpAmount = {};
  //  private String[] floatExpType = {};

    //+++++++++++++++++++++++++++++++++++++++//
    //Turned off to test floats
    //private List<String> expenseType = new ArrayList<String>();
   // private List<float> expenseAmount = new ArrayList<float>();
    //+++++++++++++++++++++++++++++++++++++++//

//    final List<String> expenseType = new ArrayList<String>();
//    final List<String> expenseAmount = new ArrayList<String>();

    //private RecyclerView myRecyclerView;
    //   private List<Expenses> listExpenses;

//    float myExpAmounts[] = {90.5f, 21.2f, 22.5f, 21.5f};
//    String myTypeExp[] = {"Travel","Accommodation", "Food", "Travel"};

//    public FragmentGraph()
//    {
//        Log.d(TAG, "Entering FragmentGraph constructor!");
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.graph_fragment, container, false);
        //Hide Keyboard if visible
        Keyboard.hideSoftKeyBoardOnTabClicked(getContext());
        Log.d(TAG, "onCreate: starting to create chart");
        pieChart = (PieChart) view.findViewById(R.id.idPieChart);

       pieChart.setDescription(" ");
        pieChart.setRotationEnabled(true);
        //pieChart.setUsePercentValues(true);
        //pieChart.setHoleColor(Color.BLUE);
        //pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(50f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Expense breakdown YTD (€)");
        pieChart.setCenterTextSize(16);

        //pieChart.setDrawEntryLabels(true);
        //pieChart.setEntryLabelTextSize(20);
        //More options just check out the documentation!

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();


        //  This shows ALL Database references loaded during testing
        //Adding User specific database location:
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("expenses/users/" + userID + "/");
        //Value Event Listener
        mDatabaseRef.addValueEventListener(new ValueEventListener()
        {
            /**
             * Map modified from example in Fragment Expenses,
             * And using below, in addition to some trial and error testing
             * Reference - https://stackoverflow.com/questions/37688031/
             * class-java-util-map-has-generic-type-parameters-please-use-generictypeindicator
             * @param dataSnapshot
             */
            //@Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                // Get Data from Database for Expense type and Amount
                // returning Name and Amount in Log testing :D
                //https://stackoverflow.com/questions/38492827/how-to-get-a-string-list-from-firebase-to-fill-a-spinner
                //Turned off to test FLOAT
//                final List<String> expenseType = new ArrayList<String>();
//                final List<String> expenseAmount = new ArrayList<String>();
                expenType = new ArrayList<String>();
                expenAmount = new ArrayList<Float>();

                for(DataSnapshot postSnapshot: dataSnapshot.getChildren())
                {


//                    Map<String, Float> map = new HashMap<String, Float>();
//
//                    float amount = map.get("expAmount");
                   // String type = map.get("expenseType");
                    //------------------------------------------------
                    String exptype = postSnapshot.child("expenseType").getValue(String.class);
                    //String expAmt = postSnapshot.child("expAmount").getValue(String.class);
                    float expAmt = postSnapshot.child("expAmount").getValue(Float.TYPE);
                    expenType.add(exptype);
                    expenAmount.add(expAmt);
                    //-----------------------------------------
                    Log.d(TAG, "Expense Type: " + exptype);
                    Log.d(TAG, "Expense Amount: " + expAmt);

                    //https://stackoverflow.com/questions/46898/how-to-efficiently-iterate-over-each-entry-in-a-map
//WORKING PART
//                    Map<String, String> map = (Map) postSnapshot.getValue();
//                    String type = map.get("expenseType");
//                    String amount = map.get("expAmount");
//                    Log.d(TAG, "Expense Name: " + type);
//                    Log.d(TAG, "Expense Amount: " + amount);
//END OF WORKING

                }



               // int len = expenAmount.size();
                //Populate float Array for use in Graph - MPANDROIDCHART REQUIRES float[] to populate data
                floatArray = new float[expenAmount.size()];
                for(int i = 0; i < expenAmount.size(); i++) {
                    floatArray[i] = expenAmount.get(i);
                }


                //Populate float Array for use in Graph - MPANDROIDCHART REQUIRES float[] to populate data
                stringArray = new String[expenType.size()];
                for(int i = 0; i < expenType.size(); i++) {
                    stringArray[i] = expenType.get(i);
                }

                // Summation - See Method and references below OnCreateView
               int uniqueEntriesInStrArr =  diffValues(stringArray);

                // initialise Arrays:
                newStringArray = new String[uniqueEntriesInStrArr];
                newFloatArray = new float[uniqueEntriesInStrArr];
               // int uniquetypes = uniqueEntriesInStrArr;
                float sumAccomodation = 0.00f;
                float sumFlights = 0.00f;
                float sumMeals = 0.00f;
                float sumOther = 0.00f;
                float sumTransport = 0.00f;
                String accom = "Accomodation";
                String fly = "Flights";
                String food = "Meals";
                String other = "Other";
                String trans = "Transport";
                // initialise Arrays:
                newStringArray = new String[uniqueEntriesInStrArr];
                newFloatArray = new float[uniqueEntriesInStrArr];
                for (int i =0; i < stringArray.length; i++)
                {
                    if(stringArray[i].contains(accom)){
                        sumAccomodation += floatArray[i];
                    } else if(stringArray[i].contains(fly)){
                        sumFlights += floatArray[i];
                    } else if(stringArray[i].contains(food)){
                        sumMeals += floatArray[i];
                    } else if(stringArray[i].contains(other)){
                        sumOther += floatArray[i];
                    } else if(stringArray[i].contains(trans)){
                        sumTransport += floatArray[i];
                    }
                    Log.d(TAG, "onCreateView - INSIDE METHOD check SUMMATION is working (1580) " + sumTransport);
                }
              newStringArray = new String[]{"Accomodation","Flights","Meals","Other","Transport"};
               newFloatArray = new float[] {sumAccomodation, sumFlights, sumMeals, sumOther, sumTransport};
               // floatArray2 = new float[expenAmount.size()];

                //Can access ArrayList here
                Log.d(TAG, "onCreateView - expenseType Length " + expenType.size());
                Log.d(TAG, "onCreateView - expenseAmount Length " + expenAmount.size());
                //Testing length of floatArray[] & strainArray[] matches length of ListArray
                Log.d(TAG, "onCreateView - floatArray Length " + floatArray.length);
                Log.d(TAG, "onCreateView - String Array Length " + stringArray.length);
                //Testing that floatArray[] & strainArray[] populated
                Log.d(TAG, "onCreateView - float Array position [2] " + newFloatArray[1]);
                Log.d(TAG, "onCreateView - String Array position [2] " + newStringArray[1]);
                Log.d(TAG, "Checking length of uniqueEntriesInStrArr after using method 'diffValues' " + uniqueEntriesInStrArr);
                Log.d(TAG, "onCreateView - check SUMMATION is working (1580) " + sumTransport);
              //  Log.d(TAG, "onCreateView - String Array position [2] " + newStringArray[1]);
/**
 * This method is a work around as I discovered Real Time data is not supported by
 * MPAndroidChart
 * See notes: https://github.com/PhilJay/MPAndroidChart/wiki/Dynamic-&-Realtime-Data
 * Method data addDataSet() - from hardcoded Data added here.
 */
                Log.d(TAG, "addDataSet started");

                //FLOAT DATA - Populating values of PIE CHART,
                ArrayList<PieEntry> yEntrys = new ArrayList<>();
                for (int i = 0; i < newFloatArray.length; i++)
                {
                    if(newFloatArray.length <=1) {
                        Toast.makeText(getActivity(), "Load more data to view.", Toast.LENGTH_SHORT).show();
                    }
                    if(newFloatArray[i] != 0){
                        yEntrys.add(new PieEntry(newFloatArray[i], i));
                    }

                }
                //STRING DATA - Populating text values of PIE CHART,
                ArrayList<String> xEntrys = new ArrayList<>();
                for (int i = 1; i < newStringArray.length; i++)
                {
                    if(newFloatArray[i] != 0){
                        xEntrys.add(newStringArray[i]);
                    }
                }

                //create the data set in piechart
                PieDataSet pieDataSet = new PieDataSet(yEntrys, "Legend");
                pieDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS); //add colors to dataset
                pieDataSet.setSliceSpace(2);
                pieDataSet.setValueTextSize(16);

                //add legend to chart
                Legend legend = pieChart.getLegend();
                legend.setForm(Legend.LegendForm.CIRCLE);
                legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
                legend.setTextSize(16);

                //create pie data object
                PieData pieData = new PieData(pieDataSet);
                pieChart.setData(pieData);
                pieChart.animateY(1500); //animates the Y axis
                pieChart.invalidate();
           }



            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        //Test - cannot access Arraylist here
        //This section runs before above ArrayList is populated.
//        Log.d(TAG, "onCreateView - expenseType Length " + expenseType.size()+10);
//        Log.d(TAG, "onCreateView - expenseAmount Length " + expenseAmount.size()+10);
//        Log.d(TAG, "onCreateView - expenseType Length " + expenType.size());
//        Log.d(TAG, "onCreateView - expenseAmount Length " + expenAmount.size());



        // This part of the code, allows user to click on PIE CHART
        // And return the String  + TOTAL Amount
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener()
        {
            @Override
            public void onValueSelected(Entry e, Highlight h)
            {
                Log.d(TAG, "onValueSelected: Value select from chart.");
                Log.d(TAG, "onValueSelected: " + e.toString());
                Log.d(TAG, "onValueSelected: " + h.toString());

                int pos1 = e.toString().indexOf("(sum): ");
                String expenseCost = e.toString().substring(pos1 + 7);

                for (int i = 0; i < newFloatArray.length; i++)
                {
                    if (newFloatArray[i] == Float.parseFloat(expenseCost))
                    {
                        pos1 = i;
                        break;
                    }
                }
                String expenseType = newStringArray[pos1];
                Toast.makeText(getActivity(), "Expense Type: " + expenseType + "\n" +
                                                    "Total: €" + expenseCost,
                                                    Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected()
            {

            }
        });
        return view;

    }

    /**
     * Modified from source which used int[],
     * Using the below to count the number of unique entries in String Array
     * Reference:  https://stackoverflow.com/questions/32444193/
     * count-different-values-in-array-in-java
     * Date: 08/04/2018
     * @param numArray
     * @return
     */
    public static int diffValues(String[] numArray){
        int numOfDifferentVals = 0;

        ArrayList<String> diffNum = new ArrayList<>();

        for(int i=0; i<numArray.length; i++){
            if(!diffNum.contains(numArray[i])){
                diffNum.add(numArray[i]);
            }
        }
        //modified this part as it may have been incorrect,
        // Original Method was not providing the true int length of the Array
        // containing unique values
        if(diffNum.size()==0){
            numOfDifferentVals = 0;
        }
        else{
            numOfDifferentVals = diffNum.size()+1;
        }

        return numOfDifferentVals;
    }

    // I may want to combine some of the below code in the OnCreateView method as a fix


//    private void addDataSet()
//    {
//        Log.d(TAG, "addDataSet started");
//        ArrayList<PieEntry> yEntrys = new ArrayList<>();
//        ArrayList<String> xEntrys = new ArrayList<>();
//
//        for (int i = 0; i < floatArray.length; i++)
//        {
//            yEntrys.add(new PieEntry(floatArray[i], i));
//        }
//
//        for (int i = 1; i < stringArray.length; i++)
//        {
//            xEntrys.add(stringArray[i]);
//        }
//
//        //create the data set in piechart
//        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Legend");
//        pieDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS); //add colors to dataset
//        pieDataSet.setSliceSpace(2);
//        pieDataSet.setValueTextSize(16);
//
//        //add legend to chart
//        Legend legend = pieChart.getLegend();
//        legend.setForm(Legend.LegendForm.CIRCLE);
//        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
//        legend.setTextSize(16);
//
//        //create pie data object
//        PieData pieData = new PieData(pieDataSet);
//        pieChart.setData(pieData);
//        pieChart.animateY(1500); //animates the Y axis
//        pieChart.invalidate();
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

//            addDataSet();





        //Test - cannot access Arraylist here
        //This section runs before above ArrayList is populated.
//        Log.d(TAG, "onCreateView - expenseType Length " + expenseType.size()+10);
//        Log.d(TAG, "onCreateView - expenseAmount Length " + expenseAmount.size()+10);






        // Note - if time consider adding a loading/progress element

//
//
//        mAuth = FirebaseAuth.getInstance();
//        FirebaseUser user = mAuth.getCurrentUser();
//        userID = user.getUid();
//        mExpense = new ArrayList<>();
////  This shows ALL Database references loaded during testing - replacing with Specific User location
////        mDatabaseRef = FirebaseDatabase.getInstance().getReference("expenses"); // mDatabaseRef
//
//        //Adding User specific database location:
//        mDatabaseRef = FirebaseDatabase.getInstance().getReference("expenses/users/" + userID + "/"); // mDatabaseRef
//        mDatabaseRef.addValueEventListener(new ValueEventListener()
//        {
//            /**
//             * Map modified from example in Fragment Expenses,
//             * And using below, in addition to some trial and error testing
//             * Reference - https://stackoverflow.com/questions/37688031/
//             * class-java-util-map-has-generic-type-parameters-please-use-generictypeindicator
//             * @param dataSnapshot
//             */
//           //@Override
//            public void onDataChange(DataSnapshot dataSnapshot)
//            {
//                // Get Data from Database for Expense type and Amount
//                // returning Name and Amount in Log testing :D
//
//                //https://stackoverflow.com/questions/38492827/how-to-get-a-string-list-from-firebase-to-fill-a-spinner
//                final List<String> expenseType = new ArrayList<String>();
//                final List<String> expenseAmount = new ArrayList<String>();
////                    String[] expenseType;
////                    String[] expenseAmount;
//                for(DataSnapshot postSnapshot: dataSnapshot.getChildren())
//                {
//
//                    String exptype = postSnapshot.child("expenseType").getValue(String.class);
//                    String expAmt = postSnapshot.child("expAmount").getValue(String.class);
//                    expenseType.add(exptype);
//                    expenseAmount.add(expAmt);
//
//                    Log.d(TAG, "Expense Type: " + exptype);
//                    Log.d(TAG, "Expense Amount: " + expAmt);
//                    //https://stackoverflow.com/questions/46898/how-to-efficiently-iterate-over-each-entry-in-a-map
////WORKING PART
////                    Map<String, String> map = (Map) postSnapshot.getValue();
////                    String type = map.get("expenseType");
////                    String amount = map.get("expAmount");
////                    Log.d(TAG, "Expense Name: " + type);
////                    Log.d(TAG, "Expense Amount: " + amount);
////END OF WORKING
//                   // Map<String, String> map = (Map) postSnapshot.getValue();
//                    //double x = Double.parseDouble(map.get("expAmount").toString());
//
//                    //Remove comma from String
////                    if(amount.contains(","))
////                    {
////                        amount.replace(",", "");
////                    }
////                    double dAmount = Double.parseDouble(amount.toString());
//
////                    for(Map.Entry<String, String> entry : map.entrySet()) {
////                        amounts = entry.getValue();
////                    }
////                    String[] results = map.values().toArray(new String[0]);
////                    Log.d(TAG, "String Array: " + results);
//
////                    Log.d(TAG, "Double Amount: " + dAmount);
//                }
//            }
//
//
//
//            @Override
//            public void onCancelled(DatabaseError databaseError)
//            {
//                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });


    }



    ///////////////////////////////////////////////////////

//    public static String trimCommaOfString(String string) {
////        String returnString;
//        if(string.contains(",")){
//            return string.replace(",","");}
//        else {
//            return string;
//        }
//
//    }

/*
    private void setupPieChart()
    {

        //populating a list of pie entries
        List<PieEntry> pieEntrList = new ArrayList<>();
        for (int i = 0; i <myExpAmounts.length; i++ ) {
            pieEntrList.add(new PieEntry(myExpAmounts[i],myTypeExp[i]));

//            pieEntrList.add(new PieEntry((float)(listExpenses.get(i).getExpAmount()),
//                                                 listExpenses.get(i).getExpenseType()));
        }
        PieDataSet dataSet = new PieDataSet(pieEntrList,"Expenses by amount");
        PieData data = new PieData(dataSet);

        // Get the chart
        // Ref use of getView:
        // https://stackoverflow.com/questions/6495898/findviewbyid-in-fragment
        // Date 11/03/2018
        PieChart chart = (PieChart) getView().findViewById(R.id.idPieChart);
        chart.setData(data);
        chart.invalidate();

    }
*/
   /*
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setupPieChart();
// https://www.youtube.com/watch?v=T_QfRU-A3s4
        // 15:34 of 23:05
//        listExpenses = new ArrayList<>();
//        listExpenses.add(new Expenses("Sean Heaslip", "Travel", 3.55,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "01/12/2017", R.drawable.ic_android_black_24dp));
//        listExpenses.add(new Expenses("Sean Heaslip", "Accomodation",40.99,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "23/01/2018", R.drawable.ic_android_black_24dp));
//        listExpenses.add(new Expenses("Sean Heaslip","Food", 82.29,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "24/03/2018", R.drawable.ic_android_black_24dp));
//        listExpenses.add(new Expenses("Sean Heaslip","Beverage",12.95,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "01/12/2017", R.drawable.ic_android_black_24dp));
//        listExpenses.add(new Expenses("Sean Heaslip","Beverage & Food",65.45,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "01/12/2017", R.drawable.ic_android_black_24dp));
//        listExpenses.add(new Expenses("Sean Heaslip","Accomodation",39.00,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "01/12/2017", R.drawable.ic_android_black_24dp));
//        listExpenses.add(new Expenses("Sean Heaslip","Travel",35.51,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "24/03/2018", R.drawable.ic_android_black_24dp));
//        listExpenses.add(new Expenses("Sean Heaslip","Food",15.50,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "01/12/2017", R.drawable.ic_android_black_24dp));
//        listExpenses.add(new Expenses("Sean Heaslip","Beverage",9,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "01/12/2017", R.drawable.ic_android_black_24dp));
//        listExpenses.add(new Expenses("Sean Heaslip","Travel",24.50,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "23/01/2018", R.drawable.ic_android_black_24dp));
//        listExpenses.add(new Expenses("Sean Heaslip","Accomodation",79.58,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "24/03/2018", R.drawable.ic_android_black_24dp));
//        listExpenses.add(new Expenses("Sean Heaslip","Food",25.01,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "01/12/2017", R.drawable.ic_android_black_24dp));
//        listExpenses.add(new Expenses("Sean Heaslip","Travel",165.98,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "01/12/2017", R.drawable.ic_android_black_24dp));
//        listExpenses.add(new Expenses("Sean Heaslip","Beverage",15,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "24/03/2018", R.drawable.ic_android_black_24dp));
//        listExpenses.add(new Expenses("Sean Heaslip","Travel",255,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "01/12/2017", R.drawable.ic_android_black_24dp));
//
    }
    */
}
