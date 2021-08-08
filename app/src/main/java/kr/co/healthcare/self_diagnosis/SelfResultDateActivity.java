package kr.co.healthcare.self_diagnosis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.eazegraph.lib.charts.StackedBarChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.StackedBarModel;

import java.util.ArrayList;
import java.util.List;

import kr.co.healthcare.R;
import kr.co.healthcare.self_diagnosis.ResultDB.SelfDiagnosisResultDatabase;
import kr.co.healthcare.self_diagnosis.ResultDB.*;
import kr.co.healthcare.self_diagnosis.ResultRecycler.RecyclerAdapter;


public class SelfResultDateActivity extends AppCompatActivity {

    //리사이클러뷰
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerAdapter adapter;
    private List<Result> results;

    //stacked bar graph
    private StackedBarChart stackedBarChart;

    Button btn_toDate;
    Button btn_toSymptom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_result_date);

        recyclerView = findViewById(R.id.rv_self_result);
        btn_toDate = findViewById(R.id.btn_toDate);
        btn_toSymptom = findViewById(R.id.btn_toSymptom);

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

        results = SelfDiagnosisResultDatabase.getInstance(this).resultDAO().getAllByDate();
        int size = results.size();
        for (int i = 0; i < size; i++) {
            adapter.addItem(results.get(i));
        }
    }

    private void initialized_bar_graph(){
        stackedBarChart = findViewById(R.id.stacked_Bar_Chart);

        StackedBarModel[] StackBar = {
                new StackedBarModel("고혈압"), new StackedBarModel("골관절염"),
                new StackedBarModel("고지혈증"), new StackedBarModel("요통"),
                new StackedBarModel("당뇨병"), new StackedBarModel("골다공증"),
                new StackedBarModel("치매")
        };

        for(int i=0; i<7; i++){
            StackBar[i].addBar(new BarModel(SelfDiagnosisResultDatabase.getInstance(this).resultDAO().countDiseaseDanger(i), 0xFFE61919));
            StackBar[i].addBar(new BarModel(SelfDiagnosisResultDatabase.getInstance(this).resultDAO().countDiseaseWarning(i), 0xFFFFC107));
            StackBar[i].addBar(new BarModel(SelfDiagnosisResultDatabase.getInstance(this).resultDAO().countDiseaseSafe(i), 0xFF00AC00));
            stackedBarChart.addBar(StackBar[i]);
        }

        stackedBarChart.startAnimation();
    }

    @Override
    protected void onStart() {
        results = SelfDiagnosisResultDatabase.getInstance(this).resultDAO().getAllByDate();
        adapter.addItems((ArrayList) results);
        super.onStart();
    }
}