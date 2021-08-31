package kr.co.healthcare.game;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.Random;

import kr.co.healthcare.R;


public class Game2Activity extends AppCompatActivity{

    TextView tv_level;
    TextView tv_score;
    TextView tv_question;
    ProgressBar progressBar;

    Button[] btn_opt = new Button[4];
    int[] Rid_btn_opt = {R.id.btn_opt1, R.id.btn_opt2, R.id.btn_opt3, R.id.btn_opt4};
    int[] option_data = new int[4];

    //점수, 게임 라운드, 사용자가 누른 번호, 레벨, 정답 버튼의 번호
    static int SCORE = 0, CNT = 0, CHECKED, LEVEL, ANS_NUM;
    static int CALCULATION_RESULT;                           //연산 결과
    static int STEP1 = 5, STEP2 = 9;                                //레벨 내 문제유형 반복 횟수 (4, 7)
    static boolean OPERATOR = false;
    static String TOTAL_TIME = "0101";                          //타이머 돌릴 시간(분-- 초--)

    CountDownTimer CDT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        tv_level = findViewById(R.id.tv_level);
        LEVEL = getIntent().getIntExtra("level", -1);

        tv_score = findViewById(R.id.tv_score);
        tv_question = findViewById(R.id.tv_question);
        progressBar = findViewById(R.id.progressBar);
        for(int i=0; i<4; i++)
            btn_opt[i] = findViewById(Rid_btn_opt[i]);

        //타이머
        countDown(TOTAL_TIME);

        initialized();

        for(int i=0; i<4; i++) {
            int finalI = i;
            btn_opt[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    check_answer(finalI);
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        // AlertDialog 빌더를 이용해 종료시 발생시킬 창을 띄운다
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this, R.style.AlertDialog);
        alBuilder.setMessage("종료 시 점수가 저장되지 않습니다.");

        alBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                initialize_data();

                //타이머 종료
                CDT.cancel();

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


    //게임 화면 초기화
    void initialized(){
        CNT++;
        tv_score.setText(SCORE +"점");
        set_textView(tv_question, "", 70, R.color.whiteColor);

        for(int i=0; i<4; i++)
            set_button(btn_opt[i], R.drawable.btn_game2, R.color.midDarkTextColor);

        show_level(LEVEL);
        start_game(LEVEL);
    }

    //정보 초기화
    void initialize_data(){
        CNT = 0;
        SCORE = 0;
        TOTAL_TIME = "0101";
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
        CDT = new CountDownTimer(conversionTime, 1000) {
            //타이머에 보이는 시간 변경
            public void onTick(long millisUntilFinished){
                //분단위
                long getMin = millisUntilFinished - (millisUntilFinished/(60 * 60 * 1000)) ;
                String min = String.valueOf(getMin/(60 * 1000)); // 몫

                //초단위
                String second = String.valueOf((getMin % (60 * 1000)) / 1000); //나머지

                //밀리세컨드 단위
                String millis = String.valueOf((getMin % (60 * 1000)) % 1000); //몫

                //타이머 bar
                if(Integer.parseInt(min)==1) progressBar.setProgress(60);
                else progressBar.setProgress(Integer.parseInt(second));


                //숫자가 한 자리면 앞에 0을 붙임
                if (min.length()==1) min = "0" + min;
                if (second.length() == 1) second = "0" + second;

                //1분 대신 60초로 표현
                if (min.equals("01")) second = "60";

                //tv_timer.setText(min + ":" + second);     //분:초 타이머
                //tv_timer.setText(second);                   //초 타이머
                TOTAL_TIME = min+second;
            }

            //제한시간 종료시
            public void onFinish() {
                for(int i=0; i<4; i++)
                    btn_opt[i].setEnabled(false);
                //tv_timer.setText("시간 종료!");
                after_time_over();
            }
        };
        CDT.start();
    }

    void after_time_over(){
        for(int i=0; i<4; i++)
            btn_opt[i].setEnabled(true);
        //tv_timer.setText("시간 종료!");

        //1초 지연 후 결과 페이지로 이동
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable()  {
            public void run() {
                int level = getIntent().getIntExtra("level", -1);

                Intent intent = new Intent(getApplicationContext(), GameResultActivity.class);
                intent.putExtra("score", SCORE);
                intent.putExtra("level", level);
                intent.putExtra("game", 2);  initialize_data();
                startActivity(intent);

                //화면 전환 효과 없애기
                overridePendingTransition(0, 0);
            }
        }, 1000); // 1초후
    }



    /* --- 연산 게임 관련 메소드 --- */

    //연산 게임용 숫자 설정하는 메소드 (자릿수, 자릿수, 연산자)
    void set_operation_numbers(int digit1, int digit2, String operator){
        Random rnd = new Random();
        int q1 = rnd.nextInt((int)Math.pow(10, digit1));
        int q2 = rnd.nextInt((int)Math.pow(10, digit2));
        while(q1<q2)
            q2 = rnd.nextInt((int)Math.pow(10, digit2));

        set_gamePage(q1, q2, operator);
    }

    //연산 게임용 숫자 설정하는 메소드 (최댓값1, 최솟값1, 최댓값2, 최솟값2, 연산자)
    void set_operation_numbers(int max1, int min1, int max2, int min2, String operator){
        Random rnd = new Random();
        int q1 = rnd.nextInt(max1-min1+1) + min1;
        int q2 = rnd.nextInt(max2-min2+1) + min2;

        set_gamePage(q1, q2, operator);
    }

    //연산 게임용 숫자 설정하는 메소드 (최댓값, 중간값, 최솟값, 연산자)
    void set_operation_numbers(int max, int mid, int min, String operator){
        set_operation_numbers(max, mid, mid, min, operator);
    }

    //할당된 숫자를 활용해 문제 출제, 화면 출력, 답 저장 등을 담당하는 메소드 (연산 게임용) (숫자1, 숫자2, String형 연산자)
    void set_gamePage(int q1, int q2, String operator){
        String ope = operator;
        Random rnd = new Random();

        if(operator.length() != 1){
            if(rnd.nextBoolean()) ope = operator.substring(0, 1);
            else ope = operator.substring(1);
        }

        if(ope.equals("*")) ope="×";
        else if(ope.equals("/")) ope="÷";

        CALCULATION_RESULT = calculate(q1, q2, ope);
        tv_question.setText(q1 + " " + ope + " " + q2);
    }



    /* --- 빈칸 채우기 게임 관련 메소드 --- */

    ////빈칸 채우기 게임용 숫자 설정하는 메소드 (최댓값1, 최솟값1, 최댓값2, 최솟값2, int형 연산자)
    void set_blank_numbers(int max1, int min1, int max2, int min2, int operator){
        //한자리 [ ] 한자리 = 답
        Random rnd = new Random();
        int ope = operator;

        //Random.nextInt( <큰수> - <작은수> + 1) + <작은수>;
        int q1 = rnd.nextInt(max1-min1+1)+min1;
        int q2 = rnd.nextInt(max2-min2+1)+min2;
        while(q2>q1) q2 = rnd.nextInt(max2-min2+1)+min2;

        //나눗셈이 불가능한 조건이라면 덧셈, 뺄셈, 곱셈 중 하나로 변경
        if(!(q2!=0 && q1%q2==0 && ope==3))
            ope = (int) (Math.random() * 3);

        set_gamePage(q1, q2, ope);
    }

    //빈칸 채우기 게임용 숫자 설정하는 메소드 (최댓값1, 최솟값1, 최댓값2, 최솟값2, String형 연산자)
    void set_blank_numbers(int max1, int min1, int max2, int min2, String operator){
        String ope = operator;
        Random rnd = new Random();

        if(operator.length() != 1){
            if(rnd.nextBoolean()) ope = operator.substring(0, 1);
            else ope = operator.substring(1);
        }

        set_blank_numbers(max1, min1, max2, min2, change_operator_StringToInt(ope));
    }

    //할당된 숫자를 활용해 문제 출제, 화면 출력, 답 저장 등을 담당하는 메소드 (연산 게임용) (숫자1, 숫자2, int형 연산자)
    void set_gamePage(int q1, int q2, int operator){
        String ope = change_operator_intToString(operator);

        if(ope.equals("*")) ope="×";
        else if(ope.equals("/")) ope="÷";

        CALCULATION_RESULT = calculate(q1, q2, ope);
        tv_question.setText(q1 + " □ " + q2 +"\n= "+ CALCULATION_RESULT);

        ANS_NUM = operator;
    }



    /* --- 계산 관련 메소드 --- */

    //숫자와 연산자를 입력받아 계산한 값을 반환하는 메소드 (숫자1, 숫자2, 연산자)
    int calculate(int num1, int num2, String operator){
        if(operator.equals("+")) return num1+num2;
        else if(operator.equals("-")) return num1-num2;
        else if(operator.equals("*") || operator.equals("×")) return num1*num2;
        else if(operator.equals("/") || operator.equals("÷")) return num1/num2;
        else return -1;
    }

    //int형 연산자(0, 1, 2, 3)를 String형 연산자(+, -, *, /)로
    String change_operator_intToString(int operator){
        switch (operator) {
            case 0:
                return "+";
            case 1:
                return "-";
            case 2:
                return "*";
            case 3:
                return "/";
            default:
                return "";
        }
    }

    //String형 연산자(+, -, *, /)를 int형 연산자(0, 1, 2, 3)로
    int change_operator_StringToInt(String operator){
        switch (operator) {
            case "+":
                return 0;
            case "-":
                return 1;
            case "*":
            case "×":
                return 2;
            case "/":
            case "÷":
                return 3;
            default:
                return -1;
        }
    }



    /* --- 레벨별 게임 함수 --- */
    void lv1_1(){
        //한자리 + 한자리
        OPERATOR = false;
        set_operation_numbers(1, 1, "+");
        fill_opt_num();
    }

    void lv1_2(){
        //두자리 +- 한자리
        OPERATOR = false;
        set_operation_numbers(2, 1, "+-");
        fill_opt_num();
    }

    void lv1_3(){
        //한자리 [ ] 한자리 = 답
        OPERATOR = true;
        int operator = (int)(Math.random()*4);     //숫자 순서대로 + - * /
        set_blank_numbers(20, 3, 10, 2, operator);
        fill_opt_op();
    }

    void lv2_1(){
        //두자리 + 두자리
        OPERATOR = false;
        set_operation_numbers(70, 30, 10, "+");
        fill_opt_num();
    }

    void lv2_2(){
        //세자리 - 한자리
        OPERATOR = false;
        set_operation_numbers(1000, 100, 10, 1, "-");
        fill_opt_num();
    }

    void lv2_3(){
        //두자리 [더하기,나누기] 두자리 = 답
        //세자리 [더하기,나누기] 한자리 = 답
        OPERATOR = true;
        Random rnd = new Random();

        //두자리_두자리
        if(rnd.nextBoolean())
            set_blank_numbers(70, 30, 30, 10, "+/");

        //세자리_한자리
        else
            set_blank_numbers(1000, 100, 10, 1, "+/");

        fill_opt_op();
    }

    void lv3_1(){
        //두자리 +- 한자리
        OPERATOR = false;
        set_operation_numbers(70, 30, 10, "+-");
        fill_opt_num();
    }

    void lv3_2(){
        //두자리 [곱하기, 나누기] 한자리 = 답
        OPERATOR = true;
        set_blank_numbers(70, 30, 10, 2, "*/");
        fill_opt_op();
    }

    void lv3_3(){
        //두자리 + 두자리
        OPERATOR = false;
        set_operation_numbers(100, 10, 1, "+");
        fill_opt_num();
    }



    //정답 외 보기(숫자) 채우는 함수
    void fill_opt_num(){
        Random rnd = new Random();
        int answer = CALCULATION_RESULT;

        //보기에 들어갈 수 저장
        for(int i=0; i<4; i++){
            int nearbyNum = (int)rnd.nextInt(5)+1;   //정답과 1~5 차이나는 수를 위한 난수

            if(rnd.nextBoolean()) option_data[i] = answer + nearbyNum;
            else option_data[i] = answer - nearbyNum;

            for(int j=0; j<i; j++)
                if(option_data[i]== option_data[j])
                    i--;
        }

        for(int i=0; i<4; i++)
            btn_opt[i].setText(""+ option_data[i]);;

        fill_answer(answer);
    }

    //보기에 정답을 쓰고 보기 번호 반환
    void fill_answer(int answer){
        ANS_NUM = (int)(Math.random()*4);
        btn_opt[ANS_NUM].setText(""+answer);
    }

    //정답 외 보기(연산자) 채우는 함수
    void fill_opt_op(){
        String[] ope = {"+", "-", "×", "÷"};
        for(int i=0; i<4; i++)
            btn_opt[i].setText(ope[i]);
    }

    void set_textView(TextView tv, String str, int size, int color){
        tv.setText(str);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
        tv.setTextColor(getResources().getColor(color));
    }

    void set_button(Button btn, int background, int textColor){
        btn.setBackground(getDrawable(background));
        btn.setTextColor(getResources().getColor(textColor));
    }



    //답 확인
    void check_answer(int number){
        CHECKED = number;
        if (ANS_NUM == CHECKED){
            set_textView(tv_question, "O", 120, R.color.whiteColor);
            SCORE += 200;
        }
        else {
            set_textView(tv_question, "X", 120, R.color.redColor);
            if(SCORE>0) SCORE -= 100;
        }
        tv_score.setText(SCORE +"점");
        OPERATOR = false;
        next_lv();
    }

    //레벨별 다른 함수를 실행
    void start_game(int level){
        int diif = mix_difficulty();
        if(level==1){
            if(diif==1) lv1_1();
            else if(diif==2) lv1_2();
            else lv1_3();
        }
        else if(level==2){
            if(diif==1) lv2_1();
            else if(diif==2) lv2_2();
            else lv2_3();
        }

        else{
            if(diif==1) lv3_1();
            else if(diif==2) lv3_2();
            else lv3_3();
        }
    }

    int mix_difficulty(){
        Random rnd = new Random();
        if(CNT<STEP1) return 1;
        else if(CNT<STEP2){
            if(rnd.nextBoolean()) return 1;
            else return 2;
        }
        else{
            if(rnd.nextInt(10) < 3) return 1;
            else
                if(rnd.nextBoolean()) return 2;
                else return 3;
        }
    }

    //레벨 표시
    void show_level(int level){
        if(level==1) tv_level.setText("쉬움");
        else if(level==2) tv_level.setText("중간");
        else tv_level.setText("어려움");
    }

    //다음 단계로 넘어가는 메소드
    void next_lv(){
        //버튼 비활성화
        for(int i=0; i<4; i++)
            btn_opt[i].setEnabled(false);

        //정오 여부에 따른 버튼 색 구분
        set_button(btn_opt[ANS_NUM], R.drawable.btn_game2_answer, R.color.whiteColor);
        if(ANS_NUM != CHECKED)
            set_button(btn_opt[CHECKED], R.drawable.btn_game2_selected, R.color.whiteColor);

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable()  {
            //시간이 끝나기 전까지 액티비티 반복
            public void run() {
                for(int i=0; i<4; i++)
                    btn_opt[i].setEnabled(true);

                initialized();
            }
        }, 1000); // 1초후
    }
}