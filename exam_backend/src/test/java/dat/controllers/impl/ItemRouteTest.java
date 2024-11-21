package dat.controllers.impl;

import dat.Populator;
import dat.config.ApplicationConfig;
import dat.config.HibernateConfig;
import dat.daos.impl.ItemDAO;
import io.javalin.Javalin;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class ItemRouteTest {
    private static Javalin app;
    private static EntityManagerFactory emf;
    private static ItemDAO tripDAO;
    private final String BASE_URL = "http://localhost:7000/api";
    private static Populator populator;

    @BeforeAll
    static void init() {
        ApplicationConfig.startServer(7070);
    }

    @BeforeEach
    void setUp() {
        emf = HibernateConfig.getEntityManagerFactory();

    }


}
