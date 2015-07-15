package org.myself.mobile.web.action.wechat.gson;

import com.caucho.hessian.client.HessianProxyFactory;
import com.derbysoft.dhotelier.ws.DHotelierHessianService;
import com.derbysoft.dhotelier.ws.model.WSHotelRequest;
import com.derbysoft.dhotelier.ws.model.WSHotelResponse;
import com.derbysoft.dhotelier.ws.model.WSRoomTypeDetail;
import org.myself.mobile.web.action.wechat.gson.bean.Articles;
import org.myself.mobile.web.action.wechat.gson.bean.InMessage;
import org.myself.mobile.web.action.wechat.gson.bean.OutMessage;
import org.myself.mobile.web.utils.Constants;
import org.myself.mobile.web.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HotelInfoSearcher {

    private Logger logger = LoggerFactory.getLogger(HotelInfoSearcher.class);

//    private static final String DR6_ADMIN_URL = EnvironmentUtils.getValue("dh6.admin.url");

    public static String Lidu = "HIHG022648";

    public static String Balidao = "AA37A6ABBE9385180DA09F510D20C37E9362";

    public static String Jindu = "PD9AE00000C2307A0";

    public static String Guomao = "AA32C6244D7BC78638CEC874D390E7F66695";


    /**
     * Get Dh6  hotel info
     */
    public WSHotelResponse getHotelInfo(String hotelPassport, String hotelGlobalIP) throws Exception {
        String url = "http://" + hotelGlobalIP + "/dhotelier/remoting/DHotelierHessianService";
        WSHotelResponse wsResponse = new WSHotelResponse();
        HessianProxyFactory factory = new HessianProxyFactory();
        DHotelierHessianService hessionService = null;
        WSHotelRequest request = new WSHotelRequest();
        request.setHotelDerbyPassport(hotelPassport);
        request.setLanguageCode("zh_CN");
        try {
            hessionService = (DHotelierHessianService) factory.create(DHotelierHessianService.class, url);
            if (hessionService != null) {
                wsResponse = hessionService.queryHotel(request);
            }
        } catch (MalformedURLException e) {
            logger.error("occur exception: " + e.getMessage());
        }
        return wsResponse;
    }

//    protected String getHotelAdminInfo(String hotelPassport) throws Exception {
//        final int i1 = DR6_ADMIN_URL.indexOf(":");
//        final int i11 = DR6_ADMIN_URL.lastIndexOf(":");
//        final int i2 = DR6_ADMIN_URL.lastIndexOf("/");
//        final int i3 = DR6_ADMIN_URL.indexOf("/", i2);
//
//        String scheme = DR6_ADMIN_URL.substring(0, i1);
//        String host = DR6_ADMIN_URL.substring(i1 + 3, (i11 > i1) ? i11 : i2);
//        int port = (i11 > i1) ? Integer.parseInt(DR6_ADMIN_URL.substring(i11 + 1, i3)) : 80;
//        String context = DR6_ADMIN_URL.substring(i3);
//
//        String subUrl = "/restservices/hotel/mapcode/" + hotelPassport + ".cc";
//        URI uri = URIUtils.createURI(scheme, host, port, context + subUrl, null, null);
//        HttpGet httpGet = new HttpGet(uri);
//        HttpEntity entity = null;
//        try {
//            HttpResponse response = new DefaultHttpClient().execute(httpGet);
//            int statusCode = response.getStatusLine().getStatusCode();
//            if (statusCode == HttpStatus.OK.value()) {
//                entity = response.getEntity();
//                return JsonStrReader.read(entity);
//            }
//        } catch (Exception e) {
//            logger.error("*** Error get data from drc.center due to [{}]", e.getMessage());
//        } finally {
//            EntityUtils.consume(entity);
//        }
//        return null;
//    }
//
//    public OutMessage setHotelMessage(OutMessage oms,InMessage msg) {
//        Hotel hotel = hotelService.findByHotelName(msg.getContent());
//        String hotelGlobalIP = "211.144.87.45";
//        if (hotel != null) {
//            WSHotelResponse response = null;
//            try {
//                String jsonString = getHotelAdminInfo(hotel.getPassport());
//                JSONObject jsonObject = new JSONObject(jsonString);
//                String hotelName = jsonObject.getString("hotelName");
//                hotelGlobalIP = jsonObject.getString("hotelGlobalIP");
//                response = getHotelInfo(hotel.getPassport(), hotelGlobalIP);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            if (response != null && response.getRoomTypeDetails() != null
//                    && response.getRoomTypeDetails().size() > 0) {
//                List<Articles> articleList = new ArrayList<Articles>();
//                oms.setArticleCount(response.getRoomTypeDetails().size() + 1);
//                if (oms.getArticleCount() > 10) {
//                    oms.setArticleCount(10);
//                }
//                Articles hotelArticle = new Articles();
//                hotelArticle.setTitle(response.getName());
//                hotelArticle.setDescription(response.getIntroduction());
//                hotelArticle.setPicUrl("http://" + hotelGlobalIP + "/dhfs/" + ((WSPicture) response.getPictures().get(0)).getUri());
//                hotelArticle.setUrl(response.getWebsite());
//                setBookingUrl(hotelArticle, response.getHotelDerbyPassport());
//
//                articleList.add(hotelArticle);
//                for (WSRoomTypeDetail details : response.getRoomTypeDetails()) {
//                    Articles roomArticle = new Articles();
//                    roomArticle.setTitle(details.getDescription().getName());
//                    roomArticle.setDescription(details.getDescription().getDescription());
//                    if (details.getPictureURI() != null) {
//                        roomArticle.setPicUrl("http://" + hotelGlobalIP + "/dhfs/" + details.getPictureURI());
//                    } else {
//                        roomArticle.setPicUrl("http://5.pic.lvren.cn/main/hotel/2022/20223/20223395.jpg");
//                    }
//                    roomArticle.setUrl(response.getWebsite());
//                    setBookingUrl(roomArticle, response.getHotelDerbyPassport());
//                    articleList.add(roomArticle);
//                }
//                oms.setArticles(articleList);
//            }
//        }
//        oms.setFuncFlag(1);
//        return oms;
//    }

    public OutMessage setRoomMessage(OutMessage oms,InMessage msg) {
//        Hotel hotel = hotelService.findByHotelName("浙江世博大酒店");
        String hotelGlobalIP = Constants.globleIP;
            WSHotelResponse response = null;
            try {
//                String jsonString = getHotelAdminInfo(hotel.getPassport());
//                JSONObject jsonObject = new JSONObject(jsonString);
//                String hotelName = jsonObject.getString("hotelName");
//                hotelGlobalIP = jsonObject.getString("hotelGlobalIP");
                response = getHotelInfo(Constants.hotelPassport, hotelGlobalIP);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (response != null && response.getRoomTypeDetails() != null
                    && response.getRoomTypeDetails().size() > 0) {
                List<Articles> articleList = new ArrayList<Articles>();
                oms.setArticleCount(response.getRoomTypeDetails().size() + 1);
                if (oms.getArticleCount() > 10) {
                    oms.setArticleCount(10);
                }
                Articles hotelArticle = getHotelArticle();
                hotelArticle.setTitle(Constants.hotelName);
                hotelArticle.setDescription(Constants.hotelName + "欢迎您,查看酒店详细介绍请点击");
                hotelArticle.setPicUrl(Constants.mserverUrl + "images/1.jpg");
                hotelArticle.setUrl(Constants.mserverUrl+"roomtypes/room_list.dhtml");
                articleList.add(hotelArticle);

                for (WSRoomTypeDetail details : response.getRoomTypeDetails()) {
                    Articles roomArticle = new Articles();
                    roomArticle.setTitle(details.getDescription().getName());
                    roomArticle.setDescription(details.getDescription().getDescription());
                    if (details.getPictureURI() != null) {
                        roomArticle.setPicUrl("http://" + hotelGlobalIP + "/dhfs/" + details.getPictureURI());
                    } else {
                        roomArticle.setPicUrl("http://5.pic.lvren.cn/main/hotel/2022/20223/20223395.jpg");
                    }
                    roomArticle.setUrl(Constants.mserverUrl+"roomtypes/room_detail.dhtml?code="+details.getCode());
//                    setBookingUrl(roomArticle, response.getHotelDerbyPassport());
                    articleList.add(roomArticle);
                }
                oms.setArticles(articleList);
            }
        oms.setFuncFlag(1);
        return oms;
    }

    public Articles getHotelArticle(){
        Articles hotelArticle = new Articles();
        hotelArticle.setTitle(Constants.hotelName);
        hotelArticle.setDescription(Constants.hotelName + "欢迎您,查看酒店详细介绍请点击");
        hotelArticle.setPicUrl(Constants.mserverUrl+"images/overview.jpg");
        hotelArticle.setUrl(Constants.mserverUrl+"hotelinfo/hotel_info.dhtml");
        return hotelArticle;
    }

    private void setBookingUrl(Articles article, String hotelDerbyPassport) {
        String checkin = DateUtils.formatDate(new Date());
        String checkout = DateUtils.formatDate(DateUtils.addDays(new Date(), +1));
        if (Lidu.equalsIgnoreCase(hotelDerbyPassport)) {
            String liduUrl = "http://m.hotellidobeijing.com/mhlidu/booking/search_results.dhtml";
            article.setUrl(liduUrl + "?d1=" + checkin + "&d2=" + checkout + "&room_nums=1");
        } else if (Balidao.equalsIgnoreCase(hotelDerbyPassport)) {
            String baliUrl = "http://m.baliplazahotel.com/mhbali/search_results.dhtml";
            article.setUrl(baliUrl + "?d1=" + checkin + "&d2=" + checkout + "&room_nums=1");
        } else if (Jindu.equalsIgnoreCase(hotelDerbyPassport)) {
            String jinduUrl = "http://211.144.87.228/mhjindu/search_results.dhtml";
            article.setUrl(jinduUrl + "?d1=" + checkin + "&d2=" + checkout + "&room_nums=1");
        } else if (Guomao.equalsIgnoreCase(hotelDerbyPassport)) {
            String guomaoUrl = "http://211.144.87.228/mhguomao/search_results.dhtml";
            article.setUrl(guomaoUrl + "?d1=" + checkin + "&d2=" + checkout + "&room_nums=1");
        }
    }
}
