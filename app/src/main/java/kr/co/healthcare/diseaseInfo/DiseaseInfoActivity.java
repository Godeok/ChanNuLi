package kr.co.healthcare.diseaseInfo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

import kr.co.healthcare.R;
import kr.co.healthcare.diseaseInfo.category.DiseaseInfoCategoryAdapter;
import kr.co.healthcare.diseaseInfo.contents.DiseaseInfoAdapter;
import kr.co.healthcare.diseaseInfo.db.DiseaseInfo;
import kr.co.healthcare.diseaseInfo.db.DiseaseInfoDB;
import kr.co.healthcare.diseaseInfoSelect.DiseaseInfoSelectAdapter;
import kr.co.healthcare.diseaseInfoSelect.DiseaseInfoSelectData;

public class DiseaseInfoActivity extends AppCompatActivity {

    private DiseaseInfo disInfo;

    private DiseaseInfoAdapter adpater_DI;
    private RecyclerView recyclerView_DI;
    private LinearLayoutManager linear_DI;

    private final String[] CATEGORY_NAMES = new String[]{"정의", "원인", "증상", "치료", "주의사항"};
    private ChipGroup chipGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_info);

        //질병 정보 초기화
        initDiseaseInfo();

        //actionBar title 설정
        this.setTitle(disInfo.getName());

        setChip();

        //질병 관련 정보
        recyclerView_DI = (RecyclerView)findViewById(R.id.DI_RV_info);
        linear_DI = new LinearLayoutManager(this);
        recyclerView_DI.setLayoutManager(linear_DI);
        adpater_DI = new DiseaseInfoAdapter(disInfo);
        recyclerView_DI.setAdapter(adpater_DI);
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

    private void setChip(){
        //chipgroup 초기화
        chipGroup = findViewById(R.id.chipgroup);
        for (String category : CATEGORY_NAMES) {
            final Chip chip = (Chip) this.getLayoutInflater().inflate(R.layout.layout_disease_info_chip, chipGroup, false);
            chip.setText(category);
            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerView_DI.smoothScrollToPosition(chipGroup.getCheckedChipId());
                }
            });
            chipGroup.addView(chip);
        }
        chipGroup.check(0);
    }
}