package kr.co.healthcare.selfDiagnosis.ResultRecycler;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import kr.co.healthcare.R;
import kr.co.healthcare.selfDiagnosis.ResultDB.Result;

import java.util.ArrayList;

import static kr.co.healthcare.selfDiagnosis.ResultDBGlobal.*;
import static kr.co.healthcare.selfDiagnosis.ResultDBGlobal.getRange_warning;

//결과 화면 리사이클러뷰
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Result> resultData = new ArrayList<>();
    String[] disease_list = {"고혈압", "골관절염", "고지혈증", "요통", "당뇨병", "골다공증", "치매"};

    public RecyclerAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_self_diagnosis_result_container,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.onBind(resultData.get(position), position);
    }

    @Override
    public int getItemCount() {
        return resultData.size();
    }

    public void addItem(Result result) {
        resultData.add(result);
    }

    public void addItems(ArrayList<Result> result) {
        resultData = result;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_disease, tv_date, tv_count, tv_num;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_disease = itemView.findViewById(R.id.tv_disease);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_count = itemView.findViewById(R.id.tv_count);
            tv_num = itemView.findViewById(R.id.tv_num);
        }

        public void onBind(final Result result, int position) {
            String s = "" + (position+1);
            int diseaseNum = result.getDisease();

            tv_num.setText(""+s);
            tv_disease.setText(""+disease_list[diseaseNum]);
            tv_date.setText(""+result.getDate());

            //검사 결과 텍스트뷰
            if(result.getCount()>getRange_warning(diseaseNum)) {
                tv_count.setBackgroundColor(ContextCompat.getColor(context, R.color.redColor));
                tv_count.setText("위험");
            }
            else if(result.getCount()>getRange_safe(diseaseNum)) {
                tv_count.setBackgroundColor(ContextCompat.getColor(context, R.color.yellowColor));
                tv_count.setText("주의");
            }
            else {
                tv_count.setBackgroundColor(ContextCompat.getColor(context, R.color.primaryColor));
                tv_count.setText("건강");
            }
        }
    }
}
