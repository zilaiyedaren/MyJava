package org.myself.mobile.web.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-9
 * Time: 下午7:53
 * To change this template use File | Settings | File Templates.
 */
//DAO层接口实现，分MYSQL和ORACLE的实现
public class DaoForMySql implements IDao {

    //添加操作
    public void insert(User user) throws Exception{
        String sql="INSERT INTO user(username,password) VALUES(?,?)";
        PreparedStatement pstmt=null;
        DataBaseConnection dbc=null;

        try {
            // 连接数据库
            dbc=new DataBaseConnection();
            pstmt=dbc.getConnection().prepareStatement(sql);
            pstmt.setString(1,user.getName());
            pstmt.setString(2,user.getPassword());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }finally {
            dbc.close();
        }
    }

    //修改操作
    public void update(User user) throws Exception {
        String sql ="UPDATE user SET username=?,password=? WHERE userId=?";
        PreparedStatement pstmt = null ;
        DataBaseConnection dbc = null ;

        try{
            // 连接数据库
            dbc = new DataBaseConnection();
            pstmt = dbc.getConnection().prepareStatement(sql);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getId());
            // 进行数据库更新操作
            pstmt.executeUpdate();
            pstmt.close() ;
        }catch (Exception e){
            throw new Exception("操作出现异常") ;
        }finally{
            dbc.close() ;
        }
    }

    //删除操作
    public void delete(String userId) throws Exception {
        String sql = "DELETE FROM user WHERE userId=?" ;
        PreparedStatement pstmt = null ;
        DataBaseConnection dbc = null ;

        try{
            // 连接数据库
            dbc = new DataBaseConnection() ;
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            pstmt.setString(1, userId);
            // 进行数据库更新操作
            pstmt.executeUpdate() ;
            pstmt.close() ;
        }catch (Exception e){
            throw new Exception("操作出现异常") ;
        }finally{
            dbc.close() ;
        }
    }
    //按ID查询
    public User queryById(String userId) throws Exception{
        User user = null ;
        String sql = "SELECT * FROM user WHERE userId=?" ;
        PreparedStatement pstmt = null ;
        DataBaseConnection dbc = null ;

        try{
            // 连接数据库
            dbc = new DataBaseConnection() ;
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            pstmt.setString(1, userId);
            // 进行数据库查询操作
            ResultSet rs = pstmt.executeQuery() ;
            if(rs.next()){
                user = new User() ;
                user.setId(rs.getString(1));
                user.setName(rs.getString(2));
                user.setPassword(rs.getString(3));
            }
            rs.close() ;
            pstmt.close() ;
        }catch (Exception e){
            throw new Exception("操作出现异常") ;
        }finally{
            dbc.close() ;
        }
        return user ;
    }

    public List<User> queryAll() throws Exception{
        List<User> all = new ArrayList<User>() ;
        String sql = "SELECT * FROM user " ;
        PreparedStatement pstmt = null ;
        DataBaseConnection dbc = null ;

        try{
            // 连接数据库
            dbc = new DataBaseConnection() ;
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            // 进行数据库查询操作
            ResultSet rs = pstmt.executeQuery() ;
            while(rs.next()){
                User user = new User() ;
                user.setId(rs.getString(1));
                user.setName(rs.getString(2));
                user.setPassword(rs.getString(3));
                all.add(user) ;
            }
            rs.close() ;
            pstmt.close() ;
        }catch (Exception e){
            throw new Exception("操作出现异常") ;
        }finally{
            dbc.close() ;
        }
        return all ;
    }
}
