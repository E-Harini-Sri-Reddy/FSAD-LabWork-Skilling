package com.example.skill_4_pt2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Student {

    @Value("19")
    private int studentId;

    @Value("Eadunur Harini Sri Reddy")
    private String name;

    private String course;
    private int year;

    @Value("FSAD")
    public void setCourse(String course) {
        this.course = course;
    }

    @Value("2025")
    public void setYear(int year) {
        this.year = year;
    }

    public void display() {
        System.out.println("ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Course: " + course);
        System.out.println("Year: " + year);
    }
}