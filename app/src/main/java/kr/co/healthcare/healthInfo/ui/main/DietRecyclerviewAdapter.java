package kr.co.healthcare.healthInfo.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import kr.co.healthcare.R;

public class DietRecyclerviewAdapter extends RecyclerView.Adapter<DietRecyclerviewAdapter.ItemViewHolder> {

    public ArrayList<DietData> dataArrayList;

    public DietRecyclerviewAdapter(ArrayList<DietData> dataArrayList){
        this.dataArrayList = dataArrayList;
    }

    @NonNull
    @Override
    public DietRecyclerviewAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diet,parent,false);
        return new DietRecyclerviewAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DietRecyclerviewAdapter.ItemViewHolder holder, final int position) {
        holder.title.setText(dataArrayList.get(position).getTitle());
        holder.description.setText(dataArrayList.get(position).getDescription());

        boolean expanded = dataArrayList.get(position).isExpanded();
        holder.linearLayout.setVisibility(expanded ? View.VISIBLE : View.GONE);
        holder.imageView.setImageResource(expanded? R.drawable.ic_baseline_keyboard_arrow_down_24 : R.drawable.ic_baseline_keyboard_arrow_up_24);
    }

    @Override
    public int getItemCount() {
        return (null != dataArrayList? dataArrayList.size():0);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        protected TextView title;
        protected ImageView imageView;
        protected LinearLayout linearLayout;
        protected TextView description;
        protected LinearLayout heading_layout;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.txt_name);
            this.imageView = itemView.findViewById(R.id.description_more);
            this.linearLayout = itemView.findViewById(R.id.layout_expand);
            this.description = itemView.findViewById(R.id.description_TV);
            this.heading_layout = itemView.findViewById(R.id.heading_layout);
            this.heading_layout.setOnClickListener(
                    new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                            System.out.println("클릭함");
                            DietData dietData = dataArrayList.get(getAdapterPosition());
                            dietData.setExpanded(!dietData.isExpanded());
                            notifyItemChanged(getAdapterPosition());
                        }
                    }
            );
        }
    }
}
