package kr.co.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shuhart.stepview.StepView;

public class MainActivity extends AppCompatActivity {
    public static Activity mainActivity;
    private Boolean isTutorialFinished = false;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = MainActivity.this;
        //초기화 연습용
        //커밋 추가 연습
        //PreferenceManger.clear(this);

        isTutorialFinished = PreferenceManger.getBoolean(this, "isTutorialFinished");
        if (!isTutorialFinished) {
            intent = new Intent(getApplicationContext(), TutorialActivity.class);
            startActivity(intent);
            mainActivity.finish();
        }else{
            TextView userNameTV = findViewById(R.id.userName);
            userNameTV.setText(PreferenceManger.getString(this, "name"));
        }

        final Button mypageBtn = (Button) findViewById(R.id.mypageBtn);
        mypageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), MypageActivity.class);
                startActivity(intent);
            }
        });
    }
}