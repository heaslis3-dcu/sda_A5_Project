package id_16109759_hdsd.sda_a5_seanheaslip;

import android.net.Uri;

/**
 * Created by seanh on 01/04/2018.
 */

public class Expense
{
    private String Name;
    private String ExpenseType;
    private String ExpDescription;
    private String Date;
    private double ExpAmount;
    private String Photo;
//    private Uri Photo;

    public Expense(){

    }
//    public Expense(String name, String expenseType, double expAmount, String expDescription, String date, Uri photo)
    public Expense(String name, String expenseType, double expAmount, String expDescription, String date, String photo)
    {
        Name = name;
        ExpenseType = expenseType;
        ExpAmount = expAmount;
        ExpDescription = expDescription;
        Date = date;
        Photo = photo;
    }
    public double getExpAmount()
    {
        return ExpAmount;
    }

    public void setExpAmount(double expAmount)
    {
        ExpAmount = expAmount;
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

    public String getPhoto()
    {
        return Photo;
    }

    public void setPhoto(String photo)
    {
        Photo = photo;
    }

//    public Uri getPhoto()
//    {
//        return Photo;
//    }
//
//    public void setPhoto(Uri photo)
//    {
//        Photo = photo;
//    }
}
