package com.yc.tickets.domain;

import java.util.List;

/*
 * 分页对象
 */
public class PageBean<T> {
	private int totalCount ;	//总记录数
	private int totalPage ; //总页数
	private List<T> list ; //每一页的数据
	private int currentPage ; //当前页码
	private int rows ; // 每页显示的条数 
	
	@Override
	public String toString() {
		return "PageBean [totalCount=" + totalCount + ", totalPage=" + totalPage + ", list=" + list + ", currentPage="
				+ currentPage + ", rows=" + rows + "]";
	}
	public PageBean() {
		super();
	}
	public PageBean(int totalCount, int totalPage, List<T> list, int currentPage, int rows) {
		super();
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.list = list;
		this.currentPage = currentPage;
		this.rows = rows;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getlist() {
		return list;
	}
	public void setlist(List<T> list) {
		this.list = list;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	
}
