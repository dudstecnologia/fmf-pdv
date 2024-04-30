package com.fmf.pdv.util;

import com.password4j.Hash;
import com.password4j.Password;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbDAO {
    
    public boolean createTables() {
        try {
            String sql = "SHOW TABLES FROM pdv LIKE 'users'";
            Connection conn = ConnectionFactory.getConnection();
            Statement pstm = conn.createStatement();
            ResultSet rset = pstm.executeQuery(sql);

            if (!rset.next()) {
                conn.setAutoCommit(false);

                this.createTableUsers(conn);
                this.createTableProducts(conn);
                this.createTableOrders(conn);
                this.createTableOrderItems(conn);
                this.createAdminUser(conn);

                conn.commit();
                conn.close();
            }

            return true;
        } catch (Exception ex) {
            System.out.println("Erro ao criar tabelas: " + ex.getMessage());
        }

        return false;
    }
    
    private void executeQuery(Connection conn, String sql) throws Exception {
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
    }

    public void createTableUsers(Connection conn) throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS users("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "name VARCHAR(100) NOT NULL, "
                    + "username VARCHAR(50) NOT NULL, "
                    + "password TEXT NOT NULL, "
                    + "email VARCHAR(150) NOT NULL, "
                    + "admin BOOLEAN NOT NULL DEFAULT FALSE, "
                    + "active BOOLEAN NOT NULL DEFAULT TRUE"
                    + ")";

        this.executeQuery(conn, sql);
    }
    
    public void createTableProducts(Connection conn) throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS products("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "name VARCHAR(150) NOT NULL, "
                    + "price DECIMAL(10, 2) NOT NULL, "
                    + "stock INT NOT NULL DEFAULT 0, "
                    + "active BOOLEAN NOT NULL DEFAULT TRUE"
                    + ")";

        this.executeQuery(conn, sql);
    }
    
    public void createTableOrders(Connection conn) throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS orders("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, "
                    + "total DECIMAL(10, 2) NOT NULL DEFAULT 0, "
                    + "discount DECIMAL(10, 2) NOT NULL DEFAULT 0, "
                    + "user_id INT NOT NULL, "
                    + "FOREIGN KEY (user_id) REFERENCES users(id)"
                    + ")";

        this.executeQuery(conn, sql);
    }
    
    public void createTableOrderItems(Connection conn) throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS order_items("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "order_id INT NOT NULL, "
                    + "product_id INT NOT NULL, "
                    + "quantity INT NOT NULL, "
                    + "unit_price DECIMAL(10, 2) NOT NULL, "
                    + "total_price DECIMAL(10, 2) NOT NULL, "
                    + "FOREIGN KEY (order_id) REFERENCES orders(id), "
                    + "FOREIGN KEY (product_id) REFERENCES products(id)"
                    + ")";

        this.executeQuery(conn, sql);
    }

    public void createAdminUser(Connection conn) throws Exception {
        String sql = "INSERT INTO users (name, username, password, email, admin) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstm = conn.prepareStatement(sql);

        Hash hash = Password.hash("admin").addRandomSalt().withScrypt();

        pstm.setString(1, "Administrador");
        pstm.setString(2, "admin");
        pstm.setString(3, hash.getResult());
        pstm.setString(4, "admin@email.com");
        pstm.setInt(5, 1);

        pstm.execute();
        pstm.close();
    }
}
