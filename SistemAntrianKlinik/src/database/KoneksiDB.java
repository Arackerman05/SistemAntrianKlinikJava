package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KoneksiDB {
    private static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/klinik_db";
                String user = "root";
                String pass = ""; // password default XAMPP
                conn = DriverManager.getConnection(url, user, pass);
                System.out.println("Koneksi berhasil.");
            } catch (SQLException e) {
                System.out.println("Koneksi gagal: " + e.getMessage());
            }
        }
        return conn;
    }
}
