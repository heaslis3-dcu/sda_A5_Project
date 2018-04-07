package id_16109759_hdsd.sda_a5_seanheaslip;

import android.net.Uri;

/**
 * Created by seanh on 01/04/2018.
 */

public class Expense
{
    private String Id;
    private String Email;
    private String ExpenseType;
   // private double ExpenseTdouble;
    private String ExpDescription;
    private String Date;
    //private double ExpAmount;
    private String ExpAmount;
    private String Photo;
//    private Uri Photo;

    public Expense(){

    }



    //    public Expense(String name, String expenseType, double expAmount, String expDescription, String date, Uri photo)
    public Expense(String id, String email, String expenseType, String expAmount, String expDescription, String date, String photo)
    {
        Id = id;
        Email = email;
        ExpenseType = expenseType;
        ExpAmount = expAmount;
        ExpDescription = expDescription;
        Date = date;
        Photo = photo;
    }
    // id - this will be the value retrieved from Firebase
    public String getId()
    {
        return Id;
    }

    public void setId(String id)
    {
        Id = id;
    }
    //Name this will be the user Name obtained at Login
    public String getName()
    {
        return Email;
    }

    public void setName(String email)
    {
        Email = email;
    }

    // Expense type retrieved from Spinner
    public String getExpenseType()
    {
        return ExpenseType;
    }

    public void setExpenseType(String expenseType)
    {
        ExpenseType = expenseType;
    }
//    // Expense type retrieved from Spinner
//    @Override
//    public double getExpenseType()
//    {
//        return ExpenseTdouble;
//    }


//    @Override
//    public void setExpenseType(double expenseType)
//    {
//        ExpenseType = ExpenseTdouble;
//    }
    // Amount Saved as String - this is because when using a Map or ArrayList linked to Firebase we declare the type
    // ie.e Map <String, String> map.....
    public String getExpAmount()
    {
        return ExpAmount;
    }

    public void setExpAmount(String expAmount)
    {
        ExpAmount = expAmount;
    }
    public String getDate() {
        return Date;
    }


    public void setDate(String date) {
        Date =date;
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
