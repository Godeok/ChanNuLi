package kr.co.healthcare.tutorial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import kr.co.healthcare.DatePickerActivity;
import kr.co.healthcare.exception.NoneBirthSelectedException;
import kr.co.healthcare.exception.NoneInputException;
import kr.co.healthcare.exception.NoneSexSelectedException;
import kr.co.healthcare.PreferenceManger;
import kr.co.healthcare.R;

public class Tutorial1StepActivity extends AppCompatActivity {
    private int ACT_SET_BIRTH = 1;
    Button dateBtn;
    private boolean isBirthChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_1_step);

        //생년월일 버튼 클릭 시
        dateBtn = (Button) findViewById(R.id.dateBtn);
        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tutorial1StepActivity.this, DatePickerActivity.class);
                startActivityForResult(intent, ACT_SET_BIRTH);
            }
        });

        //첫 번째 튜토리얼 완료 버튼 클릭 시
        final Button firstStepFinishBtn = (Button) findViewById(R.id.firstStepFinishBtn);
        firstStepFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDataAndChangeView();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //생년월일 데이터 받기
                String result = data.getIntExtra("mYear", 0) + "-"
                        + data.getIntExtra("mMonth", 1) + "-"
                        + data.getIntExtra("mDay", 1);
                dateBtn.setText(result);
                isBirthChecked = true;
            }
        }
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
                throw new NoneInputException();

            //생년월일 예외처리: 날짜를 체크해야 함.
            if(!isBirthChecked) throw new NoneBirthSelectedException();

            //성별 예외처리: 남/여 중 하나는 체크해야 함.
            if(rg.getCheckedRadioButtonId() == (R.id.radioBtnMale))
                PreferenceManger.setString(this,"sex", "Male");
            else if(rg.getCheckedRadioButtonId() == (R.id.radioBtnFemale))
                PreferenceManger.setString(this,"sex", "Female");
            else
                throw new NoneSexSelectedException();

            changeView();
        }
        catch (NoneInputException e) {
            etName.setError("이름을 한 글자 이상 입력해 주세요.");
        }
        catch (NoneBirthSelectedException e) {
            e.printStackTrace();
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
