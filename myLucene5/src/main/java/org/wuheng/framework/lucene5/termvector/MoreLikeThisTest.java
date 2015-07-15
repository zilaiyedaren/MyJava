package org.wuheng.framework.lucene5.termvector;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queries.mlt.MoreLikeThis;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-28
 * Time: 下午3:55
 * To change this template use File | Settings | File Templates.
 */

/**
 * 更多与此相似
 */
public class MoreLikeThisTest {
    public static void  main(String[] args) throws IOException {
        String indexDir="";
        Directory directory= FSDirectory.open(Paths.get(indexDir));
        IndexReader reader= DirectoryReader.open(directory);
        IndexSearcher searcher=new IndexSearcher(reader);
        MoreLikeThis moreLikeThis=new MoreLikeThis(reader);
        moreLikeThis.setAnalyzer(new StandardAnalyzer());
        moreLikeThis.setFieldNames(new String[]{"title","author","subject"});
        moreLikeThis.setMinDocFreq(1);
        moreLikeThis.setMinTermFreq(1);
        int docNum=1;
        Query query=moreLikeThis.like(docNum);

        TopDocs topDocs=searcher.search(query,Integer.MAX_VALUE);
        ScoreDoc[] scoreDocs=topDocs.scoreDocs;
        //文档id为1的书
        System.out.println(reader.document(docNum).get("title")+"------>");
        for(ScoreDoc scoreDoc:scoreDocs){
            Document document=reader.document(scoreDoc.doc);
            //找到与文档id为1的书相似的书
            System.out.println("  more like this:"+document.get("title"));
        }

    }
}
