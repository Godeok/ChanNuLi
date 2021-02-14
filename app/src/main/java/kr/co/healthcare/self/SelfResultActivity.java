package kr.co.healthcare.self;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kr.co.healthcare.R;
import kr.co.healthcare.self.ResultDB.AppDatabase;
import kr.co.healthcare.self.ResultDB.*;
import kr.co.healthcare.self.Recycler.RecyclerAdapter;


public class SelfResultActivity extends AppCompatActivity {

    //리사이클러뷰
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerAdapter adapter;
    private List<Result> results;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_result);

        recyclerView = findViewById(R.id.recycler1);

        initialized();

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initialized() {
        //add = findViewById(R.id.addMemo);

        recyclerView = findViewById(R.id.recycler1);
        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new RecyclerAdapter();

        results = AppDatabase.getInstance(this).resultDAO().getAll();
        int size = results.size();
        for(int i = 0; i < size; i++){
            adapter.addItem(results.get(i));
        }
    }


    @Override
    protected void onStart() {
        results = AppDatabase.getInstance(this).resultDAO().getAll();
        adapter.addItems((ArrayList) results);
        super.onStart();
    }
}