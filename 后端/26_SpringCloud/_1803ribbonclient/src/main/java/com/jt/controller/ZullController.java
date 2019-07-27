package com.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ZullController {
    @RequestMapping("/zuul")
    @ResponseBody
    public String getName(String name) {
        return "hi, " + name + ", I am from ribbon client";
    }

}