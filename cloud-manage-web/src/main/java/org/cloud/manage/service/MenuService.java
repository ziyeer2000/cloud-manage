package org.cloud.manage.service;

import java.util.List;

import org.cloud.lang.jdbc.PageBounds;
import org.cloud.manage.model.Menu;
import org.cloud.manage.model.vo.MenuQuery;

import com.github.pagehelper.Page;


/**
 * 菜单Service 
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2013-07-29 16:13:29
 * @author 
 *		Cloud
 */
public interface MenuService {
	
	/**
	 * 获取菜单信息
	 */
	public Page<Menu> findPage(MenuQuery query, PageBounds pageBounds);

	/**
	 * 添加
	 */
	public long insert(Menu menu);

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
	public List<Menu> findByPid(Long pid);
	
	/**
	 * 根据父Id及用户Id查找所有菜单
	 */
	public List<Menu> findByPidAndUserId(Long pid, long userId);
	
	/**
	 * 获取权限选中的菜单
	 */
	public List<Menu> findByPrivId(long privId);
	
	/**
	 * 根据权限Id查找所有菜单
	 */
	public List<Menu> findByRoleId(long roleId);
}
