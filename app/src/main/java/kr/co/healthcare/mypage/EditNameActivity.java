package kr.co.healthcare.mypage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import kr.co.healthcare.R;
import kr.co.healthcare.database.UserViewModel;

public class EditNameActivity extends AppCompatActivity {
    UserViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);

        final EditText editText = findViewById(R.id.editTextTextPersonName);
        final Button button = findViewById(R.id.editFinishBtn);

        viewModel = UserViewModel.getINSTANCE();
        editText.setText(viewModel.getUserName(this).getValue());

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                System.out.println(checkValidation(editText));
                button.setEnabled(checkValidation(editText));
                changeBtn(button, checkValidation(editText));
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditNameActivity editNameActivity = EditNameActivity.this;
                viewModel.setUserName(editNameActivity, editText.getText().toString());
                editNameActivity.finish();
            }
        });
    }

    void changeBtn(Button button, boolean validation){
        if(validation){
            button.setBackgroundResource(R.drawable.btn_edit);
            button.setTextColor(Color.parseColor("#311B92"));
        }else{
            button.setBackgroundResource(R.drawable.btn_edit_disable);
            button.setTextColor(Color.parseColor("#BABABA"));
        }
    }

    boolean checkValidation(EditText editText){
        return !editText.getText().toString().equals("") && editText.getText().length() <= 5;
    }
}