package com.fmf.pdv.dao;

import com.fmf.pdv.model.Product;
import com.fmf.pdv.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public void insert(Product product) throws Exception {
        String sql = "insert into products (name, barcode, price, stock, active) values (?, ?, ?, ?, ?)";
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pstm = con.prepareStatement(sql);

        pstm.setString(1, product.getName());
        pstm.setString(2, product.getBarcode());
        pstm.setDouble(3, product.getPrice());
        pstm.setInt(4, product.getStock());
        pstm.setBoolean(5, product.isActive());

        pstm.execute();
        pstm.close();
        con.close();
    }


    public List<Product> getAll() throws Exception {
        List<Product> users = new ArrayList<>();

        String sql = "select * from products";
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pstm = con.prepareStatement(sql);
        ResultSet rset = pstm.executeQuery();

        while(rset.next()) {
            Product product = new Product();

            product.setId(rset.getInt("id"));
            product.setName(rset.getString("name"));
            product.setBarcode(rset.getString("barcode"));
            product.setPrice(rset.getDouble("price"));
            product.setStock(rset.getInt("stock"));
            product.setActive(rset.getBoolean("active"));

            users.add(product);
        }

        return users;
    }

    public Product getOne(int id) throws Exception {
        Product product = null;

        String sql = "select * from products where id = ?";
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setInt(1, id);
        ResultSet rset = pstm.executeQuery();

        if (rset.next()) {
            product = new Product();

            product.setId(rset.getInt("id"));
            product.setName(rset.getString("name"));
            product.setBarcode(rset.getString("barcode"));
            product.setPrice(rset.getDouble("price"));
            product.setStock(rset.getInt("stock"));
            product.setActive(rset.getBoolean("active"));
        }

        return product;
    }
    
    public Product getByBarcode(String barcode) throws Exception {
        Product product = null;

        String sql = "select * from products where barcode = ?";
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, barcode);
        ResultSet rset = pstm.executeQuery();

        if (rset.next()) {
            product = new Product();

            product.setId(rset.getInt("id"));
            product.setName(rset.getString("name"));
            product.setBarcode(rset.getString("barcode"));
            product.setPrice(rset.getDouble("price"));
            product.setStock(rset.getInt("stock"));
            product.setActive(rset.getBoolean("active"));
        }

        return product;
    }

    public void delete(String id) throws Exception {
        String sql = "DELETE FROM products WHERE id = ?";
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pstm = con.prepareStatement(sql);

        pstm.setString(1, id);

        pstm.execute();
        pstm.close();
        con.close();
    }

    public void update(Product product) throws Exception {
        String sql = "UPDATE products set name=?, barcode=?, price=?, stock=?, active=? WHERE id=?";

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pstm = con.prepareStatement(sql);

        pstm.setString(1, product.getName());
        pstm.setString(2, product.getBarcode());
        pstm.setDouble(3, product.getPrice());
        pstm.setInt(4, product.getStock());
        pstm.setBoolean(5, product.isActive());
        pstm.setInt(6, product.getId());

        pstm.execute();
        pstm.close();
        con.close();
    }

}
