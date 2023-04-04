package org.cloud.manage.dao;

import java.util.Date;

/**
 * 基础Dao
 * @since
 * 		v1.0
 * @version
 * 		v1.0, 2020-01-10 09:57:07
 * @author Cloud
 */
public interface BaseDao {

	/**
	 * 查询当前时间
	 * @return
	 */
	public Date findNowDate();
}
