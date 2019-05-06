package com.jt.common.vo;

import java.util.List;


/**
 * VO: Value Object (值对象)
 * 借助此对象封装当前页数据以及分面信息
 * @author suyh
 */

public class PageObject<T> {

	private List<T> records;	// 当前页的记录
	private int pageCount;	// 总页数
	private int rowCount;	// 总记录数
	private int pageCurrent;	// 当前页的页码
	
	public List<T> getRecords() {
		return records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getPageCurrent() {
		return pageCurrent;
	}
	public void setPageCurrent(int pageCurrent) {
		this.pageCurrent = pageCurrent;
	}
	
	
}
