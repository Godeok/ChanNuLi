package kr.co.healthcare.game;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import kr.co.healthcare.R;


public class Game2Activity extends AppCompatActivity{

    TextView level_tv;
    ImageButton user_btn1;
    ImageButton user_btn2;

    static int score=0;
    static int cnt=0;
    int level;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        //level_tv = findViewById(R.id.level_tv);
        //level = getIntent().getIntExtra("level", -1);
        //show_level(level);

        loadActivity();
    }

    @Override
    public void onBackPressed() {
        // AlertDialog 빌더를 이용해 종료시 발생시킬 창을 띄운다
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
        alBuilder.setMessage("종료 시 점수가 저장되지 않습니다.");

        alBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cnt=0;
                score=0;
                //게임이 실행되던 액티비티 종료
                finish();
            }
        });

        alBuilder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });

        alBuilder.setTitle("프로그램 종료");
        alBuilder.show(); //AlertDialog.Bulider로 만든 AlertDialog 보여줌
    }


    private void loadActivity(){

        cnt++;

    }



    //레벨 표시
    void show_level(int level){
        //if(level==1) level_tv.setText("쉬움");
        //else if(level==2) level_tv.setText("중간");
        //else level_tv.setText("어려움");
    }


    //다음 단계로 넘어가는 메소드
    void next_lv(){
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable()  {
            public void run() {
                //게임 반복 횟수가 다 안 찼을 경우
                if(cnt<3){
                    Intent intent = new Intent(getApplicationContext(), Game2Activity.class);
                    startActivity(intent);
                }
                else{
                    int level = getIntent().getIntExtra("level", -1);
                    int score2 = score;
                    score=0;
                    cnt=0;

                    Intent intent = new Intent(getApplicationContext(), Game2ResultActivity.class);
                    intent.putExtra("score", score2);
                    intent.putExtra("level", level);
                    startActivity(intent);
                }
                //화면 전환 효과 없애기
                overridePendingTransition(0, 0);
            }
        }, 1000); // 1초후


    }
}