package com.ma.vue.boot;

import org.springframework.aop.framework.DefaultAopProxyFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringNote {
    /**
     * 根据xml创建spring容器
     * 调用refresh方法进行初始化-该方法实现了所有类的注册和增强
     * 其中DefaultSingletonBeanRegistry来存储bean注册信息
     * 通过三个map解决循环依赖
     * 1.Map<String, ObjectFactory<?>> singletonFactories   存放bean的工厂类
     * 2.Map<String, Object> earlySingletonObjects   如果初始化时发现依赖其他未初始化类，从singletonFactories删除，放入earlySingletonObjects
     * 3.Map<String, Object> singletonObjects   最后存放bean的map，从singletonFactories，earlySingletonObjects中删除
     * 具体细节可查看 DefaultSingletonBeanRegistry 的 getSingleton方法
     */
    private ClassPathXmlApplicationContext classPathXmlApplicationContext = null;

    /**
     * AOP代理对象默认工厂
     * 如果bean的类是接口或者类是JDK内部的代理类，其使用 JDK的动态代理类
     * 其它情况是CGLIB来实现
     */
    private DefaultAopProxyFactory defaultAopProxyFactory = new DefaultAopProxyFactory();
}
