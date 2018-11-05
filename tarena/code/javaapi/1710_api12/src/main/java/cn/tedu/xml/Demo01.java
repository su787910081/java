package cn.tedu.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Demo01 {
    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        /**
         * 利用Dom4j API 读取XML 文件
         */
        // books.xml 位置在项目文件夹中
        String file = "books.xml";
        FileInputStream in = new FileInputStream(file);
        
        SAXReader reader = new SAXReader();
        // read 方法从流中读取byte, 并且解析为
        // Document 对象(称为文件对象dom)
        Document doc = reader.read(in);
        // 检查文档对象doc 的内容
//        System.out.println(doc.asXML());
        
        // 找到唯一的根元素books
        Element root = doc.getRootElement();
//        System.out.println(root.asXML());
        
        // 找到全部的books 子元素
        List<Element> list = root.elements("book");  // 返回全部book
//        root.elements(); // 返回所有book + owner
        for (Element e : list) {
//            System.out.println(e.asXML());
            Element ele = e.element("name");
            String test = ele.getText();
            System.out.println(test);
            
            // 读取book 元素的属性
            String id = e.attributeValue("id");
            System.out.println(id);
        }
    }
}
