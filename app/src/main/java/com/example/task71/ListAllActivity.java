package com.example.task71;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.task71.databinding.ActivityShowAllBinding;

import java.util.List;

public class ListAllActivity extends AppCompatActivity {

    ActivityShowAllBinding binding;
    DatabaseHelper databaseHelper;

    RecyclerView.LayoutManager layoutManager;
    AdaptorList listAllAdaptor;

    List<Item> itemList;

    public void dataChanged(int position) {
        itemList.remove(position);
        Log.v("onresume", String.valueOf(itemList.size()));
        listAllAdaptor.notifyItemRemoved(position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowAllBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);
        itemList = databaseHelper.getAllItems();
        layoutManager = new LinearLayoutManager(this);
        listAllAdaptor = new AdaptorList(this, itemList);
        databaseHelper = new DatabaseHelper(this);


        binding.recyclerView.setAdapter(listAllAdaptor);
        binding.recyclerView.setLayoutManager(layoutManager);


    }

}