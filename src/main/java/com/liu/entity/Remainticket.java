package com.liu.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@IdClass(RemainticketPK.class)
public class Remainticket {
    private String trainId;
    private int startStationId;
    private int endStationId;
    private int ticket;
    private int level;
    private Date date;

    @Id
    @Column(name = "trainId")
    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    @Id
    @Column(name = "startStationId")
    public int getStartStationId() {
        return startStationId;
    }

    public void setStartStationId(int startStationId) {
        this.startStationId = startStationId;
    }

    @Id
    @Column(name = "endStationId")
    public int getEndStationId() {
        return endStationId;
    }

    public void setEndStationId(int endStationId) {
        this.endStationId = endStationId;
    }

    @Basic
    @Column(name = "ticket")
    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    @Id
    @Column(name = "level")
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

        Remainticket that = (Remainticket) o;

        if (startStationId != that.startStationId) return false;
        if (endStationId != that.endStationId) return false;
        if (ticket != that.ticket) return false;
        if (level != that.level) return false;
        if (trainId != null ? !trainId.equals(that.trainId) : that.trainId != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = trainId != null ? trainId.hashCode() : 0;
        result = 31 * result + startStationId;
        result = 31 * result + endStationId;
        result = 31 * result + ticket;
        result = 31 * result + level;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
