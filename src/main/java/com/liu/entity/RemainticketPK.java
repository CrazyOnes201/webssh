package com.liu.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

public class RemainticketPK implements Serializable {
    private String trainId;
    private int startStationId;
    private int endStationId;
    private int level;
    private Date date;

    @Column(name = "trainId")
    @Id
    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    @Column(name = "startStationId")
    @Id
    public int getStartStationId() {
        return startStationId;
    }

    public void setStartStationId(int startStationId) {
        this.startStationId = startStationId;
    }

    @Column(name = "endStationId")
    @Id
    public int getEndStationId() {
        return endStationId;
    }

    public void setEndStationId(int endStationId) {
        this.endStationId = endStationId;
    }

    @Column(name = "level")
    @Id
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Column(name = "date")
    @Id
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

        RemainticketPK that = (RemainticketPK) o;

        if (startStationId != that.startStationId) return false;
        if (endStationId != that.endStationId) return false;
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
        result = 31 * result + level;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
