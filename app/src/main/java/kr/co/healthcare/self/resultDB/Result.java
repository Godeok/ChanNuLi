package kr.co.healthcare.self.resultDB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Result")
public class Result {

    public Result(String disease, int count, String date){
        this.disease = disease;
        this.count = count;
        this.date = date;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="#")
    public int id;

    @ColumnInfo(name="DISEASE")
    public String disease;

    @ColumnInfo(name="COUNT")
    public int count;

    @ColumnInfo(name="DATE")
    public String date;


    public Result(){

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getDisease() {
        return disease;
    }

    public int getCount() {
        return count;
    }

    public String getDate() {
        return date;
    }
}
