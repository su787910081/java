2 JS 基础语法
2.1 JS 注释
	1. //
	2. /* */

2.2 数据类型
	2.2.1 基本数据类型
		2.2.1.1 数值类型(number)
			在JS 中，数值类型底层就只有一种，即浮点型(float). 在需要时，JS 中的数值会自动的在浮点型和整型之间进行转换。
			常见的常量: 
				Infinity: 正无穷大 
				-Infinity: 负无穷大
				NaN(not a number): 非数字，这个值比较特殊，它和任何数值都不相等，包括它自己。这也就意味着，如果要判断一个值是否是非数字，不可以用 x == NaN 进行判断，而是isNaN(x) 函数进行判断。
				
		2.2.1.2 字符串类型(string)
			在JS 中字符串类型是基本数据类型的一种。字符串常量可以用单引号或者又引号包起来。
		2.2.1.3 布尔类型(boolean)
		2.2.1.4 undefined(未定义)
			undefined 的值只有一个，就是undefined. 表示变量未定义(未初始化值).
			比如： var str;	// 此时访问str, str 的值就是undefined.
		2.2.1.5 null(空)
			null 的值也只有一个，就是null. 表示空值。
			常被用来作为函数的返回值，表示该函数返回的是一个空的对象。

	2.2.2 复杂数据类型
		2.2.2.1 复杂数据类型主要指对象. 对象分为普通对象、数组、函数. 即在js 中函数和数组也属于对象

	2.2.3 数据类型的自动转换

2.3 变量的定义和运算符
	2.3.1 变量的定义
		在JS 中是通过var 关键字来定义变量，变量是不区分类型的，所以JS 是一门弱类型语言。
			比如：声明一个字符串: var str = "hello js..";
				声明一个数值: var num = 100;

	2.3.2 运算符
		JS 的运算符和JAVA 中的大致相同

		== 和 === 的区别
			1. 如果要判断的值是同一种类型，两者是没有任何区别的，直接判断两个值是否相等；
			2. 如果要判断的两个值不是同一种类型，“==” 会先将两个值转成同一种类型，再进行比较。
				如："123" == (100+23);	// true
			3. 如果要判断的两个值不是同一种类型，“===” 会直接返回false;

		typeof运算符: 返回变量或者表达式的数据类型
		alert(typeof x);

		delete 运算符: 删除数组中的元素或者对象上的属性或者方法

2.4 语句

	2.4.1 条件语句 if...else...

	2.4.2 switch 语句

	2.4.3 循环语句
		var arr = ['a', 123, true, "helo", false];
		for (var i = 0; i < arr.length; i++) {
			
		}

2.5 数组
	2.5.1 创建数组的方式
		1. 通过Array 构造函数声明数组
			var arr1 = new Array();
			var arr1 = new Array("hello", 100, true);
		2. 通过数组直接量声明数组
			var arr2 = [];

	2.5.2 数组的长度是可以被任意改变的

	2.5.3 数组对象上常用方法
		1. length 属性: 返回数组的长度，也可以通过修改该属性的值来修改数组的长度
		2. push 方法	在数组的末尾添加一个元素，返回新数组的元素
		3. pop 方法		删除数组的末尾元素，并返回该元素。
		4. shift 方法	删除数组的第一个元素，并返回该元素。
		5. unshift 方法	在数组的头部插入元素，并返回新数组的长度

2.6 函数
	2.6.1 通过function 关键字声明函数
		函数声明: 
			function 函数名(参数列表...) {
				函数体
			}
		函数调用: 函数名(参数列表...);

	2.6.2 通过函数直接量声明函数
		函数声明: 
			var fn = function (参数列表) {
				函数体
			}
		函数调用: 通过fn 调用

2.7 对象
	2.7.1 JS 内置对象
		1. RegExp 正则对象
			(1) 声明正则表达式对象
				var reg1 = new RegExp("正则表达式"[, "标识符"]);
				var reg2 = /"正则表达式"/["标识符"];
			(2) 检测是否匹配
				reg2.test("原字符串");
				正则表达式如果需要全部检测需要自己添加开始和结束标志('^'、'$');
			(3) 标识符:
				i 表示ignoreCase 忽略大小写
				g 表示global 全局查找，即：若匹配多于一个，则默认只返回一个结果，但是添加了g 则会返回所有匹配的结果
		2. String 对象
			(1) 声明String 对象	String 对象的类型是object 类型
				var str1 = new String();	// object 类型
				var str2 = "hello";	// string 类型
		3. Array 对象
		4. Date 对象
			var date1 = new Date();	// 当前时间
			var date2 = new Date(1000 * 3600);	// 1970-1-1 加上该毫秒值
			var date3 = new Date(2017,11,28,12,15,00);	// 年，月，日，时，分秒 月份是从0 开始的

			常用方法
				getFullYear(); 获取日期对象中的年份
				getMonth(); 月份 - 从0 开始
				getDate(); 获取日期对象中的哪一天
				getDay(); 获取星期几.
		5. Math 对象
			Math.PI
			Math.ceil();	// 向上取整
			Math.round();	// 四舍五入
			Math.floor();	// 向下取整
			Math.random();	// 返回0~1 之间的伪随机数
		6. Global 对象
			parseInt();	// 将输入的值转为整数
			parseFloat(); // 将输入的值转为浮点数
			isNaN();

	2.7.2 自定义对象
		1. 方式一
			function Person()

		2. 方式二
			var p2 = {
				// 属性之间用逗号分隔
				// 属性变量名可以不加双引号
				"name": "张飞",
				"age": 18,
				"addr": "燕人", 
				"run": function() {
					alert(this.name);
				}
			};

			// 访问属性和方法
			p2.name;
			p2.age;
			p2.addr;
			p2.run();

			方式二中对象的格式为key:value  形式，key 只能是字符串，最好用双引号引起来，value 可以是任意的类型，比如: 数值，字符串，布尔值，函数，数组，对象等。

		3. 直接 {} 就是一个对象
            var p2 = { };

        // 得到一个自定义对象的所有键值
        <script type="text/javascript">
            var dataCountry = {
                "中国": ["北京市", "河北省", "辽宁省", "山东省"],
                "美国": ["纽约", "华盛顿"],
                "日本": ["东京"]
            }
            var dataProvince = {
                "北京市": ["海淀区","朝阳区","丰台区"],
                "河北省": ["石家庄", "唐山","秦皇岛"],
                "辽宁省": ["沈阳", "大连", "鞍山"],
                "山东省": ["青岛", "济南","烟台"]
            }

            window.onload = function() {
                var arrKeysCountry = [];
                for (var ele in dataCountry) {
                    if (dataCountry.hasOwnProperty(ele)) {
                        arrKeysCountry.push(ele);
                    }
                }
                alert("国家：" + arrKeysCountry);
                var arrKeysProvince = [];
                for (var ele in dataProvince) {
                    arrKeysProvince.push(ele);
                }
                alert("省份：" + arrKeysProvince);
            }

        </script>





















js 中的数据类型的转换
	JS 中的数据类型在需要的时候会自动进行类型转换，转换时遵循如下规则：
	数值类型：
		转字符串类型，直接转成对应值的字符串， 3 --> "3"
		转布尔类型，0和naN 转成false, 其他则为true
		在需要时，会自动转成对应的包装对象 100 --> new Number(100)

	字符串：
		空字符串(""): 转数值0，转布尔值为false
		非空纯数值字符串("123"): 转数值为对应的数值，转布尔值 为true
		非空非数值字符串('abc'): 转数值为NaN, 转布尔值为true
		在需要时，会自动转成对应的包装对象，"aaa" --> new String("aaa")

	布尔类型: 
		true: 转数值为 1，转字符串为 "true"
		false: 转数值为 0, 转字符串为 "false"
		在需要时，会自动转成对应的包装对象.

	undefined
		转数值为NaN，转字符串为"undefined", 转布尔值为false, 转对象会抛出异常！

	null
		转数值为0，转字符串为"null", 转布尔值为false, 转对象会抛出异常！











