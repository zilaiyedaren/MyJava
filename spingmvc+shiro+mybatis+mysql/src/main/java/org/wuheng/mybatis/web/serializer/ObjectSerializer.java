package org.wuheng.mybatis.web.serializer;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-5-29
 * Time: 下午3:54
 * To change this template use File | Settings | File Templates.
 */
public class ObjectSerializer implements Serializer {
    @Override
    public void init() throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public byte[] serialize(Object o) throws Exception {
        ByteArrayOutputStream outputStream=null;
        ObjectOutputStream oos=null;
        try {
            outputStream=new ByteArrayOutputStream();
            oos=new ObjectOutputStream(outputStream);
            oos.writeObject(o);
            return outputStream.toByteArray();
        } finally {
            if(oos!=null){
                oos.close();
            }
            if(outputStream!=null){
                outputStream.close();
            }
        }
    }

    @Override
    public Object deSerializer(byte[] bytes) throws Exception {
        ObjectInputStream ois=null;
        try {
            ois=new ObjectInputStream(new ByteArrayInputStream(bytes));
            return ois.readObject();
        } finally {
            if(ois!=null){
                ois.close();
            }
        }
    }

    @Override
    public void register(Class<?> clazz) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
