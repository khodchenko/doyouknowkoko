package com.example.doyouknowkoko;

public class User {
    public String id;
    public String outfitName;
    public String outfitBrand;
    public String outfitSize;
    public String outfitComment;
    public String outfitPrice;

    public User() {
    }

    public User(String id, String outfitName, String outfitBrand, String outfitSize, String outfitComment, String outfitPrice) {
        this.id = id;
        this.outfitName = outfitName;
        this.outfitBrand = outfitBrand;
        this.outfitSize = outfitSize;
        this.outfitComment = outfitComment;
        this.outfitPrice = outfitPrice;
    }
}