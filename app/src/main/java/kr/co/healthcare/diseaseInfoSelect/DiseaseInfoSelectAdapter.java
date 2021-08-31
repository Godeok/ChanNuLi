package kr.co.healthcare.diseaseInfoSelect;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kr.co.healthcare.R;
import kr.co.healthcare.diseaseInfo.DiseaseInfoActivity;

public class DiseaseInfoSelectAdapter extends RecyclerView.Adapter<DiseaseInfoSelectAdapter.ItemViewHolder> {
    private ArrayList<DiseaseInfoSelectData> arrayList;

    public DiseaseInfoSelectAdapter(ArrayList<DiseaseInfoSelectData> arrayList){
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public DiseaseInfoSelectAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_disease_info_select,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DiseaseInfoSelectAdapter.ItemViewHolder holder, final int position) {
        holder.diseaseName.setText(arrayList.get(position).getDiseaseName());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, DiseaseInfoActivity.class);
                intent.putExtra("diseasePosition", position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arrayList? arrayList.size():0);
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        protected TextView diseaseName;
        protected LinearLayout linearLayout;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.diseaseName = (TextView) itemView.findViewById(R.id.txt_name);
            this.linearLayout = itemView.findViewById(R.id.heading_layout);
        }
    }
}
