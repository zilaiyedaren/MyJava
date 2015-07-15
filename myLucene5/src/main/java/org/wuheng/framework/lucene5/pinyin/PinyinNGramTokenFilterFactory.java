package org.wuheng.framework.lucene5.pinyin;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.TokenFilterFactory;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-26
 * Time: 下午2:36
 * To change this template use File | Settings | File Templates.
 */

/**
 *  PinyinNGramTokenFilterFactory工厂类
 */
public class PinyinNGramTokenFilterFactory extends TokenFilterFactory {
    public static final boolean DEFAULT_NGRAM_CHINESE=false;
    private int minGram;
    private int maxGram;
    private boolean nGramChinese;

    public PinyinNGramTokenFilterFactory(Map<String, String> args) {
        super(args);
        this.minGram=getInt(args,"minGram",Constant.DEFAULT_MIN_GRAM);
        this.maxGram=getInt(args,"maxGram",Constant.DEFAULT_MAX_GRAM);
        this.nGramChinese=getBoolean(args, "nGramChinese", DEFAULT_NGRAM_CHINESE);
    }

    @Override
    public TokenStream create(TokenStream input) {
        return new PinyinNGramTokenFilter(input,minGram,maxGram,nGramChinese);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
