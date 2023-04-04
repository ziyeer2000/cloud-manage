package org.cloud.manage.service.impl;

import java.util.List;

import org.cloud.lang.jdbc.PageBounds;
import org.cloud.manage.biz.RoleBiz;
import org.cloud.manage.model.Role;
import org.cloud.manage.model.vo.RoleQuery;
import org.cloud.manage.service.RoleService;
import org.cloud.manage.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;


/**
 * 角色Service 实现
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2014-10-08 17:40:10
 * @author 
 *		Cloud
 */
@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleBiz roleBiz;
	
	@Override
	public List<Role> findAll() {
		
		RoleQuery query = new RoleQuery();
		query.setOrderByField(" id desc ");
		return roleBiz.findList(query);
	}

	@Override
	public Page<Role> findPage(RoleQuery query, PageBounds pageBounds) {
		
		query.setOrderByField(" t.id desc ");
		return roleBiz.findPage(query, pageBounds);
	}

	@Override
	public long add(Role bean) {
	
		return roleBiz.add(bean);
	}

	@Override
	public void changeRolePriv(long roleId, List<Long> privIds) {
		
		roleBiz.changeRolePriv(roleId, privIds);
		
		AuthUtil.refreshRole();
	}
	
	@Override
	public void remove(long id) {
		
		roleBiz.remove(id);
		
		AuthUtil.refreshRole();
	}
	
	@Override
	public Role findById(long id) {
		
		return roleBiz.findById(id);
	}
	
	@Override
	public void update(Role bean) {
		
		roleBiz.update(bean);
	}
	
	@Override
	public List<Role> findByUserId(long userId) {
		
		return roleBiz.findByUserId(userId);
	}
}
