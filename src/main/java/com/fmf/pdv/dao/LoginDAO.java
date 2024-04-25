package com.fmf.pdv.dao;

import com.fmf.pdv.dto.LoginDTO;
import com.fmf.pdv.model.User;
import com.fmf.pdv.util.ConnectionFactory;
import com.password4j.Password;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {

    public LoginDTO login(String username, String password) {
        LoginDTO loginDTO = new LoginDTO(false);
        try {
            String sql = "SELECT * FROM users WHERE username = ?";
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, username);
            ResultSet rset = pstm.executeQuery();

            if (rset.next()) {
                User user = new User();

                user.setId(rset.getInt("id"));
                user.setUsername(rset.getString("username"));
                user.setPassword(rset.getString("password"));
                user.setEmail(rset.getString("email"));
                user.setAdmin(rset.getBoolean("admin"));
                user.setActive(rset.getBoolean("active"));

                boolean passwordValid = Password.check(password, user.getPassword()).withScrypt();

                if (passwordValid) {
                    loginDTO = new LoginDTO(user, passwordValid);
                }
            }
        } catch (Exception ex) {
            System.out.println("Login erro: " + ex.getMessage());
        }

        return loginDTO;
    }
}
