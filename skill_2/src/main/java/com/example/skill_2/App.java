package com.example.skill_2;

/**
 * Hello world!
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {

    public static void main(String[] args) {

        // Create Product object
        Product p = new Product("Laptop", "Gaming Laptop", 75000.0, 10);

        // Create SessionFactory
        SessionFactory factory = new Configuration()
                .configure() // loads hibernate.cfg.xml
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

        // Open Session
        Session session = factory.openSession();

        // Begin Transaction
        Transaction tx = session.beginTransaction();

        // Save object (INSERT)
        session.persist(p);

        // Commit
        tx.commit();

        // Close
        session.close();
        factory.close();

        System.out.println("Product inserted successfully!");
    }
}