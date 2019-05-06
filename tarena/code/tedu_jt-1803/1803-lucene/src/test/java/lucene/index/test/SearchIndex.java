package lucene.index.test;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import com.jt.lucene.IK.IKAnalyzer6x;

public class SearchIndex {

    /*
     * 多域查询
     */
    @Test
    public void searchMulti() throws Exception {
        // 1. 路径
        Path path = Paths.get("indexdir");
        Directory dir = FSDirectory.open(path);
        // 2. 搜索对象创建searcher
        // 对接一个输入流
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        // 搜索使用的分词器IK
        Analyzer analyzer = new IKAnalyzer6x();
        // 3. 构建查询条件
        // 指定查询的多个域名称
        String[] fields = { "title", "sell_point" };
        // 利用多域对象fields 和分词器构造查询条件
        // 查询条件解析器，可以收集查询的环境，生成查询条件对象
        MultiFieldQueryParser parser = new MultiFieldQueryParser(fields,
                analyzer);
        Query query = parser.parse("平板");
        // 这条件可以使用查询，只要两个域有一个包含词项，就会搜索到
        System.out.println(query.toString());
        // 4. 获取数据for 循环遍历；默认情况下，每个doc 返回时
        // 封装到一个topDoc 的对象中，底层包装了一个数组，评分
        // doc; 循环数组，调用api 获取数据
        TopDocs topDoc = searcher.search(query, 10); // 返回所有数据前10 条
        // 封装了获取doc 的所有条件的docs 对象
        ScoreDoc[] docs = topDoc.scoreDocs;
        for (ScoreDoc scoreDoc : docs) {
            // 获取document 的文件
            Document document = searcher.doc(scoreDoc.doc);
            System.out.println("id: " + document.get("id") + ", title: "
                    + document.get("title: ") + ", sell_point: "
                    + document.get("sell_point"));
        }
    }

    // 词项查询
    @Test
    public void termQuery() throws Exception {
        // 1 路径
        Path path = Paths.get("indexdir");
        Directory dir = FSDirectory.open(path);
        // 2 搜索对象创建searcher
        // 对接一个输入流
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        // 搜索使用的分词器IK
        // Analyzer analyzer = new IKAnalyzer6x();

        // 词项查询条件
        Term term = new Term("title", "苹果");
        Query query = new TermQuery(term);

        System.out.println("query条件:" + query.toString());
        // 4 获取数据for循环遍历;默认情况下,每个doc返回时
        // 封装到一个topDoc的对象中,底层包装了一个数组,评分
        // doc;循环数组,调用api获取数据
        TopDocs topDoc = searcher.search(query, 10);// 返回所有数据前10条
        // 封装了获取doc的所有条件的docs对象
        ScoreDoc[] docs = topDoc.scoreDocs;
        for (int i = 0; i < docs.length; i++) {
            // 获取document的文件
            Document document = searcher.doc(docs[i].doc);
            System.out.println("id:" + document.get("id"));
            System.out.println("title:" + document.get("title"));
            System.out.println("sell_point:" + document.get("sell_point"));
        }
    }

    @Test
    public void booleanQuery() throws Exception {
        // 1 路径
        Path path = Paths.get("indexdir");
        Directory dir = FSDirectory.open(path);
        // 2 搜索对象创建searcher
        // 对接一个输入流
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        // 搜索使用的分词器IK
        // Analyzer analyzer = new IKAnalyzer6x();

        // 词项查询条件
        // 设置多个查询的条件对象
        Query query1 = new TermQuery(new Term("title", "苹果"));
        Query query2 = new TermQuery(new Term("sell_point", "平板"));
        // 指定逻辑关系,title有苹果,sellpiont没平板
        BooleanClause bc1 = new BooleanClause(query1, Occur.MUST);
        BooleanClause bc2 = new BooleanClause(query2, Occur.MUST_NOT);
        BooleanQuery query = new BooleanQuery.Builder().add(bc1).add(bc2)
                .build();

        System.out.println("query条件:" + query.toString());
        // 4 获取数据for循环遍历;默认情况下,每个doc返回时
        // 封装到一个topDoc的对象中,底层包装了一个数组,评分
        // doc;循环数组,调用api获取数据
        TopDocs topDoc = searcher.search(query, 10);// 返回所有数据前10条
        // 封装了获取doc的所有条件的docs对象
        ScoreDoc[] docs = topDoc.scoreDocs;
        for (int i = 0; i < docs.length; i++) {
            // 获取document的文件
            Document document = searcher.doc(docs[i].doc);
            System.out.println("id:" + document.get("id"));
            System.out.println("title:" + document.get("title"));
            System.out.println("sell_point:" + document.get("sell_point"));

        }
    }

    /*
     * 范围查询，必须对应具有intPoint, longPoint 的文档数据
     */
    @Test
    public void rangeSearch() throws Exception {
        // 1 路径
        Path path = Paths.get("indexdir");
        Directory dir = FSDirectory.open(path);
        // 2 搜索对象创建searcher
        // 对接一个输入流
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        // 搜索使用的分词器IK
        // Analyzer analyzer = new IKAnalyzer6x();

        // 词项查询条件
        Query query = IntPoint.newRangeQuery("price", 0, 2550);

        System.out.println("query条件:" + query.toString());
        // 4 获取数据for循环遍历;默认情况下,每个doc返回时
        // 封装到一个topDoc的对象中,底层包装了一个数组,评分
        // doc;循环数组,调用api获取数据
        TopDocs topDoc = searcher.search(query, 10);// 返回所有数据前10条
        // 封装了获取doc的所有条件的docs对象
        ScoreDoc[] docs = topDoc.scoreDocs;
        for (int i = 0; i < docs.length; i++) {
            // 获取document的文件
            Document document = searcher.doc(docs[i].doc);
            System.out.println("id:" + document.get("id"));
            System.out.println("title:" + document.get("title"));
            System.out.println("sell_point:" + document.get("sell_point"));
            System.out.println("price:" + document.get("price"));

        }
    }

    /*
     * 前缀查询
     */
    @Test
    public void prefixSearch() throws Exception {
        // 1 路径
        Path path = Paths.get("indexdir");
        Directory dir = FSDirectory.open(path);
        // 2 搜索对象创建searcher
        // 对接一个输入流
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        // 搜索使用的分词器IK
        // Analyzer analyzer = new IKAnalyzer6x();

        // 词项查询条件
        Term term = new Term("title", "三星");
        Query query = new PrefixQuery(term);
        System.out.println("query条件:" + query.toString());
        // 4 获取数据for循环遍历;默认情况下,每个doc返回时
        // 封装到一个topDoc的对象中,底层包装了一个数组,评分
        // doc;循环数组,调用api获取数据
        TopDocs topDoc = searcher.search(query, 10);// 返回所有数据前10条
        // 封装了获取doc的所有条件的docs对象
        ScoreDoc[] docs = topDoc.scoreDocs;
        for (int i = 0; i < docs.length; i++) {
            // 获取document的文件
            Document document = searcher.doc(docs[i].doc);
            System.out.println("id:" + document.get("id"));
            System.out.println("title:" + document.get("title"));
            System.out.println("sell_point:" + document.get("sell_point"));
            System.out.println("price:" + document.get("price"));

        }

    }

    /*
     * 多关键字查询
     * 
     */
    @Test
    public void mutiTermQuery() throws Exception {
        // 1 路径
        Path path = Paths.get("indexdir");
        Directory dir = FSDirectory.open(path);
        // 2 搜索对象创建searcher
        // 对接一个输入流
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        // 搜索使用的分词器IK
        // Analyzer analyzer = new IKAnalyzer6x();

        // 多关键字查询
        PhraseQuery.Builder build = new PhraseQuery.Builder();
        // 添加多个关键字
        build.add(new Term("title", "苹果"));
        build.add(new Term("title", "平板"));
        PhraseQuery query = build.build();

        System.out.println("query条件:" + query.toString());
        // 4 获取数据for循环遍历;默认情况下,每个doc返回时
        // 封装到一个topDoc的对象中,底层包装了一个数组,评分
        // doc;循环数组,调用api获取数据
        TopDocs topDoc = searcher.search(query, 10);// 返回所有数据前10条
        // 封装了获取doc的所有条件的docs对象
        ScoreDoc[] docs = topDoc.scoreDocs;
        for (int i = 0; i < docs.length; i++) {
            // 获取document的文件
            Document document = searcher.doc(docs[i].doc);
            System.out.println("id:" + document.get("id"));
            System.out.println("title:" + document.get("title"));
            System.out.println("sell_point:" + document.get("sell_point"));
            System.out.println("price:" + document.get("price"));

        }
    }
}
