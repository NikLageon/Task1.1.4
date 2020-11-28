package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) throws NullPointerException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        User user1 = new User("John", "Smith", (byte) 43);
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());

        User user2 = new User("Jim", "Beam", (byte) 23);
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());

        User user3 = new User("Jack", "Daniels", (byte) 33);
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());

        User user4 = new User("Sam", "Lincoln", (byte) 53);
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        System.out.println(userService.getAllUsers().toString());

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
