package org.cloud.manage.model;

import javax.persistence.Id;
import javax.persistence.Table;

import tk.mybatis.mapper.annotation.KeySql;

/**
 * 权限
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2014-10-09 17:04:53
 * @author 
 *		Cloud
 */
@Table(name = "t_priv")
public class Priv {
	
	/**
	 * 主键
	 */
	@Id
	@KeySql(useGeneratedKeys = true)
	private long id;

	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 备注
	 */
	private String comments;

	/**
	 * 获取主键
	 * @return 
	 * 		主键
	 */
	public long getId() {
		return id;
	}

	/**
	 * 设置主键
	 * @param id
	 * 			主键
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 获取名称
	 * @return 
	 * 		名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * @param name
	 * 			名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取备注
	 * @return 
	 * 		备注
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * 设置备注
	 * @param comments
	 * 			备注
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
