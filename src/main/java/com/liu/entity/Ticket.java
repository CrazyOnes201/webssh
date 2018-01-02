package com.liu.entity;

/**
 * Created by Crazy Ones on 2017/12/28.
 * 只包含票类和对应张数
 */
public class Ticket {
    private String level;
    private int num;
    private double price;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "level='" + level + '\'' +
                ", num=" + num +
                ", price=" + price +
                '}';
    }
}