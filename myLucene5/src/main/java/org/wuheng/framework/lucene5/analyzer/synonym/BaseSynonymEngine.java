package org.wuheng.framework.lucene5.analyzer.synonym;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-27
 * Time: 下午5:09
 * To change this template use File | Settings | File Templates.
 */
public class BaseSynonymEngine implements SynonymEngine {
    private static HashMap<String,String[]> map=new HashMap<String, String[]>();
    static {
        map.put("quick",new String[]{"fast","speedy"});
        map.put("jumps", new String[] {"leaps","hops"});
        map.put("over", new String[] {"above"});
        map.put("lazy", new String[] {"apathetic","slugish"});
        map.put("dog", new String[] {"canine","pooch"});
    }
    @Override
    public String[] getSynonyms(String s) throws IOException {
        return map.get(s);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
