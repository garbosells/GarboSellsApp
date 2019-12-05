package edu.asu.garbosells.Core.Activity.AddItem;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import edu.asu.garbosells.Item.ItemMeasurement;
import edu.asu.garbosells.R;

public class SingleMeasurementFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String LABEL = "label";
    private static final String HINT = "hint";
    private static final String MEASUREMENT_ID = "measurement_id";

    // TODO: Rename and change types of parameters
    private String mLabel;
    private String mHint;
    private long mId;

    TextView hintText;
    TextView promptText;
    EditText editText;

    private OnMeasurementChangeListener mListener;

    public SingleMeasurementFragment() {
        // Required empty public constructor
    }

    public static SingleMeasurementFragment newInstance(String label, String hint, long id) {
        SingleMeasurementFragment fragment = new SingleMeasurementFragment();
        Bundle args = new Bundle();
        args.putString(LABEL, label);
        args.putString(HINT, hint);
        args.putLong(MEASUREMENT_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mLabel = getArguments().getString(LABEL);
            mHint = getArguments().getString(HINT);
            mId = getArguments().getLong(MEASUREMENT_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.input_single_measurement, container, false);
        hintText = view.findViewById(R.id.measurement_hint_text);
        promptText =  view.findViewById(R.id.input_measurement_prompt);
        editText = view.findViewById(R.id.edittext_input_measurement);
        hintText.setText(mHint);
        promptText.setText(mLabel + ":");

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                onMeasurementChange(editText.getText().toString());
            }
        });
        return view;
    }

    public void onMeasurementChange(String text) {
        if (mListener != null && !text.isEmpty()) {
            ItemMeasurement measurement = new ItemMeasurement(mId, null, Double.parseDouble(text));
            mListener.onMeasurementChange(measurement);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMeasurementChangeListener) {
            mListener = (OnMeasurementChangeListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnMeasurementChangeListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnMeasurementChangeListener {
        // TODO: Update argument type and name
        void onMeasurementChange(ItemMeasurement measurement);
    }
}
