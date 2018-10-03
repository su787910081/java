IO流
	输入流(InputStream) - 读 、输出流(OutputStream) - 写
	字节流、字符流
	节点流、处理流

	抽象基类：
		InputStream
		OutputStream

	文件流：
		0、注意点: 
			1、使用结束，关闭流. close();
			2、FileOutputStream 没有文件指针，无法进行位置偏移

		输出流
			0、注意点: 
				3、若不是追加方式，则文件流中原有数据将会被截断(删除)

			构造：
				若文件不存在则创建文件
				FileOutputStream(File file);
				FileOutputStream(File file, boolean append);
				FileOutputStream(String name);
				FileOutputStream(String name, boolean append);

		输入流
			0、注意点：
				3、若不存在，则失败
			构造：
				FileInputStream(File file);
				...

	字节缓冲流
		缓冲输出流: BOS: BufferedOutputStream 

			构造
			BufferedOutputStream(OutputStream out);
				示例: 
					FileOutputStream fos = new FileOutputStream("bos.txt");
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					bos.write('a');
					bos.close();
					fos.close();

		缓冲输入流：BufferedInputStream
			构造
			BufferedInputStream(InputStream in);
				示例: 
					FileInputStream fis = new FileInputStream("fis.txt");
					BufferedInputStream bis = new BufferedInputStream(fis);
					...
					bis.close();
					fis.close();

	对象的序列化
		Serializable 接口: (没有任何内容) 仅仅标志该对象是可序列化的
			如果一个对象想实现序列化，那么这个对象的类一定要实现此接口，不需要重写任何的抽象方法。这个接口比较特殊，内部没有任何抽象方法。它仅仅标志该对象是可序列化的。

		将对象序列化后写到文件中的内容为：对象的类型，类的项目信息，包信息，类信息，成员数据，其他信息。
		class Person implements Serializable
		{
			private int id;
			private String name;
			public Person(int id, String name) {
				super();
				this.id = id;
				this.name = name;
			}
		}


	对象流
		读写对象的流: 
			ObjectOutputStream 	- 写
			ObjectInputStream	- 读

		构造: 
			ObjectOutputStream(OutputStream out);
			e.g. 
			
			写对象：
				// 要调用此方法，参数对象，必须 implements Serializable接口，但不用重写任何抽象方法
				protected  void writeObjectOverride(Object obj); // 子类用于重写默认 writeObject 方法的方法。


				public static void demo01() throws IOException
				{
					Person person = new Person(1, "aa");
					
					FileOutputStream fos = new FileOutputStream("person.txt");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					
					oos.writeObject(person);
					
					oos.close();
					fos.close();
				}

				// 必须 implements Serializable 但不用重写任何抽象方法
				class Person implements Serializable
				{
					private int id;
					private String name;
					public Person(int id, String name) {
						super();
						this.id = id;
						this.name = name;
					}
				}

		构造: 
			ObjectInputStream(InputStream in);
				protected  Object readObjectOverride() 

				public static void demo02() throws IOException, ClassNotFoundException {
					FileInputStream fis = new FileInputStream("person.txt");
					ObjectInputStream ois = new ObjectInputStream(fis);
					
					Person person = (Person)ois.readObject();
					System.out.println(person);
					
					ois.close();
					fis.close();
				}




























































