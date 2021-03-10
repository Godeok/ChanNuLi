package kr.co.healthcare.diseaseInfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import kr.co.healthcare.R;
import kr.co.healthcare.diseaseInfo.category.DiseaseInfoCategoryAdapter;
import kr.co.healthcare.diseaseInfo.contents.DiseaseInfoAdapter;
import kr.co.healthcare.diseaseInfo.db.DiseaseInfo;
import kr.co.healthcare.diseaseInfo.db.DiseaseInfoDB;

public class DiseaseInfoActivity extends AppCompatActivity {

    private TextView diseaseNameTV;
    private DiseaseInfo disInfo;

    private DiseaseInfoCategoryAdapter adpater_DIC;
    private RecyclerView recyclerView_DIC;
    private LinearLayoutManager linear_DIC;

    private DiseaseInfoAdapter adpater_DI;
    private RecyclerView recyclerView_DI;
    private LinearLayoutManager linear_DI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_info);

        //질병 정보 초기화
        initDiseaseInfo();

        //질병 이름
        diseaseNameTV = (TextView)findViewById(R.id.DI_name);
        diseaseNameTV.setText(disInfo.getName());

        //질병 관련 정보
        recyclerView_DI = (RecyclerView)findViewById(R.id.DI_RV_info);
        linear_DI = new LinearLayoutManager(this);
        recyclerView_DI.setLayoutManager(linear_DI);
        adpater_DI = new DiseaseInfoAdapter(disInfo);
        recyclerView_DI.setAdapter(adpater_DI);

        //카데코리 버튼
        recyclerView_DIC = (RecyclerView)findViewById(R.id.DI_RV_category);
        linear_DIC = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_DIC.setLayoutManager(linear_DIC);
        adpater_DIC = new DiseaseInfoCategoryAdapter(recyclerView_DI);
        recyclerView_DIC.setAdapter(adpater_DIC);
    }

    //선택한 질병 정보 초기화
    private void initDiseaseInfo() {
        Intent intent = getIntent();
        int position = intent.getExtras().getInt("diseasePosition");
        //디비생성
        DiseaseInfoDB db = DiseaseInfoDB.getAppDatabase(this);
        disInfo = db.infoDao().loadByIds(position+1);
        db.close();
    }
}