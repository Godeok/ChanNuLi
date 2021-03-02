package kr.co.healthcare.diseaseInfoSelect;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kr.co.healthcare.R;

public class DiseaseInfoSelectAdapter extends RecyclerView.Adapter<DiseaseInfoSelectAdapter.ItemViewHolder> {
    private ArrayList<DiseaseInfoSelectData> arrayList;

    public DiseaseInfoSelectAdapter(ArrayList<DiseaseInfoSelectData> arrayList){
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public DiseaseInfoSelectAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.disease_info_select_item,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DiseaseInfoSelectAdapter.ItemViewHolder holder, final int position) {
        holder.Btn_diseaseName.setText(arrayList.get(position).getDiseaseName());
        //TODO: 더 효율적인 방식으로 변경
        /*
        holder.Btn_diseaseName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, DiseaseInfoActivity.class);
                intent.putExtra("diseasePosition", position);
                context.startActivity(intent);
            }
        });

         */
    }

    @Override
    public int getItemCount() {
        return (null != arrayList? arrayList.size():0);
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        protected Button Btn_diseaseName;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.Btn_diseaseName = (Button) itemView.findViewById(R.id.DIS_name);
        }
    }
}
