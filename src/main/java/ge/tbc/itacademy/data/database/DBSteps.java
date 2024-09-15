package ge.tbc.itacademy.data.database;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DBSteps {

    public Map<String, String> getLockedOutUserCredentials() {
        Map<String, String> credentials = new HashMap<>();
        try (Connection connection = MSQLConnection.connect()) {
            String query = """
                    SELECT username, password
                    FROM Users
                    where username = 'locked_out_user';
                    """;
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                credentials.put("userName", result.getString(1));
                credentials.put("password", result.getString(2));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return credentials;
    }

    public Map<String, String> getStandardUserCredentials() {
        Map<String, String> credentials = new HashMap<>();
        try (Connection connection = MSQLConnection.connect()) {
            String query = """
                    SELECT username, password
                    FROM Users
                    where username = 'standard_user';
                    """;
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                credentials.put("userName", result.getString(1));
                credentials.put("password", result.getString(2));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return credentials;
    }

    public Map<String, String> getProblematicUserCredentials() {
        Map<String, String> credentials = new HashMap<>();
        try (Connection connection = MSQLConnection.connect()) {
            String query = """
                    SELECT username, password
                    FROM Users
                    where username = 'problem_user';
                    """;
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                credentials.put("userName", result.getString(1));
                credentials.put("password", result.getString(2));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return credentials;
    }
}
