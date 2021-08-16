package kr.co.healthcare.preference;

import android.content.Context;
import android.content.SharedPreferences;


public class GameResultPreferenceManager {

    public static final String PREFERENCES_NAME = "game_score_pref";
    public static final int DEFAULT_VALUE_INT = 0;

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static void saveBestScore(Context context, int game, int level, int value) {
        String key = setGameAndLevel(game, level);
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getBestScore(Context context, int game, int level) {
        String key = setGameAndLevel(game, level);
        SharedPreferences prefs = getPreferences(context);
        return prefs.getInt(key, DEFAULT_VALUE_INT);
    }

    public static String setGameAndLevel(int game, int level) {
        return "best_score_game"+game+"_lv"+level;
    }
}
