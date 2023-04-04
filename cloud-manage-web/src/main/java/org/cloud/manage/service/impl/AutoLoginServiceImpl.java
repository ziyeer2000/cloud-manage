package org.cloud.manage.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.cloud.manage.dao.RoleDao;
import org.cloud.manage.dao.UserDao;
import org.cloud.manage.model.User;
import org.cloud.manage.utils.AuthUtil;
import org.cloud.web.session.service.AutoLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 自动登录Service 实现
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2015-10-24 23:12:58
 * @author 
 *		Cloud
 */
@Service("autoLoginService")
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class AutoLoginServiceImpl implements AutoLoginService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public String getValidateCode(long userId, HttpServletRequest request) {
		
		return userDao.getPassword(userId);
	}

	@Override
	public boolean login(long userId, String validateCode, HttpServletRequest request) {
		
		User user = userDao.findUserById(userId);
		if (user.getPassword().equals(validateCode)) {
			
			AuthUtil.setUser(user, roleDao.findByUserId(userId), true);
			return true;
		}
		
		return false;
	}

}
