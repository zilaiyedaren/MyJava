package org.wuheng.framework.lucene5.analyzer.positiongap;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-27
 * Time: 下午5:13
 * To change this template use File | Settings | File Templates.
 */
public class PositionIncrementTokenStream extends TokenStream{
    private boolean first=true;
    private PositionIncrementAttribute attribute;
    //位置增量
    private final int positionIncrement;

    public PositionIncrementTokenStream(final int positionIncrement) {
        super();
        this.attribute=addAttribute(PositionIncrementAttribute.class);
        this.positionIncrement = positionIncrement;
    }

    @Override
    public boolean incrementToken() throws IOException {
        if(first){
            first=false;
            attribute.setPositionIncrement(positionIncrement);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void reset() throws IOException {
        super.reset();    //To change body of overridden methods use File | Settings | File Templates.
        first=true;
    }
}
