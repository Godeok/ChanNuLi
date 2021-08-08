package kr.co.healthcare.game;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import kr.co.healthcare.R;

public class GameResultActivity extends AppCompatActivity {

    TextView tv_level;
    TextView tv_score;
    TextView tv_showBest;
    TextView tv_game_type;
    Button btn_restart;
    ImageButton btn_end;
    ImageView img_star_1, img_star_2, img_star_3;

    //최고기록 저장용 shared preferences
    SharedPreferences game_score_pref;
    SharedPreferences.Editor editor;

    int score, level, game, best_record;
    String best_record_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);

        tv_level = findViewById(R.id.tv_level);
        tv_score = findViewById(R.id.tv_score);
        tv_showBest = findViewById(R.id.tv_showBest);
        tv_game_type = findViewById(R.id.tv_game_type);
        btn_restart = findViewById(R.id.btn_restart);
        btn_end = findViewById(R.id.btn_end);
        img_star_1 = findViewById(R.id.img_star_1);
        img_star_2 = findViewById(R.id.img_star_2);
        img_star_3 = findViewById(R.id.img_star_3);


        //shared preference 초기화
        game_score_pref = getSharedPreferences("game_score_pref", Activity.MODE_PRIVATE);
        editor = game_score_pref.edit();


        //이전 액티비티에서 점수와 레벨 받아오기
        score = getIntent().getIntExtra("score", -1);
        level = getIntent().getIntExtra("level", -1);
        game = getIntent().getIntExtra("game", -1);
        //show_level(level)
        save_score();

        //게임 종류 보이기
        show_game_type();

        //레벨에 따른 별 보이기
        show_level_by_star();


        //다시하기
        btn_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Game1Activity.class);
                intent.putExtra("level", level);
                int[] try_result = {0, 0, 0, 0, 0};
                intent.putExtra("try_result", try_result);
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
        tv_showBest.setText(best_record +"");
        tv_score.setText(score+"");
    }

    void show_level_by_star(){
        if(level==1) {
            img_star_2.setColorFilter(getResources().getColor(R.color.primaryLightColor));
            img_star_3.setColorFilter(getResources().getColor(R.color.primaryLightColor));
        }
        else if(level==2) img_star_3.setColorFilter(getResources().getColor(R.color.primaryLightColor));
    }

    void show_game_type(){
        if(game==1) tv_game_type.setText("하나빼기");
        else if(game==2) tv_game_type.setText("간단 연산");
        else tv_game_type.setText("카드 뒤집기");
    }
}