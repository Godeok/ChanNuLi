package kr.co.healthcare.healthInfo.db;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface VideoDao {
    @Query("SELECT * FROM Video")
    List<Video> getAll();
}
