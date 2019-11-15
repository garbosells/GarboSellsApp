package edu.asu.garbosells.UserManagement.AddPageFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.asu.garbosells.R;

public class MeasurementInfoFragment extends Fragment {
    private View view; //used to call upon components in resource file (onCreateView)
    private Boolean minimized = false; //used to minimize/maximize fragments in CardView when user proceeds to next step
    private static String label = ""; //label will be dynamically changed per item selected from Spinner

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_measurement_info, container, false);
        //return super.onCreateView(inflater, container, savedInstanceState);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public static String getText() {
        return label;
    }

    private void addDynamicFragment(String type, String text) {

    }

    public void removeCurrFragments() {

    }

    private void setupRequiredInputs(String response) {

    }

    private void minimize() {

    }

    private void maximize() {

    }

}
