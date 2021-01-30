package kr.co.healthcare.game;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kr.co.healthcare.R;

public class Game1ResultActivity extends AppCompatActivity {

    TextView level_tv;
    TextView score_tv;
    TextView showBest_tv;
    Button restart_btn;
    Button end_btn;

    //최고기록 저장용 shared preferences
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    int best_score_lv1, best_score_lv2, best_score_lv3;
    int score, level;

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
        best_score_lv1 = pref.getInt("best_score_lv1", 0);
        best_score_lv2 = pref.getInt("best_score_lv2", 0);
        best_score_lv3 = pref.getInt("best_score_lv3", 0);


        //이전 액티비티에서 점수와 레벨 받아오기
        score = getIntent().getIntExtra("score", -1);
        level = getIntent().getIntExtra("level", -1);
        show_level(level);
        save_score(level);


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

    void save_score(int level){
        if(level==1) {
            if(score>best_score_lv1){
                best_score_lv1 = score;
                editor.putInt("best_score_lv1", best_score_lv1);
                editor.apply(); //저장
            }
            showBest_tv.setText("[쉬움] "+best_score_lv1+"점");
            score_tv.setText(score+"점");
        }
        else if(level==2) {
            if(score>best_score_lv2){
                best_score_lv2 = score;
                editor.putInt("best_score_lv2", best_score_lv2);
                editor.apply(); //저장
            }
            showBest_tv.setText("[중간] "+best_score_lv2+"점");
            score_tv.setText(score+"점");
        }
        else {
            if(score>best_score_lv3){
                best_score_lv3 = score;
                editor.putInt("best_score_lv3", best_score_lv3);
                editor.apply(); //저장
            }
            showBest_tv.setText("[어려움] "+best_score_lv3+"점");
            score_tv.setText(score+"점");
        }
    }

    void show_level(int level){
        if(level==1) level_tv.setText("쉬움"+level);
        else if(level==2) level_tv.setText("중간"+level);
        else level_tv.setText("어려움"+level);
    }
}