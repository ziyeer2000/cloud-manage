package org.cloud.manage.model.vo;

import org.cloud.lang.BaseUtil;


/**
 * 权限查询对象
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2015-10-20 16:16:31
 * @author 
 *		Cloud
 */
public class PrivQuery extends BaseQuery {

	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 备注
	 */
	private String comments;

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
		
		if (BaseUtil.isEmpty(comments)) {
			this.comments = null;
		} else {
			this.comments = comments;
		}
	}
	
}
