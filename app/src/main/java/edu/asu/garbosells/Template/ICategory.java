package edu.asu.garbosells.Template;

public abstract class ICategory {
    /**
     * Uniquely identifies the Subcategory.
     */
    public long id;
    /**
     * Description of the Subcategory to display to the user.
     */
    public String description;

    @Override
    public String toString() {
        return description;
    }
}
