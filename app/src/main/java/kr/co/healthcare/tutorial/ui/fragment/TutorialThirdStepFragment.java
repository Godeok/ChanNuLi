package kr.co.healthcare.tutorial.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import kr.co.healthcare.R;
import kr.co.healthcare.tutorial.PreferenceManger;

public class TutorialThirdStepFragment extends Fragment {

    private final Context context;
    private Button nextBtn;
    private TextInputLayout yearTextInputLayout;
    private EditText editText;

    public TutorialThirdStepFragment(Context context, Button btn) {
        this.context = context;
        nextBtn = btn;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tutorial_third_step, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        yearTextInputLayout = getView().findViewById(R.id.yearTextInputLayout);
        editText = yearTextInputLayout.getEditText();
        editText.addTextChangedListener(new TextWatcher() {
            //todo: 입력 가능한 날짜 제한
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length() == 0) {
                    nextBtn.setEnabled(false);
                    nextBtn.setBackgroundResource(R.drawable.btn_tutorial_step_not_finished);
                }else{
                    nextBtn.setEnabled(true);
                    nextBtn.setBackgroundResource(R.drawable.btn_tutorial_step_finished);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length() == 0) {
                    yearTextInputLayout.setError("최소 1글자 이상 입력해야 합니다.");
                } else {
                    yearTextInputLayout.setError(null); // null은 에러 메시지를 지워주는 기능
                }
            }
        });
        /*
                Calendar calendar = Calendar.getInstance();
        yearTextInputLayout.setMaxDate(calendar.getTimeInMillis());
        calendar.add(Calendar.YEAR, -130);
         */
    }

    public void setUserYearOfBirth() {
        PreferenceManger.setString(context, PreferenceManger.PREF_USER_YEAR_OF_BIRTH, editText.getText().toString());
    }
}