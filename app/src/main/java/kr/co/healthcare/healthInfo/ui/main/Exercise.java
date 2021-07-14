package kr.co.healthcare.healthInfo.ui.main;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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

import static android.content.ContentValues.TAG;

public class Exercise extends Fragment {
    HealthInfoVideoAdapter recyclerViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise, container, false);
        YoutubeAsyncTask youtubeAsyncTask = new YoutubeAsyncTask();
        youtubeAsyncTask.execute();
        RecyclerView recyclerView = view.findViewById(R.id.videoRecyclerView);
        recyclerViewAdapter = new HealthInfoVideoAdapter(getActivity());
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    private class YoutubeAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
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
                //TODO EditText 이용해서 검색어 가져와도 됩니다.
                search.setQ("노인 건강관리");
                // 채널 지정가능
                //search.setChannelId("UCk9GmdlDTBfgGRb7vXeRMoQ");
                search.setOrder("relevance"); //date relevance

                search.setType("video");
                search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
                //최대 갯수 설정 (20)개
                search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
                SearchListResponse searchResponse = search.execute();

                List<SearchResult> searchResultList = searchResponse.getItems();
                System.out.println("출력============");
                System.out.println(searchResponse.getItems());
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
            //adapter.notifyDataSetChanged();

        }

        public void CheckList(Iterator<SearchResult> iteratorSearchResults) {
            System.out.println(TAG + "InputList");
            if (!iteratorSearchResults.hasNext()) {
                System.out.println(" There aren't any results for your query.");
            }

            while (iteratorSearchResults.hasNext()) {
                SearchResult singleVideo = iteratorSearchResults.next();
                ResourceId rId = singleVideo.getId();

                // 비디오 한번 더 체크
                if (rId.getKind().equals("youtube#video")) {
                    //썸네일  thumbnail.getUrl()
                    Thumbnail thumbnail = (Thumbnail) singleVideo.getSnippet().getThumbnails().get("default");
                    System.out.println("썸네일====");
                    System.out.println(thumbnail.getUrl());
                    // 비디오 rId.getVideoId()
                    recyclerViewAdapter.addItem(new YoutubeVideo(
                            rId.getVideoId(),
                            singleVideo.getSnippet().getTitle(),
                            thumbnail.getUrl()
                    ));
                    System.out.println("================출력중=====================");
                    System.out.println("ID : " + rId.getVideoId());
                    System.out.println(" , 제목 : " + singleVideo.getSnippet().getTitle());
                    System.out.println(" , 썸네일 주소 : " + thumbnail.getUrl());

                }
            }
        }
    }

}