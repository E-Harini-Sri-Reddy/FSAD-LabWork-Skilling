package model;

import org.springframework.stereotype.Component;

@Component
public class Certifications {

    private int id = 101;
    private String name = "AWS Cloud Practitioner";
    private String dateOfCompletion = "12-Feb-2026";

    @Override
    public String toString() {
        return "Certification [id=" + id +
               ", name=" + name +
               ", dateOfCompletion=" + dateOfCompletion + "]";
    }
}