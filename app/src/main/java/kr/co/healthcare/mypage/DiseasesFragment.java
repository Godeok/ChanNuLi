package kr.co.healthcare.mypage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

import kr.co.healthcare.R;
import kr.co.healthcare.database.UserViewModel;

public class DiseasesFragment extends Fragment {

    Context context;
    UserViewModel viewModel;

    DiseasesFragment(Context context, UserViewModel viewModel) {
        this.context = context;
        this.viewModel =viewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(viewModel.getUserDiseasesCount() != 0){
            View view = inflater.inflate(R.layout.fragment_mypage_diseases, container, false);
            ChipGroup chipGroup = view.findViewById(R.id.CHIPGROUP_diseases);
            chipGroup.removeAllViews();
            ArrayList<String> diseases = viewModel.getUserDiseases(context).getValue();
            assert diseases != null;
            diseases.forEach(diseaseName -> {
                final Chip chip = (Chip) this.getLayoutInflater().inflate(
                        R.layout.item_chip_disease, chipGroup, false);
                chip.setText(diseaseName);
                chipGroup.addView(chip);
            });
            return view;
        }else return inflater.inflate(R.layout.fragment_mypage_diseases_no, container, false);
    }
}
