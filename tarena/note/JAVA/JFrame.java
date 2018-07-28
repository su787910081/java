// 画窗口

	JFrame frame = new JFrame("飞机大战");
	World world = new World();
	
	frame.add(world);	// 将某个对象添加到窗口中
	
	// 设定窗口默认关闭行为 - 如果不设置这个，那么关闭窗口后，程序不会结束。
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// 设置窗口大小
	frame.setSize(400, 700);
	frame.setLocationRelativeTo(null);
	// 让窗体显示，默认JFrame 创建出来 是不显示出来的，需要显示调用。
	frame.setVisible(true);
		