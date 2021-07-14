package kr.co.healthcare.diseaseInfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import kr.co.healthcare.R;


public class Info extends Fragment {

    String text = "";

    public Info(String text){
        this.text = text;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        TextView textView = (TextView) view.findViewById(R.id.description_TV);
        textView.setText(text);
        return view;
    }


}
