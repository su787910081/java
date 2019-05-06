package cn.tedu;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String sayHello() {
        return "hello service";
    }
}
