package org.cloud.manage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.cloud.interceptor.annotation.NeedLogin;
import org.cloud.interceptor.annotation.NeedRoleUrl;
import org.cloud.lang.StringUtil;
import org.cloud.lang.jdbc.PageBounds;
import org.cloud.manage.model.Priv;
import org.cloud.manage.model.PrivMenu;
import org.cloud.manage.model.vo.PrivQuery;
import org.cloud.manage.service.PrivService;
import org.cloud.manage.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;


@Controller
@RequestMapping("/priv")
public class PrivController {

	@Autowired
	private PrivService service;

	/**
	 * 权限列表
	 */
	@RequestMapping(value = "/findPage", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> findPage(@RequestParam Map<String, String> data, HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		
		PageBounds pageBounds = new PageBounds();
		pageBounds.setContainsTotalCount(true);
		pageBounds.setPageNum(Integer.parseInt(data.get("page")));
		pageBounds.setPageSize(Integer.parseInt(data.get("rows")));
		
		PrivQuery query = new PrivQuery();
		query.setName(data.get("name"));
		
		Page<Priv> page = service.findPage(query, pageBounds);
		map.put("total", page.getTotal());
		map.put("rows", page);
		
		return Constants.returnSuccessReturnMap(map);
	}
	
	@RequestMapping(value = "/ztree/all/check", method = RequestMethod.GET)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> findAllByZtreeCheck(@RequestParam Map<String, String> data, HttpServletRequest request) {

		
		List<Priv> list = service.findAll();
		List<Map<String, Object>> privList = new ArrayList<Map<String, Object>>();
		
		for (Priv priv : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", priv.getId() + "");
			map.put("name", priv.getName());
			map.put("isParent", "false");
			privList.add(map);
		}
		
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("list", privList);
		
		return ret;
	}

	/**
	 * 新增
	 * 
	 * @param data
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> add(@RequestParam Map<String, String> data, HttpServletRequest request) {
		
		Priv bean = new Priv();
		bean.setComments(data.get("comments"));
		bean.setName(data.get("name"));
		
		service.add(bean);
		
		return Constants.standardControllerSuccessReturnMap;
	}

	/**
	 * 权限编辑获取初始值
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findById", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> findById(@RequestParam Map<String, String> data) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bean", service.findById(Long.parseLong(data.get("id"))));
		
		return map;
	}

	/**
	 * 提交权限修改
	 * 
	 * @return
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> modify(@RequestParam Map<String, String> data, HttpServletRequest request) {
		
		Priv bean = new Priv();
		bean.setComments(data.get("comments"));
		bean.setName(data.get("name"));
		bean.setId(Long.parseLong(data.get("id")));
		
		service.update(bean);
		
		return Constants.standardControllerSuccessReturnMap;
	}
	
	/**
	 * 提交权限删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> remove(@RequestParam Map<String, String> data, HttpServletRequest request) {
		
		service.delete(Long.parseLong(data.get("id")));
		
		return Constants.standardControllerSuccessReturnMap;
	}

	/**
	 * 提交权限菜单修改
	 * 
	 * @return
	 */
	@RequestMapping(value = "/changePrivMenu", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> changePrivMenu(@RequestParam Map<String, String> data, HttpServletRequest request) {
		
		List<PrivMenu> list = new ArrayList<PrivMenu>();
		
		String menusStr = data.get("menus");
		long privId = Long.parseLong(data.get("privId"));
		for (String menu : StringUtil.simpleSplit(menusStr, ",")) {
			List<String> menuData = StringUtil.simpleSplit(menu, ":");
			PrivMenu bean = new PrivMenu();
			bean.setDataId(Long.parseLong(menuData.get(0)));
			bean.setPrivId(privId);
			
			if ("menu".equalsIgnoreCase(menuData.get(1))) {
				bean.setType(PrivMenu.TYPE_MENU);
			} else {
				bean.setType(PrivMenu.TYPE_BUTTON);
			}
			
			list.add(bean);
		}
		
		service.changePrivMenu(privId, list);
		
		return Constants.standardControllerSuccessReturnMap;
	}
	
	@RequestMapping(value = "/findByRoleId", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> findByRoleId(@RequestParam Map<String, String> data, HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", service.findByRoleId(Long.parseLong(data.get("roleId"))));
		
		return map;
	}
}
