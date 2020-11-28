package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDaoJDBCImpl implements UserDao {
    private final static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS `mydb`.`user` (id BIGINT NOT NULL AUTO_INCREMENT,\n" +
            "                    name VARCHAR(45) NOT NULL,\n" +
            "                    lastName VARCHAR(45) NOT NULL,\n" +
            "                    age INT(3) NULL,\n" +
            "                    PRIMARY KEY (id))";
    private final static String DROP_TABLE = "DROP TABLE mydb.user";
    private final static String INSERT = "INSERT INTO mydb.user (Name, LastName, Age) VALUES (?, ?, ?)";
    private final static String REMOVE = "DELETE FROM mydb.user WHERE id = id";
    private final static String GET_ALL = "SELECT id, name, lastName, Age FROM mydb.user";
    private final static String CLEAR_USERS = "TRUNCATE TABLE user";

    Util util = new Util();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement statement = util.getConnection().createStatement()) {
            statement.executeUpdate(CREATE_TABLE);
            System.out.println("Таблица создана");
        } catch (SQLException | NullPointerException s) {
            System.out.println("Таблица не создана");
        }
    }

    public void dropUsersTable() {
        try (Statement statement = util.getConnection().createStatement()) {
            statement.executeUpdate(DROP_TABLE);
            System.out.println("Таблица удалена");
        } catch (SQLException | NullPointerException s) {
            System.out.println("Таблица не удалена");
        }
    }


    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = util.getConnection().prepareStatement(INSERT);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.execute();
            System.out.println("User добавлен");

        } catch (SQLException | NullPointerException throwables) {
            System.out.println("Запись не добавлена");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException | NullPointerException e) {}
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = util.getConnection().createStatement()) {
            statement.executeUpdate(REMOVE);
        } catch (SQLException s) {
            s.getStackTrace();
        }
        System.out.println("User удалён");
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        System.out.println("Список всех пользователей");

        try (Statement statement = util.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("Name"));
                user.setLastName(resultSet.getString("LastName"));
                user.setAge(resultSet.getByte("Age"));

                users.add(user);
            }
        } catch (SQLException | NullPointerException s) {
            s.getStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = util.getConnection().createStatement()) {
            statement.execute(CLEAR_USERS);
            System.out.println("Таблица очищена");
        } catch (SQLException | NullPointerException s) {
            System.out.println("Таблица не очищена");
        }
    }
}
