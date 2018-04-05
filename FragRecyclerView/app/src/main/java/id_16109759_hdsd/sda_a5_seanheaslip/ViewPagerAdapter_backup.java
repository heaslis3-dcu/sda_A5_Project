package id_16109759_hdsd.sda_a5_seanheaslip;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seanh on 05/03/2018.
 * For handling movement between Fragmentss
 */

public class ViewPagerAdapter_backup extends FragmentPagerAdapter
{
    private final List<Fragment> listFragment = new ArrayList<>();
    private final List<String> listTitles = new ArrayList<>();


    public ViewPagerAdapter_backup(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        return listFragment.get(position);
    }

    @Override
    public int getCount()
    {
        return listTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return listTitles.get(position);
    }

    public void AddFragment (Fragment fragment, String title){
        listFragment.add(fragment);
        listTitles.add(title);
    }
}
