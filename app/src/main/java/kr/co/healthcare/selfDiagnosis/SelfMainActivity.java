package kr.co.healthcare.selfDiagnosis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import kr.co.healthcare.R;
import kr.co.healthcare.selfDiagnosis.MainRecycler.SelfMainAdapter;
import kr.co.healthcare.selfDiagnosis.MainRecycler.SelfMainData;
import kr.co.healthcare.selfDiagnosis.QuestionDB.LoadDbClass;
import kr.co.healthcare.selfDiagnosis.QuestionDB.QuesDataAdapter;
import kr.co.healthcare.selfDiagnosis.QuestionDB.Questions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class SelfMainActivity extends AppCompatActivity {

    Button btn_chkDate;
    ArrayList<SelfMainData> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_main);

        btn_chkDate = findViewById(R.id.btn_chkDate);

        dataList = LoadDbClass.InitializeData(getApplicationContext());

        //버튼 리사이클러뷰
        RecyclerView recyclerView = findViewById(R.id.recyclerview_selfmain);
        GridLayoutManager manager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);     //LayoutManager 등록(리사이클러뷰를 grid 형식으로 set)
        recyclerView.setAdapter(new SelfMainAdapter(getApplicationContext(), dataList));  //리사이클러뷰에 Adapter 등록

        //결과확인 버튼 연결
        btn_chkDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelfMainActivity.this, SelfResultActivity.class);
                startActivity(intent);
            }
        });
    }
}