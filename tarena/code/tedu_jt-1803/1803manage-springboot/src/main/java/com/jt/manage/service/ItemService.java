package com.jt.manage.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.mapper.ItemDescMapper;
import com.jt.manage.mapper.ItemMapper;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;

@Service
public class ItemService {
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemDescMapper itemDescMapper;

    public List<Item> findItemList() {
        return itemMapper.selectAll();
    }

    public EasyUIResult findItemList(Integer page, Integer rows) {
        // 引入分布插件PageHelper
        PageHelper.startPage(page, rows); // 开启查询select 拦截
        // 经过了拦截拼接的查询结果，是一个page 对象，page 继承了List
        // select * from tb_item limit (page-1)*rows, rows
        List<Item> itemList = itemMapper.selectAll();
        // 从page 对象中获取数据
        PageInfo<Item> pageInfo = new PageInfo<Item>(itemList);
        // 利用PageInfo 将Page 对象中封装的数据，总数据条数count 和分页结果
        EasyUIResult result = new EasyUIResult();
        result.setTotal((int) pageInfo.getTotal());
        result.setRows(pageInfo.getList());
        PageHelper.clearPage();

        return result;
    }

    @Transactional
    public void saveItem(Item item, String desc) {
        item.setStatus(1);
        item.setCreated(new Date());
        item.setUpdated(item.getCreated());
        itemMapper.insert(item);
        
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        itemDescMapper.insert(itemDesc);
    }

    public SysResult queryItemDesc(Long id) {
        ItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(id);
        return SysResult.oK(itemDesc);
    }

    @Transactional
    public void updateItem(Item item, String desc) {
        Date nowDate = new Date();
        item.setUpdated(nowDate);
        itemMapper.updateByPrimaryKeySelective(item);
        
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        itemDesc.setUpdated(nowDate);
        itemDescMapper.updateByPrimaryKeySelective(itemDesc);
    }

    @Transactional
    public void deleteItems(Long[] ids) {
        for (Long id : ids) {
            itemMapper.deleteByPrimaryKey(id);
            itemDescMapper.deleteByPrimaryKey(id);
        }
    }
}
