package org.cloud.manage.service;

import org.cloud.lang.jdbc.PageBounds;
import org.cloud.manage.interceptor.OnlineDataVo;

import com.github.pagehelper.Page;


/**
 * 在线Service
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2014-10-08 17:34:30
 * @author 
 *		Cloud
 */
public interface OnlineService {

	/**
	 * 分页查询
	 */
	public Page<OnlineDataVo> findPage(long userId, PageBounds pageBounds);
	
	/**
	 * 强制下线
	 */
	public void offline(long userId, String sessionId);
}
