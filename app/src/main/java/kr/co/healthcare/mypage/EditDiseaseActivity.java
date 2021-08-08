package kr.co.healthcare.mypage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kr.co.healthcare.R;
import kr.co.healthcare.database.UserViewModel;

public class EditDiseaseActivity extends AppCompatActivity {
    UserViewModel viewModel;
    private ChipGroup chipGroup;
    private ArrayList<String> diseases;
    private ArrayList<Boolean> booleans;
    private Button button;
    private String[] tagNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_disease);

        viewModel = UserViewModel.getINSTANCE();

        chipGroup =findViewById(R.id.chipGroup);
        button = findViewById(R.id.editFinishBtn);
        tagNames = getResources().getStringArray(R.array.DISEASES_LABEL);

        booleans = makeBoolArray(viewModel.getUserDiseases(this).getValue());

        //setChip();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditDiseaseActivity editDiseaseActivity = EditDiseaseActivity.this;
                List list = chipGroup.getCheckedChipIds();
                diseases= new ArrayList<String>(list);
                viewModel.setUserDiseases(editDiseaseActivity, diseases);
                editDiseaseActivity.finish();
            }
        });
    }

    private ArrayList<Boolean> makeBoolArray(ArrayList<String> selected){
        ArrayList<Boolean> arrayList = new ArrayList<Boolean>(Arrays.asList(false, false, false, false, false, false, false));
        for(String value: selected){
            arrayList.set(Integer.parseInt(value), true);
        }
        return arrayList;
    }
/*
    private void setChip(){
        for(int i = 0; i <tagNames.length; i++){
            final Chip chip = (Chip) this.getLayoutInflater().inflate(R.layout.layout_chip, chipGroup, false);
            chip.setText(tagNames[i]);
            chip.setChecked(booleans.get(i));
            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(chipGroup.getCheckedChipIds().size() > 0){
                        button.setEnabled(true);
                        button.setBackgroundResource(R.drawable.btn_edit);
                    }else{
                        button.setEnabled(false);
                        button.setBackgroundResource(R.drawable.btn_edit_disable);
                    }
                }
            });
            chipGroup.addView(chip);
        }
    }


 */
}