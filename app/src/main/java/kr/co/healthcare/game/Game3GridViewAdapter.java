package kr.co.healthcare.game;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import kr.co.healthcare.R;

public class Game3GridViewAdapter extends BaseAdapter {

    ArrayList<Game3Item> items;
    Context context;

    public Game3GridViewAdapter(ArrayList<Game3Item> items, Context context){
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.game3_grid_item, viewGroup, false);

        Button btn_card = view.findViewById(R.id.btn_card);
        btn_card.setText(items.get(i).cardNum+"");

        /*
        TextView tv_name = view.findViewById(R.id.textView_name);
        tv_name.setText(items.get(i).name);

        TextView tv_gender = view.findViewById(R.id.textView_age);
        tv_gender.setText(items.get(i).age);
         */

        return view;
    }

}