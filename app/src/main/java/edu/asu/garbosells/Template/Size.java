package edu.asu.garbosells.Template;


/**
 * Represents a Size option. Indicates the "Size Type" (US Letter, US Numeric, UK, One-Size, etc) as well as a set of possible values.
 * The user should not be able to enter a custom value.
 */
public class Size {
    /**
     * Identifies the Size type (primary key in the category database).
     */
    public long sizeTypeId;
    /**
     * Description of the Size type to display to the user.
     *
     * Possible Values:
     * US Letter
     * US Numeric
     * UK
     * One-Size
     */
    public String sizeTypeDescription;
    /**
     * Tells whether the Size has a numeric value. May not be used.
     */
    public boolean isNumeric;
    /**
     * List of recommended values. The user can only select a value from this list.
     */
    //public List<Recommendation> recommendations;
}