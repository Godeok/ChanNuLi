package kr.co.healthcare.self.ResultDB;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Result_table")
public class Result implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="Result_num")
    public int id;

    @ColumnInfo(name="Result_DISEASE")
    public int disease;

    @ColumnInfo(name="Result_COUNT")
    public int count;

    @ColumnInfo(name="Result_DATE")
    public String date;


    public Result(int disease, int count, String date){
        this.disease = disease;
        this.count = count;
        this.date = date;
    }

    protected Result(Parcel in) {
        id = in.readInt();
        disease = in.readInt();
        count = in.readInt();
        date = in.readString();
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) { return new Result(in); }

        @Override
        public Result[] newArray(int size) { return new Result[size]; }
    };

    public void setId(int id) {
        this.id = id;
    }

    public void setDisease(int disease) {
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

    public int getDisease() {
        return disease;
    }

    public int getCount() {
        return count;
    }

    public String getDate() {
        return date;
    }


    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(disease);
        dest.writeInt(count);
        dest.writeString(date);
    }
}
