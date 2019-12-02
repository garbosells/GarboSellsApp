package edu.asu.garbosells.Core.Activity.AddItem;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.asu.garbosells.API.TemplateAPI;
import edu.asu.garbosells.R;
import edu.asu.garbosells.Template.Category;
import edu.asu.garbosells.Template.ICategory;
import edu.asu.garbosells.Template.Subcategory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CategorySubcategorySelectFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CategorySubcategorySelectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategorySubcategorySelectFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private List<Category> categories;
    private List<Subcategory> subcategories;
    private Spinner categorySpinner;
    private View subcategorySelectContainer;
    private Spinner subcategorySpinner;
    private Category selectedCategory;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CategorySubcategorySelectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategorySubcategorySelectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategorySubcategorySelectFragment newInstance(String param1, String param2) {
        CategorySubcategorySelectFragment fragment = new CategorySubcategorySelectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categories = new ArrayList<>();
        subcategories = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_category_subcategory_select, container, false);
        categories = new TemplateAPI().GetCategories();
        Category nullSelection = new Category();
        nullSelection.id=-1;
        nullSelection.description="Select";
        categories.add(0, nullSelection);
        categorySpinner = view.findViewById(R.id.spinner_category);
        subcategorySpinner = view.findViewById(R.id.spinner_subcategory);
        subcategorySpinner.setVisibility(View.INVISIBLE);
        subcategorySelectContainer = view.findViewById(R.id.container_subcategory_select);
        setupCategorySpinner();
        return view;
    }

    private void setupCategorySpinner() {
        //SET UP ADAPTER
        ArrayAdapter<Category> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, categories);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(arrayAdapter);
        //SET SPINNER ON ITEM SELECTED LISTENER
        categorySpinner.setOnItemSelectedListener(this);
        categorySpinner.setVisibility(View.VISIBLE);
    }

    private void setupSubcategorySpinner() {
        if(subcategories != null && subcategories.size() > 0) {
            //SET UP ADAPTER
            ArrayAdapter<Subcategory> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, subcategories);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            subcategorySpinner.setAdapter(arrayAdapter);
            //SET SPINNER ON ITEM SELECTED LISTENER
            subcategorySpinner.setOnItemSelectedListener(this);
            subcategorySpinner.setVisibility(View.VISIBLE);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Object selection = adapterView.getSelectedItem();
        if(selection.getClass() == Category.class) {
            Category category = (Category) selection;
            if(category.id == -1) {
                selectedCategory = null;
                subcategorySelectContainer.setVisibility(View.INVISIBLE);
                subcategories.clear();
            } else {
                selectedCategory = category;
                subcategories = new TemplateAPI().GetSubcategoriesByCategoryId(category.id);
                setupSubcategorySpinner();
                subcategorySelectContainer.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
