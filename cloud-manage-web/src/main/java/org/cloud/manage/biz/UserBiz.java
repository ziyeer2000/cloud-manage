package org.cloud.manage.biz;

import org.cloud.lang.BaseUtil;
import org.cloud.lang.exception.CheckDataException;
import org.cloud.manage.dao.UserDao;
import org.cloud.manage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户Biz
 * @since
 * 		v1.0
 * @version
 * 		v1.0, 2018-11-22 10:27:56
 * @author Cloud
 */
@Component
public class UserBiz {

	@Autowired
	private UserDao dao;
	
	/**
	 * 增加
	 */
	public long add(User bean) {
		
		check(bean);
		dao.add(bean);
		return bean.getId();
	}
	
	/**
	 * 修改
	 */
	public void modify(User bean) {
		
		check(bean);
		dao.update(bean);
	}
	
	/**
	 * 检查数据是否可用
	 */
	public void check(User bean) throws CheckDataException {
		
		String userName = bean.getUserName();
		if (BaseUtil.isEmpty(userName)) {
			throw new CheckDataException("用户名为空");
		}
		
		Long id = bean.getId();
		if (id != null) {
			User org = dao.findUserById(id);
			if (org.getUserName().equals(userName)) { //用户名改变
				return ;
			}
		}
		
		if (dao.findCountByName(userName) > 0) {
			throw new CheckDataException("用户名存在");
		}
	}
	
	/**
	 * 验证用户名是否可用
	 */
	public boolean checkUserName(String userName) {
		
		return dao.findCountByName(userName) <= 0;
	}
}
