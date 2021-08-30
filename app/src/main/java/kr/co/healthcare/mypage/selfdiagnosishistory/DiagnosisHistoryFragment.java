package kr.co.healthcare.mypage.selfdiagnosishistory;

import static kr.co.healthcare.selfDiagnosis.ResultDBGlobal.getRange_safe;
import static kr.co.healthcare.selfDiagnosis.ResultDBGlobal.getRange_warning;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import kr.co.healthcare.R;
import kr.co.healthcare.selfDiagnosis.ResultDB.ResultDAO;
import kr.co.healthcare.selfDiagnosis.ResultDB.SelfDiagnosisResultDatabase;

public class DiagnosisHistoryFragment extends Fragment {

    private TextView count_TV;
    private TextView level_TV;
    private ProgressBar progressBar;

    private final int index;
    private int max;

    private ArrayList<History> diagnosisHistoryArr;

    DiagnosisHistoryFragment(int index) {
        this.index = index;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        diagnosisHistoryArr = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history_diagnosis, container, false);
        count_TV = view.findViewById(R.id.tv_count);
        level_TV = view.findViewById(R.id.tv_level);
        progressBar = view.findViewById(R.id.progressBar);

        ResultDAO dao = SelfDiagnosisResultDatabase
                .getInstance(getActivity())
                .resultDAO();

        diagnosisHistoryArr.add(new History(R.string.health_safe, dao.countDiseaseSafe(index, getRange_safe(index))));
        diagnosisHistoryArr.add(new History(R.string.health_warning, dao.countDiseaseWarning(index, getRange_safe(index), getRange_warning(index))));
        diagnosisHistoryArr.add(new History(R.string.health_danger, dao.countDiseaseDanger(index, getRange_warning(index))));

        int count = dao.getAverageCountOfDisease(index);
        max = 20;

        count_TV.setText("자가 진단을 총 "+ Integer.toString(dao.countDisease(index)) + "회 진행하셨습니다.");

        History mostItem = new History(R.string.health_safe, dao.countDiseaseSafe(index, getRange_safe(index)));

        for(History item : diagnosisHistoryArr) {
            if(item.getCount() > mostItem.getCount()) mostItem = item;
        }

        level_TV.setText("검사 결과로 "+getResources().getString(mostItem.getLabel())+ "이(가) 가장 많이 나왔습니다.");

        progressBar.setProgress(count);
        progressBar.setMax(max);

        return view;
    }

}