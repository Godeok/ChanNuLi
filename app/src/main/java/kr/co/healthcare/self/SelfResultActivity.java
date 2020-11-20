package kr.co.healthcare.self;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import kr.co.healthcare.R;

public class SelfResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_result);

        ArrayList<String> list = new ArrayList<>();
        for (int i=0; i<30; i++) {
            list.add(String.format("검사 번호 #%d", i));
            list.add(String.format("검사 날짜 : %d", 1101+i));
            list.add(String.format("검사 결과 : %d\n", i));
        }

        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView recyclerView = findViewById(R.id.recycler1) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        SelfRecyclerAdapter adapter = new SelfRecyclerAdapter(list) ;
        recyclerView.setAdapter(adapter) ;
    }
}