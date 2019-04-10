

## `Spring MVC ` 如何从URL 中获取对应参数的值

> URL: `http://localhost:8080/CGB-SPRING-DAY10-MVC-01/req/doSaveObject02.do?tid=100&content=A` 

- 通过原生API 获取`id`、`content` 的值
    > `return "request";` 返回一个`jsp` 文件名

        @RequestMapping("doSaveObject")
        public String doSaveObject(HttpServletRequest request) {
            String id = request.getParameter("id");
            String content = request.getParameter("content");
            return "request";
        }

- 直接使用与参数名相对应的方法参数名来获取
	> 直接借助请求参数名(id=100)相同的变量(Integer id)接收请求数据[id].<br>
	> 底层会做哪些事情?<br>
	> 1. 通过反射获取方法对象<br>
	> 2. 通过反射获取参数信息(名字，类型)<br>
	> 3. 获取与请求参数名字对应的方法参数信息<br>
	> 4. 根据对应的参数信息进行类型转换，以及赋值操作<br>

	> 需要注意: <br>
	> 1. 当借助方法参数接收页面数据时，<span style="color:red">参数类型最好为对象类型</span>。<br>
	> 2. 当方法参数名与请求参数名不一致时，使用 `@RequestParam(name="tid")` 来指定接收哪个参数的值<br>
	> 3. 当方法中某个参数必须要求在页面中有对应参数时，使用 `@RequestParam(required=true)` 来指定<br>

        @RequestMapping("doSaveObject02")
        public String doSaveObject02(@RequestParam(name="tid", required=true) Integer id, String content) {
            System.out.println("ttid = " + id);
            System.out.println("ttcontent = " + content);
            return "request";
        }

- 利用`JavaBean` 对象来获取URL 中参数的数据值
    - 首先我们需要定义一个class;<br>
        > 它就是一个普通的类就可以了，不需要将它指定给spring 管理<br>
        > 需要为属性添加`set` 方法<br>
        > 需要有无参构造方法<br>

            public class Message {
                private Integer id;
                private String content;

                public Message() { }
                public void setId(Integer id) { this.id = id; }
                public void setContent(String content) { this.content = content; }      
            }
    - 使用时

            @RequestMapping("doSaveObject03")
            public String doSaveObject03(Message msg) {
                System.out.println(msg);
                return "request";
            }

- 当我们不想要返回一个`html` 标签页面，而是返回`xml`、`json` 等格式数据时，可以使用`@ResponseBody` 注解
    > `@ResponseBody`注解作用：该注解作用于将Controller的方法返回的对象，<br>
	> 通过适当的HttpMessageConverter转换为指定格式后，写入到Response对象的body数据区.<br>

        @RequestMapping("doFindObject")
        @ResponseBody
        public String doFindObject(Integer pageCurrent) {
            return "pageCurrent: " + pageCurrent;
        }
    > 当我们在浏览器中输入`http://localhost:8080/CGB-SPRING-DAY10-MVC-01/req/doDeleteObject/doFindObjects.do?pageCurrent=1`时，将会在浏览器显示一个结果。
        
        pageCurrent: 1

- 关于`@ResponseBody`注解
    > 如果我们不添加这个注解的话，在这个处理类中所有被调用之后。`DispatcherServlet` 都会生成一个页面文件。<br>
    > 然后将这个页面文件交给`ViewResolver`处理，将处理的结果响应给浏览器<br>
    
    > 但是如果我们添加了这个注解 的话，那么在这些方法的处理最后返回的结果，将直接以结果的形式返回给浏览器。<br>
    > 它会认为这些就是你处理完的结果。<br>

<br><br><br>

---

<br><br><br>

## 获取请求头中的数据

> 获取请求头中的数据时，定义的所有方法都可以添加，`Servlet` 原生API 的参数类型，比如: `HttpSession`、`HttpServletRequest`、`HttpServletResponse` 等<br>

- 获取请求头中的某一个数据

        // @RequestHeader 标识请求头中某一个数据
        @RequestMapping("doWithHeader")
        @ResponseBody
        public String doWithHeader(@RequestHeader String Accept) {
            return "obtain header accept = " + Accept;
        }

- 获取请求头中的所有数据

        // 获取到所有Header 中的数据
        @RequestMapping("doWithEntry")
        @ResponseBody
        public String doWithEntry(HttpEntity<String> entity) {
            return "headers = " + entity.getHeaders() + "<br/>body=" + entity.getBody();
        }
        
- 获取请求头中的Cookie 数据

        // 获取Cookie 中的值，需要用到@CookieValue 注解
        // 我们也可以直接让参数名与Cookie 的名字一致，则就不需要用name="JSESSIONID" 来指定
        // [@CookieValue String JSESSIONID]
        @RequestMapping("doWithCookie")
        @ResponseBody
        public String doWithCookie(@CookieValue(name="JSESSIONID") String jsid) {
            return "cookie.JSESSIONID: " + jsid;
        }

<br><br><br>

---

<br><br><br>

## `Spring MVC` 响应数据处理
- 使用`ModelAndView` 参数

        @RequestMapping("doModelAndView")
        public ModelAndView doModelAndView(ModelAndView mv) {
            mv.addObject("msg", "Hello suyh");
            mv.setViewName("response");
            return mv;
        }
        
- 使用`Model` 参数

        @RequestMapping("doModel")
        public String doModel(Model model) {
            System.out.println("model=" + model.getClass());
            model.addAttribute("msg", "hello model");
            return "response";
        }
        
- 还可以直接使用`Map` 参数,然后处理请求重定向的功能

        @RequestMapping("doMap")
        public String doMap(@RequestParam Map<String, Object> map, HttpSession session) {
            map.put("msg", "map value");
            session.setAttribute("user", "suyh");
            //  return "response";	// 请求转发
            return "redirect:doWithUI.do";	// 请求重定向
        }

        @RequestMapping("doWithUI")
        public String withUI() {
            return "response";
        }




