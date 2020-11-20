package kr.co.healthcare.self;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import kr.co.healthcare.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SelfRecyclerAdapter extends RecyclerView.Adapter<SelfRecyclerAdapter.ViewHolder>{

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


}