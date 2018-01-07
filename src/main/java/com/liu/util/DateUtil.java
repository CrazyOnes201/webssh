package com.liu.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Crazy Ones on 2018/1/6.
 */
public class DateUtil {

    /**
     * 将js传来的日期数据转换为java Date类型
     * @param dateString js传来的mm/dd/yyyy格式的日期数据
     * @return 标准Date型
     * @throws Exception
     */
    public static Date jsDateToJava(String dateString) throws Exception {
        String[] elemList = dateString.split("/");
        String pDate = elemList[2] + "-" + elemList[0] + "-" + elemList[1];
        Date result = DateFormat.getDateInstance().parse(pDate);

        return result;
    }

    /**
     * 将Date型数据转换成可用于hql查询的String格式数据
     * @param date 需要转换的Date型数据
     * @return 可用于hql查询的String格式
     */
    public static String javaDateToSql(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String result = sdf.format(date);

        return result;
    }

    /**
     * 将java String类型的日期数据转换为 js对应的日期数据格式
     * @param dateString 需要转换格式的日期字符串
     * @return 返回js对应的日期字符串格式
     */
    public static String javaDateToJs(String dateString) {
        String[] elemList = dateString.split("-");
        String result = elemList[1] + "/" + elemList[2] + "/" + elemList[0];

        return result;
    }

    public static Date getNowDateWithoutTime() {
        Date date = new Date();
        String withoutTimeString = javaDateToSql(date);
        try {
            Date result = DateFormat.getDateInstance().parse(withoutTimeString);
            return result;
        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @SuppressWarnings("Deprecated")
    public static Date getSomeDaysDate(Date date, int num) {
        Date tarDate = (Date)date.clone();
        String sDate = javaDateToSql(date);
        String sday = (sDate.split("-"))[2];
        int day = Integer.parseInt(sday);
        tarDate.setDate(day + num);

        return tarDate;
    }
}
