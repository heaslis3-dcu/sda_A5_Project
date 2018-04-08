package id_16109759_hdsd.sda_a5_seanheaslip;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.StringTokenizer;

/**
 * Amended on 08/04/2018 to include TextView,
 * After getting Float to load to database and to the Expenses/Graphs output,
 * I wanted to format the Textview after the float was posted to the database.
 * How to Automatically add thousand separators as number is input in EditText
 * Reference: - FULL CLASS OBTAINED HERE:
 * https://stackoverflow.com/questions/12338445/
 * how-to-automatically-add-thousand-separators-as-number-is-input-in-edittext
 * Date: 05/04/2018
 */

public class NumberTextWatcherForThousand implements TextWatcher
{
    private DecimalFormat df;
    private EditText editText;
    private TextView textView; // Created by Sean Heaslip - to format
    private static String thousandSeparator;
    private static String decimalMarker;
    private int cursorPosition;

    //Constructor
    public NumberTextWatcherForThousand(EditText editText)
    {
        this.editText = editText;
        df = new DecimalFormat("#,###.##");
        df.setDecimalSeparatorAlwaysShown(true);
        thousandSeparator = Character.toString(df.getDecimalFormatSymbols().getGroupingSeparator());
        decimalMarker = Character.toString(df.getDecimalFormatSymbols().getDecimalSeparator());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count)
    {

    }

    @Override
    public void afterTextChanged(Editable s)
    {
        try
        {
            editText.removeTextChangedListener(this);
            String value = editText.getText().toString();


            if (value != null && !value.equals(""))
            {

                if(value.startsWith(".")){
                    editText.setText("0.");
                }
                if(value.startsWith("0") && !value.startsWith("0.")){
                    editText.setText("");

                }


                String str = editText.getText().toString().replaceAll(",", "");
                if (!value.equals(""))
                    editText.setText(getDecimalFormattedString(str));
                editText.setSelection(editText.getText().toString().length());
            }
            editText.addTextChangedListener(this);
            return;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            editText.addTextChangedListener(this);
        }
    }

    public static String getDecimalFormattedString(String value)
    {
        StringTokenizer lst = new StringTokenizer(value, ".");
        String str1 = value;
        String str2 = "";
        if (lst.countTokens() > 1)
        {
            str1 = lst.nextToken();
            str2 = lst.nextToken();
        }
        String str3 = "";
        int i = 0;
        int j = -1 + str1.length();
        if (str1.charAt( -1 + str1.length()) == '.')
        {
            j--;
            str3 = ".";
        }
        for (int k = j;; k--)
        {
            if (k < 0)
            {
                if (str2.length() > 0)
                    str3 = str3 + "." + str2;
                return str3;
            }
            if (i == 3)
            {
                str3 = "," + str3;
                i = 0;
            }
            str3 = str1.charAt(k) + str3;
            i++;
        }

    }

    public static String trimCommaOfString(String string) {
//        String returnString;
        if(string.contains(",")){
            return string.replace(",","");}
        else {
            return string;
        }

    }
}

