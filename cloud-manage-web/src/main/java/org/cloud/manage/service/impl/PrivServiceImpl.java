package org.cloud.manage.service.impl;

import java.util.List;

import org.cloud.lang.jdbc.PageBounds;
import org.cloud.lang.BaseUtil;
import org.cloud.manage.dao.PrivDao;
import org.cloud.manage.model.Priv;
import org.cloud.manage.model.PrivMenu;
import org.cloud.manage.model.vo.PrivQuery;
import org.cloud.manage.service.PrivService;
import org.cloud.manage.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;


/**
 * 权限Service 实现
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2014-10-08 17:38:10
 * @author 
 *		Cloud
 */
@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class PrivServiceImpl implements PrivService {

	@Autowired
	private PrivDao dao;
	
	@Override
	public Page<Priv> findPage(PrivQuery query, PageBounds pageBounds) {

		PageHelper.startPage(pageBounds.getPageNum(), pageBounds.getPageSize(), pageBounds.isContainsTotalCount());
		
		Weekend<Priv> weekend = Weekend.of(Priv.class);
		WeekendCriteria<Priv, Object> criteria = weekend.weekendCriteria();
		
		String name = query.getName();
		if (BaseUtil.isNotEmpty(name)) {
			criteria.andLike(Priv::getName, "%" + name + "%");
		}
		
		String comments = query.getComments();
		if (BaseUtil.isNotEmpty(comments)) {
			criteria.andLike(Priv::getComments, "%" + comments + "%");
		}
		
		weekend.orderBy("id").asc();
		
		return (Page<Priv>) dao.selectByExample(weekend);
	}
	
	@Override
	public List<Priv> findAll() {
		
		return dao.selectAll();
	}

	@Override
	public long add(Priv bean) {
		
		dao.insert(bean);
		return bean.getId();
	}

	@Override
	public Priv findById(long id) {
		
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public void update(Priv bean) {
		
		dao.updateByPrimaryKey(bean);
	}

	@Override
	public void delete(long id) {
		
		dao.deleteByPrimaryKey(id);
		dao.deletePrivMenuByPrivId(id);
		
		AuthUtil.refreshRole();
	}

	@Override
	public void changePrivMenu(long privId, List<PrivMenu> list) {
		
		dao.deletePrivMenuByPrivId(privId);
		
		if (BaseUtil.isNotEmpty(list)) {
			dao.addPrivMenu(list);
		}

		AuthUtil.refreshRole();
	}
	
	@Override
	public List<Priv> findByRoleId(long roleId) {
		
		return dao.findByRoleId(roleId);
	}
	
}
