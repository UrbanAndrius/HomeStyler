package com.andrius.homestyler.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.andrius.homestyler.R;
import com.andrius.homestyler.view_model.FurnitureViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rvFurniture)
    RecyclerView rvFurniture;
    @BindView(R.id.btnAddFurniture)
    Button btnAddFurniture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        btnAddFurniture.setOnClickListener(view ->
                startActivity(new Intent(this, AddFurnitureActivity.class)));

        FurnitureAdapter furnitureAdapter = new FurnitureAdapter(furniture -> {
            Intent intent = new Intent(this, FurniturePreviewActivity.class);
            intent.putExtra("id", furniture.getId());
            intent.putExtra("color", furniture.getColor());
            intent.putExtra("price", furniture.getPrice());
            intent.putExtra("type", furniture.getType());
            intent.putExtra("url", furniture.getUrl());
            intent.putExtra("image", furniture.getImageBase64());
            startActivity(intent);
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvFurniture.setLayoutManager(mLayoutManager);
        rvFurniture.setAdapter(furnitureAdapter);

        FurnitureViewModel furnitureViewModel = ViewModelProviders.of(this).get(FurnitureViewModel.class);

        furnitureViewModel.getAllFurniture().observe(this, furnitureAdapter::setFurnitureList);
    }
}
