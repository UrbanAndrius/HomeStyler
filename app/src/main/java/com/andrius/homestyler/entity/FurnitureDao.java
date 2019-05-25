package com.andrius.homestyler.entity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface FurnitureDao {

    @Insert
    void insert(Furniture furniture);

    @Update
    void update(Furniture furniture);

    @Delete
    void delete(Furniture furniture);

    @Query("DELETE FROM furniture_table WHERE id = :id")
    void deleteById(int id);

    @Query("SELECT * FROM furniture_table")
    LiveData<List<Furniture>> getAllFurniture();

    @Query("SELECT * FROM furniture_table WHERE id = :id")
    Furniture getById(int id);
}
