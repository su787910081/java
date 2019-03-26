package factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

// 需求：构建ApplicationContext 对象时，读取指定文件(xml)，根据配置创建对象，
// 并将对象信息存储到map, 需要对象时，从map 获取
public class ApplicationContext {

	private static Map<String, Object> beanMap = new HashMap<String, Object>();
	
	public ApplicationContext(String file) {
		init(file);
	}

	private void init(String file) {
		// 
		InputStream in = getClass().getClassLoader().getResourceAsStream(file);
		SAXReader sr = new SAXReader();
		
		Document doc = null;
		try {
			doc = sr.read(in);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		Element root = doc.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> le = root.elements("bean");
		for (Element e : le) {
			String strId = e.attributeValue("id");
			String strClass = e.attributeValue("class");
			Object obj = null;
			try {
				obj = Class.forName(strClass).newInstance();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			beanMap.put(strId, obj);
		}
		
		Set<Entry<String, Object>> s = beanMap.entrySet();
		for (Entry<String, Object> e : s) {
			System.out.println(e.getKey() + " - " + e.getValue());
		}
	}
	
	public Object getBean(String key) {
		return beanMap.get(key);
	}
	
	public <T> T getBean(String key, Class<T> cls) {
		return (T)beanMap.get(key);
	}
	
	
	public void close() {
		beanMap.clear();
		beanMap = null;
	}
}
