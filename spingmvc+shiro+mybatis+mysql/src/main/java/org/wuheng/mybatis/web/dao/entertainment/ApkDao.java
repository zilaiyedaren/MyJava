package org.wuheng.mybatis.web.dao.entertainment;

import org.wuheng.mybatis.web.formBean.ApkFrom;
import org.wuheng.mybatis.web.slient.pojo.Apk;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-13
 * Time: 下午9:55
 * To change this template use File | Settings | File Templates.
 */
public interface ApkDao {
    public int getApkCount(ApkFrom apkFrom);

    public List<Apk> getApkList(ApkFrom apkFrom);

    public void createApk(Apk apk);

    public void deleteApk(Long...apkIds);

    public void updateApk(Apk apk);
}
