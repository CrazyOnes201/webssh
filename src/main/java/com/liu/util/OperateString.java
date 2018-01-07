package com.liu.util;

/**
 * Created by Crazy Ones on 2018/1/7.
 */
public class OperateString {

    public static boolean stringIsEmpty(String s) {
        if(s == null || "".equals(s)) {
            return true;
        }

        return false;
    }
}
