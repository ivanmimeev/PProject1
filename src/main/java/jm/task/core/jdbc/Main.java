package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        userServiceImpl.createUsersTable();
        userServiceImpl.saveUser("Ivan", "Ivanov", (byte) 30);
        System.out.println("User с именем – Ivan добавлен в базу данных");
        userServiceImpl.saveUser("Alex", "Romanov", (byte) 36);
        System.out.println("User с именем – Alex добавлен в базу данных");
        userServiceImpl.saveUser("Viktor", "Sidorov", (byte) 21);
        System.out.println("User с именем – Viktor добавлен в базу данных");
        userServiceImpl.saveUser("Roman", "Andreev", (byte) 54);
        System.out.println("User с именем – Roman добавлен в базу данных");
        System.out.println(userServiceImpl.getAllUsers());
        userServiceImpl.cleanUsersTable();
        userServiceImpl.dropUsersTable();


    }
}
