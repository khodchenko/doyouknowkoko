package com.example.doyouknowkoko;

public class ReceiveData {

    public String outfitName;
    public String outfitBrand;
    public String outfitSize;
    public String outfitComment;
    public String outfitPrice;

    public ReceiveData() {
    }

    public ReceiveData(String outfitName, String outfitBrand, String outfitSize, String outfitComment, String outfitPrice) {
        this.outfitName = outfitName;
        this.outfitBrand = outfitBrand;
        this.outfitSize = outfitSize;
        this.outfitComment = outfitComment;
        this.outfitPrice = outfitPrice;
    }

    public String getOutfitName() {
        return outfitName;
    }

    public void setOutfitName(String outfitName) {
        this.outfitName = outfitName;
    }

    public String getOutfitBrand() {
        return outfitBrand;
    }

    public void setOutfitBrand(String outfitBrand) {
        this.outfitBrand = outfitBrand;
    }

    public String getOutfitSize() {
        return outfitSize;
    }

    public void setOutfitSize(String outfitSize) {
        this.outfitSize = outfitSize;
    }

    public String getOutfitComment() {
        return outfitComment;
    }

    public void setOutfitComment(String outfitComment) {
        this.outfitComment = outfitComment;
    }

    public String getOutfitPrice() {
        return outfitPrice;
    }

    public void setOutfitPrice(String outfitPrice) {
        this.outfitPrice = outfitPrice;
    }
}
