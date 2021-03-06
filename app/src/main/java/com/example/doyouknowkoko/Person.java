package com.example.doyouknowkoko;

public class Person
{
    // Variable to store data corresponding
    // to firstname keyword in database
    private String name;

    // Variable to store data corresponding
    // to lastname keyword in database
    private String lastname;

    // Variable to store data corresponding
    // to age keyword in database
    private String age;

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
    public String getLastname()
    {
        return lastname;
    }
    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }
    public String getAge()
    {
        return age;
    }
    public void setAge(String age)
    {
        this.age = age;
    }
}