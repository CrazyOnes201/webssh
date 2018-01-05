package com.liu.action;

import com.liu.entity.Train;
import com.liu.service.TrainService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TrainAction extends ActionSupport{


    private String operation_type;//操作类型
    private Train train;
    @Autowired
    private TrainService trainService;

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public String getOperation_type() {
        return operation_type;
    }

    public void setOperation_type(String operation_type) {
        this.operation_type = operation_type;
    }

    @Override
    public String execute() throws Exception{
        System.out.println(operation_type+"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println(train.getTrainId()+"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println(train.getStartStation()+"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println(train.getEndStation()+"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println(train.getSeatType()+"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        if(operation_type.equals("a"))//增加
        {
            String result = trainService.addTrain(train);
            System.out.println(result+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            return SUCCESS;

        }
        else if(operation_type.equals("b"))//删除
        {

        }
        else if(operation_type.equals("c"))//修改
        {

        }
        else if(operation_type.equals("d"))//查找
        {
            //如果为空，返回所有车次
            if(train.getTrainId()==null){
                //TODO 未返回
                trainService.findAllTrain();
            }

            else {//根据trainId获取车次
                trainService.findTrain(train.getTrainId());
            }


        }

        return ERROR;
    }





}
