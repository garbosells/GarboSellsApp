package edu.asu.garbosells.UserManagement.AddPageFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import edu.asu.garbosells.R;

public class MeasurementInfoFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
        private View view; //used to call upon components in resource file (onCreateView)
        private Boolean minimized = false; //used to minimize/maximize fragments in CardView when user proceeds to next step
        private static String label = ""; //label will be dynamically changed per item selected from Spinner

        public static String getText() { return label; }

    @Nullable
    @Override //Fragment superclass
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_measurement_info, container, false);

        //SET UP SPINNER
        Spinner spinner = (Spinner)view.findViewById(R.id.spinnerSection);
        //CREATE ARRAY LIST<STRING>                     <---- MAKE CALL TO DATABASE TO RETRIEVE ARRAY LIST<STRING>
        ArrayList<String> list = new ArrayList<String>();
        list.add("temp");
        list.add("test");
        //SET UP ADAPTER
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        //SET SPINNER ON CLICK LISTENER
        spinner.setOnItemSelectedListener(this);
//INITIAL PROMPTS TO USER

        //BUTTON 'CONTINUE' -> MINIMIZE FRAGMENT
        Button button = (Button) view.findViewById(R.id.buttonContinue);
        button.setOnClickListener(this);
        //VIEW 'SELECTED/CLICKED' -> MAXIMIZE FRAGMENT
        view.setOnClickListener(this);
        return view;
    }

    @Override //Fragment superclass
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override //View.OnClickListener interface
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.buttonContinue:
        }
    }

    @Override //AdapterView.OnItemSelectedListener interface
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override //AdapterView.OnItemSelectedListener interface
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

        private void addDynamicFragment(String type, String text) {

        }

        public void removeCurrFragments() {

        }

        private void setupRequiredInputs(String response) {

        }

        private void minimize() {
            minimized = true;
            //HIDE SPINNER

            //HIDE NEW MEASUREMENT BUTTON
        }

        private void maximize() {
            minimized = false;

            
        }
}
