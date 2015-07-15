package org.wuheng.framework.lucene5.analyzer.mock;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.util.AttributeFactory;
import org.apache.lucene.util.AttributeSource;
import org.apache.lucene.util.automaton.CharacterRunAutomaton;
import org.apache.lucene.util.automaton.RegExp;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-27
 * Time: 下午6:01
 * To change this template use File | Settings | File Templates.
 */
public class MockTokenizer extends Tokenizer {
    public static final CharacterRunAutomaton WHITESPACE=new CharacterRunAutomaton(new RegExp("[^\t\r\n]+").toAutomaton());

    public static final CharacterRunAutomaton KEYWORD=new CharacterRunAutomaton((new RegExp(".*").toAutomaton()));
    public static final CharacterRunAutomaton SIMPLE=new CharacterRunAutomaton(new RegExp("[A-Za-zªµºÀ-ÖØ-öø-ˁ一-鿌]+").toAutomaton());

    private final CharacterRunAutomaton runAutomaton;
    private final boolean lowerCase;
    private final int maxTokenLength;
    public static final int DEFAULT_MAX_TOKEN_LENGTH=Integer.MAX_VALUE;
    private int state;
    private final CharTermAttribute charTermAttribute=addAttribute(CharTermAttribute.class);
    private final OffsetAttribute offsetAttribute=addAttribute(OffsetAttribute.class);
    private int off=0;
    private int bufferedCodePoint=-1;
    int bufferedOff=-1;

    private static enum State{
        SETREADER, // consumer set a reader input either via ctor or via reset(Reader)
        RESET,
        INCREMENT,   //consumer is consuming, has called incrementToken() == true
        INCREMENT_FALSE,    //consumer is consuming, has called incrementToken() == false
        END,
        CLOSE
    }

    private State streamState=State.CLOSE;
    private int lastOffset=0;
    private boolean enableChecks=true;
    private final Random random=new Random(System.currentTimeMillis());

    public MockTokenizer(AttributeFactory factory, CharacterRunAutomaton runAutomaton, boolean lowerCase, int maxTokenLength) {
        super(factory);
        this.runAutomaton = runAutomaton;
        this.lowerCase = lowerCase;
        this.maxTokenLength = maxTokenLength;
        this.state = runAutomaton.getInitialState();
    }

    public MockTokenizer(CharacterRunAutomaton runAutomaton, boolean lowerCase, int maxTokenLength) {
        this(AttributeFactory.DEFAULT_ATTRIBUTE_FACTORY,runAutomaton,lowerCase,maxTokenLength);
    }

    public MockTokenizer(CharacterRunAutomaton runAutomaton, boolean lowerCase) {
       this(runAutomaton,lowerCase,DEFAULT_MAX_TOKEN_LENGTH);
    }

    public MockTokenizer() {
        this(WHITESPACE,true);
    }

    public MockTokenizer(AttributeFactory factory, CharacterRunAutomaton runAutomaton, boolean lowerCase) {
       this(factory,runAutomaton,lowerCase,DEFAULT_MAX_TOKEN_LENGTH);
    }

    public MockTokenizer(AttributeFactory factory) {
        this(factory,WHITESPACE,true);
    }

    // we allow some checks (e.g. state machine) to be turned off.
    // turning off checks just means we suppress exceptions from them
    private void fail(String message){
        if(enableChecks){
            throw new IllegalStateException(message);
        }
    }

    private void failAlways(String message){
        throw new IllegalStateException(message);
    }
    @Override
    public boolean incrementToken() throws IOException {
        if(streamState !=State.RESET && streamState !=State.INCREMENT){
            fail("incrementToken() called while in wrong state: "+streamState);
        }
        clearAttributes();
        while (true){
            int startOffset;
            int cp;
            if(bufferedCodePoint>=0){
                cp=bufferedCodePoint;
                startOffset=bufferedOff;
                bufferedCodePoint=-1;
            }else{
                startOffset=off;
                cp=readCodePoint();
            }
            if(cp<0){
                break;
            }else if(isTokenChar(cp)){
                int endOffset=-1;
                do{
                    char chars[] =Character.toChars(normalize(cp));
                    for(int i=0;i<chars.length;i++){
                        charTermAttribute.append(chars[i]);
                        endOffset=off;
                        if(charTermAttribute.length()>=maxTokenLength){
                            break;
                        }
                        cp=readCodePoint();
                    }
                }while (cp>=0 && isTokenChar(cp));

                if(charTermAttribute.length()<maxTokenLength){
                    bufferedCodePoint=cp;
                    bufferedOff=endOffset;
                } else {
                    bufferedCodePoint=-1;
                }
                int correctedStartOffset=correctOffset(startOffset);
                int correctedEndOffset=correctOffset(endOffset);
                if (correctedStartOffset < 0) {
                    failAlways("invalid start offset: " + correctedStartOffset + ", before correction: " + startOffset);
                }
                if (correctedEndOffset < 0) {
                    failAlways("invalid end offset: " + correctedEndOffset + ", before correction: " + endOffset);
                }
                if (correctedStartOffset < lastOffset) {
                    failAlways("start offset went backwards: " + correctedStartOffset + ", before correction: " + startOffset + ", lastOffset: " + lastOffset);
                }
                lastOffset = correctedStartOffset;
                if (correctedEndOffset < correctedStartOffset) {
                    failAlways("end offset: " + correctedEndOffset + " is before start offset: " + correctedStartOffset);
                }
                offsetAttribute.setOffset(correctedStartOffset,correctedEndOffset);
                if(state==-1 || runAutomaton.isAccept(state)){
                    streamState=State.INCREMENT;
                    return true;
                }
            }
        }
        streamState=State.INCREMENT_FALSE;
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void end() throws IOException {

        try {
            super.end();    //To change body of overridden methods use File | Settings | File Templates.
            int finalOffset=correctOffset(off);
            offsetAttribute.setOffset(finalOffset,finalOffset);

            if(streamState!=State.INCREMENT_FALSE){
                fail("end() called before incrementToken() returned false!");
            }
        } finally {
            streamState=State.END;
        }
    }

    @Override
    public void close() throws IOException {
        try {
            super.close();    //To change body of overridden methods use File | Settings | File Templates.
            if(!(streamState==State.END || streamState == State.CLOSE)){
                fail("close() called in wrong state:"+streamState);
            }
        } finally {
            streamState=State.CLOSE;
        }
    }

    @Override
    public void reset() throws IOException {
        try {
            super.reset();    //To change body of overridden methods use File | Settings | File Templates.
            state=runAutomaton.getInitialState();
            off=0;
            lastOffset=off;
            bufferedCodePoint=-1;
            if(streamState==State.RESET){
                fail("double reset()");
            }
        } finally {
            streamState=State.RESET;
        }
    }

    protected int readCodePoint() throws IOException{
        int ch=readChar();
        if(ch<0){
            return ch;
        }else {
            if(Character.isLowSurrogate((char) ch)){
                failAlways("unpaired low surrogate:"+Integer.toHexString(ch));
            }
            off++;
            if(Character.isHighSurrogate((char) ch)){
                int ch2=readChar();
                if(ch2>=0){
                    off++;
                    if(!Character.isLowSurrogate((char) ch2)){
                        failAlways("unpaired high surrogate: " + Integer.toHexString(ch) + ", followed by: " + Integer.toHexString(ch2));
                    }
                    return Character.toCodePoint((char) ch,(char) ch2);
                }else {
                    failAlways("stream ends with unpaired high surrogate: " + Integer.toHexString(ch));
                }
            }
            return ch;
        }
    }
    protected int readChar() throws IOException{
        switch (random.nextInt(10)){
            case 0:{
                // read(char[])
                char c[] =new char[1];
                 int ret=input.read(c);
                return ret< 0 ? ret:c[0];
            }

            case 1:{
                // read(char[], int, int)
                char c[]=new char[2];
                int ret=input.read(c,1,1);
                return ret < 0 ?ret :c[1];
            }
            case 2:{
                // read(CharBuffer)
                char c[]=new char[1];
                CharBuffer cb=CharBuffer.wrap(c);
                int ret=input.read(cb);
                return ret < 0 ?ret :c[0];
            }
            default:
                //read()
                return input.read();
        }
    }
    protected boolean isTokenChar(int c){
        if(state<0){
            state=runAutomaton.getInitialState();
        }
        state=runAutomaton.step(state,c);
        if(state<0){
            return false;
        }else {
            return false;
        }
    }
    protected int normalize(int c){
        return lowerCase ? Character.toLowerCase(c) :c;
    }
    public void  setEnableChecks(boolean enableChecks){
        this.enableChecks=enableChecks;
    }
}
