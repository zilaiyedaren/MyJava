package org.wuheng.framework.lucene5.analyzer.synonym;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;

import java.io.IOException;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-27
 * Time: 下午5:25
 * To change this template use File | Settings | File Templates.
 */
public class SynonymFilter extends TokenFilter{
    public static final String TOKEN_TYPE_SYNONYM="SYNONYM";

    private Stack<String> synonymStack;
    private SynonymEngine engine;
    private State current;

    private final CharTermAttribute charTermAttribute;
    private final PositionIncrementAttribute positionIncrementAttribute;

    public SynonymFilter(TokenStream input, SynonymEngine engine) {
        super(input);
        this.engine = engine;
        this.synonymStack=new Stack<String>();
        this.charTermAttribute=addAttribute(CharTermAttribute.class);
        this.positionIncrementAttribute=addAttribute(PositionIncrementAttribute.class);
    }

    @Override
    public boolean incrementToken() throws IOException {
        if(synonymStack.size()>0){
            String syn=synonymStack.pop();
            restoreState(current);
            // 这里Lucene4.x的写法
            // termAtt.setTermBuffer(syn);

            // 这是Lucene5.x的写法
            charTermAttribute.copyBuffer(syn.toCharArray(),0,syn.length());
            positionIncrementAttribute.setPositionIncrement(0);
            return true;
        }
        if(!input.incrementToken()){
            return false;
        }
        if(addAliasesToStack()){
            current=captureState();
        }
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }
    private boolean addAliasesToStack() throws IOException{
        // 这里Lucene4.x的写法
        // String[] synonyms = engine.getSynonyms(termAtt.term()); //#8

        // 这里Lucene5.x的写法
        String[] synonyms=engine.getSynonyms(charTermAttribute.toString());
        if(synonyms == null){
            return false;
        }
        for(String synonym:synonyms){
            synonymStack.push(synonym);
        }
        return true;
    }
}
