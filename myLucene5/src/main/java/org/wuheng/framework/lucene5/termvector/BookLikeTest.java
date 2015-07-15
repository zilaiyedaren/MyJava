package org.wuheng.framework.lucene5.termvector;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.CharsRefBuilder;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-28
 * Time: 下午2:46
 * To change this template use File | Settings | File Templates.
 */
public class BookLikeTest {
    public static void main(String[] args) throws IOException {
        String indexDir="";
        Directory directory= FSDirectory.open(Paths.get(indexDir));
        IndexReader indexReader= DirectoryReader.open(directory);
        IndexSearcher indexSearcher=new IndexSearcher(indexReader);
        //最大索引文档ID
        int numDocs=indexReader.numDocs();
        BookLikeTest bookLikeTest=new BookLikeTest();
        for(int i=0;i<numDocs;i++){
            System.out.println("-------------------------------------------------");
            Document document=indexReader.document(i);
            System.out.println(document.get("title"));
            Document[] documents=bookLikeTest.docsLike(indexReader,indexSearcher,i,10);
            if(documents.length==0){
                System.out.println("---->Sorry,None like this");
            }
            for(Document likeDoc:documents){
                System.out.println("---->"+likeDoc.get("title"));
            }
        }
        indexReader.close();
        directory.close();
    }

    public Document[] docsLike(IndexReader reader,IndexSearcher searcher,int id ,int max) throws IOException{
        //根据文档id加载文档对象
        Document document=reader.document(id);
        //获取所有作者
        String[] authors=document.getValues("author");
        BooleanQuery authorQuery=new BooleanQuery();
        //遍历所有作者
        for(String author:authors){
            //包含所有作者的书籍
            authorQuery.add(new TermQuery(new Term("author",author)), BooleanClause.Occur.SHOULD);
        }
        //authorQuery权重乘以2
        authorQuery.setBoost(2.0f);

        //获取subject域的项向量
        Terms vector=reader.getTermVector(id,"subject");
        TermsEnum termsEnum=vector.iterator(null);
        CharsRefBuilder charsRefBuilder=new CharsRefBuilder();
        BytesRef text=null;
        BooleanQuery subjectQuery=new BooleanQuery();
        while((text=termsEnum.next())!=null){
            charsRefBuilder.copyUTF8Bytes(text);
            String term=charsRefBuilder.toString();
            TermQuery termQuery=new TermQuery(new Term("subject",term));
            subjectQuery.add(termQuery, BooleanClause.Occur.SHOULD);
        }
        BooleanQuery likeThisQuery=new BooleanQuery();
        likeThisQuery.add(authorQuery,BooleanClause.Occur.SHOULD);
        likeThisQuery.add(subjectQuery, BooleanClause.Occur.SHOULD);

        //排除自身
        likeThisQuery.add(new TermQuery(new Term("isbn",document.get("isbn"))), BooleanClause.Occur.SHOULD);
        TopDocs hits=searcher.search(likeThisQuery,10);
        int size=max;
        if(max>hits.scoreDocs.length){
            size=hits.scoreDocs.length;
        }
        Document[] docs=new Document[size];
        for(int i=0;i<max;i++){
            docs[i]=reader.document(hits.scoreDocs[i].doc);
        }
        return docs;
    }
}
