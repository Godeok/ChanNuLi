package kr.co.healthcare.self;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import kr.co.healthcare.R;
import kr.co.healthcare.self.ResultDB.AppDatabase;
import kr.co.healthcare.self.ResultDB.Result;


public class SelfShowResult extends AppCompatActivity {

    private static final String TAG="SelfShowResult";
    private AppDatabase db;
    Button add_data_btn, finish_btn;
    TextView tv_result, tv_desc;

    String[] disease_list = {"고혈압", "골관절염", "고지혈증", "요통/좌골신경통", "당뇨병", "골다골증", "치매"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_show_result);

        tv_result = findViewById(R.id.tv_result);
        tv_desc = findViewById(R.id.tv_desc);
        add_data_btn = findViewById(R.id.add_data_btn);
        finish_btn = findViewById(R.id.finish_btn);
        db = AppDatabase.getInstance(this);

        Intent intent = getIntent();
        final int count = intent.getIntExtra("count", -1);
        final int disease_num = intent.getIntExtra("disease_num", -1);

        tv_result.setText("검사 결과 총 "+count+"개의 항목에서 '예'라고 답했습니다.");

        if (count<1) tv_desc.setText("정상 단계입니다.");
        else if (count<3) tv_desc.setText("주의 단계입니다.");
        else tv_desc.setText("위험 단계입니다.");

        //검사 날짜
        long now = System.currentTimeMillis();
        Date nowDate = new Date(now);
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy. MM. dd");
        final String date = mFormat.format(nowDate);

        //DB에 자가진단 결과 저장
        add_data_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Result result = new Result(disease_num, count, date);
                db.resultDAO().insert(result);
                finish();
            }
        });

        //종료
        finish_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}