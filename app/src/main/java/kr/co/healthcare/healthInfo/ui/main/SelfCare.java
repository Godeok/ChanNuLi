package kr.co.healthcare.healthInfo.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kr.co.healthcare.R;

public class SelfCare extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private ArrayList<HealthInfoData> arrayList;

    public SelfCare(ArrayList<HealthInfoData> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_self_care, container, false);
        recyclerView = view.findViewById(R.id.RV_self_care);
        HealthInfoAdapter recyclerViewAdapter = new HealthInfoAdapter(getContext(), arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        return view;
    }
}