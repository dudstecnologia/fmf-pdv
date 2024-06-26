package com.fmf.pdv.model;

public class ChartValue {
    private String name;
    private int total;
    
    public ChartValue(String name, int total) {
        this.setName(name);
        this.setTotal(total);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
