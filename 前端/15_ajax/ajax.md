
## 环境
1. 引入JQuery
    1. webapp 目录下面创建libs 目录，添加jquery-3.2.1.min.js 
    2. 引入JQuery

            <script type="text/javascript" src="/libs/jquery-3.2.1.min.js"></script>
2. 添加button 事件处理，以及添加一个div 块
    > 点击角色管理将去访问 `"listUI.do"`，然后将`listUI.do` 的结果页面添加到`div` 的位置

        <button id="load-id">角色管理</button>
        
        <div id="content"></div>

        <script type="text/javascript">
            $(function(){
                console.log("load page ok.");
                $("#load-id").click(function() {
                    $("#content").load("listUI.do");
                });
            });
        </script>

3. `listUI.do` 的请求将返回一个`list.jsp` 页面

        @RequestMapping("listUI")
        public String listUI() {
            return "list";
        }

4. `list.jsp`

        <%@ page language="java" contentType="text/html; charset=UTF-8"
            pageEncoding="UTF-8"%>

        <table border="1" cellpadding="1" cellspacing="1">
            <thead>
                <tr>
                    <th>id</th>
                    <th>name</th>
                    <th>note</th>
                </tr>
            </thead>

            <tbody id="tabBodyId">
            </tbody>
        </table>

        <script type="text/javascript">
            $(function(){
                // doGetObjects01();
                // doGetObjects02();
                doPostObjects03();
            });

            function doGetObjects01() {
                // jquery 提供的ajax 函数
                $.ajax({
                    url:"doFindPageObjects.do",
                    type:"GET",
                    dataType:"json",
                    data:{"pageCurrent": 1},	// 提交的数据
                    success:function(result) {	// result: 用于接收服务端返回的数据
                        doSetTBody(result);
                    },
                    error:function(result) {
                        console.log(result);
                    }, 
                    cache:false
                });
            }
            
            // 借助jquery 的getJSION 函数发起异步GET请求
            function doGetObjects02() {
                var url = "doFindPageObjects.do";
                var data = {"pageCurrent":1};
                $.getJSON(url, data, function(result){
                    doSetTBody(result);
                });
            }
            
            // 借助jquery 的getJSION 函数发起异步POST请求
            function doPostObjects03() {
                var url = "doFindPageObjects.do";
                var data = {"pageCurrent":1};
                $.post(url, data, function(result){
                    doSetTBody(result);
                });// 表单数据提交 时一般建议使用POST
            }
            
            function doSetTBody(data) {
                // debugger;
                console.log(data);

                var tBody = $("#tabBodyId");

                for (var i in data) {
                    var tr = $("<tr></tr>");
                    tr.append("<td>" + data[i].id + "</td>");
                    tr.append("<td>" + data[i].name + "</td>");
                    tr.append("<td>" + data[i].note + "</td>");
                    tBody.append(tr);
                }
            }
        </script>

## ajax 应用

- `load` 函数应用

    > jquery 中的load函数一般用于在某个位置异步加载某个url页面,load 函数异步加载新的内容，后续内容会替换原有内容

    > 语法: `load(url,[data],[callback])`
    > 1)	`url` 表示远程一个地址
    > 2)	`data` 表示要传递数据(key/value)
    > 3)	`callback` 表示一个回调函数

    > 示例1: 

        $("#time-result-1").load("listUI.do");

    > 示例2: 

        $("body").load("listUI.do",{color:red},function(){
            …..
        });

- `ajax` 函数应用
    > juqery 中的ajax函数用于向服务端发起一个异步的ajax请求.

        $.ajax({ url: "address", 
            type:”GET”,
            data: { param1: "foo bar", param2: "baz“ },
            dataType: "json",
            success: successHandlerFunction, 
            error: errorHandlerFunction, 
            cache: false, 
        })

