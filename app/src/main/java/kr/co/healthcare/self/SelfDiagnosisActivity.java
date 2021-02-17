package kr.co.healthcare.self;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import kr.co.healthcare.R;
import kr.co.healthcare.self.QuestionDB.DataAdapter;
import kr.co.healthcare.self.QuestionDB.Questions;


public class SelfDiagnosisActivity extends AppCompatActivity {

    int num=0, countYes=0, disease_num;
    private TextView tv_title, tv_question, tv_number;
    private Button btn_yes, btn_no;
    public List<Questions> questionsList;

    private void initLoadDB(int n){
        DataAdapter mDBHelper = new DataAdapter(getApplicationContext());
        mDBHelper.createDatabase();
        mDBHelper.open();
        questionsList = mDBHelper.getTableData(n);
        mDBHelper.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_diagnosis);

        tv_title = findViewById(R.id.tv_title);
        btn_yes = findViewById(R.id.btn_yes);
        btn_no = findViewById(R.id.btn_no);

        Intent intent = getIntent();
        String title = intent.getStringExtra("str");
        disease_num = intent.getIntExtra("disease_num", -1);

        tv_title.setText(title);

        initLoadDB(disease_num);
        showQuestions(num);

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countYes++;
                if(++num>2)
                    showResult(countYes, disease_num);
                else {
                    showQuestions(num);
                }
            }
        });

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(++num>2)
                    showResult(countYes, disease_num);
                else
                    showQuestions(num);
            }
        });
    }

    protected void showQuestions(int number){
        tv_question = findViewById(R.id.tv_question);
        tv_number = findViewById(R.id.tv_number);

        tv_number.setText(questionsList.get(number).getNum());
        tv_question.setText(questionsList.get(number).getQuestion());
    }

    protected void showResult(int count, int disease_num){
        Intent intent = new Intent(SelfDiagnosisActivity.this, SelfShowResult.class);
        intent.putExtra("count", count);
        intent.putExtra("disease_num", disease_num);
        startActivity(intent);
    }
}