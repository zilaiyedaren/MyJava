package org.wuheng.framework.lucene5.crawler;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;

import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Element;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-7-15
 * Time: 下午6:28
 * To change this template use File | Settings | File Templates.
 */

/**
 * 指定url抓取图片
 */
public class CrawlerImg {
    protected Logger logger=Logger.getLogger(CrawlerImg.class);

    private File indexFile;
    private String baseUrl;
    private String inputUrl;
    private String imagePath;
    private int imageWidth;
    private int imageHeight;
    private Analyzer analyzer;

    public CrawlerImg() {
        logger.info("Init a object"+CrawlerImg.class.getName());
    }
    /**
     * 返回当前类的一个实例
     * @return
     */
    public static CrawlerImg getInstance(){
        CrawlerImg crawlerImg=new CrawlerImg();
        loadConfig(crawlerImg);
        return crawlerImg;
    }

    /**
     * 配置对象
     * @param crawlerImg
     * @return
     */
    private static CrawlerImg loadConfig(CrawlerImg crawlerImg){
        crawlerImg.imagePath="E:/images";
        new File(crawlerImg.imagePath).mkdir();
        crawlerImg.imageWidth=300;
        crawlerImg.imageHeight=300;
        crawlerImg.indexFile=new File("lucene");
        crawlerImg.baseUrl=crawlerImg.getBaseUrl(crawlerImg.inputUrl);
        crawlerImg.analyzer=new StandardAnalyzer();
        return crawlerImg;
    }

    private  String getBaseUrl(String url){
        if(url != null){
            if(!url.startsWith("http") && !url.startsWith("HTTP")){
                url="http://".concat(url);
            }
            String maoHao="://";
            int indexOfMaohao=url.indexOf(maoHao);
            int indexOfGang=url.indexOf("/",indexOfMaohao+maoHao.length());
            if(indexOfGang>0){
                url=url.substring(0,indexOfGang);
            }
        }
        return url;

    }

    /**
     *处理不合法的URL
     * @param url
     * @param from
     * @return
     */
    public String processUrl(String url,String from){
        if(url!=null){
            if(url.startsWith("#") || url.startsWith("javascript")){
                return null;
            }else if(url.startsWith("..")){
                url=url.substring(2);
                if(from.lastIndexOf("/")==from.length()){
                    from=from.substring(0,from.length()-1);
                }
                int indexOf=-1;
                if((indexOf=from.lastIndexOf("/"))>0){
                    from=from.substring(0,indexOf);
                }
                url=from+url;
            }else if(!url.startsWith("http") && !url.startsWith("HTTP")){
                return  baseUrl.concat(url);
            }
        }
        return url;
    }

    public void connect(String url){
        this.inputUrl=url;
        this.baseUrl=getBaseUrl(url);
        try {
            indexUrl(url);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        processConnect(inputUrl);
    }

    /**
     * 处理链接
     * @param url
     */
    private void processConnect(String url){
        logger.info("进入"+url);
        Connection connection= HttpConnection.connect(url);
        connection.ignoreContentType(true);
        connection.ignoreHttpErrors(true);
        connection.timeout(10000);
        org.jsoup.nodes.Document document=null;
        Connection.Response response=null;

        try {
            response=connection.execute();
        } catch (Exception e) {
            logger.error("文档获取有误");
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        if(response!=null){
             logger.info(response.contentType());
            if(response.contentType()!=null && response.contentType().indexOf("text/html")>=0){
                try {
                    document=response.parse();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            } else if(response.contentType()!=null && response.contentType().indexOf("image")>=0){
                  writeImage(url,url,url);
            } else{
                logger.info("无法处理的文档类型");
            }
        }
        if(document!=null ){
            processDoc(document,url);
        }
    }

    /**
     * 处理文档
     * @param document
     * @param from
     */
    private void processDoc(org.jsoup.nodes.Document document,String from){
        logger.info("处理文档");
        org.jsoup.select.Elements docImages=document.getElementsByTag("img");
        if(docImages!=null){
            Iterator<Element> iterator=docImages.iterator();
            while (iterator.hasNext()){
                Element element=iterator.next();
                logger.info(element.attr("alt"));
                String imageName=element.attr("alt");
                if(imageName==null || imageName.equals("")){
                    imageName=""+System.currentTimeMillis();
                }
                writeImage(element.absUrl("src"),imageName,from);
            }
        }
        org.jsoup.select.Elements elements=document.getElementsByTag("a");
        if(elements!=null){
             Iterator<Element> iterator=elements.iterator();
            while (iterator.hasNext()){
                Element element=iterator.next();
                try {
                    fenxiUrl(element.attr("href"),from);
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void fenxiUrl(String url,String from) throws ParseException,IOException{
        url=processUrl(url,from);
        logger.info("分析"+url);
        if(url==null){
            return;
        }
        if(hasOne(url) && baseUrl.equals(getBaseUrl(url))){
            indexUrl(url);
            processConnect(url);
        }

    }
    public boolean hasOne(String url) throws ParseException,IOException{
        logger.debug("查询是否有"+url);
        IndexSearcher indexSearcher=null;
        File file=indexFile;
        IndexReader indexReader=null;
        if(file==null||!file.exists()){
            return true;
        }
        try {
            indexReader= DirectoryReader.open(FSDirectory.open(file.toPath()));
            indexSearcher=new IndexSearcher(indexReader);
            logger.debug((indexReader+"::"+indexSearcher.getIndexReader()));
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(indexSearcher!=null){
            QueryParser parser=new QueryParser("url", analyzer);
            Query query= null;
            try {
                query = parser.parse(CrawlerImg.md5(url));
            } catch (org.apache.lucene.queryparser.classic.ParseException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            TopDocs docs=indexSearcher.search(query, 1);
            indexReader.close();
            if(docs.totalHits>0){
                logger.debug("有"+url);
                return false;
            }
        }
        logger.debug("没有"+url);
        return true;
    }
    private void writeImage(String imageUrl,String imageName,String from){
        imageUrl=processUrl(imageUrl,from);
        URL url=null;
        if(imageUrl!=null){
            try {
                url=new URL(imageUrl);
            } catch (MalformedURLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        if(url!=null){
            InputStream inputStream=null;
            try {
                inputStream=url.openStream();
                BufferedImage bufferedImage= ImageIO.read(inputStream);
                if(bufferedImage!=null && bufferedImage.getHeight()>imageHeight && bufferedImage.getWidth()>imageWidth){
//                if(bufferedImage!=null){
                    logger.info("写图片"+imageUrl);
                    imageName=imageName.replaceAll("/|\\\\","-");
                    ImageIO.write(bufferedImage,"jpg",new FileOutputStream(imagePath+"/"+imageName+".jpg"));
                }else{
                    logger.info("图片尺寸太小");
                }
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
    public void indexUrl(String url) throws CorruptIndexException,LockObtainFailedException,IOException {
        logger.info("索引"+url);
        Directory directory= FSDirectory.open(indexFile.toPath());
        IndexWriterConfig config=new IndexWriterConfig(analyzer);
        IndexWriter indexWriter=new IndexWriter(directory,config);
        Document document=new Document();
//        Field field=new Field("url",CrawlerImg.md5(url), Field.Store.NO, Field.Index.ANALYZED); //该构造函数在Lucene5.0中过期
        Field field=new Field("url",CrawlerImg.md5(url), TextField.TYPE_STORED);
        document.add(field);
        indexWriter.addDocument(document);
        indexWriter.commit();
        indexWriter.forceMerge(1);
        indexWriter.close();
    }

    private static String md5(String plainText){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return  null;
    }
    public static void main(String[] args) {
        String url="http://www.17786.com/3623_1.html";
        CrawlerImg crawlerImg=CrawlerImg.getInstance();
        crawlerImg.connect(url);
    }

    public File getIndexFile() {
        return indexFile;
    }
    public void setIndexFile(File indexFile) {
        this.indexFile = indexFile;
    }

}
