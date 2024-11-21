package dat;

import dat.config.HibernateConfig;
import dat.daos.impl.ItemDAO;
import dat.daos.impl.StudentDAO;
import dat.dtos.ItemDTO;
import dat.entities.Item;
import dat.entities.Student;
import dat.dtos.StudentDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import dat.enums.Category;

import java.text.SimpleDateFormat;
import java.util.*;


public class Populator {
    public static void populateDatabase(EntityManagerFactory emf) {
        ItemDAO itemDAO = ItemDAO.getInstance(emf);
        StudentDAO studentDAO = StudentDAO.getInstance(emf);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            try (EntityManager em = emf.createEntityManager()) {
                em.getTransaction().begin();

                // Creating Students
                Student student1 = new Student("Alice Johnson", "alice.johnson@example.com", sdf.parse("2021-08-15"), "1234567890");
                Student student2 = new Student("Bob Smith", "bob.smith@example.com", sdf.parse("2022-01-12"), "0987654321");
                Student student3 = new Student("Carol Brown", "carol.brown@example.com", sdf.parse("2020-09-05"), "1122334455");
                Student student4 = new Student("David Clark", "david.clark@example.com", sdf.parse("2019-11-22"), "5566778899");
                Student student5 = new Student("Eve Wilson", "eve.wilson@example.com", sdf.parse("2023-03-10"), "6677889900");

                em.persist(student1);
                em.persist(student2);
                em.persist(student3);
                em.persist(student4);
                em.persist(student5);

                // Creating Items
                Item item1 = new Item("Laptop", 999.99, Category.VIDEO, sdf.parse("2022-07-18"), "High-performance laptop");
                Item item2 = new Item("Book", 29.99, Category.VIDEO, sdf.parse("2023-01-25"), "Fiction novel");
                Item item3 = new Item("Headphones", 199.99, Category.PRINT, sdf.parse("2021-10-12"), "Noise-canceling headphones");
                Item item4 = new Item("Desk Chair", 149.99, Category.PRINT, sdf.parse("2020-05-30"), "Ergonomic desk chair");
                Item item5 = new Item("Backpack", 49.99, Category.PRINT, sdf.parse("2019-12-01"), "Waterproof backpack");

                Set<Item> itemsForStudent1 = new HashSet<>();
                itemsForStudent1.add(item1);
                itemsForStudent1.add(item2);

                Set<Item> itemsForStudent2 = new HashSet<>();
                itemsForStudent2.add(item3);

                Set<Item> itemsForStudent3 = new HashSet<>();
                itemsForStudent3.add(item4);

                Set<Item> itemsForStudent4 = new HashSet<>();
                itemsForStudent4.add(item5);

                student1.setItems(itemsForStudent1);
                student2.setItems(itemsForStudent2);
                student3.setItems(itemsForStudent3);
                student4.setItems(itemsForStudent4);

                em.persist(item1);
                em.persist(item2);
                em.persist(item3);
                em.persist(item4);
                em.persist(item5);

                em.merge(student1);
                em.merge(student2);
                em.merge(student3);
                em.merge(student4);

                em.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while populating the database: " + e.getMessage());
        }
    }
}