package org.wuheng.framework.lucene5.pinyin;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-26
 * Time: 下午2:36
 * To change this template use File | Settings | File Templates.
 */

/**
 * 对转换后的拼音进行NGram处理的TokenFilter
 */
public class PinyinNGramTokenFilter extends TokenFilter {
    public static final boolean DEFAULT_NGRAM_CHINESE=false;
    private final int minGram;
    private final int maxGram;
    //是否需要对中文进行NGram,默认为false
    private final boolean nGramChinese;
    private final CharTermAttribute termAttribute;
    private final OffsetAttribute offsetAttribute;
    private char[] curTermBuffer;
    private int curTermLength;
    private int curGramSize;
    private int tokStart;

    public PinyinNGramTokenFilter(TokenStream input) {
        this(input,Constant.DEFAULT_MIN_GRAM,Constant.DEFAULT_MAX_GRAM,DEFAULT_NGRAM_CHINESE);
    }

    public PinyinNGramTokenFilter(TokenStream input, int maxGram) {
       this(input, Constant.DEFAULT_MIN_GRAM,maxGram,DEFAULT_NGRAM_CHINESE);
    }

    public PinyinNGramTokenFilter(TokenStream input, int minGram, int maxGram) {
       this(input, minGram, maxGram,DEFAULT_NGRAM_CHINESE);
    }

    public PinyinNGramTokenFilter(TokenStream input, int minGram, int maxGram, boolean nGramChinese) {
        super(input);
        this.termAttribute=addAttribute(CharTermAttribute.class);
        this.offsetAttribute=addAttribute(OffsetAttribute.class);

        if(minGram<1){
            throw new IllegalArgumentException("minGram must be greater than zero");
        }
        if(minGram>maxGram){
            throw new IllegalArgumentException("minGram must not be greater than maxGram");
        }
        this.minGram = minGram;
        this.maxGram = maxGram;
        this.nGramChinese = nGramChinese;
    }

    /**
     * 字符串是否包含汉字
     * @param str
     * @return
     */
    public static boolean  containsChinese(String str){
        if((str == null)||("".equals(str.trim()))){
            return false;
        }
        for(int i=0;i<str.length();i++){
            if(isChinese(str.charAt(i))){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符是否是汉字
     * @param c
     * @return
     */
    public static boolean isChinese(char c){
        int v=c;
        return (v >= 19968) && (v <= 171941);
    }

    @Override
    public boolean incrementToken() throws IOException {
        while (true){
            if(curTermBuffer == null){
                //父类的input属性
                if(!input.incrementToken()){
                    return false;
                }
                if(!nGramChinese && (containsChinese(termAttribute.toString()))){
                    return true;
                }
                curTermBuffer=termAttribute.buffer().clone();
                curTermLength=termAttribute.length();
                curGramSize=minGram;
                tokStart=offsetAttribute.startOffset();
            }
            if(curGramSize<=maxGram){
                if(curGramSize>=curTermLength){
                    clearAttributes();
                    offsetAttribute.setOffset(tokStart+0,tokStart+curTermLength);
                    termAttribute.copyBuffer(curTermBuffer,0,curTermLength);
                    curTermBuffer=null;
                    return true;
                }
                int start=0;
                int end=start+curGramSize;
                clearAttributes();
                offsetAttribute.setOffset(tokStart+start,tokStart+end);
                curGramSize+=1;
                return true;
            }
            curTermBuffer=null;
        }
    }

    @Override
    public void reset() throws IOException {
        super.reset();
        this.curTermBuffer=null;
          //To change body of overridden methods use File | Settings | File Templates.
    }
}
