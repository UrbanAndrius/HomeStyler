package com.andrius.homestyler.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrius.homestyler.R;
import com.andrius.homestyler.util.ImageUtil;
import com.andrius.homestyler.view_model.FurnitureViewModel;
import com.github.florent37.runtimepermission.RuntimePermission;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FurniturePreviewActivity extends AppCompatActivity {

    @BindView(R.id.tvColor)
    TextView tvColor;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvUrl)
    TextView tvUrl;
    @BindView(R.id.ivPicture)
    ImageView ivPicture;
    @BindView(R.id.btnOpenAr)
    Button btnOpenAr;
    @BindView(R.id.btnDelete)
    Button btnDelete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_furniture_preview);

        ButterKnife.bind(this);

        FurnitureViewModel furnitureViewModel = ViewModelProviders.of(this).get(FurnitureViewModel.class);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String color = extras.getString("color");
            double price = extras.getDouble("price");
            String type = extras.getString("type");
            String url = extras.getString("url");
            String image = extras.getString("image");
            int id = extras.getInt("id");

            tvColor.setText(color);
            tvPrice.setText(String.valueOf(price));
            tvType.setText(type);
            tvUrl.setText(url);
            ivPicture.setImageBitmap(ImageUtil.getBitmap(image));

            btnOpenAr.setOnClickListener(view -> RuntimePermission.askPermission(this,
                    Manifest.permission.CAMERA)
                    .onAccepted(result -> startActivity(new Intent(this, ArActivity.class)))
                    .ask());

            btnDelete.setOnClickListener(view -> {
                furnitureViewModel.delete(id);
                finish();
            });
        }
    }
}
