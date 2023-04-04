package org.cloud.manage.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.cloud.manage.dao.MenuDao;
import org.cloud.manage.model.Menu;

/**
 * 菜单Biz
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2013-07-29 16:10:38
 * @author 
 *		Cloud
 */
@Component
public class MenuBiz {

	@Autowired
	private MenuDao dao;
	
	/**
	 * 根据父id,获取菜单
	 * @param pid
	 * @return
	 */
	public List<Menu> findByPid(Long pid) {
		
		if (pid == null) {
			pid = Menu.ROOT_ID;
		}
		
		return dao.findByPid(pid);
	}
	
	/**
	 * 根据父Id及用户Id查找所有菜单
	 */
	public List<Menu> findByPidAndUserId(Long pid, Long userId) {
		
		if (pid == null) {
			pid = Menu.ROOT_ID;
		}
		
		return dao.findByPidAndUserId(pid, userId);
	}
	
}
