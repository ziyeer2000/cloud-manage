package org.cloud.manage.service;

import java.util.List;

import org.cloud.manage.model.Button;

/**
 * 按钮Service
 * @since
 * 		v1.0
 * @version
 * 		v1.0, 2018-08-17 11:41:01
 * @author Cloud
 */
public interface ButtonService {

	/**
	 * 根据主键查询
	 */
	public Button findById(long id);

	/**
	 * 根据菜单查询
	 */
	public List<Button> findByMenuId(long menuId);

	/**
	 * 增加
	 */
	public long add(Button bean);

	/**
	 * 更新
	 */
	public void modify(Button bean);

	/**
	 * 删除
	 */
	public void remove(long id);
	
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
	public List<Button> findByUserIdAndMenuId(long userId, long menuId);
	
}
