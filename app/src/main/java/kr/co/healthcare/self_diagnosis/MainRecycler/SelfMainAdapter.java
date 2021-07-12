package kr.co.healthcare.self_diagnosis.MainRecycler;

import android.content.Context;
import android.content.Intent;
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
import kr.co.healthcare.self_diagnosis.SelfDiagnosisActivity;

public class SelfMainAdapter extends RecyclerView.Adapter<SelfMainAdapter.MyViewHolderMain> {

    private ArrayList<SelfMainData> myDataList = new ArrayList<>();
    String[] disease_list = {"고혈압", "골관절염", "고지혈증", "요통/좌골신경통", "당뇨병", "골다공증", "치매"};
    private Context mContext;

    public SelfMainAdapter(Context mContext, ArrayList<SelfMainData> dataList){
        this.mContext = mContext;
        this.myDataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolderMain onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_self_main_container, parent,false);
        return new MyViewHolderMain(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolderMain holder, final int position) {
        holder.tv_diseaseName.setText(myDataList.get(position).getDisease_name());
        holder.tv_numOfQuestions.setText(myDataList.get(position).getNum_of_qeustions()+"문항");

        //리스너
        holder.itemView.setClickable(true);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int pos = holder.getAdapterPosition();  //클릭한 아이템의 포지션 줌
                if (pos != RecyclerView.NO_POSITION){   //포지션이 recyclerView의 item인지 아닌지 확인
                    Intent intent = new Intent(mContext, SelfDiagnosisActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("str", myDataList.get(position).getDisease_name());
                    intent.putExtra("disease_num", myDataList.get(position).getID());
                    mContext.startActivity(intent);
                }
            }
        });
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

            //리스너
            /*
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        // click event
                        Intent intent = new Intent(context, SelfDiagnosisActivity.class);
                        intent.putExtra("str", buttonText);
                        intent.putExtra("disease_num", INDEX);
                        startActivity(intent);
                    }
                }
            });

             */
        }
    }
}