package id_16109759_hdsd.sda_a5_seanheaslip;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;

/**
 * Created by seanh on 05/03/2018.
 */

public class FragmentGraph_Backup extends Fragment
{
    /**
     * Reference for working example graph:
     * Modified code from:
     * https://github.com/mitchtabian/Pie-Chart-Tutorial/blob/master/
     * PieChartTutorial/app/src/main/java/com/example/user/piecharttutorial/MainActivity.java
     * Date: 11/03/2018
     */

    private float[] yData = {200.5f, 150.99f, 35.5f, 28.02f, 129.58f, 86.78f, 65.50f, 98.99f};
    private String[] xData = {"Flights", "Accomodation", "Beverages", "Food", "Client",
            "Other", "Ground Transport", "Entertainment"};
    PieChart pieChart;
    String TAG = "Assign5";


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

        addDataSet();

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

                for (int i = 0; i < yData.length; i++)
                {
                    if (yData[i] == Float.parseFloat(expenseCost))
                    {
                        pos1 = i;
                        break;
                    }
                }
                String expenseType = xData[pos1];
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

    private void addDataSet()
    {
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for (int i = 0; i < yData.length; i++)
        {
            yEntrys.add(new PieEntry(yData[i], i));
        }

        for (int i = 1; i < xData.length; i++)
        {
            xEntrys.add(xData[i]);
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
