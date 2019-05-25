package com.andrius.homestyler.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "furniture_table")
public class Furniture {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String color;

    private double price;

    private String type;

    private String url;

    private String imageBase64;

    private String modelBase64;

    public Furniture(String color, double price, String type, String url, String imageBase64, String modelBase64) {
        this.color = color;
        this.price = price;
        this.type = type;
        this.url = url;
        this.imageBase64 = imageBase64;
        this.modelBase64 = modelBase64;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    public String getFormatedPrice() {
        return String.format("%.2f", getPrice()) + " €";
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public String getModelBase64() {
        return modelBase64;
    }
}
