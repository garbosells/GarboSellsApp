package edu.asu.garbosells.Item;


/**
 * Represents the size of an item. Includes the ID of the "type" of sizing (US letter, US numeric, UK sizes, etc) and the ID of the value (S/M/L, 4, etc). The value itself is not contained in this object.
 */
public class ItemSize {
    /**
     * The ID of the size type selected by the user.
     *
     * @setby The Android app when the user selects the size type.
     * @setwhen Saving a new item.
     * @modifywhen The size type is modified on an existing item.
     * @modifiedby The user.
     */
    public long sizeTypeId;
    /**
     * The ID of the size value selected by the user. This is NOT the size itself - it is the primary key of the size value in the database.
     *
     * @setby The Android app when the user selects the size.
     * @setwhen Saving a new item.
     * @modifywhen The size is modified on an existing item.
     * @modifiedby The user.
     */
    public long sizeValueId;

    /**
     * Constructor, all fields are required.
     */
    public ItemSize(long sizeTypeId, long sizeValueId) {
        this.sizeTypeId = sizeTypeId;
        this.sizeValueId = sizeValueId;
    }
}