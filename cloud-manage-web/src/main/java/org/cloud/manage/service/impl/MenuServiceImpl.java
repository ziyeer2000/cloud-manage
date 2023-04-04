package org.cloud.manage.service.impl;

import java.util.List;

import org.cloud.lang.jdbc.PageBounds;
import org.cloud.lang.BaseUtil;
import org.cloud.manage.biz.ButtonBiz;
import org.cloud.manage.biz.MenuBiz;
import org.cloud.manage.dao.MenuDao;
import org.cloud.manage.dao.PrivDao;
import org.cloud.manage.model.Menu;
import org.cloud.manage.model.PrivMenu;
import org.cloud.manage.model.vo.MenuQuery;
import org.cloud.manage.service.MenuService;
import org.cloud.manage.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


/**
 * 菜单Service 实现
 * 
 * @since v1.0
 * @version v1.0, 2013-07-29 16:18:04
 * @author Cloud
 */
@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class MenuServiceImpl  implements MenuService {

	@Autowired
	private MenuBiz biz;
	
	@Autowired
	private MenuDao dao;
	
	@Autowired
	private PrivDao privDao;
	
	@Autowired
	private ButtonBiz buttonBiz;

	@Override
	public Page<Menu> findPage(MenuQuery query, PageBounds pageBounds) {

		PageHelper.startPage(pageBounds.getPageNum(), pageBounds.getPageSize(), pageBounds.isContainsTotalCount());
		return dao.findPage(query);
	}

	@Override
	public long insert(Menu menu) {
		
		dao.insert(menu);
		return menu.getId();
	}

	@Override
	public Menu findById(long id) {
		
		return dao.findById(id);
	}

	@Override
	public void update(Menu menu) {
		
		dao.update(menu);
		
		AuthUtil.refreshRole();
	}

	@Override
	public void delete(long id) {
		
		List<Menu> list = dao.findByPid(id);
		if (BaseUtil.isNotEmpty(list)) {
			for (Menu bean : list) {
				delete(bean.getId());
			}
		}
		
		dao.delete(id);
		privDao.deletePrivMenuByMenuId(id, PrivMenu.TYPE_MENU);
		
		
		buttonBiz.removeByMenuId(id);
		
		AuthUtil.refreshRole();
	}

	@Override
	public List<Menu> findByPid(Long pid) {

		return biz.findByPid(pid);
	}

	@Override
	public List<Menu> findByPidAndUserId(Long pid, long userId) {

		return biz.findByPidAndUserId(pid, userId);
	}

	@Override
	public List<Menu> findByPrivId(long privId) {
		
		return dao.findByPrivId(privId);
	}
	
	@Override
	public List<Menu> findByRoleId(long roleId) {
		
		return dao.findByRoleId(roleId);
	}

}
