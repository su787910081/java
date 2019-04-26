



- 页面中将js(第一步需要将treegrid插件库放到项目对应目录中)

- 示例: 
    > TreeGrid 插件，添加一个表格
    >>      <div class="box-body table-responsive no-padding">
    >>      <table id="menuTable" class="table table-hover">
    >>          <thead>
    >>          <tr>
    >>              <th data-field="selectItem" data-checkbox="true"></th>
    >>          </tr>
    >>          </thead>
    >>      </table>
    >>      </div>
    > 初始化表格中的列
    >> `selectItem` 必须与 `data-field="selectItem"`  对应的值相同<br>
    >> 
    >>>      <script type="text/javascript">
    >>>          var colunms = [
    >>>          {
    >>>          	field : 'selectItem',
    >>>          	radio : true
    >>>          },
    >>>          {
    >>>          	title : '菜单ID',
    >>>          	field : 'id',
    >>>          	visible : false,
    >>>          	align : 'center',
    >>>          	valign : 'middle',
    >>>          	width : '80px'
    >>>          },
    >>>          {
    >>>          	title : '菜单名称',
    >>>          	field : 'name',
    >>>          	align : 'center',
    >>>          	valign : 'middle',
    >>>          	sortable : true,
    >>>          	width : '180px'
    >>>          },
    >>>          {
    >>>          	title : '上级菜单',
    >>>          	field : 'parentName',
    >>>          	align : 'center',
    >>>          	valign : 'middle',
    >>>          	sortable : true,
    >>>          	width : '180px'
    >>>          },
    >>>          {
    >>>          	title : '类型',
    >>>          	field : 'type',
    >>>          	align : 'center',
    >>>          	valign : 'middle',
    >>>          	sortable : true,
    >>>          	width : '100px',
    >>>          	formatter : function(item, index) {
    >>>          		if (item.type == 1) {
    >>>          			return '<span class="label label-success">菜单</span>';
    >>>          		}
    >>>          		if (item.type == 2) {
    >>>          			return '<span class="label label-warning">按钮</span>';
    >>>          		}
    >>>          	}
    >>>          }, 
    >>>          {
    >>>          	title : '排序号',
    >>>          	field : 'sort',
    >>>          	align : 'center',
    >>>          	valign : 'middle',
    >>>          	sortable : true,
    >>>          	width : '100px'
    >>>          }, 
    >>>          {
    >>>          	title : '菜单URL',
    >>>          	field : 'url',
    >>>          	align : 'center',
    >>>          	valign : 'middle',
    >>>          	sortable : true,
    >>>          	width : '160px'
    >>>          }, 
    >>>          {
    >>>          	title : '授权标识',
    >>>          	field : 'permission',
    >>>          	align : 'center',
    >>>          	valign : 'middle',
    >>>          	sortable : true
    >>>          } ];
    >>>          
    >>>          $(function(){
    >>>          	  doGetObjects();
    >>>          });
    >> 
    >>>          function doGetObjects() {
    >>>          	var tableId="menuTable";
    >>>          	var table = new TreeTable(tableId,"menu/doFindObjects.do", colunms);
    >>>          	table.setExpandColumn(2);
    >>>          	table.init();//底层会发起异步请求加载数据,然后初始化表格
    >>>          }
    >>>      </script>

















