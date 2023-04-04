package org.cloud.manage.service.impl;

import org.cloud.cache.CacheClient;
import org.cloud.manage.dao.BaseDao;
import org.cloud.manage.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 基础Service
 * @since
 * 		v1.0
 * @version
 * 		v1.0, 2020-01-10 10:25:18
 * @author Cloud
 */
@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class BaseServiceImpl implements BaseService {

	@Autowired
	private BaseDao dao;
	
	@Override
	public boolean dbTest() {
	
		try {
			return dao.findNowDate() != null;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean cacheTest() {
		
		try {
			return CacheClient.test();
		} catch (Exception e) {
			return false;
		}
	}

}
