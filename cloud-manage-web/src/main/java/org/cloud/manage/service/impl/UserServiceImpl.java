package org.cloud.manage.service.impl;

import java.util.List;

import org.cloud.lang.jdbc.PageBounds;
import org.cloud.lang.BaseUtil;
import org.cloud.lang.security.DigestAlgorithm;
import org.cloud.lang.security.DigestUtil;
import org.cloud.manage.biz.UserBiz;
import org.cloud.manage.dao.UserDao;
import org.cloud.manage.model.User;
import org.cloud.manage.model.vo.UserQuery;
import org.cloud.manage.service.UserService;
import org.cloud.manage.utils.AuthUtil;
import org.cloud.manage.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


/**
 * 用户Service 实现
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2014-10-08 17:24:20
 * @author 
 *		Cloud
 */
@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class UserServiceImpl implements UserService  {

	@Autowired
	private UserDao dao;
	
	@Autowired
	private UserBiz biz;
	
	@Override
	public long add(User user) {

		return biz.add(user);
	}
	
	@Override
	public User findUserByName(String userName) {
		
		return dao.findUserByName(userName);
	}
	
	@Override
	public boolean changePassword(long id, String orgPassword, String newPassword) {
		
		String passwordDb = dao.getPassword(id);
		if (DigestUtil.getDigest(DigestAlgorithm.SHA_256, orgPassword).equals(passwordDb)) {
			dao.changePassword(DigestUtil.getDigest(DigestAlgorithm.SHA_256, newPassword), id);
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void changePasswordAdmin(long id, String newPassword) {
		
		dao.changePassword(DigestUtil.getDigest(DigestAlgorithm.SHA_256, newPassword), id);
	}
	
	@Override
	public Page<User> findPage(UserQuery query, PageBounds pageBounds) {
		
		PageHelper.startPage(pageBounds.getPageNum(), pageBounds.getPageSize(), pageBounds.isContainsTotalCount());
		return dao.findPage(query);
	}
	
	@Override
	public User findUserById(long id) {
		
		return dao.findUserById(id);
	}
	
	@Override
	public void modify(User bean) {
	
		biz.modify(bean);
	}
	
	@Override
	public void remove(long id) {
		
		dao.delete(id);
		dao.removeUserRoleByUserId(id);
	}
	
	@Override
	public void changeUserRole(long userId, List<Long> roleIds) {
		
		dao.removeUserRoleByUserId(userId);
		
		if (BaseUtil.isNotEmpty(roleIds)) {
			dao.addUserRole(userId, roleIds);
		}
		
		AuthUtil.changeUserRole(userId, roleIds);
	}
	
	@Override
	public void resetPassword(long userId) {
		
		dao.changePassword(Constants.PASSWORD, userId);
	}
	
	@Override
	public boolean checkUserName(String userName) {
		
		return biz.checkUserName(userName);
	}
}
