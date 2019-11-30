package edu.asu.garbosells.Template;

/**
 * Represents a measurement that should be taken of an item in a given subcategory.
 * Optional.
 */
public class Measurement {
    /**
     * Uniquely identifies this Measurement template (primary key in the category database).
     */
    public long id;
    /**
     * The text description of the measurement area to be displayed to the user (IE chest, length, etc).
     */
    public String description;
    /**
     * A hint to tell the user how to take the measurement (IE 'arm pit to arm pit' for the chest).
     */
    public String hint;
}