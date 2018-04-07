package id_16109759_hdsd.sda_a5_seanheaslip;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    String TAG = "Assign5";
    private static final int REQUEST_CODE = 1;
    FirebaseAuth.AuthStateListener mAuthListenera;
    GoogleSignInClient mGoogleSignInClient;
//    FirebaseAuth.AuthStateListener mAuthListenera;
//    FirebaseAuth mAuth;
//
//    //    @Override
//    protected void onStart()
//    {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListenera);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  toolbar = (Toolbar) findViewById(R.id.toolbar_id);
        // setSupportActionBar(toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        //Add Fragment Here
        adapter.AddFragment(new FragmentSubmit(), "Submit");
        adapter.AddFragment(new FragmentExpenses(), "Expenses");
        adapter.AddFragment(new FragmentGraph(), "Graph");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        // add Vector Asset to tabLayout remove text above,
        // for images to appear ONLY, or leave text above to include
        // both image and text.
//        tabLayout.getTabAt(0).setIcon(R.drawable.);
//        tabLayout.getTabAt(0).setIcon(R.drawable.ic_call);
//        tabLayout.getTabAt(0).setIcon(R.drawable.ic_call);

        //Remove shadows from actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);
//        Button logoutButtonTest = (Button) findViewById(R.id.test_google_signout);
//        mAuth = FirebaseAuth.getInstance();
//        logoutButtonTest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mAuth.signOut();
//            }
//        });
        verifyPermissions();
    }

    /**
     * Menu item
     *
     * @param menu
     * @return
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Log out of application when the Menu Item is selected
     *
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_send:
                signOut();
                return true;
        }
        return false;
    }

    /**
     * Added permissions verification
     * Code modified from reference:
     * https://github.com/mitchtabian/ForSale/blob/
     * b610ebb8e1927851fccf35e65a8f2529f9a4f83d/app/
     * src/main/java/codingwithmitch/com/forsale/SearchActivity.java
     * Date: 05/04/2018
     */
    private void verifyPermissions()
    {
        Log.d(TAG, "verifyPermissions: asking user for permissions");
        String[] permissions = {android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.INTERNET,
                android.Manifest.permission.CAMERA};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[0]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[1]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[2]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[3]) == PackageManager.PERMISSION_GRANTED)
        {
            //setupViewPager();
        } else
        {
            ActivityCompat.requestPermissions(MainActivity.this,
                    permissions,
                    REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        verifyPermissions();
    }


    public void signOut()
    {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        // ...
                    }
                });
    }
}
