package org.wuheng.framework.lucene5.pinyin;

import com.ibm.icu.text.Replaceable;
import com.ibm.icu.text.RuleBasedTransliterator;
import com.ibm.icu.text.Transliterator;
import com.ibm.icu.text.UnicodeSet;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import sun.text.normalizer.UTF16;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-26
 * Time: 下午3:13
 * To change this template use File | Settings | File Templates.
 */
public class PimICUTransformFilter extends TokenFilter {
    /**
     * 首汉字全拼后续汉字简拼
     *  Transliterator.createFromRules(null,
     *  ":: Han-Latin/Names;[[:space:]][bpmfdtnlgkhjqxzcsryw] { [[:any:]-[:white_space:]] >;::NFD;[[:NonspacingMark:][:Space:]]>;",
     *  Transliterator.FORWARD)
     */
    private final Transliterator[] transliterators;

    //Reusable position object
    private final Transliterator.Position position=new Transliterator.Position();

    //term attribute,will be updated with transformed text
    private final CharTermAttribute termAttribute=addAttribute(CharTermAttribute.class);

    //wraps a termAttribute  around the replaceable interface
    private final  ReplaceableTermAttribute replaceableTermAttribute=new ReplaceableTermAttribute();

    private char[] curTermBuffer;
    private int curTermLength;
    private int currentTransform;

    @SuppressWarnings("deprecation")
    public PimICUTransformFilter(TokenStream input, Transliterator[] transliterators) {
        super(input);
        this.transliterators = transliterators;
        for(Transliterator transliterator:transliterators){
            if(transliterator.getFilter()==null && transliterator instanceof RuleBasedTransliterator){
                final UnicodeSet sourceSet=transliterator.getSourceSet();
                if(sourceSet != null && !sourceSet.isEmpty()){
                    transliterator.setFilter(sourceSet);
                }
            }
        }
    }

    @Override
    public boolean incrementToken() throws IOException {
        while (true){
            if(curTermBuffer==null){
                if(!input.incrementToken()){
                    return false;
                }else{
                    curTermBuffer=termAttribute.buffer().clone();
                    curTermLength=curTermBuffer.length;
                    currentTransform=0;
                }
            }
            if(currentTransform<transliterators.length){
                termAttribute.copyBuffer(curTermBuffer,0,curTermLength);
                termAttribute.setLength(curTermLength);
                replaceableTermAttribute.setText(termAttribute);

                final int length=termAttribute.length();
                position.start=0;
                position.limit=length;
                position.contextStart=0;
                position.contextLimit=length;

                transliterators[currentTransform++].filteredTransliterate(
                        replaceableTermAttribute,position,false);
                return true;
            }
            curTermBuffer=null;
        }
    }

    final class ReplaceableTermAttribute implements Replaceable{
        private char buffer[];
        private int length;
        private CharTermAttribute token;

        public void setText(final CharTermAttribute token){
            this.token=token;
            this.buffer=token.buffer();
            this.length=token.length();
        }
        @Override
        public int length() {
            return length;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public char charAt(int offset) {
            return buffer[offset];  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public int char32At(int offset) {
            return UTF16.charAt(buffer,0,length,offset);  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void getChars(int srcStart, int srcLimit, char[] dst, int dstStart) {
            System.arraycopy(buffer,srcStart,dst,dstStart,srcLimit-srcStart); //实现数组之间的复制,dst目标数组
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void replace(int start, int limit, String text) {
            final int charsLen=text.length();
            final  int newLength=shiftForReplace(start,limit,charsLen);
            text.getChars(0,charsLen,buffer,start);
            length=newLength;
            token.setLength(length);
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void replace(int start, int limit, char[] chars, int charsStart, int charsLen) {
            final int newLength=shiftForReplace(start,limit,charsLen);
            System.arraycopy(chars,charsStart,buffer,start,charsLen);
            length=newLength;
            token.setLength(length);
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void copy(int start, int limit, int dest) {
            char text[] =new char[limit-start];
            getChars(start,limit,text,0);
            replace(dest,dest,text,0,limit-start);
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public boolean hasMetaData() {
            return false;  //To change body of implemented methods use File | Settings | File Templates.
        }

        private int shiftForReplace(int start,int limit,int charsLen){
            final int replacementLength=limit-start;
            final int newLength=length-replacementLength+charsLen;
            if(newLength>limit){
                buffer=token.resizeBuffer(newLength);
            }
            if(replacementLength != charsLen && limit<length){
                System.arraycopy(buffer,limit,buffer,start+charsLen,length-limit);
            }
            return newLength;
        }
    }
}
