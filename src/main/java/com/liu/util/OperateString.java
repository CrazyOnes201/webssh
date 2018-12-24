package com.liu.util;

/**
 * Created by Crazy Ones on 2018/1/7.
 */
public class OperateString {

    /**
     * 根据传入的字符串是否为空或者为null返回结果
     * @param s 需要检测的字符串
     * @return 返回是否为空
     */
    public static boolean stringIsEmpty(String s) {
        if(s == null || "".equals(s)) {
            return true;
        }

        return false;
    }
}
