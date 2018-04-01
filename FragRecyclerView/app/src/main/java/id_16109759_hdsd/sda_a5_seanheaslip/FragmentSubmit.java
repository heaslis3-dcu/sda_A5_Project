package id_16109759_hdsd.sda_a5_seanheaslip;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by seanh on 05/03/2018.
 */

public class FragmentSubmit extends Fragment
        //implements DatePickerDialog.OnDateSetListener
{

    private static final String TAG = "Assign5";
    TextView txtView;
    /**
     *
     */
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        final Calendar calendar = Calendar.getInstance();
//        int yy = calendar.get(Calendar.YEAR);
//        int mm = calendar.get(Calendar.MONTH);
//        int dd = calendar.get(Calendar.DAY_OF_MONTH);
//        return new DatePickerDialog(getActivity(), this, yy, mm, dd);
//    }
//
//    public void onDateSet(DatePicker view, int yy, int mm, int dd) {
//        populaterSetDate(yy, mm+1, dd);
//    }
//    public void populaterSetDate(int year, int month, int day) {
//        txtView.setText(month+"/"+day+"/"+year);
//    }

    /**
     * Reference to Date Picker
     * Ref: https://www.youtube.com/watch?v=hwe1abDO2Ag&list=PLBK0yKicwRJOZNa4L1VK7nghxOOr0ZL70&index=10&t=0s
     * Date: 31/03/2018
     * Channel - CodingWithMitch
     */
    private TextView mDate;
    private Button mButton;
    private EditText mEditText;
    String date;
    Calendar myCalendar = Calendar.getInstance();

//    public void populaterSetDate(int year, int month, int day) {
//        mEditText.setText(month+"/"+day+"/"+year);
//    }

    public FragmentSubmit()
    {
        Log.d(TAG, "Entering Fragment1 Constructor!");
    }
//https://stackoverflow.com/questions/27225815/android-how-to-show-datepicker-in-fragment
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.submit_fragment, container, false);
      //  mEditText = (EditText) view.findViewById(R.id.amount_text);
        //mDate = (TextView) view.findViewById(R.id.calendar_text);
       // mEditText = (EditText) view.findViewById(R.id.calendar_text);
        mDate = (TextView) view.findViewById(R.id.calendar_text);
        mButton = (Button) view.findViewById(R.id.calendar_btn);

        /**
         * Refernces:
         * https://github.com/wdullaer/MaterialDateTimePicker
         * Code extracted & modified from wdullaer DatePickerFragment
         * Show a datepicker when the dateButton is clicked
         * Date: 01/04/2018
         * Note: Fragment and v4.app.Fragment cause compatibility issues;
         * Additional References:
         * https://stackoverflow.com/questions/27514338/cannot-resolve-method-showandroid-support-v4-app-fragmentmanager-java-lang-st
         * https://stackoverflow.com/questions/27225815/android-how-to-show-datepicker-in-fragment
         * https://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext

         */
//working calendar -
        mButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Calendar now = Calendar.getInstance();
                new android.app.DatePickerDialog(
                        getActivity(),
                        new android.app.DatePickerDialog.OnDateSetListener()
                        {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                            {
                                Log.d("Orignal", "Got clicked");
                                String date = dayOfMonth + "/" + (++month) + "/" + year;
                                mDate.setText(date);
                            }
                        },
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                ).show();
                //https://stackoverflow.com/questions/27514338/cannot-resolve-method-showandroid-support-v4-app-fragmentmanager-java-lang-st
//                FragmentManager fm = getActivity().getFragmentManager();
//                DialogFragment dialogFragment = new SelectedDateFragment();
//                dialogFragment.show(fm, "DatePicker");
               // dialogFragment.show(getFragmentManager(), "DatePicker");
           // DatePickerDialog datePicker = new DatePickerDialog(getActivity(), )

                //mEditText.setText();
            }
        });
        //End working Calendar


        /**
         * Reference: https://developer.android.com/guide/topics/ui/controls/spinner.html
         * Date: 31/03/2018
         */
       // TextView tView = (TextView) view.findViewById(R.id.test_text);
        //EditText:
        EditText edView = (EditText) view.findViewById(R.id.text_edtx);


        edView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                v.setFocusableInTouchMode(true);
                v.requestFocusFromTouch();
            }
        });

        //Spinner:
        Spinner spinner = (Spinner) view.findViewById(R.id.expense_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
        R.array.spinner_expensetype, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        /**
         * Reference:
         * https://stackoverflow.com/questions/5357455/
         * limit-decimal-places-in-android-edittext
         * Date: 31/03/2018
         */
//        mEditText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                NumberFormat formatter = new DecimalFormat("#.##");
//                double doubleVal = Double.parseDouble(s.toString());
//                mEditText.setText(formatter.format(doubleVal));
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
//
//            @Override
//            public void afterTextChanged(Editable s) {}
//        });

        return view;
    }

    /**
     * Included but OverRide above - below not required:
     * Refernces:
     * https://github.com/wdullaer/MaterialDateTimePicker
     * Code extracted & modified from wdullaer DatePickerFragment
     * @param view
     * @param year
     * @param monthOfYear
     * @param dayOfMonth
     */
    public void onDateSet (com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth)
    {
        String date = dayOfMonth + "/" + (++monthOfYear) + "/" + year;
        mDate.setText(date);
    }
}
