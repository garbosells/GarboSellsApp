package edu.asu.garbosells.UserManagement.AddPageFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
        setupSpinner((Spinner)view.findViewById(R.id.spinnerSection));
        //BUTTON 'CONTINUE' -> MINIMIZE FRAGMENT
        Button button = (Button) view.findViewById(R.id.buttonContinue);
        button.setOnClickListener(this);
        //VIEW 'SELECTED/CLICKED' -> MAXIMIZE FRAGMENT
        view.setOnClickListener(this);

        //TESTING DYNAMICISM OF FRAGMENTS
        addDynamicFragment("DOUBLE", "TEST");
        addDynamicFragment("DOUBLE", "TESTING");
        addDynamicFragment("DOUBLE", "AGAIN");

        addDynamicFragment("BOOLEAN", "TEST");
        addDynamicFragment("BOOLEAN", "TESTING");
        addDynamicFragment("BOOLEAN", "AGAIN");
        return view;
    }

    @Override //Fragment superclass
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override //View.OnClickListener interface
    public void onClick(View view) {
        switch(view.getId()) {
            //button
            case R.id.buttonContinue:
            case R.id.fragment_measurement_info:
                if(!minimized)
                    minimize();
                else
                    maximize();
                break;
            default:
                break;

        }
    }

    private void addDynamicFragment(String type, String text) {
        label = text;
        DynamicFragmentContainer dynamicFragment;
        //LOGIC THAT DISPLAYS TYPE OF EXPECTED USER INPUT
        switch(type) {
            case "DOUBLE":
                dynamicFragment = new FragmentDouble();
                break;
            case "BOOLEAN":
                dynamicFragment = new FragmentBoolean();
                break;
            default:
                dynamicFragment = null;
                break;
        }

        if(dynamicFragment == null)
            return;
        //SET UP DYNAMIC ID: DYNAMICFRAGMENT

        //String tag = "dynamic: " + dynamicFragment.getLocalID();
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.add_measurement_fragments, dynamicFragment);
        fragmentTransaction.commit();
    }

    public void removeCurrFragments() {
            FragmentManager fm = getChildFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            List<Fragment>fragments = fm.getFragments();
            for(Fragment f: fragments){
                ft.remove(f);
            }
            ft.commit();
    }

    private void setupRequiredInputs(String response) {

    }

    private void minimize() {
        minimized = true;
        //HIDE SPINNER

        //HIDE NEW MEASUREMENT BUTTON
        view.findViewById(R.id.buttonNew).setVisibility(view.GONE);
        view.findViewById(R.id.buttonContinue).setVisibility(view.GONE);

        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        List<Fragment> test = fm.getFragments();
        for(Fragment f: test){
            ft.hide(f);
        }
        ft.commit();
    }

    private void maximize() {
        minimized = false;

        view.findViewById(R.id.buttonNew).setVisibility(view.VISIBLE);
        view.findViewById(R.id.buttonContinue).setVisibility(view.VISIBLE);

        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        List<Fragment> test = fm.getFragments();
        for(Fragment f: test){
            ft.show(f);
        }
        ft.commit();
    }

    //--------------- SPINNER SET UP --------------------

    //Method utilizes AdapterView interface (OnItemSelectedListener)
    private void setupSpinner(Spinner spinner) {
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
    }

    @Override //AdapterView.OnItemSelectedListener interface
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override //AdapterView.OnItemSelectedListener interface
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //-------------- END SPINNER ----------------
}
