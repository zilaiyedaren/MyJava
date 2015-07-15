package org.wuheng.framework.lucene5.analyzer.ngram;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.ngram.EdgeNGramTokenizer;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.wuheng.framework.lucene5.util.AnalyzerUtils;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-27
 * Time: 下午9:40
 * To change this template use File | Settings | File Templates.
 */
public class EdgeNGramAnalyzer extends Analyzer {
    private int minGram;
    private int maxGram;

    public EdgeNGramAnalyzer(int minGram, int maxGram) {
        this.minGram = minGram;
        this.maxGram = maxGram;
    }

    public EdgeNGramAnalyzer(int maxGram) {
        this.minGram=2;
        this.maxGram = maxGram;
    }

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        Tokenizer tokenizer=new EdgeNGramTokenizer(minGram,maxGram);
        TokenStream tokenStream=new StandardFilter(tokenizer);
        tokenStream=new LowerCaseFilter(tokenStream);
        tokenStream=new StopFilter(tokenStream, StopAnalyzer.ENGLISH_STOP_WORDS_SET);
        return new TokenStreamComponents(tokenizer,tokenStream);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getMinGram() {
        return minGram;
    }

    public void setMinGram(int minGram) {
        this.minGram = minGram;
    }

    public int getMaxGram() {
        return maxGram;
    }

    public void setMaxGram(int maxGram) {
        this.maxGram = maxGram;
    }

    public static void main(String[] args) throws Exception{
        String text="自来也大人，你好";
        Analyzer analyzer=new EdgeNGramAnalyzer(text.length());
        AnalyzerUtils.displayTokens(analyzer,text);
    }
}
