package kr.co.healthcare.diseaseInfoSelect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import kr.co.healthcare.R;

public class DiseaseInfoSelectActivity extends AppCompatActivity {

    private ArrayList<DiseaseInfoSelectData> arrayList;
    private DiseaseInfoSelectAdapter diseaseInfoSelectAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_info_select);

        recyclerView = (RecyclerView)findViewById(R.id.DIS_RV);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        arrayList = new ArrayList<DiseaseInfoSelectData>();

        setArrayListData();

        diseaseInfoSelectAdapter = new DiseaseInfoSelectAdapter(arrayList);
        recyclerView.setAdapter(diseaseInfoSelectAdapter);
    }

    private ArrayList<DiseaseInfoSelectData> setArrayListData(){
        String [] diseaseName = {
                "고혈압", "골관절염", "류마티스 관절염", "고지혈증", "요통", "당뇨병", "골다공증", "치매"
        };

        for(String name : diseaseName) {
            arrayList.add(new DiseaseInfoSelectData(name));
        }
        return arrayList;
    }
}