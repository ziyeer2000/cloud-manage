package org.cloud.manage.service.impl;

import java.util.List;

import org.cloud.manage.dao.ButtonDao;
import org.cloud.manage.dao.PrivDao;
import org.cloud.manage.model.Button;
import org.cloud.manage.model.PrivMenu;
import org.cloud.manage.service.ButtonService;
import org.cloud.manage.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

/**
 * 按钮Service 实现
 * @since
 * 		v1.0
 * @version
 * 		v1.0, 2018-08-17 11:41:46
 * @author Cloud
 */
@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class ButtonServiceImpl implements ButtonService {

	@Autowired
	private ButtonDao dao;
	
	@Autowired
	private PrivDao privDao;
	
	@Override
	public Button findById(long id) {
		
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public List<Button> findByMenuId(long menuId) {
		
		Weekend<Button> weekend = Weekend.of(Button.class);
		WeekendCriteria<Button, Object> criteria = weekend.weekendCriteria();
		
		criteria.andEqualTo(Button::getMenuId, menuId);
		
		weekend.orderBy("seq").desc();
		
		return dao.selectByExample(weekend);
	}

	@Override
	public long add(Button bean) {
		
		dao.insert(bean);
		return bean.getId();
	}

	@Override
	public void modify(Button bean) {
		
		dao.updateByPrimaryKeySelective(bean);
		AuthUtil.refreshRole();
	}

	@Override
	public void remove(long id) {
		
		dao.deleteByPrimaryKey(id);
		
		privDao.deletePrivMenuByMenuId(id, PrivMenu.TYPE_BUTTON);
		
		AuthUtil.refreshRole();
	}

	@Override
	public List<Button> findByPrivId(long privId) {
		
		return dao.findByPrivId(privId);
	}
	
	@Override
	public List<Button> findByRoleId(long roleId) {
	
		return dao.findByRoleId(roleId);
	}
	
	@Override
	public List<Button> findByUserIdAndMenuId(long userId, long menuId) {
		
		return dao.findByUserIdAndMenuId(userId, menuId);
	}
}
