package kr.co.healthcare.mypage.gamehistory;

import android.content.Context;
import android.widget.TextView;

import kr.co.healthcare.preference.GameResultPreferenceManager;

public class GameScore {
    private final int type;
    private final int level;
    private final TextView textView;

    public GameScore(int type, int level, TextView textView){
        this.type = type;
        this.level = level;
        this.textView = textView;
    }

    public void setBestScoreToTextView(Context context){
        textView.setText(Integer.toString(GameResultPreferenceManager.getBestScore(context, type, level)));
    }
}