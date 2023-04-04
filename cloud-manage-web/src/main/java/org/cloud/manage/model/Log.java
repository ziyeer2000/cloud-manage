package org.cloud.manage.model;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.KeySql;

@Table(name = "t_log")
public class Log {

	public static final String TYPE_LOGIN = "登录";
	
	/**
	 * 主键
	 */
	@Id
	@KeySql(useGeneratedKeys = true)
	private long id;
	
	/**
	 * 用户ID
	 */
	private long userId;
	
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * IP
	 */
	private String ip;
	
	/**
	 * 类型
	 */
	private String type;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 创建日期
	 */
	private Date createDate;

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
	 * 获取用户ID 
	 * @return userId 
	 *		用户ID 
	 */
	public long getUserId() {
		return userId;
	}

	/** 
	 * 设置用户ID 
	 * @param userId 
	 * 		用户ID 
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/** 
	 * 获取用户名 
	 * @return userName 
	 *		用户名 
	 */
	public String getUserName() {
		return userName;
	}

	/** 
	 * 设置用户名 
	 * @param userName 
	 * 		用户名 
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/** 
	 * 获取IP 
	 * @return ip 
	 *		IP 
	 */
	public String getIp() {
		return ip;
	}

	/** 
	 * 设置IP 
	 * @param ip 
	 * 		IP 
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/** 
	 * 获取类型 
	 * @return type 
	 *		类型 
	 */
	public String getType() {
		return type;
	}

	/** 
	 * 设置类型 
	 * @param type 
	 * 		类型 
	 */
	public void setType(String type) {
		this.type = type;
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
	 * 获取创建日期 
	 * @return createDate 
	 *		创建日期 
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/** 
	 * 设置创建日期 
	 * @param createDate 
	 * 		创建日期 
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
