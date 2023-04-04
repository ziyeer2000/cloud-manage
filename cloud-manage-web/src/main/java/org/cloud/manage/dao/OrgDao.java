package org.cloud.manage.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cloud.manage.model.Org;

import com.github.pagehelper.Page;


/**
 * 部门Dao
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2014-10-08 17:14:53
 * @author 
 *		Cloud
 */
public interface OrgDao {
	/**
	 * 取出父级id下的组织
	 */
	public List<Org> findByParentId(@Param("pid")long pid, @Param("hasChild")Boolean hasChild);
	
	/**
	 * 取出父级id下的组织(分页)
	 */
	public Page<Org> findByParentIdPage(@Param("pid")long pid);
	
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
	public void add(Org bean);
	
}
