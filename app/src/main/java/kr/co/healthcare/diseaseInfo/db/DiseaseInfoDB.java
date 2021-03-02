package kr.co.healthcare.diseaseInfo.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DiseaseInfo.class}, version = 1)
public abstract class DiseaseInfoDB extends RoomDatabase {
    private static DiseaseInfoDB INSTANCE;

    public abstract DiseaseInfoDao infoDao();

    //디비객체생성 가져오기
    public static DiseaseInfoDB getAppDatabase(Context context){
        if(INSTANCE == null){
            //todo: AsyncTask
            INSTANCE = Room.databaseBuilder(context, DiseaseInfoDB.class , "diseaseInfoDB")
                    .allowMainThreadQueries()
                    .createFromAsset("databases/diseaseinfo.db")
                    .build();
        }
        return  INSTANCE;
    }

    //디비객체제거
    public static void destroyInstance() {
        INSTANCE = null;
    }
}
