package kr.co.healthcare.game1;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import kr.co.healthcare.R;

//레벨3 : 컴퓨터도 사용자도 중복 없음. 컴퓨터 이기는 경우의 수 출력
public class Game1Lv3Activity extends AppCompatActivity {

    ImageButton user_btn1;
    ImageButton user_btn2;
    ImageView com_iv1;
    ImageView com_iv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1_lv3);

        user_btn1 = findViewById(R.id.user_btn1);
        user_btn2 = findViewById(R.id.user_btn2);
        com_iv1 = findViewById(R.id.com_iv1);
        com_iv2 = findViewById(R.id.com_iv2);
        final TextView result_tv = findViewById(R.id.result_tv);
        final ImageView com_img = findViewById(R.id.com_img);
        final ImageView user_img = findViewById(R.id.user_img);


        final int rand1 = (int)(Math.random()*3);
        final int rand2;
        final int rand3 = (int)(Math.random()*3);
        final int rand4;

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
        show_rsp(rand1, com_iv1);
        show_rsp(rand2, com_iv2);
        show_rsp_imgbtn(rand3, user_btn1);
        show_rsp_imgbtn(rand4, user_btn2);

        user_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //사용자
                show_rsp(rand3, user_img);

                //컴퓨터
                int p = win_rsp(rand1, rand2, rand3, rand4, com_img);
                check_result(rand3, p, result_tv);

                blurred_image(rand1, rand2, rand3, rand4);
            }
        });

        user_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //사용자
                show_rsp(rand4, user_img);

                //컴퓨터
                int p = win_rsp(rand1, rand2, rand3, rand4, com_img);
                check_result(rand4, p, result_tv);

                blurred_image(rand1, rand2, rand3, rand4);
            }
        });
    }


    //사진 흐리게
    void blurred_image(int rand1, int rand2, int rand3, int rand4){
        show_rsp_blur(rand1, com_iv1);
        show_rsp_blur(rand2, com_iv2);
        show_rsp_blur(rand3, user_btn1);
        show_rsp_blur(rand4, user_btn2);
    }

    //이미지 바꾸는 메소드
    void show_rsp(int index, ImageView img){
        //index: 난수, img: 바꿀 이미지 위치
        if(index==0)
            img.setImageResource(R.drawable.scissors);
        else if(index==1)
            img.setImageResource(R.drawable.rock);
        else
            img.setImageResource(R.drawable.paper);
    }

    //흐린 이미지 바꾸는 메소드
    void show_rsp_blur(int index, ImageView img){
        //index: 난수, img: 바꿀 이미지 위치
        if(index==0)
            img.setImageResource(R.drawable.scissors_blur);
        else if(index==1)
            img.setImageResource(R.drawable.rock_blur);
        else
            img.setImageResource(R.drawable.paper_blur);
    }

    //버튼 이미지 바꾸는 메소드
    void show_rsp_imgbtn(int index, ImageButton imgbtn){
        //index: 난수, img: 바꿀 이미지 위치
        if(index==0)
            imgbtn.setImageResource(R.drawable.scissors);
        else if(index==1)
            imgbtn.setImageResource(R.drawable.rock);
        else
            imgbtn.setImageResource(R.drawable.paper);
    }

    //게임 결과 알려주는 메소드
    void check_result(int user, int com, TextView tv){
        if ((user==0 && com==0) || (user==1 && com==1) || (user==2 && com==2))
            tv.setText("무승부입니다");

        else if ((user==0 && com==2) || (user==1 && com==0) || (user==2 && com==1))
            tv.setText("이겼습니다");

        else
            tv.setText("졌습니다");
    }


    //이길 확률을 높이는 쪽으로 작동하는 메소드
    int win_rsp(int com1, int com2, int user1, int user2, ImageView com_img){
        int s1 = compare_rsp(com1, user1, user2);
        int s2 = compare_rsp(com2, user1, user2);

        if (s1>s2){
            show_rsp(com1, com_img);
            return com1;
        }
        else {
            show_rsp(com2, com_img);
            return com2;
        }
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
}