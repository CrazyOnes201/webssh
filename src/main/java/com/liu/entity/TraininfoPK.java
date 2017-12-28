package com.liu.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Crazy Ones on 2017/12/28.
 */
public class TraininfoPK implements Serializable{
    private String trainId;
    private String station;

    @Column(name = "trainId")
    @Id
    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    @Column(name = "station")
    @Id
    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TraininfoPK that = (TraininfoPK) o;

        if (trainId != null ? !trainId.equals(that.trainId) : that.trainId != null) return false;
        return station != null ? station.equals(that.station) : that.station == null;
    }

    @Override
    public int hashCode() {
        int result = trainId != null ? trainId.hashCode() : 0;
        result = 31 * result + (station != null ? station.hashCode() : 0);
        return result;
    }
}
