package kr.co.healthcare.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Calendar;

import kr.co.healthcare.tutorial.PreferenceManger;
import kr.co.healthcare.R;

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

        setMyPageData();
/*
        RecyclerView recyclerview = findViewById(R.id.userInfoRecyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);
        UserInfoAdapter userInfoAdapter = new UserInfoAdapter(this);
        recyclerview.setAdapter(userInfoAdapter);
 */
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


    //인정사항 및 질병 정보 불러오기
    private void setMyPageData(){
        //이름
        name_TV.setText(PreferenceManger.getString(this, PreferenceManger.PREF_USER_NAME));

        //나이
        Calendar cal = Calendar.getInstance();
        int current_year = cal.get(Calendar.YEAR);
        int birth_year = Integer.parseInt(PreferenceManger.getString(this, PreferenceManger.PREF_USER_YEAR_OF_BIRTH));
        age_TV.setText(Integer.toString(current_year - birth_year +1) + "(세)");

        //성별
        if(PreferenceManger.getString(this, PreferenceManger.PREF_USER_GENDER).equals(PreferenceManger.GENDER_VALUE_WOMAN)) gender_TV.setText("여자");
        else if(PreferenceManger.getString(this, PreferenceManger.PREF_USER_GENDER).equals(PreferenceManger.GENDER_VALUE_MAN)) gender_TV.setText("남자");

        //질병
        setChip();
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