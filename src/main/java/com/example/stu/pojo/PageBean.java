package com.example.stu.pojo;

import java.util.List;


/**
 * 分页的实体
 * @author admin
 *
 * @param <T>
 */
public class PageBean<T> {
	private int pageSize;//每页多少条
	private int currentPage;//当前页
	private int totalPage;//总页码
	
	private List<T> pageList;//当前页的数据

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;//来自配置文件：pageSize = 10/20
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
//		int elementCount = new RoleDaoImpl().countRole();
		//int totalPage = (int) Math.ceil(elementCount/this.getPageSize());
		
		this.totalPage = totalPage;
	}

	public List<T> getPageList() {
		return pageList;
	}

	public void setPageList(List<T> pageList) {
		this.pageList = pageList;
	}

	public PageBean() {
		super();
		//this.setPageSize();
	}

	public PageBean(int pageSize, int currentPage, int totalPage,
			List<T> pageList) {
		super();
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalPage = totalPage;
		this.pageList = pageList;
	}
	
	
}
