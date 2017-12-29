package com.liu.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

/**
 * Created by Crazy Ones on 2017/12/28.
 */
@Entity
public class Traininfo implements Serializable {
    private String id;
    private String trainId;
    private String station;
    private Time startTime;
    private Time endTime;
    private int rank;
    private int miles;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "trainId")
    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    @Basic
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

    @Basic
    @Column(name = "rank")
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Basic
    @Column(name = "miles")
    public int getMiles() {
        return miles;
    }

    public void setMiles(int miles) {
        this.miles = miles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Traininfo traininfo = (Traininfo) o;

        if (rank != traininfo.rank) return false;
        if (miles != traininfo.miles) return false;
        if (id != null ? !id.equals(traininfo.id) : traininfo.id != null) return false;
        if (trainId != null ? !trainId.equals(traininfo.trainId) : traininfo.trainId != null) return false;
        if (station != null ? !station.equals(traininfo.station) : traininfo.station != null) return false;
        if (startTime != null ? !startTime.equals(traininfo.startTime) : traininfo.startTime != null) return false;
        return endTime != null ? endTime.equals(traininfo.endTime) : traininfo.endTime == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (trainId != null ? trainId.hashCode() : 0);
        result = 31 * result + (station != null ? station.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + rank;
        result = 31 * result + miles;
        return result;
    }

    @Override
    public String toString() {
        return "Traininfo{" +
                "id='" + id + '\'' +
                ", trainId='" + trainId + '\'' +
                ", station='" + station + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", rank=" + rank +
                ", miles=" + miles +
                '}';
    }
}
