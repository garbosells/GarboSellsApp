package edu.asu.garbosells.Core.Activity.AddItem;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import edu.asu.garbosells.API.Providers.TemplateProvider;
import edu.asu.garbosells.Core.Activity.ListActivity;
import edu.asu.garbosells.Item.Item;
import edu.asu.garbosells.R;
import edu.asu.garbosells.Template.Recommendation;
import edu.asu.garbosells.Template.Size;
import edu.asu.garbosells.Template.Subcategory;
import edu.asu.garbosells.Template.Template;
import edu.asu.garbosells.UserManagement.AppHelper;
import edu.asu.garbosells.UserManagement.SettingsActivity;

import static java.security.AccessController.getContext;

public class InputWizardActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private NavigationView nDrawer;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private String username;
    private CognitoUser user;
    private Subcategory subcategory;
    private Template template;

    EditText editTextDescription;

    Spinner sizeTypeSpinner;
    Spinner sizeValueSpinner;

    long selectedSizeTypeId = -1;
    long selectedSizeValueId = -1;
    View sizeValueContainer;
    View measurementsLayout;

    private Item item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String json = intent.getStringExtra("subcategory");
        Type SubcategoryType = new TypeToken<Subcategory>(){}.getType();
        subcategory = new Gson().fromJson(json, SubcategoryType);

        template = new TemplateProvider().GetTemplateBySubcategoryId(subcategory.id);

        setContentView(R.layout.activity_input_wizard);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Set toolbar for this screen
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle("");
        TextView main_title = (TextView) findViewById(R.id.main_toolbar_title);
        main_title.setText("New Listing: " + subcategory.description);
        setSupportActionBar(toolbar);

        // Set navigation drawer for this screen
        mDrawer = (DrawerLayout) findViewById(R.id.add_item_drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        nDrawer = (NavigationView) findViewById(R.id.nav_view);
        setNavDrawer();
        init();
        View navigationHeader = nDrawer.getHeaderView(0);
        TextView navHeaderSubTitle = (TextView) navigationHeader.findViewById(R.id.textViewNavUserSub);
        navHeaderSubTitle.setText(username);

        item = new Item();
        item.categoryId = subcategory.category.id;
        item.subcategoryId = subcategory.id;
        setupInputForm();
    }

    private void setupInputForm() {
        editTextDescription = findViewById(R.id.edittext_short_description);
        editTextDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateShortDescription(editTextDescription.getText().toString().trim());
            }
        });

        int step = 2;
        if(template.category.hasSizing) {
            setupSizeInput(step);
            step++;
        }
        if(template.category.hasMeasurements) {
            setupMeasurementInput(step);
            step++;
        }
    }

    private void updateShortDescription(String text) {
        item.setShortDescription(text);
    }

    private void setupSizeInput(int step) {
        sizeValueContainer = findViewById(R.id.item_size_value_input_container);
        TextView textViewStep = findViewById(R.id.textview_size_step_number);
        textViewStep.setText(String.valueOf(step));

        View view = findViewById(R.id.input_size_layout);
        view.setVisibility(View.VISIBLE);
        sizeTypeSpinner = findViewById(R.id.spinner_size_type);
        sizeValueSpinner = findViewById(R.id.spinner_size_value);
        sizeValueSpinner.setEnabled(false);

        //SET UP ADAPTER
        ArrayList<Size> options = (ArrayList<Size>) template.sizeOptions;
        Size nullOption = new Size();
        nullOption.sizeTypeId = -1;
        nullOption.sizeTypeDescription = "Select a Scale";
        options.add(0, nullOption);
        ArrayAdapter<Size> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeTypeSpinner.setAdapter(arrayAdapter);
        //SET SPINNER ON ITEM SELECTED LISTENER
        sizeTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Size selection = (Size) adapterView.getSelectedItem();
                if(selection.sizeTypeId == 3) {
                    //one-size
                    sizeValueContainer.setVisibility(View.INVISIBLE);
                }
                else if(selection.sizeTypeId >= 0) {
                    List<Recommendation> options = selection.recommendations;
                    ArrayAdapter<Recommendation> sizeValueAdapter = new ArrayAdapter<>(InputWizardActivity.this, android.R.layout.simple_spinner_item, options);
                    sizeValueAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sizeValueSpinner.setAdapter(sizeValueAdapter);
                    sizeValueSpinner.setEnabled(true);
                    sizeValueContainer.setVisibility(View.VISIBLE);
                } else {
                    Recommendation nullRecommendation = new Recommendation();
                    nullRecommendation.id = -1;
                    nullRecommendation.description = "Choose a scale";
                    ArrayAdapter<Recommendation> emptySizeValueAdapter = new ArrayAdapter<>(InputWizardActivity.this, android.R.layout.simple_spinner_item, new Recommendation[] {nullRecommendation});
                    emptySizeValueAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sizeValueSpinner.setAdapter(emptySizeValueAdapter);
                    sizeValueSpinner.setEnabled(false);
                    sizeValueContainer.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void setupMeasurementInput(int step) {
        measurementsLayout = findViewById(R.id.input_measurements_layout);
        measurementsLayout.setVisibility(View.VISIBLE);
        TextView textViewStep = findViewById(R.id.textview_measurements_step_number);
        textViewStep.setText(String.valueOf(step));

    }

    // Handle when the a navigation item is selected
    private void setNavDrawer() {
        nDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                performAction(item);
                return true;
            }
        });
    }

    // Initialize this activity
    private void init() {
        // Get the user name
        username = AppHelper.getCurrUser();
        user = AppHelper.getPool().getUser(username);
    }

    // Perform the action for the selected navigation item
    private void performAction(MenuItem item) {
        // Close the navigation drawer
        mDrawer.closeDrawers();

        // Find which item was selected
        switch (item.getItemId()) {
            case R.id.nav_item_add_item:
                goToAddItemActivity();
                break;
            case R.id.nav_item_view_list:
                goToListActivity();
                break;
            case R.id.nav_item_settings:
                goToSettingsActivity();
                break;
            case R.id.nav_user_sign_out:
                // Sign out from this account
                signOut();
                break;
        }
    }

    private void goToAddItemActivity() {
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivity(intent);
    }

    private void goToListActivity() {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    private void goToSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    // Sign out user
    private void signOut() {
        user.signOut();
        exit();
    }

    private void exit() {
        Intent intent = new Intent();
        if (username == null)
            username = "";
        intent.putExtra("name", username);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}