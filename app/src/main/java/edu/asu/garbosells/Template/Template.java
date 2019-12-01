package edu.asu.garbosells.Template;


import java.util.ArrayList;
import java.util.List;

/**
 * Represents a set of fields to be completed by the user. This class provides all of the data necessary for the app to generate each input in the item creation wizard based on the category and subcategory selected by the user.
 */
public class Template {
    /**
     * The Category that the item belongs to. Tells whether the item will have sizing or measurements. If it does not, then the UI should not display those inputs.
     */
    public Category category;
    /**
     * The Subcategory that the item belongs to. Contains any attributes that are specific to that subcategory, which the UI should prompt the user to enter.
     */
    public Subcategory subcategory;
    /**
     * An object to encapsulate generalized attributes that apply to most/all items.
     */
    public GeneralAttributes generalAttributes;
    /**
     * List of Size templates, which includes Size Types (US Letter, US Numeric, UK, One-Size, etc) and a list of Recommendations (the only valid values for sizes).
     */
    public List<Size> sizeOptions;
    /**
     * List of Measurements that should be taken based on the given subcategory.
     */
    public List<Measurement> measurements;

    public Template() {
        sizeOptions = new ArrayList<Size>();
        measurements = new ArrayList<Measurement>();
    }

    @Override
    public String toString() {
        return "Category: " + category +
                "\nSubcategory: " + subcategory;
    }
}