package com.andrius.homestyler.entity;

public class FurnitureFilter {

    private String color;
    private int minPrice;
    private int maxPrice;
    private String type;

    public FurnitureFilter(String color, int minPrice, int maxPrice, String type) {
        this.color = color;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.type = type;
    }

    public FurnitureFilter() {
        color = "";
        minPrice = 0;
        maxPrice = 0;
        type = "";
    }

    public boolean matchesColor(Furniture furniture) {
        if (!color.isEmpty()) {
            return furniture.getColor().toLowerCase().equals(color.toLowerCase());
        }
        return true;
    }

    public boolean matchesType(Furniture furniture) {
        if (!type.isEmpty()) {
            return furniture.getType().toLowerCase().equals(type.toLowerCase());
        }
        return true;
    }

    public boolean inPriceRange(Furniture furniture) {
        return matchesMinPrice(furniture) && matchesMaxPrice(furniture);
    }

    public boolean matchesMinPrice(Furniture furniture) {
        if (minPrice > 0) {
            return furniture.getPrice() > minPrice;
        }
        return true;
    }

    public boolean matchesMaxPrice(Furniture furniture) {
        if (maxPrice > 0) {
            return furniture.getPrice() < maxPrice;
        }
        return true;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
