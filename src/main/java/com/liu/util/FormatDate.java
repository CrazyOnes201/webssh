package com.liu.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Crazy Ones on 2018/1/6.
 */
public class FormatDate {
    public static Date jsDateToJava(String dateString) throws Exception {
        String[] elemList = dateString.split("/");
        String pDate = elemList[2] + "-" + elemList[0] + "-" + elemList[1];
        Date result = DateFormat.getDateInstance().parse(pDate);

        return result;
    }

    public static String javaDateToSql(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String result = sdf.format(date);

        return result;
    }

    public static String javaDateToJs(String dateString) {
        String[] elemList = dateString.split("-");
        String result = elemList[1] + "/" + elemList[2] + "/" + elemList[0];

        return result;
    }
}
