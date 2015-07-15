package org.wuheng.framework.lucene5.analyzer.synonym;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-27
 * Time: 下午5:14
 * To change this template use File | Settings | File Templates.
 */

/**
 * 自定义同义词分词器
 */
public class SynonymAnalyzer extends Analyzer {
    private SynonymEngine engine;
    public SynonymAnalyzer(SynonymEngine engine){
        this.engine=engine;
    }
    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        Tokenizer tokenizer=new StandardTokenizer();
        TokenStream tokenStream=new SynonymFilter(tokenizer,engine);
        tokenStream=new LowerCaseFilter(tokenStream);
        tokenStream=new StopFilter(tokenStream, StopAnalyzer.ENGLISH_STOP_WORDS_SET);

        return new TokenStreamComponents(tokenizer,tokenStream);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
