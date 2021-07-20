package kr.co.healthcare.game;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import kr.co.healthcare.R;


public class GameMainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);

        Button btn_to_game1_lv1 = findViewById(R.id.btn_to_game1_lv1);
        Button btn_to_game1_lv2 = findViewById(R.id.btn_to_game1_lv2);
        Button btn_to_game1_lv3 = findViewById(R.id.btn_to_game1_lv3);
        Button btn_toast1 = findViewById(R.id.btn_toast1);

        Button btn_to_game2_lv1 = findViewById(R.id.btn_to_game2_lv1);
        Button btn_to_game2_lv2 = findViewById(R.id.btn_to_game2_lv2);
        Button btn_to_game2_lv3 = findViewById(R.id.btn_to_game2_lv3);
        //Button btn_toast2 = findViewById(R.id.btn_toast2);

        Button btn_to_game3_lv1 = findViewById(R.id.btn_to_game3_lv1);
        //Button btn_to_game3_lv2 = findViewById(R.id.btn_to_game3_lv2);
        //Button btn_to_game3_lv3 = findViewById(R.id.btn_to_game3_lv3);
        //Button btn_toast3 = findViewById(R.id.btn_toast3);

        btn_to_game1_lv1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Game1Activity.class);
                intent.putExtra("level", 1);
                startActivity(intent);
            }
        });

        btn_to_game1_lv2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Game1Activity.class);
                intent.putExtra("level", 2);
                startActivity(intent);
            }
        });

        btn_to_game1_lv3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Game1Activity.class);
                intent.putExtra("level", 3);
                startActivity(intent);;
            }
        });


        btn_to_game2_lv1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Game2Activity.class);
                intent.putExtra("level", 1);
                startActivity(intent);
            }
        });

        btn_to_game2_lv2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Game2Activity.class);
                intent.putExtra("level", 2);
                startActivity(intent);
            }
        });

        btn_to_game2_lv3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Game2Activity.class);
                intent.putExtra("level", 3);
                startActivity(intent);;
            }
        });


        btn_to_game3_lv1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Game3Activity.class);
                intent.putExtra("level", 1);
                startActivity(intent);;
            }
        });
    }


    public void OnClickHandler(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(view.getId()==R.id.btn_toast1)
               builder.setTitle("하나빼기").setMessage("양 손으로 가위바위보를 먼저 한 다음, 둘 중 한 손을 내밀어 승패를 겨루는 게임입니다. 상대방의 양 손과 당신의 양 손을 보고, 이길 수 있는 손을 고르세요!");
        else if (view.getId()==R.id.btn_toast2)
            builder.setTitle("간단 연산").setMessage("시간 제한 안에 간단한 덧셈, 뺄셈, 나눗셈, 곱셈 연산을 하는 게임입니다. 숫자를 보고 연산자를 고르거나, 연산자를 보고 숫자를 고르세요!");
        else if (view.getId()==R.id.btn_toast3)
            builder.setTitle("카드 뒤집기").setMessage("똑같은 숫자가 적힌 카드 한 쌍을 찾는 게임입니다. 3초동안 보여주는 카드를 잘 외우고, 똑같은 카드 쌍을 찾으세요!");

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id) {}
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}