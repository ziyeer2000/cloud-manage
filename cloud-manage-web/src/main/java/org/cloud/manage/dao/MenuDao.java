package org.cloud.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cloud.manage.model.Menu;
import org.cloud.manage.model.vo.MenuQuery;

import com.github.pagehelper.Page;


/**
 * 菜单Dao
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2013-07-29 16:02:14
 * @author 
 *		Cloud
 */
public interface MenuDao {
	/**
	 * 获取菜单信息
	 */
	public Page<Menu> findPage(@Param("query")MenuQuery query);
	
	/**
	 * 添加
	 */
	public void insert(Menu menu);
	
	/**
	 * 根据id获取菜单信息
	 */
	public Menu findById(long id);
	
	/**
	 * 更新
	 */
	public void update(Menu menu);
	
	/**
	 * 删除
	 */
	public void delete(long id);

	/**
	 * 根据父Id查找所有
	 */
	public List<Menu> findByPid(long pid);
	
	/**
	 * 根据父Id及用户Id查找所有菜单
	 */
	public List<Menu> findByPidAndUserId(@Param("pid")long pid, @Param("userId")long userId);
	
	/**
	 * 根据权限Id查找所有菜单
	 */
	public List<Menu> findByRoleId(long roleId);
	
	/**
	 * 获取权限选中的菜单
	 */
	public List<Menu> findByPrivId(long privId);
}
