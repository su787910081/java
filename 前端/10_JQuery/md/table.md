

## table 创建

- 创建table 

    1. 使用HTML 创建
        > 我们可以在表的单元格中添加一个复选框

            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>name</th>
                        <th>note</th>
                        <th>createdTime</th>
                        <th>modifiedTime</th>
                        <th>operator</th>
                    </tr>
                </thead>

                <tbody id="tBodyId">
                    <tr>
                        <td><input type='checkbox' name='checkedId' value='1'>1</input></td>"
                        <td>name</td>
                        <td>note</td>
                        <td>createdTime</td>
                        <td>modifiedTime</td>
                        <td>operator</td>
                    </tr>
                </tbody>

            </table>

    2. 使用jquery 创建
        > 我们还可以在创建的时候给`tr` 一个值`tr.data("id", data[i].id);` 然后在方法中取其值

            var tBody = $("<table class="table table-hover"></table>");
            for ( var i in data) {
                var tr = $("<tr></tr>");
                tr.data("id", data[i].id);
                tr.append("<td>" + "<input type='checkbox' name='checkedId' value='" + data[i].id + "'/>" + "</td>");
                tr.append("<td>" + data[i].name + "</td>");
                tr.append("<td>" + data[i].note + "</td>");
                tr.append("<td>" + data[i].createdTime + "</td>");
                tr.append("<td>" + data[i].modifiedTime + "</td>");
                tr.append("<td><button type='button' class='btn btn-primary btn-delete-row' onClick='doDeleteRow(this)'>删除</button></td>");

                tBody.append(tr);
            }

        >  实现删除函数`doDeleteRow(this)` 我们在方法中可以取到`tr` 中添加的`id`值

            function doDeleteRow(btn) {
                var tr = $(btn).parents("tr");
                console.log(tr.data("id"));
                tr.remove();
            }
        
        > 我们还可以在页面加载完成之后直接使用下面的方法添加一个事件处理函数
        >> 在'`$("#tBodyId")` 下面找到一个`$(".btn-delete-row")`元素，然后添加`click` 事件，事件处理函数为`doDelete`

            $(function(){
                $("#tBodyId").on("click", ".btn-delete-row", doDelete);
            });
        >> 实现`doDelete`

            function doDelete() {
                var tr = $(btn).parents("tr");
                console.log(tr.data("id"));
                tr.remove();
            }


## table 的使用

- 假如有这如下的一个table 代码

        <table class="table table-hover">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>name</th>
                    <th>note</th>
                    <th>createdTime</th>
                    <th>modifiedTime</th>
                    <th>operator</th>
                </tr>
            </thead>

            <tbody id="tBodyId">
                <tr>
                    <td><input type='checkbox' name='checkedId' value='1'>1</input></td>
                    <td>suyh</td>
                    <td>suyunhong</td>
                    <td>2019</td>
                    <td>2019</td>
                    <td>
                        <button type='button' class='btn-delete-row'>删除</button>
                        <button type='button' class='btn-update-row'>修改</button>
                    </td>
                </tr>
                <tr>
                    <td><input type='checkbox' name='checkedId' value='2'>2</input></td>
                    <td>suyh-2</td>
                    <td>suyunhong-2</td>
                    <td>2019</td>
                    <td>2019</td>
                    <td>
                        <button type='button' class='btn-delete-row'>删除</button>
                        <button type='button' class='btn-update-row'>修改</button>
                    </td>
                </tr>
                <tr>
                    <td><input type='checkbox' name='checkedId' value='3'>3</input></td>
                    <td>suyh-3</td>
                    <td>suyunhong-3</td>
                    <td>2019</td>
                    <td>2019</td>
                    <td>
                        <button type='button' class='btn-delete-row'>删除</button>
                        <button type='button' class='btn-update-row'>修改</button>
                    </td>
                </tr>
            </tbody>

        </table>

    > 注册事件处理函数，在页面加载完成后

    >> 点击事件处理函数
    >>> 在`id=tBodyId` 节点下面查找包含`class=btn-delete-row` 的元素注册点击事件(`"click"`)并添加事件处理函数`doDeleteRow`。<br>
    >>> 以及包含`class=btn-update-row` 的元素注册点击事件(`"click"`)并添加事件处理函数`doUpdateRow`。

        $(function() {
            $("#tBodyId")
                .on("click", ".btn-delete-row", doDeleteRow)
                .on("click", ".btn-update-row", doUpdateRow);
        });

    > 点击删除按钮时获取所在行的id值: 
    
        function doDeleteRow() {
            var tr = $(this).parents("tr");
            var inputId = tr.find("input[name='checkedId']");
            var id=$(inputId).val();
            console.log(id);
        }



