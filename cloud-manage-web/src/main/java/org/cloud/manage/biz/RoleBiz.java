package org.cloud.manage.biz;

import java.util.List;

import org.cloud.lang.jdbc.PageBounds;
import org.cloud.lang.BaseUtil;
import org.cloud.manage.dao.RoleDao;
import org.cloud.manage.model.Role;
import org.cloud.manage.model.vo.RoleQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

/**
 * 角色Biz
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2014-10-08 17:40:51
 * @author 
 *		Cloud
 */
@Component
public class RoleBiz {

	@Autowired
	private RoleDao dao;
	
	/**
	 * 根据条件查找记录
	 * @param query
	 * @return
	 */
	public List<Role> findList(RoleQuery query) {
		
		Weekend<Role> weekend = Weekend.of(Role.class);
		WeekendCriteria<Role, Object> criteria = weekend.weekendCriteria();
		String name = query.getName();
		if (BaseUtil.isNotEmpty(name)) {
			criteria.andLike(Role::getName, "%" + name + "%");
		}
		
		weekend.orderBy("id").asc();
		
		return dao.selectByExample(weekend);
	}
	
	/**
	 * 查找单页记录
	 * @param query
	 * @return
	 */
	public Page<Role> findPage(RoleQuery query, PageBounds pageBounds) {
		
		PageHelper.startPage(pageBounds.getPageNum(), pageBounds.getPageSize(), pageBounds.isContainsTotalCount());
		
		return (Page<Role>) findList(query);
	}
	
	/**
	 * 增加权限
	 * @param bean
	 * @return
	 */
	public long add(Role bean) {
		
		dao.insert(bean);
		
		return bean.getId();
	}

	public void changeRolePriv(Long roleId, List<Long> privIds) {
		
		dao.removeRolePrivByRoleId(roleId);
		
		if (BaseUtil.isNotEmpty(privIds)) {
			dao.addRolePriv(roleId, privIds);
		}
	}
	
	public void remove(Long roleId) {
		
		dao.deleteByPrimaryKey(roleId);
		dao.removeRolePrivByRoleId(roleId);
	}
	
	public Role findById(Long id) {
		return dao.selectByPrimaryKey(id);
	}
	
	public void update(Role role) {
		
		dao.updateByPrimaryKey(role);
	}
	
	/**
	 * 获取权限选中的角色
	 */
	public List<Role> findByUserId(Long userId) {
		
		return dao.findByUserId(userId);
	}
}
