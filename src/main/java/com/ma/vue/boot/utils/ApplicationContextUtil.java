package com.ma.vue.boot.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.util.Objects;

public class ApplicationContextUtil {
    private static ApplicationContext context;

    public static ApplicationContext getApplicationContext(){
        if(context==null){
            initApplicationContext();
        }
        return context;
    }

    public static synchronized void initApplicationContext(){
        if(context==null){
            context = Objects.requireNonNull(ContextLoader.getCurrentWebApplicationContext()).getParent();
        }
    }
}
