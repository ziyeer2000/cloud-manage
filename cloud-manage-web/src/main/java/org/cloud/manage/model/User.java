package org.cloud.manage.model;

import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = -4820890988407067352L;

	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 真实姓名
	 */
	private String realName;
	
	/**
	 * 部门Id
	 */
	private Long orgId;
	
	/**
	 * 部门名称
	 */
	private String orgName;
	
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
	 * 获取用户名
	 * @return 
	 * 		用户名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置用户名
	 * @param userName
	 * 			用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 获取密码
	 * @return 
	 * 		密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置密码
	 * @param password
	 * 			密码
	 */
	public void setPassword(String password) {
		this.password = password;
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
		this.realName = realName;
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

	/**
	 * 获取部门名称
	 * @return 
	 * 		部门名称
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * 设置部门名称
	 * @param orgName
	 * 			部门名称
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
}
