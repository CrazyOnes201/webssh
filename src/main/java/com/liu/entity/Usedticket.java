package com.liu.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@IdClass(UsedticketPK.class)
public class Usedticket {
    private int userId;
    private String trainId;
    private int startStationId;
    private int endStationId;
    private int level;
    private double money;
    private Date date;

    @Id
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "trainId")
    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    @Basic
    @Column(name = "startStationId")
    public int getStartStationId() {
        return startStationId;
    }

    public void setStartStationId(int startStationId) {
        this.startStationId = startStationId;
    }

    @Basic
    @Column(name = "endStationId")
    public int getEndStationId() {
        return endStationId;
    }

    public void setEndStationId(int endStationId) {
        this.endStationId = endStationId;
    }

    @Basic
    @Column(name = "level")
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Basic
    @Column(name = "money")
    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Id
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usedticket that = (Usedticket) o;

        if (userId != that.userId) return false;
        if (startStationId != that.startStationId) return false;
        if (endStationId != that.endStationId) return false;
        if (level != that.level) return false;
        if (Double.compare(that.money, money) != 0) return false;
        if (trainId != null ? !trainId.equals(that.trainId) : that.trainId != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = userId;
        result = 31 * result + (trainId != null ? trainId.hashCode() : 0);
        result = 31 * result + startStationId;
        result = 31 * result + endStationId;
        result = 31 * result + level;
        temp = Double.doubleToLongBits(money);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
