package com.jt.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;

@Service
public class ItemService {
    // 注入持久层mapper, 获取数据，封装doc, 写入索引
    @Autowired
    private ItemMapper mapper;

    public void createIndex() throws IOException {
        List<Item> list = mapper.findAll();

        // 指向索引文件
        Path path = Paths.get("indexItem");
        Directory dir = FSDirectory.open(path);
        Analyzer analyzer = new SmartChineseAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(dir, config);
        // 构造doc对象,写入索引
        for (Item item : list) {
            // id price image title sellPoint
            Document doc = new Document();
            doc.add(new StringField("id", item.getId() + "", Store.YES));
            doc.add(new StringField("price", item.getPrice() + "", Store.YES));
            doc.add(new TextField("title", item.getTitle(), Store.YES));
            doc.add(new TextField("sellPoint", item.getSellPoint(), Store.YES));
            doc.add(new StringField("image", item.getImage(), Store.YES));
            writer.addDocument(doc);
        }
        
        writer.commit();
        writer.close();
        dir.close();
    }

    public List<Item> search(String title, String value) throws IOException {
        // 指向索引文件
        Path path = Paths.get("indexItem");
        Directory dir = FSDirectory.open(path);

        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);

        Term term = new Term(title, value);
        Query query = new TermQuery(term);
        TopDocs topDoc = searcher.search(query, 10);
        ScoreDoc[] docs = topDoc.scoreDocs;
        List<Item> itemList = new ArrayList<Item>();
        for (ScoreDoc scoreDoc : docs) { 
            Item item=new Item();
            Document document=searcher.doc(scoreDoc.doc);
            item.setId(Long.parseLong(document.get("id")));
            item.setPrice(Long.parseLong(document.get("price")));
            item.setTitle(document.get("title"));
            item.setImage(document.get("image"));
            item.setSellPoint(document.get("sellPoint"));
            itemList.add(item);
        }

        return itemList;
    }

}
