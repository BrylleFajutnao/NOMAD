import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/nomad";
        String username = "root";
        String password = "bienoo1000";

        return DriverManager.getConnection(url, username, password);
    }
}

