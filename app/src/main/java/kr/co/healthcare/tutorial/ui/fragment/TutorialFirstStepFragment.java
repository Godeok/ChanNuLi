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

import java.util.regex.Pattern;

import kr.co.healthcare.R;
import kr.co.healthcare.preference.UserInfoPreferenceManger;

public class TutorialFirstStepFragment extends Fragment {

    private TextInputLayout nameInputLayout;
    private EditText editText;
    private final Button nextBtn;
    private final Context context;

    public TutorialFirstStepFragment(Context context, Button btn) {
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
        return inflater.inflate(R.layout.fragment_tutorial_first_step, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nameInputLayout = getView().findViewById(R.id.nameTextInputLayout);
        editText = nameInputLayout.getEditText();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nextBtn.setEnabled(checkNameValidation(editText.getText().toString()));
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    boolean checkNameValidation(String name){
        if (name.matches("^.*[!@#$%^&*(),.?\":{}|<>]+.*$")) {
            nameInputLayout.setError("특수 문자는 사용할 수 없습니다.");
            return false;
        }else if(name.length() == 0) {
            nameInputLayout.setError("최소 1글자 이상 입력해야 합니다.");
            return false;
        }else if(name.length() > 5){
            nameInputLayout.setError("최대 글자 수를 초과했습니다.");
            return false;
        } else {
            nameInputLayout.setError(null);
            return true;
        }
    }

    public void setUserName(){
        UserInfoPreferenceManger.setString(context, UserInfoPreferenceManger.PREF_KEY_USER_NAME, editText.getText().toString());
    }

}