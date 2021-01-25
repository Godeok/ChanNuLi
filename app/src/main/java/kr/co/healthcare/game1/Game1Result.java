package kr.co.healthcare.game1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kr.co.healthcare.R;

public class Game1Result extends AppCompatActivity {

    TextView level_tv;
    TextView score_tv;
    TextView showBest_tv;
    Button restart_btn;
    Button end_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1_result);

        level_tv = findViewById(R.id.level_tv);
        score_tv = findViewById(R.id.score_tv);
        showBest_tv = findViewById(R.id.showBest_tv);
        restart_btn = findViewById(R.id.restart_btn);
        end_btn = findViewById(R.id.end_btn);

        int score = getIntent().getIntExtra("score", -1);

        score_tv.setText(score+"점");

        restart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Game1Activity.class);
                startActivity(intent);
            }
        });

        end_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}