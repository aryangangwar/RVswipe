package com.sujal.swipeablervexample;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.sujal.swipeablerv.SwipeLeftRightCallback;
import com.sujal.swipeablerv.SwipeableRecyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<String> mList;
    private Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        createList();

        final SwipeableRecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(mAdapter);

        rv.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedLeft(int position) {
                mList.remove(position);
                mAdapter.notifyDataSetChanged();
                Snackbar.make(rv,
                        "Item " + position + " Marked As Read",
                        Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onSwipedRight(int position) {
                mList.remove(position);
                mAdapter.notifyDataSetChanged();
                Snackbar.make(rv,
                        "Item " + position + " Removed",
                        Snackbar.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createList() {
        mList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            mList.add("Sujal - " + i);
        }
        mAdapter = new Adapter(mList);
    }

}
