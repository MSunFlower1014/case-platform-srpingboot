package com.ma.vue.boot.util;

import com.ma.vue.boot.aop.OperationLog;
import com.ma.vue.boot.controller.CaseEntityController;

import java.lang.reflect.Method;

public class AnnotationNote {

    public static void main(String[] args) {
        Class c = CaseEntityController.class;

        Method[] methods = c.getMethods();
        for(Method m : methods){
            boolean annotationPresent = m.isAnnotationPresent(OperationLog.class);
            System.out.println(m + " - annotationPresent is "+annotationPresent);
        }
    }
}
