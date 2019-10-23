package edu.asu.garbosells.Core.Provider.Template;

/**
 * A set of Attributes that applies to all items.
 */
public class GeneralAttributes {
    /**
     * Represents the primary color attribute and its recommended values.
     * OPTIONAL
     */
    public Attribute primaryColor;
    /**
     * Represents the secondary color attribute and its recommended values.
     * OPTIONAL
     */
    public Attribute secondaryColor;
    /**
     * Represents the era attribute and its recommended values. The era is whenever the item was manufactured. It is used by the Ebay/Etsy services to determine whether the item can be categorized as "vintage."
     * REQUIRED
     */
    public Attribute era;
    /**
     * Represents the material attribute and its recommended values. That is, what is the item made of?
     * OPTIONAL
     */
    public Attribute material;
}