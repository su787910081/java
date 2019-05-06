package com.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


// 当前启动类继承配置适应器，可以调用 方法，将'/'访问路径，
// 利用servlet 转发到index.jsp或其他
@SpringBootApplication
@Controller
public class WebStarter extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(WebStarter.class, args);
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 调用内部 controller 接收'/' 路径，和自定义单独写controller 效果一样。
        registry.addViewController("/").setViewName("forward:/index.jsp");
        
        // 权限控制，把当前这个controller 的接收默认的路径"/" 设置为最高级，别的相同的controller 无效
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

        super.addViewControllers(registry);
    }
    
    @RequestMapping("index")
    public String goIndex() {
        // 通过配置的前缀后缀，是当前controller 返回后的字符串
        // 专项 /WEB-INF/view/index.jsp
        return "index";
    }
}
