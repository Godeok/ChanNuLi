package kr.co.healthcare.diseaseInfo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;


import kr.co.healthcare.R;
import kr.co.healthcare.diseaseInfo.db.DiseaseInfo;
import kr.co.healthcare.diseaseInfo.db.DiseaseInfoDB;

public class DiseaseInfoActivity extends AppCompatActivity {

    private DiseaseInfo disInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_info);

        //질병 정보 초기화
        initDiseaseInfo();

        //actionBar title 설정
        this.setTitle(disInfo.getName());

        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tabs);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, disInfo, getSupportFragmentManager());

        viewPager.setAdapter(sectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    //선택한 질병 정보 초기화
    private void initDiseaseInfo() {
        Intent intent = getIntent();
        int position = intent.getExtras().getInt("diseasePosition");
        //디비생성
        DiseaseInfoDB db = DiseaseInfoDB.getAppDatabase(this);
        disInfo = db.infoDao().loadByIds(position+1);
        db.close();
    }
}