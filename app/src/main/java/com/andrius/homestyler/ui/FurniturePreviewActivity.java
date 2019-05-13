package com.andrius.homestyler.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrius.homestyler.R;
import com.andrius.homestyler.entity.Furniture;
import com.andrius.homestyler.util.ImageUtil;
import com.andrius.homestyler.view_model.FurnitureViewModel;

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
            int price = extras.getInt("price");
            String type = extras.getString("type");
            String url = extras.getString("url");
            String image = extras.getString("image");
            int id = extras.getInt("id");

            tvColor.setText(color);
            tvPrice.setText(String.valueOf(price));
            tvType.setText(type);
            tvUrl.setText(url);
            ivPicture.setImageBitmap(ImageUtil.getBitmap(image));

            btnOpenAr.setOnClickListener(view -> {
                Log.e("TAG", "view in ar: ");
            });

            btnDelete.setOnClickListener(view -> {
                furnitureViewModel.delete(id);
                finish();
            });
        }
    }
}
