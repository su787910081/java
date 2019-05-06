package lucene.analyzer.test;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.Test;

import com.jt.lucene.IK.IKAnalyzer6x;

public class AnalyzerTest {

    // 根据词条化的理解，原文件中的内容
    // 可以被词条化，这种计算在lucene, 开方 了一个接口
    // analyzer, 只要实现这个接口，就可以将字符串进行流的计算
    // 不同语言有各种各样analyzer -- 分词器
    // lucene 自带的分词器 standardAnalyzer, whitespaceanalyzer, simpleanalyzer
    // 第三方的智能中文分词器，IK 分词器

    // 通过调用该方法，传入一个分词器, 传入一个分词器
    // 调用 analyzer 的代码，完成对msg 分词计算后的词项输出
    public static void printAnalyzerString(Analyzer analyzer, String msg)
            throws IOException {
        // 将字符串读到流对象当中
        StringReader reader = new StringReader(msg);
        // analyzer 的实现主要是靠一个叫做tokenStream 的流实现的
        // 作用就是把流对象转化成根据底层分词计算得到的数据获取
        // 原有的流对象可以被其计算分成流的集合
        TokenStream token = analyzer.tokenStream("msg", reader);
        // 重置初始化信息，此项数据结构携带(词项，位移，偏移量，频率)，重置可以是一些多余的信息从初始化开始计算，被携带
        token.reset();
        // 从token 的流中获取我们真正想要的分词结果
        CharTermAttribute attribute = token
                .getAttribute(CharTermAttribute.class);

        while (token.incrementToken()) {
            System.out.println(attribute.toString());
        }
    }
    
    @Test
    public void testAnalyzer() throws IOException {
        String msg = "许久不见，多多挂念，明日三更，松树林见";
        Analyzer a1 = new SmartChineseAnalyzer();
        Analyzer a2 = new SimpleAnalyzer();
        Analyzer a3 = new StandardAnalyzer();
        
        System.out.println("****** 我是智能中文分词器 ******");
        AnalyzerTest.printAnalyzerString(a1, msg);
        System.out.println("****** 我是简单分词器 ******");
        AnalyzerTest.printAnalyzerString(a2, msg);
        System.out.println("****** 我是标准分词器 ******");
        AnalyzerTest.printAnalyzerString(a3, msg);
    }
    
    @Test
    public void ikTest() throws IOException {
        String msg = "许久不见，多多挂念，明日三更，松树林见";
        // IK 分词器的版本是和lucene 严格匹配的  6.0.0  <-->  6.0.0
        Analyzer a1 = new IKAnalyzer6x();
        
        AnalyzerTest.printAnalyzerString(a1, msg);
    }

    // 实现分词的扩展与停用
    // 许久不见计算成为单独的词项
    // 松树 停的词项
}
