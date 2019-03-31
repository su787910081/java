package cn.tedu.project.util;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("idg")
@Scope("prototype")
@Lazy(value=false)
public class IdGenerator {
	private Map<String, String> map = new HashMap<String, String>();

	public IdGenerator() {
		System.out.println("IdGenerator.IdGenerator()");
	}
	
	@PostConstruct
	public void init() {
		System.out.println("IdGenerator.init()");
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("IdGenerator.destroy()");
	}
	
	public void newId() {
//		map.put("", "");
//		m = Collections.synchronizedMap(m);
		
	}
	
	public synchronized String getId(String key) {
		return null;
	}
}


