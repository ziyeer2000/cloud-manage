package org.cloud.manage.service;

import java.util.List;

import org.cloud.lang.jdbc.PageBounds;
import org.cloud.manage.model.Role;
import org.cloud.manage.model.vo.RoleQuery;

import com.github.pagehelper.Page;


/**
 * 角色Service
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2013-08-02 10:19:42
 * @author 
 *		Cloud
 */
public interface RoleService {

	/**
	 * 根据条件查找记录
	 */
	public List<Role> findAll();
	
	/**
	 * 查找单页记录
	 */
	public Page<Role> findPage(RoleQuery query, PageBounds pageBounds);
	
	/**
	 * 增加角色
	 */
	public long add(Role bean);

	/**
	 * 修改角色权限关系
	 */
	public void changeRolePriv(long roleId, List<Long> privIds);
	
	/**
	 * 删除角色
	 */
	public void remove(long id);
	
	/**
	 * 根据id获取角色
	 */
	public Role findById(long id);
	
	/**
	 * 更新数据
	 */
	public void update(Role bean);
	
	/**
	 * 获取权限选中的角色
	 */
	public List<Role> findByUserId(long userId);
}
