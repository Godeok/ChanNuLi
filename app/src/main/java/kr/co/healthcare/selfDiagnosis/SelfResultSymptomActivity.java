package kr.co.healthcare.selfDiagnosis;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kr.co.healthcare.R;
import kr.co.healthcare.selfDiagnosis.ResultDB.SelfDiagnosisResultDatabase;
import kr.co.healthcare.selfDiagnosis.ResultRecycler.RecyclerAdapter;
import kr.co.healthcare.selfDiagnosis.ResultDB.Result;


public class SelfResultSymptomActivity extends AppCompatActivity {

    //리사이클러뷰
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerAdapter recyclerAdapter;
    private List<Result> results;

    Button btn_toDate;
    Button btn_toSymptom;
    Spinner spinner;

    //질병 번호
    static int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_result_symptom);

        recyclerView = findViewById(R.id.rv_self_result);
        spinner = findViewById(R.id.spinner);
        btn_toDate = findViewById(R.id.btn_toDate);
        btn_toSymptom = findViewById(R.id.btn_toSymptom);
        recyclerView = findViewById(R.id.rv_self_result);

        linearLayoutManager = new LinearLayoutManager(this);


        //스피너

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //스피너 글자 색 변경
                ((TextView) spinner.getChildAt(0)).setTextColor(Color.BLACK);

                //결과 보여줄 리사이클러뷰 어뎁터 선언(새로운 정보 불러오면 항상 새로 선언)
                recyclerAdapter = new RecyclerAdapter();

                //스피너에서 선택된 질병명 -> 숫자로 변환 -> 해당 쿼리 불러오기 -> 리사이클러뷰 어뎁터에 데이터 추가
                num = return_disease_num(parent.getItemAtPosition(position).toString());
                check_query(num);
                int size = results.size();
                for (int i = 0; i < size; i++)
                    recyclerAdapter.addItem(results.get(i));

                //리사이클러뷰 화면에 표시하기
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        btn_toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelfResultSymptomActivity.this, SelfResultDateActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        btn_toSymptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelfResultSymptomActivity.this, SelfResultSymptomActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    //질병명 입력하면 숫자로 반환해주는 함수
    int return_disease_num(String string) {
        if (string.equals("고혈압")) return 0;
        else if (string.equals("골관절염")) return 1;
        else if (string.equals("고지혈증")) return 2;
        else if (string.equals("요통/좌골신경통")) return 3;
        else if (string.equals("당뇨병")) return 4;
        else if (string.equals("골다공증")) return 5;
        else if (string.equals("치매")) return 6;
        else return -1;
    }

    //질병에 해당하는 쿼리
    void check_query(int number) {
        if(number>=0 || number<=6)
            results = SelfDiagnosisResultDatabase.getInstance(this).resultDAO().getAllByDisease(number);
        else
            results = SelfDiagnosisResultDatabase.getInstance(this).resultDAO().getAllByDate();
    }
}