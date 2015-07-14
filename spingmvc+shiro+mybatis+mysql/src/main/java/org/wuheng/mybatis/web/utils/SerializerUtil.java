package org.wuheng.mybatis.web.utils;

import org.wuheng.mybatis.web.serializer.ObjectSerializer;
import org.wuheng.mybatis.web.serializer.Serializer;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-7
 * Time: 下午5:07
 * To change this template use File | Settings | File Templates.
 */
public class SerializerUtil {
    private static Serializer serializer=new ObjectSerializer();

//    将Object对象序列化
    public static byte[] serializeObject(Object object) throws Exception{
        return serializer.serialize(object);
    }
//    将Object对象反序列化
    public static Object deserializeObject(byte[] bytes) throws Exception{
        return serializer.deSerializer(bytes);
    }
    //将一个Map中的key和value全部序列化，并生成一个新的Map。
    // 被序列化的Map中的key和value必须都是Serializable的。
    public final static   Map<byte[],byte[]> serializeMap(Map<?,?> map) throws Exception{
        if(map==null||map.isEmpty()){
            throw  new NullPointerException("map is null or empty");
        }
        Map<byte[],byte[]> newMap=new HashMap<byte[], byte[]>();
        for(Object object:map.keySet()){
            byte[] key=serializeObject(object);
            byte[] value=serializeObject(map.get(object));
           newMap.put(key,value);
        }
        return newMap;
    }
    //将已序列化过内部元素的Map反序列化。
    public static final Map<?,?> deserializeMap(Map<byte[],byte[]> map) throws Exception{
        if(map==null || map.isEmpty()){
            throw new NullPointerException("map is null or empty");

        }
        Map<Object,Object> newMap=new HashMap<Object, Object>();
        for(byte[] obj:map.keySet()){
            Object key=deserializeObject(obj);
            Object value=deserializeObject(map.get(obj));
            newMap.put(key,value);
        }
        return newMap;
    }

    /**
     * 将指定List中所有元素序列化，并生成新的List，被序列化的List中元素必须是Serializable的。
     */
    public final static List<byte[]> serializeList(List<?> list)
            throws Exception {
        if (list == null || list.size() == 0) {
            throw new NullPointerException("list is null or empty.");
        }
        List<byte[]> newList = new ArrayList<byte[]>();
        for (Object obj : list) {
            byte[] e = serializeObject(obj);
            newList.add(e);
        }
        return newList;
    }

    /**
     * 将内部元素已经序列化的List反序列化。
     */
    public final static List<?> deserializeList(List<byte[]> list)
            throws Exception {
        if (list == null || list.size() == 0) {
            throw new NullPointerException("list is null or empty.");
        }
        List<Object> newList = new ArrayList<Object>();
        for (byte[] obj : list) {
            Object e = deserializeObject(obj);
            newList.add(e);
        }
        return newList;
    }

    /**
     * 将指定Set的所有元素序列化，并生成新的Set，被序列化的Set的元素必须是Serializable的。
     */
    public final static Set<byte[]> serializeSet(Set<?> set) throws Exception {
        if (set == null || set.size() == 0) {
            throw new NullPointerException("set is null or empty.");
        }
        Set<byte[]> newSet = new HashSet<byte[]>();
        for (Object obj : set) {
            byte[] e = serializeObject(obj);
            newSet.add(e);
        }
        return newSet;
    }

    /**
     * 将内部元素已经序列化的Set反序列化。
     */
    public final static Set<?> deserializeSet(Set<byte[]> set) throws Exception {
        if (set == null || set.size() == 0) {
            throw new NullPointerException("set is null or empty.");
        }
        Set<Object> newSet = new HashSet<Object>();
        for (byte[] obj : set) {
            Object e = deserializeObject(obj);
            newSet.add(e);
        }
        return newSet;
    }
}
