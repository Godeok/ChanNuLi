package kr.co.healthcare.tutorial.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.ArrayList;

import kr.co.healthcare.R;
import kr.co.healthcare.preference.UserInfoPreferenceManger;

public class TutorialFourthStepFragment extends Fragment {

    private String[] tagNames;
    private final Button nextBtn;
    private final Context context;
    private  ArrayList<CheckBox> checkBoxes;

    public TutorialFourthStepFragment(Context context, Button btn) {
        this.context = context;
        nextBtn = btn;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tagNames = getResources().getStringArray(R.array.DISEASES_LABEL);
        checkBoxes = new ArrayList<CheckBox>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tutorial_fourth_step, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCheckbox();
        nextBtn.setEnabled(true);
        nextBtn.setBackgroundResource(R.drawable.btn);
    }

    private void setCheckbox(){
        final LinearLayout linearLayout = getView().findViewById(R.id.linearLayout);
        for (String tagName : tagNames) {
            final CheckBox checkBox = (CheckBox) this.getLayoutInflater().inflate(R.layout.item_checkbox, linearLayout, false);
            checkBox.setText(tagName);
            checkBoxes.add(checkBox);
            linearLayout.addView(checkBox);
        }
    }

    public void setUserDiseases() {
        ArrayList<String> datas = new ArrayList<String>();
        for(CheckBox checkBox : checkBoxes) {
            if(checkBox.isChecked()) datas.add((String) checkBox.getText());
        }
        UserInfoPreferenceManger.setStringArrayList(context, UserInfoPreferenceManger.PREF_KEY_USER_DISEASES, datas);
    }
}