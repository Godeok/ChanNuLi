package kr.co.healthcare.mypage.selfdiagnosishistory;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;

import kr.co.healthcare.R;
import kr.co.healthcare.selfDiagnosis.MainRecycler.SelfMainData;
import kr.co.healthcare.selfDiagnosis.QuestionDB.LoadDbClass;
import kr.co.healthcare.selfDiagnosis.SelfDiagnosisActivity;

public class DiagnosisHistoryNoDataFragment extends Fragment{

    private int position;
    private Button button;
    private FragmentActivity fragmentActivity;
    private ArrayList<SelfMainData> dataList = new ArrayList<>();

    DiagnosisHistoryNoDataFragment(int position, FragmentActivity fragmentActivity){
        this.position = position;
        this.fragmentActivity = fragmentActivity;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history_diagnosis_no_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataList = LoadDbClass.InitializeData(fragmentActivity);
        button = getView().findViewById(R.id.btn_do_self_diagnosis);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fragmentActivity, SelfDiagnosisActivity.class);
                intent.putExtra("str", dataList.get(position).getDisease_name());
                intent.putExtra("disease_num", dataList.get(position).getID());
                startActivity(intent);
            }
        });
    }
}