package com.fmf.pdv.dao;

import com.fmf.pdv.model.OrderItemChart;
import com.fmf.pdv.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SaleDAO {
    public List<OrderItemChart> getMaxItems() throws Exception {
        List<OrderItemChart> items = new ArrayList<>();

        String sql = "select count(oi.id) total, p.name from order_items oi join products p on p.id = oi.product_id group by p.name order by total desc limit 10";
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pstm = con.prepareStatement(sql);
        ResultSet rset = pstm.executeQuery();

        while(rset.next()) {
            OrderItemChart item = new OrderItemChart(rset.getString("name"), rset.getInt("total"));

            items.add(item);
        }

        return items;
    }
}
