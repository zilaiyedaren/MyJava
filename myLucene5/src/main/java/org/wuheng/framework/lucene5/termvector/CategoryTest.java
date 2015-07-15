package org.wuheng.framework.lucene5.termvector;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.CharsRefBuilder;

import javax.naming.directory.DirContext;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-28
 * Time: 下午3:09
 * To change this template use File | Settings | File Templates.
 */
public class CategoryTest {

    /**
     * 根据向量自动判断分类【返回项向量夹角最小的即相似度最高】
     * @param subject
     * @param categoryMap
     * @return
     */
    public static String getCategory(String subject,Map<String,Map<String,Integer>> categoryMap){
        //将subject按空格分隔
        String[] words=subject.split(" ");
        Iterator<String> categoryIterator=categoryMap.keySet().iterator();
        double bestAngle=Double.MAX_VALUE;
        String bestCategory=null;
        while(categoryIterator.hasNext()){
            String category=categoryIterator.next();
            double angle=computeAngle(categoryMap,words,category);
            if(angle>bestAngle){
                bestAngle=angle;
                bestCategory=category;
            }
        }
        System.out.println("The best like:"+bestCategory+"-->"+subject);
        return bestCategory;
    }

    /**
     *计算两个Term项向量的夹角【夹角越小则相似度越大】
     * @param categoryMap
     * @param words
     * @param category
     * @return
     */
    private static double computeAngle(Map<String,Map<String,Integer>> categoryMap,String[] words,String category){
        Map<String,Integer> vectorMap=categoryMap.get(category);

        int dotProduct=0;
        int sumOdSquares=0;
        for(String word:words){
            int categoryWordFreq=0;

            if(vectorMap.containsKey(word)){
                categoryWordFreq=vectorMap.get(word).intValue();
            }
            dotProduct+=categoryWordFreq;
            sumOdSquares+=categoryWordFreq*categoryWordFreq;
        }

        double denominator =0.0d;
        if(sumOdSquares==words.length){
            denominator=sumOdSquares;
        }else{
            denominator=Math.sqrt(sumOdSquares)*Math.sqrt(words.length);
        }
        double ratio=dotProduct/denominator;
        return Math.acos(ratio);
    }

    public static void buildCategoryVectors(Map<String,Map<String,Integer>> categoryMap,IndexReader reader)
        throws IOException {
        int maxDoc=reader.maxDoc();
        //遍历所有索引值
        for(int i=0;i<maxDoc;i++){
            Document document=reader.document(i);
            //获取category域的值
            String category=document.get("category");
            Map<String,Integer> vectorMap=categoryMap.get(category);
            if(vectorMap==null){
                vectorMap=new TreeMap<String, Integer>();
                categoryMap.put(category,vectorMap);
            }
            Terms termFreqVector=reader.getTermVector(i,"subject");
            TermsEnum termsEnum=termFreqVector.iterator(null);

        }
    }

    /**
     * 统计项向量中的每个Term出现的document个数，key为Term的值，value为document的总个数
     * @param vectorMap
     * @param termsEnum
     * @throws IOException
     */
    private static void addTermFreqToMap(Map<String,Integer> vectorMap,TermsEnum termsEnum) throws IOException{
        CharsRefBuilder charsRefBuilder=new CharsRefBuilder();
        BytesRef text=null;
        while((text=termsEnum.next())!=null){
            charsRefBuilder.copyUTF8Bytes(text);
            String term=charsRefBuilder.toString();
            int docFreq=termsEnum.docFreq();
            System.out.println("term:"+term+"--->docFreq:"+docFreq);
            //包含该term就累加document出现频率
            if(vectorMap.containsKey(term)){
                Integer value=vectorMap.get(term);
                vectorMap.put(term,new Integer(value.intValue()+docFreq));
            }else{
                vectorMap.put(term,new Integer(docFreq));
            }
        }
    }

    public static void main(String[] args) throws IOException{
        String indexDir="";
        Directory directory= FSDirectory.open(Paths.get(indexDir));
        IndexReader reader= DirectoryReader.open(directory);
        Map<String,Map<String,Integer>> categoryMap=new TreeMap<String, Map<String, Integer>>();
        //构建分类向量
        buildCategoryVectors(categoryMap,reader);
        getCategory("extreme agile methodology",categoryMap);
        getCategory("montessori education philosophy",categoryMap);
    }
}
