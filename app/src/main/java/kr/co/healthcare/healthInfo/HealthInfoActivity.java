package kr.co.healthcare.healthInfo;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
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
import java.util.Iterator;
import java.util.List;

import kr.co.healthcare.R;
import kr.co.healthcare.dialog.LoadingDialog;
import kr.co.healthcare.game.Game1Activity;
import kr.co.healthcare.healthInfo.ui.main.Exercise;
import kr.co.healthcare.healthInfo.ui.main.HealthInfoVideoAdapter;
import kr.co.healthcare.healthInfo.ui.main.YoutubeVideo;

public class HealthInfoActivity extends AppCompatActivity {

    private final String YOUTUBE_URL = "https://www.youtube.com";
    private String[] keywords;
    private ChipGroup chipGroup;
    private TextInputEditText textInput;
    private HealthInfoVideoAdapter recyclerViewAdapter;
    private LoadingDialog dialog;
    private RecyclerView videoRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_info);

        setResourses();
        //setKeywords();

        // 네트워크 연결상태 체크
        if (!NetworkConnection())
            NotConnected_showAlert();

        YoutubeAsyncTask youtubeAsyncTask = new YoutubeAsyncTask();
        youtubeAsyncTask.execute();

        recyclerViewAdapter = new HealthInfoVideoAdapter(this);
        videoRecyclerView.setAdapter(recyclerViewAdapter);
        videoRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        //chip
        for(String keyword : keywords){
            final Chip chip = (Chip) this.getLayoutInflater().inflate(
                    R.layout.item_chip_search_keyword, chipGroup, false);
            chip.setText(keyword);
            chipGroup.addView(chip);

            //chip 선택 했을 경우
            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), HealthInfoSearchActivity.class);
                    intent.putExtra("keyword", keyword);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                }
            });
        }

        //검색어 입력 후 엔터 눌렀을 경우
        textInput.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String keyword = textInput.getText().toString();
                switch (keyCode){
                    case  KeyEvent.KEYCODE_ENTER:
                        Intent intent = new Intent(getApplicationContext(), HealthInfoSearchActivity.class);
                        intent.putExtra("keyword", keyword);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        break;
                }
                return true;
            }
        });
    }

    private void setResourses(){
        keywords = getResources().getStringArray(R.array.SEARCH_KEYWORD_LABEL);
        dialog = new LoadingDialog(this);

        chipGroup = findViewById(R.id.CHIPGROUP_keywords);
        textInput = findViewById(R.id.textInput);
        videoRecyclerView = findViewById(R.id.videoRecyclerView);
    }

    private boolean NetworkConnection() {
        int[] networkTypes = {ConnectivityManager.TYPE_MOBILE, ConnectivityManager.TYPE_WIFI};
        try {
            ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            for (int networkType : networkTypes) {
                NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
                if (activeNetwork != null && activeNetwork.getType() == networkType) {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    private void NotConnected_showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HealthInfoActivity.this);
        builder.setTitle("네트워크 연결 오류");
        builder.setMessage("사용 가능한 무선네트워크가 없습니다.\n" + "먼저 무선네트워크 연결상태를 확인해 주세요.")
                .setCancelable(false)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish(); // exit
                        //application 프로세스를 강제 종료
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private class YoutubeAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            System.out.println(TAG + "doInBackground");

            try {
                HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
                final JsonFactory JSON_FACTORY = new JacksonFactory();
                final long NUMBER_OF_VIDEOS_RETURNED = 20; // 가져올 동영상의 갯수 지정

                YouTube youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
                    public void initialize(HttpRequest request) throws IOException {
                    }
                }).setApplicationName("youtube-search-sample").build();

                //검색 설정하기
                //선언 및 가져올 json자료 선정
                YouTube.Search.List search = youtube.search().list("id,snippet");
                //apiKey입력
                String API_KEY = "AIzaSyDkYsEr8kwXcSbDwA3uQZvvt4K1-j33Duo";
                search.setKey(API_KEY);
                //검색어
                search.setQ("노인 건강관리");
                search.setOrder("relevance"); //date relevance

                search.setType("video");
                search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/high/url)");
                //최대 갯수 설정 (20)개
                search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
                SearchListResponse searchResponse = search.execute();

                List<SearchResult> searchResultList = searchResponse.getItems();
                if (searchResultList != null) {
                    CheckList(searchResultList.iterator());
                }
            } catch (GoogleJsonResponseException e) {
                System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                        + e.getDetails().getMessage());
                System.err.println("There was a service error 2: " + e.getLocalizedMessage() + " , " + e.toString());
            } catch (IOException e) {
                System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
            } catch (Throwable t) {
                t.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            recyclerViewAdapter.notifyDataSetChanged();
            dialog.dismiss();
        }

        public void CheckList(Iterator<SearchResult> iteratorSearchResults) {
            if (!iteratorSearchResults.hasNext()) {
                System.out.println(" There aren't any results for your query.");
            }

            while (iteratorSearchResults.hasNext()) {
                SearchResult singleVideo = iteratorSearchResults.next();
                ResourceId rId = singleVideo.getId();

                // 비디오 한번 더 체크
                if (rId.getKind().equals("youtube#video")) {
                    Thumbnail thumbnail = (Thumbnail) singleVideo.getSnippet().getThumbnails().get("high");
                    recyclerViewAdapter.addItem(new YoutubeVideo(
                            rId.getVideoId(),
                            singleVideo.getSnippet().getTitle(),
                            thumbnail.getUrl()
                    ));
                }
            }
        }
    }
}