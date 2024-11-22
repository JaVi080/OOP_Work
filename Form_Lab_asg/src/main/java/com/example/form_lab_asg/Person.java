package com.example.form_lab_asg;

public class Person {
    String name;
    String father_name;
    String cnic;
    String DOB;
    String gender;
    String cities;

    public Person(String name, String father_name, String cnic, String DOB,String gender, String cities) {
        this.name = name;
        this.father_name = father_name;
        this.cnic = cnic;
        this.DOB = DOB;
        this.gender=gender;
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", father_name='" + father_name + '\'' +
                ", cnic='" + cnic + '\'' +
                ", DOB='" + DOB + '\'' +
                ", Gender='" + gender + '\'' +
                ", cities='" + cities + '\'' +
                '}';
    }
}
