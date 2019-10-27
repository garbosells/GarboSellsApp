package edu.asu.garbosells.Core.Provider;

import java.util.ArrayList;

import edu.asu.garbosells.Core.Provider.Item.Item;

public class ItemInjector implements IItemDataProvider{
    IItemDataProvider item_provider = new ItemProvider();
    @Override
    public ArrayList<Item> getAllItems() {
        return null;
    }
}
