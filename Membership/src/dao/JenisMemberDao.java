package dao;

import db.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import model.JenisMember;

public class JenisMemberDao {

    // Method untuk menambahkan data ke tabel 'jenis_member'
    public int insert(JenisMember jenisMember) {
        int result = -1;  // Menyimpan hasil operasi SQL, -1 jika gagal
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            // Menggunakan PreparedStatement untuk menghindari SQL Injection
            PreparedStatement statement = connection.prepareStatement("INSERT INTO jenis_member (id, nama) VALUES (?, ?)");
            statement.setString(1, jenisMember.getId());  // Mengisi nilai parameter pertama (id)
            statement.setString(2, jenisMember.getNama());  // Mengisi nilai parameter kedua (nama)

            result = statement.executeUpdate();  // Menjalankan query dan mendapatkan hasil eksekusi

        } catch (SQLException e) {
            e.printStackTrace();  // Menangani jika terjadi kesalahan SQL
        }
        return result;  // Mengembalikan hasil eksekusi
    }

    // Method untuk memperbarui data di tabel 'jenis_member'
    public int update(JenisMember jenisMember) {
        int result = -1;  // Menyimpan hasil operasi SQL, -1 jika gagal
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            // Query SQL untuk update data
            PreparedStatement statement = connection.prepareStatement("UPDATE jenis_member SET nama = ? WHERE id = ?");
            statement.setString(1, jenisMember.getNama());  // Mengisi nama baru
            statement.setString(2, jenisMember.getId());  // Menentukan id data yang ingin diupdate

            result = statement.executeUpdate();  // Menjalankan query dan mendapatkan hasil eksekusi

        } catch (SQLException e) {
            e.printStackTrace();  // Menangani jika terjadi kesalahan SQL
        }
        return result;  // Mengembalikan hasil eksekusi
    }

    // Method untuk menghapus data dari tabel 'jenis_member'
    public int delete(JenisMember jenisMember) {
        int result = -1;  // Menyimpan hasil operasi SQL, -1 jika gagal
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM jenis_member WHERE id = ?")) {
            
            statement.setString(1, jenisMember.getId());  // Menentukan id data yang ingin dihapus
            result = statement.executeUpdate();  // Menjalankan query dan mendapatkan hasil eksekusi

        } catch (SQLException e) {
            e.printStackTrace();  // Menangani jika terjadi kesalahan SQL
        }
        return result;  // Mengembalikan hasil eksekusi
    }

    // Method untuk mengambil semua data dari tabel 'jenis_member'
    public List<JenisMember> findAll() {
        List<JenisMember> list = new ArrayList<>();  // Menyimpan hasil pengambilan data
        
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM jenis_member")) {
            
            // Loop untuk memproses setiap baris dalam result set
            while (resultSet.next()) {
                JenisMember jenisMember = new JenisMember();
                jenisMember.setId(resultSet.getString("id"));  // Mengisi id dari result set
                jenisMember.setNama(resultSet.getString("nama"));  // Mengisi nama dari result set
                list.add(jenisMember);  // Menambahkan ke list
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Menangani jika terjadi kesalahan SQL
        }
        return list;  // Mengembalikan list hasil pengambilan data
    }
}