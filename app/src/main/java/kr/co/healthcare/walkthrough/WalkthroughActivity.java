package kr.co.healthcare.walkthrough;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import kr.co.healthcare.MainActivity;
import kr.co.healthcare.R;
import kr.co.healthcare.tutorial.ui.TutorialActivity;

public class WalkthroughActivity extends AppCompatActivity {

    public Activity walkThroughActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough);

        walkThroughActivity = WalkthroughActivity.this;
    }

    public void onClickLayout(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        walkThroughActivity.finish();
    }
}