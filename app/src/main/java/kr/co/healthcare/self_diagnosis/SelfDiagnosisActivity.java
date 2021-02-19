package kr.co.healthcare.self_diagnosis;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import kr.co.healthcare.R;
import kr.co.healthcare.self_diagnosis.QuestionDB.DataAdapter;
import kr.co.healthcare.self_diagnosis.QuestionDB.Questions;


public class SelfDiagnosisActivity extends AppCompatActivity {

    int num=0, countYes=0, disease_num;
    String title="";
    private TextView tv_title, tv_question, tv_number;
    private Button btn_yes, btn_no;
    public List<Questions> questionsList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_diagnosis);

        tv_title = findViewById(R.id.tv_title);
        btn_yes = findViewById(R.id.btn_yes);
        btn_no = findViewById(R.id.btn_no);

        Intent intent = getIntent();
        title = intent.getStringExtra("str");
        disease_num = intent.getIntExtra("disease_num", -1);

        tv_title.setText(title);

        initLoadDB(disease_num);
        showQuestions(num);

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countYes++;
                if(++num>questionsList.size()-1) showResult(countYes, disease_num, title);
                else showQuestions(num);
            }
        });

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(++num>questionsList.size()-1) showResult(countYes, disease_num, title);
                else showQuestions(num);
            }
        });
    }

    @Override
    public void onBackPressed() {
        // AlertDialog 빌더를 이용해 종료시 발생시킬 창을 띄운다
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
        alBuilder.setMessage("종료 시 검사 결과가 저장되지 않습니다.");

        alBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alBuilder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });

        alBuilder.setTitle("자가진단 종료");
        alBuilder.show(); //AlertDialog.Bulider로 만든 AlertDialog 보여줌
    }

    //load DB
    private void initLoadDB(int n){
        DataAdapter mDBHelper = new DataAdapter(getApplicationContext());
        mDBHelper.createDatabase();
        mDBHelper.open();
        questionsList = mDBHelper.getTableData(n);
        mDBHelper.close();
    }

    //화면에 질문 띄우는 함수
    protected void showQuestions(int number){
        tv_question = findViewById(R.id.tv_question);
        tv_number = findViewById(R.id.tv_number);

        tv_number.setText(questionsList.get(number).getNum()+"번");
        tv_question.setText(questionsList.get(number).getQuestion());
    }

    //결과 페이지로 이동
    protected void showResult(int count, int disease_num, String title){
        Intent intent = new Intent(SelfDiagnosisActivity.this, SelfShowResult.class);
        intent.putExtra("count", count);
        intent.putExtra("disease_num", disease_num);
        intent.putExtra("title", title);
        startActivity(intent);
    }
}