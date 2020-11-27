package kr.co.healthcare.tutorial;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shuhart.stepview.StepView;

import java.util.ArrayList;
import java.util.List;

import kr.co.healthcare.DataAdapter;
import kr.co.healthcare.Diseases;
import kr.co.healthcare.MainActivity;
import kr.co.healthcare.PreferenceManger;
import kr.co.healthcare.R;

public class Tutorial2StepActivity extends AppCompatActivity {
    public List<Diseases> diseasesList;
    private DiseasesListAdapter mAdapter;
    Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_2_step);
        mcontext=this;

        final StepView svStep2 = findViewById(R.id.step_view_step2);
        svStep2.go(1, true);

        //질병 DB 초기화
        initLoadDB();
        List<String> diseasesNameList = new ArrayList<>();
        for(Diseases object : diseasesList){
            diseasesNameList.add(object.getName());
        }
        String[] DISEASES = diseasesNameList.toArray(new String[diseasesNameList.size()]);

        //자동완성 설정
        ArrayAdapter<String> autoCompleteAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, DISEASES);
        final AutoCompleteTextView autoCompleteTextViewTV = (AutoCompleteTextView)findViewById(R.id.autoCompleteTV);
        autoCompleteTextViewTV.setAdapter(autoCompleteAdapter);

        //질병 리스트 연결
        RecyclerView recyclerView = findViewById(R.id.diseasesRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        mAdapter = new DiseasesListAdapter();
        recyclerView.setAdapter(mAdapter);

        //질병 추가 버튼
        final Button addDiseaseBtn = (Button) findViewById(R.id.addDiseaseBtn);
        addDiseaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseN = autoCompleteTextViewTV.getText().toString();
                mAdapter.addItem(diseaseN);
                mAdapter.notifyDataSetChanged();
                autoCompleteTextViewTV.setText("");
            }
        });


        //두 번째 튜토리얼 완료 버튼
        final Button secondStepFinishBtn = (Button) findViewById(R.id.secondStepFinishBtn);
        secondStepFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //질병 데이터 저장
                String data = "";
                ArrayList<String> diseasesList = ((DiseasesListAdapter) mAdapter).getDiseasesList();
                for (int i = 0; i < diseasesList.size(); i++) {
                    String disease = diseasesList.get(i);
                    data = data + "\n" + disease;
                }
                PreferenceManger.setString(mcontext, "diseases",data);
                PreferenceManger.setBoolean(mcontext, "isTutorialFinished",true);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initLoadDB() {
        DataAdapter mDbHelper = new DataAdapter(getApplicationContext());
        mDbHelper.createDatabase();
        mDbHelper.open();

        // db에 있는 값들을 model을 적용해서 넣는다.
        diseasesList = mDbHelper.getTableData();

        // db 닫기
        mDbHelper.close();
    }
}
