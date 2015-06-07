package com.phone.cn.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

/**
 * @author zgdcool
 * @version 2015年1月21日 下午6:49:35
 * 
 */
public class SearchBean implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	/** a_id.asc , b_name.desc **/
	private String sort;

	private Integer pageNo = 1;

	private Integer pageSize = 10;// 0x7fffffff

	private Boolean isIframe;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	private String createUser;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	
	
	

	protected Object doNull(Object object) {
		if (object instanceof String) {
			return StringUtils.isBlank((String) object) ? null : object.toString().trim();
		}
		if (object instanceof Date) {
			return null;
		}
		return object;
	}

	private String updateUser;

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Boolean getIsIframe() {
		return isIframe;
	}

	public void setIsIframe(Boolean isIframe) {
		this.isIframe = isIframe;
	}

	private Map<String, String> orderExprs = new HashMap<String, String>();

	public void addOrderExpr(String property, String expr) {
		this.orderExprs.put(property, expr);
	}

	public PageBounds toPageBounds() {
		List<Order> orders = Order.formString(sort);
		for (int i = 0; i < orders.size(); i++) {
			Order order = orders.get(i);
			if (orderExprs.get(order.getProperty()) != null) {
				orders.set(i, new Order(order.getProperty(), order.getDirection(), orderExprs.get(order.getProperty())));
			}
		}
		return new PageBounds(pageNo, pageSize, orders);
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
    
	
	public Map<String, String> getOrderExprs() {
		return orderExprs;
	}

	public void setOrderExprs(Map<String, String> orderExprs) {
		this.orderExprs = orderExprs;
	}

}
