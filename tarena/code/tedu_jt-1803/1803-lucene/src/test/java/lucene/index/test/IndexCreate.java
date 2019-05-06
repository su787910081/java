package lucene.index.test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import com.jt.lucene.IK.IKAnalyzer6x;

public class IndexCreate {

    /**
     * 创建索引，基本单位: 文档，只要有文档对象索引就可以创建，手动拼接文档数据
     * @throws IOException 
     */
    @Test
    public void createIndex() throws IOException {
        // 第一步：指向一个索引文件的目录(文件夹)，写出的索引文件都保存在这个目录
        // 文件都保存在这个目录
        Path indexPath = Paths.get("indexdir");
        // lucene 的dir 对象可以实现索引的流输出
        Directory dir = FSDirectory.open(indexPath);
        // 第二步：引入一个创建索引计算分词的分词器IK
        Analyzer analyzer = new IKAnalyzer6x();
        // 将analyzer 添加到索引创建的配置对象中；
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        // 写出的indexdir 文件在第二次创建时，会被覆盖
        //          OpenMode.APPEND
        config.setOpenMode(OpenMode.CREATE_OR_APPEND);
        // 第三步：生成索引文件保存的document 文档对象；
        Document doc1 = new Document();
        Document doc2 = new Document();
        // 在其中添加些数据，3-5 域 字段值
        
        // id 表示当前域的名称，相当 于字段名，100 表示值
        // Strore.YES  文档对象一但存储到索引文件，占用空间，对于一些没有必要存储在文件中的数据可以调用 Store.NO
        // 在创建索引文件时，该 字段的值，不会保存在文档中，即使搜到了文档对象，也不能获取。
        // 域的类型， String --  varchar(数据库)   -- StringField/TextField
        doc1.add(new StringField("id", "100", Store.NO));
        doc1.add(new TextField("title", " 三星 Galaxy S 轻奢版平板", Store.YES));
        doc1.add(new TextField("sell_point", "白条12期免息", Store.YES));
        doc1.add(new IntPoint("price", 2000));
        doc1.add(new StringField("price", "2000", Store.YES));
        
        doc2.add(new StringField("id", "101", Store.YES));
        doc2.add(new TextField("title", " 苹果 Galaxy S 轻奢版", Store.YES));
        doc2.add(new TextField("sell_point", "不要钱平板", Store.YES));
        
        // 第四步，将文档数据输出到索引 文件
        IndexWriter writer = new IndexWriter(dir, config);
        // 将文档数据添加到输出流中
        writer.addDocument(doc1);
        writer.addDocument(doc2);
        
        writer.commit();
        writer.close();
        dir.close();
    }
    
    // 更新索引
    @Test
    public void updateIndex() {
        Path indexPath = Paths.get("indexdir");
        Directory dir;
        // 分词计算器，配置对象
        Analyzer analyzer = new IKAnalyzer6x();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        
        try {
            // 第一步，指向dir
            dir = FSDirectory.open(indexPath);
            // 第二步，构造新的对象数据
            Document doc = new Document();
            IndexWriter writer = new IndexWriter(dir, config);
            doc.add(new StringField("id", "2", Store.YES));
            doc.add(new TextField("title", "达内大平板，我们不是专业的", Store.YES));
            doc.add(new TextField("sell_point", "好用", Store.YES));
            // 第三步，根据条件更新覆盖原文档对象
            
            // 参数Term:
            // Term 参数1：域名，根据哪个域名更新
            // Term 参数2：域数据的分词词项，大概星，搜索带有三星分词词项的第一个document 对象进行覆盖
            // 参数doc: 数据替代者
            writer.updateDocument(new Term("title", "苹果"), doc);
            writer.commit();
            writer.close();
            dir.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void deleteIndex() {
        Path indexPath = Paths.get("indexdir");
        Directory dir;
        // 分词计算器，配置对象
        Analyzer analyzer = new IKAnalyzer6x();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        
        try {
            dir = FSDirectory.open(indexPath);
            // writer
            IndexWriter writer = new IndexWriter(dir, config);
            // 删 deleteDocument 的方法参数不同
            // 词项对比删除，必须词项完全匹配，才删除
            // Query: 查询对象，Term,匹配规则
            writer.deleteDocuments(new Term("sell_point", "不要钱"));
            
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
