package org.wuheng.mybatis.web.serializer;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-28
 * Time: 下午6:36
 * To change this template use File | Settings | File Templates.
 */
public interface Serializer {
    public void init() throws Exception;

    public byte[] serialize(Object o) throws Exception;

    public Object deSerializer(byte[] bytes) throws Exception;

    public void register(Class<?> clazz);
}
