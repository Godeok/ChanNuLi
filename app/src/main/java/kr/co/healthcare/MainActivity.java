package kr.co.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kr.co.healthcare.self.SelfMainActivity;

public class MainActivity extends AppCompatActivity {
    public static Activity mainActivity;
    private Boolean isTutorialFinished = false;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //PreferenceManger.clear(this);

        //튜토리얼 완료 확인
        isTutorialFinished = PreferenceManger.getBoolean(this, "isTutorialFinished");
        mainActivity = MainActivity.this;

        if (!isTutorialFinished) {  //튜토리얼 미완료 시
            intent = new Intent(getApplicationContext(), TutorialStartActivity.class);
            startActivity(intent);
            mainActivity.finish();
        }else{ // 튜토리얼 완료 시
            TextView userNameTV = findViewById(R.id.userName);
            userNameTV.setText(PreferenceManger.getString(this, "name"));
        }

        //마이페이지
        final Button mypageBtn = (Button) findViewById(R.id.mypageBtn);
        mypageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), MypageActivity.class);
                startActivity(intent);
            }
        });

        //자가진단
        final Button selfBtn = (Button)findViewById(R.id.selfBtn);
        selfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), SelfMainActivity.class);
                startActivity(intent);
            }
        });
    }
}