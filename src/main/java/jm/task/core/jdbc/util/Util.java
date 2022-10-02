package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import java.util.Properties;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static SessionFactory sessionFactory;
    private static final String URL = "jdbc:mysql://localhost:3306/users";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Vanich_123";

    public static Connection connection () {
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
            return connection;
    }
        public static SessionFactory getSessionFactory() {
            if (sessionFactory == null) {
                try {
                    Configuration configuration = new Configuration();

                    Properties settings = new Properties();
                    settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                    settings.put(Environment.URL, "jdbc:mysql://localhost:3306/users");
                    settings.put(Environment.USER, "root");
                    settings.put(Environment.PASS, "Vanich_123");
                    settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                    settings.put(Environment.SHOW_SQL, "true");

                    settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                    settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                    configuration.setProperties(settings);

                    configuration.addAnnotatedClass(User.class);


                    sessionFactory = configuration.buildSessionFactory();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return sessionFactory;
        }
    }


