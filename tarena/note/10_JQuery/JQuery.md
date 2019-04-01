## 一、jQuery 简介
1. 什么是jQuery?
    > jQuery: JavaScript Query(JS查询), 是一个轻量的，免费开源的JS 函数库，能够极大的简化JS 代码。<br>
    > 写法: 只有"jQuery" 是正确的，"JQuery" "JQUERY" 等都是错误的

        -- 获取name 值为username 的元素
        document.getElementsByName("username")[0];
        $("*[name='username']");

        -- 获取id 为d1 的元素
        document.getElementById("d1");
        $("#d1");

2. jQuery 的优势
    1. 可以极大的简化JS 代码
    2. 可以向CSS 那样来获取页面元素
        > 需求：为所有的div 设置背景颜色
        
            CSS: div { background-color: red; }
            jQuery: $("div").css("background-color", "red");

            CSS: #d1 { background-color: red; }
            jQuery: $("#d1").css("background-color", "red");

    3. 可以操作CSS 属性来控制页面的效果
    4. 可以兼容常用的浏览器
        > 在JS 中，少数内容在不同的浏览器中不兼容. innerText、removeNode()、replaceNode() 在火狐中无法使用。

3. jQuery 的版本支持
    > 缺点：升级可能会导致老的方法在新版本中无法使用，没有很好的兼容老版本
    >> 1.x - 支持常用浏览器，以及IE6+ 以上的版本
    >> 2.x - 支持常用浏览器，以及IE9+ 以上的版本
    >> 3.x - 支持常用浏览器，以及IE9+ 以上的版本

4. jQuery 引入
    > jQuery 函数本身是一个js 文件，所以引入jQuery 跟引入普通js 文件一样
    >> `<script src="js/jquery-1.4.2.js"></script>`

## 二、jQuery 语法
1. '$' 符号介绍
    > $ 符号等价于 jQuery，参见 jQuery 的源码. 1.4.2 的最后三行.<br>
    > 调用$() 等价于调用jQuery(), 该函数会返回一个jQuery 对象，该对象包含了若干个 HTML 元素，可以调用jQuery 中提供的方法或者属性事操作这些元素。<br>
    > `$("div").css("background-color", "red");`    -- 为所有的div 设置背景颜色为red<br>

2. 文档就绪事件
    > window.onload 事件: 在整个HTML 文档加载完成之后立即触发<br>
    > 在jQuery 中提供了一个文档就绪事件函数
    >> `$(document).ready(function(){ 函数体(在整个HTML 文档加载完成之后立即触发执行)... });`

    > 简写: 
    >> `$(function() { 函数体... })`

3. jQuery 对象和JS 对象的相互转换
    1. 为什么需要相互转换
        > jQuery 是js 的框架，在使用时通过$() 可以返回一个jQuery 对象，可以通过jQuery 提供的属性或者方法来操作其中的内容。但是，不可以通过jQuery 对象来调用属性或者方法。反之，也不可以通过JS 对象来调用 jQuery 的属性或者方法。

    2. js 对象转成jQuery 对象

            var oInp = document.getElementById("username");
            var $Inp = $(oInp); // jQuery 的对象是以$ 开头的. 此时 $Inp 就是一个jQuery 对象了。
            $Inp.val(); // 调用jQuery 对象的函数

    3. jQuery 转成js 对象

            var oInp = $Inp.get(0); // 或者使用 $Inp[0];
            oInp.value; // 使用JS 对象调用 JS 属性

## 三、jQuery 选择器(重点)

1. 基本选择器

    [示例代码](code/selector/01_selector_base.html)
    > `$("div");` <br>
    > `$(".c1");` <br>
    > `$("#d1");` <br>
    > `$("div,.c1,#d1");` <br>
2. 层级选择器

    [示例代码](selector/02_jQuery_selector_level.html)
    
    > `$("#two~div")`   // 匹配id 为two 元素后面的兄弟div 与nextAll() 等价

    > `siblings();` // 同辈元素，或同辈指定div 元素
    >> e.g.   $("#two").siblings("div").css("background-color", "#FF6347");
    
    > `next();`
    >> e.g.   `$("#d1").next("span")` - 匹配id 为d1 元素后面紧邻的span 元素
    
    > `nextAll();`
    >> e.g.   `nextAll("div")` 与`$("#two~div");` 等价
    
    > `prev();`

    > `prevAll();`

3. 基本过滤选择器

    [示例代码](selector/03_jQuery_selector_filter.html)
    
    > `$("div:first")` <br>
    > `$("div:last")` <br>
    > `$("div:eq(n)")` <br>
    > `$("div:lt(n)")` <br>
    > `$("div:gt(n)")` <br>
    > `$("div[id^='d1']")` - 匹配具有id 属性并且值以d1 开头的元素 <br>
    > `$("div[id$='d1']")` - 匹配具有id 属性并且值以d1 结尾的元素 <br>

4. 内容选择器

    [示例代码](selector/04_jQuery_selector_content.html)

    > `contains` <br>
    > `empty` <br>
    > `has(div)` <br>
    > `parent` <br>
5. 可见选择器

    [示例代码](selector/05_jQuery_selector_visible.html)

    > `$("div:hidden")` <br>
    > `$("div:visible")` <br>
    > `hide()` <br>
    > `show()` <br>

6. 属性选择器

    [示例代码](selector/06_jQuery_selector_attribute.html)

    > `$("div[id^='d1']")` - 匹配具有id 属性并且值以d1 开头的元素
    > `$("div[id$='d1']")` - 匹配具有id 属性并且值以d1 结尾的元素
7. 子元素选择器
    
    [示例代码](selector/06_jQuery_selector_attribute.html)

    > `$("div span:first-child")` // DIV 第一个span 后代元素 <br>
    > `$("div>span:first-child")`   // 子元素 <br>
    > `$("div>span:last-child")` <br>
    > `$("div>span:nth-child(n)")`  // 第n 个span子元素 <br>
8. 表单选择器

    [示例代码](selector/07_jQuery_selector_form.html)

    > `$(":input")` -- 匹配所有的表单项元素(包括input,select,textarea 等) <br>
    > `$(":text")` -- 匹配所有的单行文本框元素 <br>
    > `$(":password")` -- 匹配所有的密码框 <br>
    > `$(":redio")`     -- 匹配所有的单选框 <br>
    > `$(":checkbox")` -- 匹配所有的复选框 <br>
    > `$(":checked")`   -- 匹配所有的被选中的单选框、复选框、option(IE 里面是不包含 option的) <br>
    > `$(":selected")` <br>



## 四、文档操作(练习)
1. HTML 元素的增删改查
    1. 创建新元素

            $("<div></div>") - 创建一个div 元素
            $("<div>hello...</div>")
            $("<div><input type='text' /></div>") 包含子元素的div 元素
    2. 添加子元素

            $("div").append($oSpan);
            $("div").append("<span></span>");
    3. 删除元素本身

            $("div").remove(); -- 删除匹配的所有div 元素
    4. 替换元素
        
            $("div").replaceWith($span);    // 用$span 来替换所有匹配的div

2. HTML 属性/值操作
    > `html() text() attr()`
    1. `html()` -- 获取或者设置元素的html 内容
        > `$("div").html();`     -- 只能获取到第一个，但是设置的话，则会将所有匹配到的都更改 <br>
        > `$("div").html("<span>xxx</span>");` -- 将所有匹配到的div 元素的HTML 内容更新。 <br>
    2. `text()` -- 获取或者设置元素的文本内容
    3. `attr()` -- 获取或者设置元素的属性

3. 操作css 样式
    1. css() 函数

            $("div").css("background-color") - 获取所匹配的div 元素中的第一个div 元素的背景颜色
            $("div").css("background-color", "red") - 设置背景色

            hide()
            show()
            toggle() -- 切换元素的显示状态，如果显示则切换为隐藏，如果是隐藏则切换为显示

## 五、事件
1. click
2. blur 失去焦点事件
3. focus 输入框获得输入焦点事件，在输入框获得输入焦点时触发
4. change 下拉选框选项切换事件发生时调用
5. ready 文档就绪事件


















































