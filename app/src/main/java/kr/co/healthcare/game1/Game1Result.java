package kr.co.healthcare.game1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kr.co.healthcare.R;

public class Game1Result extends AppCompatActivity {

    TextView level_tv;
    TextView score_tv;
    TextView showBest_tv;
    Button restart_btn;
    Button end_btn;

    //최고기록 저장용 shared preferences
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    int best_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1_result);

        level_tv = findViewById(R.id.level_tv);
        score_tv = findViewById(R.id.score_tv);
        showBest_tv = findViewById(R.id.showBest_tv);
        restart_btn = findViewById(R.id.restart_btn);
        end_btn = findViewById(R.id.end_btn);



        //shared preference 초기화
        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();

        //저장한 값 불러오기
        best_score = pref.getInt("best_score", 0);


        int score = getIntent().getIntExtra("score", -1);
        int level = getIntent().getIntExtra("level", -1);
        show_level(level);

        if(score>best_score){
            best_score = score;
            editor.putInt("best_score", best_score);
            editor.apply(); //저장
        }
        showBest_tv.setText(best_score+"점");
        score_tv.setText(score+"점");



        restart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Game1Activity.class);
                startActivity(intent);
            }
        });

        end_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void show_level(int level){
        if(level==1) level_tv.setText("쉬움");
        else if(level==2) level_tv.setText("중간");
        else level_tv.setText("어려움");
    }
}