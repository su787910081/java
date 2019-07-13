


# 用于处理非同源调用
- ### 同源策略
    - > 访问的域名和端口相同则为同源
    - > 相同的IP及端口也为同源
    - > 域名与IP 相映射的访问被认为是非同源
    - > 使用ajax 访问的时候，不能违反同源策略
        > - 解决这个问题引出了jsonp 技术
        >> - jsonp 技术实际就是在使用ajax 的时候，访问非同源WEB 。
        >> - jsonp 技术只要满足，从WEB 服务端返回的字符串中，将json字符串添加一个方法将其包起来。然后在ajax 中使用同名方法调用。
        >>>     public static final ObjectMapper mapper=new ObjectMapper();
        >>>     @Autowired
        >>>     private ItemCatMapper itemCatMapper;
        >>>     @RequestMapping("web/itemcat/all")
        >>>     @ResponseBody
        >>>     
        >>>     // 在传递的参数中,有一个callback参数,值,就是方法名称
        >>>     //根据js同源策略解析成jsonp格式
        >>>     public String getAllItemCat(String callback) throws Exception{
        >>>         //获取所有分类的list
        >>>         List<ItemCat> itemCatList=itemCatMapper.selectAll();
        >>>         ItemCatResult itemCatResult = ItemCatUtil.allItemCat(itemCatList);
        >>>         String jsonData = mapper.writeValueAsString(itemCatResult);
        >>>         String jsonpData=callback+"("+jsonData+")";
        >>>         return jsonpData;
        >>>     }





