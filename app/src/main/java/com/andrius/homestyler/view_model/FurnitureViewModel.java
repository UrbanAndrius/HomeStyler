package com.andrius.homestyler.view_model;

import android.app.Application;
import android.os.AsyncTask;

import com.andrius.homestyler.database.FurnitureDatabase;
import com.andrius.homestyler.entity.Furniture;
import com.andrius.homestyler.entity.FurnitureDao;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class FurnitureViewModel extends AndroidViewModel {

    private FurnitureDao furnitureDao;
    private LiveData<List<Furniture>> allFurniture;

    public FurnitureViewModel(@NonNull Application application) {
        super(application);
        FurnitureDatabase instance = FurnitureDatabase.getInstance(application);
        furnitureDao = instance.furnitureDao();
        allFurniture = furnitureDao.getAllFurniture();
    }

    public void insert(Furniture furniture) {
        AsyncTask.execute(() -> furnitureDao.insert(furniture));
    }

    public void update(Furniture furniture) {
        AsyncTask.execute(() -> furnitureDao.update(furniture));
    }

    public void delete(Furniture furniture) {
        AsyncTask.execute(() -> furnitureDao.delete(furniture));
    }

    public void delete(int id) {
        AsyncTask.execute(() -> furnitureDao.deleteById(id));
    }

    public LiveData<List<Furniture>> getAllFurniture() {
        return allFurniture;
    }
}
