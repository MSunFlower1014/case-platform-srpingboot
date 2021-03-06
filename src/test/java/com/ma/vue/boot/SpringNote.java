package com.ma.vue.boot;

import org.springframework.aop.framework.DefaultAopProxyFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ConcurrentSkipListSet;

public class SpringNote {
    /**
     * 根据xml创建spring容器
     * 调用refresh方法进行初始化-该方法实现了所有类的注册和增强
     * refresh流程：
     * 获取beanFactory -> 准备工厂(设置配置信息) -> 设置后处理器工厂 -> 创建后处理器类 -> 注册后处理器
     * -> 初始化消息源 -> 初始化发布事件委托类 -> 注册bean数据 -> 注册监听器 -> 完成beanFactory的初始化 -> 完成刷新
     * 其中DefaultSingletonBeanRegistry来存储bean注册信息
     * 通过三个map解决循环依赖
     * 1.Map<String, ObjectFactory<?>> singletonFactories   存放bean的工厂类
     * 2.Map<String, Object> earlySingletonObjects   如果初始化时发现依赖其他未初始化类，从singletonFactories删除，放入earlySingletonObjects
     * 3.Map<String, Object> singletonObjects   最后存放bean的map，从singletonFactories，earlySingletonObjects中删除
     * 具体细节可查看 DefaultSingletonBeanRegistry 的 getSingleton方法
     *
     * bean的生命周期
     * 实例化
     * 属性注入
     * 初始化
     * 调用
     * 销毁
     *
     * bean的作用域scope
     * 单例和原型
     *
     * 容器启动会初始化所有非懒加载的单例bean
     *
     * 默认支持支持bean覆盖，即同一配置文件重复配置报错，不同文件会发生覆盖
     * 默认支持循环依赖
     */
    private ClassPathXmlApplicationContext classPathXmlApplicationContext = null;

    private AbstractApplicationContext abstractApplicationContext;

    /**
     * AOP代理对象默认工厂
     *
     * 如果bean的类是接口或者类是JDK内部的代理类，其使用 JDK的动态代理类，只能代理接口包含方法（无法代理非public方法）
     * 其它情况是CGLIB通过继承生成子类来实现（无法代理final，private方法）
     *
     * spring AOP动态代理只支持对bean的方法执行代理，对于构造器或属性获取的代理需要通过aspectj静态代理通过修改编译类实现
     *
     * spring AOP的代理通过proxy和target实现，对于this调用的方法不会进行代理
     *
     * aspectj直接修改字节码文件，调用this方法也会进行代理
     *
     */
    private DefaultAopProxyFactory defaultAopProxyFactory = new DefaultAopProxyFactory();
}
