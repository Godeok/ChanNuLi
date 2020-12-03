package kr.co.healthcare.self;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import kr.co.healthcare.R;
import kr.co.healthcare.self.resultDB.AppDatabase;
import kr.co.healthcare.self.resultDB.Result;

public class SelfResultActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    //RecyclerView.Adapter adapter;
    SelfRecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_result);

        recyclerView = findViewById(R.id.recycler1);
        List<Result> results; // = new ArrayList<>();



        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "result-db")
                .allowMainThreadQueries()
                .build();

        results = db.resultDao().getAllResults();

        /*
        for (int i=0; i<10; i++){
            Result result = new Result(1, 3, "12.04");
            results.add(result);
        }
        */



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new SelfRecyclerAdapter(results);
        recyclerView.setAdapter(adapter);


    }
}