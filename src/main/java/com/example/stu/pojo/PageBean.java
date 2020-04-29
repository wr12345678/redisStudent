package com.example.stu.pojo;

import java.util.List;


/**
 * ��ҳ��ʵ��
 * @author admin
 *
 * @param <T>
 */
public class PageBean<T> {
	private int pageSize;//ÿҳ������
	private int currentPage;//��ǰҳ
	private int totalPage;//��ҳ��
	
	private List<T> pageList;//��ǰҳ������

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;//���������ļ���pageSize = 10/20
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
