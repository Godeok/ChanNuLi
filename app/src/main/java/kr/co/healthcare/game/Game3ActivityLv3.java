package kr.co.healthcare.game;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Random;

import kr.co.healthcare.R;

public class Game3ActivityLv3 extends AppCompatActivity {

    static int max_attempt = 10;
    static int attempt_cnt=0;
    static int first=0, second=0,       //카드에 적힌 숫자
            cardNum1=0, cardNum2=0;    //1-24 카드 중 어떤 카드인지

    Animation rotate_180;
    Animation scale_bigger;
    Animation scale_smaller;

    TextView tv_count2;
    TextView[] cards = new TextView[24];
    Integer[] Rid_tv_cards = {
            R.id.card1, R.id.card2, R.id.card3, R.id.card4, R.id.card5, R.id.card6, R.id.card7, R.id.card8,
            R.id.card9, R.id.card10, R.id.card11, R.id.card12, R.id.card13, R.id.card14, R.id.card15, R.id.card16,
            R.id.card17, R.id.card18, R.id.card19, R.id.card20, R.id.card21, R.id.card22, R.id.card23, R.id.card24,
    };
    int[] check_card = new int[24];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3_lv3);

        tv_count2 = findViewById(R.id.tv_count2);
        rotate_180 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_180);
        scale_bigger = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale_bigger);
        scale_smaller = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale_smaller);

        //카드 id 연결 + 터치 잠금
        for (int i=0; i<24; i++) {
            cards[i] = findViewById(Rid_tv_cards[i]);
            cards[i].setEnabled(false);
        }

        //1부터 24까지 랜덤으로 정렬
        int[] randomNum = new int[24];
        Random r = new Random();
        for(int i=0; i<24; i++){
            randomNum[i] = r.nextInt(24);
            for(int j=0; j<i; j++)
                if (randomNum[i] == randomNum[j])
                    i--;
        }

        //버튼에 숫자 입력
        int cnt=0;
        for (int i=1; i<=12; i++){
            cards[randomNum[cnt++]].setText(""+i);
            cards[randomNum[cnt++]].setText(""+i);
        }

        first = second = cardNum1 = cardNum2 = 0;

        //3초 카운트다운
       new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int color_green = ContextCompat.getColor(getApplicationContext(), R.color.colorGreen);
                for (int i=0; i<24; i++) {
                    cards[i].setTextColor(color_green);
                    cards[i].setBackgroundColor(color_green);
                    cards[i].setEnabled(true);
                }
            }
        }, 3000);

        //실행
        for(int i=0; i<24; i++){
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
                attempt_cnt = 0;
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
        int color_white = ContextCompat.getColor(getApplicationContext(), R.color.whiteColor);

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
        cardNum1 = i;
        first = Integer.parseInt(cards[cardNum1].getText().toString());
        cards[i].setEnabled(false);
        change_to_checked(cardNum1);
        cards[i].startAnimation(scale_bigger);
    }

    void if_second(int i){
        cardNum2 = i;
        second = Integer.parseInt(cards[cardNum2].getText().toString());
        change_to_checked(cardNum2);
        cards[i].startAnimation(scale_bigger);

        for (int j=0; j<24; j++)
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
        if(first==second && cardNum1!=cardNum2){
            int color_white = ContextCompat.getColor(getApplicationContext(), R.color.whiteColor);
            cards[cardNum1].setBackgroundColor(color_white);
            cards[cardNum2].setBackgroundColor(color_white);
            check_card[cardNum1] = check_card[cardNum2] = 1;

            cards[cardNum1].setEnabled(false);
            cards[cardNum2].setEnabled(false);
        }

        //카드 다르면
        else{
            int color_green = ContextCompat.getColor(getApplicationContext(), R.color.colorGreen);
            cards[cardNum1].setBackgroundColor(color_green);
            cards[cardNum1].setTextColor(color_green);

            cards[cardNum2].setBackgroundColor(color_green);
            cards[cardNum2].setTextColor(color_green);

            //////
            //cards[cardNum1].startAnimation(scale_smaller);
            //cards[cardNum2].startAnimation(scale_smaller);

            attempt_cnt++;
            tv_count2.setText(max_attempt-attempt_cnt+"회");
        }

        first = second = cardNum1 = cardNum2 = 0;

        check_game_over();
    }

    void check_game_over(){
        //게임 끝(lose)
        if(max_attempt==attempt_cnt){
            Intent intent = new Intent(getApplicationContext(), Game3ResultActivity.class);
            intent.putExtra("score", 0);
            intent.putExtra("level", 3);
            startActivity(intent);
        }

        //게임 끝(win)
        for(int i=0; i<24; i++){
            if(check_card[i]!=1)
                break;
            if(i==23) {
                Intent intent = new Intent(getApplicationContext(), Game3ResultActivity.class);
                intent.putExtra("score", max_attempt-attempt_cnt);
                intent.putExtra("level", 3);
                startActivity(intent);
            }
        }

        for (int j=0; j<24; j++)
            cards[j].setEnabled(true);
    }

    void card_touched(int i){
        if(first==0) if_first(i);
        else if(second==0) if_second(i);
    }
}