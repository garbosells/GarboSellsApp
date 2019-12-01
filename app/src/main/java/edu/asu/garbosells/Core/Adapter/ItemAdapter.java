package edu.asu.garbosells.Core.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.asu.garbosells.Item.Item;
import edu.asu.garbosells.Core.Provider.ItemInjector;
import edu.asu.garbosells.R;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    Context mcontext;

    ItemInjector itemInjector = new ItemInjector();
    ArrayList<Item> items = itemInjector.getAllItems();
    int[] images;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView shortDescription;
        public TextView longDescription;
        public ImageView image;

        public ViewHolder(final View itemView, int i){
            super(itemView);
            shortDescription = itemView.findViewById(R.id.textview_item_short_description);
            longDescription = itemView.findViewById(R.id.textview_item_long_description);
            image = itemView.findViewById(R.id.imageview_item_image);
        }
    }

    // Constructor
    public ItemAdapter(Context context){
        mcontext = context;
        images = new int[] {R.drawable.image_subcategory0, R.drawable.image_subcategory1, R.drawable.image_subcategory2,
        R.drawable.image_subcategory3, R.drawable.image_subcategory4, R.drawable.image_subcategory5};
    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, i);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.shortDescription.setText(items.get(i).shortDescription);
        viewHolder.longDescription.setText(items.get(i).longDescription);
        viewHolder.image.setImageResource(images[(int)items.get(i).subcategoryId-1]);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
