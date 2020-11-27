package kr.co.healthcare.tutorial;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kr.co.healthcare.R;

public class DiseasesListAdapter extends RecyclerView.Adapter<DiseasesListAdapter.ItemViewHolder> {

    // adapter에 들어갈 list 입니다.
    private ArrayList<String> diseasesList = new ArrayList<String>();

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.disease_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(diseasesList.get(position));
    }

    // RecyclerView의 총 개수
    @Override
    public int getItemCount() {
        return diseasesList.size();
    }

    // 외부에서 item을 추가시킬 함수
    void addItem(String diseaseName) {
        diseasesList.add(diseaseName);
    }

    //질병 리스트 반환
    public ArrayList<String> getDiseasesList() {
        return diseasesList;
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        ItemViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.diseaseNameTV);
        }

        void onBind(String diseaseName) {
            textView.setText(diseaseName);
        }
    }
}