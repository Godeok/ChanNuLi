package kr.co.healthcare.self.Recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kr.co.healthcare.R;
import kr.co.healthcare.self.ResultDB.Result;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<Result> resultData = new ArrayList<>();
    String[]
            disease_list = {"고혈압", "골관절염", "고지혈증", "요통/좌골신경통", "당뇨병", "골다공증", "치매"};

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_self_recycler_item,parent,false);
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
            tv_num.setText(""+s);
            tv_disease.setText(""+disease_list[result.getDisease()]);
            tv_date.setText(""+result.getDate());
            tv_count.setText(""+result.getCount());
        }
    }
}
