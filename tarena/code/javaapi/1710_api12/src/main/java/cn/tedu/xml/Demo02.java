package cn.tedu.xml;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class Demo02 {
    public static void main(String[] args) throws IOException {
        // 创建XML DOC 对象，并写到文件中
        Document doc = DocumentHelper.createDocument();

        // 添加根元素
        Element root = doc.addElement("products");
        // addElement 在当前元素上添加子元素
        // "product" 是子元素名，返回子元素对象
        Element p1 = root.addElement("product");
        Element p2 = root.addElement("product");
        // 为元素添加属性
        p1.addAttribute("id", "p1");
        // 为元素添加文本
        p1.addElement("name").addText("擎天柱");

        // 将doc 对象写到文件中
        FileOutputStream out = new FileOutputStream("products.xml");
        // Dom4j 提供 了“漂亮的”格式 化功能 
        OutputFormat fmt = OutputFormat.createPrettyPrint();
        
        XMLWriter writer = new XMLWriter(out, fmt);
        writer.write(doc);

        System.out.println(doc.asXML());
        
        writer.close();
        out.close();
    }
}
