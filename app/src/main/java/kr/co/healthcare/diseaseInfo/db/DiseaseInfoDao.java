package kr.co.healthcare.diseaseInfo.db;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DiseaseInfoDao {
    @Query("SELECT * FROM DiseaseInfo")
    List<DiseaseInfo> getAll();

    @Query("SELECT * FROM DiseaseInfo WHERE id = :diseaseId")
    DiseaseInfo loadByIds(int diseaseId);
}
