package factory;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;

public class AnnotationAppContext {
	public AnnotationAppContext() {
		String pkg = "project";
		scanPkg(pkg);
	}

	// 扫描指定的包，找到包中的类文件
	private void scanPkg(String pkg) {
		String pkgDir = pkg.replaceAll("\\.", "/");
		URL url = getClass().getClassLoader().getResource(pkgDir);
		File file = new File(url.getFile());
		File[] fs = file.listFiles(new FileFilter() {

			@Override
			public boolean accept(File file) {
				String fileName = file.getName();
				if (file.isDirectory()) {
					scanPkg(pkg + "." + fileName);
					return false;
				}
				
				if (fileName.endsWith(".class")) {
					return true;
					
					
				}

				return false;
			}
		});

		for (File f : fs) {
			String fileName = f.getName();
			fileName = fileName.substring(0, fileName.lastIndexOf('.'));
			String clsName = pkg + "." + fileName;

			try {
				Class<?> c = Class.forName(clsName);
				// 判定这个类上是否有CGB1709 注解
				if (c.isAnnotationPresent(CGB1709.class)) {
					Object obj = c.newInstance();
				}
//				Object obj = Class.forName(clsName).newInstance();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
			
		}
	}

	public static void main(String[] args) {
		AnnotationAppContext ctx = new AnnotationAppContext();
	}
}
