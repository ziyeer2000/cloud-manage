package org.cloud.manage.interceptor;

import java.io.Serializable;
import java.util.Date;

import org.cloud.manage.model.User;

/**
 * 在线数据
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2014-07-08 09:36:13
 * @author 
 *		Cloud
 */
public class OnlineData implements Serializable {

	private static final long serialVersionUID = -7398247690555179472L;

	private String sessionId;
	
	private User user;
	
	private String ip;
	
	private Date createDate;
	
	private boolean remember;
	
	/**
	 * 获取sessionId
	 * @return 
	 * 		sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * 设置sessionId
	 * @param sessionId
	 * 			sessionId
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * 获取user
	 * @return 
	 * 		user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * 设置user
	 * @param user
	 * 			user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 获取ip
	 * @return 
	 * 		ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * 设置ip
	 * @param ip
	 * 			ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 获取createDate
	 * @return 
	 * 		createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 设置createDate
	 * @param createDate
	 * 			createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 获取remember
	 * @return 
	 * 		remember
	 */
	public boolean isRemember() {
		return remember;
	}

	/**
	 * 设置remember
	 * @param remember
	 * 			remember
	 */
	public void setRemember(boolean remember) {
		this.remember = remember;
	}
	
	/**
	 * 获取用户ID
	 */
	public long getUserId() {
		
		return user.getId();
	}
	
}
