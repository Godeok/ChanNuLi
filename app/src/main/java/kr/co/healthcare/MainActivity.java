package kr.co.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kr.co.healthcare.diseaseInfoSelect.DiseaseInfoSelectActivity;
import kr.co.healthcare.game.GameMainActivity;
import kr.co.healthcare.healthInfo.HealthInfoActivity;
import kr.co.healthcare.mypage.MypageActivity;
import kr.co.healthcare.self_diagnosis.SelfMainActivity;
import kr.co.healthcare.tutorial.PreferenceManger;
import kr.co.healthcare.tutorial.ui.TutorialActivity;
import kr.co.healthcare.database.UserViewModel;

public class MainActivity extends AppCompatActivity {
    public static Activity mainActivity;
    private Boolean isTutorialFinished = false;
    private TextView userNameTV;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //튜토리얼 완료 확인
        isTutorialFinished = PreferenceManger.getBoolean(this, PreferenceManger.PREF_IS_TUTORIAL_FINISHED);
        mainActivity = MainActivity.this;

        UserViewModel viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        viewModel.getUserName(this).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String name) {
                userNameTV.setText(name);
            }
        });

        if (!isTutorialFinished) {  //튜토리얼 미완료 시
            intent = new Intent(getApplicationContext(), TutorialActivity.class);
            startActivity(intent);
            mainActivity.finish();
        }else{ // 튜토리얼 완료 시
            userNameTV = findViewById(R.id.userName);
            userNameTV.setText(PreferenceManger.getString(this, PreferenceManger.PREF_USER_NAME));
        }

        //자가진단
        final Button selfBtn = (Button)findViewById(R.id.selfBtn);
        selfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), SelfMainActivity.class);
                startActivity(intent);
            }
        });


        //게임
        final Button game_btn = (Button)findViewById(R.id.game_btn);
        game_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), GameMainActivity.class);
                startActivity(intent);
            }
        });
    }

    //마이페이지
    public void showMyPage(View view){
        intent = new Intent(getApplicationContext(), MypageActivity.class);
        startActivity(intent);
    }

    //건강 정보
    public void showHealthInfo(View view){
        intent = new Intent(getApplicationContext(), HealthInfoActivity.class);
        startActivity(intent);
    }

    //질병정보
    public void showDiseaseInfoSelectPage(View view){
        intent = new Intent(getApplicationContext(), DiseaseInfoSelectActivity.class);
        startActivity(intent);
    }

}