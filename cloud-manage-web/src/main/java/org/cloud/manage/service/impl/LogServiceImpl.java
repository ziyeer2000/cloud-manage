package org.cloud.manage.service.impl;

import java.util.Date;

import org.cloud.lang.BaseUtil;
import org.cloud.lang.jdbc.PageBounds;
import org.cloud.manage.dao.LogDao;
import org.cloud.manage.model.Log;
import org.cloud.manage.model.vo.LogQuery;
import org.cloud.manage.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;


/**
 * 日志Service 实现
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2014-10-08 17:29:14
 * @author 
 *		Cloud
 */
@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class LogServiceImpl implements LogService {

	@Autowired
	private LogDao dao;
	
	@Override
	public long add(Log bean) {

		dao.insert(bean);
		return bean.getId();
	}

	@Override
	public Page<Log> findPage(LogQuery query, PageBounds pageBounds) {
		
		PageHelper.startPage(pageBounds.getPageNum(), pageBounds.getPageSize(), pageBounds.isContainsTotalCount());
		
		Weekend<Log> weekend = Weekend.of(Log.class);
		WeekendCriteria<Log, Object> criteria = weekend.weekendCriteria();
		
		String type = query.getType();
		if (BaseUtil.isNotEmpty(type)) {
			criteria.andEqualTo(Log::getType, type);
		}
		
		Date createDateStart = query.getCreateDateStart();
		Date createDateEnd = query.getCreateDateEnd();
		if (BaseUtil.isNotEmpty(createDateStart) || BaseUtil.isNotEmpty(createDateEnd)) {
			criteria.andBetween(Log::getCreateDate,createDateStart, createDateEnd);
		}
		
		weekend.orderBy("createDate").desc();
		
		return (Page<Log>) dao.selectByExample(weekend);
	}
}
