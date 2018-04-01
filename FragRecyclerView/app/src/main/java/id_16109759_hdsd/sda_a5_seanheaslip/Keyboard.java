package id_16109759_hdsd.sda_a5_seanheaslip;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by seanh on 31/03/2018.
 */

public class Keyboard
{

    /**
     * Reference: https://stackoverflow.com/questions/1109022/
     * close-hide-the-android-soft-keyboard/1109108#1109108
     * Date: 31/03/2018
     * @param context
     */

    public static void hideSoftKeyBoardOnTabClicked(Context context) {
        View view = ((Activity) context).getCurrentFocus();
        if (view != null && context != null) {
            InputMethodManager inputmanager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanager.hideSoftInputFromWindow(view.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
