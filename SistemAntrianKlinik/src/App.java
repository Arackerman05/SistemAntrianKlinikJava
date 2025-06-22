import java.sql.Connection;

import database.KoneksiDB;

public class App {
    public static void main(String[] args) {
        Connection conn = KoneksiDB.getConnection();
        if (conn != null) {
            System.out.println("Tes koneksi berhasil!");
        }
    }
}
