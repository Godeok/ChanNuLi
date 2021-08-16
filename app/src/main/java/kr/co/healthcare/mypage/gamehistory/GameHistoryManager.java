package kr.co.healthcare.mypage.gamehistory;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;

public class GameHistoryManager {

    private SharedPreferences sharedPreferences;

    GameHistoryManager(){
        sharedPreferences = getSharedPreferences("game_score_pref", MODE_PRIVATE);    //초기화 (shared preferences 이름, 실행 모드)
        int score = sharedPreferences.getInt("best_score_game1_lv1", 0);     //가져올 데이터의 key, 데이터 없을 시 default 값
    }

    int load_data(int game, int level){
        SharedPreferences pref= getSharedPreferences("game_score_pref", Activity.MODE_PRIVATE);
        String str = "best_score_game"+game+"_lv"+level;
        int best_record = pref.getInt(str , 0);
        return best_record;
    }
}


    best_score_game1_lv1
            best_score_game1_lv2
    best_score_game1_lv3
            best_score_game2_lv1
    best_score_game2_lv2
            best_score_game2_lv3
    best_score_game3_lv1
            best_score_game3_lv2
best_score_game3_lv3

        textView.setText(score +"점");

