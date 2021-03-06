package kr.co.healthcare.healthInfo.ui.main;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

import kr.co.healthcare.healthInfo.db.Video;
import kr.co.healthcare.healthInfo.db.VideoDB;

public class DataBuilder {
    private final Context context;

    DataBuilder(Context context){
        this.context = context;
    }

    public ArrayList<Video> initVideoByNameOfDB(String name) {
        VideoDB db = VideoDB.getAppDatabase(context);
        ArrayList<Video> videoArrayList = new ArrayList<Video>(db.videoDao().getAll());
        db.close();
        return videoArrayList;
    }
}
