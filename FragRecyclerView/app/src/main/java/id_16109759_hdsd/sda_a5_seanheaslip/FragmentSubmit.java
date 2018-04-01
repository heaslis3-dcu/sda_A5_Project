package id_16109759_hdsd.sda_a5_seanheaslip;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

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

    private Button mClearBtn;
    private Button mSubmitBtn;
    private EditText mDate;
    private EditText mAmount;
    private EditText mDescription;
    private Spinner mSpinner;
    private ImageButton mCamera;
    private int origSpinnerPos = 0;

    public static final String DATE_TIME = "ddmmyyyy_hhmmss";
    public static final String FILE_TYPE = ".jpg";
    private static final int REQUEST_SHARE = 39714;
    //private String imageFileName = "myImage.jpg";
    private File m_ImageFile;
    private Uri m_ImageUri;
    String mImgFilename;
    private String m_ImageLocation;
    private String mtimeStamp;
    Intent mediaScanIntent;
    String date;
    Calendar myCalendar = Calendar.getInstance();
    //private StorageReference mStorageRef;

    /**
     * Reference: Saving Data to Firebase
     * https://www.youtube.com/watch?v=EM2x33g4syY&index=1&list=PLBK0yKicwRJOZNa4L1VK7nghxOOr0ZL70&t=0s
     * Date: 01/04/2018
     */
    DatabaseReference FragRecyclerView;


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
        //Hide Keyboard if visible
        Keyboard.hideSoftKeyBoardOnTabClicked(getContext());
      //  mEditText = (EditText) view.findViewById(R.id.amount_text);
        //mDate = (TextView) view.findViewById(R.id.calendar_text);
       // mEditText = (EditText) view.findViewById(R.id.calendar_text);
        mDate = (EditText) view.findViewById(R.id.calendar_text);
        mAmount = (EditText) view.findViewById(R.id.amount_text);
        mDescription = (EditText) view.findViewById(R.id.text_edtx);
        mSpinner = (Spinner) view.findViewById(R.id.expense_spinner);
        mClearBtn = (Button) view.findViewById(R.id.clear_btn);
        mCamera = (ImageButton) view.findViewById(R.id.camera_img);

        FragRecyclerView = FirebaseDatabase.getInstance().getReference("expenses");
        //mStorageRef = FirebaseStorage.getInstance().getReference("expenses");
        mSubmitBtn = (Button) view.findViewById(R.id.submit_btn);
        mSubmitBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                addExpense();
            }
        });

        mCamera.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                captureImage();
            }
        });

        //Clear fields button - RESET Form
        mClearBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mDate.setText("");
                mAmount.setText("");
                mDescription.setText("");
                mSpinner.setSelection(origSpinnerPos);
//                int item = mSpinner.getSelectedItemPosition()
//                mSpinner.getSelectedItem().toString();
//                Toast.makeText(getActivity(),"Spinner: "+mSpinner,Toast.LENGTH_SHORT).show();
            }
        });


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
        mDate.setOnClickListener(new View.OnClickListener()
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
//        EditText edView = (EditText) view.findViewById(R.id.text_edtx);
//
//
//        edView.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v) {
//                v.setFocusableInTouchMode(true);
//                v.requestFocusFromTouch();
//            }
//        });

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
         * DO NOT USE - Causes keyboard to stay on Screen even after exiting App
         * Date: 31/03/2018
         */
//        mAmount.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                NumberFormat formatter = new DecimalFormat("#.##");
//                double doubleVal = Double.parseDouble(s.toString());
//                mAmount.setText(formatter.format(doubleVal));
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

    /**
     * Reference: Modified Code from Assign4 using the below link
     * https://stackoverflow.com/questions/41777836/
     * using-camera-to-take-photo-and-save-to-gallery
     * Date: 01/04/2018
     */
    public void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mtimeStamp = new SimpleDateFormat(DATE_TIME, Locale.UK).format(new Date()); //TimeStamp
        mImgFilename = mtimeStamp + FILE_TYPE; //filename - TimeStamp.jpg
        Log.i(TAG, "Camera has been activated.");;
        m_ImageFile = new File(Environment.getExternalStorageDirectory(), mImgFilename);
//        m_ImageUri = Uri.fromFile(m_ImageFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, m_ImageUri);
        startActivityForResult(intent, REQUEST_SHARE);//note number of request_share
    }

    private void addImageGallery() {
        mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        m_ImageFile = new File(mImgFilename);
        m_ImageUri = Uri.fromFile(m_ImageFile);
        mediaScanIntent.setData(m_ImageUri);
        getActivity().sendBroadcast(mediaScanIntent);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SHARE && resultCode == RESULT_OK)
        {
            addImageGallery();
        } else
        {
            Toast.makeText(getActivity(), "An Error has occurred", Toast.LENGTH_SHORT).show();
        }
    }
    private void addExpense() {
//        EditText mDate;
//        EditText mAmount;
//        EditText mDescription;
//        Spinner mSpinner;
        String expDate = mDate.getText().toString().trim();
        Double expAmount = Double.parseDouble(mAmount.getText().toString());
        String expDesc = mDescription.getText().toString().trim();
        String expType = mSpinner.getSelectedItem().toString();
      String imageUri = m_ImageUri.toString().trim();
//        Uri imageUri = m_ImageUri;

        if(TextUtils.isEmpty(expDate)) {
            Toast.makeText(getActivity(), "Date required", Toast.LENGTH_SHORT).show();
        } else if(expAmount == null) {
            Toast.makeText(getActivity(), "Amount required", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(expDesc)) {
            Toast.makeText(getActivity(), "Expense description required", Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(expType)) {
            Toast.makeText(getActivity(), "Select expense type", Toast.LENGTH_SHORT).show();
        } else if(Uri.EMPTY.equals(imageUri)) {
            Toast.makeText(getActivity(), "No Image", Toast.LENGTH_SHORT).show();
        }else{
           String id = FragRecyclerView.push().getKey();

           Expense expense = new Expense(id, expType,expAmount,expDesc,expDate,imageUri);
            //Pass Expense with id to the Database to avoid overwriting data in Database
            FragRecyclerView.child(id).setValue(expense);
            Toast.makeText(getActivity(), "Expense added to database.", Toast.LENGTH_SHORT).show();
        }
    }
}
