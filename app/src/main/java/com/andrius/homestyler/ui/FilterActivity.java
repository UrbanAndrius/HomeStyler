package com.andrius.homestyler.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        ButterKnife.bind(this);

        btnFilter.setOnClickListener(view -> finishWithResult(spColor.getSelectedItem().toString()));

        btnClearFilter.setOnClickListener(view -> finishWithResult(""));
    }

    private void finishWithResult(String color) {
        Intent intent = new Intent();
        intent.putExtra("color", color);
        setResult(RESULT_OK, intent);
        finish();
    }
}
