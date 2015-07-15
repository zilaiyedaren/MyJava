package org.myself.mobile.web.dao;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-9
 * Time: 下午7:48
 * To change this template use File | Settings | File Templates.
 */
//DAO层接口：
public interface IDao {
    // 增加操作
    public void insert(User user) throws Exception ;
    // 修改操作
    public void update(User user) throws Exception ;
    // 删除操作
    public void delete(String userId) throws Exception ;
    // 按ID查询操作
    public User queryById(String userId) throws Exception ;
    // 查询全部
    public List queryAll() throws Exception ;
}
