package org.wuheng.framework.lucene5.analyzer.synonym;

import org.apache.lucene.analysis.Analyzer;
import org.wuheng.framework.lucene5.util.AnalyzerUtils;


/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-27
 * Time: 下午5:40
 * To change this template use File | Settings | File Templates.
 */
public class SynonymAnalyzerTest {
    public static void main(String[] args) throws Exception {
        String text="The quick brown fox jumps over the lazy dog" ;
        Analyzer analyzer=new SynonymAnalyzer(new BaseSynonymEngine());
        AnalyzerUtils.displayTokens(analyzer,text);
    }
}
