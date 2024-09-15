package ge.tbc.itacademy.data.database;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MSQLConnection {
    public static Connection connect() throws SQLException {
        try {
            DriverManager.registerDriver(new SQLServerDriver());

            String jdbcUrl = DBConfigurations.getURL();
            String password = DBConfigurations.getPassword();
            String username = DBConfigurations.getUsername();
            return DriverManager.getConnection(jdbcUrl, username, password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}