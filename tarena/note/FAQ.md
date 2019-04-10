

1. 如何将前端的数组类型，通过ajax 传入spring MVC 的controller 的方法参数中?
    > 在ajax 处添加指定参数

        var checkedIds = [8, 9];
        var params = {
				"checkedIds": checkedIds
		};
		var url = "role/doDeleteObjectByArray.do";
		$.post(url, params, function(result){
            /// ...
		});
    > 在controller 中需要添加注解`@RequestParam(value = "checkedIds[]", required = false)` 注意有一个`[]`

        @RequestMapping(value = "doDeleteObjectByArray",method = RequestMethod.POST)
        @ResponseBody
        public String doDeleteObjectByArray(@RequestParam(value = "checkedIds[]", required = false)String[] checkedIds) {
            System.out.println("checkedIds: " + Arrays.toString(checkedIds));
            return "ok";
        }













