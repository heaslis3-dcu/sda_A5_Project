package id_16109759_hdsd.sda_a5_seanheaslip;

import android.widget.Spinner;

/**
 * Created by seanh on 06/03/2018.
 */

public class Expenses
{
    private String Name;
    private String ExpenseType;
    private String ExpDescription;
    private String Date;
    private int Photo;

    public Expenses(){

    }

    public Expenses(String name, String expenseType, String expDescription, String date, int photo)
    {
        Name = name;
        ExpenseType = expenseType;
        ExpDescription = expDescription;
        Date = date;
        Photo = photo;
    }

    public String getDate() {
        return Date;
    }
    public String getExpenseType()
    {
        return ExpenseType;
    }

    public void setExpenseType(String expenseType)
    {
        ExpenseType = expenseType;
    }

    public void setDate(String date) {
        Date =date;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public String getExpDescription()
    {
        return ExpDescription;
    }

    public void setExpDescription(String expDescription)
    {
        ExpDescription = expDescription;
    }

    public int getPhoto()
    {
        return Photo;
    }

    public void setPhoto(int photo)
    {
        Photo = photo;
    }
}
