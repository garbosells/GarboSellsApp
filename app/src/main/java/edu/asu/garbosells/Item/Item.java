package edu.asu.garbosells.Item;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents an "Item" or "Listing" as it is represented in the app.
 */
public class Item {
    /**
     * The date/time when the item was originally created.
     *
     * @setby The frontend Android app when saving a new item, using <code>new Date()</code>
     * @setwhen Creating a new item in the frontend Android application.
     * @modifywhen Never. This should not be modified once set.
     * @modifiedby No one.
     */
    public Date createdDateTime;
    /**
     * The date/time when the item was last modified.
     *
     * @setby The frontend Android app when saving a new item, using <code>new Date()</code>
     * @setwhen Creating a new item on the frontend Android application.
     * @modifywhen Saving an existing item in the frontend Android application.
     * @modifiedby The Android application.
     */
    public Date updatedDateTime;
    /**
     * The ID of the user who created the item originally.
     *<p>
     * NOTE: this is not the <code>userLoginId</code> (which is a <code>GUID</code> or <code>string</code>). It is the <code>userId</code>, which is a <code>long</code>.
     *</p>
     * @setby The frontend Android app when saving a new item, using <code>userId</code> of the user who is logged in
     * @setwhen Creating a new item on the frontend Android application.
     * @modifywhen Never. This should not be modified once set.
     * @modifiedby No one.
     */
    public long createdByUserId;
    /**
     * The ID of the user who last modified the item.
     *<p>
     * NOTE: this is not the <code>userLoginId</code> (which is a <code>GUID</code> or <code>string</code>). It is the <code>userId</code>, which is a <code>long</code>.
     *</p>
     * @setby The frontend Android app when saving a new item, using <code>userId</code> of the user who is logged in
     * @setwhen Creating a new item on the frontend Android application.
     * @modifywhen Saving an existing item in the frontend Android application.
     * @modifiedby The Android application.
     */
    public long updatedByUserId;
    /**
     * The ID of the item. This is the primary key identifying the item in the database.
     * <p>
     *     NOTE: This will be NULL when creating a new item in the app. It is not set until the item is saved.
     * </p>
     *
     * @setby The database (should be set to autoincrement)
     * @setwhen A new item is saved to the database
     * @modifywhen Never.
     * @modifiedby No one.
     */
    public Long id; //nullable
    /**
     * This field will become the title of the listing once it is posted to Ebay/Etsy
     * @setby The user in the frontend Android app
     * @setwhen Creating an item in the Android app
     * @modifywhen Editing an item in the Android app.
     * @modifiedby The user.
     */
    public String shortDescription;
    /**
     * This field will become the text body of the listing once it is posted to Ebay/Etsy
     * @setby The user in the frontend Android app
     * @setwhen Creating an item in the Android app
     * @modifywhen Editing an item in the Android app.
     * @modifiedby The user.
     */
    public String longDescription;
    /**
     * The ID of the category that the item belongs to.
     * @setby The Android app on item creation (the Category is set by the user, the ID is saved)
     * @setwhen Creating an item in the Android app
     * @modifywhen Never. Once the category is set, it should not be modified.
     * @modifiedby No one.
     */
    public long categoryId;
    /**
     * The ID of the subcategory that the item belongs to.
     * @setby The Android app on item creation (the Subcategory is set by the user, the ID is saved)
     * @setwhen Creating an item in the Android app
     * @modifywhen Never. Once the subcategory is set, it should not be modified.
     * @modifiedby No one.
     */
    public long subcategoryId;
    /**
     * NOT IN USE.
     * Extended feature that may be implemented after basic functionality exists.
     */
    public String[] tags;
    /**
     * The measurements of the item. Only applies if <code>category.hasMeasurements == TRUE</code> May be empty. See ItemMeasurement for details.
     *
     * OPTIONAL FIELDS
     *
     * @setby The Android app when the user enters measurements.
     * @setwhen Saving a new item.
     * @modifywhen The measurements are modified on an existing item.
     * @modifiedby The user.
     */
    public List<ItemMeasurement> measurements;
    /**
     * The size of the item. See ItemSize for details.
     *
     * REQUIRED FIELD if <code>category.hasSizing == TRUE</code>
     *
     * @setby The Android app when the user selects the size type and size value.
     * @setwhen Saving a new item.
     * @modifywhen The size type and size value are modified on an existing item.
     * @modifiedby The user.
     */
    public ItemSize size;
    /**
     * A set of attributes that generally applies to all items, regardless of subcategory.
     * @setby The user when completing the item creation wizard.
     * @setwhen Creating an item in the Android app.
     * @modifywhen Saving an existing item.
     * @modifiedby The user.
     */
    public GeneralItemAttributes generalItemAttributes;
    /**
     * A set of attributes that apply to the particular subcategory selected. A given subcategory may have 0 or more of these.
     * @setby The user when completing the item creation wizard.
     * @setwhen Creating an item in the Android .
     * @modifywhen Saving an existing item.
     * @modifiedby The user.
     */
    public List<ItemAttribute> attributes;

    /**
     * Default constructor to initialize fields.
     */
    public Item() {
        this.generalItemAttributes = new GeneralItemAttributes();
        this.attributes = new ArrayList<ItemAttribute>();
        this.measurements = new ArrayList<ItemMeasurement>();
    }

    /**
     * This exists for testing purposes.
     */
    public void print() {
        System.out.println("Item ID: " + id);
        System.out.println("Item Cateogory ID: " + categoryId);
        System.out.println("Item Subcategory ID: " + subcategoryId);
        System.out.println("Short Description: " + shortDescription);
        if(generalItemAttributes.primaryColor != null)
            System.out.println("Primary Color ID: " + generalItemAttributes.primaryColor.attributeRecommendationId);
        if(generalItemAttributes.secondaryColor != null)
            System.out.println("Secondary Color ID: " + generalItemAttributes.secondaryColor.attributeRecommendationId);
        if(size != null) {
            System.out.println("Size Type ID: " + size.sizeTypeId);
            System.out.println("Size Value ID: " + size.sizeValueId);
        }
        if(!measurements.isEmpty()) {
            System.out.println("Measurements by ID and value:");
            /*measurements.forEach(m -> {
                System.out.println(m.categoryMeasurementId + ": " + m.itemMeasurementValue);
            });*/
        }
        if(generalItemAttributes.material != null)
            System.out.println("Material ID: " + generalItemAttributes.material.attributeRecommendationId);
        if(generalItemAttributes.era != null)
            System.out.println("Era ID: " + generalItemAttributes.era.attributeRecommendationId);
        if(!attributes.isEmpty()){}
            /*attributes.forEach(a -> {
                System.out.println("Subcategory Attribute ID " + a.subcategoryAttributeId + " has value:");
                System.out.println(a.attributeRecommendationId == null ? a.itemAttributeValue : a.attributeRecommendationId);
            });*/
    }
}