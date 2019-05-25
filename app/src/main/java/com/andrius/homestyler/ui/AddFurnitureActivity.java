package com.andrius.homestyler.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.andrius.homestyler.R;
import com.andrius.homestyler.entity.Furniture;
import com.andrius.homestyler.util.ImageUtil;
import com.andrius.homestyler.view_model.FurnitureViewModel;
import com.obsez.android.lib.filechooser.ChooserDialog;

import java.io.File;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AddFurnitureActivity extends AppCompatActivity {

    @BindView(R.id.etPrice)
    EditText etPrice;
    @BindView(R.id.spType)
    Spinner spType;
    @BindView(R.id.spColor)
    Spinner spColor;
    @BindView(R.id.etUrl)
    EditText etUrl;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.btnUploadModel)
    Button btnUploadModel;

    private File model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_furniture);
        ButterKnife.bind(this);

        FurnitureViewModel furnitureViewModel = ViewModelProviders.of(this).get(FurnitureViewModel.class);

        btnUploadModel.setOnClickListener(view -> {
            new ChooserDialog(this)
                    .withChosenListener((path, pathFile) ->
                    {
                        MainActivity.log(new File(path).length() + "");
                        File file = new File(path);
                        if (file.getName().endsWith(".sfb")) {
                            model = file;
                        } else {
                            Toast.makeText(this, "not model", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .build().show();
        });

        btnSubmit.setOnClickListener(view -> {

            if (etPrice.getText().toString().isEmpty() || etUrl.getText().toString().isEmpty() ||
                    model == null) {
                Toast.makeText(this, "fill all", Toast.LENGTH_SHORT).show();
            } else {
                String color = spColor.getSelectedItem().toString();
                double price = Double.parseDouble(etPrice.getText().toString());
                String type = spType.getSelectedItem().toString();
                String url = etUrl.getText().toString();
                String imageBase64 = ImageUtil.getImageString(getResources());

                String modelBase64 = ImageUtil.getModelStringBase64(model);

                furnitureViewModel.insert(new Furniture(color, price, type, url, imageBase64, modelBase64));
                Toast.makeText(this, "furniture added", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
