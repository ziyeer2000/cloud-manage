package org.cloud.manage.service;

/**
 * 基础Service
 * @since
 * 		v1.0
 * @version
 * 		v1.0, 2020-01-10 10:19:37
 * @author Cloud
 */
public interface BaseService {

	/**
	 * 判断数据库连接是否成功
	 */
	public boolean dbTest();
	
	/**
	 * 判断缓存连接是否成功
	 */
	public boolean cacheTest();
}
