package com.fmf.pdv.dao;

import com.fmf.pdv.model.ChartValue;
import com.fmf.pdv.model.Order;
import com.fmf.pdv.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SaleDAO {
    public List<ChartValue> getMaxItems() throws Exception {
        List<ChartValue> items = new ArrayList<>();

        String sql = "select count(oi.id) total, p.name from order_items oi join products p on p.id = oi.product_id group by p.name order by total desc limit 10";
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pstm = con.prepareStatement(sql);
        ResultSet rset = pstm.executeQuery();

        while(rset.next()) {
            ChartValue item = new ChartValue(rset.getString("name"), rset.getInt("total"));

            items.add(item);
        }

        return items;
    }

    public List<ChartValue> getLastSales() throws Exception {
        List<ChartValue> items = new ArrayList<>();

        String sql = "select sum(total) total, SUBSTRING(date, 1, 7) name from orders where MONTH(date) between MONTH(date) - 3 and MONTH(CURRENT_DATE()) group by date";
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pstm = con.prepareStatement(sql);
        ResultSet rset = pstm.executeQuery();

        while(rset.next()) {
            ChartValue item = new ChartValue(rset.getString("name"), rset.getInt("total"));

            items.add(item);
        }

        return items;
    }
    
    public List<Order> listOrdersFromPeriod(String dateStart, String dateEnd) throws Exception {
        List<Order> orders = new ArrayList<>();

        String sql = "select o.id, o.date, o.total, u.name from orders o join users u on u.id = o.user_id where o.date between ? and ?";
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pstm = con.prepareStatement(sql);

        pstm.setString(1, dateStart);
        pstm.setString(2, dateEnd);

        ResultSet rs = pstm.executeQuery();

        while(rs.next()) {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setDate(rs.getString("date"));
            order.setTotalLocal(rs.getDouble("total"));
            order.setNameUser(rs.getString("name"));

            orders.add(order);
        }

        return orders;
    }
}
