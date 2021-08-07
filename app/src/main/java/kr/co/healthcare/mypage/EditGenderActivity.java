package kr.co.healthcare.mypage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import kr.co.healthcare.R;
import kr.co.healthcare.database.UserViewModel;
import kr.co.healthcare.preference.UserInfoPreferenceManger;

public class EditGenderActivity extends AppCompatActivity {
    private UserViewModel viewModel;
    private Boolean manClicked = false;
    private Boolean womanClicked = false;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_gender);

        viewModel = UserViewModel.getINSTANCE();

        final ImageButton manBtn = findViewById(R.id.manBtn);
        final ImageButton womanBtn = findViewById(R.id.womanBtn);
        button = findViewById(R.id.editFinishBtn);

        if(viewModel.getUserGender(this).getValue().equals(UserInfoPreferenceManger.PREF_VALUE_GENDER_WOMAN)) {
            womanBtn.setBackgroundResource(R.drawable.btn_round_selected);
            womanClicked = !womanClicked;
        }
        else if(viewModel.getUserGender(this).getValue().equals(UserInfoPreferenceManger.PREF_VALUE_GENDER_MAN)) {
            manBtn.setBackgroundResource(R.drawable.btn_round_selected);
            manClicked = !manClicked;
        }

        manBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(manClicked) {
                    manBtn.setBackgroundResource(R.drawable.btn_round_not_selected);
                }
                else {
                    if(womanClicked) {
                        womanBtn.setBackgroundResource(R.drawable.btn_round_not_selected);
                        womanClicked = !womanClicked;
                    }
                    manBtn.setBackgroundResource(R.drawable.btn_round_selected);
                }
                manClicked = !manClicked;
                checkGenderBtnClicked();
            }
        });
        womanBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(womanClicked) {
                    womanBtn.setBackgroundResource(R.drawable.btn_round_not_selected);
                }
                else {
                    if(manClicked) {
                        manBtn.setBackgroundResource(R.drawable.btn_round_not_selected);
                        manClicked = !manClicked;
                    }
                    womanBtn.setBackgroundResource(R.drawable.btn_round_selected);
                }
                womanClicked = !womanClicked;
                checkGenderBtnClicked();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender = "";
                if(womanClicked) gender = UserInfoPreferenceManger.PREF_VALUE_GENDER_WOMAN;
                else if(manClicked) gender = UserInfoPreferenceManger.PREF_VALUE_GENDER_MAN;

                EditGenderActivity editGenderActivity = EditGenderActivity.this;
                viewModel.setUserGender(editGenderActivity, gender);
                editGenderActivity.finish();
            }
        });
    }

    private void checkGenderBtnClicked(){
        if(manClicked || womanClicked) {
            button.setEnabled(true);
            button.setBackgroundResource(R.drawable.btn_edit);
        }else {
            button.setEnabled(false);
            button.setBackgroundResource(R.drawable.btn_edit_disable);
        }
    }
}