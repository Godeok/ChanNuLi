package kr.co.healthcare.diseaseInfo;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

import kr.co.healthcare.R;
import kr.co.healthcare.diseaseInfo.db.DiseaseInfo;

public class SectionsPagerAdapter extends FragmentPagerAdapter{
    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.disease_info_tab_text_1, R.string.disease_info_tab_text_2,R.string.disease_info_tab_text_3, R.string.disease_info_tab_text_4, R.string.disease_info_tab_text_5};
    private final Context context;
    private final DiseaseInfo diseaseInfo;
    private ArrayList<Fragment> fragments;

    public SectionsPagerAdapter(Context context, DiseaseInfo diseaseInfo, FragmentManager fm) {
        super(fm);
        this.context = context;
        this.diseaseInfo =diseaseInfo;
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

    public void addFragment(){
        fragments = new ArrayList<>();
        fragments.add(new Info(diseaseInfo.getDefinition()));
        fragments.add(new Info(diseaseInfo.getCause()));
        fragments.add(new Info(diseaseInfo.getSymptom()));
        fragments.add(new Info(diseaseInfo.getTreatment()));
        fragments.add(new Info(diseaseInfo.getPrecaution()));
    }

}