package com.andrius.homestyler.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.andrius.homestyler.R;

import java.io.ByteArrayOutputStream;

public final class ImageUtil {

    public static String getImageString(Resources resources) {
        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.furniture);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        return Base64.encodeToString(stream.toByteArray(), Base64.NO_WRAP);
    }

    public static Bitmap getBitmap(String imageBase64) {
        byte[] decodedString = Base64.decode(imageBase64, Base64.NO_WRAP);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

}
