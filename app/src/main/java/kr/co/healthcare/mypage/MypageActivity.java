package kr.co.healthcare.mypage;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import kr.co.healthcare.PreferenceManger;
import kr.co.healthcare.R;

public class MypageActivity extends AppCompatActivity {

    //인적정보 및 질병 관련 위젯
    TextView name_TV;
    TextView birth_TV;
    ImageView gender_Img;
    TextView dieases_TV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        name_TV = (TextView) findViewById(R.id.name_TV_MP);
        birth_TV = (TextView) findViewById(R.id.birrth_TV_MP);
        gender_Img = (ImageView) findViewById(R.id.gender_Img_MP);
        dieases_TV = (TextView) findViewById(R.id.diseases_TV_MP);

        setMyPageData();
    }

    //인정사항 및 질병 정보 불러오기
    private void setMyPageData(){
        //이름
        name_TV.setText(PreferenceManger.getString(this, "name"));

        //생년월일(나이)
        birth_TV.setText(
                String.format("%d-%d-%d (%d세)",
                        PreferenceManger.getInt(this, "year"),
                        PreferenceManger.getInt(this, "month"),
                        PreferenceManger.getInt(this, "day"),
                        calculateAge())
        );

        //성별에 따른 이미지
        if(PreferenceManger.getString(this, "gender").equals("male")) gender_Img.setImageResource(R.drawable.man);
        else if (PreferenceManger.getString(this, "gender").equals("female")) gender_Img.setImageResource(R.drawable.woman);

        //보유 질병
        dieases_TV.setText(PreferenceManger.getString(this, "diseases"));
    }

    int calculateAge(){
        Calendar cal = Calendar.getInstance();
        int current_year = cal.get(Calendar.YEAR);
        int birth_year = PreferenceManger.getInt(this, "year");

        return (current_year - birth_year +1);
    }
}