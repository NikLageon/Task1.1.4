package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/mydb?serverTimezone=Europe/Moscow";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "qwerty";

    public Connection getConnection() {
        try {
            Class.forName(DRIVER);
//            System.out.println("Driver OK");
        } catch (ClassNotFoundException e) {
//            System.out.println("Driver ERROR");
        }

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Соединение с БД установлено!");
            return connection;
        } catch (SQLException s) {
            System.out.println("Не удалось соедениться с БД");
        }
        return null;
    }


}
