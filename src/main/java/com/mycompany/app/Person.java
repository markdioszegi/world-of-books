package com.mycompany.app;

import java.sql.Date;

/**
 * @param age
 * @param name
 * @param dob
 * 
 * @see google.com
 */
public class Person {
    int age;
    String name;
    Date dateOfBirth;

    public Person(int age, String name, Date dob) {
        this.age = age;
        this.name = name;
        this.dateOfBirth = dob;
    }

    @Override
    public String toString() {
        return String.format("Age: %d, Name: %s, Date of birth: %s", this.age, this.name, this.dateOfBirth);
    }
}