package org.wuheng.framework.lucene5.analyzer.positiongap;

import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;


/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-27
 * Time: 下午5:50
 * To change this template use File | Settings | File Templates.
 */
public class GapTest {
    public static void main(String[] args) throws IOException,ParseException {
        final Directory directory=new RAMDirectory();
        final IndexWriterConfig config=new IndexWriterConfig(new SimpleAnalyzer());
        final IndexWriter writer=new IndexWriter(directory,config);

        Document document=new Document();
        document.add(new TextField("body","A B C", Field.Store.YES));
        //放 10个间隙
        document.add(new TextField("body", new PositionIncrementTokenStream(10)));
        document.add(new TextField("body", "D E F", Field.Store.YES));
        System.out.println(document);
        writer.addDocument(document);
        writer.close();

        final IndexReader reader= DirectoryReader.open(directory);
        IndexSearcher indexSearcher=new IndexSearcher(reader);

        QueryParser queryParser=new QueryParser("body",new SimpleAnalyzer());
        for(String queryString:new String[]{"\"A B C\"", "\"A B C D\"",
                "\"A B C D\"", "\"A B C D\"~10", "\"A B C D E F\"~10",
                "\"A B C D F E\"~10", "\"A B C D F E\"~11" }){
            Query query=queryParser.parse(queryString);
            TopDocs docs=indexSearcher.search(query,10);
            System.out.println(docs.totalHits+"\t"+queryString);
        }
        reader.close();
    }
}
