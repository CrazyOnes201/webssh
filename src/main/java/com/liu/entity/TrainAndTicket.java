package com.liu.entity;

import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by Crazy Ones on 2017/12/28.
 * 作为controller层返回的完整信息实体
 */
public class TrainAndTicket {
    private String trainId;
    private String beginStation;
    private String targetStation;
    private boolean isStart;
    private boolean isEnd;
    private Time beginTime;
    private Time targetTime;
    private Time lastTime;
    private String sDate;
    private ArrayList<Ticket> ticketList;

    public TrainAndTicket() {
        trainId = "####";
    }

    public TrainAndTicket(Traininfo startInfo, Traininfo targetInfo, String sDate) {
        trainId = startInfo.getTrainId();
        beginStation = startInfo.getStation();
        targetStation = targetInfo.getStation();
        beginTime = startInfo.getStartTime();
        targetTime = targetInfo.getStartTime();
        this.sDate = sDate;
        // 缺少对lastTime的计算
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getBeginStation() {
        return beginStation;
    }

    public void setBeginStation(String beginStation) {
        this.beginStation = beginStation;
    }

    public String getTargetStation() {
        return targetStation;
    }

    public void setTargetStation(String targetStation) {
        this.targetStation = targetStation;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public Time getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Time beginTime) {
        this.beginTime = beginTime;
    }

    public Time getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(Time targetTime) {
        this.targetTime = targetTime;
    }

    public Time getLastTime() {
        return lastTime;
    }

    public void setLastTime(Time lastTime) {
        this.lastTime = lastTime;
    }

    public String getsDate() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }

    public ArrayList<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(ArrayList<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    @Override
    public String toString() {
        return "TrainAndTicket{" +
                "trainId='" + trainId + '\'' +
                ", beginStation='" + beginStation + '\'' +
                ", targetStation='" + targetStation + '\'' +
                ", isStart=" + isStart +
                ", isEnd=" + isEnd +
                ", beginTime=" + beginTime +
                ", targetTime=" + targetTime +
                ", lastTime=" + lastTime +
                ", sDate='" + sDate + '\'' +
                ", ticketList=" + ticketList +
                '}';
    }
}
