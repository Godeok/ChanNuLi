package kr.co.healthcare.mypage.selfdiagnosishistory;

import static kr.co.healthcare.selfDiagnosis.ResultDBGlobal.getRange_safe;
import static kr.co.healthcare.selfDiagnosis.ResultDBGlobal.getRange_warning;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import kr.co.healthcare.R;
import kr.co.healthcare.selfDiagnosis.ResultDB.ResultDAO;
import kr.co.healthcare.selfDiagnosis.ResultDB.SelfDiagnosisResultDatabase;

public class SectionsPagerAdapter extends FragmentStateAdapter {
    private final String[] TAB_TITLES;
    private final ResultDAO dao;
    private final FragmentActivity fragmentActivity;

    public SectionsPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.fragmentActivity = fragmentActivity;
        TAB_TITLES = fragmentActivity.getResources().getStringArray(R.array.DISEASES_LABEL);
        dao = SelfDiagnosisResultDatabase
                .getInstance(fragmentActivity)
                .resultDAO();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if ((dao.countDisease(position) != 0)) return new DiagnosisHistoryFragment(fragmentActivity, position);
        else return new DiagnosisHistoryNoDataFragment(position, fragmentActivity);
    }

    @Override
    public int getItemCount() {
        return TAB_TITLES.length;
    }

    public String getTabTitle(int postion){
        return TAB_TITLES[postion];
    }
}
