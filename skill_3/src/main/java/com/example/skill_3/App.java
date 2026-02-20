package com.example.skill_3;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class App {

    public static void main(String[] args) {

        // Create SessionFactory
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

        // Open Session
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // -------- INSERT DATA --------
        session.persist(new Product("Pen","Stationery",10,50));
        session.persist(new Product("Notebook","Stationery",50,30));
        session.persist(new Product("Mouse","Electronics",500,10));
        session.persist(new Product("Keyboard","Electronics",700,5));
        session.persist(new Product("Bag","Accessories",1200,15));
        session.persist(new Product("Bottle","Accessories",300,0));

        tx.commit();
        session.close();

        // -------- HQL OPERATIONS --------
        Session session2 = factory.openSession();

        // 1. Sort by price ASC
        List<Product> list1 = session2
                .createQuery("FROM Product p ORDER BY p.price ASC", Product.class)
                .list();

        System.out.println("\nPrice Ascending:");
        list1.forEach(p -> System.out.println(p.getName() + " " + p.getPrice()));

        // 2. Sort by price DESC
        List<Product> list2 = session2
                .createQuery("FROM Product p ORDER BY p.price DESC", Product.class)
                .list();

        System.out.println("\nPrice Descending:");
        list2.forEach(p -> System.out.println(p.getName() + " " + p.getPrice()));

        // 3. Sort by quantity (highest first)
        List<Product> list3 = session2
                .createQuery("FROM Product p ORDER BY p.quantity DESC", Product.class)
                .list();

        System.out.println("\nQuantity Descending:");
        list3.forEach(p -> System.out.println(p.getName() + " " + p.getQuantity()));

        // 4. Pagination (First 3 products)
        Query<Product> q1 = session2.createQuery("FROM Product p", Product.class);
        q1.setFirstResult(0);
        q1.setMaxResults(3);

        List<Product> paginatedList = q1.list();

        System.out.println("\nFirst 3 Products (Pagination):");
        paginatedList.forEach(p -> System.out.println(p.getName()));

        // 5. Aggregate functions

        // COUNT
        Long totalCount = session2
                .createQuery("SELECT COUNT(p) FROM Product p", Long.class)
                .uniqueResult();

        // MIN & MAX
        Object[] minMax = session2
                .createQuery("SELECT MIN(p.price), MAX(p.price) FROM Product p", Object[].class)
                .uniqueResult();

        System.out.println("\nTotal Products: " + totalCount);
        System.out.println("Min Price: " + minMax[0]);
        System.out.println("Max Price: " + minMax[1]);

        // 6. LIKE example (safe parameter binding)
        List<Product> likeList = session2
                .createQuery("FROM Product p WHERE p.name LIKE :pattern", Product.class)
                .setParameter("pattern", "%o%")
                .list();

        System.out.println("\nProducts containing 'o':");
        likeList.forEach(p -> System.out.println(p.getName()));

        session2.close();
        factory.close();
    }
}