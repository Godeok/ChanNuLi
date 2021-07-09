package kr.co.healthcare.game;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

import kr.co.healthcare.R;

public class Game3Activity extends AppCompatActivity {

    int level;
    int number_of_cards;

    static int MAX_CARDS = 24;
    static int MAX_ATTEMPT = 10;
    static int ATTEMPT_CNT =0;
    static int FIRST=0, SECOND=0,       //카드에 적힌 숫자
            CARD_NUM_1=0, CARD_NUM_2=0;    //1-16 카드 중 어떤 카드인지

    Animation rotate_180;
    Animation scale_bigger;
    Animation scale_smaller;

    TextView tv_count2;
    TextView[] cards = new TextView[MAX_CARDS];
    Integer[] Rid_tv_cards = {
            R.id.card1, R.id.card2, R.id.card3, R.id.card4, R.id.card5, R.id.card6, R.id.card7, R.id.card8,
            R.id.card9, R.id.card10, R.id.card11, R.id.card12, R.id.card13, R.id.card14, R.id.card15, R.id.card16,
            R.id.card17, R.id.card18, R.id.card19, R.id.card20, R.id.card21, R.id.card22, R.id.card23, R.id.card24
    };
    int[] check_card = new int[MAX_CARDS];
    LinearLayout layout_lv2, layout_lv3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);

        //id 연결
        tv_count2 = findViewById(R.id.tv_count2);
        rotate_180 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_180);
        scale_bigger = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale_bigger);
        scale_smaller = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale_smaller);
        layout_lv2 = findViewById(R.id.layout_lv2);
        layout_lv3 = findViewById(R.id.layout_lv3);


        //레벨 설정
        level = getIntent().getIntExtra("level", -1);
        if(level==1)
            number_of_cards = 16;
        else if(level==2){
            number_of_cards = 20;
            layout_lv2.setVisibility(View.VISIBLE);     //숨긴 카드 보이게 하기
        }
        else if(level==3) {
            number_of_cards = 24;
            layout_lv2.setVisibility(View.VISIBLE);
            layout_lv3.setVisibility(View.VISIBLE);
        }
        else number_of_cards = -1;


        //점수 설정



        //카드 id 연결 + 터치 잠금
        for (int i=0; i<number_of_cards; i++) {
            cards[i] = findViewById(Rid_tv_cards[i]);
            cards[i].setEnabled(false);
        }

        //1부터 number_of_cards까지 랜덤으로 정렬
        int[] randomNum = new int[number_of_cards];
        Random r = new Random();
        for(int i=0; i<number_of_cards; i++){
            randomNum[i] = r.nextInt(number_of_cards);
            for(int j=0; j<i; j++)
                if (randomNum[i] == randomNum[j])
                    i--;
        }

        //버튼에 숫자 입력
        int cnt=0;
        for (int i=1; i<=number_of_cards/2; i++){
            cards[randomNum[cnt++]].setText(""+i);
            cards[randomNum[cnt++]].setText(""+i);
        }

        FIRST = SECOND = CARD_NUM_1 = CARD_NUM_2 = 0;

        //3초 카운트다운
       new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int color_green = ContextCompat.getColor(getApplicationContext(), R.color.colorGreen);
                for (int i=0; i<number_of_cards; i++) {
                    cards[i].setTextColor(color_green);
                    cards[i].setBackgroundColor(color_green);
                    cards[i].setEnabled(true);
                }
            }
        }, 3000);

        //실행
        for(int i=0; i<number_of_cards; i++){
            final int n=i;
            cards[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    card_touched(n);
                }
            });
        }
    }


    @Override
    public void onBackPressed() {
        // AlertDialog 빌더를 이용해 종료시 발생시킬 창을 띄운다
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
        alBuilder.setMessage("종료 시 점수가 저장되지 않습니다.");

        alBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ATTEMPT_CNT = 0;
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


    void change_to_checked(int num){
        int color_purple = ContextCompat.getColor(getApplicationContext(), R.color.colorPurple);
        int color_white = ContextCompat.getColor(getApplicationContext(), R.color.colorWhite);

        //카드 애니메이션
        /*
        ObjectAnimator animator = ObjectAnimator.ofFloat(cards[num], "rotationY", 0, 180);
        animator.setDuration(500);
        animator.start();
        */


        cards[num].setBackgroundColor(color_purple);
        cards[num].setTextColor(color_white);
    }

    void if_first(int i){
        CARD_NUM_1 = i;
        FIRST = Integer.parseInt(cards[CARD_NUM_1].getText().toString());
        cards[i].setEnabled(false);
        change_to_checked(CARD_NUM_1);
        cards[i].startAnimation(scale_bigger);
    }

    void if_second(int i){
        CARD_NUM_2 = i;
        SECOND = Integer.parseInt(cards[CARD_NUM_2].getText().toString());
        change_to_checked(CARD_NUM_2);
        cards[i].startAnimation(scale_bigger);

        for (int j=0; j<number_of_cards; j++)
            cards[j].setEnabled(false);

        //결과 확인 전 지연
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                match_or_not();
            }
        }, 600);


    }

    void match_or_not(){
        //카드 같으면
        if(FIRST == SECOND && CARD_NUM_1 != CARD_NUM_2){
            int color_white = ContextCompat.getColor(getApplicationContext(), R.color.colorWhite);
            cards[CARD_NUM_1].setBackgroundColor(color_white);
            cards[CARD_NUM_2].setBackgroundColor(color_white);
            check_card[CARD_NUM_1] = check_card[CARD_NUM_2] = 1;

            cards[CARD_NUM_1].setEnabled(false);
            cards[CARD_NUM_2].setEnabled(false);
        }

        //카드 다르면
        else{
            int color_green = ContextCompat.getColor(getApplicationContext(), R.color.colorGreen);
            cards[CARD_NUM_1].setBackgroundColor(color_green);
            cards[CARD_NUM_1].setTextColor(color_green);

            cards[CARD_NUM_2].setBackgroundColor(color_green);
            cards[CARD_NUM_2].setTextColor(color_green);

            //////
            //cards[cardNum1].startAnimation(scale_smaller);
            //cards[cardNum2].startAnimation(scale_smaller);

            ATTEMPT_CNT++;
            tv_count2.setText(MAX_ATTEMPT - ATTEMPT_CNT +"회");
        }

        FIRST = SECOND = CARD_NUM_1 = CARD_NUM_2 = 0;

        check_game_over();
    }

    void check_game_over(){
        //게임 끝(lose)
        if(MAX_ATTEMPT == ATTEMPT_CNT){
            Intent intent = new Intent(getApplicationContext(), Game3ResultActivity.class);
            intent.putExtra("score", 0);
            intent.putExtra("level", level);
            startActivity(intent);
        }

        //게임 끝(win)
        for(int i=0; i<number_of_cards; i++){
            if(check_card[i]!=1)
                break;
            if(i==number_of_cards-1) {
                Intent intent;
                if(level==3) {
                    intent = new Intent(getApplicationContext(), Game3ResultActivity.class);
                    intent.putExtra("level", level);
                }
                else {
                    intent = new Intent(getApplicationContext(), Game3Activity.class);
                    intent.putExtra("level", level + 1);
                }

                intent.putExtra("score", MAX_ATTEMPT - ATTEMPT_CNT);
                startActivity(intent);
            }
        }

        for (int j=0; j<number_of_cards; j++)
            cards[j].setEnabled(true);
    }

    void card_touched(int i){
        if(FIRST ==0) if_first(i);
        else if(SECOND ==0) if_second(i);
    }
}