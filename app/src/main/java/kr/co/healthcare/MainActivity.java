package kr.co.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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
    public Activity mainActivity;
    private TextView userNameTV;
    ImageButton avatarImgBtn;
    UserViewModel viewModel;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = MainActivity.this;
        viewModel = UserViewModel.getINSTANCE();
        setLayout();

        checkIfTutorialIsFinished();
    }

    private void setLayout(){
        userNameTV = findViewById(R.id.userName);
        avatarImgBtn = findViewById(R.id.mypageBtn);
    }

    private void checkIfTutorialIsFinished(){
        if (!UserInfoPreferenceManger.getBoolean(
                this, UserInfoPreferenceManger.PREF_KEY_TUTORIAL_FINISHED)) {
            intent = new Intent(getApplicationContext(), TutorialActivity.class);
            startActivity(intent);
            mainActivity.finish();
        }else{
            setUserName();
            setAvatarImg();
        }
    }

    private void setUserName(){
        viewModel.getUserGender(this).observe(this, new Observer<String>() {
            @Override
            public void onChanged(final String name) { userNameTV.setText(name); }
        });
    }

    private void setAvatarImg(){
        viewModel.getUserGender(this).observe(this, new Observer<String>() {
            @Override
            public void onChanged(final String gender) {
                if(gender.equals(UserInfoPreferenceManger.PREF_VALUE_GENDER_WOMAN)) avatarImgBtn.setImageResource(R.drawable.img_person_woman_round);
                if(gender.equals(UserInfoPreferenceManger.PREF_VALUE_GENDER_MAN)) avatarImgBtn.setImageResource(R.drawable.img_person_man_round);
            }
        });
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