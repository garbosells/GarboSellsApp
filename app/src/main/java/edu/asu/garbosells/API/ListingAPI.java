package edu.asu.garbosells.API;

import java.util.ArrayList;

import edu.asu.garbosells.API.Interfaces.IItemAPI;
import edu.asu.garbosells.API.Providers.Mocks.MockListingProvider;
import edu.asu.garbosells.Item.Item;

public class ListingAPI implements IItemAPI {
    IItemAPI itemProvider = new MockListingProvider();
    @Override
    public ArrayList<Item> getAllItems() {
        return itemProvider.getAllItems();
    }
}
