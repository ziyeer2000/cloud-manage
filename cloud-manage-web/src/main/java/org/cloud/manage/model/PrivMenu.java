package org.cloud.manage.model;

/**
 * 权限-菜单关联表
 * @since
 * 		v1.0
 * @version
 * 		v1.0, 2018-08-17 16:50:44
 * @author Cloud
 */
public class PrivMenu {
	
	/**
	 * 类型:菜单
	 */
	public static final int TYPE_MENU = 1;
	
	/**
	 * 类型:按钮
	 */
	public static final int TYPE_BUTTON = 2;
	
	/**
	 * 主键
	 */
	private long id;
	
	/**
	 * 菜单/按钮 ID
	 */
	private long dataId;
	
	/**
	 * 权限ID
	 */
	private long privId;
	
	/**
	 * 类型
	 */
	private int type;

	/** 
	 * 获取主键 
	 * @return id 
	 *		主键 
	 */
	public long getId() {
		return id;
	}

	/** 
	 * 设置主键 
	 * @param id 
	 * 		主键 
	 */
	public void setId(long id) {
		this.id = id;
	}

	/** 
	 * 获取菜单按钮ID 
	 * @return dataId 
	 *		菜单按钮ID 
	 */
	public long getDataId() {
		return dataId;
	}

	/** 
	 * 设置菜单按钮ID 
	 * @param dataId 
	 * 		菜单按钮ID 
	 */
	public void setDataId(long dataId) {
		this.dataId = dataId;
	}

	/** 
	 * 获取权限ID 
	 * @return privId 
	 *		权限ID 
	 */
	public long getPrivId() {
		return privId;
	}

	/** 
	 * 设置权限ID 
	 * @param privId 
	 * 		权限ID 
	 */
	public void setPrivId(long privId) {
		this.privId = privId;
	}

	/** 
	 * 获取类型 
	 * @return type 
	 *		类型 
	 */
	public int getType() {
		return type;
	}

	/** 
	 * 设置类型 
	 * @param type 
	 * 		类型 
	 */
	public void setType(int type) {
		this.type = type;
	}
	
}
