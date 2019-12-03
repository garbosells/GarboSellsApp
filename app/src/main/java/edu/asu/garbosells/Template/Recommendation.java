package edu.asu.garbosells.Template;

/**
 * Represents a recommended value for an Attribute or other value like Size.
 */
public class Recommendation {
    /**
     * Uniquely identifies this recommendation (primary key in the category database).
     */
    public long id;
    /**
     * Describes the recommendation - the text to be displayed to the user.
     */
    public String description;

    public Recommendation() {}
    public Recommendation(long id, String description) {
        this.id =id;
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
