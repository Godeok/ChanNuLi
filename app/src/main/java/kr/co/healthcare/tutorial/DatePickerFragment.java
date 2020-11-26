package kr.co.healthcare.tutorial;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import kr.co.healthcare.MainActivity;
import kr.co.healthcare.PreferenceManger;
import kr.co.healthcare.R;
import kr.co.healthcare.tutorial.Tutorial1StepActivity;
import kr.co.healthcare.tutorial.TutorialStartActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    Tutorial1StepActivity activity;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        activity = (Tutorial1StepActivity)getActivity();
        boolean isDateSelected = PreferenceManger.getBoolean(activity, "isDateSelected");
        final Calendar c = Calendar.getInstance();
        int year, month, day;

        if (!isDateSelected) {  //처음 선택할 때: 현재 날짜로 데이터피커 초기값 설정
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);
        }
        else{ // 선택한 적이 있을 때: 이전에 선택한 날짜로 데이터피커 초기값 설정
            year = PreferenceManger.getInt(activity, "year");
            month = PreferenceManger.getInt(activity, "month");
            day = PreferenceManger.getInt(activity, "day");
        }

        DatePickerDialog dialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog,this, year, month, day);
        dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
        return dialog;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        activity.processDatePickerResult(year,month,day);
        PreferenceManger.setBoolean(activity, "isDateSelected",true);
    }
}