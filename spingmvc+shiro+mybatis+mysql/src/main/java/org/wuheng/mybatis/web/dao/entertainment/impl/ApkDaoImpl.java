package org.wuheng.mybatis.web.dao.entertainment.impl;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.summercool.mybatis.spring.support.SqlSessionDaoSupport;
import org.wuheng.mybatis.web.dao.entertainment.ApkDao;
import org.wuheng.mybatis.web.formBean.ApkFrom;
import org.wuheng.mybatis.web.formBean.NovelForm;
import org.wuheng.mybatis.web.slient.pojo.Apk;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-13
 * Time: 下午9:55
 * To change this template use File | Settings | File Templates.
 */
@Repository("apkDao")
public class ApkDaoImpl extends SqlSessionDaoSupport implements ApkDao {
    @Override
    public int getApkCount(ApkFrom apkFrom) {
        return (Integer) getSqlSession().selectOne("SHIRO-APK.getApkCount",apkFrom);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Apk> getApkList(ApkFrom apkFrom) {
        return getSqlSession().selectList("SHIRO-APK.getApkList",apkFrom);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void createApk(Apk apk) {
        getSqlSession().insert("SHIRO-APK.createApk",apk);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteApk(Long... apkIds) {
        for(int i=0;i<apkIds.length;i++){
            getSqlSession().delete("SHIRO-APK.deleteApk",apkIds[i]);
        }
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateApk(Apk apk) {
        getSqlSession().update("SHIRO-APK.updateApk",apk);
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
