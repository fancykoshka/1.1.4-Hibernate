package jm.task.core.jdbc.util;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import jm.task.core.jdbc.model.User;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String HOST = "jdbc:mysql://localhost:3306/test_db";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "rooot";
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        // 4. Проверка, существует ли уже SessionFactory
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                //  Настройка свойств подключения к базе данных
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, DRIVER); // Указываем драйвер
                settings.put(Environment.URL, HOST); // Указываем URL базы данных
                settings.put(Environment.USER, LOGIN); // Указываем пользователя
                settings.put(Environment.PASS, PASSWORD); // Указываем пароль
                settings.put(Environment.DIALECT, org.hibernate.dialect.MySQLDialect.class.getName()); // Указываем диалект для MySQL

                //  Применяем настройки к конфигурации
                configuration.setProperties(settings);

                // Добавляем аннотированный класс (в данном случае User)
                configuration.addAnnotatedClass(User.class);

                //  Создаем сервисный регистр
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();

                //  Создаем SessionFactory
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                //  Обработка исключений
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

  /*  private static Connection connection;
    private static boolean isConnected = false;
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String HOST = "jdbc:mysql://localhost:3306/test_db";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "rooot";
    private static SessionFactory sessionFactory = null;



    private Util() {

        //Session session = factory.getCurrentSession();

    }
    public static SessionFactory getSConnection() {
        try {
        Configuration configuration = new Configuration()
                .setProperty("hibernate.connection.driver_class", DRIVER)
                .setProperty("hibernate.connection.url", HOST)
                .setProperty("hibernate.connection.username", LOGIN)
                .setProperty("hibernate.connection.password", PASSWORD)
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .addAnnotatedClass(User.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    } catch (Throwable e) {
        e.printStackTrace();
    }
        return sessionFactory;

        *//*try {
            SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).buildSessionFactory();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return factory;*//*
    }

    public static void initializeConnection() {
        if (!isConnected) {
            try {
                // Загрузка драйвера MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Настройка свойств подключения
                Properties properties = new Properties();
                properties.setProperty("user", "root");
                properties.setProperty("password", "rooot");

                // Подключение к базе данных
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/test_db",
                        properties
                );

                // Проверка подключения
                if (connection != null) {
                    System.out.println("Successfully connected to the database!");
                } else {
                    System.err.println("Failed to connect to the database.");
                }

                isConnected = true;
            } catch (ClassNotFoundException e) {
                System.err.println("MySQL JDBC driver not found!");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("Error connecting to the database!");
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
        if (!isConnected || connection == null) {
            initializeConnection();
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed successfully.");
                isConnected = false;
            } catch (SQLException e) {
                System.err.println("Error closing connection!");
                e.printStackTrace();
            }
        }
    }*/
}
