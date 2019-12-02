package edu.asu.garbosells.Template;

/**
 * Represents the broad category that the item belongs to.
 */
public class Category extends ICategory {
    /**
     * Indicates whether sizing applies to this Category. Can be used to determine whether to show the Size input component.
     */
    public boolean hasSizing;
    /**
     * Indicates whether measurements apply to this Category. Can be used to determine whether to show the Measurement input components.
     */
    public boolean hasMeasurements;
}