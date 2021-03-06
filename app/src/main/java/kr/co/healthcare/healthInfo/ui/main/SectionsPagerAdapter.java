package kr.co.healthcare.healthInfo.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import kr.co.healthcare.R;
import kr.co.healthcare.healthInfo.db.Video;
import kr.co.healthcare.healthInfo.db.VideoDB;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.health_info_tab_text_1, R.string.health_info_tab_text_2,R.string.health_info_tab_text_3};
    private final Context context;
    private final DataBuilder dataBuilder;
    private ArrayList<Fragment> fragments;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        dataBuilder = new DataBuilder(context);
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
        fragments.add(new SelfCare(initVideoByNameOfDB("exercise")));
        fragments.add(new Exercise(initVideoByNameOfDB("exercise")));
        fragments.add(new Diet());
    }

    public ArrayList<Video> initVideoByNameOfDB(String name) {
        VideoDB db = VideoDB.getAppDatabase(context);
        List<Video> videoList = db.videoDao().getAll();
        ArrayList<Video> videoArrayList = new ArrayList<Video>(db.videoDao().getAll());
        db.close();
        return videoArrayList;
    }
}