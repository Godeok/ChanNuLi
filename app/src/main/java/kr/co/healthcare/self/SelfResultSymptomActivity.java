package kr.co.healthcare.self;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kr.co.healthcare.R;
import kr.co.healthcare.self.Recycler.RecyclerAdapter;
import kr.co.healthcare.self.ResultDB.AppDatabase;
import kr.co.healthcare.self.ResultDB.Result;


public class SelfResultSymptomActivity extends AppCompatActivity {

    //리사이클러뷰
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerAdapter adapter;
    private List<Result> results;

    TextView tv_resultTitle;
    Button btn_toDate;
    Button btn_toSymptom;
    Spinner spinner;
    static int num=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_result_symptom);

        recyclerView = findViewById(R.id.recycler1);
        spinner = findViewById(R.id.spinner);
        btn_toDate = findViewById(R.id.btn_toDate);
        btn_toSymptom = findViewById(R.id.btn_toSymptom);
        tv_resultTitle = findViewById(R.id.tv_resultTitle);
        tv_resultTitle.setText("결과 확인");

        initialized();




        //스피너
        ArrayAdapter monthAdapter = ArrayAdapter
                .createFromResource(
                        this,
                        R.array.질병 ,
                        android.R.layout.simple_spinner_dropdown_item);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(monthAdapter); //어댑터에 연결

        //스피너 리스너
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = (String)parent.getItemAtPosition(position);
                num = return_disease_num(str);
                initialized();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


        btn_toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelfResultSymptomActivity.this, SelfResultDateActivity.class);
                startActivity(intent);
            }
        });

        btn_toSymptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelfResultSymptomActivity.this, SelfResultSymptomActivity.class);
                startActivity(intent);
            }
        });
    }



    private void initialized() {
        recyclerView = findViewById(R.id.recycler1);
        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new RecyclerAdapter();

        //results = AppDatabase.getInstance(this).resultDAO().getAllByDisease1();
        check_query(num);

        int size = results.size();
        for (int i = 0; i < size; i++) {
            adapter.addItem(results.get(i));
        }
    }

    @Override
    protected void onStart() {
        //results = AppDatabase.getInstance(this).resultDAO().getAllByDisease1();
        check_query(num);
        adapter.addItems((ArrayList) results);
        super.onStart();
    }


    int return_disease_num(String string){
        if(string=="고혈압") return 0;
        else if(string=="골관절염") return 1;
        else if(string=="고지혈증") return 2;
        else if(string=="요통/좌골신경통") return 3;
        else if(string=="당뇨병") return 4;
        else if(string=="골다공증") return 5;
        else if(string=="치매") return 6;
        else return 6;
    }

    void check_query(int number){
        if(number==0) results = AppDatabase.getInstance(this).resultDAO().getAllByDisease0();
        else if(number==1) results = AppDatabase.getInstance(this).resultDAO().getAllByDisease1();
        else if(number==2) results = AppDatabase.getInstance(this).resultDAO().getAllByDisease2();
        else if(number==3) results = AppDatabase.getInstance(this).resultDAO().getAllByDisease3();
        else if(number==4) results = AppDatabase.getInstance(this).resultDAO().getAllByDisease4();
        else if(number==5) results = AppDatabase.getInstance(this).resultDAO().getAllByDisease5();
        else if(number==6) results = AppDatabase.getInstance(this).resultDAO().getAllByDisease6();
    }

}