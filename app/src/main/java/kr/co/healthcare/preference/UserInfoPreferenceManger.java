package kr.co.healthcare.preference;
import android.content.Context;

import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class UserInfoPreferenceManger {

    public static final String PREFERENCES_NAME = "USER_INFORMATION";

    public static final String PREF_KEY_USER_NAME = "USER_NAME";
    public static final String PREF_KEY_USER_BIRTH = "USER_BIRTH";
    public static final String PREF_KEY_USER_GENDER = "USER_GENDER";
    public static final String PREF_KEY_USER_DISEASES = "USER_DISEASES";
    public static final String PREF_KEY_TUTORIAL_FINISHED = "TUTORIAL_FINISHED";

    public static final String PREF_VALUE_GENDER_MAN = "MAN";
    public static final String PREF_VALUE_GENDER_WOMAN = "WOMAN";

    private static final String DEFAULT_VALUE_STRING = "";
    private static final boolean DEFAULT_VALUE_BOOLEAN = false;
    private static final int DEFAULT_VALUE_INT = -1;

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static void setString(Context context, String key, String value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

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

    public static void setBoolean(Context context, String key, boolean value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void setInt(Context context, String key, int value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static String getString(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getString(key, DEFAULT_VALUE_STRING);
    }

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

    public static boolean getBoolean(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getBoolean(key, DEFAULT_VALUE_BOOLEAN);
    }

    public static int getInt(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getInt(key, DEFAULT_VALUE_INT);
    }

    public static void removeKey(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor edit = prefs.edit();
        edit.remove(key);
        edit.apply();
    }

    public static void clear(Context context) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor edit = prefs.edit();
        edit.clear();
        edit.apply();
    }
}