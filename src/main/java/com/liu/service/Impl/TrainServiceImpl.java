package com.liu.service.Impl;

import com.liu.dao.TrainDAO;
import com.liu.entity.Traininfo;
import com.liu.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Crazy Ones on 2017/12/28.
 */
@Service("trainService")
public class TrainServiceImpl implements TrainService {
    @Autowired
    TrainDAO trainDao;

    public void testDao() {
        List<Traininfo> daoList =  trainDao.findTrain("杭州", "北京");
        for(Traininfo element : daoList) {
            System.out.println(element);
        }
    }
}
