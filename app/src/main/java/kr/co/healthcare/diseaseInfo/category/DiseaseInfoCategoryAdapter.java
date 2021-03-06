package kr.co.healthcare.diseaseInfo.category;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kr.co.healthcare.R;

public class DiseaseInfoCategoryAdapter extends RecyclerView.Adapter<DiseaseInfoCategoryAdapter.ItemViewHolder>{
    private ArrayList<String> arrayList;
    private RecyclerView recyclerView;
    public DiseaseInfoCategoryAdapter(RecyclerView recyclerView){
        //TODO: 룸에서 데이터 불러와서 구성하기
        //this.arrayList = arrayList;
        arrayList = new ArrayList<String>();
        this.recyclerView = recyclerView;
        this.arrayList.add("정의");
        this.arrayList.add("원인");
        this.arrayList.add("증상");
        this.arrayList.add("치료");
        this.arrayList.add("주의사항");
    }

    @NonNull
    @Override
    public DiseaseInfoCategoryAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.disease_info_btn_item,parent,false);
        return new DiseaseInfoCategoryAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DiseaseInfoCategoryAdapter.ItemViewHolder holder, final int position) {
        System.out.println("onBindViewHolder");
        holder.button.setText(arrayList.get(position));
        //todo: 더 효율적인 방식으로 변경
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo: 버튼 클릭 시 해당 내용으로 자동 스크롤
                recyclerView.smoothScrollToPosition(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arrayList? arrayList.size():0);
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        protected Button button;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            System.out.println("ItemViewHolder");
            this.button = (Button) itemView.findViewById(R.id.DI_category_btn);
        }
    }
}

