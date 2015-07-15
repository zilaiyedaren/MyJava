package org.wuheng.framework.lucene5.pinyin;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-22
 * Time: 下午9:43
 * To change this template use File | Settings | File Templates.
 */

/**
 * 拼音过滤器[负责将汉字转换为拼音]
 */
public class PinyinTokenFilter extends TokenFilter {
    private final CharTermAttribute termAttribute;
    //汉语拼音输出转换器[基于Pinyin4j]
    private HanyuPinyinOutputFormat outputFormat;
     //对于多音字会有多个拼音,firstChar即表示只取第一个,否则会取多个拼音
    private boolean firstChar;
    // Term最小长度[小于这个最小长度的不进行拼音转换]
    private int minTermLength;
    private char[] curTermBuffer;
    private int curTermLength;
    private boolean outChinese;

    public PinyinTokenFilter(TokenStream input) {
        this(input,Constant.DEFAULT_FIRST_CHAR,Constant.DEFAULT_MIN_TERM_LENGTH);
    }

    public PinyinTokenFilter(TokenStream input, boolean firstChar) {
        this(input, firstChar, Constant.DEFAULT_MIN_TERM_LENGTH);
    }

    public PinyinTokenFilter(TokenStream input, boolean firstChar, int minTermLength) {
        this(input, firstChar, minTermLength, Constant.DEFAULT_NGRAM_CHINESE);
    }

    public PinyinTokenFilter(TokenStream input, boolean firstChar, int minTermLength, boolean outChinese) {
        super(input);
        this.termAttribute=addAttribute(CharTermAttribute.class);
        this.outputFormat=new HanyuPinyinOutputFormat();
        this.firstChar=false;
        this.minTermLength=Constant.DEFAULT_MIN_TERM_LENGTH;
        this.outChinese=Constant.DEFAULT_OUT_CHINESE;

        this.firstChar = firstChar;
        this.minTermLength = minTermLength;
        this.outChinese = outChinese;
        if(this.minTermLength<1){
            this.minTermLength=1;
        }
        this.outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        this.outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    }

    public static boolean containsChinese(String str){
        if((str==null) || ("".contains(str.trim()))){
            return false;
        }
        for(int i=0;i<str.length();i++){
            if(isChinese(str.charAt(i))){
                return true;
            }
        }
        return false;
    }
    public static boolean isChinese(char a){
        int v=a;
        return (v>=19968) && (v<=171941);
    }

    @Override
    public final boolean incrementToken() throws IOException {
        while (true){
            if(curTermBuffer==null){
                if(input.incrementToken()){
                    return false;
                }
                curTermBuffer=termAttribute.buffer().clone();
                curTermLength=termAttribute.length();
            }

            if(outChinese){
                outChinese=false;
                termAttribute.copyBuffer(curTermBuffer,0,curTermLength);
                return true;
            }
            outChinese=true;
            String chinese=termAttribute.toString();

            if(containsChinese(chinese)){
                outChinese=true;
                if(chinese.length()>=minTermLength){
                    try {
                        String chineseTerm=getPinyinString(chinese);
                        termAttribute.copyBuffer(chineseTerm.toCharArray(),0,chineseTerm.length());
                    } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                        badHanyuPinyinOutputFormatCombination.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    curTermBuffer=null;
                    return true;
                }
            }
            curTermBuffer=null;
        }
    }

    @Override
    public void reset() throws IOException {
        super.reset();    //To change body of overridden methods use File | Settings | File Templates.
    }

    private String getPinyinString(String chinese) throws BadHanyuPinyinOutputFormatCombination{
        String chineseTerm =null;
        if(firstChar){
            StringBuilder stringBuilder=new StringBuilder();
            for(int i=0;i<chinese.length();i++){
                String[] array= PinyinHelper.toHanyuPinyinStringArray(chinese.charAt(i),outputFormat);
                if((array!=null) && (array.length!=0)){
                    String s=array[0];
                    char c=s.charAt(0);
                    stringBuilder.append(c);
                }
            }
            chineseTerm=stringBuilder.toString();
        } else {
            chineseTerm=PinyinHelper.toHanyuPinyinString(chinese,outputFormat,"");
        }
        return chineseTerm;

    }
}
