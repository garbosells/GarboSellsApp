package edu.asu.garbosells.API.Providers.Mocks;
import java.util.ArrayList;

import edu.asu.garbosells.API.Interfaces.IItemAPI;
import edu.asu.garbosells.Item.Item;

public class MockListingProvider implements IItemAPI {
    ArrayList<Item> temp_items;

    public ArrayList<Item> getMockItems(){
        temp_items = new ArrayList<>();

        Item tshirt1 = new Item();
        tshirt1.categoryId = 0;
        tshirt1.subcategoryId = 1;
        tshirt1.id = new Long(0);
        tshirt1.shortDescription = "Cool true vintage men's t-shirt";
        tshirt1.longDescription = "This is a listing for a men's t-shirt. It's vintage, so you know it's really cool and unique. One of a kind, you might say. You should buy it.";
        temp_items.add(tshirt1);

        Item hat1 = new Item();
        hat1.categoryId = 1;
        hat1.subcategoryId = 4;
        hat1.id = new Long(2);
        hat1.shortDescription = "Awesome vintage hat";
        hat1.longDescription = "This is a really cool old hat. It's vintage, so you know it's really cool and unique. One of a kind, you might say. You should buy it.";
        temp_items.add(hat1);

        Item tshirt2 = new Item();
        tshirt2.categoryId = 0;
        tshirt2.subcategoryId = 2;
        tshirt2.id = new Long(1);
        tshirt2.shortDescription = "Super cool true vintage women's t-shirt";
        tshirt2.longDescription = "Holy moly look at this. It is a listing for a women's t-shirt. It's vintage and super cute, so you know it's really cool and unique. One of a kind, you might say. You should buy it.";
        temp_items.add(tshirt2);

        Item hat2 = new Item();
        hat2.categoryId = 1;
        hat2.subcategoryId = 5;
        hat2.id = new Long(3);
        hat2.shortDescription = "Awesome vintage hat";
        hat2.longDescription = "Heyyyyyyyyyy this is a really cool old hat. It's vintage, so you know it's really cool and unique. One of a kind, you might say. You should buy it.";
        temp_items.add(hat2);

        Item hat3 = new Item();
        hat3.categoryId = 1;
        hat3.subcategoryId = 3;
        hat3.id = new Long(4);
        hat3.shortDescription = "Yet another awesome vintage hat";
        hat3.longDescription = "Look at this great beanie. This is a really cool old hat. It's vintage, so you know it's really cool and unique. One of a kind, you might say. You should buy it.";
        temp_items.add(hat3);

        Item tshirt3 = new Item();
        tshirt3.categoryId = 0;
        tshirt3.subcategoryId = 1;
        tshirt3.id = new Long(5);
        tshirt3.shortDescription = "A unisex t-shirt";
        tshirt3.longDescription = "Unisex t-shirts are a lie. They're really men's t-shirts.";
        temp_items.add(tshirt3);


        return temp_items;
    }

    @Override
    public ArrayList<Item> getAllItems() {
        return getMockItems();
    }
}
