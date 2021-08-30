package kr.co.healthcare.selfDiagnosis;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kr.co.healthcare.R;
import kr.co.healthcare.selfDiagnosis.MainRecycler.SelfMainData;
import kr.co.healthcare.selfDiagnosis.QuestionDB.QuesDataAdapter;
import kr.co.healthcare.selfDiagnosis.QuestionDB.Questions;
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

    ConstraintLayout layout_notice;
    ScrollView scrollView;
    Button btn_go_to;

    //연결 코드
    ArrayList<SelfMainData> dataList = new ArrayList<>();;
    public List<Questions> questionsList;
    String[] disease_list = {"고혈압", "골관절염", "고지혈증", "요통", "당뇨병", "골다공증", "치매"};

    //질병 번호
    static int num;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_self_result_symptom, container, false);

        layout_notice = v.findViewById(R.id.layout_notice);
        scrollView = v.findViewById(R.id.scrollView_self_symptom);
        recyclerView = v.findViewById(R.id.rv_self_result);
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        Spinner spinner = v.findViewById(R.id.spinner_self);
        btn_go_to = v.findViewById(R.id.btn_go_to);

        //스피너
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //스피너 글자 색 변경
                //((TextView)spinner.getChildAt(0)).setTextColor(getResources().getColor(R.color.defaultTextColor));

                //결과 보여줄 리사이클러뷰 어뎁터 선언(새로운 정보 불러오면 항상 새로 선언)
                recyclerAdapter = new RecyclerAdapter();

                //스피너에서 선택된 질병명 -> 숫자로 변환 -> 해당 쿼리 불러오기 -> 리사이클러뷰 어뎁터에 데이터 추가
                num = return_disease_num(parent.getItemAtPosition(position).toString());
                check_query(num);
                int size = results.size();

                setVisibility(size);

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

        //자가진단 페이지 연결
        InitializeData();

        btn_go_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), SelfDiagnosisActivity.class);
                intent.putExtra("str", dataList.get(num).getDisease_name());
                intent.putExtra("disease_num", dataList.get(num).getID());
                startActivity(intent);
            }
        });

        return v;
    }


    //질병명 입력하면 숫자로 반환해주는 함수
    int return_disease_num(String string) {
        String[] disName = {"고혈압", "골관절염", "고지혈증", "요통", "당뇨병", "골다공증", "치매"};
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

    void setVisibility(int size) {
        if (size == 0) {
            scrollView.setVisibility(View.GONE);
            layout_notice.setVisibility(View.VISIBLE);
        } else {
            scrollView.setVisibility(View.VISIBLE);
            layout_notice.setVisibility(View.GONE);
        }
    }

    public void InitializeData(){
        dataList = new ArrayList<>();
        for (int i=0; i<7; i++) {
            initLoadDB(i);
            //dataList에 질병 id, 질병명, 자가진단 문항 수(질문 개수) 저장
            dataList.add(new SelfMainData(i, disease_list[i], questionsList.size()));
        }
    }

    private void initLoadDB(int n){
        QuesDataAdapter mDBHelper = new QuesDataAdapter(getActivity().getApplicationContext());
        mDBHelper.createDatabase();
        mDBHelper.open();
        questionsList = mDBHelper.getTableData(n);
        mDBHelper.close();
    }
}