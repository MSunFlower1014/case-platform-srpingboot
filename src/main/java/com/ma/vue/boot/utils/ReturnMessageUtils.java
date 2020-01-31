package com.ma.vue.boot.utils;

import com.ma.vue.boot.common.Constant;

import java.util.HashMap;
import java.util.Map;

public class ReturnMessageUtils {
    public static Map returnErrorMessage(int resultCode,String errorMessage){
        HashMap result = new HashMap(2);
        result.put(Constant.System.RESULT_CODE,resultCode);
        result.put(Constant.System.RESULT_MESSAGE,errorMessage);
        return result;
    }
}
