package kr.co.healthcare.selfDiagnosis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import kr.co.healthcare.R;
import kr.co.healthcare.selfDiagnosis.ResultDB.SelfDiagnosisResultDatabase;
import kr.co.healthcare.selfDiagnosis.ResultDB.Result;

import static kr.co.healthcare.selfDiagnosis.ResultDBGlobal.*;


public class SelfShowResult extends AppCompatActivity {

    private static final String TAG="SelfShowResult";
    private SelfDiagnosisResultDatabase db;
    Button btn_add_data, btn_finish;
    TextView tv_result, tv_desc, tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_show_result);

        tv_title = findViewById(R.id.tv_title);
        tv_result = findViewById(R.id.tv_result);
        tv_desc = findViewById(R.id.tv_desc);
        btn_add_data = findViewById(R.id.btn_add_data);
        btn_finish = findViewById(R.id.btn_finish);
        db = SelfDiagnosisResultDatabase.getInstance(this);

        Intent intent = getIntent();
        final int count = intent.getIntExtra("count", -1);
        final int disease_num = intent.getIntExtra("disease_num", -1);
        final String title = intent.getStringExtra("title");

        tv_title.setText(title);
        tv_result.setText("검사 결과 총 "+count+"개의 항목에서 '예'라고 답했습니다.");

        if (count<=getRange_safe(disease_num)) tv_desc.setText("정상 단계입니다.");
        else if (count<=getRange_warning(disease_num)) tv_desc.setText("주의 단계입니다.");
        else tv_desc.setText("위험 단계입니다.");

        //검사 날짜
        long now = System.currentTimeMillis();
        Date nowDate = new Date(now);
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy. MM. dd");
        final String date = mFormat.format(nowDate);

        //DB에 자가진단 결과 저장
        btn_add_data.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Result result = new Result(disease_num, count, date);
                db.resultDAO().insert(result);

                Intent intent = new Intent(SelfShowResult.this, SelfResultActivity.class);
                startActivity(intent);
            }
        });

        //종료
        btn_finish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelfShowResult.this, SelfMainActivity.class);
                startActivity(intent);
            }
        });
    }
}