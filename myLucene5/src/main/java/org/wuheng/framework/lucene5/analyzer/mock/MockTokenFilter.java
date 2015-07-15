package org.wuheng.framework.lucene5.analyzer.mock;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.util.automaton.Automata;
import org.apache.lucene.util.automaton.CharacterRunAutomaton;
import org.apache.lucene.util.automaton.Operations;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-27
 * Time: 下午8:54
 * To change this template use File | Settings | File Templates.
 */
public class MockTokenFilter extends TokenFilter {
    //Empty set of stopwords
    public static final CharacterRunAutomaton EMPTY_STOPSET=
            new CharacterRunAutomaton(Automata.makeEmpty());

    //Set of common english stopwords
    public static final CharacterRunAutomaton ENGLISH_STOPSET=
            new CharacterRunAutomaton(Operations.union(Arrays.asList(
                    Automata.makeString("a"),Automata.makeString("an"), Automata.makeString("and"), Automata.makeString("are"),
                    Automata.makeString("as"), Automata.makeString("at"), Automata.makeString("be"), Automata.makeString("but"),
                    Automata.makeString("by"), Automata.makeString("for"), Automata.makeString("if"), Automata.makeString("in"),
                    Automata.makeString("into"), Automata.makeString("is"), Automata.makeString("it"), Automata.makeString("no"),
                    Automata.makeString("not"), Automata.makeString("of"), Automata.makeString("on"), Automata.makeString("or"),
                    Automata.makeString("such"), Automata.makeString("that"), Automata.makeString("the"), Automata.makeString("their"),
                    Automata.makeString("then"), Automata.makeString("there"), Automata.makeString("these"), Automata.makeString("they"),
                    Automata.makeString("this"), Automata.makeString("to"), Automata.makeString("was"), Automata.makeString("will"),
                    Automata.makeString("with"))));
    private final CharacterRunAutomaton filter;
    private final CharTermAttribute charTermAttribute=addAttribute(CharTermAttribute.class);
    private final PositionIncrementAttribute positionIncrementAttribute=addAttribute(PositionIncrementAttribute.class);
    private int skippedPositions;

    public MockTokenFilter(TokenStream input, CharacterRunAutomaton filter) {
        super(input);
        this.filter = filter;
    }

    @Override
    public boolean incrementToken() throws IOException {
        skippedPositions=0;
        while (input.incrementToken()){
            if(!filter.run(charTermAttribute.buffer(),0,charTermAttribute.length())){
                positionIncrementAttribute.setPositionIncrement(positionIncrementAttribute.getPositionIncrement()+skippedPositions);;
                return true;
            }
            skippedPositions+=positionIncrementAttribute.getPositionIncrement();
        }
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void end() throws IOException {
        super.end();    //To change body of overridden methods use File | Settings | File Templates.
        positionIncrementAttribute.setPositionIncrement(positionIncrementAttribute.getPositionIncrement()+skippedPositions);;
    }

    @Override
    public void reset() throws IOException {
        super.reset();    //To change body of overridden methods use File | Settings | File Templates.
        skippedPositions=0;
    }
}
