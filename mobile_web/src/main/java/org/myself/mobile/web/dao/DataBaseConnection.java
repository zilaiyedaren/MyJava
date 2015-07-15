package org.myself.mobile.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-9
 * Time: 下午8:19
 * To change this template use File | Settings | File Templates.
 */
//数据库连接类
public class DataBaseConnection {
    //定义数据库驱动类
    private final static String  DB_DRIVER ="com.mysql.jdbc.Driver";
    //定义数据库连接URL
    private static final String DB_URL = "jdbc:mysql://localhost:3306/javaweb";
    //定义数据库的用户名和密码
    private static final String DB_USER="root";
    private static final String DB_PASSWORD="admin";
    //定义数据库连接对象
    private Connection conn = null ;

    //构造方法，加载驱动 ,通过修改此方法来定义针对不同数据库的连接方式
   public DataBaseConnection(){
       try {
           Class.forName(DB_DRIVER);
           this.conn= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);//core
       } catch (ClassNotFoundException e) {
           e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
       } catch (SQLException e) {
           e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
       }
   }
    // 取得数据库连接
    public Connection getConnection(){
        return conn ;
    }

    public void close(){
        try{
            conn.close() ;
        }catch (Exception e){
            System.out.println("数据库连接关闭失败");
        }
    }
}
