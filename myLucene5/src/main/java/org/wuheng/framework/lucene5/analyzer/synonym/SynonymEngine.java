package org.wuheng.framework.lucene5.analyzer.synonym;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-27
 * Time: 下午5:05
 * To change this template use File | Settings | File Templates.
 */
public interface SynonymEngine {
    String[] getSynonyms(String s) throws IOException;
}
