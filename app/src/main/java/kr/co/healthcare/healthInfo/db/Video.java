package kr.co.healthcare.healthInfo.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Video {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private final int id;
    private final String videoId;

    Video(@NonNull int id, String videoId){
        this.id = id;
        this.videoId = videoId;
    }

    public int getId() {
        return id;
    }

    public String getVideoId() {
        return videoId;
    }
}