package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import config.AppConfig;
import model.Student;

public class MainApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Student student = context.getBean(Student.class);
        student.displayDetails();

        context.close();
    }
}