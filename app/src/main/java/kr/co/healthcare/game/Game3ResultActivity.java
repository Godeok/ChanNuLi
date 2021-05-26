package kr.co.healthcare.game;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import kr.co.healthcare.R;

public class Game3ResultActivity extends AppCompatActivity {

    TextView tv_level;
    TextView tv_score;
    TextView tv_showBest;
    Button btn_restart;
    Button btn_end;

    //최고기록 저장용 shared preferences
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    int best_score_lv1, best_score_lv2, best_score_lv3;
    int score, level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1_result);

        tv_level = findViewById(R.id.tv_level);
        tv_score = findViewById(R.id.tv_score);
        tv_showBest = findViewById(R.id.tv_showBest);
        btn_restart = findViewById(R.id.btn_restart);
        btn_end = findViewById(R.id.btn_end);


        //shared preference 초기화
        pref = getSharedPreferences("pref3", Activity.MODE_PRIVATE);
        editor = pref.edit();

        //저장한 값 불러오기
        best_score_lv1 = pref.getInt("best_score_lv1", 0);
        best_score_lv2 = pref.getInt("best_score_lv2", 0);
        best_score_lv3 = pref.getInt("best_score_lv3", 0);


        //이전 액티비티에서 점수와 레벨 받아오기
        score = getIntent().getIntExtra("score", -1);
        //level = getIntent().getIntExtra("level", -1);
        level=2;
        show_level(level);
        save_score(level);


        //다시하기
        btn_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Game1Activity.class);
                intent.putExtra("level", level);
                startActivity(intent);
            }
        });

        //끝내기
        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish();
            }
        });
    }

    @Override
    public void onBackPressed() { }

    void save_score(int level){
        if(level==1) {
            if(score>best_score_lv1){
                best_score_lv1 = score;
                editor.putInt("best_score_lv1", best_score_lv1);
                editor.apply(); //저장
            }
            tv_showBest.setText("[쉬움] "+best_score_lv1+"점");
            tv_score.setText(score+"점");
        }
        else if(level==2) {
            if(score>best_score_lv2){
                best_score_lv2 = score;
                editor.putInt("best_score_lv2", best_score_lv2);
                editor.apply(); //저장
            }
            tv_showBest.setText("[중간] "+best_score_lv2+"점");
            tv_score.setText(score+"점");
        }
        else {
            if(score>best_score_lv3){
                best_score_lv3 = score;
                editor.putInt("best_score_lv3", best_score_lv3);
                editor.apply(); //저장
            }
            tv_showBest.setText("[어려움] "+best_score_lv3+"점");
            tv_score.setText(score+"점");
        }
    }

    void show_level(int level){
        if(level==1) tv_level.setText("쉬움");
        else if(level==2) tv_level.setText("중간");
        else tv_level.setText("어려움");
    }
}