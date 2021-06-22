package kr.co.healthcare.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Calendar;

import kr.co.healthcare.tutorial.PreferenceManger;
import kr.co.healthcare.R;
import kr.co.healthcare.database.UserViewModel;

public class MypageActivity extends AppCompatActivity {

    //인적정보 및 질병 관련 위젯
    TextView name_TV;
    TextView age_TV;
    TextView gender_TV;
    Button editBtn;

    private String[] tagNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        tagNames = getResources().getStringArray(R.array.DISEASES_LABEL);
        name_TV = (TextView) findViewById(R.id.userNameTV);
        age_TV = (TextView) findViewById(R.id.userAgeTV);
        gender_TV = (TextView) findViewById(R.id.userGenderTV);

        UserViewModel viewModel = UserViewModel.getINSTANCE();

        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(final String name) {
                name_TV.setText(name);
            }
        };
        viewModel.getUserName(this).observe(this, nameObserver);

        final Observer<String> genderObserver = new Observer<String>() {
            @Override
            public void onChanged(final String gender) {
                if(gender.equals(PreferenceManger.GENDER_VALUE_WOMAN)) gender_TV.setText("여자");
                else if(gender.equals(PreferenceManger.GENDER_VALUE_MAN)) gender_TV.setText("남자");
            }
        };
        viewModel.getUserGender(this).observe(this, genderObserver);

        final Observer<String> birthYearObserver = new Observer<String>() {
            @Override
            public void onChanged(final String year) {
                Calendar cal = Calendar.getInstance();
                int currentYear = cal.get(Calendar.YEAR);
                int birthYear = Integer.parseInt(year);
                age_TV.setText((currentYear - birthYear + 1) + "(세)");
            }
        };
        viewModel.getUserBirthYear(this).observe(this, birthYearObserver);

        //setChip();
    }

    //이름 수정 페이지로 가기
    public void showEditNameActivity(View view){
        Intent intent = new Intent(getApplicationContext(), EditNameActivity.class);
        startActivity(intent);
    }

    //나이 수정 페이지로 가기
    public void showEditAgeActivity(View view){
        Intent intent = new Intent(getApplicationContext(), EditAgeActivity.class);
        startActivity(intent);
    }

    //나이 수정 페이지로 가기
    public void showEditGenderActivity(View view){
        Intent intent = new Intent(getApplicationContext(), EditGenderActivity.class);
        startActivity(intent);
    }

    //질병 수정 페이지로 가기
    public void showEditDiseaseActivity(View view){
        Intent intent = new Intent(getApplicationContext(), EditDiseaseActivity.class);
        startActivity(intent);
    }


    private void setChip(){
        ArrayList<String> userDiseasesArrayList = PreferenceManger.getStringArrayList(this, PreferenceManger.PREF_USER_DISEASES);
        ChipGroup chipGroup = findViewById(R.id.chipGroup);
        for (String diseaseIndex : userDiseasesArrayList) {
            final Chip chip = (Chip) this.getLayoutInflater().inflate(R.layout.layout_mypage_chip, chipGroup, false);
            chip.setText(tagNames[Integer.parseInt(diseaseIndex)-1]);
            chipGroup.addView(chip);
        }
    }

}