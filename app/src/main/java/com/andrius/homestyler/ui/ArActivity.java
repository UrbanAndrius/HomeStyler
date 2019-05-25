package com.andrius.homestyler.ui;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.andrius.homestyler.R;
import com.andrius.homestyler.util.ImageUtil;
import com.andrius.homestyler.view_model.FurnitureViewModel;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.io.File;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class ArActivity extends AppCompatActivity {

    private ArFragment arFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);

        if (Build.VERSION.SDK_INT > 25) {

            arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);

            FurnitureViewModel furnitureViewModel = ViewModelProviders.of(this).get(FurnitureViewModel.class);

            Bundle extras = getIntent().getExtras();

            if (extras != null) {
                int id = extras.getInt("id");

                furnitureViewModel.getById(id, furniture -> {
                    String modelBase64 = furniture.getModelBase64();

                    File model = ImageUtil.writeToFile(modelBase64);

                    arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
                        Anchor anchor = hitResult.createAnchor();

                        ModelRenderable.builder()
                                .setSource(this, Uri.fromFile(model))
                                .build()
                                .thenAccept(modelRenderable -> {
                                    addModelToScene(anchor, modelRenderable);
                                })
                                .exceptionally(throwable -> {
                                    Log.e("TAG", throwable.toString());
                                    return null;
                                });
                    });
                });
            }
        } else {
            Toast.makeText(this, "not supported", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void addModelToScene(Anchor anchor, ModelRenderable modelRenderable) {
        AnchorNode anchorNode = new AnchorNode(anchor);
        TransformableNode transformableNode = new TransformableNode(arFragment.getTransformationSystem());
        transformableNode.setParent(anchorNode);
        transformableNode.setRenderable(modelRenderable);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
        transformableNode.select();
    }
}
