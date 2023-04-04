package org.cloud.manage.model.vo;

import org.cloud.lang.BaseUtil;
import org.cloud.lang.StringUtil;

import tk.mybatis.mapper.entity.Example.OrderBy;
import tk.mybatis.mapper.weekend.Weekend;

/**
 * 基础查询类
 * @author Cloud
 *
 */
public class BaseQuery {

	/**
	 * 排序字段
	 */
	private String orderByField;
	
	/**
	 * 数据库排序字段
	 */
	private String orderByDbField;
	
	/**
	 * 是否倒序
	 */
	private boolean desc = true;

	/** 
	 * 获取排序字段
	 * @return orderByField
	 *		排序字段
	 */
	public String getOrderByField() {
		return orderByField;
	}

	/** 
	 * 设置排序字段
	 * @param orderByField 
	 * 		排序字段
	 */
	public void setOrderByField(String orderByField) {
		this.orderByField = orderByField;
	}

	/** 
	 * 获取是否倒序
	 * @return desc
	 *		是否倒序
	 */
	public boolean isDesc() {
		return desc;
	}

	/** 
	 * 设置是否倒序
	 * @param desc 
	 * 		是否倒序
	 */
	public void setDesc(boolean desc) {
		this.desc = desc;
	}
	
	/** 
	 * 设置数据库排序字段
	 * @param orderByDbField 
	 * 		数据库排序字段
	 */
	public void setOrderByDbField(String orderByDbField) {
		this.orderByDbField = orderByDbField;
	}

	/**
	 * 获取排序SQL
	 * @return
	 */
	public String getOrderBySql() {
		
		if (BaseUtil.isNotEmpty(orderByDbField)) {
			return orderByDbField + (desc ? " desc " : " asc "); 
		}
		
		if (BaseUtil.isNotEmpty(orderByField)) {
			return StringUtil.humpToUnderline(orderByField) + (desc ? " desc " : " asc "); 
		} 
		
		return null;
	}
	
	/**
	 * 解析set字段值. 空返回null, 其他返回val.trim()
	 * @param val
	 * @return
	 */
	public String parseSetStringFieldValue(String val) {
		
		if (BaseUtil.isEmpty(val)) {
			return null;
		} else {
			return val.trim();
		}
	}
	
	/**
	 * 填充OrderBy
	 */
	public void fillOrderBy(Weekend<?> weekend) {
		
		OrderBy orderBy = weekend.orderBy(getOrderByField());
		if (isDesc()) {
			orderBy.desc();
		} else {
			orderBy.asc();
		}
	}
}
