package org.cloud.manage.interceptor;

public class OnlineDataVo extends OnlineData {
	
	public OnlineDataVo(OnlineData bean) {
		
		super.setCreateDate(bean.getCreateDate());
		super.setIp(bean.getIp());
		super.setRemember(bean.isRemember());
		super.setSessionId(bean.getSessionId());
		super.setUser(bean.getUser());
	}

	private static final long serialVersionUID = 1216741603800935152L;
	
	private String userName;
	
	/**
	 * 获取userName
	 * @return 
	 * 		userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置userName
	 * @param userName
	 * 			userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
