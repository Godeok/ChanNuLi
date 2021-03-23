package kr.co.healthcare.self_diagnosis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.eazegraph.lib.charts.StackedBarChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.StackedBarModel;

import java.util.ArrayList;
import java.util.List;

import kr.co.healthcare.R;
import kr.co.healthcare.self_diagnosis.ResultDB.AppDatabase;
import kr.co.healthcare.self_diagnosis.ResultDB.*;
import kr.co.healthcare.self_diagnosis.Recycler.RecyclerAdapter;


public class SelfResultDateActivity extends AppCompatActivity {

    //리사이클러뷰
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerAdapter adapter;
    private List<Result> results;

    //stacked bar graph
    private StackedBarChart stackedBarChart;

    TextView tv_resultTitle;
    Button btn_toDate;
    Button btn_toSymptom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_result_date);

        recyclerView = findViewById(R.id.rv_self_result);
        btn_toDate = findViewById(R.id.btn_toDate);
        btn_toSymptom = findViewById(R.id.btn_toSymptom);
        tv_resultTitle = findViewById(R.id.tv_resultTitle);
        tv_resultTitle.setText("결과 확인");

        //DB에서 정보 가져오기
        initialized();

        //그래프
        initialized_bar_graph();

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        btn_toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelfResultDateActivity.this, SelfResultDateActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        btn_toSymptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelfResultDateActivity.this, SelfResultSymptomActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }



    private void initialized() {
        recyclerView = findViewById(R.id.rv_self_result);
        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new RecyclerAdapter();

        results = AppDatabase.getInstance(this).resultDAO().getAllByDate();
        int size = results.size();
        for (int i = 0; i < size; i++) {
            adapter.addItem(results.get(i));
        }
    }

    private void initialized_bar_graph(){
        stackedBarChart = findViewById(R.id.stacked_Bar_Chart);

        StackedBarModel s1 = new StackedBarModel("고혈압");
        s1.addBar(new BarModel(AppDatabase.getInstance(this).resultDAO().countDiseaseDanger(0), 0xFFE61919));
        s1.addBar(new BarModel(AppDatabase.getInstance(this).resultDAO().countDiseaseWarning(0), 0xFFFFC107));
        s1.addBar(new BarModel(AppDatabase.getInstance(this).resultDAO().countDiseaseSafe(0), 0xFF00AC00));

        StackedBarModel s2 = new StackedBarModel("골관절염");
        s2.addBar(new BarModel(AppDatabase.getInstance(this).resultDAO().countDiseaseDanger(1), 0xFFE61919));
        s2.addBar(new BarModel(AppDatabase.getInstance(this).resultDAO().countDiseaseWarning(1), 0xFFFFC107));
        s2.addBar(new BarModel(AppDatabase.getInstance(this).resultDAO().countDiseaseSafe(1), 0xFF00AC00));

        StackedBarModel s3 = new StackedBarModel("고지혈증");
        s3.addBar(new BarModel(AppDatabase.getInstance(this).resultDAO().countDiseaseDanger(2), 0xFFE61919));
        s3.addBar(new BarModel(AppDatabase.getInstance(this).resultDAO().countDiseaseWarning(2), 0xFFFFC107));
        s3.addBar(new BarModel(AppDatabase.getInstance(this).resultDAO().countDiseaseSafe(2), 0xFF00AC00));

        StackedBarModel s4 = new StackedBarModel("요통/좌골신경통");
        s4.addBar(new BarModel(AppDatabase.getInstance(this).resultDAO().countDiseaseDanger(3), 0xFFE61919));
        s4.addBar(new BarModel(AppDatabase.getInstance(this).resultDAO().countDiseaseWarning(3), 0xFFFFC107));
        s4.addBar(new BarModel(AppDatabase.getInstance(this).resultDAO().countDiseaseSafe(3), 0xFF00AC00));

        StackedBarModel s5 = new StackedBarModel("당뇨병");
        s5.addBar(new BarModel(AppDatabase.getInstance(this).resultDAO().countDiseaseDanger(4), 0xFFE61919));
        s5.addBar(new BarModel(AppDatabase.getInstance(this).resultDAO().countDiseaseWarning(4), 0xFFFFC107));
        s5.addBar(new BarModel(AppDatabase.getInstance(this).resultDAO().countDiseaseSafe(4), 0xFF00AC00));

        StackedBarModel s6 = new StackedBarModel("골다공증");
        s6.addBar(new BarModel(AppDatabase.getInstance(this).resultDAO().countDiseaseDanger(5), 0xFFE61919));
        s6.addBar(new BarModel(AppDatabase.getInstance(this).resultDAO().countDiseaseWarning(5), 0xFFFFC107));
        s6.addBar(new BarModel(AppDatabase.getInstance(this).resultDAO().countDiseaseSafe(5), 0xFF00AC00));

        StackedBarModel s7 = new StackedBarModel("치매");
        s7.addBar(new BarModel(AppDatabase.getInstance(this).resultDAO().countDiseaseDanger(6), 0xFFE61919));
        s7.addBar(new BarModel(AppDatabase.getInstance(this).resultDAO().countDiseaseWarning(6), 0xFFFFC107));
        s7.addBar(new BarModel(AppDatabase.getInstance(this).resultDAO().countDiseaseSafe(6), 0xFF00AC00));

        stackedBarChart.addBar(s1);
        stackedBarChart.addBar(s2);
        stackedBarChart.addBar(s3);
        stackedBarChart.addBar(s4);
        stackedBarChart.addBar(s5);
        stackedBarChart.addBar(s6);
        stackedBarChart.addBar(s7);

        stackedBarChart.startAnimation();
    }

    @Override
    protected void onStart() {
        results = AppDatabase.getInstance(this).resultDAO().getAllByDate();
        adapter.addItems((ArrayList) results);
        super.onStart();
    }
}