package kr.co.healthcare.self.resultDB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;


@Dao
public interface ResultDAO {

    @Query("SELECT * FROM result")
    List<Result> getAllResults();

    @Insert
    void insertAll(Result results);
    //void insertAll(Result... results);
}
