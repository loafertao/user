package cn.kgc.ums.pojo.vo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;

import cn.kgc.ums.pojo.entity.User;
import cn.kgc.ums.util.ConstantUtil;

public class SystemPage<E> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer pageNum;
	private Integer pageSize;
	private List<E> list;
	private Integer totalPage;
	private Long totalSize;
	
	public SystemPage() {}
	public SystemPage(Integer pageNum,Integer pageSize) {
		if (pageNum != null && pageNum > 0) {
			this.pageNum = pageNum;
		}else {
			this.pageNum = ConstantUtil.PAGE_NUM;
		}
		if (pageSize != null && pageSize > 0) {
			this.pageSize = pageSize;
		}else {
			this.pageSize = ConstantUtil.PAGE_SIZE;
		}
		
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public List<E> getList() {
		return list;
	}
	public void setList(List<E> list) {
		this.list = list;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Long getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(Long totalSize) {
		this.totalSize = totalSize;
	}
	public void parseToSystemPage(Page<E> page) {
		this.setList(page.getContent());
		this.setTotalPage(page.getTotalPages());
		this.setTotalSize(page.getTotalElements());
	}
	
} 
