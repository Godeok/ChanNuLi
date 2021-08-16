package kr.co.healthcare.mypage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Calendar;

import kr.co.healthcare.database.UserViewModel;
import kr.co.healthcare.mypage.gamehistory.GameScore;
import kr.co.healthcare.preference.GameResultPreferenceManager;
import kr.co.healthcare.preference.UserInfoPreferenceManger;
import kr.co.healthcare.R;

public class MypageActivity extends AppCompatActivity {

    //인적정보 및 질병 관련 위젯
    TextView name_TV;
    TextView age_TV;
    UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        viewModel = UserViewModel.getINSTANCE();

        name_TV = findViewById(R.id.userName_TV);
        age_TV = findViewById(R.id.userAge_TV);

        setObserverOnUserName();
        setObserverOnUserAge();
        setObserverOnUserDiseases();

        setGameBestScore();
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
        Intent intent = new Intent(getApplicationContext(), InitActivity.class);
        startActivity(intent);
    }

    private void setChip(){
        ChipGroup chipGroup = findViewById(R.id.CHIPGROUP_diseases);
        chipGroup.removeAllViews();
        ArrayList<String> diseases = viewModel.getUserDiseases(this).getValue();
        assert diseases != null;
        diseases.forEach(diseaseName -> {
            final Chip chip = (Chip) this.getLayoutInflater().inflate(
                    R.layout.item_chip, chipGroup, false);
            chip.setText(diseaseName);
            chipGroup.addView(chip);
        });
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

    private void setObserverOnUserDiseases(){
        final Observer<ArrayList> diseaseObserver = new Observer<ArrayList>() {
            @Override
            public void onChanged(ArrayList arrayList) {
                setChip();
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

