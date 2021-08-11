package kr.co.healthcare.mypage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kr.co.healthcare.R;
import kr.co.healthcare.database.UserViewModel;
import kr.co.healthcare.preference.UserInfoPreferenceManger;

public class EditDiseaseActivity extends AppCompatActivity {
    UserViewModel viewModel;
    private String[] tagNames;
    private  ArrayList<CheckBox> checkBoxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_disease);

        viewModel = UserViewModel.getINSTANCE();
        tagNames = getResources().getStringArray(R.array.DISEASES_LABEL);
        checkBoxes = new ArrayList<>();

        setCheckbox();
    }

    //변경 버튼 눌렀을 때 실행
    public void saveDataAndFinishActivity(View view){
        saveCheckedDisease();
        finishActivity();
    }

    private void setCheckbox(){
        ArrayList<String> diseases = loadCheckedDisease();
        final LinearLayout linearLayout = findViewById(R.id.linearLayout);

        for (String tagName : tagNames) {
            final CheckBox checkBox = (CheckBox) this.getLayoutInflater()
                    .inflate(R.layout.item_checkbox, linearLayout, false);

            checkBox.setText(tagName);
            if(diseases.contains(tagName)) checkBox.setChecked(true);
            checkBoxes.add(checkBox);
            linearLayout.addView(checkBox);
        }
    }

    private ArrayList<String> loadCheckedDisease(){
        return viewModel.getUserDiseases(this).getValue();
    }

    private void saveCheckedDisease(){
        ArrayList<String> checkedDiseases = new ArrayList<>();
        checkBoxes.forEach(checkBox -> {
            if(checkBox.isChecked())
                checkedDiseases.add((String) checkBox.getText());
        });
        viewModel.setUserDiseases(this, checkedDiseases);
    }

    private void finishActivity(){
        EditDiseaseActivity editDiseaseActivity = EditDiseaseActivity.this;
        editDiseaseActivity.finish();
    }
}