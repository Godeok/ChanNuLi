package kr.co.healthcare.mypage.gamehistory;

import android.content.Context;
import android.widget.TextView;

import kr.co.healthcare.preference.GameResultPreferenceManager;

public class GameScore {
    private final int level;
    private final int type;
    private final TextView textView;

    public GameScore(int level, int type, TextView textView){
        this.level = level;
        this.type = type;
        this.textView = textView;
    }

    public void setBestScoreToTextView(Context context){
        textView.setText(GameResultPreferenceManager.getBestScore(context, type, level));
    }
}