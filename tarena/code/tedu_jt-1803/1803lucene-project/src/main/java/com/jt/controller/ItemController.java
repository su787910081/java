package com.jt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.pojo.Item;
import com.jt.service.ItemService;

@RestController
public class ItemController {
    @Autowired
    private ItemService service;

    // 接收一个create 请求地址的方法
    // 没有参数调用ervice 业务层创建索引
    @RequestMapping("create")
    public String createIndex() {
        try {
            service.createIndex();
            return "successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * 搜索索引返回数据10 localhost:8091/search/title/三星
     */
    @RequestMapping("search/{title}/{value}")
    public List<Item> searchIndex(@PathVariable String title,
            @PathVariable String value) {

        try {
            List<Item> list = service.search(title, value);
            return list;
        } catch (Exception e) {
            return new ArrayList<>();
        }

    }
}
