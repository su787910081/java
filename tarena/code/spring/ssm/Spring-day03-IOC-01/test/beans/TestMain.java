package beans;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestMain {
	// 给定一个包，找出这个包下面的所有类名
	public static void main(String[] args) {
		
		String strDir = "project.controller".replaceAll("\\.", "/");
		List<String> lsClassName = findClassName(strDir);
		
		System.out.println(lsClassName);
	}

	private static List<String> findClassName(String strDir) {
		List<String> lsClassName = new ArrayList<String>();
		
		TestMain tm = new TestMain();
		URL url = tm.getClass().getClassLoader().getResource(strDir);
		File f = new File(url.getPath());
		
		File[] fs = f.listFiles();
		for (File ff : fs) {
			if (ff.isDirectory()) {
				List<String> ls = findClassName(strDir + "/" + ff.getName());
				if (ls != null && !ls.isEmpty()) {
					// do something
					lsClassName.addAll(ls);
				}
			} else {
				String strFileName = ff.getName();
				if (strFileName.endsWith(".class")) {
					int nEndIndex = strFileName.lastIndexOf('.');
					String strClassName = strFileName.substring(0, nEndIndex);
					lsClassName.add(strClassName);
				}
			}
		}
		
		return lsClassName;
	}
}
