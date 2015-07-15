package org.wuheng.framework.lucene5.spatial;

import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.distance.DistanceUtils;
import com.spatial4j.core.shape.Point;
import com.spatial4j.core.shape.Shape;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericDocValuesField;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queries.function.ValueSource;
import org.apache.lucene.search.*;
import org.apache.lucene.spatial.SpatialStrategy;
import org.apache.lucene.spatial.prefix.RecursivePrefixTreeStrategy;
import org.apache.lucene.spatial.prefix.tree.GeohashPrefixTree;
import org.apache.lucene.spatial.prefix.tree.SpatialPrefixTree;
import org.apache.lucene.spatial.query.SpatialArgs;
import org.apache.lucene.spatial.query.SpatialOperation;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * Created with IntelliJ IDEA.
 * User: wuheng
 * Date: 15-6-28
 * Time: 下午4:12
 * To change this template use File | Settings | File Templates.
 */

/**
 *  Lucene地理位置查询测试
 */
public class LuceneSpatialTest {
    //Spatial上下文
    private SpatialContext context;
    //提供索引和查询模型的策略接口
    private SpatialStrategy strategy;
    //索引目录
    private Directory directory;

    //Spatial初始化
    protected void init(){
        //SpatialContext 也可以通过SpatialContextFactory工厂类构建
        this.context=SpatialContext.GEO;
        //网络最大11层
        int maxLevels=11;
        //SpatialPrefixTree 也可以通过SpatialfixTreeFactory工厂构建
        SpatialPrefixTree grid=new GeohashPrefixTree(context,maxLevels);
        this.strategy=new RecursivePrefixTreeStrategy(grid,"myGeoField");
        //初始化索引目录
        this.directory=new RAMDirectory();
    }
    private void indexPoints() throws Exception{
        IndexWriterConfig config=new IndexWriterConfig(new IKAnalyzer());
        IndexWriter indexWriter=new IndexWriter(directory,config);
        //这里的x,y即经纬度，x为Longitude，y为Latitude
        indexWriter.addDocument(newSampleDocument(2,context.makePoint(-80.93,33.77)));
        //WKT表示法：POINT（Longitude，Latitude）
        indexWriter.addDocument(newSampleDocument(4,context.readShapeFromWkt("POINT(60.9289094 -50.7693246)")));
        indexWriter.addDocument(newSampleDocument(20,context.makePoint(0.1,0.1)));
        indexWriter.close();
    }

    /**
     *创建Document索引对象
     * @param id
     * @param shapes
     * @return
     */
    private Document newSampleDocument(int id,Shape... shapes){
        Document document=new Document();
        document.add(new StoredField("id",id));
        document.add(new NumericDocValuesField("id",id));
        for(Shape shape:shapes){
            for(Field field:strategy.createIndexableFields(shape)){
                document.add(field);
            }
            Point point=(Point)shape;
            document.add(new StoredField(strategy.getFieldName(),point.getX()+" "+point.getY()));
        }
        return document;
    }

    private void search() throws Exception{
        IndexReader indexReader= DirectoryReader.open(directory);
        IndexSearcher indexSearcher=new IndexSearcher(indexReader);
        //按照id升序排列
        Sort idSort=new Sort(new SortField("id", SortField.Type.INT));
        //搜索方圆200千米范围以内,这里-80.0, 33.0分别是当前位置的经纬度，以当前位置为圆心，200米为半径画圆
        //注意后面的EARTH_MEAN_RADIUS_KM表示200的单位是千米。
        SpatialArgs args=new SpatialArgs(SpatialOperation.Intersects,context.makeCircle(-80.0,33.0,
                DistanceUtils.degrees2Dist(200,DistanceUtils.EARTH_EQUATORIAL_RADIUS_KM)));
        //根据SpatialArgs参数创建过滤器
        Filter filter=strategy.makeFilter(args);
        //开始搜索
        TopDocs docs=indexSearcher.search(new MatchAllDocsQuery(),filter,10,idSort);

        Document document=indexSearcher.doc(docs.scoreDocs[0].doc);
        String docStr=document.getField(strategy.getFieldName()).stringValue();
        int spaceIdx=docStr.indexOf(' ');
        double x=Double.parseDouble(docStr.substring(0,spaceIdx));
        double y=Double.parseDouble(docStr.substring(spaceIdx+1));
        double docDistDEG=context.calcDistance(args.getShape().getCenter(),x,y);
        System.out.println("(Longitude,Latitude):"+"("+x+","+y+")");
        System.out.println("docDistDEG:"+docDistDEG*DistanceUtils.DEG_TO_KM);
        System.out.println(DistanceUtils.degrees2Dist(docDistDEG,DistanceUtils.EARTH_EQUATORIAL_RADIUS_KM));

        //定义一个坐标点(x,y)即当前用户所在地点
        Point point=context.makePoint(60,-50);
        //计算当前用户所在坐标点与索引坐标点之间的距离即当前用户地点与每个待匹配地点之间的距离，DEG_TO_KM表示以KM为单位
        ValueSource valueSource=strategy.makeDistanceValueSource(point,DistanceUtils.DEG_TO_KM);
        //根据命中点与当前位置坐标点的距离远近降序排列，距离数字越大的排在前面，false表示降序，true表示升序
        Sort distSort=new Sort(valueSource.getSortField(false)).rewrite(indexSearcher);
        TopDocs topDocs=indexSearcher.search(new MatchAllDocsQuery(),10,distSort);
        ScoreDoc[] scoreDocs=topDocs.scoreDocs;
        for(ScoreDoc scoreDoc:scoreDocs){
            int docID=scoreDoc.doc;
            Document doc=indexSearcher.doc(docID);
            int gotId=doc.getField("id").numericValue().intValue();
            String geoField=doc.getField(strategy.getFieldName()).stringValue();
            int xy=geoField.indexOf(' ');
            double xPoint=Double.parseDouble(geoField.substring(0,xy));
            double yPoint=Double.parseDouble(geoField.substring(xy+1));
            double distEDG=context.calcDistance(args.getShape().getCenter(),xPoint,yPoint);
            double distance=DistanceUtils.degrees2Dist(distEDG,DistanceUtils.EARTH_EQUATORIAL_RADIUS_KM);
            System.out.println("docId:"+docID+",id:"+gotId+",distance:"+distance+"KM");

        }
        indexReader.close();
    }

    public static void main(String[] args) throws Exception{
        LuceneSpatialTest luceneSpatialTest=new LuceneSpatialTest();
        luceneSpatialTest.init();
        luceneSpatialTest.indexPoints();
        luceneSpatialTest.search();
    }
}
