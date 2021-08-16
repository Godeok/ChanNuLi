package kr.co.healthcare.self_diagnosis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import kr.co.healthcare.R;

public class SelfResultActivity extends AppCompatActivity {

    Fragment fragDate, fragSymptom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_result);

        fragDate = new SelfResultDateFragment();
        fragSymptom = new SelfResultSymptomFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.frame, fragDate).commit();

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Fragment selected = null;
                if(position == 0) selected = fragDate;
                else if (position == 1) selected = fragSymptom;

                getSupportFragmentManager().beginTransaction().replace(R.id.frame, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}