package org.cloud.manage.model;

import java.util.Date;

/**
 * 组织
 * @since 
 *		v1.0
 * @version
 * 		v1.0, 2014-10-09 16:49:15
 * @author 
 *		Cloud
 */
public class Org {

	public static final Long ROOT_ID = -1L;
	
	/**
	 * 主键
	 */
	private long id;
	
	/**
	 * 名称
	 */
	private String name;

	/**
	 * 是否有子节点
	 */
	private boolean hasChild;
	
	/**
	 * 父节点
	 */
	private String parentId;

	/**
	 * 备注
	 */
	private String comments;
	
	/**
	 * 创建日期
	 */
	private Date createDate;

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
	 * 获取是否有子节点
	 * @return 
	 * 		是否有子节点
	 */
	public boolean isHasChild() {
		return hasChild;
	}

	/**
	 * 设置是否有子节点
	 * @param hasChild
	 * 			是否有子节点
	 */
	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

	/**
	 * 获取父节点
	 * @return 
	 * 		父节点
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * 设置父节点
	 * @param parentId
	 * 			父节点
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	/**
	 * 获取创建日期
	 * @return 
	 * 		创建日期
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 设置创建日期
	 * @param createDate
	 * 			创建日期
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
