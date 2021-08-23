package kr.co.healthcare.mypage;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import kr.co.healthcare.R;

public class SectionsPagerAdapter extends FragmentStateAdapter {
    private final String[] TAB_TITLES;

    public SectionsPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        TAB_TITLES = fragmentActivity.getResources().getStringArray(R.array.DISEASES_LABEL);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new DiagnosisHistory(position);
    }

    @Override
    public int getItemCount() {
        return TAB_TITLES.length;
    }

    public String getTabTitle(int postion){
        return TAB_TITLES[postion];
    }
}
