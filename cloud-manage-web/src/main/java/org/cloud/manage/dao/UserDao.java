package org.cloud.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cloud.manage.model.User;
import org.cloud.manage.model.vo.UserQuery;

import com.github.pagehelper.Page;


/**
 * 用户Dao
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2014-10-08 17:08:31
 * @author 
 *		Cloud
 */
public interface UserDao {

	/**
	 * 插入数据
	 */
	public void add(User user);
	
	/**
	 * 根据用户名获取用户
	 */
	public User findUserByName(String userName);
	
	/**
	 * 根据用户名获取用户数量
	 */
	public long findCountByName(String userName);
	
	/**
	 * 获取密码
	 */
	public String getPassword(long id);
	
	/**
	 * 修改密码
	 */
	public void changePassword(@Param("password")String password, @Param("id")long id);
	
	/**
	 * 分页查询
	 */
	public Page<User> findPage(@Param("query")UserQuery query);
	
	/**
	 * 根据主键获取数据
	 */
	public User findUserById(long id);
	
	/**
	 * 更新
	 */
	public void update(User bean);
	
	/**
	 * 删除
	 */
	public void delete(long id);
	
	/**
	 * 删除用户角色关系
	 */
	public void removeUserRoleByUserId(long userId);
	
	/**
	 * 添加用户角色关系 
	 */
	public void addUserRole(@Param("userId")long userId, @Param("roleIds")List<Long> roleIds);
	
}

