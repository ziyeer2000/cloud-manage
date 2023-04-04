package org.cloud.manage.model.vo;

import org.cloud.lang.BaseUtil;

public class RoleQuery extends BaseQuery {

	/**
	 * 名称
	 */
	private String name;
	
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

}
