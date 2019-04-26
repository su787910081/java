

## 异常处理

![异常处理控制流程](./img/exception.png)

- 控制器中对异常处理

        // 定义Controller 中的异常处理方法，这些方法需要借助ExceptionHandler 注解进行描述，
        // 注解中的内容表示我这个方法能够处理的异常(包括这个异常的子类类型)
        @ExceptionHandler(value=Exception.class)
        @ResponseBody
        public String handleException(Exception e) {
            return e.getMessage();
        }

- 全局环境中对异常处理

        // 说明：使用@ControllerAdvice 注解描述的类，一般作为全局的异常处理类
        @ControllerAdvice
        public class AdviceExceptionHandler {
            @ExceptionHandler(Throwable.class)
            @ResponseBody
            public String handlerException(Throwable e) {
                return e.getMessage();
            }
        }

- 说明:
    > 全局的异常处理的优先级是最低的。<br>
    > 也就是说如果一个异常同时可以被局异常处理和全局异常处理。那么它会被局部异常处理。<br>


## 自定义异常处理(京淘项目异常处理)

- 京淘项目的异常处理
    > 在实际项目中，为了让客户端更好的处理服务端数据，通常会在服务端对数据进行统一的数据封装(例如状态码，具体信息，数据等)。
    
    1. 自定义一个异常类，例如`ServiceException` 处理业务层异常

            public class ServiceException extends RuntimeException {
                private static final long serialVersionUID = -4181072079763562413L;
            }

    2. 定义一个全局的异常处理器(`handler`)，在此类中进行全局异常处理
        > `@ControllerAdvice` 注解添加在类上面

        > `@ExceptionHandler` 注解: 表示要处理的异常类型

        > `@ResponseBody` 注解：返回给客户端的结果

        > `JsonReslut` 返回值为自定义的统一返回类型

            import org.springframework.web.bind.annotation.ControllerAdvice;
            import org.springframework.web.bind.annotation.ExceptionHandler;
            import org.springframework.web.bind.annotation.ResponseBody;

            @ControllerAdvice
            public class ControllerExceptionHandler {
                @ExceptionHandler(ServiceException.class)
                @ResponseBody
                public JsonResult handleServiceException(ServiceException e) {
                    e.printStackTrace();
                    return new JsonResult(1, e.message());
                }
            }

- 京淘项目的统一返回类型
    > 自行添加相应的构造方法以及get 方法

        public class JsonResult {
            private int state = 1;	// 状态码 state = 1, 业务成功. state = 0, 业务失败
            private String message = "OK"; // 与状态码对应的具体消息(例如：404 <==> 页面不存在) 
            private Object data;	// 借助此发展封装服务端返回的具体数据，例如查询的结果
        }

- 小结：
    > 1. 实现一个异常处理类，继承自`RuntimeException`; 
    >> - 全局异常
    >>> 1. 实现一个全局异常处理器(`ControllerExceptionHandler`); 
    >>> 2. 通过注解`@ControllerAdvice` 指定它是一个全局异常处理类;
    >>> 3. 通过注解`@ExceptionHandler(ServiceException.class)` 指定一个方法来处理这个异常; 
    >>> 4. 同时通过`@ResponseBody` 来指定返回一个数据结果; 
    >> - 实现局部异常处理
    >>> 1. 在`Controller` 控制器类中添加一个实现方法; 
    >>>> - `public JsonResult handleException(ServiceException e);`
    >>> 2. 为此方法上添加注解: `@ExceptionHandler(value=ServiceException.class)` 表示要处理哪个异常;
    > 2. 我们也需要统一一个返回类型(比如: `JsonResult`); 







