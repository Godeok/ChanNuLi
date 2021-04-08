package kr.co.healthcare.healthInfo.ui.main;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import kr.co.healthcare.R;
import kr.co.healthcare.healthInfo.db.Video;

import static android.content.ContentValues.TAG;

public class HealthInfoVideoAdapter extends RecyclerView.Adapter<HealthInfoVideoAdapter.ItemViewHolder> {
    private ArrayList<YoutubeVideo> arrayList;
    private final Context context;

    public HealthInfoVideoAdapter(Context context){
        arrayList = new ArrayList<YoutubeVideo>();
        arrayList.add(new YoutubeVideo("wudOB4J7Oxs","노인이 되어도 건강하게 살고 싶다면","https://i.ytimg.com/vi/wudOB4J7Oxs/default.jpg"));
        this.arrayList = arrayList;
        this.context = context;
        System.out.println(arrayList.size());
    }

    @NonNull
    @Override
    public HealthInfoVideoAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_youtube_video,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HealthInfoVideoAdapter.ItemViewHolder holder, final int position) {
        System.out.println("onBindViewHolder");
        holder.videoTitle.setText(arrayList.get(position).getVideoTitle());
        Glide.with(context).load(arrayList.get(position).getVideoThumbnail()).into(holder.videoView);
        holder.videoView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        context.startActivity(new Intent(Intent.ACTION_VIEW)
                                .setData(Uri.parse(String.format("https://www.youtube.com/watch?v=%s", arrayList.get(position).getVideoId()))) // edit this url
                                .setPackage("com.google.android.youtube"));	// do not edit
                    }
                }
        );
    }

    public void addItem(YoutubeVideo data) {
        // 외부에서 item을 추가시킬 함수입니다.
        arrayList.add(data);
    }

    @Override
    public int getItemCount() {
        System.out.println("숫자셈~~");
        return (null != arrayList? arrayList.size():0);
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView videoTitle;
        ImageView videoView;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            videoTitle = itemView.findViewById(R.id.videoTitle);
            videoView = itemView.findViewById(R.id.videoView);
        }
    }



}
