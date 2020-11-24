package kr.co.healthcare.tutorial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import kr.co.healthcare.exception.NoneBirthSelectedException;
import kr.co.healthcare.exception.NoneInputException;
import kr.co.healthcare.exception.NoneGenderSelectedException;
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
    }

    //생년월일 버튼 클릭 시
    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }

    //첫 번째 튜토리얼 완료 버튼 클릭 시
    public void firstStepFinish(View view){
        setDataAndChangeView();
    }

    public void processDatePickerResult(int year, int month, int day){
        PreferenceManger.setInt(this,"year", year);
        PreferenceManger.setInt(this,"month", month+1);
        PreferenceManger.setInt(this,"day", day);
        isBirthChecked = true;

        final Button dateBtn = (Button) findViewById(R.id.dateBtn);
        dateBtn.setText(String.format("%s-%s-%s", Integer.toString(year), Integer.toString(month+1), Integer.toString(day)));
    }

    //이름, 생년월일, 성별 데이터 저장 후 다음 튜토리얼 스텝으로 화면 변경
    private void setDataAndChangeView(){
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
                PreferenceManger.setString(this,"gender", "male");
            else if(rg.getCheckedRadioButtonId() == (R.id.radioBtnFemale))
                PreferenceManger.setString(this,"gender", "female");
            else
                throw new NoneGenderSelectedException();

            changeView();
        }
        catch (NoneInputException e) {
            etName.setError("이름을 한 글자 이상 입력해 주세요.");
        }
        catch (NoneBirthSelectedException e) {
            e.printStackTrace();
        }
        catch (NoneGenderSelectedException e) {
            e.printStackTrace();
        }
    }

    private void changeView(){
        Intent intent = new Intent(getApplicationContext(), Tutorial2StepActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}
