package model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Student {

    private int id = 19;
    private String name = "Eadunur Harini Sri Reddy";
    private String gender = "Female";

    @Autowired
    private Certifications certification;

    public void displayDetails() {
        System.out.println("Student Id: " + id);
        System.out.println("Student Name: " + name);
        System.out.println("Gender: " + gender);
        System.out.println("Certification Details: " + certification);
    }
}