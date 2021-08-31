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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import kr.co.healthcare.R;

import static android.content.ContentValues.TAG;

public class HealthInfoVideoAdapter extends RecyclerView.Adapter<HealthInfoVideoAdapter.ItemViewHolder> {
    private ArrayList<YoutubeVideo> arrayList;
    private final Context context;

    public HealthInfoVideoAdapter(Context context){
        arrayList = new ArrayList<YoutubeVideo>();
        this.context = context;
        System.out.println(arrayList.size());
    }

    @NonNull
    @Override
    public HealthInfoVideoAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_youtube_video,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HealthInfoVideoAdapter.ItemViewHolder holder, final int position) {
        holder.videoTitle.setText(arrayList.get(position).getVideoTitle());
        Glide.with(context).load(arrayList.get(position).getVideoThumbnail()).into(holder.videoView);
        holder.linearLayout.setOnClickListener(
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
        arrayList.add(data);
    }

    @Override
    public int getItemCount() {
        return (null != arrayList? arrayList.size():0);
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView videoTitle;
        ImageView videoView;
        LinearLayout linearLayout;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            videoTitle = itemView.findViewById(R.id.videoTitle);
            videoView = itemView.findViewById(R.id.videoView);
            linearLayout = itemView.findViewById(R.id.heading_layout);
        }
    }
}
