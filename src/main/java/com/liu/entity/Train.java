package com.liu.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Train {
    private String trainId;
    private String startStation;
    private String endStation;
    private String seatType;

    @Id
    @Column(name = "trainId")
    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    @Basic
    @Column(name = "startStation")
    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    @Basic
    @Column(name = "endStation")
    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    @Basic
    @Column(name = "seatType")
    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Train train = (Train) o;

        if (trainId != null ? !trainId.equals(train.trainId) : train.trainId != null) return false;
        if (startStation != null ? !startStation.equals(train.startStation) : train.startStation != null) return false;
        if (endStation != null ? !endStation.equals(train.endStation) : train.endStation != null) return false;
        return seatType != null ? seatType.equals(train.seatType) : train.seatType == null;
    }

    @Override
    public int hashCode() {
        int result = trainId != null ? trainId.hashCode() : 0;
        result = 31 * result + (startStation != null ? startStation.hashCode() : 0);
        result = 31 * result + (endStation != null ? endStation.hashCode() : 0);
        result = 31 * result + (seatType != null ? seatType.hashCode() : 0);
        return result;
    }
}
