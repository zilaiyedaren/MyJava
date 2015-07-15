package org.wuheng.framework.lucene5.pinyin;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.TokenFilterFactory;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-22
 * Time: 下午9:35
 * To change this template use File | Settings | File Templates.
 */
public class PinyinTokenFilterFactory extends TokenFilterFactory {
    private boolean firstChar;
    private boolean outChinese;
    private int minTermLength;

    public PinyinTokenFilterFactory(Map<String,String> args){
        super(args);
        this.firstChar=getBoolean(args,"firstChar",Constant.DEFAULT_FIRST_CHAR);
        this.outChinese=getBoolean(args,"outChinese",Constant.DEFAULT_OUT_CHINESE);
        this.minTermLength=getInt(args, "minTermLength", Constant.DEFAULT_MIN_TERM_LENGTH);

    }
    @Override
    public TokenStream create(TokenStream input) {
        return new PinyinTokenFilter(input,this.firstChar,this.minTermLength,this.outChinese);
                        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isFirstChar() {
        return firstChar;
    }

    public void setFirstChar(boolean firstChar) {
        this.firstChar = firstChar;
    }

    public boolean isOutChinese() {
        return outChinese;
    }

    public void setOutChinese(boolean outChinese) {
        this.outChinese = outChinese;
    }

    public int getMinTermLength() {
        return minTermLength;
    }

    public void setMinTermLength(int minTermLength) {
        this.minTermLength = minTermLength;
    }
}
