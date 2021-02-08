package kr.co.healthcare.game;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import kr.co.healthcare.R;


public class Game2Activity extends AppCompatActivity{

    TextView level_tv;
    TextView score_tv;
    TextView question_tv;
    TextView answer_tv;
    TextView result_tv;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;

    int[] opt = new int[4];

    static int score=0;
    static int cnt=0;
    static int checked=0;
    static int a=0;           //정답 저장
    int level;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        level_tv = findViewById(R.id.level_tv);
        level = getIntent().getIntExtra("level", -1);
        show_level(level);

        cnt++;

        score_tv = findViewById(R.id.score_tv);
        question_tv = findViewById(R.id.question_tv);
        answer_tv = findViewById(R.id.answer_tv);
        result_tv = findViewById(R.id.result_tv);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);

        //첫 화면 설정
        score_tv.setText(score+"점");


        //레벨 설정
        final int num = lv1_1();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked=1;
                answer_tv.setText(""+opt[0]);
                check_answer(num);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked=2;
                answer_tv.setText(""+opt[1]);
                check_answer(num);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked=3;
                answer_tv.setText(""+opt[2]);
                check_answer(num);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked=4;
                answer_tv.setText(""+opt[3]);
                check_answer(num);
            }
        });
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



    int lv1_1(){
        //한자리 + 한자리
        int q1 = (int)(Math.random()*10);
        int q2 = (int)(Math.random()*10);
        a = q1+q2;

        question_tv.setText(q1+"+"+q2);

        int num = fill_opt(a);
        return num;
    }

    int lv1_2(){
        //두자리 +- 한자리
        int q1 = (int)(Math.random()*10);
        int q2 = (int)(Math.random()*10);
        a = q1+q2;

        question_tv.setText(q1+"+"+q2);

        int num = fill_opt(a);
        return num;
    }


    //정답 외 보기 채우는 함수
    int fill_opt(int answer){
        int rand1, rand2;

        //보기에 들어갈 수 저장
        for(int i=0; i<4; i++){
            rand1 = (int)(Math.random()*5)+1;
            rand2 = (int)(Math.random()*2);

            if(rand2==0) opt[i] = answer+rand1;
            else opt[i] = answer-rand1;

            for(int j=0; j<i; j++)
                if(opt[i]==opt[j])
                    i--;
        }

        btn1.setText(""+opt[0]);
        btn2.setText(""+opt[1]);
        btn3.setText(""+opt[2]);
        btn4.setText(""+opt[3]);

        int num = fill_answer(answer);
        return num;
    }

    //보기에 정답을 쓰고 보기 번호 반환
    int fill_answer(int answer){
        int num = (int)(Math.random()*4)+1;
        if(num==1){
            btn1.setText(""+answer);
            return 1;
        }
        else if(num==2){
            btn2.setText(""+answer);
            return 2;
        }

        else if(num==3){
            btn3.setText(""+answer);
            return 3;
        }
        else{
            btn4.setText(""+answer);
            return 4;
        }
    }

    //버튼 연결
    void button(){

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked=1;
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked=2;
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked=3;
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked=4;
            }
        });
    }

    //답 확인
    void check_answer(int num){
        if (num==checked){
            result_tv.setText("정답입니다");
            answer_tv.setText(""+a);
            score += 200;
        }
        else {
            result_tv.setText("틀렸습니다");
            answer_tv.setTextColor(Color.parseColor("#FF0000"));
        }

        next_lv();
    }


    //레벨 표시
    void show_level(int level){
        if(level==1) level_tv.setText("쉬움");
        else if(level==2) level_tv.setText("중간");
        else level_tv.setText("어려움");
    }

    //다음 단계로 넘어가는 메소드
    void next_lv(){
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable()  {
            public void run() {
                //게임 반복 횟수가 다 안 찼을 경우
                if(cnt<3){
                    Intent intent = new Intent(getApplicationContext(), Game2Activity.class);
                    intent.putExtra("level", level);
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