package kr.co.healthcare.self;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import kr.co.healthcare.R;

public class SelfShowResult extends AppCompatActivity {

    TextView tv_result, tv_desc;

    //SharedPreferences sf = getSharedPreferences("saveResult", MODE_PRIVATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_show_result);

        tv_result = findViewById(R.id.tv_result);
        tv_desc = findViewById(R.id.tv_desc);

        Intent intent = getIntent();
        int count = intent.getIntExtra("count", -1);
        int disease_num = intent.getIntExtra("disease_num", -1);

        tv_result.setText("검사 결과 총 "+count+"개의 항목에서 '예'라고 답했습니다.");

        if (count<1) tv_desc.setText("정상 단계입니다.");
        else if (count<3) tv_desc.setText("주의 단계입니다.");
        else tv_desc.setText("위험 단계입니다.");

        //shared preferences : 자가진단 결과 저장
        SharedPreferences sp = getSharedPreferences("saveResult", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        String result = Integer.toString(disease_num) + String.format("%02d", count);
        editor.putString("date", result);     //key-value. 날짜-진단결과(_문제번호 __카운트)
        editor.commit();
    }
}