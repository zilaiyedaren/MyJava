package org.wuheng.framework.lucene5.collector;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-28
 * Time: 下午6:26
 * To change this template use File | Settings | File Templates.
 */
public class GroupCollectorTest {
    public static void main(String[] args) throws IOException {
        String indexDir="D:/dictionary";
        Directory directory= FSDirectory.open(Paths.get(indexDir));
        IndexReader indexReader=DirectoryReader.open(directory);
        IndexSearcher indexSearcher=new IndexSearcher(indexReader);
        TermQuery termQuery=new TermQuery(new Term("title","lucene"));
        GroupCollector collector=new GroupCollector("title2");
        indexSearcher.search(termQuery,null,collector);
        List<ScoreDoc> docs=collector.getScoreDocs();
        for(ScoreDoc scoreDoc:docs){
            int docID=scoreDoc.doc;
            Document document=indexSearcher.doc(docID);
            String title=document.get("title");
            float score=scoreDoc.score;
            System.out.println(docID+":"+title+"  "+score);
        }
        indexReader.close();
        directory.close();
    }
}
