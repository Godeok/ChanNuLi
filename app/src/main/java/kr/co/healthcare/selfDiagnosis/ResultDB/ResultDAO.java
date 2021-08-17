package kr.co.healthcare.selfDiagnosis.ResultDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface ResultDAO {

    @Insert
    void insert(Result results);

    @Update
    void update(Result results);

    @Query("UPDATE Result_table SET Result_DISEASE = :di, Result_COUNT = :c, Result_DATE = :dt WHERE Result_num = :num")
    void update(int di, int c, String dt, int num);

    @Delete
    void delete(Result results);


    @Query("SELECT * FROM Result_table")
    List<Result> getAll();

    //최신순
    @Query("SELECT * FROM Result_table ORDER BY Result_num DESC")
    List<Result> getAllByDate();

    //질병별 최신순
    @Query("SELECT * FROM Result_table WHERE Result_DISEASE=:num ORDER BY Result_num DESC")
    List<Result> getAllByDisease(int num);

    //개수 반환 쿼리
    @Query("SELECT COUNT(*) FROM Result_table WHERE Result_DISEASE=:num AND Result_COUNT<=3")
    int countDiseaseSafe(int num);

    @Query("SELECT COUNT(*) FROM Result_table WHERE Result_DISEASE=:num AND Result_COUNT>3 AND Result_COUNT<=5")
    int countDiseaseWarning(int num);

    @Query("SELECT COUNT(*) FROM Result_table WHERE Result_DISEASE=:num AND Result_COUNT>5")
    int countDiseaseDanger(int num);

    //평균 반환 쿼리
    @Query("SELECT AVG(Result_COUNT) FROM Result_table WHERE Result_DISEASE=:num")
    int getAverageCountOfDisease(int num);

    @Query("DELETE FROM Result_table")
    void deleteAll();

    @Query("SELECT COUNT(*) as cnt FROM Result_table")
    int getDataCount();
}
