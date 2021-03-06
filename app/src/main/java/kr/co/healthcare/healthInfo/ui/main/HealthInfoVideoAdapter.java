package kr.co.healthcare.healthInfo.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

import kr.co.healthcare.R;
import kr.co.healthcare.healthInfo.db.Video;

public class HealthInfoVideoAdapter extends RecyclerView.Adapter<HealthInfoVideoAdapter.ItemViewHolder> {
    private ArrayList<Video> arrayList;
    private Context context;

    public HealthInfoVideoAdapter(Context context, ArrayList<Video> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public HealthInfoVideoAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.health_info_item,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HealthInfoVideoAdapter.ItemViewHolder holder, final int position) {
        holder.youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.cueVideo(arrayList.get(position).getVideoId(), 0f);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arrayList? arrayList.size():0);
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private YouTubePlayerView youTubePlayerView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.youTubePlayerView = (YouTubePlayerView) itemView.findViewById(R.id.youtube_player_view);
        }
    }
}