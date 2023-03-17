package com.yanxia.study.utils;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 连接的工具类，用于从数据源中获取一个连接，并且实现和线程的绑定
 */
public class ConnectionUtils {
    private ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private DataSource dataSource;

    /**
     * 获取当前线程上的连接
     */
    public Connection getThreadConnection() {
        //判断线程上是否有连接
        try {
            //先从ThreadLocal获取连接
            Connection connection = connectionThreadLocal.get();
            if (connection == null) {
                //如果没有连接，从数据源里获取一个连接，存入ThreadLocal中
                connection = dataSource.getConnection();
                connectionThreadLocal.set(connection);
            }
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 把连接和线程解绑
     */
    public void removeConnection(){
        connectionThreadLocal.remove();
    }
}
