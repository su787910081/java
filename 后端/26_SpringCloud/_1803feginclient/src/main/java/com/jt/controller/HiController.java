package com.jt.controller;


import com.jt.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HiController {
    @Autowired
    private HiService service;

    @RequestMapping("/hi")
    @ResponseBody
    public String sayHi(String name) {
        String hi = service.sayHi(name);
        return hi;
    }

}
