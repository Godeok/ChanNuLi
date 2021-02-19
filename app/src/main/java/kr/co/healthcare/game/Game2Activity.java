package kr.co.healthcare.game;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import kr.co.healthcare.R;


public class Game2Activity extends AppCompatActivity{

    TextView level_tv;
    TextView score_tv;
    TextView question_tv;
    TextView answer_tv;
    TextView result_tv;
    TextView equal_tv;
    TextView timer_tv;
    Button btn1, btn2, btn3, btn4;

    int[] opt = new int[4];

    //점수, 게임 횟수, 사용자가 누른 번호, 정답 값, 정답 보기 번호
    static int score=0, cnt=0, checked=0, a=0, num=0;
    static boolean operator = false;
    static String total_time = "0010";       //타이머 돌릴 시간(분-- 초--)
    int level;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        level_tv = findViewById(R.id.level_tv);
        level = getIntent().getIntExtra("level", -1);
        show_level(level);

        //게임 진행 단계 count
        cnt++;

        score_tv = findViewById(R.id.score_tv);
        question_tv = findViewById(R.id.question_tv);
        answer_tv = findViewById(R.id.answer_tv);
        result_tv = findViewById(R.id.result_tv);
        equal_tv = findViewById(R.id.equal_tv);
        timer_tv = findViewById(R.id.timer_tv);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);

        //첫 화면 설정
        score_tv.setText(score+"점");
        equal_tv.setText("=");

        //타이머
        countDown(total_time);

        //레벨 설정
        num = start_game(level);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_method(1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_method(2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_method(3);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_method(4);
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
                total_time="0010";
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

        alBuilder.setTitle("게임 종료");
        alBuilder.show(); //AlertDialog.Bulider로 만든 AlertDialog 보여줌
    }

    //타이머
    public void countDown(String time) {

        long conversionTime = 0;
        //1000 단위가 1초, 60000 단위가 1분, 60000 * 3600 = 1시간

        String getMin = time.substring(0, 2);
        String getSecond = time.substring(2, 4);

        // "00"이 아니고, 첫번째 자리가 0 이면 제거
        if (getMin.substring(0, 1)=="0") getMin = getMin.substring(1, 2);
        if (getSecond.substring(0, 1)=="0") getSecond = getSecond.substring(1, 2);

        // 변환시간
        conversionTime = Long.valueOf(getMin)*60*1000 + Long.valueOf(getSecond)*1000;

        //첫번째 인자 : 원하는 시간 (예를들어 30초면 30 x 1000(주기))
        //두번째 인자 : 주기(1000 = 1초)
        new CountDownTimer(conversionTime, 1000) {
            //타이머에 보이는 시간 변경
            public void onTick(long millisUntilFinished) {
                //분단위
                long getMin = millisUntilFinished - (millisUntilFinished / (60 * 60 * 1000)) ;
                String min = String.valueOf(getMin / (60 * 1000)); // 몫

                //초단위
                String second = String.valueOf((getMin % (60 * 1000)) / 1000); // 나머지

                //밀리세컨드 단위
                String millis = String.valueOf((getMin % (60 * 1000)) % 1000); // 몫

                //숫자가 한 자리면 앞에 0을 붙임
                if (min.length()==1) min = "0" + min;
                if (second.length() == 1) second = "0" + second;

                timer_tv.setText(min + ":" + second);
                total_time = min+second;
                //total_time = String.valueOf(min) + String.valueOf(second);
            }

            //제한시간 종료시
            public void onFinish() {
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
                btn4.setEnabled(false);

                timer_tv.setText("시간 종료!");

                //1초 지연 후 결과 페이지로 이동
                Handler mHandler = new Handler();
                mHandler.postDelayed(new Runnable()  {
                    public void run() {
                        int level = getIntent().getIntExtra("level", -1);
                        int score2 = score;
                        score=0;
                        cnt=0;
                        total_time="0010";

                        Intent intent = new Intent(getApplicationContext(), Game2ResultActivity.class);
                        intent.putExtra("score", score2);
                        intent.putExtra("level", level);
                        startActivity(intent);

                        //화면 전환 효과 없애기
                        overridePendingTransition(0, 0);
                    }
                }, 1000); // 1초후
            }
        }.start();
    }


    //레벨별 게임 함수
    int lv1_1(){
        //한자리 + 한자리
        int q1 = (int)(Math.random()*10);
        int q2 = (int)(Math.random()*10);
        a = q1+q2;

        question_tv.setText(q1+"+"+q2);

        int num = fill_opt_num(a);
        return num; //정답 보기 번호
    }

    int lv1_2(){
        //두자리 +- 한자리
        Random rnd = new Random();
        //Random.nextInt( <큰수> - <작은수> + 1) + <작은수>;
        int q1 = rnd.nextInt(91)+10;
        int q2 = (int)(Math.random()*10);
        int rand = (int)(Math.random()*2);

        if(rand==0){
            question_tv.setText(q1+"+"+q2);
            a = q1+q2;
        }
        else{
            question_tv.setText(q1+"-"+q2);
            a = q1-q2;
        }

        int num = fill_opt_num(a);
        return num;
    }

    int lv1_3(){
        //한자리 [ ] 한자리 = 답
        operator = true;
        Random rnd = new Random();
        //Random.nextInt( <큰수> - <작은수> + 1) + <작은수>;
        int q1 = rnd.nextInt(18)+3;      //3~20
        int q2 = rnd.nextInt(9)+2;     //2~10
        while(q2>q1) q2 = rnd.nextInt(9)+2;
        int q3, number;
        int[] value;
        int rand = (int)(Math.random()*4);

        value = play_fill_blanks(q1, q2, rand);

        number = value[0];
        q3 = value[1];

        question_tv.setText(q1 + " □ " + q2 + " = " + q3);
        equal_tv.setText("");
        fill_opt_op();

        //정답의 보기 번호 반환
        return number;
    }

    int lv2_1(){
        //두자리 + 두자리
        Random rnd = new Random();
        //Random.nextInt( <큰수> - <작은수> + 1) + <작은수>;
        int q1 = rnd.nextInt(41)+30;    //30~70
        int q2 = rnd.nextInt(21)+10;    //10~30

        question_tv.setText(q1+"+"+q2);
        a = q1+q2;

        int num = fill_opt_num(a);
        return num;
    }

    int lv2_2(){
        //세자리 - 한자리
        Random rnd = new Random();
        //Random.nextInt( <큰수> - <작은수> + 1) + <작은수>;
        int q1 = rnd.nextInt(901)+100;      //100~1000
        int q2 = (int)(Math.random()*10 + 1);      //1~10

        question_tv.setText(q1+"-"+q2);
        a = q1-q2;

        int num = fill_opt_num(a);
        return num;
    }

    int lv2_3(){
        //두자리 [더하기,나누기] 두자리 = 답
        //세자리 [더하기,나누기] 한자리 = 답
        operator = true;
        Random rnd = new Random();
        int q1, q2, q3, number;
        int[] value;
        int rand = (int)(Math.random()*2);
        int rand2 = (int)(Math.random()*2);

        //두자리_두자리
        if(rand2==0){
            //Random.nextInt( <큰수> - <작은수> + 1) + <작은수>;
            q1 = rnd.nextInt(41)+30;    //30~70
            q2 = rnd.nextInt(21)+10;    //10~30
            while(q2>q1) q2 = rnd.nextInt(21)+10;
        }
        //세자리_한자리
        else{
            //Random.nextInt( <큰수> - <작은수> + 1) + <작은수>;
            q1 = rnd.nextInt(901)+100;      //100~1000
            q2 = (int)(Math.random()*10 + 1);      //1~10
            while(q2>q1) q2 = (int)(Math.random()*10 + 1);
        }

        value = play_fill_blanks_lv2(q1, q2, rand);

        number = value[0];
        q3 = value[1];

        question_tv.setText(q1 + " □ " + q2 + " = " + q3);
        equal_tv.setText("");
        fill_opt_op();

        //정답의 보기 번호 반환
        return number;
    }

    int lv3_1(){
        //두자리 +- 한자리
        Random rnd = new Random();
        //Random.nextInt( <큰수> - <작은수> + 1) + <작은수>;
        int q1 = rnd.nextInt(41)+30;    //30~70
        int q2 = rnd.nextInt(21)+10;    //10~30
        int rand = (int)(Math.random()*2);

        if(rand==0){
            question_tv.setText(q1+"+"+q2);
            a = q1+q2;
        }
        else{
            question_tv.setText(q1+"-"+q2);
            a = q1-q2;
        }

        int num = fill_opt_num(a);
        return num;
    }

    int lv3_2(){
        //두자리 [곱하기, 나누기] 한자리 = 답
        operator = true;
        Random rnd = new Random();
        int q1, q2, q3, number;

        //두자리_두자리
        //Random.nextInt( <큰수> - <작은수> + 1) + <작은수>;
        q1 = rnd.nextInt(41)+30;    //30~70
        q2 = rnd.nextInt(9)+2;     //2~10
        while(q2>q1) q2 = rnd.nextInt(21)+10;


        int[] value;
        int rand = (int)(Math.random()*2);

        value = play_fill_blanks(q1, q2, rand);

        number = value[0];
        q3 = value[1];

        question_tv.setText(q1 + " □ " + q2 + " = " + q3);
        equal_tv.setText("");
        fill_opt_op();

        //정답의 보기 번호 반환
        return number;
    }

    int lv3_3(){
        //두자리 + 두자리
        Random rnd = new Random();
        //Random.nextInt( <큰수> - <작은수> + 1) + <작은수>;
        int q1 = rnd.nextInt(91)+10;   //10~100
        int q2 = (int)(Math.random()*10);     //1~10

        question_tv.setText(q1+"x"+q2);
        a = q1*q2;

        int num = fill_opt_num(a);
        return num;
    }


    //빈칸 채우기 문제 내기
    int[] play_fill_blanks(int q1, int q2, int rand){
        int number=0, q3=-1;
        int[] return_value = new int[2];

        //나눗셈의 경우
        if(q2!=0) {
            if (q1 % q2 == 0) {
                if (rand == 3) {
                    q3 = q1 / q2;
                    number = 4;
                } else rand = (int) (Math.random() * 3);
            }
            else rand = (int) (Math.random() * 3);
        }
        else rand = (int) (Math.random() * 3);

        if(rand==0){
            q3 = q1 + q2;
            number=1;
        }

        else if(rand==1){
            q3 = q1 - q2;
            number=2;
        }

        else if(rand==2){
            q3 = q1 * q2;
            number=3;
        }

        return_value[0] = number;
        return_value[1] = q3;

        return return_value;
    }

    //빈칸 채우기 문제 내기
    int[] play_fill_blanks_lv2(int q1, int q2, int rand){
        int number=0, q3=-1;
        int[] return_value = new int[2];

        //나눗셈의 경우
        if(rand==0){
            q3 = q1 + q2;
            number=1;
        }

        else if(rand==1){
            q3 = q1 - q2;
            number=2;
        }

        return_value[0] = number;
        return_value[1] = q3;

        return return_value;
    }

    //정답 외 보기(숫자) 채우는 함수
    int fill_opt_num(int answer){
        int rand1, rand2;

        //보기에 들어갈 수 저장
        for(int i=0; i<4; i++){
            rand1 = (int)(Math.random()*5)+1;   //정답과 1~5 차이나는 수를 위한 난수
            rand2 = (int)(Math.random()*2);     //정답보다 작은 수 만들지 큰 수 만들지 결정하는 난수

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

        int number = fill_answer(answer);
        return number;
    }

    //정답 외 보기(연산자) 채우는 함수
    void fill_opt_op(){
        btn1.setText("+");
        btn2.setText("-");
        btn3.setText("×");
        btn4.setText("÷");
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

    //버튼 실행시
    void btn_method(int number){
        //사용자가 누른 번호(number) checked에 저장
        checked=number;

        //화면에 사용자가 누른 '보기' 보여주기
        if(operator==true){
            if(number==1) answer_tv.setText("+");
            else if(number==2) answer_tv.setText("-");
            else if(number==3) answer_tv.setText("×");
            else answer_tv.setText("÷");
        }
        else
            answer_tv.setText(""+opt[number-1]);

        check_answer();
    }

    //답 확인
    void check_answer(){
        if (num==checked){
            result_tv.setText("정답입니다");
            if(operator==false){
                answer_tv.setText(""+a);
            }
            answer_tv.setTextColor(Color.parseColor("#4CAF50"));
            score += 200;
            score_tv.setText(score+"점");
        }
        else {
            result_tv.setText("틀렸습니다");
            answer_tv.setTextColor(Color.parseColor("#FF0000"));
        }
        operator=false;
        next_lv();
    }

    //레벨별 다른 함수를 실행
    int start_game(int level){
        int number;
        //cnt 순서대로 4 7
        if(level==1){
            if(cnt<2) number=lv1_1();
            else if(cnt<3) number=lv1_2();
            else number=lv1_3();
        }
        else if(level==2){
            if(cnt<2) number=lv2_1();
            else if(cnt<3) number=lv2_2();
            else number=lv2_3();
        }
        else{
            if(cnt<2) number=lv3_1();
            else if(cnt<3) number=lv3_2();
            else number=lv3_3();
        }
        //정답 반환
        return number;
    }

    //레벨 표시
    void show_level(int level){
        if(level==1) level_tv.setText("쉬움");
        else if(level==2) level_tv.setText("중간");
        else level_tv.setText("어려움");
    }

    //다음 단계로 넘어가는 메소드
    void next_lv(){
        //버튼 비활성화
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable()  {
            public void run() {
                //시간이 끝나기 전까지 액티비티 반복
                Intent intent = new Intent(getApplicationContext(), Game2Activity.class);
                intent.putExtra("level", level);
                startActivity(intent);

                //화면 전환 효과 없애기
                overridePendingTransition(0, 0);
            }
        }, 1000); // 1초후
    }
}