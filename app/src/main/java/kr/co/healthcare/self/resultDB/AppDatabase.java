package kr.co.healthcare.self.resultDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Result.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ResultDAO resultDao();
}
