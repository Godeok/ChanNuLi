package kr.co.healthcare.healthInfo.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

import kr.co.healthcare.R;

public class Search extends Fragment {
    private String[] keywords;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        keywords = getResources().getStringArray(R.array.SEARCH_KEYWORD_LABEL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ChipGroup chipGroup = view.findViewById(R.id.CHIPGROUP_keywords);
        for(String keyword : keywords){
            final Chip chip = (Chip) this.getLayoutInflater().inflate(
                    R.layout.item_chip_search_keyword, chipGroup, false);
            chip.setText(keyword);
            chipGroup.addView(chip);
        }
        return view;
    }
}
