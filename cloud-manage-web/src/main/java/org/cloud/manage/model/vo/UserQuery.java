package org.cloud.manage.model.vo;

import org.cloud.lang.BaseUtil;

/**
 * 用户查询对象
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2014-06-27 15:04:58
 * @author 
 *		Cloud
 */
public class UserQuery extends BaseQuery {

	/**
	 * 用户名称
	 */
	private String userName;
	
	/**
	 * 真实姓名
	 */
	private String realName;
	
	/**
	 * 部门Id
	 */
	private Long orgId;

	/**
	 * 获取用户名称
	 * @return 
	 * 		用户名称
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置用户名称
	 * @param userName
	 * 			用户名称
	 */
	public void setUserName(String userName) {
		
		if (BaseUtil.isEmpty(userName)) {
			this.userName = null;
		} else {
			this.userName = userName;
		}
	}

	/**
	 * 获取真实姓名
	 * @return 
	 * 		真实姓名
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * 设置真实姓名
	 * @param realName
	 * 			真实姓名
	 */
	public void setRealName(String realName) {
		
		if (BaseUtil.isEmpty(realName)) {
			this.realName = null;
		} else {
			this.realName = realName;
		}
	}
	
	/**
	 * 获取部门Id
	 * @return 
	 * 		部门Id
	 */
	public Long getOrgId() {
		return orgId;
	}

	/**
	 * 设置部门Id
	 * @param orgId
	 * 			部门Id
	 */
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public UserQuery() {
		setOrderByField(" t.id desc ");
	}
	
}
