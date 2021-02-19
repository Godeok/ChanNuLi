package kr.co.healthcare.self_diagnosis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import kr.co.healthcare.R;
import androidx.appcompat.app.AppCompatActivity;


public class SelfMainActivity extends AppCompatActivity {

    private Button btn_chkDate, btn_chkSymptom;

    Button button_q[] = new Button[7];
    Integer[] Rid_button_q = {
            R.id.btn_01, R.id.btn_02, R.id.btn_03, R.id.btn_04,
            R.id.btn_05, R.id.btn_06, R.id.btn_07
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_main);

        //버튼 ID 연결
        for (int i=0; i<7; i++) button_q[i] = findViewById(Rid_button_q[i]);
        btn_chkDate = findViewById(R.id.btn_chkDate);
        btn_chkSymptom = findViewById(R.id.btn_chkSymptom);

        //자가진단 버튼 Listener 연결
        for (int i=0; i<button_q.length; i++){
            final String buttonText = button_q[i].getText().toString();
            final int INDEX = i;

            button_q[INDEX].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SelfMainActivity.this, SelfDiagnosisActivity.class);
                    intent.putExtra("str", buttonText);
                    intent.putExtra("disease_num", INDEX);
                    startActivity(intent);
                }
            });
        }

        //결과확인 버튼 - 날짜별
        btn_chkDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelfMainActivity.this, SelfResultDateActivity.class);
                startActivity(intent);
            }
        });

        //결과확인 버튼 - 증상별
        btn_chkSymptom.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelfMainActivity.this, SelfResultSymptomActivity.class);
                startActivity(intent);
            }
        });
    }

}