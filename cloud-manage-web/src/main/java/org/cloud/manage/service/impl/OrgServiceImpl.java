package org.cloud.manage.service.impl;

import java.util.List;

import org.cloud.lang.jdbc.PageBounds;
import org.cloud.lang.BaseUtil;
import org.cloud.manage.dao.OrgDao;
import org.cloud.manage.model.Org;
import org.cloud.manage.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


/**
 * 部门Service 实现
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2014-10-08 17:36:09
 * @author 
 *		Cloud
 */
@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class OrgServiceImpl implements OrgService  {

	@Autowired
	private OrgDao dao;
	
	@Override
	public List<Org> findByParentId(Long pid, Boolean hasChild) {
		
		if (pid == null) {
			pid = Org.ROOT_ID;
		}
		
		return dao.findByParentId(pid, hasChild);
	}
	
	@Override
	public Page<Org> findByParentIdPage(Long pid, PageBounds pageBounds) {
		
		if (pid == null) {
			pid = Org.ROOT_ID;
		}
		
		PageHelper.startPage(pageBounds.getPageNum(), pageBounds.getPageSize(), pageBounds.isContainsTotalCount());
		return dao.findByParentIdPage(pid);
	}

	@Override
	public void delete(long id) {
		
		List<Org> list = dao.findByParentId(id, null);
		if (BaseUtil.isNotEmpty(list)) {
			for (Org bean : list) {
				delete(bean.getId());
			}
		}
		
		dao.delete(id);
	}
	
	@Override
	public Org findById(long id) {
		
		return dao.findById(id);
	}

	@Override
	public void update(Org bean) {
		
		dao.update(bean);
	}

	@Override
	public long add(Org bean) {
		
		dao.add(bean);
		
		return bean.getId();
	}
	
}
