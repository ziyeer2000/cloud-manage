package org.cloud.manage.biz;

import org.cloud.manage.dao.ButtonDao;
import org.cloud.manage.model.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

/**
 * 按钮Biz
 * @since
 * 		v1.0
 * @version
 * 		v1.0, 2018-12-28 20:47:39
 * @author Cloud
 */
@Component
public class ButtonBiz {

	@Autowired
	private ButtonDao dao;
	
	/**
	 * 根据菜单ID,删除按钮
	 */
	public void removeByMenuId(long menuId) {
		
		Weekend<Button> weekend = Weekend.of(Button.class);
		WeekendCriteria<Button, Object> criteria = weekend.weekendCriteria();
		
		criteria.andEqualTo(Button::getMenuId, menuId);
		
		dao.deleteByExample(weekend);
	}
}
