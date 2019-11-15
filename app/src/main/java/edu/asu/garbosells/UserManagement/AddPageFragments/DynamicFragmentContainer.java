package edu.asu.garbosells.UserManagement.AddPageFragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

public abstract class DynamicFragmentContainer extends Fragment {

    //Used to count fragments?
    private static int requiredFragments = 1;
    //Stores list of fragments added
    // - MAY BE REDUNDANT COMPARED TO UTILIZING FRAGMENT MANAGER
    private static ArrayList<DynamicFragmentContainer> list = new ArrayList<DynamicFragmentContainer>();
    private static FragmentManager fragmentManager;
    private static FragmentTransaction fragmentTransaction;

    protected static int count = 0;
    protected int localID;
    protected String label = "";
    protected boolean required = false;

    public DynamicFragmentContainer() {

    }

    protected static int getListSize() {
        return 0;
    }

    protected int getRequiredFragments() {
        return 0;
    }

    protected int getLocalID() {
        return 0;
    }

    protected void add(DynamicFragmentContainer fragment) {

    }

    protected void remove() {

    }

    public static void destroyed() {

    }

    public static void removeFragment() {

    }

}
