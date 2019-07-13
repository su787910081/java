


## button 的使用
- 事件处理
    > 添加事件处理

        <button onClick='doDeleteRow(this)'>删除</button>
    > 事件处理函数(jquery)

        function doDeleteRow(btn) {
            $(btn).parents("tr").remove();
	    }