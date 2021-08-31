package kr.co.healthcare.game;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
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
    static int ATTEMPT_CNT = 0, MAX_ATTEMPT = 20, SCORE = 0, LEVEL = 1, PREVIOUS_SCORE = 0;

    int points, number_of_cards;
    LinearLayout layout_lv2, layout_lv3;
    ConstraintLayout layout_nextLv;
    TextView tv_level, tv_score, tv_leftAttempts;
    Animation anim_scale_bigger1, anim_scale_bigger2;

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

        init();

        init_cards();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<number_of_cards; i++) {
                    cards[i].setImageResource(R.drawable.img_card_back);
                    cards[i].setEnabled(true);
                }
            }
        }, 2000+ LEVEL *1000);

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
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this, R.style.AlertDialog);
        alBuilder.setMessage("종료 시 점수가 저장되지 않습니다.");

        alBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                reset_score();
            }
        });

        alBuilder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });

        alBuilder.setTitle("게임 종료");
        alBuilder.show();
    }


    //첫 화면 초기화 함수
    void init(){
        tv_level = findViewById(R.id.tv_level);
        tv_score = findViewById(R.id.tv_score);
        tv_leftAttempts = findViewById(R.id.tv_leftAttempts);
        layout_lv2 = findViewById(R.id.layout_lv2);
        layout_lv3 = findViewById(R.id.layout_lv3);
        layout_nextLv = findViewById(R.id.layout_nextLv);
        anim_scale_bigger1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_game3_scale_bigger);
        anim_scale_bigger2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_game3_scale_bigger);

        close_notice();

        //레벨 설정
        if(LEVEL ==1)
            setting(16, 20, 20, "쉬움");
        else if(LEVEL ==2){
            setting(20, 30, 25, "중간");
            layout_lv2.setVisibility(View.VISIBLE);     //숨긴 카드 보이게 하기
        }
        else if(LEVEL ==3) {
            setting(24, 40, 30, "어려움");
            layout_lv2.setVisibility(View.VISIBLE);
            layout_lv3.setVisibility(View.VISIBLE);
        }
        else number_of_cards = -1;

        tv_score.setText(SCORE +"");
        tv_leftAttempts.setText(MAX_ATTEMPT+"");
    }

    void setting(int numOfCards, int pts, int maxAttempt, String strLevel){
        number_of_cards = numOfCards;
        points = pts;
        MAX_ATTEMPT = maxAttempt;
        tv_level.setText(strLevel);
    }

    //카드 초기화
    void init_cards(){
        randomNum = new int[number_of_cards];
        imageNum = new int[number_of_cards];
        cards = new ImageView[number_of_cards];
        check_card = new int[number_of_cards];

        //randomNum 배열에 1부터 카드 개수 까지의 수 랜덤 정렬
        Random r = new Random();
        for(int i=0; i<number_of_cards; i++){
            randomNum[i] = r.nextInt(number_of_cards);
            for(int j=0; j<i; j++)
                if (randomNum[i] == randomNum[j])
                    i--;
        }

        //그림 번호 배열에 저장
        for(int i=0; i<number_of_cards; i++){
            imageNum[i] = randomNum[i]/2;
        }

        for(int i=0; i<number_of_cards; i++){
            cards[i] = (ImageView)findViewById(card_rid[i]);

            cards[i].setImageResource(img_card_content[imageNum[i]]);
            cards[i].setEnabled(false);

            cards[i].setElevation(10);
            cards[i].setBackground(getDrawable(R.drawable.view_game3_card));
            cards[i].setClipToOutline(true);
        }
    }

    void reset_score(){
        LEVEL = 1;
        SCORE = 0;
        ATTEMPT_CNT = 0;
        PREVIOUS_SCORE = 0;
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
        cards[cardNumber].startAnimation(anim_scale_bigger1);
    }

    void if_second(final int cardNumber){
        SECOND_CARD_NUMBER = cardNumber;
        SECOND_CARD_IMAGE = imageNum[cardNumber];

        cards[cardNumber].setImageResource(img_card_content[imageNum[cardNumber]]);
        cards[cardNumber].startAnimation(anim_scale_bigger2);

        for (int j=0; j<number_of_cards; j++)
            cards[j].setEnabled(false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                match_or_not();
            }
        }, 600);
    }

    void set_cards(int cardNum, int cardImage, int cardBackgroundImg, boolean bool){
        cards[cardNum].setImageResource(cardImage);
        cards[cardNum].setBackground(getDrawable(cardBackgroundImg));
        cards[cardNum].setEnabled(bool);
        check_card[cardNum] = 1;
    }

    void match_or_not(){
        //카드 같으면
        if(FIRST_CARD_IMAGE == SECOND_CARD_IMAGE){
            set_cards(FIRST_CARD_NUMBER, R.drawable.img_card_back_blur, R.drawable.view_game3_card_blur, false);
            set_cards(SECOND_CARD_NUMBER, R.drawable.img_card_back_blur, R.drawable.view_game3_card_blur, false);

            SCORE += points;
            tv_score.setText(SCORE +"");
        }

        //카드 다르면
        else{
            cards[FIRST_CARD_NUMBER].setImageResource(R.drawable.img_card_back);
            cards[SECOND_CARD_NUMBER].setImageResource(R.drawable.img_card_back);

            tv_leftAttempts.setText(MAX_ATTEMPT - ++ATTEMPT_CNT +"");
            if(SCORE>=PREVIOUS_SCORE+10) SCORE-=10;
            tv_score.setText(SCORE +"");
        }

        FIRST_CARD_NUMBER = SECOND_CARD_NUMBER = FIRST_CARD_IMAGE = SECOND_CARD_IMAGE = -1;

        check_game_over();
    }

    void check_game_over(){
        //게임 끝(lose)
        if(MAX_ATTEMPT == ATTEMPT_CNT){
            show_notice(3);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), GameResultActivity.class);
                    intent.putExtra("game", 3);
                    intent.putExtra("score", SCORE);
                    intent.putExtra("level", LEVEL);
                    reset_score();
                    startActivity(intent);
                }
            }, 1000);
        }

        //게임 끝(win)
        for(int i=0; i<number_of_cards; i++){
            if(check_card[i]!=1)
                break;

            if(i==number_of_cards-1) {
                show_notice(LEVEL);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent;
                        if(LEVEL==3) {
                            intent = new Intent(getApplicationContext(), GameResultActivity.class);
                            intent.putExtra("game", 3);
                            intent.putExtra("score", SCORE);
                            intent.putExtra("level", LEVEL);
                            reset_score();
                        }
                        else {
                            intent = new Intent(getApplicationContext(), Game3Activity.class);
                            PREVIOUS_SCORE = SCORE;
                            LEVEL += 1;
                            ATTEMPT_CNT = 0;
                        }

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

    void show_notice(int level){
        layout_nextLv.setVisibility(View.VISIBLE);
        TextView tv_nextLv = findViewById(R.id.tv_nextLv);
        if(level==3)
            tv_nextLv.setText("결과 화면으로!");
        else
            tv_nextLv.setText("다음 단계로!");
    }

    void close_notice(){
        layout_nextLv.setVisibility(View.GONE);
    }
}