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

public class GameResultActivity extends AppCompatActivity {

    TextView tv_level;
    TextView tv_score;
    TextView tv_showBest;
    Button btn_restart;
    Button btn_end;

    //최고기록 저장용 shared preferences
    SharedPreferences game_score_pref;
    SharedPreferences.Editor editor;

    int best_score_game1_lv1, best_score_game1_lv2, best_score_game1_lv3;
    int best_score_game2_lv1, best_score_game2_lv2, best_score_game2_lv3;
    int best_score_game3_lv1, best_score_game3_lv2, best_score_game3_lv3;
    int score, level, game, best_record;
    String best_record_str;

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
        game_score_pref = getSharedPreferences("game_score_pref", Activity.MODE_PRIVATE);
        editor = game_score_pref.edit();


        //이전 액티비티에서 점수와 레벨 받아오기
        score = getIntent().getIntExtra("score", -1);
        level = getIntent().getIntExtra("level", -1);
        game = getIntent().getIntExtra("game", -1);
        show_level(level);
        save_score();


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

    void save_score(){
        best_record_str = "best_score_game"+game+"_lv"+level;
        best_record = game_score_pref.getInt(best_record_str, 0);

        if(score > best_record){
            best_record = score;
            editor.putInt(best_record_str, best_record);
            editor.apply(); //저장
        }
;
        tv_showBest.setText(best_record +"점");
        tv_score.setText(score+"점");
    }

    void show_level(int level){
        if(level==1) tv_level.setText("쉬움");
        else if(level==2) tv_level.setText("중간");
        else tv_level.setText("어려움");
    }
}