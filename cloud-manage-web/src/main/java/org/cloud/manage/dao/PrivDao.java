package org.cloud.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cloud.manage.model.Priv;
import org.cloud.manage.model.PrivMenu;

import tk.mybatis.mapper.common.Mapper;


/**
 * 权限Dao
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2014-10-08 17:12:25
 * @author 
 *		Cloud
 */
public interface PrivDao extends Mapper<Priv>{
	
	/**
	 * 添加权限菜单关联
	 */
	public void addPrivMenu(@Param("list")List<PrivMenu> list);
	
	/**
	 * 根据权限Id删除权限菜单关联
	 */
	public void deletePrivMenuByPrivId(long privId);
	
	/**
	 * 根据菜单/按钮Id删除权限菜单关联
	 */
	public void deletePrivMenuByMenuId(@Param("dataId")long dataId, @Param("type")int type);
	
	/**
	 * 获取权限选中的权限
	 */
	public List<Priv> findByRoleId(long roleId);

}
