package dao;

import db.MySqlConnection;
import java.sql.Connection;
import java.sql.PrepardStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Resultset;
import java.until.List;
import java.until.ArrayList;
import model.JenisMember;

public class JenisMemberDao {
    public int insert (JenisMember jenisMember)
    {
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            PrepareStatement statement = connection.prepareStatment("insert into jenis_member (id, nama) values (?,?)");
            statement.setString(1, jenisMember.getID());
            statement.setString(2, jenisMember.getNama());

            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(JenisMember jenisMember) 
    {
        int result = -1;
        try(Connection connection = MySqlConnection.getImstance().getConnection();) {
            PreparedStattment statement = connection.prepareStatment("update jenis_member set nama = ? where id = ?");
            statement.setString(1, jenisMembe.getIdO());

            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(JenisMember jenisMember) {
    int result = -1;
    
    try (Connection connection = MySqlConnection.getInstance().getConnection();
         PreparedStatement statement = connection.prepareStatement("DELETE FROM jenis_member WHERE id = ?")) {

        statement.setString(1, jenisMember.getId());
        result = statement.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return result;
}
    public List<JenisMember> findAll() {
    List<JenisMember> list = new ArrayList<>();
    
    try (Connection connection = MySqlConnection.getInstance().getConnection();
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery("SELECT * FROM jenis_member")) {
        
        while (resultSet.next()) {
            JenisMember jenisMember = new JenisMember();
            jenisMember.setId(resultSet.getString("id"));
            jenisMember.setNama(resultSet.getString("nama"));
            list.add(jenisMember);
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return list;
}

}