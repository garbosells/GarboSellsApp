package edu.asu.garbosells.Template;

import java.util.List;

/**
 * Represents the Subcategory of the Item. More specific than Category.
 */
public class Subcategory extends ICategory {
    /**
     * The parent Category of this Subcategory.
     */
    public Category category;
    /**
     * Any unique attributes that belong to the Subcategory. Will be NULL if none exist.
     */
    public List<Attribute> attributes;
}