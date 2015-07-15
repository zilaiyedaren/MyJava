package org.myself.mobile.web.dao;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-9
 * Time: 下午8:10
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String[] args){
        DaoFactory daoFactory=DaoFactory.getInstance();
        IDao iDao=daoFactory.getDao();

        User user = new User();
        user.setId("12");
        user.setName("zhangsan");
        user.setPassword("123456");
        try {
            iDao.insert(user);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
