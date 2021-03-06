package kr.co.healthcare.healthInfo.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Video.class}, version = 1)
public abstract class VideoDB extends RoomDatabase{
    private static VideoDB INSTANCE;

    public abstract VideoDao videoDao();

    //디비객체생성 가져오기
    public static VideoDB getAppDatabase(Context context){
        if(INSTANCE == null){
            //todo: AsyncTask
            INSTANCE = Room.databaseBuilder(context, VideoDB.class , "exerciseDB")
                    .allowMainThreadQueries()
                    .createFromAsset("databases/exercise.db")
                    .build();
        }
        return  INSTANCE;
    }

    //디비객체제거
    public static void destroyInstance() {
        INSTANCE = null;
    }
}