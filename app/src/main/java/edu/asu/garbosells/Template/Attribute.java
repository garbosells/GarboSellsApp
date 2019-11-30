package edu.asu.garbosells.Template;


/**
 * Represents a general or subcategory-specific Attribute.
 */
public class Attribute {
    /**
     * Unique identifier of the attribute - the primary key of the attribute in the category database. This is NULL for general attributes (colors, era, material).
     */
    public Long id; //nullable
    /**
     * Description of the attribute for quick reference (internal - might not be used)
     */
    public String description;
    /**
     * The text to display to the user in prompting for a value.
     */
    public String displayText;
    /**
     * Text to display if the attribute needs a hint to help the user understand it (not currently used).
     */
    public String hintText;
    /**
     * Indicates what type of value applies: integer, string, bool. All are stored as strings, so this may be used by the Ebay/Etsy services for casting.
     */
    public String valueType;
    /**
     * Indicates whether the attribute must be given a value.
     */
    public boolean isRequired;
    /**
     * Identifies which UI input component should be generated to prompt the user for a value.
     */
    public long uiInputId;
    /**
     * The limit that applies. Not currently used, but if it was, this would be the maximum integer value OR the maximum length for a string.
     */
    public Integer valueLimit;
    //nullable
    /**
     * The list of value recommendations. If <code>uiInputId == 0</code>, then the spinner should be populated with these recommendations for the user to select.
     */
    //public List<Recommendation> recommendations;
}