package com.jt.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.redis.RedisService;
import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.pojo.ItemCat;

@Service
public class ItemCatService {
    @Autowired
    private ItemCatMapper itemCatMapper;
    @Autowired
    private RedisService redisService;

    public List<ItemCat> findAllItemCat() {
        return itemCatMapper.selectAll();
    }

    public static final ObjectMapper MAPPER = new ObjectMapper();

    public List<ItemCat> findListByParentId(Long id) {
        // 1. 生成全局唯一key 值，对应当前存储的数据 ITEM_CAT_ID
        // 2. 判断key 值是否在缓存已有
        // 2.1 若有，数据封装直接返回controller
        // 2.2. 没有数据表示缓存不能直接返回数据，进入持久层从数据库获取数据
        // 与key 值对应存入缓存，供后续使用。
        String key = "ITEM_CAT_" + id;
        if (redisService.exists(key)) {
            try {
                // 获取数据，转化成对象，返回
                String jsonData = redisService.get(key);
                System.out.println("jsonData: ");
                System.out.println(jsonData);
                JsonNode data = MAPPER.readTree(jsonData);
                List<ItemCat> itemCatList = null;
                if (data.isArray() && data.size() > 0) {
                    itemCatList = MAPPER.readValue(data.traverse(),
                            MAPPER.getTypeFactory().constructCollectionType(
                                    List.class, ItemCat.class));
                }
                return itemCatList;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                // 从数据库获取数据，然后添加到redis 中
                // 通过对参数itemCat 这个对象中所有非null 属性的条件拼接完成SQL 语句
                ItemCat itemCat = new ItemCat();
                itemCat.setParentId(id);
                List<ItemCat> itemCatList = itemCatMapper.select(itemCat);

                String jsonData = MAPPER.writeValueAsString(itemCatList);
                redisService.set(key, jsonData);

                return itemCatList;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
