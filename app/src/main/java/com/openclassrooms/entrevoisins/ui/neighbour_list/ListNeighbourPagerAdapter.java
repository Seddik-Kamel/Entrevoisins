package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.openclassrooms.entrevoisins.R;

import java.util.ArrayList;
import java.util.List;


public class ListNeighbourPagerAdapter extends FragmentStatePagerAdapter {

    private final Context mContext;
    private final List<Fragment> fragmentList = new ArrayList<>();

    public ListNeighbourPagerAdapter(FragmentManager fm,  Context context) {
        super(fm);
        mContext = context;
        addFragments();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return getFragment(position);
    }

    @Override
    public int getCount() {
        return getNumberOfItems();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        int [] tabTitles = initItemTitle();
        return getItemTitleFromStringResources(tabTitles[position]);
    }

    private Fragment getFragment(int position) {
        return getFragmentList().get(position);
    }

    private int getNumberOfItems() {
        return fragmentList.size();
    }

    private String getItemTitleFromStringResources(int tabTitle) {
        return mContext.getResources().getString(tabTitle);
    }

    /**
     *
     * @return a int array with the titles of items
     */

    @NonNull
    private static int[] initItemTitle() {
        return new int[]{R.string.title_item_neighbour, R.string.title_item_favorite,};
    }

    /**
     * If you want to add a fragment add it in the fragmentList
     */

    private void addFragments() {
        fragmentList.add(NeighbourFragment.newInstance());
        fragmentList.add(NeighbourFavoritesFragment.newInstance());
    }


    /**
     * Getters
     **/
    public List<Fragment> getFragmentList() {
        return fragmentList;
    }
}