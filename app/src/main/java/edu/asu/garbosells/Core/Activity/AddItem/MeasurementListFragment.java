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

import edu.asu.garbosells.R;
import edu.asu.garbosells.Template.Measurement;

public class MeasurementListFragment extends Fragment {
    private static final String MEASUREMENTS = "measurements";
    private static final String STEP = "step";

    // TODO: Rename and change types of parameters
    private List<Measurement> measurementList;
    private int step;

    private OnMeasurementListFragmentListener mListener;

    private View layout;

    private FragmentManager fragmentManager;

    public MeasurementListFragment() {
        // Required empty public constructor
    }

    public static MeasurementListFragment newInstance(String measurements, int inputStep) {
        MeasurementListFragment fragment = new MeasurementListFragment();
        Bundle args = new Bundle();
        args.putString(MEASUREMENTS, measurements);
        args.putInt(STEP, inputStep);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            step = getArguments().getInt(STEP);
            String json = getArguments().getString(MEASUREMENTS);
            Type MeasurementListType = new TypeToken<List<Measurement>>() {
            }.getType();
            measurementList = new Gson().fromJson(json, MeasurementListType);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout = inflater.inflate(R.layout.fragment_measurement_list, container, false);
        fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        AtomicInteger tag = new AtomicInteger(1);
        measurementList.forEach(m -> {
            Fragment fragment = SingleMeasurementFragment.newInstance(m.description, m.hint, m.id);
            fragmentTransaction.add(R.id.fragment_measurement_list, fragment, "tag"+tag);
            tag.getAndIncrement();

        });
        fragmentTransaction.commit();
        return layout;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
//            mListener.onAttributeFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMeasurementListFragmentListener) {
            mListener = (OnMeasurementListFragmentListener) context;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnMeasurementListFragmentListener {
        // TODO: Update argument type and name
        void onMeasurementChange();
    }
}
