package edu.asu.garbosells.Core.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import edu.asu.garbosells.Core.Provider.Item.Item;
import edu.asu.garbosells.Core.Provider.ItemInjector;
import edu.asu.garbosells.R;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    Context mcontext;

    ItemInjector mockItem = new ItemInjector();
    ArrayList<Item> mockItemList = mockItem.getAllItems();

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView item_sd;
        public TextView item_ld;

        public ViewHolder(final View itemView, int i){
            super(itemView);
            item_sd = itemView.findViewById(R.id.textview_item_sd);
            item_ld = itemView.findViewById(R.id.textview_item_ld);

        }
    }

    // Constructor
    public ItemAdapter(Context context){
        mcontext = context;
    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, i);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //String curr_item_SD = mockItemList.get(i).shortDescription;
        //String curr_item_LD = mockItemList.get(i).longDescription;

        //viewHolder.item_ld.setText(curr_item_LD);
        //viewHolder.item_sd.setText(curr_item_SD);

            viewHolder.item_ld.setText("Long Description about the Item");
            viewHolder.item_sd.setText("Item Title Here");
    }

    @Override
    public int getItemCount() {
        return 25;
    }


}
