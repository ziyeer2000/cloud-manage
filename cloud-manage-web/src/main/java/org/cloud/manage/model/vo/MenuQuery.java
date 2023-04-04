package org.cloud.manage.model.vo;

import org.cloud.lang.BaseUtil;

/**
 * 菜单查询对象
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2015-10-20 15:14:11
 * @author 
 *		Cloud
 */
public class MenuQuery extends BaseQuery {

	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * URL
	 */
	private String url;
	
	/**
	 * 是否有子菜单
	 */
	private Boolean hasChild;
	
	/**
	 * 父菜单Id
	 */
	private Long superId;

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
		
		if (BaseUtil.isEmpty(name)) {
			this.name = null;
		} else {
			this.name = name;
		}
	}

	/**
	 * 获取URL
	 * @return 
	 * 		URL
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置URL
	 * @param url
	 * 			URL
	 */
	public void setUrl(String url) {
		
		if (BaseUtil.isEmpty(url)) {
			this.url = null;
		} else {
			this.url = url;
		}
	}

	/**
	 * 获取是否有子菜单
	 * @return 
	 * 		是否有子菜单
	 */
	public Boolean getHasChild() {
		return hasChild;
	}

	/**
	 * 设置是否有子菜单
	 * @param hasChild
	 * 			是否有子菜单
	 */
	public void setHasChild(Boolean hasChild) {
		this.hasChild = hasChild;
	}

	/**
	 * 获取父菜单Id
	 * @return 
	 * 		父菜单Id
	 */
	public Long getSuperId() {
		return superId;
	}

	/**
	 * 设置父菜单Id
	 * @param superId
	 * 			父菜单Id
	 */
	public void setSuperId(Long superId) {
		this.superId = superId;
	}
	
}
