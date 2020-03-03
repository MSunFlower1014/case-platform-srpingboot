package com.ma.vue.boot;

import org.apache.ibatis.executor.CachingExecutor;
import org.apache.ibatis.executor.SimpleExecutor;
import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 框架笔记
 */
public class FrameNote {
    /**
     * SpringMVC的关键类
     * 继承关系：
     * DispatcherServlet -> FrameworkServlet -> HttpServletBean -> HttpServlet
     */
    private DispatcherServlet dispatcherServlet = new DispatcherServlet();



    /**
     * Mybatis的配置类
     * 默认配置可在构造器中查看
     * 该类由XMLConfigBuilder构建，其中会包括获取xml配置，并解析mapper文件
     */
    private Configuration configuration = new Configuration();

    /**
     *  Mybatis的sqlSession的工厂类，两种实现SqlSessionManager已被弃用
     *  该工厂类通过SqlSessionFactoryBuilder构建
     *  该工厂构建DefaultSqlSession来执行命令，获取映射器和管理事务
     */
    private DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);

    /**
     *  Mybatis缓存执行器，未命中缓存会调用传入的执行器执行方法
     */
    private CachingExecutor cachingExecutor= new CachingExecutor(new SimpleExecutor(configuration, new JdbcTransaction(null)));

    /**
     * Mybatis默认的参数处理，将配置文件转换为sql
     */
    private DefaultParameterHandler defaultParameterHandler =new DefaultParameterHandler(null,null,null);

    /**
     * Mybatis默认的结果处理器
     */
    private DefaultResultSetHandler defaultResultSetHandler = null;
}
