import java.sql.*;

public class App {
    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public static void main(String[] args) {
        String connectionUrl =
                "jdbc:sqlserver://localhost:3306;"
                        + "database=Lab 1;"
                        + "user=root@localhost;"
                        + "password=Ltz7ZC366BPt4MZ;" //Ltz7ZC366BPt4MZ
                        + "encrypt=true;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;";

        try (Connection connection = DriverManager.getConnection(connectionUrl);) {
            // Code here.
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
