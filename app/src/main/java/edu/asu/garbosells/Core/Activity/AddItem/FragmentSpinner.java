package edu.asu.garbosells.Core.Activity.AddItem;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import edu.asu.garbosells.Core.AddPageFragments.DynamicFragmentContainer;
import edu.asu.garbosells.R;
import edu.asu.garbosells.Template.Recommendation;

public class FragmentSpinner extends Fragment {
    private static final String RECOMMENDATIONS="recommendations";

    List<Recommendation> recommendations;
    public FragmentSpinner() {
        super();
    }

    public static FragmentSpinner newInstance(String recommendations) {
        FragmentSpinner fragment = new FragmentSpinner();
        Bundle args = new Bundle();
        args.putString(RECOMMENDATIONS, recommendations);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View layout = inflater.inflate(R.layout.fragment_input_spinner, container, false);
        Spinner spinner = (Spinner) layout.findViewById(R.id.input_spinner);

        Type RecommendationListType = new TypeToken<List<Recommendation>>(){}.getType();
        recommendations = new Gson().fromJson(getArguments().getString(RECOMMENDATIONS), RecommendationListType);
        Recommendation nullRecommendation = new Recommendation();
        nullRecommendation.id = -1;
        nullRecommendation.description = "Select";
        recommendations.add(0, nullRecommendation);
        ArrayAdapter<Recommendation> recommendationArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, recommendations);
        recommendationArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(recommendationArrayAdapter);
        AttributeFragment parent = (AttributeFragment) getParentFragment();
        spinner.setOnItemSelectedListener(parent);

        return layout;
    }
}
