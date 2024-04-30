package com.fmf.pdv.dao;

import com.fmf.pdv.model.User;
import com.fmf.pdv.util.ConnectionFactory;
import com.password4j.Hash;
import com.password4j.Password;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public void insert(User user) throws Exception {
        String sql = "insert into users (name, username, password, email, admin, active) values (?, ?, ?, ?, ?, ?)";
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pstm = con.prepareStatement(sql);

        Hash hash = Password.hash(user.getPassword()).addRandomSalt().withScrypt();

        pstm.setString(1, user.getName());
        pstm.setString(2, user.getUsername());
        pstm.setString(3, hash.getResult());
        pstm.setString(4, user.getEmail());
        pstm.setBoolean(5, user.isAdmin());
        pstm.setBoolean(6, user.isActive());

        pstm.execute();
        pstm.close();
        con.close();
    }

    public List<User> getAll() throws Exception {
        List<User> users = new ArrayList<>();

        String sql = "select * from users";
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pstm = con.prepareStatement(sql);
        ResultSet rset = pstm.executeQuery();

        while(rset.next()) {
            User user = new User();

            user.setId(rset.getInt("id"));
            user.setName(rset.getString("name"));
            user.setUsername(rset.getString("username"));
            user.setEmail(rset.getString("email"));
            user.setAdmin(rset.getBoolean("admin"));
            user.setActive(rset.getBoolean("active"));

            users.add(user);
        }

        return users;
    }
    
    public User getOne(int id) throws Exception {
        User user = null;

        String sql = "select * from users where id = ?";
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setInt(1, id);
        ResultSet rset = pstm.executeQuery();

        if (rset.next()) {
            user = new User();

            user.setId(rset.getInt("id"));
            user.setName(rset.getString("name"));
            user.setUsername(rset.getString("username"));
            user.setEmail(rset.getString("email"));
            user.setAdmin(rset.getBoolean("admin"));
            user.setActive(rset.getBoolean("active"));
        }

        return user;
    }

    public void delete(String id) throws Exception {
        String sql = "DELETE FROM users WHERE codigo = ?";
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pstm = con.prepareStatement(sql);

        pstm.setString(1, id);

        pstm.execute();
        pstm.close();
        con.close();
    }
    
    public void update(User user) throws Exception {
        String sql = "UPDATE users set name=?, username=?, email=?, admin=?, active=? WHERE id=?";

        if (!user.getPassword().isEmpty()) {
            sql = "UPDATE users set name=?, username=?, email=?, admin=?, active=?, password=? WHERE id=?";
        }

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pstm = con.prepareStatement(sql);

        pstm.setString(1, user.getName());
        pstm.setString(2, user.getUsername());
        pstm.setString(3, user.getEmail());
        pstm.setBoolean(4, user.isAdmin());
        pstm.setBoolean(5, user.isActive());

        if (!user.getPassword().isEmpty()) {
            Hash hash = Password.hash(user.getPassword()).addRandomSalt().withScrypt();

            pstm.setString(6, hash.getResult());
            pstm.setInt(7, user.getId());
        } else {
            pstm.setInt(6, user.getId());
        }

        pstm.execute();
        pstm.close();
        con.close();
    }

}
