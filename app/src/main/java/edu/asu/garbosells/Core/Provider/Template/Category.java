package edu.asu.garbosells.Core.Provider.Template;

/**
 * Represents the broad category that the item belongs to.
 */
public class Category {
    /**
     * Uniquely identifies the Category (primary key in the categroy database).
     */
    public long id;
    /**
     * The description of the Category to be displayed to the user.
     */
    public String description;
    /**
     * Indicates whether sizing applies to this Category. Can be used to determine whether to show the Size input component.
     */
    public boolean hasSizing;
    /**
     * Indicates whether measurements apply to this Category. Can be used to determine whether to show the Measurement input components.
     */
    public boolean hasMeasurements;
}