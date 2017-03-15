package com.example.bakhtiyar.twitter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Bakhtiyar on 2/26/2017.
 */
public class PostFragment extends FragmentPagerAdapter {
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:

                return "Write Post";

            case 1:

                return "Read Post";

            default:
                return null;


        }
    }

    public PostFragment(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:

                return new PostWriteFragment();

            case 1:

                return new PostReadFragment();

            default:
                return null;


        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
