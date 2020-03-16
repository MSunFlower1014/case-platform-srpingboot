package com.ma.vue.boot.listener;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * spring容器启动之后执行，通过order顺序执行
 */
@Log
@Component
@Order(1)
public class AppRunnerListener implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        log.info("AppRunnerListener run >>>>>>>>>>>>>>>>>>");
    }
}
