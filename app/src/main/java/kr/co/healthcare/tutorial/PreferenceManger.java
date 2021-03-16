package kr.co.healthcare.tutorial;
import android.content.Context;

import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

//데이터 저장 및 로드 클래스
public class PreferenceManger {
    public static final String PREFERENCES_NAME = "USER_INFORMATION";

    public static final String GENDER_VALUE_MAN = "MAN";
    public static final String GENDER_VALUE_WOMAN = "WOMAN";

    public static final String PREF_USER_NAME = "username";
    public static final String PREF_USER_YEAR_OF_BIRTH = "useryearofbirth";
    public static final String PREF_USER_GENDER = "usergender";
    public static final String PREF_USER_DISEASES = "userdiseases";
    public static final String PREF_IS_TUTORIAL_FINISHED = "istutorialfinished";

    private static final String DEFAULT_VALUE_STRING = "";
    private static final boolean DEFAULT_VALUE_BOOLEAN = false;
    private static final int DEFAULT_VALUE_INT = -1;

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    //String 값 저장
    public static void setString(Context context, String key, String value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    //ArrayList값 저장
    public static void setStringArrayList(Context context, String key, ArrayList<String> values) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < values.size(); i++) {
            jsonArray.put(values.get(i));
        }
        if (!values.isEmpty()) {
            editor.putString(key, jsonArray.toString());
        } else {
            editor.putString(key, null);
        }
        editor.apply();
    }

    //boolean 값 저장
    public static void setBoolean(Context context, String key, boolean value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    //int 값 저장
    public static void setInt(Context context, String key, int value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    //String 값 로드
    public static String getString(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getString(key, DEFAULT_VALUE_STRING);
    }

    //String 값 로드
    public static ArrayList<String> getStringArrayList(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        String json = prefs.getString(key, null);
        ArrayList<String> arrayList = new ArrayList<String>();
        if (json != null) {
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    String value = jsonArray.optString(i);
                    arrayList.add(value);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    //boolean 값 로드
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getBoolean(key, DEFAULT_VALUE_BOOLEAN);
    }

    //int 값 로드
    public static int getInt(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getInt(key, DEFAULT_VALUE_INT);
    }

    //키 값 삭제
    public static void removeKey(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor edit = prefs.edit();
        edit.remove(key);
        edit.apply();
    }

    //모든 저장 데이터 삭제
    public static void clear(Context context) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor edit = prefs.edit();
        edit.clear();
        edit.apply();
    }
}