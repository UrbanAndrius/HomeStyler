package com.andrius.homestyler.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.andrius.homestyler.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterActivity extends AppCompatActivity {

    @BindView(R.id.btnFilter)
    Button btnFilter;
    @BindView(R.id.btnClearFilter)
    Button btnClearFilter;
    @BindView(R.id.spColor)
    Spinner spColor;
    @BindView(R.id.btnBack)
    Button btnBack;
    @BindView(R.id.etMinPrice)
    EditText etMinPrice;
    @BindView(R.id.etMaxPrice)
    EditText etMaxPrice;
    @BindView(R.id.spType)
    Spinner spType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            selectSpinnerItemByValue(spColor, extras.getString("color"));
            selectSpinnerItemByValue(spType, extras.getString("type"));
            etMinPrice.setText(String.valueOf(extras.getInt("minPrice")));
            etMaxPrice.setText(String.valueOf(extras.getInt("maxPrice")));
        }

        btnBack.setOnClickListener(view -> finish());

        btnFilter.setOnClickListener(view -> {
            String color = spColor.getSelectedItem().toString().trim();
            int minPrice, maxPrice;
            try {
                minPrice = Integer.parseInt(etMinPrice.getText().toString().trim());
            } catch (NumberFormatException e) {
                minPrice = 0;
            }
            try {
                maxPrice = Integer.parseInt(etMaxPrice.getText().toString().trim());
            } catch (NumberFormatException e) {
                maxPrice = 0;
            }
            String type = spType.getSelectedItem().toString();
            finishWithResult(color, minPrice, maxPrice, type);
        });

        btnClearFilter.setOnClickListener(view -> finishWithResult("", 0, 0, ""));
    }

    private void finishWithResult(String color, int minPrice, int maxPrice, String type) {
        Intent intent = new Intent();
        intent.putExtra("color", color);
        intent.putExtra("minPrice", minPrice);
        intent.putExtra("maxPrice", maxPrice);
        intent.putExtra("type", type);
        setResult(RESULT_OK, intent);
        finish();
    }

    public static void selectSpinnerItemByValue(Spinner spinner, String value) {
        SpinnerAdapter adapter = spinner.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).toString().equals(value)) {
                spinner.setSelection(i);
                return;
            }
        }
    }
}
