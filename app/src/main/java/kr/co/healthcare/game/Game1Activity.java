package kr.co.healthcare.game;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import kr.co.healthcare.R;


public class Game1Activity extends AppCompatActivity{

    TextView tv_level, tv_score;;
    ImageButton ib_user1, ib_user2;
    ImageView iv_com1, iv_com2;
    ImageView iv_character, iv_background;

    static int SCORE = 0;
    static int CNT = 0;
    static int GAME_ROUND = 5;
    static int LEVEL_EASY = 1, LEVEL_NORMAL = 3, LEVEL_HARD = 5;

    //시도 횟수 TextView 저장하는 배열
    TextView[] tv_try = new TextView[5];
    int[] tv_try_rid = {
            R.id.tv_try1, R.id.tv_try2, R.id.tv_try3, R.id.tv_try4, R.id.tv_try5
    };
    //승패 여부 저장하는 배열 (intent 전달용)
    int[] try_result = new int[5];

    int rand1_com, rand2_com, rand3_user, rand4_user;     //컴퓨터와 사용자의 가위바위보 결과
    int level;
    Animation animTransRight;
    Animation animTransLeft;
    Animation animAlpha;
    Animation animScaleSmallerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);

        tv_level = findViewById(R.id.tv_level);
        iv_character = findViewById(R.id.iv_character);
        iv_background = findViewById(R.id.iv_background);

        level = getIntent().getIntExtra("level", -1);
        try_result = getIntent().getIntArrayExtra("try_result");
        setting_by_level(level);

        loadActivity();
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
        alBuilder.setMessage("종료 시 점수가 저장되지 않습니다.");

        alBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //변수 초기화 및 액티비티 종료
                CNT = 0;
                SCORE = 0;
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


    //게임 실행 함수
    private void loadActivity(){
        //게임 실행 횟수(라운드)
        CNT++;

        //레이아웃 R id 연결
        ib_user1 = findViewById(R.id.ib_user1);
        ib_user2 = findViewById(R.id.ib_user2);
        iv_com1 = findViewById(R.id.iv_com1);
        iv_com2 = findViewById(R.id.iv_com2);
        tv_score = findViewById(R.id.tv_score);

        for(int i=0; i<5; i++)
            tv_try[i] = findViewById(tv_try_rid[i]);

        //애니메이션 R id 연결
        animTransRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_game1_trans_right);
        animTransLeft = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_game1_trans_left);
        animAlpha = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_game1_alpha);
        animScaleSmallerBtn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_game1_scale_smaller);


        //가위, 바위, 보 랜덤함수로 결정 (순서대로 컴퓨터, 사용자)
        rand1_com = (int)(Math.random()*3);
        rand3_user = (int)(Math.random()*3);

        //가위, 바위, 보 중복 제거 (순서대로 컴퓨터, 사용자)
        int rand2_temp = (int)(Math.random()*3);
        while (rand1_com ==rand2_temp)
            rand2_temp = (int)(Math.random()*3);
        rand2_com = rand2_temp;

        int rand4_temp = (int)(Math.random()*3);
        while (rand3_user ==rand4_temp)
            rand4_temp = (int)(Math.random()*3);
        rand4_user = rand4_temp;


        //첫 화면 설정
        set_rsp_image_com(rand1_com, iv_com1);
        set_rsp_image_com(rand2_com, iv_com2);
        set_rsp_image_user(rand3_user, ib_user1);
        set_rsp_image_user(rand4_user, ib_user2);
        tv_score.setText(SCORE +"점");
        set_tv_try();


        //첫 번째 이미지 버튼을 눌렀을 경우
        ib_user1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //레벨 확인 후 난이도 결정
                check_lv(level, rand3_user);

                //버튼 비활성화
                gray_image(ib_user2);
                ib_user1.setClickable(false);
                ib_user2.setClickable(false);
                ib_user2.startAnimation(animScaleSmallerBtn);

                next_lv();
            }
        });

        //두 번째 이미지 버튼을 눌렀을 경우
        ib_user2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //레벨 확인 후 난이도 결정
                check_lv(level, rand4_user);

                //버튼 비활성화
                gray_image(ib_user1);
                ib_user1.setClickable(false);
                ib_user2.setClickable(false);
                ib_user1.startAnimation(animScaleSmallerBtn);

                next_lv();
            }
        });
    }

    //레벨 확인 후 난이도 결정
    void check_lv(int level, int button_num){
        int random = (int)(Math.random()*10);
        int p;
        if(level==1){
            //컴퓨터가 이길 경우를 선택하는 경우
            if(random<LEVEL_EASY)
                p = set_rsp_smart(rand1_com, rand2_com, rand3_user, rand4_user);
            //컴퓨터가 랜덤 확률로 선택하는 경우
            else
                p = set_rsp_random(rand1_com, rand2_com);
        }

        else if(level==2){
            if(random<LEVEL_NORMAL)
                p = set_rsp_smart(rand1_com, rand2_com, rand3_user, rand4_user);
            else
                p = set_rsp_random(rand1_com, rand2_com);
        }

        else{
            if(random<LEVEL_HARD)
                p = set_rsp_smart(rand1_com, rand2_com, rand3_user, rand4_user);
            else
                p = set_rsp_random(rand1_com, rand2_com);
        }

        check_result(button_num, p);
    }


    //레벨에 따른 설정 (레벨 textView, 컴퓨터 캐릭터, 배경색)
    void setting_by_level(int level){
        if(level==1){
            tv_level.setText("쉬움");
            iv_character.setImageResource(R.drawable.img_game1_lv1_person);
            iv_background.setImageResource(R.drawable.img_game1_background_lv1);
        }
        else if(level==2){
            tv_level.setText("중간");
            iv_character.setImageResource(R.drawable.img_game1_lv2_person);
            iv_background.setImageResource(R.drawable.img_game1_background_lv2);
        }
        else{
            tv_level.setText("어려움");
            iv_character.setImageResource(R.drawable.img_game1_lv3_person);
            iv_background.setImageResource(R.drawable.img_game1_background_lv3);
        }
    }


    //이미지에 회색 필터 씌우는 메소드
    void gray_image(ImageView img){
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);

        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        img.setColorFilter(filter);
    }


    //가위바위보 이미지 바꾸는 메소드
    void set_rsp_image_com(int index, ImageView img) {
        //index: 난수, img: 바꿀 이미지 위치
        if (index == 0)
            img.setImageResource(R.drawable.img_scissor);
        else if (index == 1)
            img.setImageResource(R.drawable.img_rock);
        else
            img.setImageResource(R.drawable.img_paper);
    }


    //버튼의 가위바위보 이미지 바꾸는 메소드
    void set_rsp_image_user(int index, ImageButton imgbtn){
        //index: 난수, img: 바꿀 이미지 위치
        if(index==0)
            imgbtn.setImageResource(R.drawable.img_scissor);
        else if(index==1)
            imgbtn.setImageResource(R.drawable.img_rock);
        else
            imgbtn.setImageResource(R.drawable.img_paper);
    }

    //승패 여부 색 결정
    void set_tv_try(){
        for(int i=0; i<5; i++){
            if(try_result[i]==1)
                tv_try[i].setBackground(ContextCompat.getDrawable(this, R.drawable.view_game_progress_win));
            else if(try_result[i]==2)
                tv_try[i].setBackground(ContextCompat.getDrawable(this, R.drawable.view_game_progress_lose));
            else if(try_result[i]==3)
                tv_try[i].setBackground(ContextCompat.getDrawable(this, R.drawable.view_game_progress_draw));
            else;
        }
    }

    //게임 결과 확인하는 메소드
    void check_result(int user, int com){
        if ((user==0 && com==0) || (user==1 && com==1) || (user==2 && com==2)){
            SCORE +=100;
            tv_score.setText(SCORE +"점");

            //승패 여부 색 setting
            tv_try[CNT-1].setBackground(ContextCompat.getDrawable(this, R.drawable.view_game_progress_draw));
            //승패 여부 배열에 저장
            try_result[CNT-1] = 3;
        }

        else if ((user==0 && com==2) || (user==1 && com==0) || (user==2 && com==1)) {
            SCORE +=200;
            tv_score.setText(SCORE +"점");

            tv_try[CNT-1].setBackground(ContextCompat.getDrawable(this, R.drawable.view_game_progress_win));
            try_result[CNT-1] = 1;
        }

        else {
            tv_try[CNT-1].setBackground(ContextCompat.getDrawable(this, R.drawable.view_game_progress_lose));
            try_result[CNT-1] = 2;
        }
    }


    //컴퓨터가 랜덤으로 작동하는 메소드
    int set_rsp_random(int rand1, int rand2){
        int randNum = (int) (Math.random() * 2);
        if (randNum == 0) {
            //애니메이션
            iv_com1.startAnimation(animTransRight);
            iv_com2.startAnimation(animAlpha);

            //컴퓨터가 선택할 값 반환
            return rand1;
        } else {
            iv_com2.startAnimation(animTransLeft);
            iv_com1.startAnimation(animAlpha);
            return rand2;
        }
    }

    //컴퓨터가 이길 확률을 높이는 쪽으로 작동하는 메소드
    int set_rsp_smart(int com1, int com2, int user1, int user2){
        //컴퓨터의 선택에 따른 이길 확률 계산
        int s1 = compare_rsp(com1, user1, user2);
        int s2 = compare_rsp(com2, user1, user2);

        if (s1>s2){
            iv_com1.startAnimation(animTransRight);
            iv_com2.startAnimation(animAlpha);
            return com1;
        }
        else{
            iv_com2.startAnimation(animTransLeft);
            iv_com1.startAnimation(animAlpha);
            return com2;
        }
    }

    //가위바위보 a가 승/무:1, 승/패:0, 무/패:-1
    int compare_rsp(int a, int b, int c){
        if ((a==0 && b==0)||(a==1 && b==1)||(a==2 && b==2)){        //무승부
            if ((a==0 && c==2)||(a==1 && c==0)||(a==2 && c==1))     //승
                return 1;
            else return -1;                                         //패
        }

        else if ((a==0 && b==2)||(a==1 && b==0)||(a==2 && b==1)){   //승
            if ((a==0 && c==0)||(a==1 && c==1)||(a==2 && c==2))     //무
                return 1;
            else return 0;                                          //패
        }

        else{                                                       //패
            if ((a==0 && c==2)||(a==1 && c==0)||(a==2 && c==1))     //승
                return 0;
            else return -1;                                         //무승부
        }
    }

    //다음 단계로 넘어가는 메소드
    void next_lv(){
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable()  {
            public void run() {
                //게임 반복 횟수가 다 안 찼을 경우
                if(CNT <GAME_ROUND){
                    Intent intent = new Intent(getApplicationContext(), Game1Activity.class);
                    intent.putExtra("level", level);
                    intent.putExtra("game", 1);
                    intent.putExtra("try_result", try_result);
                    startActivity(intent);
                }
                //게임이 끝났을 경우
                else{
                    int level = getIntent().getIntExtra("level", -1);
                    int score2 = SCORE;
                    SCORE = 0;
                    CNT = 0;

                    Intent intent = new Intent(getApplicationContext(), GameResultActivity.class);
                    intent.putExtra("score", score2);
                    intent.putExtra("level", level);
                    intent.putExtra("game", 1);
                    startActivity(intent);
                }
                //화면 전환 효과 없애기
                overridePendingTransition(0, 0);
            }
        }, 1500); // 1.5초후
    }
}