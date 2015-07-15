package org.wuheng.framework.lucene5.termvector;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queries.mlt.MoreLikeThisQuery;
import org.apache.lucene.search.IndexSearcher;
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
 * Time: 下午4:03
 * To change this template use File | Settings | File Templates.
 */
public class MoreLikeThisQueryTest {
    public static void  main(String[] args) throws IOException {
        String indexDir="";
        Directory directory= FSDirectory.open(Paths.get(indexDir));
        IndexReader reader= DirectoryReader.open(directory);
        IndexSearcher searcher=new IndexSearcher(reader);

        String[] moreLikeFields=new String[]{"title","author"};

        MoreLikeThisQuery query=new MoreLikeThisQuery("lucene in action",moreLikeFields,new StandardAnalyzer(),"author");
        query.setMinDocFreq(1);
        query.setMinTermFrequency(1);
        TopDocs topDocs=searcher.search(query,Integer.MAX_VALUE);
        ScoreDoc[] scoreDocs=topDocs.scoreDocs;
        for(ScoreDoc scoreDoc:scoreDocs){
            Document document=reader.document(scoreDoc.doc);
            //找到与文档id为1的书相似的的书
            System.out.println("  more like this: "+document.get("title"));
        }
    }
}
