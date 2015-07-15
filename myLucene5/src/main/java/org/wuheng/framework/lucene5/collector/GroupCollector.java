package org.wuheng.framework.lucene5.collector;

import org.apache.lucene.index.LeafReaderContext;
import org.apache.lucene.index.SortedDocValues;
import org.apache.lucene.search.Collector;
import org.apache.lucene.search.LeafCollector;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Scorer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-28
 * Time: 下午6:16
 * To change this template use File | Settings | File Templates.
 */

/**
 *     自定义Collector结果收集器
 */
public class GroupCollector implements Collector,LeafCollector {
    //评分计算器
    private Scorer scorer;
    //段文的编号
    private int docBaseId;
    private String fieldName;
    private SortedDocValues sortedDocValues;

    private List<ScoreDoc> scoreDocs=new ArrayList<ScoreDoc>();

    @Override
    public LeafCollector getLeafCollector(LeafReaderContext context) throws IOException {
        this.sortedDocValues=context.reader().getSortedDocValues(fieldName);
        return this;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setScorer(Scorer scorer) throws IOException {
        this.scorer=scorer;
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void collect(int doc) throws IOException {
        //scoreDoc：docId和评分
        this.scoreDocs.add(new ScoreDoc(this.docBaseId+doc,this.scorer.score()));
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public GroupCollector(String fieldName) {
        super();
        this.fieldName = fieldName;
    }

    public Scorer getScorer() {
        return scorer;
    }

    public int getDocBaseId() {
        return docBaseId;
    }

    public void setDocBaseId(int docBaseId) {
        this.docBaseId = docBaseId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public SortedDocValues getSortedDocValues() {
        return sortedDocValues;
    }

    public void setSortedDocValues(SortedDocValues sortedDocValues) {
        this.sortedDocValues = sortedDocValues;
    }

    public List<ScoreDoc> getScoreDocs() {
        return scoreDocs;
    }

    public void setScoreDocs(List<ScoreDoc> scoreDocs) {
        this.scoreDocs = scoreDocs;
    }
}
