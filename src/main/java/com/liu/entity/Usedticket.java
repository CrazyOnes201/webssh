package com.liu.entity;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@IdClass(UsedticketPK.class)
public class Usedticket {
    private int userId;
    private String trainId;
    private String startStationId;
    private String endStationId;
    private String level;
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
    public String getStartStationId() {
        return startStationId;
    }

    public void setStartStationId(String startStationId) {
        this.startStationId = startStationId;
    }

    @Basic
    @Column(name = "endStationId")
    public String getEndStationId() {
        return endStationId;
    }

    public void setEndStationId(String endStationId) {
        this.endStationId = endStationId;
    }

    @Basic
    @Column(name = "level")
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
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
        if (Double.compare(that.money, money) != 0) return false;
        if (trainId != null ? !trainId.equals(that.trainId) : that.trainId != null) return false;
        if (startStationId != null ? !startStationId.equals(that.startStationId) : that.startStationId != null)
            return false;
        if (endStationId != null ? !endStationId.equals(that.endStationId) : that.endStationId != null) return false;
        if (level != null ? !level.equals(that.level) : that.level != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = userId;
        result = 31 * result + (trainId != null ? trainId.hashCode() : 0);
        result = 31 * result + (startStationId != null ? startStationId.hashCode() : 0);
        result = 31 * result + (endStationId != null ? endStationId.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        temp = Double.doubleToLongBits(money);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
