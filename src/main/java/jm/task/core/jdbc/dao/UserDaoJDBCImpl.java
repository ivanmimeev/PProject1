package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection conn = Util.connection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String table = "       CREATE TABLE IF NOT EXISTS `user`" +
                "  (`id` INT NOT NULL AUTO_INCREMENT," +
                "  `name` VARCHAR(45) NOT NULL," +
                "  `lastName` VARCHAR(45) NOT NULL," +
                "  `age` TINYINT NOT NULL," +
                "        PRIMARY KEY (`id`));";
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate(table);
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    public void dropUsersTable() {
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS user");
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedstatement = conn.prepareStatement("INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)")) {
            preparedstatement.setString(1, name);
            preparedstatement.setString(2, lastName);
            preparedstatement.setByte(3, age);
            preparedstatement.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate("DELETE FROM `user` WHERE `ID` = id");
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM `user`");) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                list.add(user);
            }
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return list;

    }

    public void cleanUsersTable() {
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate("TRUNCATE FROM user");
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
