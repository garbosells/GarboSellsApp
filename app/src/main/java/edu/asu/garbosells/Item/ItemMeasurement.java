package edu.asu.garbosells.Item;

/**
 * Represents a measurement of an item. The area that is to be measured is provided by the template loaded from the CategoryDataService and is identifiable by the ID provided in the template.
 */
public class ItemMeasurement {
    /**
     * The ID of the measurement field (IE, chest, length, etc), provided by the template.
     *
     * @setby The Android app when the user sets a value for a measurement. Assigned to correspond to the ID provided by the template.
     * @setwhen Saving a new item.
     * @modifywhen Never.
     * @modifiedby No one.
     */
    public long categoryMeasurementId;
    /**
     * The ID of the measurement. Set by the database and used to relate the measurement to the Item.
     * <p>
     *     NOTE: This will be NULL when creating a new item in the app. It is not set until the item is saved.
     * </p>
     * @setby The database (should be autoincremented).
     * @setwhen Saving a new item.
     * @modifywhen Never.
     * @modifiedby No one.
     */
    public Long itemMeasurementId;
    /**
     * The value of the measurement as provided by the user.
     * Assumed to be inches.
     *
     * @setby The user.
     * @setwhen Creating an item in the app.
     * @modifywhen Editing an existing item in the app.
     * @modifiedby The user.
     */
    public double itemMeasurementValue;

    /**
     * Constructor. All three fields are required.
     */
    public ItemMeasurement(long categoryMeasurementId, Long itemMeasurementId, double itemMeasurementValue) {
        this.categoryMeasurementId = categoryMeasurementId;
        this.itemMeasurementId = itemMeasurementId;
        this.itemMeasurementValue = itemMeasurementValue;
    }
}