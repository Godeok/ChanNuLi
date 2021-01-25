package kr.co.healthcare.game1;

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

        Button move_lv1 = findViewById(R.id.move_lv1);
        Button move_lv2 = findViewById(R.id.move_lv2);
        Button move_lv3 = findViewById(R.id.move_lv3);

        move_lv1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Game1Activity.class);
                intent.putExtra("level", 1);
                startActivity(intent);
            }
        });

        move_lv2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Game1Activity.class);
                intent.putExtra("level", 2);
                startActivity(intent);
            }
        });

        move_lv3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Game1Activity.class);
                intent.putExtra("level", 3);
                startActivity(intent);;
            }
        });
    }
}