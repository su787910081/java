HTML: 超文本标记语言，用开开发网页的一门语言 W3C 组织
HTML 是一门标记语言，不是一门编程语言
HTML 是利用标记(标签/元素 如<a></a>)来组织网页
HTML 是文件的一种

HTML 结构
	1. 案例：编写我的第一个HTML 网页
		<!doctype html>
		<html>
			<head>
				<title>My First Page</title>
				<meta charset="utf-8" />
			</head>
			<body>
				hello html...<br/>
				哈喽...中国
			</body>
		</html>

	2. 网页的结构
		(1). <!doctype html> 文档声明，用来声明HTML 文档所遵循的HTML 规范
		(2). <head></head> 头部分，用来存放HTML 文档的基本属性信息，比如网页的标题，文档使用的编码等，这部分信息会被浏览器优先加载。
		(3). <body></body> 体部分，用来存放网页可视化数据。即真正的网页数据
		(4). <title></title>
		(5). <meta charset="utf-8"/> 用来通知浏览器使用哪一个编码来打开HTML 文档，这个编码一定要和文件保存时用的编码保持一致，才不会出现中文编码问题。

HTML 语法
	1. HTML 标签
		HTML 是一门标记语言，标签分为开始标签和结束标签，如：<a></a> 
	2. HTML 属性
		HTML 标签都可以具有属性，属性包括属性名和属性值，如果具有多个属性，中间用空格隔开，属性的值用单引号或者双引号引起来，也可以不用引号
		<a href="" target="" name="" id=""></a>
	3. HTML 注释
		
	4. 如何在网页中做一个空格或者换行
		在HTML中，多个连接的空白字符会被当成一个空格来显示
		所以，如果要用一个换行，可以使用<br/> 标签来代替
		如果要做一个空格，可以使用 '&nbsp;' 来代码
	5. HTML 和XML 的区别
		相同点: 都是标记语言，都是由大量的标记来组织网页结构
		不同点: 
			(1). XML 是可扩展标记语言，其中的标签可以自己定义，而HTML 中的标签是预定义好的，不能私自添加或修改
			(2). XML 对语言的要求非常严格，比如严格区分大小写，标签一定要有开始标签和结束标签或者标签自闭.标签不能交叉嵌套
			(3). HTML 对语法的要求非常不严格，比如，不区分大小写，如果标签出现交叉嵌套或者没有结束标签，也可能被正常解析

HTML 标题标签
	<h1>一级标题</h1>
	<h2>二级标题</h2>
	<h3>三级标题</h3>
	<h4>四级标题</h4>
	<h5>五级标题</h5>
	
		<!-- 水平居中 align="center" left(左) right(右) jusity(两端对齐) -->
		<h1 align="center">马云有没有去过天上人间..., 去过...</h1>
	
		<!-- ul列表 type指定前面的小加点(disc、square、circle) -->
		<ul type="circle">
			<li>中国</li>
			<li>美国</li>
			<li>韩国</li>
			<li>日本</li>
		</ul>
		<!-- li 用来定义列表看的条目 -->
		
		<!-- 图片标签 
			src - 用来指定图片的url 地址 
			width - 指定图片的宽度 
			height - 指定图片的高度 
			border - 指定图片的边框
			-->
		<image src="./image/039.jpg" width="500px" height="500px" border="5px"/>

HTML 超链接标签
	(1). 通过超链接可以实现点击超链接后跳转到另一个文档
		href 用来指定点击超链接的时候跳转到的地址
		<!-- target 指定以什么样的方式打开超链接 _self: 当前窗口 _blank: 新窗口  -->
		<a href="http://www.baidu.com" target="_blank">百度一个，你就知道</a>
	(2). 通过超链接可以实现
		<!-- 标记name 为top 的位置 -->
		<a name="top"></a>
		<!-- 回到name 为top 的位置 -->
		<a href="#top">回到顶部</a><br/>

HTML 表格标签


HTML 表单标签
	向服务器发送数据的方式(有两种)
	(1) 通过超链接向服务器发送数据
		其实就是在超链接的后面通过问号(?)来拼接参数，问号后面是参数，参数可以有多个参数，参数之间用 & 分隔
	(2) 通过表单向服务器发送数据
		在表单中的表单项可以输入信息，通过提交表单可以将表单中输入的数据提交给服务器

	<!-- 颜色值，使用RGB -->
	bgcolor="#D3D3D3"

	如果实现一个表单，需要注意的是: 表单中的表单项都需要指定 name 属性，name 属性的值 就是该项被提交时参数名，该项中输入的值就是参数值


元素的类型: 
	<body>
		<!-- 其他标签 -->
		<!-- DIV 默认和页面一样的宽度，自动收缩 -->
		<div style="border:1px solid red">这是一个DIV</div>
		<div style="border:1px solid red">这是一个DIV</div>
		<!-- span 默认和文本一样的宽度，行内元素 -->
		<span style="border:2px solid blue">这是一个span</span>
		<span style="border:2px solid blue">这是一个span</span>
		<!-- 会有一行空行的样子 -->
		<p style="border:2px solid green">这是一个p</p>
		<p style="border:2px solid green">这是一个p</p>
	</body>
	
	(1). 块级元素
		块级元素默认情况下独占一行. 如: div,p,h1~h6,hr,br
	(2). 行内元素
		默认情况下多个行内元素可以处在同一行. 如: span,a,input




























