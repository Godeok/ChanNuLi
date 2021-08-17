package kr.co.healthcare.selfDiagnosis;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import kr.co.healthcare.R;
import kr.co.healthcare.selfDiagnosis.ResultDB.Result;
import kr.co.healthcare.selfDiagnosis.ResultDB.ResultDAO;
import kr.co.healthcare.selfDiagnosis.ResultDB.SelfDiagnosisResultDatabase;
import kr.co.healthcare.selfDiagnosis.ResultRecycler.RecyclerAdapter;


public class SelfResultSymptomFragment extends Fragment {

    //리사이클러뷰
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerAdapter recyclerAdapter;
    private List<Result> results;

    //질병 번호
    static int num;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_self_result_symptom, container, false);

        recyclerView = v.findViewById(R.id.rv_self_result);
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        Spinner spinner = v.findViewById(R.id.spinner);

        //스피너
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //스피너 글자 색 변경
                //((TextView)spinner.getChildAt(0)).setTextColor(Color.BLACK);

                //결과 보여줄 리사이클러뷰 어뎁터 선언(새로운 정보 불러오면 항상 새로 선언)
                recyclerAdapter = new RecyclerAdapter();

                //스피너에서 선택된 질병명 -> 숫자로 변환 -> 해당 쿼리 불러오기 -> 리사이클러뷰 어뎁터에 데이터 추가
                num = return_disease_num(parent.getItemAtPosition(position).toString());
                check_query(num);
                int size = results.size();
                for (int i=0; i<size; i++)
                    recyclerAdapter.addItem(results.get(i));

                //리사이클러뷰 화면에 표시하기
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return v;
    }


    //질병명 입력하면 숫자로 반환해주는 함수
    int return_disease_num(String string) {
        String[] disName = {"고혈압", "골관절염", "고지혈증", "요통/좌골신경통", "당뇨병", "골다공증", "치매"};
        for(int i=0; i<7; i++)
            if(string.equals(disName[i]))
                return i;
        return -1;
    }

    //질병에 해당하는 쿼리
    void check_query(int number) {
        ResultDAO dao = SelfDiagnosisResultDatabase.getInstance(getActivity().getApplicationContext()).resultDAO();
        if(number>=0 || number<=6) results = dao.getAllByDisease(number);
        else results = dao.getAllByDate();
    }
}