package kr.co.healthcare.self_diagnosis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import kr.co.healthcare.R;
import kr.co.healthcare.self_diagnosis.MainRecycler.SelfMainAdapter;
import kr.co.healthcare.self_diagnosis.MainRecycler.SelfMainData;
import kr.co.healthcare.self_diagnosis.QuestionDB.DataAdapter;
import kr.co.healthcare.self_diagnosis.QuestionDB.Questions;
import kr.co.healthcare.self_diagnosis.ResultDB.Result;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class SelfMainActivity extends AppCompatActivity {

    Button btn_chkDate;
    ArrayList<SelfMainData> dataList;
    List<Result> results;
    public List<Questions> questionsList;

    String[] disease_list = {"고혈압", "골관절염", "고지혈증", "요통/좌골신경통", "당뇨병", "골다공증", "치매"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_main);

        btn_chkDate = findViewById(R.id.btn_chkDate);

        this.InitializeData();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview_selfmain);
        GridLayoutManager manager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager); // LayoutManager 등록
        recyclerView.setAdapter(new SelfMainAdapter(getApplicationContext(), dataList));  // Adapter 등록

        //결과확인 버튼 - 날짜별
        btn_chkDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelfMainActivity.this, SelfResultActivity.class);
                startActivity(intent);
            }
        });
    }

    public void InitializeData(){
        dataList = new ArrayList<>();

        for (int i=0; i<7; i++) {
            initLoadDB(i);
            dataList.add(new SelfMainData(i, disease_list[i], questionsList.size()));
        }
    }

    //load DB (Question db 응용)
    private void initLoadDB(int n){
        DataAdapter mDBHelper = new DataAdapter(getApplicationContext());
        mDBHelper.createDatabase();
        mDBHelper.open();
        questionsList = mDBHelper.getTableData(n);
        mDBHelper.close();
    }
}