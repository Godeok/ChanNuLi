package kr.co.healthcare.diseaseInfo.contents;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kr.co.healthcare.R;
import kr.co.healthcare.diseaseInfo.db.DiseaseInfo;

public class DiseaseInfoAdapter extends RecyclerView.Adapter<DiseaseInfoAdapter.ItemViewHolder>{
    private final ArrayList<DiseaseInfoData> arrayList;

    public DiseaseInfoAdapter(DiseaseInfo diseaseInfo){
        arrayList = new ArrayList<DiseaseInfoData>();
        this.arrayList.add(new DiseaseInfoData("정의", diseaseInfo.getDefinition()));
        this.arrayList.add(new DiseaseInfoData("원인", diseaseInfo.getCause()));
        this.arrayList.add(new DiseaseInfoData("증상", diseaseInfo.getSymptom()));
        this.arrayList.add(new DiseaseInfoData("치료", diseaseInfo.getTreatment()));
        if(diseaseInfo.getPrecaution() != null) this.arrayList.add(new DiseaseInfoData("주의사항", diseaseInfo.getPrecaution()));
    }

    @NonNull
    @Override
    public DiseaseInfoAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.disease_info_item,parent,false);
        return new DiseaseInfoAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DiseaseInfoAdapter.ItemViewHolder holder, final int position) {
        holder.TV_title.setText(arrayList.get(position).getTitle());
        holder.TV_contents.setText(arrayList.get(position).getContents());
    }

    @Override
    public int getItemCount() {
        return (null != arrayList? arrayList.size():0);
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        protected TextView TV_title;
        protected TextView TV_contents;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.TV_title = (TextView) itemView.findViewById(R.id.DI_title);
            this.TV_contents = (TextView) itemView.findViewById(R.id.DI_contents);
        }
    }
}

