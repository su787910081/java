

## table

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


