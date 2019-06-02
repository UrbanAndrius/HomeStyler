package com.andrius.homestyler.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;

import com.andrius.homestyler.R;
import com.andrius.homestyler.view_model.FurnitureViewModel;
import com.github.florent37.runtimepermission.RuntimePermission;

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
    @BindView(R.id.btnLogin)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        btnLogin.setOnClickListener(view -> {
            startActivity(new Intent(this, LoginActivity.class));
        });

        RuntimePermission.askPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                .onAccepted(result -> {
                    btnAddFurniture.setOnClickListener(view ->
                            startActivity(new Intent(this, AddFurnitureActivity.class)));

                    FurnitureAdapter furnitureAdapter = new FurnitureAdapter(furniture -> {
                        Intent intent = new Intent(this, FurniturePreviewActivity.class);
                        intent.putExtra("id", furniture.getId());
                        startActivity(intent);
                    });

                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
                    rvFurniture.setLayoutManager(mLayoutManager);
                    rvFurniture.setAdapter(furnitureAdapter);

                    FurnitureViewModel furnitureViewModel = ViewModelProviders.of(this).get(FurnitureViewModel.class);

                    furnitureViewModel.getAllFurniture().observe(this, furnitureAdapter::setFurnitureList);
                }).ask();
    }

    public static void log(String text) {
        Log.e("TAG", text);
    }
}
