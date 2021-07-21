package kr.co.healthcare.game;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

import kr.co.healthcare.R;

public class Game3Activity extends AppCompatActivity {

    static int FIRST_CARD_IMAGE = -1, SECOND_CARD_IMAGE = -1;
    static int FIRST_CARD_NUMBER = -1, SECOND_CARD_NUMBER = -1;
    static int ATTEMPT_CNT = 0, MAX_ATTEMPT = 100;

    int level, score=0, number_of_cards;
    LinearLayout layout_lv2, layout_lv3;
    TextView tv_level, tv_score, tv_count2;
    Animation scale_bigger;

    ImageView[] cards;
    int check_card[];
    int randomNum[], imageNum[];
    int card_rid[] = {
            R.id.card1, R.id.card2, R.id.card3, R.id.card4, R.id.card5, R.id.card6, R.id.card7, R.id.card8,
            R.id.card9, R.id.card10, R.id.card11, R.id.card12, R.id.card13, R.id.card14, R.id.card15, R.id.card16,
            R.id.card17, R.id.card18, R.id.card19, R.id.card20, R.id.card21, R.id.card22, R.id.card23, R.id.card24
    };
    int[] img_card_content = {
            R.drawable.img_card_content_01, R.drawable.img_card_content_02, R.drawable.img_card_content_03, R.drawable.img_card_content_04,
            R.drawable.img_card_content_05, R.drawable.img_card_content_06, R.drawable.img_card_content_07, R.drawable.img_card_content_08,
            R.drawable.img_card_content_09, R.drawable.img_card_content_10, R.drawable.img_card_content_11, R.drawable.img_card_content_12
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);

        tv_level = findViewById(R.id.tv_level);
        tv_score = findViewById(R.id.tv_score);
        tv_count2 = findViewById(R.id.tv_count2);
        layout_lv2 = findViewById(R.id.layout_lv2);
        layout_lv3 = findViewById(R.id.layout_lv3);
        scale_bigger = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale_bigger);


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

        score = getIntent().getIntExtra("score", 0);
        tv_score.setText(score+"점");



        randomNum = new int[number_of_cards];
        imageNum = new int[number_of_cards];
        cards = new ImageView[number_of_cards];
        check_card = new int[number_of_cards];

        //
        Random r = new Random();
        for(int i=0; i<number_of_cards; i++){
            randomNum[i] = r.nextInt(number_of_cards);
            for(int j=0; j<i; j++)
                if (randomNum[i] == randomNum[j])
                    i--;
        }

        for(int i=0; i<number_of_cards; i++){
            imageNum[i] = randomNum[i]/2;
        }

        for(int i=0; i<number_of_cards; i++){
            cards[i] = (ImageView)findViewById(card_rid[i]);
            cards[i].setImageResource(img_card_content[imageNum[i]]);
            cards[i].setEnabled(false);

            cards[i].setBackground(getDrawable(R.drawable.btn_game3_card));
            //cards[i].setClipToOutline(true);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int color_green = ContextCompat.getColor(getApplicationContext(), R.color.primaryColor);
                for (int i=0; i<number_of_cards; i++) {
                    cards[i].setImageResource(R.drawable.img_card_back);
                    cards[i].setEnabled(true);
                }
            }
        }, 2000+level*1000);

        //실행
        for(int i=0; i<number_of_cards; i++){
            final int N=i;
            cards[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    card_touched(N);
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

    void card_touched(int cardNumber){
        if(FIRST_CARD_IMAGE == -1) if_first(cardNumber);
        else if_second(cardNumber);
    }

    void if_first(int cardNumber){
        FIRST_CARD_NUMBER = cardNumber;
        FIRST_CARD_IMAGE = imageNum[cardNumber];

        cards[cardNumber].setImageResource(img_card_content[imageNum[cardNumber]]);
        cards[cardNumber].setEnabled(false);
        //change_to_checked(cardNumber);
        cards[cardNumber].startAnimation(scale_bigger);
    }

    void if_second(final int cardNumber){
        SECOND_CARD_NUMBER = cardNumber;
        SECOND_CARD_IMAGE = imageNum[cardNumber];

        cards[cardNumber].setImageResource(img_card_content[imageNum[cardNumber]]);
        cards[cardNumber].startAnimation(scale_bigger);

        for (int j=0; j<number_of_cards; j++)
            cards[j].setEnabled(false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                match_or_not();
            }
        }, 600);
    }

    void match_or_not(){
        //카드 같으면
        if(FIRST_CARD_IMAGE == SECOND_CARD_IMAGE){
            cards[FIRST_CARD_NUMBER].setImageResource(R.drawable.img_card_content_11);
            cards[SECOND_CARD_NUMBER].setImageResource(R.drawable.img_card_content_11);

            cards[FIRST_CARD_NUMBER].setEnabled(false);
            cards[SECOND_CARD_NUMBER].setEnabled(false);

            check_card[FIRST_CARD_NUMBER] = check_card[SECOND_CARD_NUMBER] = 1;

            score+=20;
            tv_score.setText(score+"점");
        }

        //카드 다르면
        else{
            cards[FIRST_CARD_NUMBER].setImageResource(R.drawable.img_card_back);
            cards[SECOND_CARD_NUMBER].setImageResource(R.drawable.img_card_back);

            tv_count2.setText(MAX_ATTEMPT - ++ATTEMPT_CNT +"회");
            if(score>=10) score-=10;
            else score=0;
            tv_score.setText(score+"점");
        }

        FIRST_CARD_NUMBER = SECOND_CARD_NUMBER = FIRST_CARD_IMAGE = SECOND_CARD_IMAGE = -1;

        check_game_over();
    }

    void check_game_over(){
        //게임 끝(lose)
        if(MAX_ATTEMPT == ATTEMPT_CNT){
            Intent intent = new Intent(getApplicationContext(), Game3ResultActivity.class);
            intent.putExtra("score", score);
            intent.putExtra("level", level);
            startActivity(intent);
        }

        //게임 끝(win)
        for(int i=0; i<number_of_cards; i++){
            if(check_card[i]!=1)
                break;
            if(i==number_of_cards-1) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    Intent intent;
                    if(level==3) {
                        intent = new Intent(getApplicationContext(), Game3ResultActivity.class);
                        intent.putExtra("level", level);
                    }
                    else {
                        intent = new Intent(getApplicationContext(), Game3Activity.class);
                        intent.putExtra("level", level + 1);
                    }

                    intent.putExtra("score", score);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    }
                }, 1000);
            }
        }

        for (int j=0; j<number_of_cards; j++)
            if(check_card[j]!=1)
                cards[j].setEnabled(true);
    }

    void change_to_checked(int cardNumber){

    }
}