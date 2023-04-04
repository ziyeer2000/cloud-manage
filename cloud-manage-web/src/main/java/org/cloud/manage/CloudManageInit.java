package org.cloud.manage;

import java.util.Map;

import javax.servlet.ServletConfig;

import org.cloud.interceptor.def.CheckBelongToMeDefault;
import org.cloud.interceptor.def.NeedLoginDefault;
import org.cloud.interceptor.def.NeedRoleUrlDefault;
import org.cloud.lang.file.FileUtil;
import org.cloud.lang.LangProperties;
import org.cloud.lang.init.InitAware;
import org.cloud.lang.init.InitParam;
import org.cloud.manage.utils.AuthUtil;
import org.cloud.manage.utils.Constants;
import org.cloud.spring.BeanFactory;
import org.cloud.web.session.SessionProperties;
import org.cloud.web.session.service.AutoLoginService;

@InitParam(path="/config.properties")
public class CloudManageInit implements InitAware {

	@Override
	public void init(Map<String, Object> paramMap) throws Exception {
		
		ServletConfig sc = (ServletConfig) paramMap.get(LangProperties.SEVLET_CONFIG_NAME);

		//获取自动登录Service
		SessionProperties.setAutoLoginService(BeanFactory.getBean(AutoLoginService.class));
		
		//获取系统路径
		Constants.ROOT_PATH = FileUtil.formatPath(sc.getServletContext().getRealPath("/"));
		Constants.SYSTEM_PATH = sc.getServletContext().getContextPath();
		
		//获取登录验证失败跳转地址
		NeedLoginDefault.redirectUrl = (String)paramMap.get(Constants.NEED_LOGIN_REDIRECT_URL_NAME);
		NeedLoginDefault.redirectPage = (String)paramMap.get(Constants.NEED_LOGIN_REDIRECT_PAGE_NAME);
		
		//获取权限验证失败跳转地址
		NeedRoleUrlDefault.redirectUrl = (String)paramMap.get(Constants.NEED_LOGIN_REDIRECT_URL_NAME);
		NeedRoleUrlDefault.redirectPage = (String)paramMap.get(Constants.NEED_LOGIN_REDIRECT_PAGE_NAME);
		
		//获取数据权限验证失败跳转地址
		CheckBelongToMeDefault.redirectUrl = (String)paramMap.get(Constants.NEED_LOGIN_REDIRECT_URL_NAME);
		CheckBelongToMeDefault.redirectPage = (String)paramMap.get(Constants.NEED_LOGIN_REDIRECT_PAGE_NAME);
				
		//初始化权限
		AuthUtil.init();
		AuthUtil.refreshRole();
	}

	@Override
	public void destroy(Map<String, Object> paramMap) throws Exception {
		
		
	}
	
}
