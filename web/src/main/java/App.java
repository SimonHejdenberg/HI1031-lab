package web.src.main.java;

import java.sql.*;

public class App {
    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public static void main(String[] args) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab1", "root", "Ltz7ZC366BPt4MZ");
            if (con != null) {
                System.out.println("Connection succesfull!");
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
