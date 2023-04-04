package org.cloud.manage.service;

import java.util.List;

import org.cloud.lang.jdbc.PageBounds;
import org.cloud.manage.model.User;
import org.cloud.manage.model.vo.UserQuery;

import com.github.pagehelper.Page;


/**
 * 用户Service
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2014-10-08 17:23:29
 * @author 
 *		Cloud
 */
public interface UserService {
	
	/**
	 * 插入数据
	 */
	public long add(User user);
	
	/**
	 * 根据用户名获取用户
	 */
	public User findUserByName(String userName);
	
	/**
	 * 修改密码
	 */
	public boolean changePassword(long id, String orgPassword, String newPassword);

	/**
	 * 修改密码(管理员)
	 */
	public void changePasswordAdmin(long id, String newPassword);
	
	/**
	 * 分页查询
	 */
	public Page<User> findPage(UserQuery query, PageBounds pageBounds);
	
	/**
	 * 根据主键获取数据
	 */
	public User findUserById(long id);
	
	/**
	 * 更新
	 */
	public void modify(User bean);
	
	/**
	 * 删除
	 */
	public void remove(long id);
	
	/**
	 * 添加用户角色关系 
	 */
	public void changeUserRole(long userId, List<Long> roleIds);
	
	/**
	 * 重置用户密码
	 */
	public void resetPassword(long userId);
	
	/**
	 * 验证用户名是否可用
	 */
	public boolean checkUserName(String userName);
	
}
