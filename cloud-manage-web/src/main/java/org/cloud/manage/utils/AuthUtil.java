package org.cloud.manage.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.cloud.interceptor.def.NeedRoleUrlDefault;
import org.cloud.lang.BaseUtil;
import org.cloud.manage.interceptor.OnlineData;
import org.cloud.manage.model.Button;
import org.cloud.manage.model.Menu;
import org.cloud.manage.model.Role;
import org.cloud.manage.model.User;
import org.cloud.manage.service.ButtonService;
import org.cloud.manage.service.MenuService;
import org.cloud.manage.service.RoleService;
import org.cloud.spring.BeanFactory;
import org.cloud.web.session.Session;
import org.cloud.web.session.SessionInit;
import org.cloud.web.session.service.SessionService;


public class AuthUtil {
	
	private static RoleService roleService;
	
	private static MenuService menuService;

	private static ButtonService buttonService;
	
	private static SessionService sessionService;
	
	public static void init() {
		roleService = BeanFactory.getBean(RoleService.class);
		menuService = BeanFactory.getBean(MenuService.class);
		buttonService = BeanFactory.getBean(ButtonService.class);
		sessionService = SessionInit.getService();
	}

	public static Long getUserId() {
		
		return Session.getCurrent().getUserId();
		
	}
	
	public static void setUser(User user, List<Role> roleList, boolean remember) {

		Session session = Session.getCurrent();
		HttpServletRequest request = session.getRequest();
		String ip = BaseUtil.getIp(request);
		OnlineData data = new OnlineData();
		data.setCreateDate(new Date());
		data.setIp(ip);
		data.setSessionId(session.getId());
		data.setUser(user);
		data.setRemember(remember);
		
		List<Long> roleIdList = new ArrayList<Long>();
		for (Role role : roleList) {
			roleIdList.add(role.getId());
		}
		
		session.saveUser(user.getId());
		session.saveUserRole(roleIdList);
		
		session.put(getSessionFlag(), data);
		
		if (remember) {
			session.remember();
		}
	}
	
	public static void removeUser() {
		
		Session.getCurrent().remove();
	}
	
	public static String getSessionFlag() {
		
		return "onlineData";
	}
	
	/**
	 * 刷新权限
	 */
	public static void refreshRole() {
		
		Map<Long, List<String>> roleMap = new HashMap<Long, List<String>>();
		List<Role> roleList = roleService.findAll();
		for (Role role : roleList) {
			long roleId = role.getId();
			List<String> urlList = new ArrayList<String>();
			
			List<Menu> menuList = menuService.findByRoleId(roleId);
			for (Menu menu : menuList) {
				String url = menu.getUrl();
				if (BaseUtil.isNotEmpty(url)) {
					urlList.add(url);
				}
				List<String> ajaxUrlList = menu.getAjaxUrlList();
				if (BaseUtil.isNotEmpty(ajaxUrlList)) {
					urlList.addAll(ajaxUrlList);
				}
			}
			
			List<Button> buttonList = buttonService.findByRoleId(roleId);
			for (Button button : buttonList) {
				String url = button.getUrl();
				if (BaseUtil.isNotEmpty(url)) {
					urlList.add(url);
				}
				List<String> ajaxUrlList = button.getAjaxUrlList();
				if (BaseUtil.isNotEmpty(ajaxUrlList)) {
					urlList.addAll(ajaxUrlList);
				}
			}
			
			roleMap.put(role.getId(), urlList);
		}
		
		
		
		
		
		NeedRoleUrlDefault.setRoleMap(roleMap);
	}
	
	/**
	 * 强制下线
	 */
	public static void offline(long userId) {
		
		sessionService.remove(userId, null);
	}
	
	/**
	 * 修改用户权限
	 */
	public static void changeUserRole(long userId, List<Long> roleIdList) {
		
		List<Session> sessionList = sessionService.findByUserId(userId);
		if (BaseUtil.isEmpty(sessionList)) {
			return ;
		}
		
		for (Session session : sessionList) {
			session.setHttpParam(Session.getCurrent().getRequest(), Session.getCurrent().getResponse());
			session.saveUserRole(roleIdList);
			sessionService.update(session);
		}
	}
}
