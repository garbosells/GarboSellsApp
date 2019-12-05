package edu.asu.garbosells.Core.Activity.AddItem;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import edu.asu.garbosells.Item.ItemAttribute;
import edu.asu.garbosells.R;
import edu.asu.garbosells.Template.Attribute;

public class DynamicAttributeListFragment extends Fragment implements AttributeFragment.OnAttributeFragmentInteractionListener {
    private static final String ATTRIBUTES = "attributes";
    private static final String STEP_NUMBER = "step_number";

    private List<Attribute> attributeList;
    private OnAttributeListFragmentInteractionListener mListener;
    private FragmentManager fragmentManager;
    private View layout;
    private int startingStepNumber;

    public DynamicAttributeListFragment() {
        // Required empty public constructor
    }

    public static DynamicAttributeListFragment newInstance(String attributes, int stepNumber) {
        DynamicAttributeListFragment fragment = new DynamicAttributeListFragment();
        Bundle args = new Bundle();
        args.putString(ATTRIBUTES, attributes);
        args.putInt(STEP_NUMBER, stepNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String attributesJson = getArguments().getString(ATTRIBUTES);
            Type AttributeListType = new TypeToken<List<Attribute>>() {}.getType();
            attributeList = new Gson().fromJson(attributesJson, AttributeListType);
            startingStepNumber = getArguments().getInt(STEP_NUMBER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_dynamic_attribute_list, container, false);
        fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        AtomicInteger step = new AtomicInteger(startingStepNumber);
        attributeList.forEach(a -> {
            step.getAndIncrement();
            String recommendationListJson = a.uiInputId == 0 ? new Gson().toJson(a.recommendations) : null;
            AttributeFragment fragment = AttributeFragment.newInstance(a.displayText, a.id, (int) a.uiInputId, step.intValue(), recommendationListJson);
            fragment.setListener(this);
            fragmentTransaction.add(R.id.layout_dynamic_attribute_list, fragment, "step"+step);
        });
        fragmentTransaction.commit();
        returnStep(step.intValue());
        return layout;
    }

    public void returnStep(int step) {
        mListener.setNewStep(step);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onAttributeListFragmentChange(null);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAttributeListFragmentInteractionListener) {
            mListener = (OnAttributeListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAttributeListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnAttributeListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onAttributeListFragmentChange(ItemAttribute attribute);
        void setNewStep(int step);
    }

    @Override
    public void onAttributeFragmentInteraction(ItemAttribute attribute) {
        mListener.onAttributeListFragmentChange(attribute);
    }
}
