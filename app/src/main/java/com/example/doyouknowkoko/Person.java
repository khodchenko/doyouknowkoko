package com.example.doyouknowkoko;

public class Person
{
    // Variable to store data corresponding
    // to firstname keyword in database
    private String name;

    // Variable to store data corresponding
    // to lastname keyword in database
    private String brand;

    // Variable to store data corresponding
    // to age keyword in database
    private String size;

    // Mandatory empty constructor
    // for use of FirebaseUI
    public Person() {}

    // Getter and setter method
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getBrand()
    {
        return brand;
    }
    public void setBrand(String brand)
    {
        this.brand = brand;
    }
    public String getSize()
    {
        return size;
    }
    public void setSize(String size)
    {
        this.size = size;
    }
}