package kr.co.healthcare.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        Button btn_to_game2_lv1 = findViewById(R.id.btn_to_game2_lv1);
        Button btn_to_game2_lv2 = findViewById(R.id.btn_to_game2_lv2);
        Button btn_to_game2_lv3 = findViewById(R.id.btn_to_game2_lv3);

        Button btn_to_game3_lv1 = findViewById(R.id.btn_to_game3_lv1);
        Button btn_to_game3_lv2 = findViewById(R.id.btn_to_game3_lv2);
        Button btn_to_game3_lv3 = findViewById(R.id.btn_to_game3_lv3);

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
                Intent intent = new Intent(getApplicationContext(), Game3ActivityLv1.class);
                intent.putExtra("level", 1);
                startActivity(intent);;
            }
        });

        btn_to_game3_lv2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Game3ActivityLv2.class);
                intent.putExtra("level", 2);
                startActivity(intent);;
            }
        });

        btn_to_game3_lv3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Game3ActivityLv3.class);
                intent.putExtra("level", 3);
                startActivity(intent);;
            }
        });
    }
}