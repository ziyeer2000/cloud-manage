package org.cloud.manage.model;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;

import org.cloud.lang.StringUtil;

import tk.mybatis.mapper.annotation.KeySql;

/**
 * 按钮
 * @since
 * 		v1.0
 * @version
 * 		v1.0, 2018-08-17 11:23:53
 * @author Cloud
 */
@Table(name = "t_button")
public class Button {

	/**
	 * 类型: 数据级
	 */
	public static final int TYPE_ROW = 1;
	
	/**
	 * 类型: 全局
	 */
	public static final int TYPE_GLOBAL = 2;

	/**
	 * 主键
	 */
	@Id
	@KeySql(useGeneratedKeys = true)
	private long id;
	
	/**
	 * 菜单ID
	 */
	private long menuId;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 事件
	 */
	private String event;
	
	/**
	 * 地址
	 */
	private String url;

	/**
	 * Ajax URL列表. 逗号分隔
	 */
	private String ajaxUrls;
	
	/**
	 * 排序
	 */
	private long seq;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 类型
	 */
	private int type = TYPE_ROW;

	/** 
	 * 获取主键 
	 * @return id 
	 *		主键 
	 */
	public long getId() {
		return id;
	}

	/** 
	 * 设置主键 
	 * @param id 
	 * 		主键 
	 */
	public void setId(long id) {
		this.id = id;
	}

	/** 
	 * 获取菜单ID 
	 * @return menuId 
	 *		菜单ID 
	 */
	public long getMenuId() {
		return menuId;
	}

	/** 
	 * 设置菜单ID 
	 * @param menuId 
	 * 		菜单ID 
	 */
	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}

	/** 
	 * 获取名称 
	 * @return name 
	 *		名称 
	 */
	public String getName() {
		return name;
	}

	/** 
	 * 设置名称 
	 * @param name 
	 * 		名称 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** 
	 * 获取地址 
	 * @return url 
	 *		地址 
	 */
	public String getUrl() {
		return url;
	}

	/** 
	 * 设置地址 
	 * @param url 
	 * 		地址 
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/** 
	 * 获取排序 
	 * @return seq 
	 *		排序 
	 */
	public long getSeq() {
		return seq;
	}

	/** 
	 * 设置排序 
	 * @param seq 
	 * 		排序 
	 */
	public void setSeq(long seq) {
		this.seq = seq;
	}

	/** 
	 * 获取备注 
	 * @return remark 
	 *		备注 
	 */
	public String getRemark() {
		return remark;
	}

	/** 
	 * 设置备注 
	 * @param remark 
	 * 		备注 
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/** 
	 * 获取AjaxURL列表.逗号分隔 
	 * @return ajaxUrls 
	 *		AjaxURL列表.逗号分隔 
	 */
	public String getAjaxUrls() {
		return ajaxUrls;
	}

	/** 
	 * 设置AjaxURL列表.逗号分隔 
	 * @param ajaxUrls 
	 * 		AjaxURL列表.逗号分隔 
	 */
	public void setAjaxUrls(String ajaxUrls) {
		this.ajaxUrls = ajaxUrls;
	}
	
	/**
	 * 获取Ajax Url 列表
	 */
	public List<String> getAjaxUrlList() {
		
		return StringUtil.simpleSplit(ajaxUrls, ",");
	}

	/** 
	 * 获取事件
	 * @return event 
	 *		事件
	 */
	public String getEvent() {
		return event;
	}

	/** 
	 * 设置事件
	 * @param event 
	 * 		事件
	 */
	public void setEvent(String event) {
		this.event = event;
	}

	/** 
	 * 获取类型
	 * @return type 
	 *		类型
	 */
	public int getType() {
		return type;
	}

	/** 
	 * 设置类型
	 * @param type 
	 * 		类型
	 */
	public void setType(int type) {
		this.type = type;
	}
	
}
