package org.wuheng.framework.lucene5.util;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-22
 * Time: 下午9:22
 * To change this template use File | Settings | File Templates.
 */
public class UUIDGenerator {
    public static String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str=uuid.toString();
//        // 去掉"-"符号
        String temp=str.replace("-","");
       return str+","+temp;

    }

    //获取指定数量的UUID
    public static String[] getUUID(int number){
        if(number<1){
            return null;
        }
        String[] ss=new String[number];
        for(int i=0;i<number;i++){
            ss[i]=getUUID();
        }
        return ss;
    }
    public static void main(String[] strings){
        getUUID();
    }
}
