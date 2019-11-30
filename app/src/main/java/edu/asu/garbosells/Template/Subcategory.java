package edu.asu.garbosells.Template;

import java.util.List;

/**
 * Represents the Subcategory of the Item. More specific than Category.
 */
public class Subcategory {
    /**
     * Uniquely identifies the Subcategory.
     */
    public long id;
    /**
     * Description of the Subcategory to display to the user.
     */
    public String description;
    /**
     * The parent Category of this Subcategory.
     */
    public Category category;
    /**
     * Any unique attributes that belong to the Subcategory. Will be NULL if none exist.
     */
    public List<Attribute> attributes;
}