package kr.co.healthcare.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Calendar;

import kr.co.healthcare.database.UserViewModel;
import kr.co.healthcare.mypage.gamehistory.GameScore;
import kr.co.healthcare.R;
import kr.co.healthcare.mypage.selfdiagnosishistory.SectionsPagerAdapter;
import kr.co.healthcare.preference.UserInfoPreferenceManger;
import kr.co.healthcare.tutorial.ui.fragment.TutorialFirstStepFragment;
import kr.co.healthcare.tutorial.ui.fragment.TutorialFourthStepFragment;
import kr.co.healthcare.tutorial.ui.fragment.TutorialSecondStepFragment;
import kr.co.healthcare.tutorial.ui.fragment.TutorialThirdStepFragment;

public class MypageActivity extends AppCompatActivity {

    private TextView name_TV;
    private TextView age_TV;
    private ImageView avatar_img;
    private UserViewModel viewModel;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private SectionsPagerAdapter sectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        viewModel = UserViewModel.getINSTANCE();
        sectionsPagerAdapter = new SectionsPagerAdapter(this);

        setResource();

        setObserverOnUserName();
        setObserverOnUserAge();
        setObserverOnUserDiseases();
        setObserverOnUserGender();

        setGameBestScore();

        viewPager.setAdapter(sectionsPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(sectionsPagerAdapter.getTabTitle(position))).attach();
    }

    private void setResource(){
        name_TV = findViewById(R.id.userName_TV);
        age_TV = findViewById(R.id.userAge_TV);
        avatar_img = findViewById(R.id.img_avatar);
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
    }

    //사용자 정보 수정 페이지로 가기
    public void showEditUserInfoActivity(View view){
        Intent intent = new Intent(getApplicationContext(), EditUserInfoActivity.class);
        startActivity(intent);
    }

    //질병 수정 페이지로 가기
    public void showEditDiseaseActivity(View view){
        Intent intent = new Intent(getApplicationContext(), EditDiseaseActivity.class);
        startActivity(intent);
    }

    //어플 설명 페이지 이동
    public void showAppDescriptionActivity(View view){
        Intent intent = new Intent(getApplicationContext(), AppDescriptionActivity.class);
        startActivity(intent);
    }

    //초기화 페이지 이동
    public void showInitActivity(View view){
        Intent intent = new Intent(getApplicationContext(), ResetActivity.class);
        startActivity(intent);
    }

    private void setObserverOnUserName(){
        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(final String name) {
                name_TV.setText(name);
            }
        };
        viewModel.getUserName(this).observe(this, nameObserver);
    }

    private void setObserverOnUserAge(){
        final Observer<String> ageObserver = new Observer<String>() {
            @Override
            public void onChanged(final String year) {
                Calendar cal = Calendar.getInstance();
                int currentYear = cal.get(Calendar.YEAR);
                int birthYear = Integer.parseInt(year);
                age_TV.setText(Integer.toString(currentYear - birthYear + 1));
            }
        };
        viewModel.getUserBirthYear(this).observe(this, ageObserver);
    }

    private void setObserverOnUserGender(){
        final Observer<String> genderObserver = new Observer<String>() {
            @Override
            public void onChanged(final String gender) {
                if(gender.equals(UserInfoPreferenceManger.PREF_VALUE_GENDER_WOMAN)) avatar_img.setImageResource(R.drawable.img_person_woman_round);
                if(gender.equals(UserInfoPreferenceManger.PREF_VALUE_GENDER_MAN)) avatar_img.setImageResource(R.drawable.img_person_man_round);
            }
        };
        viewModel.getUserGender(this).observe(this, genderObserver);
    }

    private void setObserverOnUserDiseases(){
        final Observer<ArrayList> diseaseObserver = new Observer<ArrayList>() {
            @Override
            public void onChanged(ArrayList arrayList) {
                getSupportFragmentManager().beginTransaction().replace(R.id.diseasesFrameLayout, new DiseasesFragment(MypageActivity.this, viewModel)).commit();
            }};
        viewModel.getUserDiseases(this).observe(this, diseaseObserver);
    }

    private void setGameBestScore(){

        GameScore[] gameScores = {
                new GameScore(1, 1, findViewById(R.id.tv_game1_level1_bestscore)),
                new GameScore(1, 2, findViewById(R.id.tv_game1_level2_bestscore)),
                new GameScore(1, 3, findViewById(R.id.tv_game1_level3_bestscore)),
                new GameScore(2, 1, findViewById(R.id.tv_game2_level1_bestscore)),
                new GameScore(2, 2, findViewById(R.id.tv_game2_level2_bestscore)),
                new GameScore(2, 3, findViewById(R.id.tv_game2_level3_bestscore)),
                new GameScore(3, 1, findViewById(R.id.tv_game3_level1_bestscore)),
        };

        for(GameScore gameScore : gameScores){
            gameScore.setBestScoreToTextView(this);
        }
    }
}

