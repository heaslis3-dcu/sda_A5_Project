package id_16109759_hdsd.sda_a5_seanheaslip;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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


//    // FirebaseAuth.AuthStateListener mAuthListenera;
//    GoogleSignInClient mGoogleSignInClient;
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
        adapter.AddFragment(new FragmentSubmit(),"Submit");
        adapter.AddFragment(new FragmentExpenses(),"Expenses");
        adapter.AddFragment(new FragmentGraph(),"Graph");
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
    }

//    public void signOut() {
//        mGoogleSignInClient.signOut()
//                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        // ...
//                    }
//                });
//    }
}
