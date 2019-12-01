package edu.asu.garbosells.Core.AddPageFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import java.util.List;

import edu.asu.garbosells.R;

public class FragmentBoolean extends DynamicFragmentContainer implements View.OnClickListener{

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private String label;
    private boolean selected = false;

    public FragmentBoolean() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_input_boolean, container, false);
        Switch btn = (Switch) view.findViewById(R.id.input_boolean);
        btn.setOnClickListener(this);
        return view;
    }

    @Override //View.OnClickListener interface
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.input_boolean:
                selected = !selected;
                removeCurrFragments();
                if(selected) {
                    //extract response for fragments to display
                    String[] layouts = null, labels = null;
                    //adapterView.getItemAtPosition(pos).toString()
                    layouts = MeasurementInfoFragment.getLayouts("HOODIE").split(";");
                    labels = MeasurementInfoFragment.getLabels("JACKET").split(";");

                    if(layouts == null || labels == null)
                        return;

                    //add in dynamic fragments to display
                    for(int j = 0; j < layouts.length; j++)
                        addDynamicFragment(layouts[j], labels[j]);
                }
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
            case "BOOLEAN":
                dynamicFragment = new FragmentBoolean();
                break;
            case "DOUBLE":
                dynamicFragment = new FragmentDouble();
                break;
            case "INTEGER":
                dynamicFragment = new FragmentInteger();
                break;
            case "SPINNER":
                dynamicFragment = new FragmentSpinner();
                break;
            case "STRING":
                dynamicFragment = new FragmentString();
                break;
            default:
                dynamicFragment = null;
                break;
        }

        if(dynamicFragment == null)
            return;
        fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.boolean_layout, dynamicFragment);
        fragmentTransaction.commit();
    }

    public void removeCurrFragments() {
        fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        List<Fragment> fragments = fragmentManager.getFragments();
        for(Fragment f: fragments){
            fragmentTransaction.remove(f);
        }
        fragmentTransaction.commit();
    }

}
