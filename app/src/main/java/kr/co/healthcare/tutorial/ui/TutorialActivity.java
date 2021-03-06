package kr.co.healthcare.tutorial.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import kr.co.healthcare.R;
import kr.co.healthcare.tutorial.PreferenceManger;
import kr.co.healthcare.tutorial.ui.fragment.TutorialFirstStepFragment;
import kr.co.healthcare.tutorial.ui.fragment.TutorialFourthStepFragment;
import kr.co.healthcare.tutorial.ui.fragment.TutorialSecondStepFragment;
import kr.co.healthcare.tutorial.ui.fragment.TutorialThirdStepFragment;
import kr.co.healthcare.walkthrough.WalkthroughActivity;


//ToDo: 2,3번째 페이지 문구에 사람 이름 추가 ex. a멍멍멍님!
public class TutorialActivity extends AppCompatActivity {

    public static Activity tutorialActivity;

    private TextView title;
    private TextView subTitle;
    private ProgressBar progressBar;
    private Button nextBtn;

    private ArrayList<String> titles;

    private TutorialFirstStepFragment firstStepFragment;
    private TutorialSecondStepFragment secondStepFragment;
    private TutorialThirdStepFragment thirdStepFragment;
    private TutorialFourthStepFragment fourthStepFragment;

    private int progressNum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_main);
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        tutorialActivity = TutorialActivity.this;

        title = findViewById(R.id.stepExplain_TV);
        subTitle = findViewById(R.id.step_TV);
        progressBar = findViewById(R.id.progressBar);
        nextBtn = findViewById(R.id.stepBtn);

        initTitles();
        initFragments();

        //튜토리얼 첫 번째 단계 내용 화면 초기화
        changeTutorial(progressNum);
    }

    //fragments 초기화 및 생성
    private void initFragments(){
        firstStepFragment = new TutorialFirstStepFragment(this, nextBtn);
        secondStepFragment = new TutorialSecondStepFragment(this, nextBtn);
        thirdStepFragment = new TutorialThirdStepFragment(this, nextBtn);
        fourthStepFragment = new TutorialFourthStepFragment(this, nextBtn);
    }

    //titles 초기화
    private void initTitles(){
        titles = new ArrayList<String>();
        titles.add(getString(R.string.tutorial_step_1));
        titles.add(getString(R.string.tutorial_step_2));
        titles.add(getString(R.string.tutorial_step_3));
        titles.add(getString(R.string.tutorial_step_4));
    }

    public void onClickNextStepBtn(View v){
        setUserData(progressNum);
        if(progressNum<4) {
            changeTutorial(++progressNum);
            // 버튼 다시 비활성화
            nextBtn.setEnabled(false);
            nextBtn.setBackgroundResource(R.drawable.btn_tutorial_step_not_finished);
        }else{
            PreferenceManger.setBoolean(this, PreferenceManger.PREF_IS_TUTORIAL_FINISHED, true);
            Intent intent = new Intent(getApplicationContext(), WalkthroughActivity.class);
            startActivity(intent);
            tutorialActivity.finish();
        }
    }

    private void changeTutorial(int index){
        subTitle.setText(getSubTitleByIndex(index));
        title.setText(getTitleByIndex(index));
        changeProgressBar(index);
        changeFragment(index);
    }

    private String getSubTitleByIndex(int index){ return String.format("%d번째 단계", index); }

    private String getTitleByIndex(int index){ return titles.get(index-1); }

    private void changeProgressBar(int index){ progressBar.setProgress(index); }

    private void setUserData(int index){
        if (index == 1) firstStepFragment.setUserName();
        else if (index == 2) secondStepFragment.setUserGender();
        else if (index == 3) thirdStepFragment.setUserYearOfBirth();
        else if (index == 4) fourthStepFragment.setUserDiseases();
        else throw new IllegalStateException("Unexpected value: " + index);
    }

    private void changeFragment(int index){
        if (index == 1) getSupportFragmentManager().beginTransaction().replace(R.id.tutorialFrameLayout, firstStepFragment).commit();
        else if (index == 2) getSupportFragmentManager().beginTransaction().replace(R.id.tutorialFrameLayout, secondStepFragment).commit();
        else if (index == 3) getSupportFragmentManager().beginTransaction().replace(R.id.tutorialFrameLayout, thirdStepFragment).commit();
        else if (index == 4) getSupportFragmentManager().beginTransaction().replace(R.id.tutorialFrameLayout, fourthStepFragment).commit();
        else throw new IllegalStateException("Unexpected value: " + index);
    }

    // 뒤로가기 버튼
    public void onClickBackBtn(View v){
        changeTutorial(--progressNum);
    }
}