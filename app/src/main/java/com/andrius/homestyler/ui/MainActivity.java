package com.andrius.homestyler.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.andrius.homestyler.R;
import com.andrius.homestyler.entity.FurnitureFilter;
import com.andrius.homestyler.view_model.FurnitureViewModel;
import com.github.florent37.runtimepermission.RuntimePermission;

import androidx.annotation.Nullable;
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
    @BindView(R.id.btnFilter)
    Button btnFilter;

    private FurnitureAdapter furnitureAdapter;
    FurnitureViewModel furnitureViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        RuntimePermission.askPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                .onAccepted(result -> init()).ask();
    }

    private void init() {
        btnFilter.setOnClickListener(view -> {
            Intent intent = new Intent(this, FilterActivity.class);
            FurnitureFilter filter = furnitureAdapter.getFilter();
            intent.putExtra("color", filter.getColor());
            intent.putExtra("minPrice", filter.getMinPrice());
            intent.putExtra("maxPrice", filter.getMaxPrice());
            intent.putExtra("type", filter.getType());
            startActivityForResult(intent, 123);
        });

        btnAddFurniture.setOnClickListener(view ->
                startActivity(new Intent(this, AddFurnitureActivity.class)));

        furnitureAdapter = new FurnitureAdapter(furniture -> {
            Intent intent = new Intent(this, FurniturePreviewActivity.class);
            intent.putExtra("id", furniture.getId());
            startActivity(intent);
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvFurniture.setLayoutManager(mLayoutManager);
        rvFurniture.setAdapter(furnitureAdapter);

        furnitureViewModel = ViewModelProviders.of(this).get(FurnitureViewModel.class);

        furnitureViewModel.getAllFurniture().observe(this, furnitureAdapter::setFurnitureList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == RESULT_OK && data != null) {
            String color = data.getStringExtra("color");
            int minPrice = data.getIntExtra("minPrice", 0);
            int maxPrice = data.getIntExtra("maxPrice", 0);
            String type = data.getStringExtra("type");
            FurnitureFilter filter = new FurnitureFilter(color, minPrice, maxPrice, type);
            furnitureAdapter.setFilter(filter);
        }
    }

    public static void log(String text) {
        Log.e("TAG", text);
    }
}
