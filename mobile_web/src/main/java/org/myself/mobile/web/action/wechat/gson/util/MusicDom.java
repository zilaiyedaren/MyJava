package org.myself.mobile.web.action.wechat.gson.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created with IntelliJ IDEA.
 * User: jimmy
 * Date: 13-8-1
 * Time: 下午4:44
 * To change this template use File | Settings | File Templates.
 */
public class MusicDom {

    public static Logger logger = LoggerFactory.getLogger(MusicDom.class);

    //解析DOM
    public static String dom(String uri){

        String address="";//歌曲的实际地址
        try{
            //建立工厂类
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            //建立Builder对象
            DocumentBuilder builder = factory.newDocumentBuilder();
            //将制定内容解析为XML文档，返回一个Document对象
            Document doc = builder.parse(uri);
            //建立NodeList
            NodeList url_list = doc.getElementsByTagName("url");//取得url标签列表
            //取得url标签下的所有子标签

            //取得encode标签内容
            Element e1 = (Element)url_list.item(0);
            address = e1.getElementsByTagName("encode").item(0).getFirstChild().getNodeValue();//加歌曲地址
            //取得decode标签内容
            Element e2 = (Element)url_list.item(0);

            String songName = e2.getElementsByTagName("decode").item(0).getFirstChild().getNodeValue();//加歌曲名
            logger.error(songName);
            songName.replace("&mid","&amp;mid");
            address += songName;
        }catch(Exception e){
            e.printStackTrace();
        }
        return address;
    }
}
