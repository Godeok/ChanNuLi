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
import kr.co.healthcare.preference.UserInfoPreferenceManger;

public class TutorialSecondStepFragment extends Fragment {

    private final Context context;

    private ImageButton manBtn;
    private ImageButton womanBtn;
    private final Button button;

    public TutorialSecondStepFragment(Context context, Button button) {
        this.context = context;
        this.button = button;
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
        addOnclickListenerToButtons();
    }

    void addOnclickListenerToButtons(){
        manBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                womanBtn.setEnabled(false);
                manBtn.setPressed(!manBtn.isPressed());
                button.setEnabled(manBtn.isPressed() || womanBtn.isPressed());
            }
        });

        womanBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                manBtn.setEnabled(false);
                womanBtn.setPressed(!womanBtn.isPressed());
                button.setEnabled(manBtn.isPressed() || womanBtn.isPressed());
            }
        });
    }

    public void setUserGender() {
        if(womanBtn.isPressed())
            UserInfoPreferenceManger.setString(
                    context,
                    UserInfoPreferenceManger.PREF_KEY_USER_GENDER,
                    UserInfoPreferenceManger.PREF_VALUE_GENDER_WOMAN);

        if(manBtn.isPressed())
            UserInfoPreferenceManger.setString(
                    context,
                    UserInfoPreferenceManger.PREF_KEY_USER_GENDER,
                    UserInfoPreferenceManger.PREF_VALUE_GENDER_MAN);
    }
}