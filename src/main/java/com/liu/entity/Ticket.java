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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (num != ticket.num) return false;
        if (Double.compare(ticket.price, price) != 0) return false;
        return level != null ? level.equals(ticket.level) : ticket.level == null;
    }

}