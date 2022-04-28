package com.v.database;

import com.v.database.model.Salary;
import com.v.database.model.Streamer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DBUtils {
    // Утильный класс для непосредственной работы с БД
    public static List<Streamer> getStreamers() throws SQLException {
        List<Streamer> streamers = new ArrayList<Streamer>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "select * from streamers;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int followers = resultSet.getInt("followers");

                Streamer otherOne = new Streamer(id, name, followers);
                streamers.add(otherOne);
            }
        }
        return streamers;
    }

    public static Optional<Streamer> getStreamerById(int id) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "select * from streamers where id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int followers = resultSet.getInt("followers");

                Streamer otherOne = new Streamer(id, name, followers);
                return Optional.of(otherOne);
            }
        }
        return Optional.empty();
    }

    public static boolean deleteStreamer(int id) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "" +
                    "delete from salary where streamer_id = ?;" +
                    "delete from streamers where id = ?;" +
                    "";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setInt(2, id);
            statement.execute();
        }
        return getStreamerById(id).isEmpty();
    }

    public static Optional<Streamer> createStreamer(int id, String name, int followers) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "insert into streamers (id, name, followers) values ( ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setInt(3, followers);

            int count = statement.executeUpdate();
            if (count == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    int streamerId = resultSet.getInt(1);
                    return getStreamerById(streamerId);
                }
            }
            return Optional.empty();
        }
    }


    // Замена всех значений стримера по id
    public static Optional<Streamer> updateStreamer(int id, String name, int followers) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "update streamers set name = '?', followers = ? where id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.setInt(2, followers);
            statement.setInt(3, id);

            int count = statement.executeUpdate(sql);
            if (count == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    return getStreamerById(id);
                }
            }
        }
        return Optional.empty();
    }

    // Замена по id только имени стримера
    public static Optional<Streamer> updateStreamer(int id, String name) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "update streamers set name = '?' where id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.setInt(2, id);

            int count = statement.executeUpdate(sql);
            if (count == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    return getStreamerById(id);
                }
            }
        }
        return Optional.empty();
    }

    // Замена по id только колличества подписчиков стримера
    public static Optional<Streamer> updateStreamer(int id, int followers) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "update streamers set followers = ? where id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, followers);
            statement.setInt(2, id);

            int count = statement.executeUpdate(sql);
            if (count == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    return getStreamerById(id);
                }
            }
        }
        return Optional.empty();
    }

    public static List<Salary> getSalary() throws SQLException {
        List<Salary> allSalary = new ArrayList<Salary>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "select * from salary;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id_streamer = resultSet.getInt("streamer_id");
                Long card_number = resultSet.getLong("card_number");
                int salary = resultSet.getInt("salary");
                Salary otherOne = new Salary(id_streamer, card_number, salary);
                allSalary.add(otherOne);
            }
        }
        return allSalary;
    }

    public static Optional<Salary> getSalaryById(int id) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "select * from salary where id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Long cared_number = resultSet.getLong("card_number");
                int salary = resultSet.getInt("salary");
                Salary otherOne = new Salary(id, cared_number, salary);
                return Optional.of(otherOne);
            }
        }
        return Optional.empty();
    }

    public static boolean deleteSalary(int id) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "delete from streamers where id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
        }
        return getSalaryById(id).isEmpty();
    }

    public static Optional<Salary> createSalary(int id_streamer, Long card_number, int salary) throws SQLException {

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "insert into salary (id, card_number, salary) values ( ?, ?, ?);";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id_streamer);
            statement.setLong(2, card_number);
            statement.setInt(3, salary);

            int count = statement.executeUpdate(sql);
            if (count == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    return getSalaryById(id_streamer);
                }
            }
        }
        return Optional.empty();
    }


    // Замена всех значений по id
    public static Optional<Salary> updateSalary(int id_streamer, Long card_number, int salary) throws SQLException {
        Optional<Salary> checkBuff = getSalaryById(id_streamer);
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "update salary set card_number = ?, salary = ? where id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, card_number);
            statement.setInt(2, salary);
            statement.setInt(3, id_streamer);

            int count = statement.executeUpdate(sql);
            if (count == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    return getSalaryById(id_streamer);
                }
            }
        }
        return Optional.empty();
    }

    // Замена по id только номера карты
    public static Optional<Salary> updateSalary(int id_streamer, Long card_number) throws SQLException {
        Optional<Salary> checkBuff = getSalaryById(id_streamer);
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "update salary set card_number = ? where id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, card_number);
            statement.setInt(2, id_streamer);

            int count = statement.executeUpdate(sql);
            if (count == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    return getSalaryById(id_streamer);
                }
            }
        }
        return Optional.empty();
    }

    // Замена по id только запрлаты
    public static Optional<Salary> updateSalary(int id_streamer, int salary) throws SQLException {
        Optional<Salary> checkBuff = getSalaryById(id_streamer);
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "update salary set card_number = ?, salary = ? where id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, salary);
            statement.setInt(2, id_streamer);

            int count = statement.executeUpdate(sql);
            if (count == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    return getSalaryById(id_streamer);
                }
            }
        }
        return Optional.empty();
    }
}
