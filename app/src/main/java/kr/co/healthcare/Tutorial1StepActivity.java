package kr.co.healthcare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class Tutorial1StepActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_1_step);

        //첫 번째 튜토리얼 완료 시
        final Button firstStepFinishBtn = (Button) findViewById(R.id.firstStepFinishBtn);
        firstStepFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDataAndChangeView();
            }
        });
    }

    //이름, 생년월일, 성별 데이터 저장 후 다음 튜토리얼 스텝으로 화면 변경

    void setDataAndChangeView(){
        EditText etName = (EditText)findViewById(R.id.editTextPersonName);
        RadioGroup rg = (RadioGroup) findViewById(R.id.rdGroup);
        try {
            //이름 예외처리: 최소 1글자 이상이여야 함.
            if(etName.getText().toString().length() != 0)
                PreferenceManger.setString(this,"name", etName.getText().toString());
            else
                throw new NameCountZeroException();

            //TODO : 생년월일 예외처리: 날짜를 하나 이상 체크해야 함.

            //성별 예외처리: 남/여 중 하나는 체크해야 함.
            if(rg.getCheckedRadioButtonId() == (R.id.radioBtnMale))
                PreferenceManger.setString(this,"sex", "Male");
            else if(rg.getCheckedRadioButtonId() == (R.id.radioBtnFemale))
                PreferenceManger.setString(this,"sex", "Female");
            else
                throw new NoneSexSelectedException();

            changeView();
        }
        catch (NameCountZeroException e) {
            etName.setError("이름을 한 글자 이상 입력해 주세요.");
        }
        catch (NoneSexSelectedException e) {
            e.printStackTrace();
        }
    }

    void changeView(){
        Intent intent = new Intent(getApplicationContext(), Tutorial2StepActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}
