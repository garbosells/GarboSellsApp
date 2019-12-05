package edu.asu.garbosells.Core.Activity.AddItem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import edu.asu.garbosells.API.Providers.TemplateProvider;
import edu.asu.garbosells.API.Remote.RemotePostListingAPI;
import edu.asu.garbosells.Core.Activity.ListActivity;
import edu.asu.garbosells.Item.Item;
import edu.asu.garbosells.Item.ItemAttribute;
import edu.asu.garbosells.Item.ItemMeasurement;
import edu.asu.garbosells.Item.ItemSize;
import edu.asu.garbosells.Item.Listing;
import edu.asu.garbosells.Item.PostListingRequest;
import edu.asu.garbosells.R;
import edu.asu.garbosells.Template.Attribute;
import edu.asu.garbosells.Template.Recommendation;
import edu.asu.garbosells.Template.Size;
import edu.asu.garbosells.Template.Subcategory;
import edu.asu.garbosells.Template.Template;
import edu.asu.garbosells.UserManagement.AppHelper;
import edu.asu.garbosells.UserManagement.SettingsActivity;

public class InputWizardActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        MeasurementListFragment.OnMeasurementListFragmentListener, SingleMeasurementFragment.OnMeasurementChangeListener,
        DynamicAttributeListFragment.OnAttributeListFragmentInteractionListener
{

    private NavigationView nDrawer;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private String username;
    private CognitoUser user;
    private Subcategory subcategory;
    private Template template;
    private int step = 1;

    EditText editTextDescription;

    Spinner sizeTypeSpinner;
    Spinner sizeValueSpinner;

    long selectedSizeTypeId = -1;
    long selectedSizeValueId = -1;
    View sizeValueContainer;
    View measurementsLayout;
    HashMap<Long, ItemMeasurement> measurementMap;
    HashMap<Long, ItemAttribute> attributeMap;


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
        setupInputForm();
    }

    private void setupInputForm() {
        step = 2;
        if(template.category.hasSizing) {
            setupSizeInput(step);
            step++;
        }
        if(template.category.hasMeasurements) {
            setupMeasurementInput(step);
            step++;
        }
        setupColorInput(step);
        step++;
        setupDecadeInput(step);
        step++;
        setupMaterialInput(step);
        step++;
        setupDynamicInputs(step);
        setupDescriptionInput(step);
        step++;
        setupPriceInput(step);
        setupSubmit();

        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSubmit();
            }
        });
    }

    private void setupSubmit() {
    }

    private void setupPriceInput(int step) {
    }

    private void setupDescriptionInput(int step) {
        TextView stepText = findViewById(R.id.textview_description_step_number);
        stepText.setText(String.valueOf(step));
    }

    private void setupDynamicInputs(int step) {
        attributeMap = new HashMap<>();
        template.subcategory.attributes.forEach(a -> {
            if(a.uiInputId == 5) {
                ItemAttribute itemAttribute = new ItemAttribute();
                itemAttribute.subcategoryAttributeId = a.id;
                itemAttribute.itemAttributeValue = "F";
                attributeMap.put(a.id, itemAttribute);
            }
        });

        List<Attribute> attributes = template.subcategory.attributes;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = DynamicAttributeListFragment.newInstance(new Gson().toJson(attributes), step);
        fragmentTransaction.add(R.id.fragment_attribute_list_placeholder, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void setNewStep(int step) {
        this.step = step+1;
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
                selectedSizeTypeId = selection.sizeTypeId;
                if(selection.sizeTypeId == 3) {
                    //one-size
                    sizeValueContainer.setVisibility(View.INVISIBLE);
                    ItemSize size = new ItemSize(selection.sizeTypeId, 0);

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
                    item.size = null;
                    selectedSizeValueId = -1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sizeValueSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Recommendation selection = (Recommendation) adapterView.getSelectedItem();
                selectedSizeValueId = selection.id;
                if(selectedSizeValueId >= 0 && selectedSizeTypeId >= 0) {
                    ItemSize size = new ItemSize(selectedSizeTypeId, selectedSizeValueId);
                    item.size = size;
                } else {
                    item.size = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void setupMeasurementInput(int step) {
        measurementMap = new HashMap<>();
        measurementsLayout = findViewById(R.id.input_measurement_layout);
        measurementsLayout.setVisibility(View.VISIBLE);

        TextView stepview = findViewById(R.id.textview_measurements_step_number);
        if(stepview != null)
            stepview.setText(String.valueOf(step));

        Fragment measurementListFragment = MeasurementListFragment.newInstance(new Gson().toJson(template.measurements), step);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_measurement_list_placeholder, measurementListFragment);
        fragmentTransaction.commit();
    }

    private void setupColorInput(int step) {
        TextView stepView = findViewById(R.id.textview_color_step_number);
        stepView.setText(String.valueOf(step));

        Spinner primaryColorSpinner = findViewById(R.id.spinner_color_primary);
        List<Recommendation> colors =  template.generalAttributes.primaryColor.recommendations;
        Recommendation nullColor = new Recommendation();
        nullColor.id = -1;
        nullColor.description="Select";
        colors.add(0, nullColor);
        ArrayAdapter<Recommendation> colorArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, colors);
        colorArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        primaryColorSpinner.setAdapter(colorArrayAdapter);
        primaryColorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Recommendation selection = (Recommendation) adapterView.getSelectedItem();
                if(selection.id >= 0) {
                    ItemAttribute color = new ItemAttribute();
                    color.attributeRecommendationId = selection.id;
                    item.generalItemAttributes.primaryColor = color;
                } else {
                    item.generalItemAttributes.primaryColor = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner secondaryColorSpinner = findViewById(R.id.spinner_color_secondary);
        secondaryColorSpinner.setAdapter(colorArrayAdapter);
        secondaryColorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Recommendation selection = (Recommendation) adapterView.getSelectedItem();
                if(selection.id >= 0) {
                    ItemAttribute color = new ItemAttribute();
                    color.attributeRecommendationId = selection.id;
                    item.generalItemAttributes.secondaryColor = color;
                } else {
                    item.generalItemAttributes.secondaryColor = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setupDecadeInput(int step) {
        TextView stepview = findViewById(R.id.textview_decade_step_number);
        stepview.setText(String.valueOf(step));

        List<Recommendation> vals = template.generalAttributes.era.recommendations;
        Recommendation nullRecommendation = new Recommendation();
        nullRecommendation.id = -1;
        nullRecommendation.description = "Select";
        vals.add(0, nullRecommendation);
        ArrayAdapter<Recommendation> decadeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, vals);
        decadeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner decadeSpinner = findViewById(R.id.spinner_decade);
        decadeSpinner.setAdapter(decadeAdapter);

        decadeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Recommendation selection = (Recommendation) adapterView.getSelectedItem();
                if(selection.id >= 0) {
                    ItemAttribute decade = new ItemAttribute();
                    decade.attributeRecommendationId = selection.id;
                    item.generalItemAttributes.era = decade;
                } else {
                    item.generalItemAttributes.era = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setupMaterialInput(int step) {
        TextView stepview = findViewById(R.id.textview_material_step_number);
        stepview.setText(String.valueOf(step));

        List<Recommendation> vals = template.generalAttributes.material.recommendations;
        Recommendation nullRecommendation = new Recommendation();
        nullRecommendation.id = -1;
        nullRecommendation.description = "Select";
        vals.add(0, nullRecommendation);
        ArrayAdapter<Recommendation> materialAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, vals);
        materialAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner decadeSpinner = findViewById(R.id.spinner_material);
        decadeSpinner.setAdapter(materialAdapter);

        decadeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Recommendation selection = (Recommendation) adapterView.getSelectedItem();
                if(selection.id >= 0) {
                    ItemAttribute material = new ItemAttribute();
                    material.attributeRecommendationId = selection.id;
                    item.generalItemAttributes.material = material;
                } else {
                    item.generalItemAttributes.material = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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

    @Override
    public void onMeasurementChange(ItemMeasurement measurement) {
        if(item.measurements == null)
            item.measurements = new ArrayList<>();
        if(measurementMap.containsKey(measurement.categoryMeasurementId)) {
            measurementMap.replace(measurement.categoryMeasurementId, measurement);
        } else {
            measurementMap.put(measurement.categoryMeasurementId, measurement);
        }
    }

    @Override
    public void onMeasurementChange() {

    }

    public boolean postToEbay() {
        return ((CheckBox) findViewById(R.id.checkbox_ebay)).isChecked();
    }

    public boolean postToEtsy() {
        return ((CheckBox) findViewById(R.id.checkbox_etsy)).isChecked();
    }

    public void onClickSubmit() {
        item.createdDateTime = new Date();
        item.updatedDateTime = new Date();
        item.categoryId = subcategory.category.id;
        item.subcategoryId = subcategory.id;

        item.shortDescription = ((EditText) findViewById(R.id.edittext_title)).getText().toString();
        item.longDescription = ((EditText) findViewById(R.id.edittext_description)).getText().toString();

        if(measurementMap != null) {
            item.measurements = new ArrayList<>();
            measurementMap.forEach((k,v) -> {
                item.measurements.add(v);
            });
        }

        if (attributeMap != null) {
            item.attributes = new ArrayList<>();
            attributeMap.forEach((k,v) -> {
                item.attributes.add(v);
            });
        }

        Listing listing = new Listing();
        listing.inventoryItem = item;

        PostListingRequest postListingRequest = new PostListingRequest();
        postListingRequest.listing = listing;
        postListingRequest.postToEbay = postToEbay();
        postListingRequest.postToEtsy = postToEtsy();

        String result = new RemotePostListingAPI(this).PostListing(postListingRequest);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Post Listing Request Result");
        alertDialogBuilder.setMessage(result);
        alertDialogBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                goToListActivity();
            }
        });
        alertDialogBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                goToListActivity();
            }
        });
        alertDialogBuilder.create().show();
    }

    @Override
    public void onAttributeListFragmentChange(ItemAttribute attribute) {
        if(attribute.subcategoryAttributeId >= 0) {
            if(item.attributes == null)
                item.attributes = new ArrayList<>();
            if(attributeMap.containsKey(attribute.subcategoryAttributeId)) {
                attributeMap.replace(attribute.subcategoryAttributeId, attribute);
            } else {
                attributeMap.put(attribute.subcategoryAttributeId, attribute);
            }
        }
    }

}