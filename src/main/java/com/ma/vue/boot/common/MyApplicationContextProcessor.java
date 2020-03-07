package com.ma.vue.boot.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 容器启动处理特定逻辑的多种方法
 * ApplicationRunner.run和ApplicationListener.onApplicationEvent方法将在IOC容器完全初始化后执行
 */
@Component
public class MyApplicationContextProcessor implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent>,
        ApplicationRunner, SmartLifecycle, InitializingBean, BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(MyApplicationContextProcessor.class);

    /**
     * 最先执行，IOC容器未初始化完成
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("MyApplicationContextProcessor --------  setApplicationContext  ");
    }

    /**
     * 第三个执行，IOC容器初始化完成
     * @param contextRefreshedEvent
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("MyApplicationContextProcessor --------  onApplicationEvent  ");
    }

    /**
     * 第二个执行，IOC容器未初始化完成
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("MyApplicationContextProcessor --------  afterPropertiesSet  ");
    }

    /**
     * 第四个执行，IOC容器初始化完成
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("MyApplicationContextProcessor --------  run  ");
    }

    @Override
    public void start() {
        logger.info("MyApplicationContextProcessor --------  start  ");
    }

    @Override
    public void stop() {
        logger.info("MyApplicationContextProcessor --------  stop  ");
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
