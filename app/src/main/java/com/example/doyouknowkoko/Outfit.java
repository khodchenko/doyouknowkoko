package com.example.doyouknowkoko;

public class Outfit {

    public String name;
    public String brand;
    public String size;
    public String comment;
    public String price;
    public String imageUrl;

    public Outfit() {
    }

    public Outfit(String outfitName, String outfitBrand, String outfitSize, String outfitComment, String outfitPrice, String imageUrl) {
        this.name = outfitName;
        this.brand = outfitBrand;
        this.size = outfitSize;
        this.comment = outfitComment;
        this.price = outfitPrice;
        this.imageUrl = imageUrl;
    }
}