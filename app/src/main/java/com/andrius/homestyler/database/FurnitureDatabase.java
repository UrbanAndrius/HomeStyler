package com.andrius.homestyler.database;

import android.content.Context;

import com.andrius.homestyler.entity.Furniture;
import com.andrius.homestyler.entity.FurnitureDao;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Furniture.class, version = 1)
public abstract class FurnitureDatabase extends RoomDatabase {

    private static FurnitureDatabase instance;

    public abstract FurnitureDao furnitureDao();

    public static synchronized FurnitureDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), FurnitureDatabase.class,
                    "furniture_database").build();
        }
        return instance;
    }
}
