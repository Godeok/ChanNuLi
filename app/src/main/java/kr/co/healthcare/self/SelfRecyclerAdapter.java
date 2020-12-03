package kr.co.healthcare.self;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import kr.co.healthcare.R;
import kr.co.healthcare.self.resultDB.Result;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SelfRecyclerAdapter extends RecyclerView.Adapter<SelfRecyclerAdapter.ViewHolder>{

    List<Result> results = new ArrayList<>();

    public SelfRecyclerAdapter(List<Result> results){
        this.results = results;
    }

    @NonNull
    @Override
    public SelfRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelfRecyclerAdapter.ViewHolder holder, int position) {
        holder.tv_disease.setText(results.get(position).getDisease());
        holder.tv_date.setText(results.get(position).getDate());
        holder.tv_count.setText(results.get(position).getCount());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void setResults(List<Result> results){
        this.results = results;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_disease;
        private TextView tv_date;
        private TextView tv_count;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_disease = itemView.findViewById(R.id.tv_disease);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_count = itemView.findViewById(R.id.tv_count);
        }
    }










    /* 처음 만든 리사이클러. 여기서부터 맨 아래까지
    private ArrayList<String> mData = null;

    //아이템 뷰 저장하는 뷰홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView date, result;

        ViewHolder(View itemView){
            super(itemView);
            date = itemView.findViewById(R.id.date);
            //result = itemView.findViewById(R.id.result);
        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    SelfRecyclerAdapter(ArrayList<String> list) {
        mData = list ;
    }


    @NonNull
    @Override
    public SelfRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.activity_recycler_item, parent, false) ;
        SelfRecyclerAdapter.ViewHolder vh = new SelfRecyclerAdapter.ViewHolder(view) ;

        return vh ;
    }

    @Override
    public void onBindViewHolder(@NonNull SelfRecyclerAdapter.ViewHolder holder, int position) {
        String text = mData.get(position) ;
        holder.date.setText(text);
        //holder.result.setText(text);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
*/
}