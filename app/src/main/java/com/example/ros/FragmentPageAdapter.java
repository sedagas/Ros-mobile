package com.example.ros;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


public class FragmentPageAdapter extends FragmentPagerAdapter {

    Context context;
    List<Tables> tablelList;

    public FragmentPageAdapter(FragmentManager fm, Context context, List<Tables> tblList) {
        super(fm);
        this.context = context;
        this.tablelList = tblList;
    }

    @Override
    public Fragment getItem(int i) {
        ShowTableFragment showTableFragment = new ShowTableFragment(tablelList, i);
        return showTableFragment;
    }

    @Override
    public int getCount() {
        return tablelList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        return tablelList.get(position).getName();
    }

}
