

- ## IK 分词器，就是对一段文本进行最小单位词组的划分
    > - 使用
    >> - IK分词器的版本是和lucene严格匹配的;  `6.0.0--6.0.0`
    >> - 小众的jar包,maven中央库没有; 手动导包; `IKAnalyzer2012_u6.jar`
    >> - 需要自行调用IK对应版本的方法实现两个类的接口
    > - 配置文件
    >> - 我们可以添加我们要启用的一些字典以及停用的一些字典
    >> - 在配置中我们添加了两个二级配置文件，这些配置文件中就是要启用与停用的字典
    >> <details>
    >> <summary><mark><font color=darkred>IKAnalyzer.cfg.xml</font></mark></summary>
    >> 
    >>      <?xml version="1.0" encoding="UTF-8"?>
    >>      <!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">  
    >>      <properties>  
    >>          <comment>IK Analyzer 扩展配置</comment>
    >>          <!--用户可以在这里配置自己的扩展字典 -->
    >>          <entry key="ext_dict">ext.dic;</entry> 
    >>          
    >>          <!--用户可以在这里配置自己的扩展停止词字典-->
    >>          <entry key="ext_stopwords">stopword.dic;</entry> 
    >>          
    >>      </properties>
    >> </details>
    >> 
    > - 小结
    >> 1. 实现tokenizer analyzer的接口
    >> 1. 将xml ext.dic,stopword.dic放到类加载器的所在路径
    >> 1. maven--classes
    >> 1. 代码编解码,和词典编解码一致;


- ## Lucene 

- ### 解释说明
    > - 常用field类型
    >> - TextField: 字符串类型数据;数据内容经过分词计算标题,买点,文章内容
    >> - StringFiel:字符串类型的数据;数据内容不做分词计算id,url,image链接,barcode(条形码)
    > - 记录数据的类型
    >> - IntPoint
    >> - LongPoint
    >> - DoublePoint
    >> - FloatPoint
    >> - 以上四种不存储数据,只做创建索引后的范围计算等筛选功能的引用;
    >> - 一旦需要展示,搜索后获取回来这个字段数据;需要使用字符串类型的同名域,来保存数据;


- ### 工具
    > - 利用luke软件观察索引文件中的内容
    > - 版本要和lucene完全匹配
    > - `java-4th-1803\java-第四阶段-1803-资料\1803京淘课前资料\01-课前资料\37-Lucene\lucene传给学生资料\luke-6.0.0-luke-release`

- ### 添加pom 的依赖
    > - 一个一个加，不然很容易卡死在那里
    >> 
    >> <details>
    >> <summary><mark><font color=darkred>pom 依赖</font></mark></summary>
    >> 
    >>         <dependency> <!-- 查询相关jar包 -->
	>>     		<groupId>org.apache.lucene</groupId>
	>>     		<artifactId>lucene-queryparser</artifactId>
	>>     		<version>6.0.0</version>
	>>     	</dependency>
	>>     	<dependency> <!-- lucene自带只能中文分词器jar包 -->
    >>             <groupId>org.apache.lucene</groupId>
    >>             <artifactId>lucene-analyzers-smartcn</artifactId>
    >>             <version>6.0.0</version>
    >>         </dependency>
    >>         <dependency> <!-- 测试用到的lucene工具包 -->
    >>             <groupId>org.apache.lucene</groupId>
    >>             <artifactId>lucene-analyzers-common</artifactId>
    >>             <version>6.0.0</version>
    >>         </dependency>
    >>         <dependency> <!-- 测试用到的lucene核心包 -->
    >>             <groupId>org.apache.lucene</groupId>
    >>             <artifactId>lucene-core</artifactId>
    >>             <version>6.0.0</version>
    >>         </dependency>
    >> </details>

- ### 索引: 增删改

    >> <details>
    >> <summary><mark><font color=darkred>引入包</font></mark></summary>
    >> 
    >>      package lucene.index.test;
    >>      
    >>      import java.io.IOException;
    >>      import java.nio.file.Path;
    >>      import java.nio.file.Paths;
    >>      
    >>      import org.apache.lucene.analysis.Analyzer;
    >>      import org.apache.lucene.document.Document;
    >>      import org.apache.lucene.document.StringField;
    >>      import org.apache.lucene.document.TextField;
    >>      import org.apache.lucene.document.Field.Store;
    >>      import org.apache.lucene.document.IntPoint;
    >>      import org.apache.lucene.index.IndexWriter;
    >>      import org.apache.lucene.index.IndexWriterConfig;
    >>      import org.apache.lucene.index.IndexWriterConfig.OpenMode;
    >>      import org.apache.lucene.index.Term;
    >>      import org.apache.lucene.store.Directory;
    >>      import org.apache.lucene.store.FSDirectory;
    >>      import org.junit.Test;
    >>      
    >>      import com.jt.lucene.IK.IKAnalyzer6x;
    >> </details>
    >>      
    >>      
    >> - 创建索引，基本单位: 文档，只要有文档对象索引就可以创建，手动拼接文档数据
    >> 
    >> <details>
    >> <summary><mark><font color=darkred>创建索引示例</font></mark></summary>
    >> 
    >>          @Test
    >>          public void createIndex() throws IOException {
    >>              // 第一步：指向一个索引文件的目录(文件夹)，写出的索引文件都保存在这个目录
    >>              // 文件都保存在这个目录
    >>              Path indexPath = Paths.get("indexdir");
    >>              // lucene 的dir 对象可以实现索引的流输出
    >>              Directory dir = FSDirectory.open(indexPath);
    >>              // 第二步：引入一个创建索引计算分词的分词器IK
    >>              Analyzer analyzer = new IKAnalyzer6x();
    >>              // 将analyzer 添加到索引创建的配置对象中；
    >>              IndexWriterConfig config = new IndexWriterConfig(analyzer);
    >>              // 写出的indexdir 文件在第二次创建时，会被覆盖
    >>              //          OpenMode.APPEND
    >>              config.setOpenMode(OpenMode.CREATE_OR_APPEND);
    >>              // 第三步：生成索引文件保存的document 文档对象；
    >>              Document doc1 = new Document();
    >>              Document doc2 = new Document();
    >>              // 在其中添加些数据，3-5 域 字段值
    >>              
    >>              // id 表示当前域的名称，相当 于字段名，100 表示值
    >>              // Strore.YES  文档对象一但存储到索引文件，占用空间，对于一些没有必要存储在文件中的数据可以调用 Store.NO
    >>              // 在创建索引文件时，该 字段的值，不会保存在文档中，即使搜到了文档对象，也不能获取。
    >>              // 域的类型， String --  varchar(数据库)   -- StringField/TextField
    >>              doc1.add(new StringField("id", "100", Store.NO));
    >>              doc1.add(new TextField("title", " 三星 Galaxy S 轻奢版平板", Store.YES));
    >>              doc1.add(new TextField("sell_point", "白条12期免息", Store.YES));
    >>              doc1.add(new IntPoint("price", 2000));
    >>              doc1.add(new StringField("price", "2000", Store.YES));
    >>              
    >>              doc2.add(new StringField("id", "101", Store.YES));
    >>              doc2.add(new TextField("title", " 苹果 Galaxy S 轻奢版", Store.YES));
    >>              doc2.add(new TextField("sell_point", "不要钱平板", Store.YES));
    >>              
    >>              // 第四步，将文档数据输出到索引 文件
    >>              IndexWriter writer = new IndexWriter(dir, config);
    >>              // 将文档数据添加到输出流中
    >>              writer.addDocument(doc1);
    >>              writer.addDocument(doc2);
    >>              
    >>              writer.commit();
    >>              writer.close();
    >>              dir.close();
    >>          }
    >> </details>
    >>          
    >> <details>
    >> <summary><mark><font color=darkred>更新索引示例</font></mark></summary>
    >> 
    >>          @Test
    >>          public void updateIndex() {
    >>              Path indexPath = Paths.get("indexdir");
    >>              Directory dir;
    >>              // 分词计算器，配置对象
    >>              Analyzer analyzer = new IKAnalyzer6x();
    >>              IndexWriterConfig config = new IndexWriterConfig(analyzer);
    >>              
    >>              try {
    >>                  // 第一步，指向dir
    >>                  dir = FSDirectory.open(indexPath);
    >>                  // 第二步，构造新的对象数据
    >>                  Document doc = new Document();
    >>                  IndexWriter writer = new IndexWriter(dir, config);
    >>                  doc.add(new StringField("id", "2", Store.YES));
    >>                  doc.add(new TextField("title", "达内大平板，我们不是专业的", Store.YES));
    >>                  doc.add(new TextField("sell_point", "好用", Store.YES));
    >>                  // 第三步，根据条件更新覆盖原文档对象
    >>                  
    >>                  // 参数Term:
    >>                  // Term 参数1：域名，根据哪个域名更新
    >>                  // Term 参数2：域数据的分词词项，大概星，搜索带有三星分词词项的第一个document 对象进行覆盖
    >>                  // 参数doc: 数据替代者
    >>                  writer.updateDocument(new Term("title", "苹果"), doc);
    >>                  writer.commit();
    >>                  writer.close();
    >>                  dir.close();
    >>              } catch (Exception e) {
    >>                  e.printStackTrace();
    >>              }
    >>          }
    >>          
    >> </details>
    >> 
    >> <details>
    >> <summary><mark><font color=darkred>删除索引示例</font></mark></summary>
    >> 
    >>          @Test
    >>          public void deleteIndex() {
    >>              Path indexPath = Paths.get("indexdir");
    >>              Directory dir;
    >>              // 分词计算器，配置对象
    >>              Analyzer analyzer = new IKAnalyzer6x();
    >>              IndexWriterConfig config = new IndexWriterConfig(analyzer);
    >>              
    >>              try {
    >>                  dir = FSDirectory.open(indexPath);
    >>                  // writer
    >>                  IndexWriter writer = new IndexWriter(dir, config);
    >>                  // 删 deleteDocument 的方法参数不同
    >>                  // 词项对比删除，必须词项完全匹配，才删除
    >>                  // Query: 查询对象，Term,匹配规则
    >>                  writer.deleteDocuments(new Term("sell_point", "不要钱"));
    >>                  
    >>                  writer.close();
    >>              } catch (Exception e) {
    >>                  e.printStackTrace();
    >>              }
    >>          }
    >> </details>
    >> 
    >> 

---

- ### 索引: 查询

    >> <details>
    >> <summary><mark><font color=darkred>引入包</font></mark></summary>
    >> 
    >>      
    >>      import java.nio.file.Path;
    >>      import java.nio.file.Paths;
    >>      
    >>      import org.apache.lucene.analysis.Analyzer;
    >>      import org.apache.lucene.document.Document;
    >>      import org.apache.lucene.document.IntPoint;
    >>      import org.apache.lucene.index.DirectoryReader;
    >>      import org.apache.lucene.index.IndexReader;
    >>      import org.apache.lucene.index.Term;
    >>      import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
    >>      import org.apache.lucene.search.BooleanClause;
    >>      import org.apache.lucene.search.BooleanClause.Occur;
    >>      import org.apache.lucene.search.BooleanQuery;
    >>      import org.apache.lucene.search.IndexSearcher;
    >>      import org.apache.lucene.search.PhraseQuery;
    >>      import org.apache.lucene.search.PrefixQuery;
    >>      import org.apache.lucene.search.Query;
    >>      import org.apache.lucene.search.ScoreDoc;
    >>      import org.apache.lucene.search.TermQuery;
    >>      import org.apache.lucene.search.TopDocs;
    >>      import org.apache.lucene.store.Directory;
    >>      import org.apache.lucene.store.FSDirectory;
    >>      import org.junit.Test;
    >>      
    >>      import com.jt.lucene.IK.IKAnalyzer6x;
    >>
    >> </details>
    >>      
    >>      
    >> - 多域查询: 同时检查搜索多个field字段的数据是否存在希望找到的结构
    >> <details>
    >> <summary><mark><font color=darkred>多域查询</font></mark></summary>
    >> 
    >>          @Test
    >>          public void searchMulti() throws Exception {
    >>              // 1. 路径
    >>              Path path = Paths.get("indexdir");
    >>              Directory dir = FSDirectory.open(path);
    >>              // 2. 搜索对象创建searcher
    >>              // 对接一个输入流
    >>              IndexReader reader = DirectoryReader.open(dir);
    >>              IndexSearcher searcher = new IndexSearcher(reader);
    >>              // 搜索使用的分词器IK
    >>              Analyzer analyzer = new IKAnalyzer6x();
    >>              // 3. 构建查询条件
    >>              // 指定查询的多个域名称
    >>              String[] fields = { "title", "sell_point" };
    >>              // 利用多域对象fields 和分词器构造查询条件
    >>              // 查询条件解析器，可以收集查询的环境，生成查询条件对象
    >>              MultiFieldQueryParser parser = new MultiFieldQueryParser(fields,
    >>                      analyzer);
    >>              Query query = parser.parse("平板");
    >>              // 这条件可以使用查询，只要两个域有一个包含词项，就会搜索到
    >>              System.out.println(query.toString());
    >>              // 4. 获取数据for 循环遍历；默认情况下，每个doc 返回时
    >>              // 封装到一个topDoc 的对象中，底层包装了一个数组，评分
    >>              // doc; 循环数组，调用api 获取数据
    >>              TopDocs topDoc = searcher.search(query, 10); // 返回所有数据前10 条
    >>              // 封装了获取doc 的所有条件的docs 对象
    >>              ScoreDoc[] docs = topDoc.scoreDocs;
    >>              for (ScoreDoc scoreDoc : docs) {
    >>                  // 获取document 的文件
    >>                  Document document = searcher.doc(scoreDoc.doc);
    >>                  System.out.println("id: " + document.get("id") + ", title: "
    >>                          + document.get("title: ") + ", sell_point: "
    >>                          + document.get("sell_point"));
    >>              }
    >>          }
    >>
    >> </details>
    >>      
    >> - 词项查询: 完全匹配到词项时,返回对应的documents信息
    >> <details>
    >> <summary><mark><font color=darkred>词项查询</font></mark></summary>
    >> 
    >>          @Test
    >>          public void termQuery() throws Exception {
    >>              // 1 路径
    >>              Path path = Paths.get("indexdir");
    >>              Directory dir = FSDirectory.open(path);
    >>              // 2 搜索对象创建searcher
    >>              // 对接一个输入流
    >>              IndexReader reader = DirectoryReader.open(dir);
    >>              IndexSearcher searcher = new IndexSearcher(reader);
    >>              // 搜索使用的分词器IK
    >>              // Analyzer analyzer = new IKAnalyzer6x();
    >>      
    >>              // 词项查询条件
    >>              Term term = new Term("title", "苹果");
    >>              Query query = new TermQuery(term);
    >>      
    >>              System.out.println("query条件:" + query.toString());
    >>              // 4 获取数据for循环遍历;默认情况下,每个doc返回时
    >>              // 封装到一个topDoc的对象中,底层包装了一个数组,评分
    >>              // doc;循环数组,调用api获取数据
    >>              TopDocs topDoc = searcher.search(query, 10);// 返回所有数据前10条
    >>              // 封装了获取doc的所有条件的docs对象
    >>              ScoreDoc[] docs = topDoc.scoreDocs;
    >>              for (int i = 0; i < docs.length; i++) {
    >>                  // 获取document的文件
    >>                  Document document = searcher.doc(docs[i].doc);
    >>                  System.out.println("id:" + document.get("id"));
    >>                  System.out.println("title:" + document.get("title"));
    >>                  System.out.println("sell_point:" + document.get("sell_point"));
    >>              }
    >>          }
    >>      
    >> </details>
    >> 
    >> - 布尔查询: 利用逻辑关系 与,或,非,通过不同的条件将查询结果筛选;
    >> <details>
    >> <summary><mark><font color=darkred>布尔查询</font></mark></summary>
    >> 
    >>          @Test
    >>          public void booleanQuery() throws Exception {
    >>              // 1 路径
    >>              Path path = Paths.get("indexdir");
    >>              Directory dir = FSDirectory.open(path);
    >>              // 2 搜索对象创建searcher
    >>              // 对接一个输入流
    >>              IndexReader reader = DirectoryReader.open(dir);
    >>              IndexSearcher searcher = new IndexSearcher(reader);
    >>              // 搜索使用的分词器IK
    >>              // Analyzer analyzer = new IKAnalyzer6x();
    >>      
    >>              // 词项查询条件
    >>              // 设置多个查询的条件对象
    >>              Query query1 = new TermQuery(new Term("title", "苹果"));
    >>              Query query2 = new TermQuery(new Term("sell_point", "平板"));
    >>              // 指定逻辑关系,title有苹果,sellpiont没平板
    >>              BooleanClause bc1 = new BooleanClause(query1, Occur.MUST);
    >>              BooleanClause bc2 = new BooleanClause(query2, Occur.MUST_NOT);
    >>              BooleanQuery query = new BooleanQuery.Builder().add(bc1).add(bc2)
    >>                      .build();
    >>      
    >>              System.out.println("query条件:" + query.toString());
    >>              // 4 获取数据for循环遍历;默认情况下,每个doc返回时
    >>              // 封装到一个topDoc的对象中,底层包装了一个数组,评分
    >>              // doc;循环数组,调用api获取数据
    >>              TopDocs topDoc = searcher.search(query, 10);// 返回所有数据前10条
    >>              // 封装了获取doc的所有条件的docs对象
    >>              ScoreDoc[] docs = topDoc.scoreDocs;
    >>              for (int i = 0; i < docs.length; i++) {
    >>                  // 获取document的文件
    >>                  Document document = searcher.doc(docs[i].doc);
    >>                  System.out.println("id:" + document.get("id"));
    >>                  System.out.println("title:" + document.get("title"));
    >>                  System.out.println("sell_point:" + document.get("sell_point"));
    >>      
    >>              }
    >>          }
    >>      
    >> </details>
    >> 
    >> - 范围查询，必须对应具有intPoint, longPoint 的文档数据
    >> - 根据int long double float数值,进行范围筛选
    >> <details>
    >> <summary><mark><font color=darkred>范围查询</font></mark></summary>
    >> 
    >>          @Test
    >>          public void rangeSearch() throws Exception {
    >>              // 1 路径
    >>              Path path = Paths.get("indexdir");
    >>              Directory dir = FSDirectory.open(path);
    >>              // 2 搜索对象创建searcher
    >>              // 对接一个输入流
    >>              IndexReader reader = DirectoryReader.open(dir);
    >>              IndexSearcher searcher = new IndexSearcher(reader);
    >>              // 搜索使用的分词器IK
    >>              // Analyzer analyzer = new IKAnalyzer6x();
    >>      
    >>              // 词项查询条件
    >>              Query query = IntPoint.newRangeQuery("price", 0, 2550);
    >>      
    >>              System.out.println("query条件:" + query.toString());
    >>              // 4 获取数据for循环遍历;默认情况下,每个doc返回时
    >>              // 封装到一个topDoc的对象中,底层包装了一个数组,评分
    >>              // doc;循环数组,调用api获取数据
    >>              TopDocs topDoc = searcher.search(query, 10);// 返回所有数据前10条
    >>              // 封装了获取doc的所有条件的docs对象
    >>              ScoreDoc[] docs = topDoc.scoreDocs;
    >>              for (int i = 0; i < docs.length; i++) {
    >>                  // 获取document的文件
    >>                  Document document = searcher.doc(docs[i].doc);
    >>                  System.out.println("id:" + document.get("id"));
    >>                  System.out.println("title:" + document.get("title"));
    >>                  System.out.println("sell_point:" + document.get("sell_point"));
    >>                  System.out.println("price:" + document.get("price"));
    >>      
    >>              }
    >>          }
    >>      
    >> </details>
    >> 
    >> - 前缀查询: 查询包含查询条件词语为前缀的所有结果;
    >> - 例如: 三星,返回结果 三星galaxy,三星node,三星***
    >> <details>
    >> <summary><mark><font color=darkred>前缀查询</font></mark></summary>
    >> 
    >>          @Test
    >>          public void prefixSearch() throws Exception {
    >>              // 1 路径
    >>              Path path = Paths.get("indexdir");
    >>              Directory dir = FSDirectory.open(path);
    >>              // 2 搜索对象创建searcher
    >>              // 对接一个输入流
    >>              IndexReader reader = DirectoryReader.open(dir);
    >>              IndexSearcher searcher = new IndexSearcher(reader);
    >>              // 搜索使用的分词器IK
    >>              // Analyzer analyzer = new IKAnalyzer6x();
    >>      
    >>              // 词项查询条件
    >>              Term term = new Term("title", "三星");
    >>              Query query = new PrefixQuery(term);
    >>              System.out.println("query条件:" + query.toString());
    >>              // 4 获取数据for循环遍历;默认情况下,每个doc返回时
    >>              // 封装到一个topDoc的对象中,底层包装了一个数组,评分
    >>              // doc;循环数组,调用api获取数据
    >>              TopDocs topDoc = searcher.search(query, 10);// 返回所有数据前10条
    >>              // 封装了获取doc的所有条件的docs对象
    >>              ScoreDoc[] docs = topDoc.scoreDocs;
    >>              for (int i = 0; i < docs.length; i++) {
    >>                  // 获取document的文件
    >>                  Document document = searcher.doc(docs[i].doc);
    >>                  System.out.println("id:" + document.get("id"));
    >>                  System.out.println("title:" + document.get("title"));
    >>                  System.out.println("sell_point:" + document.get("sell_point"));
    >>                  System.out.println("price:" + document.get("price"));
    >>      
    >>              }
    >>      
    >>          }
    >>      
    >> </details>
    >> 
    >> - 多关键字查询: 利用查询条件中的多个关键字进行查询
    >> <details>
    >> <summary><mark><font color=darkred>多关键字查询</font></mark></summary>
    >> 
    >>          @Test
    >>          public void mutiTermQuery() throws Exception {
    >>              // 1 路径
    >>              Path path = Paths.get("indexdir");
    >>              Directory dir = FSDirectory.open(path);
    >>              // 2 搜索对象创建searcher
    >>              // 对接一个输入流
    >>              IndexReader reader = DirectoryReader.open(dir);
    >>              IndexSearcher searcher = new IndexSearcher(reader);
    >>              // 搜索使用的分词器IK
    >>              // Analyzer analyzer = new IKAnalyzer6x();
    >>      
    >>              // 多关键字查询
    >>              PhraseQuery.Builder build = new PhraseQuery.Builder();
    >>              // 添加多个关键字
    >>              build.add(new Term("title", "苹果"));
    >>              build.add(new Term("title", "平板"));
    >>              PhraseQuery query = build.build();
    >>      
    >>              System.out.println("query条件:" + query.toString());
    >>              // 4 获取数据for循环遍历;默认情况下,每个doc返回时
    >>              // 封装到一个topDoc的对象中,底层包装了一个数组,评分
    >>              // doc;循环数组,调用api获取数据
    >>              TopDocs topDoc = searcher.search(query, 10);// 返回所有数据前10条
    >>              // 封装了获取doc的所有条件的docs对象
    >>              ScoreDoc[] docs = topDoc.scoreDocs;
    >>              for (int i = 0; i < docs.length; i++) {
    >>                  // 获取document的文件
    >>                  Document document = searcher.doc(docs[i].doc);
    >>                  System.out.println("id:" + document.get("id"));
    >>                  System.out.println("title:" + document.get("title"));
    >>                  System.out.println("sell_point:" + document.get("sell_point"));
    >>                  System.out.println("price:" + document.get("price"));
    >>      
    >>              }
    >>          }
    >>      
    >> </details>
    >> 
    >> - 模糊查询: 例如 tramp 是查询时拼错的单词, trump特朗普
    >> - 通配符: ?  *
    >> - ...
