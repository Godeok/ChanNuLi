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
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import kr.co.healthcare.R;
import kr.co.healthcare.preference.UserInfoPreferenceManger;

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
                nextBtn.setEnabled(checkAgeValidation(editText.getText().toString()));
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    boolean checkAgeValidation(String text){
        if(text.length() == 0) {
            yearTextInputLayout.setError("최소 1글자 이상 입력해야 합니다.");
            return false;
        }else if(isYearDateBeforeThisYear(text)){
            yearTextInputLayout.setError("생년은 올해보다 이전이어야 합니다.");
            return false;
        } else {
            yearTextInputLayout.setError(null);
            return true;
        }
    }

    boolean isYearDateBeforeThisYear(String input){
        return Integer.parseInt(input) < Calendar.getInstance().get(Calendar.YEAR);
    }

    public void setUserYearOfBirth() {
        UserInfoPreferenceManger.setString(context, UserInfoPreferenceManger.PREF_KEY_USER_BIRTH, editText.getText().toString());
    }
}