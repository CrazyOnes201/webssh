package com.liu.entity;

import javax.persistence.*;

@Entity
@IdClass(TrainmilePK.class)
public class Trainmile {
    private String trainId;
    private String station;
    private int miles;

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

        Trainmile trainmile = (Trainmile) o;

        if (miles != trainmile.miles) return false;
        if (trainId != null ? !trainId.equals(trainmile.trainId) : trainmile.trainId != null) return false;
        if (station != null ? !station.equals(trainmile.station) : trainmile.station != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = trainId != null ? trainId.hashCode() : 0;
        result = 31 * result + (station != null ? station.hashCode() : 0);
        result = 31 * result + miles;
        return result;
    }
}
