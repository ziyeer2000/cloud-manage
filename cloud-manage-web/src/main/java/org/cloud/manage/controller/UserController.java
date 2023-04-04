package org.cloud.manage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.LogFactory;
import org.cloud.interceptor.annotation.NeedLogin;
import org.cloud.interceptor.annotation.NeedRoleUrl;
import org.cloud.lang.BaseUtil;
import org.cloud.lang.StringUtil;
import org.cloud.lang.exception.CheckDataException;
import org.cloud.lang.exception.ExceptionUtil;
import org.cloud.lang.jdbc.PageBounds;
import org.cloud.lang.security.DigestAlgorithm;
import org.cloud.lang.security.DigestUtil;
import org.cloud.manage.model.Log;
import org.cloud.manage.model.User;
import org.cloud.manage.model.vo.UserQuery;
import org.cloud.manage.service.LogService;
import org.cloud.manage.service.RoleService;
import org.cloud.manage.service.UserService;
import org.cloud.manage.utils.AuthUtil;
import org.cloud.manage.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;

@Controller
@RequestMapping("/user")
public class UserController {

	private static org.apache.commons.logging.Log logger = LogFactory.getLog(UserController.class);
	
	@Autowired
	private UserService service;
	
	@Autowired
	private LogService logService;

	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
    @ResponseBody
	public Map<String,Object> login(User loginUser, HttpServletRequest request,HttpServletResponse response){
    	
        Map<String,Object> result = new HashMap<String,Object>();
        String autoLogin = request.getParameter("autoLogin");
        
		try {
			User user = service.findUserByName(loginUser.getUserName());
			
			if (user != null && user.getPassword().equals(DigestUtil.getDigest(DigestAlgorithm.SHA_256, loginUser.getPassword()))) {
				
				AuthUtil.setUser(user, roleService.findByUserId(user.getId()), "on".equals(autoLogin));
				
				Log bean = new Log();
				bean.setCreateDate(new Date());
				bean.setIp(BaseUtil.getIp(request));
				bean.setRemark("登录成功");
				bean.setType(Log.TYPE_LOGIN);
				bean.setUserId(user.getId());
				bean.setUserName(user.getUserName());
				
				logService.add(bean);

				result.put("redirectURL", Constants.SYSTEM_PATH + "/index");
				result.put("result", Constants.RESULT_FLAG_SUCCESS);
				result.put("userName", user.getRealName());
			}else{
				Log bean = new Log();
				bean.setCreateDate(new Date());
				bean.setIp(BaseUtil.getIp(request));
				bean.setRemark("登录失败");
				bean.setType(Log.TYPE_LOGIN);
				bean.setUserName(loginUser.getUserName());
				
				logService.add(bean);
				
				result.put("result", Constants.RESULT_FLAG_FAILURE);
			}
			
		} catch (Exception e) {
			logger.error("account login error...",e);
            result.put("result", Constants.RESULT_FLAG_FAILURE);
		}
        return result;
	}
	
	@RequestMapping(value = "/logout")
	@ResponseBody
	@NeedLogin
	public Map<String, Object> logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();
		
		AuthUtil.removeUser();
		
		result.put("redirectURL", Constants.SYSTEM_PATH);
		
		return result;
	}
	
	@RequestMapping(value = "/changePassword")
	@ResponseBody
	@NeedLogin
	public Map<String, Object> changePassword(@RequestParam Map<String, String> data, HttpServletRequest request) throws Exception {

		String orgPassword = data.get("orgPassword");
		String newPassword = data.get("newPassword");
		String newPasswordAgain = data.get("newPasswordAgain");
		
		if (!newPassword.equals(newPasswordAgain)) {
			throw new RuntimeException("两次密码不一致");
		}
		
		boolean success = service.changePassword(AuthUtil.getUserId(), orgPassword, newPassword);
		
		if (success) {
			return Constants.standardControllerSuccessReturnMap;
		} else {
			return Constants.returnFailReturnMap("原始密码不正确");
		}
	}
	
	@RequestMapping(value = "/changePassworAdmin")
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> changePassworAdmin(@RequestParam Map<String, String> data, HttpServletRequest request) throws Exception {

		String newPassword = data.get("newPassword");
		String newPasswordAgain = data.get("newPasswordAgain");
		
		if (!newPassword.equals(newPasswordAgain)) {
			return Constants.returnFailReturnMap("两次密码不一致");
		}
		
		service.changePasswordAdmin(Long.parseLong(data.get("id")), newPassword);
		return Constants.standardControllerSuccessReturnMap;
	}
	
	@RequestMapping(value = "/getUserInfo")
	@ResponseBody
	@NeedLogin
	public Map<String, Object> getUserInfo(@RequestParam Map<String, String> data, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("user", service.findUserById(AuthUtil.getUserId()));
		
		return Constants.returnSuccessReturnMap(map);
	}
	
	@RequestMapping(value = "/findPage")
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> findPage(@RequestParam Map<String, String> data, HttpServletRequest request) throws Exception {
		
		PageBounds pageBounds = new PageBounds();
		pageBounds.setContainsTotalCount(true);
		pageBounds.setPageNum(Integer.parseInt(data.get("page")));
		pageBounds.setPageSize(Integer.parseInt(data.get("rows")));
		
		UserQuery query = new UserQuery();
		query.setRealName(data.get("realName"));
		query.setUserName(data.get("userName"));
		query.setOrgId(BaseUtil.isEmpty(data.get("orgId")) ? null : Long.parseLong(data.get("orgId")));
		
		Page<User> page = service.findPage(query, pageBounds);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", page);
		map.put("total", page.getTotal());
		
		return Constants.returnSuccessReturnMap(map);
	}
	
	@RequestMapping(value = "/add")
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> add(@RequestParam Map<String, String> data, HttpServletRequest request) throws Exception {
		
		User user = new User();
		user.setRealName(data.get("realName").trim());
		user.setUserName(data.get("userName").trim());
		user.setOrgId(Long.parseLong(data.get("orgId")));
		user.setPassword(Constants.PASSWORD);
		
		try {
			service.add(user);
			return Constants.standardControllerSuccessReturnMap;
		} catch (CheckDataException e) {
			Map<String, Object> map = new HashMap<String, Object>();
	    	map.put(Constants.OPERATOR_MESSAGE, ExceptionUtil.getRootException(e).getMessage());
	    	return Constants.returnSuccessReturnMap(map);
		} catch (Exception e) {
			logger.error(e);
			return Constants.returnFailReturnMap(ExceptionUtil.getRootException(e).getMessage());
		}
	}
	
	@RequestMapping(value = "/findById")
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> findById(@RequestParam Map<String, String> data, HttpServletRequest request) throws Exception {
		
		Long userId = Long.parseLong(data.get("id"));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bean", service.findUserById(userId));
		
		return Constants.returnSuccessReturnMap(map);
	}
	
	@RequestMapping(value = "/modify")
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> modify(@RequestParam Map<String, String> data, HttpServletRequest request) throws Exception {
		
		try {
			User user = new User();
			user.setId(Long.parseLong(data.get("id")));
			user.setRealName(data.get("realName").trim());
			user.setUserName(data.get("userName").trim());
			user.setOrgId(Long.parseLong(data.get("orgId")));
			
			service.modify(user);
			return Constants.standardControllerSuccessReturnMap;
		} catch (CheckDataException e) {
			Map<String, Object> map = new HashMap<String, Object>();
	    	map.put(Constants.OPERATOR_MESSAGE, ExceptionUtil.getRootException(e).getMessage());
	    	return Constants.returnSuccessReturnMap(map);
		} catch (Exception e) {
			logger.error(e);
			return Constants.returnFailReturnMap(ExceptionUtil.getRootException(e).getMessage());
		}
		
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> remove(@RequestParam Map<String, String> data, HttpServletRequest request) throws Exception {
		
		
		try {
			Long id = Long.parseLong(data.get("id"));
			service.remove(id);
			return Constants.standardControllerSuccessReturnMap;
		} catch (Exception e) {
			return Constants.returnFailReturnMap(ExceptionUtil.getRootException(e).getMessage());
		}
	}
	
	@RequestMapping(value = "/changeUserRole", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> changeUserRole(@RequestParam Map<String, String> data, HttpServletRequest request) {
		
		List<Long> roleIds = BaseUtil.isEmpty(data.get("roleIds")) ? 
							 null : StringUtil.toLongList(data.get("roleIds").split(","));
		
		service.changeUserRole(Long.parseLong(data.get("userId")), roleIds);
		
		return Constants.standardControllerSuccessReturnMap;
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> resetPassword(@RequestParam Map<String, String> data, HttpServletRequest request) {
		
		service.resetPassword(Long.parseLong(data.get("userId")));
		
		return Constants.standardControllerSuccessReturnMap;
	}
	
	/**
	 * 验证用户名是否可用
	 */
	@RequestMapping(value = "/checkUserName", method = RequestMethod.POST)
	@ResponseBody
	@NeedLogin
	@NeedRoleUrl
	public Map<String, Object> checkUserName(@RequestParam Map<String, String> data, HttpServletRequest request) {
		
		Map<String, Object> ret = new HashMap<String, Object>();
		
		ret.put("pass", service.checkUserName(data.get("userName")));
		ret.put("message", "用户名已存在");
		
		return Constants.returnSuccessReturnMap(ret);
	}
}
