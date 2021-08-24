package kr.co.healthcare.mypage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import kr.co.healthcare.R;
import kr.co.healthcare.selfDiagnosis.ResultDB.SelfDiagnosisResultDatabase;

public class DiagnosisHistory extends Fragment {

    private TextView count_TV;
    private TextView level_TV;
    private ProgressBar progressBar;

    private final int index;
    private int max;

    DiagnosisHistory(int index) {
        this.index = index;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history_selfdiagnosis, container, false);
        count_TV = view.findViewById(R.id.tv_count);
        level_TV = view.findViewById(R.id.tv_level);
        progressBar = view.findViewById(R.id.progressBar);

        int count = SelfDiagnosisResultDatabase
                .getInstance(getActivity())
                .resultDAO()
                .getAverageCountOfDisease(index);

        count_TV.setText(Integer.toString(count));

        if (count <5) level_TV.setText(R.string.health_safe);
        else if(count <=5 && count > 3) level_TV.setText(R.string.health_warning);
        else level_TV.setText(R.string.health_danger);

        progressBar.setProgress(count);

        return view;
    }
}