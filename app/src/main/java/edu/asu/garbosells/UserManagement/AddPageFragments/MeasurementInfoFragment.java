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
        return view;
    }

    @Override //Fragment superclass
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override //View.OnClickListener interface
    public void onClick(View view) {
        switch(view.getId()) {
            //button continue
            case R.id.buttonContinue:
                if(!minimized)
                    minimize();
                break;
            //entire fragment section (wiz creation step) selected
            case R.id.fragment_measurement_info:
                if(minimized)
                    maximize();
                break;
            default:
                break;

        }
    }

    private void addDynamicFragment(String type, String text) {
        label = text;
        DynamicFragmentContainer dynamicFragment;
        //-------------- LOGIC THAT DISPLAYS TYPE OF EXPECTED USER INPUT --------------
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
        //HIDE BUTTONS
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

    //-------------- SPINNER SET UP --------------

    //Method utilizes AdapterView interface (OnItemSelectedListener)
    private void setupSpinner(Spinner spinner) {
        //CREATE ARRAY LIST<STRING>
        ArrayList<String> list = new ArrayList<String>(); //<---- MAKE CALL TO DATABASE TO RETRIEVE ARRAY LIST<STRING>
        list.add("HOODIE");
        list.add("JACKET");
        list.add("TSHIRT");
        //SET UP ADAPTER
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        //SET SPINNER ON CLICK LISTENER
        spinner.setOnItemSelectedListener(this);
    }

    //-------------- SIMULATE CALL TO DATABASE FOR DYNAMIC LAYOUT --------------
    @Override //AdapterView.OnItemSelectedListener interface
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long row_id) {
        //remove current fragments when spinner item is selected before adding in new dynamic fragments
        removeCurrFragments();

        //extract response for fragments to display
        String[] layouts = null, labels = null;
        //adapterView.getItemAtPosition(pos).toString()
        layouts = getLayouts(adapterView.getItemAtPosition(pos).toString()).split(";");
        labels = getLabels(adapterView.getItemAtPosition(pos).toString()).split(";");

        if(layouts == null || labels == null)
            return;

        //add in dynamic fragments to display
        for(int j = 0; j < layouts.length; j++)
            addDynamicFragment(layouts[j], labels[j]);

    }

    @Override //AdapterView.OnItemSelectedListener interface
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //-------------- END SPINNER ----------------

    //-------------- MOCK DATABASE CALLS - ENCAPSULATION --------------
    private String getLayouts(String type) {
            switch(type) {
                case "HOODIE":
                    return "BOOLEAN;DOUBLE;DOUBLE;";
                case "JACKET":
                    return "DOUBLE;BOOLEAN;BOOLEAN";
                default:
                    return "DOUBLE;BOOLEAN;DOUBLE;BOOLEAN;DOUBLE";
            }
    }

    private String getLabels(String type) {
            switch(type) {
                case "HOODIE":
                    return "Strings attached;Arm length;Chest length";
                case "JACKET":
                    return "Arm length;Zipper;Hoodie";
                default:
                    return "Dimension 1 length;Strings;Dimension 2 length;Zipper;Dimension 3 length";
            }
    }

}
