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
    private View.OnClickListener mOnItemClickListener;

    ItemInjector mockItem = new ItemInjector();
    ArrayList<Item> mockItemList = mockItem.getAllItems();

    public ItemAdapter(Context context){
        mcontext = context;
    }
    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, i);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder viewHolder, int i) {
        String curr_item_SD = mockItemList.get(i).shortDescription;
        String curr_item_LD = mockItemList.get(i).longDescription;

        viewHolder.item_ld.setText(curr_item_LD);
        viewHolder.item_sd.setText(curr_item_SD);
    }

    @Override
    public int getItemCount() {
        return mockItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView item_sd;
        public TextView item_ld;

        public ViewHolder(final View itemView, int i){
            super(itemView);
            item_sd = itemView.findViewById(R.id.textview_item_sd);
            item_ld = itemView.findViewById(R.id.textview_item_ld);

            itemView.setTag(this);

        }
    }
}
