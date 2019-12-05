package edu.asu.garbosells.Core.Activity.AddItem;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import edu.asu.garbosells.Item.ItemAttribute;
import edu.asu.garbosells.R;
import edu.asu.garbosells.Template.Recommendation;

public class AttributeFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    private static final String LABEL = "label";
    private static final String ATTRIBUTE_ID = "attribute_id";
    private static final String INPUT_TYPE = "input_type";
    private static final String STEP_NUMBER = "step_number";
    private static final String RECOMMENDATIONS = "recommendations";

    private static final int SPINNER_TYPE = 0;
    private static final int SWITCH_TYPE = 5;

    private String mLabel;
    private long mAttributeId;
    private String returnValue;
    private long mValueId;
    private List<Recommendation> recommendationList;
    private int mType;
    private int mStep;

    private View layout;

    private OnAttributeFragmentInteractionListener mListener;

    private FragmentManager fragmentManager;

    private ItemAttribute attribute;

    public AttributeFragment() {
        // Required empty public constructor
    }

    public static AttributeFragment newInstance(String label, long attributeId, int type, int stepNumber, String recommendations) {
        AttributeFragment fragment = new AttributeFragment();
        Bundle args = new Bundle();
        args.putString(LABEL, label);
        args.putLong(ATTRIBUTE_ID, attributeId);
        args.putInt(INPUT_TYPE, type);
        args.putInt(STEP_NUMBER, stepNumber);
        if(recommendations != null)
            args.putString(RECOMMENDATIONS, recommendations);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attribute = new ItemAttribute();
        if (getArguments() != null) {
            mLabel = getArguments().getString(LABEL);
            mAttributeId = getArguments().getLong(ATTRIBUTE_ID);
            attribute.subcategoryAttributeId = mAttributeId;
            mType = getArguments().getInt(INPUT_TYPE);
            mStep = getArguments().getInt(STEP_NUMBER);

            if (mType == SPINNER_TYPE) {
                String recommendationJson = getArguments().getString(RECOMMENDATIONS);
                Type RecommendationListType = new TypeToken<List<Recommendation>>() {}.getType();
                recommendationList = new Gson().fromJson(recommendationJson, RecommendationListType);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout = inflater.inflate(R.layout.input_attribute, container, false);
        TextView stepNumberView = layout.findViewById(R.id.textview_attribute_step_number);
        stepNumberView.setText(String.valueOf(mStep));

        TextView labelView = layout.findViewById(R.id.input_attribute_header);
        labelView.setText(mLabel);

        fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment;
        switch(mType) {
            case SPINNER_TYPE:
//                Fragment fragment = SingleMeasurementFragment.newInstance(m.description, m.hint, m.id);
//                fragmentTransaction.add(R.id.fragment_measurement_list, fragment, "tag"+tag);
                break;
            case SWITCH_TYPE:
                fragment = FragmentBoolean.newInstance();
                fragmentTransaction.add(R.id.fragment_attribute_placeholder, fragment);
                fragmentTransaction.commit();
                break;
            default:
                break;
        }
        return layout;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAttributeFragmentInteractionListener) {
            mListener = (OnAttributeFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAttributeFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        attribute.itemAttributeValue = isChecked ? "T" : "F";
        mListener.onAttributeFragmentInteraction(attribute);
    }

    public interface OnAttributeFragmentInteractionListener {
        void onAttributeFragmentInteraction(ItemAttribute attribute);
    }
}
