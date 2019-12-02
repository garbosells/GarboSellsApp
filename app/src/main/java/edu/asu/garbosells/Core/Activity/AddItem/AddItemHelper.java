package edu.asu.garbosells.Core.Activity.AddItem;

import edu.asu.garbosells.Item.Listing;
import edu.asu.garbosells.Template.Category;
import edu.asu.garbosells.Template.Subcategory;

public class AddItemHelper {
    private boolean categoryIsSet;
    private long categoryId;

    private boolean subCategoryIsSet;
    private long subcategoryId;

    public AddItemHelper() {
        categoryIsSet = false;
        subCategoryIsSet = false;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
        this.categoryIsSet = true;
    }

    public void setSubcategoryId(long subcategoryId) {
        this.subcategoryId = subcategoryId;
        this.subCategoryIsSet = true;
    }

    public Listing GenerateListing() throws ListingCreationException {
        return new Listing();
    }
}
