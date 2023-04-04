package org.cloud.manage.model;

import java.util.List;

import org.cloud.lang.StringUtil;

/**
 * 系统菜单
 * @author Cloud
 *
 */
public class Menu {

	/**
	 * 根节点Id
	 */
	public static Long ROOT_ID = -1L;
	
	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 排序
	 */
	private Integer seq;
	
	/**
	 * 菜单对应的URL
	 */
	private String url;
	
	/**
	 * 父Id
	 */
	private Long superId;
	
	/**
	 * Ajax URL列表. 逗号分隔
	 */
	private String ajaxUrls;
	
	/**
	 * 是否有子菜单
	 */
	private boolean hasChild = false;
	
	/**
	 * 备注
	 */
	private String comments;
	
	/**
	 * 父菜单名称
	 */
	private String superName;

	/**
	 * 获取主键
	 * @return 
	 * 		主键
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置主键
	 * @param id
	 * 			主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取名称
	 * @return 
	 * 		名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * @param name
	 * 			名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取排序
	 * @return 
	 * 		排序
	 */
	public Integer getSeq() {
		return seq;
	}

	/**
	 * 设置排序
	 * @param seq
	 * 			排序
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	/**
	 * 获取菜单对应的URL
	 * @return 
	 * 		菜单对应的URL
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置菜单对应的URL
	 * @param url
	 * 			菜单对应的URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取父Id
	 * @return 
	 * 		父Id
	 */
	public Long getSuperId() {
		return superId;
	}

	/**
	 * 设置父Id
	 * @param superId
	 * 			父Id
	 */
	public void setSuperId(Long superId) {
		this.superId = superId;
	}

	/**
	 * 获取是否有子菜单
	 * @return 
	 * 		是否有子菜单
	 */
	public boolean isHasChild() {
		return hasChild;
	}

	/**
	 * 设置是否有子菜单
	 * @param hasChild
	 * 			是否有子菜单
	 */
	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

	/**
	 * 获取备注
	 * @return 
	 * 		备注
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * 设置备注
	 * @param comments
	 * 			备注
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * 获取父菜单名称
	 * @return 
	 * 		父菜单名称
	 */
	public String getSuperName() {
		return superName;
	}

	/**
	 * 设置父菜单名称
	 * @param superName
	 * 			父菜单名称
	 */
	public void setSuperName(String superName) {
		this.superName = superName;
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
}
