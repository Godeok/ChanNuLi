package kr.co.healthcare.selfDiagnosis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.tabs.TabLayout;

import kr.co.healthcare.R;
import kr.co.healthcare.selfDiagnosis.ResultDB.ResultDAO;
import kr.co.healthcare.selfDiagnosis.ResultDB.SelfDiagnosisResultDatabase;

public class SelfResultActivity extends AppCompatActivity {

    Fragment fragDate, fragSymptom;
    FrameLayout frame, layout_noData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_result);

        fragDate = new SelfResultDateFragment();
        fragSymptom = new SelfResultSymptomFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.frame, fragDate).commit();

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        frame = findViewById(R.id.frame);
        layout_noData = findViewById(R.id.layout_noData);

        //저장된 데이터가 없다면 그래프 대신 안내사항 표시
        ResultDAO dao = SelfDiagnosisResultDatabase.getInstance(this).resultDAO();
        if(dao.getDataCount()==0) {
            frame.setVisibility(View.GONE);
            layout_noData.setVisibility(View.VISIBLE);
        }

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