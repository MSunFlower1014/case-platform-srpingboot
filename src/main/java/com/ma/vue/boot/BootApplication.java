package com.ma.vue.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StopWatch;

@EnableTransactionManagement(proxyTargetClass = true) //开启事务支持,然后在访问数据库的Service方法上添加注解@Transactional便可
@MapperScan(basePackages = { "com.ma.vue.boot" }) //Mapper类包扫描
@SpringBootApplication
public class BootApplication {
    private static final Logger log = LoggerFactory.getLogger(BootApplication.class);
    public static void main(String[] args) {
        log.info("....Begin to start the coupon platform application....");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ApplicationContext context = SpringApplication.run(BootApplication.class, args);
        // 执行时长(毫秒)
        stopWatch.stop();
        long executionTime = stopWatch.getTotalTimeMillis();
        log.info("....The application '{}' has been took '{}' milliseconds started!", context.getApplicationName(), executionTime);

    }

}
