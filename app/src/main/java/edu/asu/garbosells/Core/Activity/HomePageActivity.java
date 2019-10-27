package edu.asu.garbosells.Core.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import edu.asu.garbosells.Core.Adapter.ItemAdapter;
import edu.asu.garbosells.R;

public class HomePageActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    public RecyclerView.Adapter mAdapter;
    public RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //RECYCLERVIEW
        recyclerView =findViewById(R.id.recycler_view);
        //recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        //TODO: Fix
        //recyclerView.setLayoutManager(layoutManager);
        //mAdapter = new ItemAdapter(this);
        //recyclerView.setAdapter(mAdapter);
    }
}
