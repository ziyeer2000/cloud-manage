package org.cloud.manage.service;

import org.cloud.lang.jdbc.PageBounds;
import org.cloud.manage.model.Log;
import org.cloud.manage.model.vo.LogQuery;

import com.github.pagehelper.Page;

/**
 * 日志Service
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2014-10-08 17:29:01
 * @author 
 *		Cloud
 */
public interface LogService {

	public long add(Log bean);
	
	public Page<Log> findPage(LogQuery query, PageBounds pageBounds);
}
