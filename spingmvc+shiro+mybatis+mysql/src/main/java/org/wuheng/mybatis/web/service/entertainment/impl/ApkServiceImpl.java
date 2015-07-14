package org.wuheng.mybatis.web.service.entertainment.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wuheng.mybatis.web.dao.entertainment.ApkDao;
import org.wuheng.mybatis.web.formBean.ApkFrom;
import org.wuheng.mybatis.web.service.entertainment.ApkService;
import org.wuheng.mybatis.web.shiro.cache.CacheUtil;
import org.wuheng.mybatis.web.slient.pojo.Apk;
import org.wuheng.mybatis.web.utils.DataGridResult;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-13
 * Time: 下午9:57
 * To change this template use File | Settings | File Templates.
 */
@Service("apkService")
public class ApkServiceImpl implements ApkService {
    @Autowired
    private ApkDao apkDao;

    @Override
    public DataGridResult listApk(ApkFrom apkFrom) {
        apkFrom.initOffset();
        int apkCount=apkDao.getApkCount(apkFrom);
        List<Apk> list=apkDao.getApkList(apkFrom);
        return new DataGridResult(apkCount,list);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void createApk(Apk apk) {
        apkDao.createApk(apk);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateApk(Apk apk) {
        apkDao.updateApk(apk);
        CacheUtil.clearAuthInfo();
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteApk(Long... apkIds) {
        apkDao.deleteApk(apkIds);
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
