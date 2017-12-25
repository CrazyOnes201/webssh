package com.liu.entity;

import javax.persistence.*;
import java.sql.Time;

@Entity
@IdClass(TraintimePK.class)
public class Traintime {
    private String trainId;
    private String station;
    private Time startTime;
    private Time endTime;

    @Id
    @Column(name = "trainId")
    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    @Id
    @Column(name = "station")
    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    @Basic
    @Column(name = "startTime")
    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "endTime")
    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Traintime traintime = (Traintime) o;

        if (trainId != null ? !trainId.equals(traintime.trainId) : traintime.trainId != null) return false;
        if (station != null ? !station.equals(traintime.station) : traintime.station != null) return false;
        if (startTime != null ? !startTime.equals(traintime.startTime) : traintime.startTime != null) return false;
        if (endTime != null ? !endTime.equals(traintime.endTime) : traintime.endTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = trainId != null ? trainId.hashCode() : 0;
        result = 31 * result + (station != null ? station.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        return result;
    }
}
