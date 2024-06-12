package com.fmf.pdv.dao;

import com.fmf.pdv.model.Order;
import com.fmf.pdv.model.OrderItem;
import com.fmf.pdv.model.User;
import com.fmf.pdv.util.ConnectionFactory;
import com.fmf.pdv.util.Helpers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PdvDAO {
    public void insert(Order order, User user) throws Exception {
        String sqlOrder = "insert into orders (date, total, user_id) values (?, ?, ?)";
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pstmOrder = con.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);

        pstmOrder.setString(1, Helpers.dateToday());
        pstmOrder.setDouble(2, order.getTotal());
        pstmOrder.setInt(3, user.getId());
        pstmOrder.execute();

        ResultSet rs = pstmOrder.getGeneratedKeys();

        if (rs.next()) {
            int orderId = rs.getInt(1);

            for (OrderItem item: order.getItems()) {
                String sqlItem = "insert into order_items (order_id, product_id, quantity, unit_price, total_price) values (?, ?, ?, ?, ?)";
                PreparedStatement pstmItem = con.prepareStatement(sqlItem);

                pstmItem.setInt(1, orderId);
                pstmItem.setInt(2, item.getProduct().getId());
                pstmItem.setInt(3, item.getQuantity());
                pstmItem.setDouble(4, item.getProduct().getPrice());
                pstmItem.setDouble(5, item.getTotalPrice());
                pstmItem.execute();

                pstmItem.close();
            }
        }

        pstmOrder.close();
        con.close();
    }
}
