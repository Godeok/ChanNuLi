package kr.co.healthcare.selfDiagnosis.MainRecycler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kr.co.healthcare.R;
import kr.co.healthcare.selfDiagnosis.SelfDiagnosisActivity;

public class SelfMainAdapter extends RecyclerView.Adapter<SelfMainAdapter.MyViewHolderSelfMain> {

    private ArrayList<SelfMainData> myDataList = new ArrayList<>();
    private Context mContext;

    public SelfMainAdapter(Context mContext, ArrayList<SelfMainData> dataList){
        this.mContext = mContext;
        this.myDataList = dataList;
    }

    //viewHolder가 초기화 될/할 때 실해외는 메소드
    @NonNull
    @Override
    public MyViewHolderSelfMain onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())      //1.부모로부터 받은 context 기반으로 LayoutInflater 설정
                .inflate(R.layout.item_self_main_container, parent,false);  //2.LayoutInflater로 어떤 레이아웃 가져올지 결정

        //위에서 설정한 view를 관리하기 위한 viewHolder를 OnBindViewHolder 반환
       return new MyViewHolderSelfMain(view);
    }

    //리사이클러뷰의 row를 구현하기 위해 bind 할 때 실행
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolderSelfMain holder, final int position) {
        holder.tv_diseaseName.setText(myDataList.get(position).getDisease_name());
        holder.tv_numOfQuestions.setText(myDataList.get(position).getNum_of_questions()+"문항");

        //리스너
        holder.itemView.setClickable(true);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int pos = holder.getAdapterPosition();  //클릭한 아이템의 포지션을 반환
                if (pos != RecyclerView.NO_POSITION){   //포지션이 recyclerView의 item이 맞다면
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

    //질병명, 문항수를 담고있는 뷰홀더 클래스
    public class MyViewHolderSelfMain extends RecyclerView.ViewHolder {
        private TextView tv_diseaseName, tv_numOfQuestions;
        public MyViewHolderSelfMain(@NonNull View itemView) {
            super(itemView);
            tv_diseaseName = itemView.findViewById(R.id.tv_diseaseName);
            tv_numOfQuestions = itemView.findViewById(R.id.tv_numOfQuestions);
        }
    }
}