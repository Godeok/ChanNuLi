package kr.co.healthcare.self_diagnosis;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.eazegraph.lib.charts.StackedBarChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.StackedBarModel;

import java.util.ArrayList;
import java.util.List;

import kr.co.healthcare.R;
import kr.co.healthcare.self_diagnosis.ResultDB.Result;
import kr.co.healthcare.self_diagnosis.ResultDB.SelfDiagnosisResultDatabase;
import kr.co.healthcare.self_diagnosis.ResultRecycler.RecyclerAdapter;

public class SelfResultDateFragment extends Fragment {

    //리사이클러뷰
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    RecyclerAdapter adapter;
    List<Result> results;

    //stacked bar graph
    StackedBarChart stackedBarChart;

    View v;

    public SelfResultDateFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_self_result_date, container, false);

        recyclerView = v.findViewById(R.id.rv_self_result);
        stackedBarChart = v.findViewById(R.id.stacked_Bar_Chart);

        //DB에서 정보 가져오기
        initialized();

        //그래프
        initialized_bar_graph();

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        return v;
    }


    private void initialized() {
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        adapter = new RecyclerAdapter();

        results = SelfDiagnosisResultDatabase.getInstance(getActivity().getApplicationContext()).resultDAO().getAllByDate();
        int size = results.size();
        for (int i = 0; i < size; i++) {
            adapter.addItem(results.get(i));
        }
    }

    private void initialized_bar_graph(){

        StackedBarModel[] StackBar = {
                new StackedBarModel("고혈압"), new StackedBarModel("골관절염"),
                new StackedBarModel("고지혈증"), new StackedBarModel("요통"),
                new StackedBarModel("당뇨병"), new StackedBarModel("골다공증"),
                new StackedBarModel("치매")
        };

        for(int i=0; i<7; i++){
            StackBar[i].addBar(new BarModel(SelfDiagnosisResultDatabase.getInstance(getActivity().getApplicationContext()).resultDAO().countDiseaseDanger(i), 0xFFE61919));
            StackBar[i].addBar(new BarModel(SelfDiagnosisResultDatabase.getInstance(getActivity().getApplicationContext()).resultDAO().countDiseaseWarning(i), 0xFFFFC107));
            StackBar[i].addBar(new BarModel(SelfDiagnosisResultDatabase.getInstance(getActivity().getApplicationContext()).resultDAO().countDiseaseSafe(i), 0xFF00AC00));
            stackedBarChart.addBar(StackBar[i]);
        }

        stackedBarChart.startAnimation();
    }

    @Override
    public void onStart() {
        results = SelfDiagnosisResultDatabase.getInstance(getActivity().getApplicationContext()).resultDAO().getAllByDate();
        adapter.addItems((ArrayList) results);
        super.onStart();
    }
}