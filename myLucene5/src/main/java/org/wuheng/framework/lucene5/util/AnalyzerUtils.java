package org.wuheng.framework.lucene5.util;

import com.ibm.icu.impl.Assert;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-26
 * Time: 下午4:09
 * To change this template use File | Settings | File Templates.
 */
/**
 *  用于分词器测试的一个简单工具类(用于打印分词情况，包括Term的起始位置和结束位置(即所谓的偏移量)，
 * 位置增量，Term字符串，Term字符串类型(字符串/阿拉伯数字之类的))
 */
public class AnalyzerUtils {
    protected static Logger logger=Logger.getLogger(AnalyzerUtils.class);

    public static void displayTokens(Analyzer analyzer,String text) throws Exception{
        TokenStream tokenStream=analyzer.tokenStream("text",text);
        displayTokens(tokenStream);
    }
    public static void displayTokens(TokenStream tokenStream) throws IOException{
        OffsetAttribute offsetAttribute=tokenStream.addAttribute(OffsetAttribute.class);
        PositionIncrementAttribute positionIncrementAttribute=tokenStream.addAttribute(PositionIncrementAttribute.class);
        CharTermAttribute charTermAttribute=tokenStream.addAttribute(CharTermAttribute.class);
        TypeAttribute typeAttribute=tokenStream.addAttribute(TypeAttribute.class);

        tokenStream.reset();
        int position=0;
        while (tokenStream.incrementToken()){
            int increment=positionIncrementAttribute.getPositionIncrement();
            if(increment>0){
                position+=increment;
                logger.info("position:"+position);
            }
            int startOffset=offsetAttribute.startOffset();
            int endOffset=offsetAttribute.endOffset();
            String term=charTermAttribute.toString();
            logger.info("["+term+"]"+":("+startOffset+"---->"+endOffset+");"+typeAttribute.type());
        }
    }
//    public static void assertAnalyzerTo(Analyzer analyzer,String text,String[] expecteds) throws IOException{
//        TokenStream tokenStream=analyzer.tokenStream("text",text);
//        CharTermAttribute charTermAttribute=tokenStream.addAttribute(CharTermAttribute.class);
//        for(String expected:expecteds){
//            Assert.assertTrue
//        }
//    }
}
