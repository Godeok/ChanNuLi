package kr.co.healthcare.walkthrough;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import kr.co.healthcare.MainActivity;
import kr.co.healthcare.R;

public class WalkthroughActivity extends AppCompatActivity {

    public Activity walkThroughActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough);

        walkThroughActivity = WalkthroughActivity.this;
        loadAnimation();
    }

    public void onClickNextStepBtn(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        walkThroughActivity.finish();
    }

    private void loadAnimation(){
        final LinearLayout linearLayout = findViewById(R.id.linearLayout);
        final Animation popUpAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_tutorial_fade_in);
        linearLayout.setAnimation(popUpAnimation);
    }
}