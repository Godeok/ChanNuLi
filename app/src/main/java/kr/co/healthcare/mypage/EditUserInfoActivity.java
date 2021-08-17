package kr.co.healthcare.mypage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import kr.co.healthcare.R;
import kr.co.healthcare.database.UserViewModel;
import kr.co.healthcare.preference.UserInfoPreferenceManger;

public class EditUserInfoActivity extends AppCompatActivity {
    private UserViewModel viewModel;

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
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    boolean checkNameValidation(EditText editText){
        return !editText.getText().toString().equals("") && editText.getText().length() <= 5;
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

    boolean checkAgeValidation(String year){
        String regExp = "^[0-9]+$";
        return year.matches(regExp);
    }
}