package sk.posam.fsa.jdbc;

import java.sql.*;

public class JdbcApplicationWithExceptionHandling {

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

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT count(*) FROM actor");
            resultSet.next();
            int count = resultSet.getInt(1);

            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
