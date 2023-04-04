package org.cloud.manage.service;

import java.util.List;

import org.cloud.lang.jdbc.PageBounds;
import org.cloud.manage.model.Org;

import com.github.pagehelper.Page;


/**
 * 部门Service 
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2014-10-08 17:35:48
 * @author 
 *		Cloud
 */
public interface OrgService {
	
	/**
	 * 取出父级id下的组织
	 */
	public List<Org> findByParentId(Long pid, Boolean hasChild);
	
	/**
	 * 取出父级id下的组织(分页)
	 */
	public Page<Org> findByParentIdPage(Long pid, PageBounds pageBounds);
	
	/**
	 * 删除组织
	 */
	public void delete(long id);
	
	/**
	 * 获取组织
	 */
	public Org findById(long id);
	
	/**
	 * 更新组织
	 */
	public void update(Org bean);
	
	/**
	 * 添加组织
	 */
	public long add(Org bean);
	
}
