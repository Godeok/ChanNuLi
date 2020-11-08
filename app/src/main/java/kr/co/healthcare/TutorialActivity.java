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

import androidx.appcompat.app.AppCompatActivity;

import com.shuhart.stepview.StepView;

import java.util.List;

public class TutorialActivity extends AppCompatActivity {
    //변수
    public static Activity tutorialActivity;
    String[] items = { "SM3", "SM5", "SM7", "SONATA", "AVANTE", "SOUL", "K5", "K7" };
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
                    case 0:
                        setDataAtStep1();
                        step += 1;
                        stepView.go(step, true);
                        break;
                    case 1:
                        //setDataAtStep2();
                        step += 1;
                        stepView.go(step, true);
                        break;
                    case 2:
                        setDataAtStep3();
                        stepView.done(true);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        tutorialActivity.finish();
                        break;
                }
                changeView(step);
            }
        });
    }

    void setDataAtStep1(){
        EditText etName = (EditText)findViewById(R.id.editTextPersonName);
        EditText etYear = (EditText)findViewById(R.id.editTextYear);
        EditText etMonth = (EditText)findViewById(R.id.editTextMonth);
        EditText etDay = (EditText)findViewById(R.id.editTextDay);
        RadioGroup rg = (RadioGroup) findViewById(R.id.rdGroup);

        //입력한 인적정보 받아오기(예외처리 해야 함)
        String name = etName.getText().toString();
        int year = Integer.parseInt(etYear.getText().toString());
        int month = Integer.parseInt(etMonth.getText().toString());
        int day = Integer.parseInt(etDay.getText().toString());
        String sex = "";
        if(rg.getCheckedRadioButtonId() == (R.id.radioBtnMale))
            sex = "Male";
        else
            sex = "Female";

        //입력한 정보 저장
        PreferenceManger.setString(mcontext,"name", name);
        PreferenceManger.setInt(mcontext,"year", year);
        PreferenceManger.setInt(mcontext,"month", month);
        PreferenceManger.setInt(mcontext,"day", day);
        PreferenceManger.setString(mcontext,"sex", sex);
    }

    void setDataAtStep2() { //더 효율적이게 수정해야 함
        String diseases = "";
        for(int i =0; i<diseasesArr.length-1; i++){
            diseases += diseasesArr[i] + ",";
        }
        diseases += diseasesArr[diseasesArr.length];
        PreferenceManger.setString(mcontext,"disease", diseases);
    }

    void setDataAtStep3() {
        PreferenceManger.setBoolean(mcontext, "isTutorialFinished",true);
    }

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
                break ;
            case 1 :
                view = inflater.inflate(R.layout.tutorial_second_step, frame, false) ;
                //AutoCompleteTextView autoCompleteTV = (AutoCompleteTextView) findViewById(R.id.autoCompleteTV);
                //autoCompleteTV.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items));
                break ;
            case 2 :
                view = inflater.inflate(R.layout.tutorial_third_step, frame, false) ;
                break ;
        }
        // FrameLayout에 뷰 추가.
        if (view != null) {
            frame.addView(view) ;
        }
    }
}
