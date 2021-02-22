package kr.co.healthcare.healthInfo.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

import kr.co.healthcare.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.health_info_tab_text_1, R.string.health_info_tab_text_2,R.string.health_info_tab_text_3,R.string.health_info_tab_text_4};
    private final Context context;
    private ArrayList<Fragment> fragments;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        addFragment();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }

    public void addFragment (){
        fragments = new ArrayList<>();
        fragments.add(new SelfCare(createHealthInfoData()));
        fragments.add(new Exercise());
        fragments.add(new Diet());
        fragments.add(new Other());
    }

    ArrayList<HealthInfoData> createHealthInfoData(){
        ArrayList<HealthInfoData> arrayList = new ArrayList<HealthInfoData>();
        arrayList.add(new HealthInfoData("Z8jmID7zeGY"));
        arrayList.add(new HealthInfoData("Ndq4G-NmSTQ"));
        arrayList.add(new HealthInfoData("xLWpuv_Jk6I"));
        arrayList.add(new HealthInfoData("ueE8G45cWDs"));
        return arrayList;
    }
}