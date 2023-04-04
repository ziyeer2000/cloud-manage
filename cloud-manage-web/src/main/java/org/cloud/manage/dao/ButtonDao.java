package org.cloud.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cloud.manage.model.Button;

import tk.mybatis.mapper.common.Mapper;

/**
 * 按钮 Dao
 * @since
 * 		v1.0
 * @version
 * 		v1.0, 2018-08-17 11:26:44
 * @author Cloud
 */
public interface ButtonDao extends Mapper<Button>{

	/**
	 * 获取权限选中的按钮
	 */
	public List<Button> findByPrivId(long privId);
	
	/**
	 * 根据权限Id查找所有按钮
	 */
	public List<Button> findByRoleId(long roleId);
	
	/**
	 * 根据用户Id及菜单ID,查找所有按钮
	 */
	public List<Button> findByUserIdAndMenuId(@Param("userId") long userId, @Param("menuId") long menuId);
}
