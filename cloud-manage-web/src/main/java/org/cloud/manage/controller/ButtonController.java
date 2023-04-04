package org.cloud.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cloud.interceptor.annotation.NeedLogin;
import org.cloud.interceptor.annotation.NeedRoleUrl;
import org.cloud.lang.exception.ExceptionUtil;
import org.cloud.manage.model.Button;
import org.cloud.manage.service.ButtonService;
import org.cloud.manage.utils.Constants;
import org.cloud.web.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 按钮Controller
 * @since
 * 		v1.0
 * @version
 * 		v1.0, 2018-08-17 11:45:33
 * @author Cloud
 */
@Controller
@RequestMapping("/button")
public class ButtonController {
	
	private static Log logger = LogFactory.getLog(ButtonController.class);

	@Autowired
	private ButtonService service;
	
	/**
	 * 按钮列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findByMenuId", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> list(@RequestParam Map<String, String> data, HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Button> page = service.findByMenuId(Long.parseLong(data.get("menuId")));
		map.put("total", page.size());
		map.put("rows", page);
		
		return Constants.returnSuccessReturnMap(map);
	}
	
	/**
	 * 新增
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> add(@RequestParam Map<String, String> data, HttpServletRequest request) {
		
		Button bean = new Button();
		bean.setRemark(data.get("remark"));
		bean.setName(data.get("name"));
		bean.setEvent(data.get("event"));
		bean.setSeq(Integer.parseInt(data.get("seq")));
		bean.setUrl(data.get("url"));
		bean.setMenuId(Long.parseLong(data.get("menuId")));
		bean.setAjaxUrls(data.get("ajaxUrls"));
		bean.setType(Integer.parseInt(data.get("type")));
		
		try {
			service.add(bean);
		} catch (Exception e) {
			logger.error(e);
			return Constants.returnFailReturnMap(ExceptionUtil.getRootException(e).getMessage());
		}
		
		return Constants.standardControllerSuccessReturnMap;
	}
	
	/**
	 * 根据主键查询
	 */
	@RequestMapping(value = "/findById", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> findById(@RequestParam Map<String, String> data) {
		
		long id = Long.parseLong(data.get("id"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bean", service.findById(id));
		
		return map;
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> modify(@RequestParam Map<String, String> data, HttpServletRequest request) {
		
		Button bean = new Button();
		bean.setRemark(data.get("remark"));
		bean.setName(data.get("name"));
		bean.setEvent(data.get("event"));
		bean.setSeq(Integer.parseInt(data.get("seq")));
		bean.setUrl(data.get("url"));
		bean.setAjaxUrls(data.get("ajaxUrls"));
		bean.setType(Integer.parseInt(data.get("type")));
		bean.setMenuId(Long.parseLong(data.get("menuId")));
		bean.setId(Long.parseLong(data.get("id")));
		
		try {
			service.modify(bean);
			return Constants.standardControllerSuccessReturnMap;
		} catch (Exception e) {
			logger.error(e);
			return Constants.returnFailReturnMap(ExceptionUtil.getRootException(e).getMessage());
		}
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> remove(@RequestParam Map<String, String> data, HttpServletRequest request) {
		
		
		try {
			this.service.remove(Long.parseLong(data.get("id")));
			return Constants.standardControllerSuccessReturnMap;
		} catch (Exception e) {
			logger.error(e);
			return Constants.returnFailReturnMap(ExceptionUtil.getRootException(e).getMessage());
		}
	}
	
	/**
	 * 获取权限选中的按钮
	 */
	@RequestMapping(value = "/findByPrivId", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> findByPrivId(@RequestParam Map<String, String> data, HttpServletRequest request) {

		Long privId = Long.parseLong(data.get("privId"));
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("list", service.findByPrivId(privId));
		
		return result;
	}
	
	/**
	 * 获取我的能操作的按钮
	 */
	@RequestMapping(value = "/findMy", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	public Map<String, Object> findMy(@RequestParam Map<String, String> data, HttpServletRequest request) {

		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("list", service.findByUserIdAndMenuId(Session.getCurrent().getUserId(), Long.parseLong(data.get("menuId"))));
		
		return result;
	}
}
