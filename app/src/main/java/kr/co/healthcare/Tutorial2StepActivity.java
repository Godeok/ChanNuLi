package kr.co.healthcare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.shuhart.stepview.StepView;

import java.util.ArrayList;
import java.util.List;

public class Tutorial2StepActivity extends AppCompatActivity {
    public List<Diseases> diseasesList;
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
            System.out.println(object.name);
            diseasesNameList.add(object.name);
        }
        String[] DISEASES = diseasesNameList.toArray(new String[diseasesNameList.size()]);

        //자동완성 설정
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, DISEASES);
        AutoCompleteTextView autoCompleteTextViewTV = (AutoCompleteTextView)findViewById(R.id.autoCompleteTV);
        autoCompleteTextViewTV.setAdapter(adapter);

        //두 번째 튜토리얼 완료 시
        final Button secondStepFinishBtn = (Button) findViewById(R.id.secondStepFinishBtn);
        secondStepFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
