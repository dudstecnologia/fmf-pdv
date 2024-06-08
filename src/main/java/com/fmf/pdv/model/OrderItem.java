package com.fmf.pdv.model;

public class OrderItem {
    private Product product;
    private int quantity;
    private double totalPrice;
    
    public OrderItem(Product product, int quantity) {
        this.setProduct(product);
        this.setQuantity(quantity);

        this.setTotalPrice(this.getProduct().getPrice() * this.getQuantity());
    }
    
    public void updateOrder(int quantity) {
        this.setQuantity(this.quantity + quantity);

        this.setTotalPrice(this.getProduct().getPrice() * this.getQuantity());
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
