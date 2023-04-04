package org.cloud.manage.storage;

import java.util.Map;

import javax.servlet.ServletConfig;

import org.cloud.lang.file.FileUtil;
import org.cloud.lang.LangProperties;
import org.cloud.lang.init.InitAware;
import org.cloud.lang.init.InitParam;

@InitParam(path="/cloud-upload.properties")
public class StorageInit implements InitAware {

	@Override
	public void init(Map<String, Object> paramMap) throws Exception {
		
		ServletConfig sc = (ServletConfig) paramMap.get(LangProperties.SEVLET_CONFIG_NAME);

		//获取系统路径
		Constants.ROOT_PATH = FileUtil.formatPath(sc.getServletContext().getRealPath("/"));
		Constants.SYSTEM_PATH = sc.getServletContext().getContextPath();
		Constants.UPLOAD_PATH = Constants.ROOT_PATH + paramMap.get("cloud.simple.upload.rootPath").toString();
	}

	@Override
	public void destroy(Map<String, Object> paramMap) throws Exception {
		
		
	}
	
}
