package org.wuheng.mybatis.web.service.entertainment;

import org.wuheng.mybatis.web.formBean.ApkFrom;
import org.wuheng.mybatis.web.slient.pojo.Apk;
import org.wuheng.mybatis.web.utils.DataGridResult;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-13
 * Time: 下午9:56
 * To change this template use File | Settings | File Templates.
 */
public interface ApkService {
    public DataGridResult listApk(ApkFrom apkFrom);
    public void createApk(Apk apk);
    public void updateApk(Apk apk);
    public void deleteApk(Long... apkIds);
}
