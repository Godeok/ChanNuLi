package kr.co.healthcare.self_diagnosis.ResultDB;

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
    @Query("SELECT * FROM Result_table WHERE Result_DISEASE=0 ORDER BY Result_num DESC")
    List<Result> getAllByDisease0();

    @Query("SELECT * FROM Result_table WHERE Result_DISEASE=1 ORDER BY Result_num DESC")
    List<Result> getAllByDisease1();

    @Query("SELECT * FROM Result_table WHERE Result_DISEASE=2 ORDER BY Result_num DESC")
    List<Result> getAllByDisease2();

    @Query("SELECT * FROM Result_table WHERE Result_DISEASE=3 ORDER BY Result_num DESC")
    List<Result> getAllByDisease3();

    @Query("SELECT * FROM Result_table WHERE Result_DISEASE=4 ORDER BY Result_num DESC")
    List<Result> getAllByDisease4();

    @Query("SELECT * FROM Result_table WHERE Result_DISEASE=5 ORDER BY Result_num DESC")
    List<Result> getAllByDisease5();

    @Query("SELECT * FROM Result_table WHERE Result_DISEASE=6 ORDER BY Result_num DESC")
    List<Result> getAllByDisease6();


    //개수 반환 쿼리
    @Query("SELECT COUNT(*) FROM Result_table WHERE Result_DISEASE=:num AND Result_COUNT<3")
    int countDiseaseSafe(int num);

    @Query("SELECT COUNT(*) FROM Result_table WHERE Result_DISEASE=:num AND Result_COUNT>2 AND Result_COUNT<6")
    int countDiseaseWarning(int num);

    @Query("SELECT COUNT(*) FROM Result_table WHERE Result_DISEASE=:num AND Result_COUNT>5")
    int countDiseaseDanger(int num);





    @Query("DELETE FROM Result_table")
    void deleteAll();

    @Query("SELECT COUNT(*) as cnt FROM Result_table")
    int getDataCount();


}
