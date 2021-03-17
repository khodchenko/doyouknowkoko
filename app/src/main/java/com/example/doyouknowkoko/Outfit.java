package com.example.doyouknowkoko;

import android.widget.Toast;

import kotlin.jvm.internal.Ref;

public class Outfit {

    public String name;
    public String brand;
    public String size;
    //public String outfitComment;
    //public String outfitPrice;

    public Outfit() {
    }

    public Outfit(String outfitName, String outfitBrand, String outfitSize) {
        this.name = outfitName;
        this.brand = outfitBrand;
        this.size = outfitSize;
        //this.outfitComment = outfitComment;
        //this.outfitPrice = outfitPrice;
    }

}