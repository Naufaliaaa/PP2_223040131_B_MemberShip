package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnection {
    // URL database MySQL yang akan dihubungkan
    private final static String DB_URL = "jdbc:mysql://localhost:3306/pp2_membership";
    // Username untuk database
    private final static String DB_USER = "root";
    // Password untuk database
    private final static String DB_PASS = "1234";

    // Singleton instance untuk MySqlConnection
    private static MySqlConnection instance;

    // Method untuk mendapatkan instance tunggal (singleton) dari MySqlConnection
    public static MySqlConnection getInstance() {
        if (instance == null) {
            instance = new MySqlConnection();  // Membuat instance baru jika belum ada
        }
        return instance;
    }

    // Method untuk mendapatkan koneksi ke database
    public Connection getConnection() {
        Connection connection = null;
        try {
            // Memuat driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Membuat koneksi ke database
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (Exception e) {
            e.printStackTrace();  // Menangani kesalahan dengan mencetak stack trace
        }
        return connection;  // Mengembalikan koneksi yang dibuat
    }
}
