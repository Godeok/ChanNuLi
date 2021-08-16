package kr.co.healthcare.selfDiagnosis.ResultDB;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Result.class}, version = 2, exportSchema = false)
public abstract class SelfDiagnosisResultDatabase extends RoomDatabase {

    public abstract ResultDAO resultDAO();
    private static SelfDiagnosisResultDatabase instance = null;

    //싱글톤
    //데이터에 수정 하고싶을때에는 version 올리고, fallbackToDestructiveMigration 사용
    public static synchronized SelfDiagnosisResultDatabase getInstance(Context context){
        if(instance == null){
            instance =  Room.databaseBuilder(context.getApplicationContext(),
                    SelfDiagnosisResultDatabase.class, "memo_Database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
