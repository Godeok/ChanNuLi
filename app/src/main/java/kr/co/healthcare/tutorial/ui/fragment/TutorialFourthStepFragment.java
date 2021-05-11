package kr.co.healthcare.tutorial.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kr.co.healthcare.R;
import kr.co.healthcare.tutorial.PreferenceManger;

public class TutorialFourthStepFragment extends Fragment {

    //todo: DB에서 질병 이름 불러오기
    private String[] tagNames;
    private final Button nextBtn;
    private final Context context;
    private ChipGroup chipGroup;
    private ArrayList<String> diseases;

    public TutorialFourthStepFragment(Context context, Button btn) {
        this.context = context;
        nextBtn = btn;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tagNames = getResources().getStringArray(R.array.DISEASES_LABEL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tutorial_fourth_step, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setChip();

    }


    private void setChip(){
        chipGroup = getView().findViewById(R.id.chipGroup);
        for (String tagName : tagNames) {
            final Chip chip = (Chip) this.getLayoutInflater().inflate(R.layout.layout_chip, chipGroup, false);
            chip.setText(tagName);
            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(chipGroup.getCheckedChipIds().size() > 0){
                        nextBtn.setEnabled(true);
                        nextBtn.setBackgroundResource(R.drawable.btn_tutorial_step_finished);
                    }else{
                        nextBtn.setEnabled(false);
                        nextBtn.setBackgroundResource(R.drawable.btn_tutorial_step_not_finished);
                    }
                }
            });
            chipGroup.addView(chip);
        }

    }

    public void setUserDiseases() {
        List list = chipGroup.getCheckedChipIds();
        diseases= new ArrayList<String>(list);
        PreferenceManger.setStringArrayList(context, PreferenceManger.PREF_USER_DISEASES, diseases);
    }
}