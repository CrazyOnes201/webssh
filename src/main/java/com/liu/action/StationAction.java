package com.liu.action;

import com.liu.entity.Traininfo;
import com.liu.service.StationService;
import com.liu.service.TicketService;
import com.liu.service.TrainService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

@Controller
public class StationAction extends ActionSupport {


    @Autowired
    private StationService stationService;

    private Traininfo traininfo;
    private String operation_type;//操作类型
    private String stationlist;
    private String starttimelist;
    private String endtimelist;
    private String milelist;


    public StationService getStationService() {
        return stationService;
    }

    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    public Traininfo getTraininfo() {
        return traininfo;
    }

    public void setTraininfo(Traininfo traininfo) {
        this.traininfo = traininfo;
    }

    public String getOperation_type() {
        return operation_type;
    }

    public void setOperation_type(String operation_type) {
        this.operation_type = operation_type;
    }

    public String getStationlist() {
        return stationlist;
    }

    public void setStationlist(String stationlist) {
        this.stationlist = stationlist;
    }

    public String getStarttimelist() {
        return starttimelist;
    }

    public void setStarttimelist(String starttimelist) {
        this.starttimelist = starttimelist;
    }

    public String getEndtimelist() {
        return endtimelist;
    }

    public void setEndtimelist(String endtimelist) {
        this.endtimelist = endtimelist;
    }

    public String getMilelist() {
        return milelist;
    }

    public void setMilelist(String milelist) {
        this.milelist = milelist;
    }

    @Override
    public String execute() throws Exception {
        if (operation_type.equals("a"))//增加
        {
            ArrayList<Traininfo> list = new ArrayList<Traininfo>();//所有要添加的车站
            ArrayList<ArrayList<String>> lists = new ArrayList<ArrayList<String>>();//存取前端各字段,0-站名，1-开始时间，2-结束时间，3-距离
            ArrayList<String> list1;//中间结果

            int count1 = 0;//一共多少站
            int count = 0;//一共多少站*4 用于验证
            int startI = 0;//字符串起始位置
            //记录有几站
            for (int i = 0; i < stationlist.length(); i++) {
                if (stationlist.charAt(i) == ';') {
                    count1++;
                }
            }

            //增加车站信息
            startI = 0;
            list1 = new ArrayList<String>();
            for (int i = 0; i < stationlist.length(); i++) {
                if (stationlist.charAt(i) == ';') {
                    count++;
                    list1.add(stationlist.substring(startI, i));
                    startI = i + 1;
                }
            }
            lists.add(list1);

            //增加开车时间
            startI = 0;
            list1 = new ArrayList<String>();
            for (int i = 0; i < starttimelist.length(); i++) {
                if (starttimelist.charAt(i) == ';') {
                    count++;
                    list1.add(starttimelist.substring(startI, i));

                    startI = i + 1;
                }
            }

            lists.add(list1);

            //增加离站时间
            list1 = new ArrayList<String>();
            startI = 0;
            for (int i = 0; i < endtimelist.length(); i++) {
                if (endtimelist.charAt(i) == ';') {
                    count++;
                    list1.add(endtimelist.substring(startI, i));

                    startI = i + 1;
                }
            }
            lists.add(list1);

            //增加车站距离
            list1 = new ArrayList<String>();
            startI = 0;
            for (int i = 0; i < milelist.length(); i++) {
                if (milelist.charAt(i) == ';') {
                    count++;
                    list1.add(milelist.substring(startI, i));
                    startI = i + 1;
                }
            }
            lists.add(list1);


            //开始set 到 traininfo了

            if (count == count1 * 4) {

                for (int k = 0; k < count1; k++) {
                    Traininfo traininfo1 = new Traininfo();

//                    lists.get(k).get(k)为第k组元素的值
                    String station = lists.get(0).get(k);
                    String starttime = lists.get(1).get(k);
                    String endtime = lists.get(2).get(k);
                    String mile = lists.get(3).get(k);


                    int shour = 0;
                    int smin = 0;
                    int flag = 1;
                    int start = 0;
                    for (int x = 0; x < starttime.length(); x++) {

                        if (starttime.charAt(x) == ':') {
                            if (flag == 1) {
                                shour = Integer.parseInt(starttime.substring(start, x));
                                flag++;
                                start = x + 1;
                            } else if (flag == 2)
                                smin = Integer.parseInt(starttime.substring(start, x));
                        }
                    }

                    int ehour = 0;
                    int emin = 0;
                    int flag1 = 1;
                    int start1 = 0;
                    for (int x = 0; x < starttime.length(); x++) {

                        if (starttime.charAt(x) == ':') {
                            if (flag1 == 1) {
                                ehour = Integer.parseInt(starttime.substring(start1, x));
                                flag++;
                                start1 = x + 1;
                            } else if (flag1 == 2)
                                emin = Integer.parseInt(starttime.substring(start1, x));
                        }
                    }
                    Time starttime1 = new Time(shour, smin, 00);
                    Time endtime1 = new Time(ehour, emin, 00);

                    traininfo1.setTrainId(traininfo.getTrainId());
                    traininfo1.setStation(station);
                    traininfo1.setStartTime(starttime1);
                    traininfo1.setEndTime(endtime1);
                    traininfo1.setMiles(Integer.parseInt(mile));
                    traininfo1.setRank(k + 1);

                    list.add(traininfo1);//添加一个站


                }
            }

            if (stationService.addStation(list).equals("success")) {
                return SUCCESS;
            }

            return ERROR;
        }


//        else if(operation_type.equals("b"))//删除
//        {
//            String result = trainService.deleteTrain(train);
//            if(result.equals("success"))
//                return SUCCESS;
//            else
//                return ERROR;
//
//        }
//        else if(operation_type.equals("c"))//修改
//        {
//            String result = trainService.updateTrain(train);
//            if(result.equals("success"))
//                return SUCCESS;
//            else
//                return ERROR;
//
//        }
//        else if(operation_type.equals("d"))//查找
//        {
//            //如果为空，返回所有车次
//            if(train.getTrainId()==null){
//                //TODO 未返回
//                trainService.findAllTrain();
//            }

//            else {//根据trainId获取车次
//                //TODO
//                trainService.findTrain(train.getTrainId());
//                String result = trainService.addTrain(train);
//                if(result.equals("success"))
//                    return SUCCESS;
//                else
//                    return ERROR;
//            }
//
//
//        }

        return ERROR;
    }







    }
