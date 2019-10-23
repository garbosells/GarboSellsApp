package edu.asu.garbosells.Core.Provider;
import java.util.ArrayList;

import edu.asu.garbosells.Core.Provider.Item.Item;
import edu.asu.garbosells.Core.Provider.Item.GeneralItemAttributes;

public class ItemProvider {
    ArrayList<Item> temp_items;

    public ArrayList<Item> getMockItems(){
        temp_items = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            Item temp_item = new Item();
            temp_item.shortDescription = "TestItem "+ i;
            temp_item.categoryId = i %2;

            GeneralItemAttributes temp_GA = new GeneralItemAttributes();
            temp_item.generalItemAttributes = temp_GA;
            temp_items.add(temp_item);
        }

        return temp_items;
    }
}
