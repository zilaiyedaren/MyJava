package org.wuheng.mybatis.web.utils;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-7
 * Time: 下午4:31
 * To change this template use File | Settings | File Templates.
 */
public class NumberStatisticUtil {
    //用于保存各指标的统计数值
    private ConcurrentHashMap<String,StatisticHandler> statisticMap=new ConcurrentHashMap<String, StatisticHandler>();

    //指标的单位统计时间，默认为1分钟
    private HashMap<String,Long> intervalMap=new HashMap<String, Long>();

    //为指标设定单位统计时间
    public void setIntervalMap(String key,long interval){
        if(interval<=0){
            throw new IllegalArgumentException("interval must great than 0.");
        }
        intervalMap.put(key,interval);
    }
    //  获取指定指标，前一次统计值

    public long getPreValue(String key){
        StatisticHandler statisticHandler=statisticMap.get(key);
        if (statisticHandler==null){
            return 0L;
        }
        return statisticHandler.getPreValue();
    }
    //获取指定指标，当前统计值
    public long getValue(String key) {
        StatisticHandler statisticHandler = statisticMap.get(key);
        if (statisticHandler == null) {
            return 0L;
        }

        return statisticHandler.getValue();
    }
    //为指定指标增加指定量，并返回当前统计值
    public long addAndGet(String key, long delta) {
        StatisticHandler statisticHandler = statisticMap.get(key);
        //
        if (statisticHandler == null) {
            Long interval = intervalMap.get(key);
            if (interval == null) {
                statisticHandler = new DefaultStatisticHandlerImpl();
            } else {
                statisticHandler = new DefaultStatisticHandlerImpl(interval);
            }
            StatisticHandler existedHandler = statisticMap.putIfAbsent(key, statisticHandler);
            // 处理并发
            if (existedHandler != null) {
                statisticHandler = existedHandler;
            }
        }

        return statisticHandler.addValue(delta);
    }

    // 为指定指标加1，并返回当前统计值
    public long incrementAndGet(String key) {
        return addAndGet(key, 1);
    }



    private interface StatisticHandler{
        //获取当前一次统计值
        long getPreValue();
        //获取当前统计值
        long getValue();
        //增加指定值后，返回当前统计值
        long addValue(long delta);
    }
    // 以分钟为单位的统计算法
    private static class DefaultStatisticHandlerImpl implements StatisticHandler{
        private long interval=60*1000;
        private AtomicLong preValue=new AtomicLong();
        private AtomicLong value=new AtomicLong();
        private long time;

        private DefaultStatisticHandlerImpl() {
        }

        private DefaultStatisticHandlerImpl(long interval) {
            this.interval = interval;
        }

        @Override
        public long getPreValue() {
            checkTime();
            return preValue.get();  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public long getValue() {
            checkTime();
            return value.get();  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public long addValue(long delta) {
            checkTime();
            return value.addAndGet(delta);  //To change body of implemented methods use File | Settings | File Templates.
        }

        private void checkTime(){
            long now=System.currentTimeMillis();
            if(now>time+interval){
                // 此处加锁后，重新做判断，以避免并发调用
                preValue.set(value.get());
                value.set(0L);
                time=now;
            }
        }
    }
}
