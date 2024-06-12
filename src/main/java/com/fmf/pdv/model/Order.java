package com.fmf.pdv.model;

import com.fmf.pdv.util.Helpers;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String date;
    private List<OrderItem> items;
    
    public Order() {
        items = new ArrayList<>();
    }

    public String getDate() {
        return date;
    }

    private void setDate(String date) {
        this.date = date;
    }

    public double getTotal() {
        double total = 0;
        
        for (OrderItem item : items) {
            total += item.getTotalPrice();
        }
        
        return total;
    }
    
    public String getTotalFormmated() {
        return Helpers.moneyFormat(this.getTotal());
    }

    public void addProduct(Product product, int quantity) {
        OrderItem item = this.items.stream()
            .filter(i -> product.getId() == i.getProduct().getId())
            .findAny()
            .orElse(null);
        
        if (item != null) {
            item.updateOrder(quantity);
        } else {
            OrderItem newItem = new OrderItem(product, quantity);
            this.items.add(newItem);
        }
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
    
    public void clearItems() {
        this.items.clear();
    }
}
