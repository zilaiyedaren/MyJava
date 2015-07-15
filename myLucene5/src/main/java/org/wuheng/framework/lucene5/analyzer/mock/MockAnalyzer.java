package org.wuheng.framework.lucene5.analyzer.mock;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.util.automaton.CharacterRunAutomaton;
import org.wuheng.framework.lucene5.util.AnalyzerUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-27
 * Time: 下午9:02
 * To change this template use File | Settings | File Templates.
 */
public class MockAnalyzer extends Analyzer {
     public static void main(String[] args) throws Exception {
         Map<String,String> synonyms=new HashMap<String, String>();
         synonyms.put("人民", "百姓");
         synonyms.put("世界", "world");
         synonyms.put("赶赴", "奔赴");
         synonyms.put("速度", "velocity");

         String text = "速度赶赴事故种花人民饶天亮可口可乐看男子怕女友被人抢走 将其从100斤喂到180斤后求婚。。醉了把世界名画变成2.5D动画，太美了。。We have a development schema/db and production schema/db. When developers are working on stuff they just make a copy of the \"build machines\" development database and restore it locally. This database is much smaller than the production db and is ideal for testing. Your production db should no be that much different than your development db schema wise (make smaller changes and release more often if it is the case.)";

         AnalyzerUtils.displayTokens(new WhitespaceAnalyzer(),text);
         AnalyzerUtils.displayTokens(new MockAnalyzer(),text);
     }
    private final CharacterRunAutomaton runAutomaton;
    private final boolean lowerCase;
    private final CharacterRunAutomaton filter;
    private int positionIncrementGap;
    private Integer offsetGap;
    private boolean enableChecks=true;
    private int maxTokenLength=MockTokenizer.DEFAULT_MAX_TOKEN_LENGTH;

    public MockAnalyzer(CharacterRunAutomaton runAutomaton, boolean lowerCase, CharacterRunAutomaton filter) {
        super(PER_FIELD_REUSE_STRATEGY);
        this.runAutomaton = runAutomaton;
        this.lowerCase = lowerCase;
        this.filter = filter;
    }

    public MockAnalyzer(CharacterRunAutomaton runAutomaton, boolean lowerCase) {
        this(runAutomaton,lowerCase,MockTokenFilter.EMPTY_STOPSET);
    }

    public MockAnalyzer() {
        this(MockTokenizer.WHITESPACE,true);
    }

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        MockTokenizer tokenizer=new MockTokenizer(runAutomaton,lowerCase,maxTokenLength);
        tokenizer.setEnableChecks(enableChecks);
        MockTokenFilter mockTokenFilter=new MockTokenFilter(tokenizer,filter);

        return new TokenStreamComponents(tokenizer,mockTokenFilter);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setPositionIncrementGap(int positionIncrementGap) {
        this.positionIncrementGap = positionIncrementGap;
    }

    @Override
    public int getPositionIncrementGap(String fieldName) {
        return positionIncrementGap;    //To change body of overridden methods use File | Settings | File Templates.
    }

    public void setOffsetGap(Integer offsetGap) {
        this.offsetGap = offsetGap;
    }

    @Override
    public int getOffsetGap(String fieldName) {
        return offsetGap==null ?super.getOffsetGap(fieldName):offsetGap;    //To change body of overridden methods use File | Settings | File Templates.
    }

    public void setEnableChecks(boolean enableChecks) {
        this.enableChecks = enableChecks;
    }

    public void setMaxTokenLength(int maxTokenLength) {
        this.maxTokenLength = maxTokenLength;
    }
}
