package id_16109759_hdsd.sda_a5_seanheaslip;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

/**
 * Created by seanh on 05/03/2018.
 */

public class FragmentSubmit extends Fragment
{

    private static final String TAG = "Assign5";
    TextView txtView;

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
    //Camera related
    public static final String DATE_TIME = "ddmmyyyy_hhmmss";
    public static final String FILE_TYPE = ".jpg";
    private static final int REQUEST_SHARE = 39714;
    public String mImageFilePath;
    private File m_ImageFile;
    private Uri m_ImageUri = null;
    private Uri filePath;
    public Bitmap mPhoto;
    private String m_ImageLocation;
    private String mtimeStamp;
    private byte[] mbitmapdata;
    private String idExtention;
    private StorageTask mUploadTask; // used to check if there is an upload currently running
    Intent mediaScanIntent;
    String mImgFilename;

    /**
     * Reference: Saving Data to Firebase
     * https://www.youtube.com/watch?v=EM2x33g4syY&index=1&list=PLBK0yKicwRJOZNa4L1VK7nghxOOr0ZL70&t=0s
     * Date: 01/04/2018
     */
    //Firebase Data
    private String userID;
    private DatabaseReference mDatabaseRef; // Storage for Data
    private FirebaseAuth firebaseAuth; // Login Authentication
    //Firebase Images
    FirebaseStorage firebaseStorage;
    StorageReference storageReference; // Storage for Images


    String date;
    Calendar myCalendar = Calendar.getInstance();

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

        //Firebase Images
        // firebaseStorage = FirebaseStorage.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference("images/users/"); //mStorageRef
        //Firebase database References
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("expenses/users/"); // mDatabaseRef
        //firebaseStorage = FirebaseStorage.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        /*
        storageReference = FirebaseStorage.getInstance().getReference("images/users/");
        */

        //User ID
        // userID = firebaseAuth.getCurrentUser().getEmail();
        //Get Signed in User
        FirebaseUser user = firebaseAuth.getCurrentUser();
        userID = user.getUid();
        //userID = firebaseAuth.getCurrentUser().getDisplayName();
        //userID = FirebaseUser.getEmail();

        mDate = (EditText) view.findViewById(R.id.calendar_text);
        mAmount = (EditText) view.findViewById(R.id.amount_text);
        mDescription = (EditText) view.findViewById(R.id.text_edtx);
        mSpinner = (Spinner) view.findViewById(R.id.expense_spinner);
        mClearBtn = (Button) view.findViewById(R.id.clear_btn);
        mCamera = (ImageButton) view.findViewById(R.id.camera_img);
        //mStorageRef = FirebaseStorage.getInstance().getReference("expenses");
        mSubmitBtn = (Button) view.findViewById(R.id.submit_btn);
        // How to Automatically add thousand separators as number is input in EditText
        // For Reference: See Downloaded Class NumberTextWatcherForThousand
        //Text Water for
        //mSubmitBtn.setSelected(false);
//        ToggleButton toggleButton = (ToggleButton)view.findViewById(R.id.clear_btn);
//        toggleButton.setChecked(false);

        //Testing double
       // mAmount.addTextChangedListener(new NumberTextWatcherForThousand(mAmount));

        mCamera.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                chooseImage();
            }
        });
        mSubmitBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                // IF TIME - Replace with loop through EditTexts:
                //Reference: https://stackoverflow.com/questions/6290531/check-if-edittext-is-empty

                //Load information to Database retrieve key from image uploaded:
                String expDate = mDate.getText().toString().trim(); //Date as String
                String expAmount = mAmount.getText().toString(); // Save amount as String due to structure of Data in Firebase
                //double expAmount = Double.parseDouble(mAmount.getText().toString());

               // float expAmount = Float.parseFloat(mAmount.getText().toString());
                String expType = mSpinner.getSelectedItem().toString(); // Expense
                String expDesc = mDescription.getText().toString().trim(); //Description Saved


                // String imageUri = taskSnapshot.getDownloadUrl().toString(); //ID of uploaded image
                if (filePath == null)
                {
                    Toast.makeText(getActivity(), "Image missing!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(expDate))
                {
                    mDate.setError("Date required"); //Should place icon in EditText and display message
                    //Toast.makeText(getActivity(), "Date required ", Toast.LENGTH_SHORT).show();
                }
                // float
//                else if (expAmount == 0.0f)
//                {
//                    Toast.makeText(getActivity(), "Amount required", Toast.LENGTH_SHORT).show();
//                }
                //String
                else if (TextUtils.isEmpty(expAmount))
                {
                    mAmount.setError("Amount required"); //Should place icon in EditText and display message
                    //Toast.makeText(getActivity(), "Amount required", Toast.LENGTH_SHORT).show();
                }

                else if (expType == mSpinner.getItemAtPosition(origSpinnerPos))
                {
                   Toast.makeText(getActivity(), "Select expense type!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(expDesc))
                {
                    mDescription.setError("A description is required!"); //Should place icon in EditText and display message
                    //Toast.makeText(getActivity(), "Expense description required", Toast.LENGTH_SHORT).show();
                } else
                {
                    //check if an upload is currently running,
                    // Check if not null - to aovid crash
                    if (mUploadTask != null && mUploadTask.isInProgress())
                    {
                        // if an upload in progress we want to avoid uploading file
                        Toast.makeText(getActivity(), "Upload in progress!", Toast.LENGTH_SHORT).show();
                    } else
                    {
                        //If no upload in progress - upload file
                        uploadImage();
                        //Toast.makeText(getActivity(), "Please attach an Image!", Toast.LENGTH_SHORT).show();
                    }
//                (mUploadTask.isComplete())
//                    //String cameraImg = "@drawable/ic_photo_camera_black_24dp";
//                    mDate.setText("");
//                    // mAmount.setText("");
//                    mDescription.setText("");
//                    mSpinner.setSelection(origSpinnerPos);
//                    // mCamera.setImageURI(null);
//                    ImageButton imageButton = (ImageButton) v.findViewById(R.id.camera_img);
//                    // imageButton.setImageResource(R.drawable.ic_photo_camera_black_24dp); //change imagebutton
                }
            }
        });





/**
 * With Reference to changing ImageButton when Clear Button is clicked:
 * Issue persists and crashes application so have removed this from my
 * Clear button method
 * Reference: https://stackoverflow.com/questions/12249495/
 * android-imagebutton-change-image-onclick
 * Date: 05/04/2018
 */
        //Clear fields button - RESET Form
        mClearBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                //String cameraImg = "@drawable/ic_photo_camera_black_24dp";
                mDate.setText("");
              mAmount.setText("");
                mDescription.setText("");
                mSpinner.setSelection(origSpinnerPos);
                // mCamera.setImageURI(null);
                //ImageButton imageButton = (ImageButton) v.findViewById(R.id.camera_img);
                mCamera.setImageResource(R.drawable.ic_photo_camera_black_24dp); //change imagebutton


            }
        });


        /**
         * Refernces: Calendar
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

        return view;
    }

//  Start of New Approach to handle Image

    /**
     * Referene:
     * Code modified from:
     * https://code.tutsplus.com/tutorials/image-upload-to-firebase-in-android-application--cms-29934
     * Date: 02/04/2018
     */
    private void chooseImage()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_SHARE);
    }

    /**
     * Compressing images
     * Adapted from Reference:
     * https://gist.github.com/john990/739fa230bd309554c244
     * https://www.youtube.com/watch?v=j4h_L2pzZs0
     * Code modified using below reference for Bitmap - within Try/Catch block
     * https://stackoverflow.com/questions/7620401/how-to-convert-byte-array-to-bitmap
     * // below link referenced when testing Bitmap to byte filetype - using putByte(byte)
     * to upload to Firebase
     * https://stackoverflow.com/questions/40885860/how-to-save-bitmap-to-firebase
     * Date: 04/04/2018
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SHARE && resultCode == RESULT_OK
                && data != null && data.getData() != null)
        {
            filePath = data.getData();

            //This portion of code assigns the compressed Bitmap to the ImageView
            try
            {

                //Adding compression: - Discovered @ 01:37am on 5th April 2018 - filepath above is being used
                // To populate the image toFirebase and not the compressed Bitmap below.
                //May need to replace this with Picasso.with(this).load(filepath).into(mCamera);
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                ByteArrayOutputStream blob = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 0, blob);
                byte[] bitmapdata = blob.toByteArray();
                Bitmap mBitMap = BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length);
                // return bytes;
                //rotate inmage in imageView
                //Save bitmap to ImageView
                mCamera.setImageBitmap(mBitMap);

            } catch (IOException e)
            {
                e.printStackTrace();
            }

        }
    }

    //Compress Bitmap
    private void ReturnByteImg(byte[] byteMB)
    {

    }

    //Compress bitmap
    public byte[] ReturnByteImage()
    {
        byte[] mBitMapByte = new byte[1];
        try
        {
            //Compression
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
            ByteArrayOutputStream blob = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 0, blob);
            mBitMapByte = blob.toByteArray();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return mBitMapByte;
    }

    private void uploadImage()
    {

        if (filePath != null)
        {
            //Popup Progress Dialog
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            idExtention = userID + "/" + UUID.randomUUID().toString();
            StorageReference ref = storageReference.child(idExtention);

            //Testing - storage filepath similar to database filepath
            Log.d(TAG, "Storage filepath: " + ref);


            mUploadTask = ref.putBytes(ReturnByteImage())    //uploads compressed byte file
                    // ref.putFile(filePath)    //upload full file uncompressed
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
                    {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                        {
                            //Load information to Database retrieve key from image uploaded:
                            String expDate = mDate.getText().toString().trim(); //Date as String
                            //double expAmount = Double.parseDouble(mAmount.getText().toString()); // Save amount as String due to structure of Data in Firebase
                           // String expAmount = mAmount.getText().toString(); // Save amount as String due to structure of Data in Firebase
                            //float
                            float expAmount = Float.parseFloat(mAmount.getText().toString());

                            String expDesc = mDescription.getText().toString().trim(); //Description Saved
                            String expType = mSpinner.getSelectedItem().toString();

                            //ID of uploaded image
                            String imageUri = taskSnapshot.getDownloadUrl().toString();

                            //generate Unique ID from Firebase
                            String id = idExtention;

                            //Users Email - unique therefore adding as an
                            userID = firebaseAuth.getCurrentUser().getEmail();

                            // Updating expense Class
                            Expense expense = new Expense(id, userID, expType, expAmount, expDesc, expDate, imageUri);
                            //Pass Expense with id to the Database to avoid overwriting data in Database
                            mDatabaseRef.child(id).setValue(expense);
                            // Testing Database filepath similar to Storage Filepath
                            Log.d(TAG, "Database path: " + mDatabaseRef);

                            //Remove Progress Dialog popup
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Data uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener()
                    {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>()
                    {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot)
                        {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploading data: " + (int) progress + "%");
                        }
                    });
        }
//        else {
//            Toast.makeText(getActivity(), "No Image attached!", Toast.LENGTH_SHORT).show();
//        }
    }

    /**
     * Compress Bitmap - modified use of method to work with existing code:
     * Source: https://github.com/mitchtabian/ForSale/blob/
     * 7ce6d2dfa7e481f028a68de1d69c5499dca81c80/app/src/main/
     * java/codingwithmitch/com/forsale/PostFragment.java
     * Date: 04/04/2018
     *
     * @param bitmap
     * @param quality
     * @return
     */
    public static byte[] getBytesFromBitmap(Bitmap bitmap, int quality)
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream);
        return stream.toByteArray();
    }


//  End of New Approach to handling Image

//  Comment - changing scope of project to select image from Gallery,
//  Removing use of camera from app - As unable to upload image to Firebase,
//  Trying new approach
//    /**
//     * Reference: Modified Code from Assign4 using the below link
//     * https://stackoverflow.com/questions/41777836/
//     * using-camera-to-take-photo-and-save-to-gallery
//     * Date: 01/04/2018
//     */
//
//    public void captureImage() {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        mtimeStamp = new SimpleDateFormat(DATE_TIME, Locale.UK).format(new Date()); //TimeStamp
//        mImgFilename = mtimeStamp + FILE_TYPE; //filename - TimeStamp.jpg
//        Log.i(TAG, "Camera has been activated.");;
//        m_ImageFile = new File(Environment.getExternalStorageDirectory(), mImgFilename);
//        mImageFilePath = m_ImageFile.getAbsolutePath(); // Filepath of image
//        m_ImageUri = Uri.fromFile(m_ImageFile);
//
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, m_ImageUri);
//        startActivityForResult(intent, REQUEST_SHARE);//note number of request_share
//    }
//
//    private void addImageGallery() {
//        mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
////        m_ImageFile = new File(mImgFilename);
////        m_ImageUri = Uri.fromFile(m_ImageFile);
//
//        mediaScanIntent.setData(m_ImageUri);
//        getActivity().sendBroadcast(mediaScanIntent);
//
//       // return m_ImageUri;
//    }
//
//    public void xonActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        super.xonActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_SHARE && resultCode == RESULT_OK)
//        {
//            addImageGallery();
////            m_ImageUri = data.getData();
////            mCamera.setImageURI(m_ImageUri);
//        } else
//        {
//            Toast.makeText(getActivity(), "An Error has occurred", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//private void uploadFile(){
//        if(m_ImageUri != null) {
//            StorageReference fileReference = storageReference.child(System.currentTimeMillis()
//                    + FILE_TYPE);
//            fileReference.putFile(m_ImageUri)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
//                    {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
//                        {
//
//                        }
//                    });
//        }
//}
//  LoadImage - does not work, issue with Uri
//public void LoadImage(){
//    //Get Signed in User
//    FirebaseUser user = firebaseAuth.getCurrentUser();
//    String user_ID = user.getUid();
//
//    String expDate = mDate.getText().toString().trim();
//    String expAmount = mAmount.getText().toString();
//   Uri imageUri = Uri.fromFile(m_ImageFile);
//    //Storing image
//    if(!TextUtils.isEmpty(expDate) && !TextUtils.isEmpty(expAmount)){
//        //StorageReference imagePath = storageReference.child("images/users/" + user_ID + "/" + m_ImageUri);
//        StorageReference imagePath = storageReference.child("images/users/" + user_ID + "/" + mImgFilename);
////.child(m_ImageUri.getLastPathSegment());
//        imagePath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
//        {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
//            {
//                Uri downloadurl = taskSnapshot.getDownloadUrl();
//                Toast.makeText(getActivity(),"This is the image: ", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//}

    //  Working addExpense code
//    private void addExpense()
//    {
////        EditText mDate;
////        EditText mAmount;
////        EditText mDescription;
////        Spinner mSpinner;
//        String expDate = mDate.getText().toString().trim();
//       // String expAmount = mAmount.getText().toString(); // Save amount as String due to structure of Data in Firebase
//        // double expAmount = Double.parseDouble(mAmount.getText().toString());
//        float expAmount = Float.parseFloat(mAmount.getText().toString());
//        String expDesc = mDescription.getText().toString().trim();
//        String expType = mSpinner.getSelectedItem().toString();
//        String imageUri = m_ImageUri.toString().trim();
//        //  Uri imageUri = m_ImageUri;
//
//        if (TextUtils.isEmpty(expDate))
//        {
//            Toast.makeText(getActivity(), "Date required", Toast.LENGTH_SHORT).show();
////        } else if(expAmount == null) {
////            Toast.makeText(getActivity(), "Amount required", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(expDesc))
//        {
//            Toast.makeText(getActivity(), "Expense description required", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(expType))
//        {
//            Toast.makeText(getActivity(), "Select expense type", Toast.LENGTH_SHORT).show();
//        } else if (Uri.EMPTY.equals(imageUri))
//        {
//            Toast.makeText(getActivity(), "No Image", Toast.LENGTH_SHORT).show();
//        } else
//        {
//
//            //generate Unique ID from Firebase
//            String id = mDatabaseRef.push().getKey();
//            //Users Email - unique therefore adding as an
//            userID = firebaseAuth.getCurrentUser().getEmail();
//            // Updating expense Class
//            Expense expense = new Expense(id, userID, expType, expAmount, expDesc, expDate, imageUri);
//            //Pass Expense with id to the Database to avoid overwriting data in Database
//            mDatabaseRef.child(id).setValue(expense);
//            Toast.makeText(getActivity(), "Image Uri." + m_ImageUri, Toast.LENGTH_LONG).show();
//            // Toast.makeText(getActivity(), "Image File Path." + mImageFilePath, Toast.LENGTH_LONG).show();
//        }
//    }
}
