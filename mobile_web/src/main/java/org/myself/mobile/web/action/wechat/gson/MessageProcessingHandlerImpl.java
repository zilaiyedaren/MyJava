package org.myself.mobile.web.action.wechat.gson;

import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.myself.mobile.web.action.wechat.gson.bean.Articles;
import org.myself.mobile.web.action.wechat.gson.bean.InMessage;
import org.myself.mobile.web.action.wechat.gson.bean.Music;
import org.myself.mobile.web.action.wechat.gson.bean.OutMessage;
import org.myself.mobile.web.action.wechat.gson.inf.MessageProcessingHandler;
import org.myself.mobile.web.action.wechat.gson.util.MusicDom;
import org.myself.mobile.web.action.wechat.gson.util.WeChatUtil;
import org.myself.mobile.web.utils.Constants;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 自定义实现消息处理
 *
 * @author GodSon
 */
@Service
public class MessageProcessingHandlerImpl implements MessageProcessingHandler {


    private HotelInfoSearcher hotelSearcher = new HotelInfoSearcher();

//    @Autowired
//    private HotelService hotelService;

    @Override
    public OutMessage textTypeMsg(InMessage msg) {
        //图文消息
//        <xml>
//        <ToUserName><![CDATA[toUser]]></ToUserName>
//        <FromUserName><![CDATA[fromUser]]></FromUserName>
//        <CreateTime>12345678</CreateTime>
//        <MsgType><![CDATA[news]]></MsgType>
//        <ArticleCount>2</ArticleCount>
//        <ArticleDetail>
//        <item>
//        <Title><![CDATA[title1]]></Title>
//        <Description><![CDATA[description1]]></Description>
//        <PicUrl><![CDATA[picurl]]></PicUrl>
//        <Url><![CDATA[url]]></Url>
//        </item>
//        <item>
//        <Title><![CDATA[title]]></Title>
//        <Description><![CDATA[description]]></Description>
//        <PicUrl><![CDATA[picurl]]></PicUrl>
//        <Url><![CDATA[url]]></Url>
//        </item>
//        </ArticleDetail>
//        <FuncFlag>1</FuncFlag>
//        </xml>
        //音乐消息
//        <xml>
//        <ToUserName><![CDATA[toUser]]></ToUserName>
//        <FromUserName><![CDATA[fromUser]]></FromUserName>
//        <CreateTime>12345678</CreateTime>
//        <MsgType><![CDATA[music]]></MsgType>
//        <Music>
//        <Title><![CDATA[TITLE]]></Title>
//        <Description><![CDATA[DESCRIPTION]]></Description>
//        <MusicUrl><![CDATA[MUSIC_Url]]></MusicUrl>
//        <HQMusicUrl><![CDATA[HQ_MUSIC_Url]]></HQMusicUrl>
//        </Music>
//        </xml>
        OutMessage oms = new OutMessage();
        // 设置发送信息
        oms.setCreateTime(new Date().getTime());
        oms.setToUserName(msg.getFromUserName());
        oms.setFromUserName(msg.getToUserName());
//        Hotel hotel = hotelService.findByHotelName(msg.getContent());
//        if (msg.getContent().contains("周边酒店")) {
//            oms.setMsgType("text");
//            String content = "<a href='"+ Constants.mserverUrl+"maps/hotel_search.html'>点击查看周边酒店</a>";
//            oms.setContent(content);
//        } else if (msg.getContent().contains("周边银行")) {
//            oms.setMsgType("text");
//            String content = "<a href='"+ Constants.mserverUrl+"maps/bank_search.html'>点击查看周边银行</a>";
//            oms.setContent(content);
//        } else if (msg.getContent().contains("周边餐厅")) {
//                oms.setMsgType("text");
//                String content = "<a href='"+ Constants.mserverUrl+"maps/res_search.html'>点击查看周边餐厅</a>";
//                oms.setContent(content);
//        } else
        if (msg.getContent().contains("发送")) {
//            oms.setMsgType("news");
//            oms.setArticleCount(1);
//            List articleList = new ArrayList();
//            ArticleDetail hotelArticle = new ArticleDetail();
//            hotelArticle.setTitle("酒店促销新闻消息");
//            String[] content =  msg.getContent().split("发送");
//            if(content.length>1)
//                hotelArticle.setDescription(content[1]);
//            hotelArticle.setPicUrl("http://211.144.87.228/mhshibo/images/lidu_overview.jpg");
//            hotelArticle.setUrl("http://211.144.87.228/mhshibo/promotion/promotion_list.dhtml");
//            articleList.add(hotelArticle);
//            oms.setArticles(articleList);
//            oms.setFuncFlag(1);
            String[] content = msg.getContent().split("发送");
            if (content.length > 1) {
                WeChatUtil.sendBroadcastMessage(content[1]);
                oms.setMsgType("text");
                oms.setContent("成功发送内容为：" + content[1]);
            }
//        } else if (hotel != null) {
//            oms.setMsgType("news");
//            hotelSearcher.setHotelMessage(oms, msg);
        } else {
            String song = "";
            String singer = "";
            if (msg.getContent() != null) {
                song = msg.getContent().split(" ")[0];
                if (msg.getContent().split(" ").length > 1) {
                    singer = msg.getContent().split(" ")[1];
                }
            }
            //将会返回歌曲信息的XML
            //http://box.zhangmen.baidu.com/x?op=12&count=1&title=那些年$$胡夏$$$$
            String url_str = "http://box.zhangmen.baidu.com/x?op=12&count=1&title=" + song + "$$" + singer + "$$$$";

//            URL url = new URL(url_str);//新建URL对象
//            URLConnection conn = url.openConnection();//连接对象
//            conn.connect();//发送连接
//
//            InputStream input = conn.getInputStream();//获取目的的输入流
//            File file = new File("C:"+File.separator+music_name+".xml");
//            OutputStream output = new FileOutputStream(file);//输出流
//            byte []b = new byte[20240];
//            int len = input.read(b);
//            output.write(b,0,len);
            //Dom解析
            String address = MusicDom.dom(url_str);//播放地址 调用解析Dom解析的类
            if (address != null && address.length() > 20) {
                oms.setMsgType("music");
                Music music = new Music();
                music.setTitle(song + "--" + singer);
                music.setMusicUrl("<![CDATA[" + address + "]]>");
                music.setHQMusicUrl("<![CDATA[" + address + "]]>");
                music.setDescription(song + "--" + singer);
                oms.setMusic(music);
//                oms.setMsgType("text");
//                oms.setContent(address);
            } else {
                oms.setMsgType("text");
                String content = "抱歉，未找到匹配信息";
                oms.setContent(content);
            }
        }
//        oms.setMusicUrl("http://music.baidu.com/data/music/file?link=http://zhangmenshiting.baidu.com/data2/music/1386699/521408201600128.mp3?xcode=275358940d1c76f5a38b6b212ae94e69b9e1ff7e2ca8c7f5");
        return oms;
    }


    @Override
    public OutMessage locationTypeMsg(InMessage msg) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public OutMessage imageTypeMsg(InMessage msg) {
        //图文消息
//        <xml>
//        <ToUserName><![CDATA[toUser]]></ToUserName>
//        <FromUserName><![CDATA[fromUser]]></FromUserName>
//        <CreateTime>12345678</CreateTime>
//        <MsgType><![CDATA[news]]></MsgType>
//        <ArticleCount>2</ArticleCount>
//        <ArticleDetail>
//        <item>
//        <Title><![CDATA[title1]]></Title>
//        <Description><![CDATA[description1]]></Description>
//        <PicUrl><![CDATA[picurl]]></PicUrl>
//        <Url><![CDATA[url]]></Url>
//        </item>
//        <item>
//        <Title><![CDATA[title]]></Title>
//        <Description><![CDATA[description]]></Description>
//        <PicUrl><![CDATA[picurl]]></PicUrl>
//        <Url><![CDATA[url]]></Url>
//        </item>
//        </ArticleDetail>
//        <FuncFlag>1</FuncFlag>
//        </xml>
        OutMessage oms = new OutMessage();
        // 设置发送信息
        oms.setCreateTime(new Date().getTime());
        oms.setToUserName(msg.getFromUserName());
        oms.setFromUserName(msg.getToUserName());

        String url =  "http://api2.sinaapp.com/recognize/picture/?appkey=0020120430&appsecert=fa6095e123cd28fd&reqtype=text"
        +"&keyword="+msg.getPicUrl();
        HttpClient client =  new HttpClient();
        GetMethod method =  new GetMethod(url);
        String result= "八戒你也真调皮呀！我叫你不要乱扔东西，乱扔东西是不对的。";
        try {
            int statusCode = client.executeMethod(method);
            System.out.println(method.getResponseBodyAsString());
            result = method.getResponseBodyAsString();
            if(StringUtils.isNotBlank(method.getResponseBodyAsString())){
                JSONObject object = JSONObject.fromObject(method.getResponseBodyAsString());
                object = object.getJSONObject("text");
                result =(String)object.get("content");
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        oms.setMsgType("news");
        oms.setArticleCount(1);
        List articleList = new ArrayList();
        Articles hotelArticle = new Articles();
        hotelArticle.setTitle("面部识别");
        hotelArticle.setDescription(result);
        hotelArticle.setPicUrl(msg.getPicUrl());
        hotelArticle.setUrl("");
        articleList.add(hotelArticle);

        oms.setArticles(articleList);
        oms.setFuncFlag(1);
        return oms;
    }

    @Override
    public OutMessage linkTypeMsg(InMessage msg) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public OutMessage eventTypeMsg(InMessage msg) {
        OutMessage oms = new OutMessage();
        // 设置发送信息
        oms.setCreateTime(new Date().getTime());
        oms.setToUserName(msg.getFromUserName());
        oms.setFromUserName(msg.getToUserName());

        if ("subscribe".equalsIgnoreCase(msg.getEvent())) {
//            oms.setMsgType("text");
//            String content = "我这里有几百家酒店的客房信息和万首好歌给你听,回复酒店名称查看客房信息，或者歌曲名+空格+歌手畅听您喜欢的音乐";
//            oms.setContent(content);
            oms.setMsgType("news");
            oms.setArticleCount(1);
            List articleList = new ArrayList();
            Articles hotelArticle = hotelSearcher.getHotelArticle();

            articleList.add(hotelArticle);
            oms.setArticles(articleList);
            oms.setFuncFlag(1);
        } else if ("brief".equalsIgnoreCase(msg.getEventKey())) {
            oms.setMsgType("news");
            oms.setArticleCount(1);
            List articleList = new ArrayList();
            Articles hotelArticle = hotelSearcher.getHotelArticle();

            articleList.add(hotelArticle);
            oms.setArticles(articleList);
            oms.setFuncFlag(1);
        } else if ("room".equalsIgnoreCase(msg.getEventKey())) {
            oms.setMsgType("news");
            hotelSearcher.setRoomMessage(oms, msg);
        } else if ("song".equalsIgnoreCase(msg.getEventKey())) {
            oms.setMsgType("text");
            String content = "请回复歌曲名+空格+歌手畅听您喜欢的音乐";
            oms.setContent(content);
        } else if ("facility".equalsIgnoreCase(msg.getEventKey())) {
            oms.setMsgType("news");
            oms.setArticleCount(3);
            List articleList = new ArrayList();
            Articles hotelArticle1 = new Articles();
            hotelArticle1.setTitle(Constants.hotelName);
            hotelArticle1.setDescription("点击查看酒店设施");
            hotelArticle1.setPicUrl(Constants.mserverUrl+"images/banquet_04.jpg");
            hotelArticle1.setUrl(Constants.mserverUrl+"facilities/facilities.dhtml");
            Articles hotelArticle2 = new Articles();
            hotelArticle2.setTitle("餐饮美食");
            hotelArticle2.setDescription("餐饮美食");
            hotelArticle2.setPicUrl(Constants.mserverUrl+"images/banquet_04.jpg");
            hotelArticle2.setUrl(Constants.mserverUrl+"facilities/meeting/banquet_list.dhtml");
            Articles hotelArticle3 = new Articles();
            hotelArticle3.setTitle("会议会场");
            hotelArticle3.setDescription("会议会场");
            hotelArticle3.setPicUrl(Constants.mserverUrl+"images/meeting_00.jpg");
            hotelArticle3.setUrl(Constants.mserverUrl+"facilities/meeting/meeting_list.dhtml");
//            Articles hotelArticle4 = new Articles();
//            hotelArticle4.setTitle("餐饮酒吧");
//            hotelArticle4.setDescription("餐饮酒吧");
//            hotelArticle4.setPicUrl(Constants.mserverUrl+"images/dinner_type_1.jpg");
//            hotelArticle4.setUrl(Constants.mserverUrl+"facilities/dinner/dinner_list.dhtml");

            articleList.add(hotelArticle1);
            articleList.add(hotelArticle2);
            articleList.add(hotelArticle3);
//            articleList.add(hotelArticle4);
            oms.setArticles(articleList);
            oms.setFuncFlag(1);
        }  else if ("news".equalsIgnoreCase(msg.getEventKey())) {
            oms.setMsgType("news");
            oms.setArticleCount(1);
            List articleList = new ArrayList();
            Articles hotelArticle = hotelSearcher.getHotelArticle();

            articleList.add(hotelArticle);
            oms.setArticles(articleList);
            oms.setFuncFlag(1);
        }  else if ("wedding".equalsIgnoreCase(msg.getEventKey())){
            oms.setMsgType("news");
            oms.setArticleCount(5);
            List articleList = new ArrayList();
            Articles hotelArticle1 = new Articles();
            hotelArticle1.setTitle("一站式婚宴管家");
            hotelArticle1.setDescription("一站式婚宴管家");
            hotelArticle1.setPicUrl("http://mmbiz.qpic.cn/mmbiz/OIQicsa6Ej3FIoauowibPwgayVZnAVO50UODS9J7M6M7erxsjzoEib855dsicOnWroVzm9mWxwtZeneSriaXmHglejA/0");
            hotelArticle1.setUrl("http://mp.weixin.qq.com/s?__biz=MzA4NDAwOTQxMA==&mid=200100702&idx=1&sn=89c9660c8606fc58e0cddfe2878d94f2#rd");
            Articles hotelArticle2 = new Articles();
            hotelArticle2.setTitle("团队中心");
            hotelArticle2.setDescription("团队中心");
            hotelArticle2.setPicUrl("http://mmbiz.qpic.cn/mmbiz/OIQicsa6Ej3FIoauowibPwgayVZnAVO50U4vfuzJEibDeSznwMLVGLS595qBGO4nR3jXYghbHickYW7ibUkKMyCGicoA/0");
            hotelArticle2.setUrl("http://mp.weixin.qq.com/s?__biz=MzA4NDAwOTQxMA==&mid=200100702&idx=2&sn=1d7117d1268ef2ae85200dbdf736131a#rd");
            Articles hotelArticle3 = new Articles();
            hotelArticle3.setTitle("婚宴预订流程");
            hotelArticle3.setDescription("婚宴预订流程");
            hotelArticle3.setPicUrl("http://mmbiz.qpic.cn/mmbiz/OIQicsa6Ej3FIoauowibPwgayVZnAVO50UojHpg3EpEEyAaA7Dl2g8cA2QBh68ddWp8HicwBeXFeq0FuUX9EK0b7A/0");
            hotelArticle3.setUrl("http://mp.weixin.qq.com/s?__biz=MzA4NDAwOTQxMA==&mid=200100702&idx=3&sn=0faf2cce07c77a3832b1e63feacf55ee#rd");
            Articles hotelArticle4 = new Articles();
            hotelArticle4.setTitle("婚宴注意事项");
            hotelArticle4.setDescription("婚宴注意事项");
            hotelArticle4.setPicUrl("http://mmbiz.qpic.cn/mmbiz/OIQicsa6Ej3FzIJBCwK4Mc4Vs1Jbjicwyq5wnrTibF5QLUUMkzy4gpv9ncN6Y7RSZXPMxCrXIZSN9Eg3vOSpr9Xkw/0");
            hotelArticle4.setUrl("http://mp.weixin.qq.com/s?__biz=MzA4NDAwOTQxMA==&mid=200100702&idx=4&sn=a4d4c8e510e264101d527598a7453dc8#rd");
            Articles hotelArticle5 = new Articles();
            hotelArticle5.setTitle("主题布置欣赏");
            hotelArticle5.setDescription("主题布置欣赏");
            hotelArticle5.setPicUrl("http://mmbiz.qpic.cn/mmbiz/OIQicsa6Ej3HhksBibflrplicJvadeYicYxMVhncLuxHqaRPpHGCpaYWISDpfseibRl9W98O0ApqLjPfnAoFkSFiaERQ/0");
            hotelArticle5.setUrl("http://mp.weixin.qq.com/s?__biz=MzA4NDAwOTQxMA==&mid=200100702&idx=5&sn=8e61e81206af3c01fc9e08170d0aadc6#rd");

            articleList.add(hotelArticle1);
            articleList.add(hotelArticle2);
            articleList.add(hotelArticle3);
            articleList.add(hotelArticle4);
            articleList.add(hotelArticle5);
            oms.setArticles(articleList);
            oms.setFuncFlag(1);
        } else if ("travel".equalsIgnoreCase(msg.getEventKey())) {
            oms.setMsgType("news");
            oms.setArticleCount(8);
            List articleList = new ArrayList();
            Articles hotelArticle1 = new Articles();
            hotelArticle1.setTitle("【星空旅游】");
            hotelArticle1.setDescription("【星空旅游】");
            hotelArticle1.setPicUrl("http://mmbiz.qpic.cn/mmbiz/OIQicsa6Ej3FpXxtTjMib2PyH8wibEgWAdAvo9BREick4db4M6J9u75Bkbiba8QX2VaibMg69wqxVUWc89B5YwXIOBFQ/0");
            hotelArticle1.setUrl("http://mp.weixin.qq.com/s?__biz=MzA4NDAwOTQxMA==&mid=200122568&idx=1&sn=8a4c4cfc5c4bbef35276b05e7631b4c7#rd");
            Articles hotelArticle2 = new Articles();
            hotelArticle2.setTitle("近期特价团（普吉）");
            hotelArticle2.setDescription("近期特价团（普吉）");
            hotelArticle2.setPicUrl("http://mmbiz.qpic.cn/mmbiz/OIQicsa6Ej3FpXxtTjMib2PyH8wibEgWAdAF3pVK0b1NALUjRFa5Y0ibibUIicV6oNyEIETecaVTgsW5B6sm5CbvIeibQ/0");
            hotelArticle2.setUrl("http://mp.weixin.qq.com/s?__biz=MzA4NDAwOTQxMA==&mid=200122568&idx=2&sn=2a0cabf7f51484f816d473f75f3d8eb1#rd");
            Articles hotelArticle3 = new Articles();
            hotelArticle3.setTitle("近期特价团（柬埔寨）");
            hotelArticle3.setDescription("近期特价团（柬埔寨）");
            hotelArticle3.setPicUrl("http://mmbiz.qpic.cn/mmbiz/OIQicsa6Ej3FpXxtTjMib2PyH8wibEgWAdATKKUTFFgKuzicpynGj1pEvmLoZ9xtMjTFhvPFRt1ry2QztjyppYEHicA/0");
            hotelArticle3.setUrl("http://mp.weixin.qq.com/s?__biz=MzA4NDAwOTQxMA==&mid=200122568&idx=3&sn=7cced65e43cf8d9727f85e30f28b2c1c#rd");
            Articles hotelArticle4 = new Articles();
            hotelArticle4.setTitle("近期特价团（港澳）");
            hotelArticle4.setDescription("近期特价团（港澳）");
            hotelArticle4.setPicUrl("http://mmbiz.qpic.cn/mmbiz/OIQicsa6Ej3FpXxtTjMib2PyH8wibEgWAdAtgNBP5ODb57h0OSJuPibr15eGJJN3ZykyJ4C2OZGYbocsX3ZhuibmWZg/0");
            hotelArticle4.setUrl("http://mp.weixin.qq.com/s?__biz=MzA4NDAwOTQxMA==&mid=200122568&idx=4&sn=9b92cdac87cc8a5d2d2c4e7c20721c69#rd");
            Articles hotelArticle5 = new Articles();
            hotelArticle5.setTitle("近期特价团（巴厘岛）");
            hotelArticle5.setDescription("近期特价团（巴厘岛）");
            hotelArticle5.setPicUrl("http://mmbiz.qpic.cn/mmbiz/OIQicsa6Ej3FpXxtTjMib2PyH8wibEgWAdA0Kd5PQcFic2XzkjibIkTZbqQLLMnWamt9XbUicVCuub7bicQm6dvcqibRQg/0");
            hotelArticle5.setUrl("http://mp.weixin.qq.com/s?__biz=MzA4NDAwOTQxMA==&mid=200122568&idx=5&sn=b4942dbb9aaa852a7dc11ef11535d2d1#rd");
            Articles hotelArticle6 = new Articles();
            hotelArticle6.setTitle("近期特价团（阿联酋）");
            hotelArticle6.setDescription("近期特价团（阿联酋）");
            hotelArticle6.setPicUrl("http://mmbiz.qpic.cn/mmbiz/OIQicsa6Ej3FpXxtTjMib2PyH8wibEgWAdAM7XQIoZFV6deYUaNCmtibKDY3KpDw7EXx54XZYlHQImyGuLP3gkha8g/0");
            hotelArticle6.setUrl("http://mp.weixin.qq.com/s?__biz=MzA4NDAwOTQxMA==&mid=200122568&idx=6&sn=a48b5244eb5036e0ff6f4739433106cd#rd");
            Articles hotelArticle7 = new Articles();
            hotelArticle7.setTitle("近期特价团（夏威夷）");
            hotelArticle7.setDescription("近期特价团（夏威夷）");
            hotelArticle7.setPicUrl("http://mmbiz.qpic.cn/mmbiz/OIQicsa6Ej3FpXxtTjMib2PyH8wibEgWAdAP0dbPlvfELjLPA22VykOrePzaCK1rYmHZYG9rVWibtdXyYf9OrPlvNQ/0");
            hotelArticle7.setUrl("http://mp.weixin.qq.com/s?__biz=MzA4NDAwOTQxMA==&mid=200122568&idx=7&sn=9736f403211a2566accd3dfa9ae05c75#rd");
            Articles hotelArticle8 = new Articles();
            hotelArticle8.setTitle("近期特价团余位");
            hotelArticle8.setDescription("近期特价团余位");
            hotelArticle8.setPicUrl("http://mmbiz.qpic.cn/mmbiz/OIQicsa6Ej3FpXxtTjMib2PyH8wibEgWAdAurYKfngrKiaOhhWgLm6gf3QQB2pIGwW4jBJ5c4MkWicus9I9xdSxqZXg/0");
            hotelArticle8.setUrl("http://mp.weixin.qq.com/s?__biz=MzA4NDAwOTQxMA==&mid=200122568&idx=8&sn=c4550c737dc97159c96918582698ecd3#rd");

            articleList.add(hotelArticle1);
            articleList.add(hotelArticle2);
            articleList.add(hotelArticle3);
            articleList.add(hotelArticle4);
            articleList.add(hotelArticle5);
            articleList.add(hotelArticle6);
            articleList.add(hotelArticle7);
            articleList.add(hotelArticle8);
            oms.setArticles(articleList);
            oms.setFuncFlag(1);
        }  else if("weibo".equalsIgnoreCase(msg.getEventKey())){
            oms.setMsgType("news");
            oms.setArticleCount(2);
            List articleList = new ArrayList();
            Articles hotelArticle1 = new Articles();
            hotelArticle1.setTitle("微活动");
            hotelArticle1.setDescription("微活动");
            hotelArticle1.setPicUrl("http://mmbiz.qpic.cn/mmbiz/OIQicsa6Ej3FIoauowibPwgayVZnAVO50UQlPFtQwgzvXuMBUvpH46CA4yibCe28jB9sIbQEH6w7LKDW13JqmtRibA/0");
            hotelArticle1.setUrl("http://mp.weixin.qq.com/s?__biz=MzA4NDAwOTQxMA==&mid=200100657&idx=1&sn=3ce77a418d0af8f764c0aa7615a5a6e6#rd");
            Articles hotelArticle2 = new Articles();
            hotelArticle2.setTitle("往期微活动回顾");
            hotelArticle2.setDescription("往期微活动回顾");
            hotelArticle2.setPicUrl("http://mmbiz.qpic.cn/mmbiz/OIQicsa6Ej3F1bZM5aL0hBnuGO0gF3h5vJkMHs5RnumJzv2nSFtjXFjrevhOl4PQxDD3SZ3KBWz750t3CicFRtJg/0");
            hotelArticle2.setUrl("http://mp.weixin.qq.com/s?__biz=MzA4NDAwOTQxMA==&mid=200100657&idx=2&sn=83e625d31aaebba6ca4f2794bf4427ae#rd");

            articleList.add(hotelArticle1);
            articleList.add(hotelArticle2);

            oms.setArticles(articleList);
            oms.setFuncFlag(1);
        } else if ("book".equalsIgnoreCase(msg.getEventKey())) {
            oms.setMsgType("news");
            oms.setArticleCount(1);
            List articleList = new ArrayList();
            Articles hotelArticle = new Articles();
            hotelArticle.setTitle(Constants.hotelName + "客房预订");
            hotelArticle.setDescription(Constants.hotelName + "欢迎您,房间预订请点击");
            hotelArticle.setPicUrl(Constants.mserverUrl + "images/roomspec.jpg");
            hotelArticle.setUrl(Constants.mserverUrl + "booking/rooms_search.dhtml?from=wechat&openId=" + msg.getFromUserName());

            articleList.add(hotelArticle);
            oms.setArticles(articleList);
            oms.setFuncFlag(1);
        } else if ("bind".equalsIgnoreCase(msg.getEventKey())) {
            oms.setMsgType("text");
            String content = "<a href='" + Constants.mserverUrl + "wechatbind.dhtml?openId=" + msg.getFromUserName() + "'>点击进行会员或协议公司绑定</a>\n绑定后微信中无需登录就可预订和查询自己订单";
            oms.setContent(content);
        } else if ("reservation".equalsIgnoreCase(msg.getEventKey())) {
            oms.setMsgType("text");
            String content = "<a href='" + Constants.mserverUrl + "order/order_search.dhtml?openId=" + msg.getFromUserName() + "'>点击查询我的订单</a>";
            oms.setContent(content);
        }
        return oms;
    }

    @Override
    public OutMessage voiceTypeMsg(InMessage msg) {
        OutMessage oms = new OutMessage();
        // 设置发送信息
        oms.setCreateTime(new Date().getTime());
        oms.setToUserName(msg.getFromUserName());
        oms.setFromUserName(msg.getToUserName());
        if (msg.getRecognition().contains("帮助")) {
            oms.setMsgType("text");
            String content = "";
            oms.setContent(content);
        } else if (msg.getRecognition().contains("酒店介绍")) {
            oms.setMsgType("news");
            oms.setArticleCount(1);
            List articleList = new ArrayList();
            Articles hotelArticle = hotelSearcher.getHotelArticle();
            articleList.add(hotelArticle);
            oms.setArticles(articleList);
            oms.setFuncFlag(1);
        } else if (msg.getRecognition().contains("房型")) {
            oms.setMsgType("news");
            hotelSearcher.setRoomMessage(oms, msg);
        } else if ("song".equalsIgnoreCase(msg.getEventKey())) {
            oms.setMsgType("text");
            String content = "请回复歌曲名+空格+歌手畅听您喜欢的音乐";
            oms.setContent(content);
        } else if (msg.getRecognition().contains("设施")) {
            oms.setMsgType("news");
            oms.setArticleCount(3);
            List articleList = new ArrayList();
            Articles hotelArticle1 = new Articles();
            hotelArticle1.setTitle(Constants.hotelName);
            hotelArticle1.setDescription("点击查看酒店设施");
            hotelArticle1.setPicUrl(Constants.mserverUrl+"images/banquet_04.jpg");
            hotelArticle1.setUrl(Constants.mserverUrl+"facilities/facilities.dhtml");
            Articles hotelArticle2 = new Articles();
            hotelArticle2.setTitle("餐饮美食");
            hotelArticle2.setDescription("餐饮美食");
            hotelArticle2.setPicUrl(Constants.mserverUrl+"images/banquet_04.jpg");
            hotelArticle2.setUrl(Constants.mserverUrl+"facilities/meeting/banquet_list.dhtml");
            Articles hotelArticle3 = new Articles();
            hotelArticle3.setTitle("会议会场");
            hotelArticle3.setDescription("会议会场");
            hotelArticle3.setPicUrl(Constants.mserverUrl+"images/meeting_00.jpg");
            hotelArticle3.setUrl(Constants.mserverUrl+"facilities/meeting/meeting_list.dhtml");

            articleList.add(hotelArticle1);
            articleList.add(hotelArticle2);
            articleList.add(hotelArticle3);
            oms.setArticles(articleList);
            oms.setFuncFlag(1);
        } else if (msg.getRecognition().contains("预订")) {
            oms.setMsgType("news");
            oms.setArticleCount(1);
            List articleList = new ArrayList();
            Articles hotelArticle = new Articles();
            hotelArticle.setTitle(Constants.hotelName);
            hotelArticle.setDescription("点击进入客房预订");
            hotelArticle.setPicUrl(Constants.mserverUrl+"images/lidu_overview.jpg");
            hotelArticle.setUrl(Constants.mserverUrl+"booking/rooms_search.dhtml");

            articleList.add(hotelArticle);
            oms.setArticles(articleList);
            oms.setFuncFlag(1);
        } else if (msg.getRecognition().contains("促销")) {
            oms.setMsgType("news");
            oms.setArticleCount(1);
            List articleList = new ArrayList();
            Articles hotelArticle = new Articles();
            hotelArticle.setTitle(Constants.hotelName);
            hotelArticle.setDescription("点击进入优惠促销");
            hotelArticle.setPicUrl(Constants.mserverUrl+"images/lidu_overview.jpg");
            hotelArticle.setUrl(Constants.mserverUrl+"promotion/promotion_list.dhtml");

            articleList.add(hotelArticle);
            oms.setArticles(articleList);
            oms.setFuncFlag(1);
        } else if (msg.getRecognition().contains("发送")) {
            String voiceContent = msg.getRecognition();
            String[] message = voiceContent.split("发送");

            if (message.length >= 2) {
//                if ("密码ABC".equalsIgnoreCase(message[0])) {
//                    oms.setMsgType("text");
//                    oms.setContent("发送成功内容为：" + message[1]);
//                    WeChatUtil.sendBroadcastMessage(message[1]);
//                } else {
//                    oms.setMsgType("text");
//                    oms.setContent("群发密码错误");
//                }
                oms.setMsgType("text");
                oms.setContent("成功发送内容为：" + message[1]);
                WeChatUtil.sendBroadcastMessage(message[1]);
            }
        }   else if (msg.getRecognition().contains("周边酒店")) {
                oms.setMsgType("text");
                String content = "<a href='"+ Constants.mserverUrl+"maps/hotel_search.html'>点击查看周边酒店</a>";
                oms.setContent(content);
            } else if (msg.getRecognition().contains("周边银行")) {
                oms.setMsgType("text");
                String content = "<a href='"+ Constants.mserverUrl+"maps/bank_search.html'>点击查看周边银行</a>";
                oms.setContent(content);
            }  else if (msg.getRecognition().contains("周边餐厅")) {
                oms.setMsgType("text");
                String content = "<a href='"+ Constants.mserverUrl+"maps/res_search.html'>点击查看周边餐厅</a>";
                oms.setContent(content);
        } else {
            oms.setMsgType("text");
            String content = "不能理解您的录入内容：" + msg.getRecognition();
            oms.setContent(content);
        }
        return oms;
    }

}
