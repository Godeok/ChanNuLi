package kr.co.healthcare.self_diagnosis.MainRecycler;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kr.co.healthcare.R;
import kr.co.healthcare.self_diagnosis.MainRecycler.SelfMainData;

public class SelfMainAdapter extends RecyclerView.Adapter<SelfMainAdapter.MyViewHolderMain> {

    private ArrayList<SelfMainData> myDataList = new ArrayList<>();
    String[] disease_list = {"고혈압", "골관절염", "고지혈증", "요통/좌골신경통", "당뇨병", "골다공증", "치매"};

    public SelfMainAdapter(ArrayList<SelfMainData> dataList){
        myDataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolderMain onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_self_main_container, parent,false);
        return new MyViewHolderMain(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderMain holder, int position) {
        //holder.onBind(resultData.get(position), position);

        holder.tv_diseaseName.setText(myDataList.get(position).getDisease_name());
        holder.tv_numOfQuestions.setText(myDataList.get(position).getNum_of_qeustions()+"문항");
    }

    @Override
    public int getItemCount() {
        return myDataList.size();
    }

    /*
    public void addItem(Result result) {
        resultData.add(result);
    }

    public void addItems(ArrayList<Result> result) {
        resultData = result;
    }

     */

    public class MyViewHolderMain extends RecyclerView.ViewHolder {
        private TextView tv_diseaseName, tv_numOfQuestions;

        public MyViewHolderMain(@NonNull View itemView) {
            super(itemView);

            tv_diseaseName = itemView.findViewById(R.id.tv_diseaseName);
            tv_numOfQuestions = itemView.findViewById(R.id.tv_numOfQuestions);
        }
    }
}