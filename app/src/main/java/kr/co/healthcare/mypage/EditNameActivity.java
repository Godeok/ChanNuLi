package kr.co.healthcare.mypage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import kr.co.healthcare.R;
import kr.co.healthcare.self_diagnosis.SelfMainActivity;
import kr.co.healthcare.tutorial.PreferenceManger;

public class EditNameActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);

        editText = findViewById(R.id.editTextTextPersonName);
        final Button button = findViewById(R.id.editFinishBtn);

        editText.setText(PreferenceManger.getString(EditNameActivity.this, PreferenceManger.PREF_USER_NAME));
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                button.setEnabled(!editText.getText().toString().equals(""));
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditNameActivity editNameActivity = EditNameActivity.this;
                saveUserName();
                editNameActivity.finish();
            }
        });
    }

    public void saveUserName(){
        PreferenceManger.setString(this, PreferenceManger.PREF_USER_NAME, editText.getText().toString());
    }
}