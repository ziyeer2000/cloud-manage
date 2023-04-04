package org.cloud.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cloud.manage.model.Role;

import tk.mybatis.mapper.common.Mapper;


/**
 * 角色Dao
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2013-08-02 09:28:56
 * @author 
 *		Cloud
 */
public interface RoleDao extends Mapper<Role> {

	/**
	 * 添加角色权限关系 
	 *  
	 * @return
	 */
	public void addRolePriv(@Param("roleId")long roleId, @Param("privIds")List<Long> privIds);

	/**
	 * 删除角色权限关系
	 */
	public boolean removeRolePrivByRoleId(long roleId);
	
	/**
	 * 获取权限选中的角色
	 */
	public List<Role> findByUserId(long userId);
}
