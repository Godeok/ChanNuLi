package kr.co.healthcare.mypage.selfdiagnosishistory;

import static kr.co.healthcare.selfDiagnosis.ResultDBGlobal.getRange_safe;
import static kr.co.healthcare.selfDiagnosis.ResultDBGlobal.getRange_warning;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import kr.co.healthcare.R;
import kr.co.healthcare.selfDiagnosis.MainRecycler.SelfMainData;
import kr.co.healthcare.selfDiagnosis.QuestionDB.LoadDbClass;
import kr.co.healthcare.selfDiagnosis.ResultDB.ResultDAO;
import kr.co.healthcare.selfDiagnosis.ResultDB.SelfDiagnosisResultDatabase;

public class DiagnosisHistoryFragment extends Fragment {

    private Context context;

    private TextView count_TV;
    private TextView level_TV;
    private ProgressBar progressBar;

    private final int index;
    private int max;

    private ArrayList<History> diagnosisHistoryArr;
    private ArrayList<SelfMainData> dataList = new ArrayList<>();

    DiagnosisHistoryFragment(Context context, int index) {
        this.context = context;
        this.index = index;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        diagnosisHistoryArr = new ArrayList<>();
        dataList = LoadDbClass.InitializeData(context);
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

        diagnosisHistoryArr.add(new History(
                R.string.health_safe,
                dao.countDiseaseSafe(index, getRange_safe(index)),
                ContextCompat.getColor(context, R.color.primaryColor)
                ));
        diagnosisHistoryArr.add(new History(
                R.string.health_warning,
                dao.countDiseaseWarning(index, getRange_safe(index), getRange_warning(index)),
                ContextCompat.getColor(context, R.color.yellowColor)
        ));
        diagnosisHistoryArr.add(new History(
                R.string.health_danger,
                dao.countDiseaseDanger(index, getRange_warning(index)),
                ContextCompat.getColor(context, R.color.redColor)
                ));

        int count = dao.getAverageCountOfDisease(index);
        max = dataList.get(index).getNum_of_questions();
        count_TV.setText("자가 진단을 총 "+ Integer.toString(dao.countDisease(index)) + "회 진행하셨습니다.");

        History mostItem = new History(R.string.health_safe, dao.countDiseaseSafe(index, getRange_safe(index)),ContextCompat.getColor(context, R.color.primaryColor));

        for(History item : diagnosisHistoryArr) {
            if(item.getCount() > mostItem.getCount()) mostItem = item;
        }

        level_TV.setText("검사 결과로 "+getResources().getString(mostItem.getLabel())+ "이(가) 가장 많이 나왔습니다.");
        progressBar.setProgressTintList(ColorStateList.valueOf(mostItem.getColor()));
        progressBar.setProgress(count+1);
        progressBar.setMax(max+1);

        return view;
    }

}