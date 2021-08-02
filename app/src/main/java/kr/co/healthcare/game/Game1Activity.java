package kr.co.healthcare.game;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import kr.co.healthcare.R;


public class Game1Activity extends AppCompatActivity{

    TextView tv_level;
    ImageButton ib_user1;
    ImageButton ib_user2;
    ImageView iv_com1;
    ImageView iv_com2;
    TextView tv_score;
    TextView tv_result;
    int rand1, rand2, rand3, rand4;

    static int score=0;
    static int cnt=0;
    int level;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1_renewal);

        tv_level = findViewById(R.id.tv_level);
        level = getIntent().getIntExtra("level", -1);
        show_level(level);

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

        alBuilder.setTitle("게임 종료");
        alBuilder.show(); //AlertDialog.Bulider로 만든 AlertDialog 보여줌
    }


    private void loadActivity(){

        cnt++;

        ib_user1 = findViewById(R.id.ib_user1);
        ib_user2 = findViewById(R.id.ib_user2);
        iv_com1 = findViewById(R.id.iv_com1);
        iv_com2 = findViewById(R.id.iv_com2);
        tv_score = findViewById(R.id.tv_score);
        tv_result = findViewById(R.id.tv_result);

        //가위, 바위, 보 랜덤함수로 결정
        rand1 = (int)(Math.random()*3);
        rand3 = (int)(Math.random()*3);

        //가위, 바위, 보 중복 제거
        int rand2_temp = (int)(Math.random()*3);
        while (rand1==rand2_temp)
            rand2_temp = (int)(Math.random()*3);
        rand2 = rand2_temp;

        int rand4_temp = (int)(Math.random()*3);
        while (rand3==rand4_temp)
            rand4_temp = (int)(Math.random()*3);
        rand4 = rand4_temp;

        //첫 화면 설정
        show_rsp(rand1, iv_com1);
        show_rsp(rand2, iv_com2);
        show_rsp_imgbtn(rand3, ib_user1);
        show_rsp_imgbtn(rand4, ib_user2);
        tv_score.setText(score+"점");


        //첫 번째 이미지 버튼을 눌렀을 경우
        ib_user1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //사용자
               //show_rsp(rand3, iv_user);

                //컴퓨터
                check_lv(level, rand3);

                //이미지 앞으로
                //iv_user.bringToFront();
                //iv_com.bringToFront();
                gray_image(ib_user2);
                //blurred_image(rand1, rand2, rand3, rand4);
                next_lv();
            }
        });

        //두 번째 이미지 버튼을 눌렀을 경우
        ib_user2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //사용자
                //show_rsp(rand4, iv_user);

                //컴퓨터
                check_lv(level, rand4);

                //iv_user.bringToFront();
                //iv_com.bringToFront();
                gray_image(ib_user1);
                //blurred_image(rand1, rand2, rand3, rand4);
                next_lv();
            }
        });
    }

    //레벨 확인
    void check_lv(int level, int button_num){
        int random = (int)(Math.random()*10);
        if(level==1){
            if(random<3){
                int p = win_rsp(rand1, rand2, rand3, rand4);
                check_result(button_num, p);
            }
            else{
                int p = random_rsp(rand1, rand2, tv_result);
                check_result(button_num, p);
            }
        }

        else if(level==2){
            if(random<6){
                int p = win_rsp(rand1, rand2, rand3, rand4);
                check_result(button_num, p);
            }
            else{
                int p = random_rsp(rand1, rand2, tv_result);
                check_result(button_num, p);
            }
        }

        else{
            if(random<9){
                int p = win_rsp(rand1, rand2, rand3, rand4);
                check_result(button_num, p);
            }
            else{
                int p = random_rsp(rand1, rand2, tv_result);
                check_result(button_num, p);
            }
        }
    }

    //레벨 표시
    void show_level(int level){
        if(level==1) tv_level.setText("쉬움");
        else if(level==2) tv_level.setText("중간");
        else tv_level.setText("어려움");
    }

    //사진 흐리게
    /*
    void blurred_image(int rand1, int rand2, int rand3, int rand4){
        show_rsp_blur(rand1, iv_com1);
        show_rsp_blur(rand2, iv_com2);
        show_rsp_blur(rand3, btn_user1);
        show_rsp_blur(rand4, btn_user2);
    }

     */

    void gray_image(ImageView img){
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);

        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        img.setColorFilter(filter);
    }

    //이미지 바꾸는 메소드
    void show_rsp(int index, ImageView img) {
        //index: 난수, img: 바꿀 이미지 위치
        if (index == 0)
            img.setImageResource(R.drawable.img_scissor);
        else if (index == 1)
            img.setImageResource(R.drawable.img_rock);
        else
            img.setImageResource(R.drawable.img_paper);
    }

    //흐린 이미지 바꾸는 메소드
    /*
    void show_rsp_blur(int index, ImageView img){
        //index: 난수, img: 바꿀 이미지 위치
        if(index==0)
            img.setImageResource(R.drawable.scissors_blur);
        else if(index==1)
            img.setImageResource(R.drawable.rock_blur);
        else
            img.setImageResource(R.drawable.paper_blur);
    }
    */

    //버튼 이미지 바꾸는 메소드
    void show_rsp_imgbtn(int index, ImageButton imgbtn){
        //index: 난수, img: 바꿀 이미지 위치
        if(index==0)
            imgbtn.setImageResource(R.drawable.img_scissor);
        else if(index==1)
            imgbtn.setImageResource(R.drawable.img_rock);
        else
            imgbtn.setImageResource(R.drawable.img_paper);
    }

    //게임 결과 알려주는 메소드
    void check_result(int user, int com){
        if ((user==0 && com==0) || (user==1 && com==1) || (user==2 && com==2)){
            //tv.setText("무승부입니다");
            score+=100;
            tv_score.setText(score+"점");
        }

        else if ((user==0 && com==2) || (user==1 && com==0) || (user==2 && com==1)) {
            //tv.setText("이겼습니다");
            score+=200;
            tv_score.setText(score+"점");
        }

        //else tv.setText("졌습니다");
    }

    //컴퓨터가 랜덤으로 작동하는 메소드
    int random_rsp(int rand1, int rand2, TextView result_tv){
        int randNum = (int) (Math.random() * 2);
        if (randNum == 0) {
            return rand1;
            //check_result(rand3, rand1, result_tv);
        } else {
            return rand2;
            //check_result(rand3, rand2, result_tv);
        }
    }

    //컴퓨터가 이길 확률을 높이는 쪽으로 작동하는 메소드
    int win_rsp(int com1, int com2, int user1, int user2){
        int s1 = compare_rsp(com1, user1, user2);
        int s2 = compare_rsp(com2, user1, user2);

        if (s1>s2) return com1;
        else return com2;
    }

    //가위바위보 a가 승/무:1, 승/패:0, 무/패:-1
    int compare_rsp(int a, int b, int c){
        if ((a==0 && b==0) || (a==1 && b==1) || (a==2 && b==2)) { //무승부
            if ((a==0 && c==2) || (a==1 && c==0) || (a==2 && c==1)) //승
                return 1;
            else return -1; //패
        }

        else if ((a==0 && b==2) || (a==1 && b==0) || (a==2 && b==1)){   //승
            if ((a==0 && c==0) || (a==1 && c==1) || (a==2 && c==2))     //무
                return 1;
            else return 0;  //패
        }

        else{   //패
            if ((a==0 && c==2) || (a==1 && c==0) || (a==2 && c==1)) //승
                return 0;
            else return -1; //무승부
        }
    }

    //다음 단계로 넘어가는 메소드
    void next_lv(){
        //버튼 비활성화
        ib_user1 = findViewById(R.id.btn_user1);
        ib_user2 = findViewById(R.id.btn_user2);
        //ib_user1.setEnabled(false);
        //ib_user2.setEnabled(false);

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable()  {
            public void run() {
                //게임 반복 횟수가 다 안 찼을 경우
                if(cnt<3){
                    Intent intent = new Intent(getApplicationContext(), Game1Activity.class);
                    intent.putExtra("level", level);
                    intent.putExtra("game", 1);
                    startActivity(intent);
                }
                else{
                    int level = getIntent().getIntExtra("level", -1);
                    int score2 = score;
                    score=0;
                    cnt=0;

                    Intent intent = new Intent(getApplicationContext(), GameResultActivity.class);
                    intent.putExtra("score", score2);
                    intent.putExtra("level", level);
                    intent.putExtra("game", 1);
                    startActivity(intent);
                }
                //화면 전환 효과 없애기
                overridePendingTransition(0, 0);
            }
        }, 1000); // 1초후


    }
}