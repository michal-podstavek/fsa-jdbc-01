package sk.posam.fsa.jdbc;

import java.sql.*;

public class JdbcApplicationWithTryWithResourcesStatement {

    private static final String URL = "jdbc:postgresql://localhost:5432/dvdrental";
    private static final String USER = "postgres";
    private static final String PASSWORD = "a";


    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver"); // pre Driver typu 4 uz netreba
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try (
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT count(*) FROM actor")
        ) {
            resultSet.next();
            int count = resultSet.getInt(1);

            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
