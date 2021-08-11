package kr.co.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import kr.co.healthcare.diseaseInfoSelect.DiseaseInfoSelectActivity;
import kr.co.healthcare.game.GameMainActivity;
import kr.co.healthcare.healthInfo.HealthInfoActivity;
import kr.co.healthcare.mypage.MypageActivity;
import kr.co.healthcare.self_diagnosis.SelfMainActivity;
import kr.co.healthcare.preference.UserInfoPreferenceManger;
import kr.co.healthcare.tutorial.ui.TutorialActivity;
import kr.co.healthcare.database.UserViewModel;

public class MainActivity extends AppCompatActivity {
    public static Activity mainActivity;
    private TextView userNameTV;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = MainActivity.this;
        userNameTV = findViewById(R.id.userName);

        checkISTutorialFinished();
    }

    private void checkISTutorialFinished(){
        //튜토리얼 정보 불러오기
        boolean isTutorialFinished = UserInfoPreferenceManger.getBoolean(this, UserInfoPreferenceManger.PREF_KEY_TUTORIAL_FINISHED);

        if (!isTutorialFinished) {  //튜토리얼 미완료 시
            intent = new Intent(getApplicationContext(), TutorialActivity.class);
            startActivity(intent);
            mainActivity.finish();
        }else{ // 튜토리얼 완료 시
            userNameTV.setText(UserInfoPreferenceManger.getString(this, UserInfoPreferenceManger.PREF_KEY_USER_NAME));

            UserViewModel viewModel = new ViewModelProvider(this).get(UserViewModel.class);
            viewModel.getUserName(this).observe(this, new Observer<String>() {
                @Override
                public void onChanged(String name) {
                    userNameTV.setText(name);
                }
            });
        }
    }

    //자가진단
    public void showSelfDiagnosis(View view) {
        intent = new Intent(getApplicationContext(), SelfMainActivity.class);
        startActivity(intent);
    }

    //게임
    public void showGame(View view) {
        intent = new Intent(getApplicationContext(), GameMainActivity.class);
        startActivity(intent);
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