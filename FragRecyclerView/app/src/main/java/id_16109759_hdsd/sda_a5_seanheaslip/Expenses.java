package id_16109759_hdsd.sda_a5_seanheaslip;

/**
 * Created by seanh on 06/03/2018.
 */

public class Expenses
{
    private String Name;
    private String ExpDescription;
    private String Date;
    private int Photo;

    public Expenses(){

    }

    public Expenses(String name, String expDescription, String date, int photo)
    {
        Name = name;
        ExpDescription = expDescription;
        Date = date;
        Photo = photo;
    }

    public String getDate() {
        return Date;
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
