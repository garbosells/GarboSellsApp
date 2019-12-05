package edu.asu.garbosells.Core.Activity.AddItem;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import edu.asu.garbosells.R;

public class FragmentBoolean extends Fragment {

    private Switch toggle;

    public FragmentBoolean() {
        super();
    }

    public static FragmentBoolean newInstance() {
        FragmentBoolean fragment = new FragmentBoolean();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_input_boolean, container, false);
        toggle = (Switch) view.findViewById(R.id.switch_input_boolean);

        AttributeFragment parent = (AttributeFragment) getParentFragment();
        toggle.setOnCheckedChangeListener(parent);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
