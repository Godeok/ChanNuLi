package kr.co.healthcare.healthInfo.ui.main;

public class YoutubeVideo{
    private final String videoId;
    private final String videoTitle;
    private final String videoThumbnail;

    YoutubeVideo(String videoId, String videoTitle, String videoThumbnail){
        this.videoId = videoId;
        this.videoTitle = videoTitle;
        this.videoThumbnail = videoThumbnail;
    }

    public String getVideoId() {
        return videoId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public String getVideoThumbnail() {
        return videoThumbnail;
    }
}