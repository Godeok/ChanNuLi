package kr.co.healthcare.tutorial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import kr.co.healthcare.R;

public class TutorialStartActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_start);

        final Button tutorialStartBtn = (Button) findViewById(R.id.tutorialStartBtn);
        tutorialStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Tutorial1StepActivity.class);
                startActivity(intent);
            }
        });
    }
}
