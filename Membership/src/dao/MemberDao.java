package dao;

import db.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import model.Member;
import model.JenisMember;

public class MemberDao {

    // Method untuk menambahkan data ke tabel 'member'
    public int insert(Member member) {
        int result = -1;  // Menyimpan hasil operasi SQL, -1 jika gagal
        
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO member (id, nama, jenis_member_id) VALUES (?, ?, ?)")) {

            // Mengisi parameter untuk query SQL
            statement.setString(1, member.getId());  // Mengisi id
            statement.setString(2, member.getNama());  // Mengisi nama
            statement.setString(3, member.getJenisMember().getId());  // Mengisi id jenis_member
            
            result = statement.executeUpdate();  // Menjalankan query dan mendapatkan hasil eksekusi

        } catch (SQLException e) {
            e.printStackTrace();  // Menangani jika terjadi kesalahan SQL
        }
        
        return result;  // Mengembalikan hasil eksekusi
    }

    // Method untuk memperbarui data di tabel 'member'
    public int update(Member member) {
        int result = -1;  // Menyimpan hasil operasi SQL, -1 jika gagal

        try (Connection connection = MySqlConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(
                 "UPDATE member SET nama = ?, jenis_member_id = ? WHERE id = ?")) {

            // Mengisi parameter untuk query SQL
            statement.setString(1, member.getNama());  // Mengisi nama baru
            statement.setString(2, member.getJenisMember().getId());  // Mengisi id jenis_member yang baru
            statement.setString(3, member.getId());  // Mengisi id member yang akan diupdate

            result = statement.executeUpdate();  // Menjalankan query dan mendapatkan hasil eksekusi

        } catch (SQLException e) {
            e.printStackTrace();  // Menangani jika terjadi kesalahan SQL
        }

        return result;  // Mengembalikan hasil eksekusi
    }

    // Method untuk menghapus data dari tabel 'member'
    public int delete(Member member) {
        int result = -1;  // Menyimpan hasil operasi SQL, -1 jika gagal

        try (Connection connection = MySqlConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM member WHERE id = ?")) {

            statement.setString(1, member.getId());  // Mengisi id member yang akan dihapus
            result = statement.executeUpdate();  // Menjalankan query dan mendapatkan hasil eksekusi

        } catch (SQLException e) {
            e.printStackTrace();  // Menangani jika terjadi kesalahan SQL
        }

        return result;  // Mengembalikan hasil eksekusi
    }

    // Method untuk mengambil semua data dari tabel 'member' dan relasi dengan tabel 'jenis_member'
    public List<Member> findAll() {
        List<Member> list = new ArrayList<>();  // Menyimpan hasil pengambilan data
        
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                 "SELECT member.id, member.nama, jenis_member.id AS jenis_member_id, jenis_member.nama AS jenis_member_nama " +
                 "FROM member JOIN jenis_member ON jenis_member.id = member.jenis_member_id")) {

            // Loop untuk memproses setiap baris dalam result set
            while (resultSet.next()) {
                Member member = new Member();  // Membuat objek Member baru
                member.setId(resultSet.getString("id"));  // Mengisi id member dari result set
                member.setNama(resultSet.getString("nama"));  // Mengisi nama member dari result set

                // Membuat objek JenisMember baru untuk relasi
                JenisMember jenisMember = new JenisMember();
                jenisMember.setId(resultSet.getString("jenis_member_id"));  // Mengisi id jenis_member dari result set
                jenisMember.setNama(resultSet.getString("jenis_member_nama"));  // Mengisi nama jenis_member dari result set

                member.setJenisMember(jenisMember);  // Menambahkan objek JenisMember ke objek Member
                list.add(member);  // Menambahkan objek Member ke list hasil
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Menangani jika terjadi kesalahan SQL
        }

        return list;  // Mengembalikan list hasil pengambilan data
    }
}
