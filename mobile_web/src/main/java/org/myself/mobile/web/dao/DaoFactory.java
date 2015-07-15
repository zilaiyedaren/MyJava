package org.myself.mobile.web.dao;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 14-11-9
 * Time: 下午7:55
 * To change this template use File | Settings | File Templates.
 */
//工厂类，利用工厂得到DAO
public class DaoFactory {
    private static DaoFactory daoFactory=new DaoFactory();
    private String daoName;

    private DaoFactory(){
        daoName="DaoForMySql";
    }

    public static DaoFactory getInstance(){
        return daoFactory;
    }
    public IDao getDao(){
        IDao iDao=null;
        if(daoName.equalsIgnoreCase("DaoForMySql")){
            iDao=new DaoForMySql();
        }else if(daoName.equalsIgnoreCase("DaoForOracle")){
            iDao=new DaoForOracle();
        }
        return iDao;
    }
}
