package com.jt.manage.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.service.ItemService;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("item/query")
    @ResponseBody
    public EasyUIResult findItemList(Integer page, Integer rows) {
        return itemService.findItemList(page, rows);
    }

    // 新增端口和端口详情
    @RequestMapping("item/save")
    @ResponseBody
    public SysResult saveItem(Item item, String desc) {
        SysResult result = null;
        try {
            itemService.saveItem(item, desc);
            result = SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
            result = SysResult.build(201, e.getMessage());
        }

        return result;
    }

    @RequestMapping("item/update")
    @ResponseBody
    public SysResult updateItem(Item item, String desc) {
        SysResult result = null;
        try {
            itemService.updateItem(item, desc);
            result = SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
            result = SysResult.build(201, e.getMessage());
        }

        return result;
    }

    @RequestMapping("item/query/item/desc/{id}")
    @ResponseBody
    public SysResult queryItemDesc(@PathVariable Long id) {
        SysResult result = null;

        try {
            result = itemService.queryItemDesc(id);
        } catch (Exception e) {
            e.printStackTrace();
            result = SysResult.build(201, e.getMessage());
        }

        return result;
    }

    @RequestMapping("item/delete")
    @ResponseBody
    public SysResult deleteItems(Long[] ids) {
        SysResult result = null;
        try {
            itemService.deleteItems(ids);
            result = SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
            result = SysResult.build(201, "删除失败");
        }
        return result;
    }
}
