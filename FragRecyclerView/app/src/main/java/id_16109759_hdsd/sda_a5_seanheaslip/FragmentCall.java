package id_16109759_hdsd.sda_a5_seanheaslip;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by seanh on 05/03/2018.
 */

public class FragmentCall extends Fragment
{
    String TAG = "Assign5";

    public FragmentCall()
    {
        Log.d(TAG, "Entering Fragment1 Constructor!");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.call_fragment, container, false);
        return view;
    }
}
