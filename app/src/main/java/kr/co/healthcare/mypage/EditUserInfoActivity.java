package kr.co.healthcare.mypage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.textfield.TextInputLayout;

import kr.co.healthcare.R;
import kr.co.healthcare.database.UserViewModel;
import kr.co.healthcare.preference.UserInfoPreferenceManger;

public class EditUserInfoActivity extends AppCompatActivity {
    private UserViewModel viewModel;

    private TextInputLayout nameInputLayout;
    private TextInputLayout yearInputLayout;
    private EditText nameEditText;
    private EditText ageEditText;
    private ImageButton manBtn;
    private ImageButton womanBtn;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_info);

        viewModel = UserViewModel.getINSTANCE();

        nameInputLayout = findViewById(R.id.nameTextInputLayout);
        yearInputLayout = findViewById(R.id.yearTextInputLayout);
        nameEditText = findViewById(R.id.editTextTextPersonName);
        ageEditText = findViewById(R.id.editTextTextYear);
        manBtn = findViewById(R.id.manBtn);
        womanBtn = findViewById(R.id.womanBtn);
        button = findViewById(R.id.completeBtn);

        setNameEditText();
        setGender();
        setAgeEditText();

        addTextChangeListenerToNameEditText();
        addOnclickListenerToButtons();
        addTextChangeListenerToAgeEditText();
    }

    //저장
    public void saveUserInfo(View view){
        EditUserInfoActivity editUserInfoActivity = EditUserInfoActivity.this;

        viewModel.setUserName(editUserInfoActivity,
                nameEditText.getText().toString());

        if(womanBtn.isSelected())
            viewModel.setUserGender(editUserInfoActivity,
                    UserInfoPreferenceManger.PREF_VALUE_GENDER_WOMAN);

        if(manBtn.isSelected())
            viewModel.setUserGender(editUserInfoActivity,
                    UserInfoPreferenceManger.PREF_VALUE_GENDER_MAN);

        viewModel.setUserBirthYear(editUserInfoActivity,
                ageEditText.getText().toString());

        editUserInfoActivity.finish();
    }

    //이름
    void setNameEditText(){
        nameEditText.setText(viewModel.getUserName(this).getValue());
    }

    void addTextChangeListenerToNameEditText(){
        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                button.setEnabled(checkNameValidation(nameEditText));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    boolean checkNameValidation(EditText editText){
        if (editText.getText().toString().contains("#")) {
            nameInputLayout.setError("특수 문자는 사용할 수 없습니다.");
            return false;
        }else if(editText.getText().toString().length() == 0) {
            nameInputLayout.setError("최소 1글자 이상 입력해야 합니다.");
            return false;
        }else if(editText.getText().toString().length() > 5){
            nameInputLayout.setError("최대 글자 수를 초과했습니다.");
            return false;
        } else {
            nameInputLayout.setError(null);
            return true;
        }
    }

    //성별
    void setGender(){
        if(UserInfoPreferenceManger.PREF_VALUE_GENDER_WOMAN.equals(viewModel.getUserGender(this).getValue()))
            womanBtn.setSelected(true);
        else if(UserInfoPreferenceManger.PREF_VALUE_GENDER_MAN.equals(viewModel.getUserGender(this).getValue()))
            manBtn.setSelected(true);
    }

    void addOnclickListenerToButtons(){
        manBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                womanBtn.setSelected(false);
                manBtn.setSelected(!manBtn.isSelected());
                button.setEnabled(manBtn.isSelected() || womanBtn.isSelected());
            }
        });

        womanBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                manBtn.setSelected(false);
                womanBtn.setSelected(!womanBtn.isSelected());
                button.setEnabled(manBtn.isSelected() || womanBtn.isSelected());
            }
        });
    }

    //나이
    void setAgeEditText() {
        ageEditText.setText(viewModel.getUserBirthYear(this).getValue());
    }

    void addTextChangeListenerToAgeEditText(){
        ageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                button.setEnabled(checkAgeValidation(ageEditText.getText().toString()));
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
    //TODO: 유효검사 수정 필요
    boolean checkAgeValidation(String text){
        if(text.length() == 0) {
            yearInputLayout.setError("최소 1글자 이상 입력해야 합니다.");
            return false;
        }else if(text.length() > 5){
            yearInputLayout.setError("");
            return false;
        } else {
            yearInputLayout.setError(null);
            return true;
        }
    }
}