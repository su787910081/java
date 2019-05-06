package com.jt.sys.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jt.common.DateJsonSerializer;

public class SysMenu implements Serializable {
	private static final long serialVersionUID = 8101607337651651254L;

	Integer id;
	String name;
	String url;
	Integer type;
	Integer sort;
	String note;
	Integer parentId;
	String parentName;
	String permission;
	Date createdTime;
	Date modifiedTime;
	String createdUser;
	String modifiedUser;
	
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	@JsonSerialize(using=DateJsonSerializer.class)
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	@JsonSerialize(using=DateJsonSerializer.class)
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	@Override
	public String toString() {
		return "SysMenu [id=" + id + ", name=" + name + ", url=" + url + ", type=" + type + ", sort=" + sort + ", note="
				+ note + ", parentId=" + parentId + ", parentName=" + parentName + ", permission=" + permission
				+ ", createdTime=" + createdTime + ", modifiedTime=" + modifiedTime + ", createdUser=" + createdUser
				+ ", modifiedUser=" + modifiedUser + "]";
	}
	
	
}
