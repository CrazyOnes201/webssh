package com.liu.entity;

import javax.persistence.*;

@Entity
@IdClass(TrainstationPK.class)
public class Trainstation {
    private String trainId;
    private String station;
    private int rank;

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
    @Column(name = "rank")
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trainstation that = (Trainstation) o;

        if (rank != that.rank) return false;
        if (trainId != null ? !trainId.equals(that.trainId) : that.trainId != null) return false;
        if (station != null ? !station.equals(that.station) : that.station != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = trainId != null ? trainId.hashCode() : 0;
        result = 31 * result + (station != null ? station.hashCode() : 0);
        result = 31 * result + rank;
        return result;
    }
}
