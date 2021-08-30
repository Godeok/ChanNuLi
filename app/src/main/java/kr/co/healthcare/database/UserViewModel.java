package kr.co.healthcare.database;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import kr.co.healthcare.preference.UserInfoPreferenceManger;

public class UserViewModel extends ViewModel {
    private static UserViewModel INSTANCE;
    public MutableLiveData<String> name;
    public MutableLiveData<String> gender;
    public MutableLiveData<String> birthYear;
    public MutableLiveData<ArrayList<String>> diseases;

    public static UserViewModel getINSTANCE() {
        if(INSTANCE == null) INSTANCE = new UserViewModel();
        return INSTANCE;
    }

    /*
    유저 이름
     */

    public LiveData<String> getUserName(Context context) {
        if (name == null) {
            name = new MutableLiveData<String>();
            name.setValue(loadUserName(context));
        }
        return name;
    }

    public void setUserName(Context context, String name){
        this.name.setValue(name);
        saveUserName(context, this.name.getValue());
    }

    private void saveUserName(Context context, String name){
        UserInfoPreferenceManger.setString(context, UserInfoPreferenceManger.PREF_KEY_USER_NAME, name);
    }

    private String loadUserName(Context context){
        return UserInfoPreferenceManger.getString(context, UserInfoPreferenceManger.PREF_KEY_USER_NAME);
    }

    /*
    유저 성별
     */

    public LiveData<String> getUserGender(Context context) {
        if (gender == null) {
            gender = new MutableLiveData<String>();
            gender.setValue(loadUserGender(context));
        }
        return gender;
    }

    public void setUserGender(Context context, String gender){
        this.gender.setValue(gender);
        saveUserGender(context, this.gender.getValue());
    }

    private void saveUserGender(Context context, String gender){
        UserInfoPreferenceManger.setString(context, UserInfoPreferenceManger.PREF_KEY_USER_GENDER, gender);
    }

    private String loadUserGender(Context context){
        return UserInfoPreferenceManger.getString(context, UserInfoPreferenceManger.PREF_KEY_USER_GENDER);
    }

    /*
    유저 생년
     */

    public LiveData<String> getUserBirthYear(Context context) {
        if (birthYear == null) {
            birthYear = new MutableLiveData<String>();
            birthYear.setValue(loadUserBirthYear(context));
        }
        return birthYear;
    }

    public void setUserBirthYear(Context context, String birthYear){
        this.birthYear.setValue(birthYear);
        saveUserBirthYear(context, this.birthYear.getValue());
    }

    private void saveUserBirthYear(Context context, String birthYear){
        UserInfoPreferenceManger.setString(context, UserInfoPreferenceManger.PREF_KEY_USER_BIRTH, birthYear);
    }

    private String loadUserBirthYear(Context context){
        return UserInfoPreferenceManger.getString(context, UserInfoPreferenceManger.PREF_KEY_USER_BIRTH);
    }

        /*
    유저 질병
     */

    public LiveData<ArrayList<String>> getUserDiseases(Context context) {
        if (diseases == null) {
            diseases = new MutableLiveData<ArrayList<String>>();
            diseases.setValue(loadUserDiseases(context));
        }
        return diseases;
    }

    public void setUserDiseases(Context context, ArrayList<String> diseases){
        this.diseases.setValue(diseases);
        saveUserDiseases(context, this.diseases.getValue());
    }

    private void saveUserDiseases(Context context, ArrayList<String> diseases){
        UserInfoPreferenceManger.setStringArrayList(context, UserInfoPreferenceManger.PREF_KEY_USER_DISEASES, diseases);
    }

    private ArrayList<String> loadUserDiseases(Context context){
        return UserInfoPreferenceManger.getStringArrayList(context, UserInfoPreferenceManger.PREF_KEY_USER_DISEASES);
    }

    public int getUserDiseasesCount() {
        if (diseases == null) return 0;
        else return diseases.getValue().size();
    }

}

