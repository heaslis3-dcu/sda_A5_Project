//package id_16109759_hdsd.sda_a5_seanheaslip;
//
////import android.app.DatePickerDialog;
////import android.app.Dialog;
////import android.app.DialogFragment;
////import android.os.Bundle;
////import android.view.LayoutInflater;
////import android.view.View;
////import android.view.ViewGroup;
////import android.widget.DatePicker;
////import android.widget.TextView;
////import android.app.Fragment;
////import java.util.Calendar;
//import android.app.Fragment;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.DatePicker;
//import android.widget.TextView;
//
//import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
//
//import java.util.Calendar;
///**
// * Created by seanh on 01/04/2018.
// */
//
//public class SelectedDateFragment extends Fragment implements DatePickerDialog.OnDateSetListener
//{
//    //DialogFragment
//    private TextView dateTextView;
//
//    //Constructor
//    public SelectedDateFragment()
//    {
//
//    }
//
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState)
//    {
//        View view = inflater.inflate(R.layout.submit_fragment, container, false);
//
//        // Find our View instances
//        dateTextView = (TextView) view.findViewById(R.id.calendar_text);
//
//        view.findViewById(R.id.calendar_btn).setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                Calendar now = Calendar.getInstance();
//                new android.app.DatePickerDialog(
//                        getActivity(),
//                        new android.app.DatePickerDialog.OnDateSetListener()
//                        {
//
//                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
//                            {
//                                Log.d("Orignal", "Got clicked");
//                            }
//                        },
//                        now.get(Calendar.YEAR),
//                        now.get(Calendar.MONTH),
//                        now.get(Calendar.DAY_OF_MONTH)
//                ).show();
//            }
//        });
//        return view;
//
//     //   TextView txtView;
////    @Override
////    public Dialog onCreateDialog(Bundle savedInstanceState) {
////        final Calendar calendar = Calendar.getInstance();
////        int yy = calendar.get(Calendar.YEAR);
////        int mm = calendar.get(Calendar.MONTH);
////        int dd = calendar.get(Calendar.DAY_OF_MONTH);
////        return new DatePickerDialog(getActivity(), this, yy, mm, dd);
////    }
//
////    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
////            String date = dayOfMonth+"/"+(++monthOfYear)+"/"+year;
////            dateTextView.setText(date);
////    }
//
//
////    public void populaterSetDate(int year, int month, int day) {
////        //txtView.setText(month+"/"+day+"/"+year);
////    }
//    }
//    @Override
//    public void onDateSet (DatePickerDialog view,int year, int monthOfYear, int dayOfMonth)
//    {
//        String date = dayOfMonth + "/" + (++monthOfYear) + "/" + year;
//        dateTextView.setText(date);
//    }
//}
