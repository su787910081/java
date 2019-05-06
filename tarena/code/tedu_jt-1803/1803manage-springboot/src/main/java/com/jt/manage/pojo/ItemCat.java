package com.jt.manage.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jt.common.po.BasePojo;

@Table(name="tb_item_cat")
// 忽略属性转换,某些GET 在表中没有对应字段时使用
@JsonIgnoreProperties(ignoreUnknown=true)
public class ItemCat extends BasePojo {
    private static final long serialVersionUID = -8884548477708215563L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long parentId;
    private String name;
    private Integer status;
    private Integer sortOrder;
    private Boolean isParent;
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getParentId() {
        return parentId;
    }
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getSortOrder() {
        return sortOrder;
    }
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
    public Boolean getIsParent() {
        return isParent;
    }
    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }
    // json 字符串中出现text 属性，就是name 的值
    public String getText() {
        return name;
    }
    // 在json 字符串中出现state 属性，值为open 或者closed 字符串
    public String getState() {
        return isParent ? "closed" : "open";
    }
}
