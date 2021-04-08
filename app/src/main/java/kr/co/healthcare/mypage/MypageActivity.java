package kr.co.healthcare.mypage;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;

import kr.co.healthcare.tutorial.PreferenceManger;
import kr.co.healthcare.R;

public class MypageActivity extends AppCompatActivity {

    //인적정보 및 질병 관련 위젯
    TextView name_TV;
    TextView age_TV;
    TextView gender_TV;
    TextView health_TV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        name_TV = (TextView) findViewById(R.id.userNameTV);
        age_TV = (TextView) findViewById(R.id.userAgeTV);
        gender_TV = (TextView) findViewById(R.id.userGenderTV);
        health_TV = (TextView) findViewById(R.id.userHealthTV);

        setMyPageData();
/*
        RecyclerView recyclerview = findViewById(R.id.userInfoRecyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);
        UserInfoAdapter userInfoAdapter = new UserInfoAdapter(this);
        recyclerview.setAdapter(userInfoAdapter);
 */
    }

    //인정사항 및 질병 정보 불러오기
    private void setMyPageData(){
        Calendar cal = Calendar.getInstance();
        int current_year = cal.get(Calendar.YEAR);
        int birth_year = Integer.parseInt(PreferenceManger.getString(this, PreferenceManger.PREF_USER_YEAR_OF_BIRTH));
        //이름
        name_TV.setText(PreferenceManger.getString(this, PreferenceManger.PREF_USER_NAME));
        age_TV.setText(Integer.toString(current_year - birth_year +1));
        if(PreferenceManger.getString(this, PreferenceManger.PREF_USER_GENDER).equals(PreferenceManger.GENDER_VALUE_WOMAN)) gender_TV.setText("여자");
        else if(PreferenceManger.getString(this, PreferenceManger.PREF_USER_GENDER).equals(PreferenceManger.GENDER_VALUE_MAN)) gender_TV.setText("남자");
        health_TV.setText(PreferenceManger.getString(this, PreferenceManger.PREF_USER_DISEASES));
    }

}