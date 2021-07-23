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
import android.widget.ImageButton;

import kr.co.healthcare.R;
import kr.co.healthcare.tutorial.PreferenceManger;

public class TutorialSecondStepFragment extends Fragment {

    private final Context context;

    private ImageButton manBtn;
    private ImageButton womanBtn;
    private final Button nextBtn;

    private Boolean manClicked = false;
    private Boolean womanClicked = false;

    public TutorialSecondStepFragment(Context context, Button btn) {
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
        return inflater.inflate(R.layout.fragment_tutorial_second_step, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        manBtn = getView().findViewById(R.id.manBtn);
        womanBtn = getView().findViewById(R.id.womanBtn);
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
    }

    private void checkGenderBtnClicked(){
        nextBtn.setEnabled(manClicked || womanClicked);
    }

    public void setUserGender() {
        if (womanClicked)
            PreferenceManger.setString(context, PreferenceManger.PREF_USER_GENDER, PreferenceManger.GENDER_VALUE_WOMAN);
        else if (manClicked)
            PreferenceManger.setString(context, PreferenceManger.PREF_USER_GENDER, PreferenceManger.GENDER_VALUE_MAN);
    }
}