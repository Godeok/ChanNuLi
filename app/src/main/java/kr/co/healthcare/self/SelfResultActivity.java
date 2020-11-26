package kr.co.healthcare.self;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import kr.co.healthcare.R;

public class SelfResultActivity extends AppCompatActivity {

    //ArrayList<SelfShowResult.SaveResult> selfShowResult = new SelfShowResult().ReadResultData();
    ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_result);

        for (int i=0; i<10; i++) {
            list.add(String.format("검사 번호 #%d", i));
            //list.add(String.format("검사 날짜 : %s", selfShowResult.get(i).date.toString()));
            //list.add(String.format("검사한 질병 : %d\n", selfShowResult.get(i).disease_num));
            //list.add(String.format("검사 결과 : %d개 응답에서 '예'\n", selfShowResult.get(i).countYes));
        }


        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView recyclerView = findViewById(R.id.recycler1) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        SelfRecyclerAdapter adapter = new SelfRecyclerAdapter(list) ;
        recyclerView.setAdapter(adapter) ;
    }
}