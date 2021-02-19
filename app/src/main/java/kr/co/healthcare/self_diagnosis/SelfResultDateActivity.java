package kr.co.healthcare.self_diagnosis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    TextView tv_resultTitle;
    Button btn_toDate;
    Button btn_toSymptom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_result_date);

        recyclerView = findViewById(R.id.recycler1);
        btn_toDate = findViewById(R.id.btn_toDate);
        btn_toSymptom = findViewById(R.id.btn_toSymptom);
        tv_resultTitle = findViewById(R.id.tv_resultTitle);
        tv_resultTitle.setText("결과 확인");

        //DB에서 정보 가져오기
        initialized();
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        btn_toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelfResultDateActivity.this, SelfResultDateActivity.class);
                startActivity(intent);
            }
        });

        btn_toSymptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelfResultDateActivity.this, SelfResultSymptomActivity.class);
                startActivity(intent);
            }
        });
    }



    private void initialized() {
        recyclerView = findViewById(R.id.recycler1);
        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new RecyclerAdapter();

        results = AppDatabase.getInstance(this).resultDAO().getAllByDate();
        int size = results.size();
        for (int i = 0; i < size; i++) {
            adapter.addItem(results.get(i));
        }
    }

    @Override
    protected void onStart() {
        results = AppDatabase.getInstance(this).resultDAO().getAllByDate();
        adapter.addItems((ArrayList) results);
        super.onStart();
    }
}