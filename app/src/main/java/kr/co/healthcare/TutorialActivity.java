package kr.co.healthcare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.shuhart.stepview.StepView;

import java.util.List;

public class TutorialActivity extends AppCompatActivity {
    //변수
    public static Activity tutorialActivity;
    public EditText dateText;
    public Button dateBtn;
    private static final String[] diseasesData = new String[] {
            "Belgium", "France", "Italy", "Germany", "Spain"
    };
    String[] diseasesArr = {};

    Context mcontext;
    int step = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        mcontext = this;
        tutorialActivity = TutorialActivity.this;
        final StepView stepView = findViewById(R.id.step_view);

        changeView(step);
        final Button finishBtn1 = (Button) findViewById(R.id.firstStepFinishBtn);
        finishBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(step){
                    case 0: //첫 번째 튜토리얼 완성했을 때
                        setDataAndChangeViewbyStep1(stepView);
                        break;
                    case 1: //두 번째 튜토리얼 완성했을 때
                        setDataAndChangeViewbyStep2(stepView);
                        break;
                    case 2: //세 번째 튜토리얼 완성했을 때
                        setDataAndChangeViewbyStep3(stepView);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        tutorialActivity.finish();
                        break;
                }
            }
        });

        dateText = (EditText)findViewById(R.id.editTextDate);
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        dateBtn = (Button) findViewById(R.id.dateBtn);
        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
    }

    //첫 번째 데이터(인적정보) 저장
    void setDataAndChangeViewbyStep1(StepView sv){
        EditText etName = (EditText)findViewById(R.id.editTextPersonName);
        EditText etBirth = (EditText)findViewById(R.id.editTextDate);
        RadioGroup rg = (RadioGroup) findViewById(R.id.rdGroup);
        //todo 예외처리
        try {
            if(etName.getText().toString().length() != 0)
                PreferenceManger.setString(mcontext,"name", etName.getText().toString());
            else
                throw new Exception();
            if(rg.getCheckedRadioButtonId() == (R.id.radioBtnMale))
                PreferenceManger.setString(mcontext,"sex", "Male");
            else if(rg.getCheckedRadioButtonId() == (R.id.radioBtnFemale))
                PreferenceManger.setString(mcontext,"sex", "Female");
            else
             throw new Exception();

            sv.go(++step, true);
            changeView(step);
        }catch (Exception e) {
            etName.setError("이름을 제대로 입력해 주세요.");
        }
    }

    //두 번째 데이터 저장
    void setDataAndChangeViewbyStep2(StepView sv) {
        /*
        //fixme 함수 효율적으로 짜기
        String diseases = "";
        for(int i =0; i<diseasesArr.length-1; i++){
            diseases += diseasesArr[i] + ",";
        }
        diseases += diseasesArr[diseasesArr.length];
        PreferenceManger.setString(mcontext,"disease", diseases);
        */
        sv.go(++step, true);
        changeView(step);
    }

    //세 번째 데이터 저장
    void setDataAndChangeViewbyStep3(StepView sv) {
        PreferenceManger.setBoolean(mcontext, "isTutorialFinished",true);
        sv.done(true);
    }

    //튜토리얼 화면 변경
    private void changeView(int index) {
        // LayoutInflater 초기화.
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        FrameLayout frame = (FrameLayout) findViewById(R.id.stepFrame) ;
        if (frame.getChildCount() > 0) {
            // FrameLayout에서 뷰 삭제.
            frame.removeViewAt(0);
        }

        // XML에 작성된 레이아웃을 View 객체로 변환.
        View view = null ;
        switch (index) {
            case 0 :
                view = inflater.inflate(R.layout.tutorial_first_step, frame, false) ;
                break;
            case 1 :
                view = inflater.inflate(R.layout.tutorial_second_step, frame, false) ;
                /*
                AutoCompleteTextView autoCompleteTV = (AutoCompleteTextView) findViewById(R.id.autoCompleteTV);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_dropdown_item_1line, diseasesData);
                autoCompleteTV.setAdapter(adapter);
                */
                break ;
            case 2 :
                view = inflater.inflate(R.layout.tutorial_third_step, frame, false) ;
                break ;
        }
        // FrameLayout에 뷰 추가
        if (view != null) {
            frame.addView(view) ;
        }
    }

    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }

    public void processDatePickerResult(int year, int month, int day){
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (year_string + "-" + month_string + "-" + day_string);
        dateText.setText(dateMessage);
        dateBtn.setText(dateMessage);
    }
}
