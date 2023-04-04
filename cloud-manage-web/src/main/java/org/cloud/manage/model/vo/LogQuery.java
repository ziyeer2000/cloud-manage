package org.cloud.manage.model.vo;

import java.util.Date;

import org.cloud.lang.BaseUtil;

/**
 * 日志查询对象
 * @since
 * 		v1.0
 * @version
 * 		v1.0, 2018-08-22 17:35:56
 * @author Cloud
 */
public class LogQuery extends BaseQuery {

	/**
	 * 类型
	 */
	private String type;
	
	/**
	 * 开始日期
	 */
	private Date createDateStart;
	
	/**
	 * 结束日期
	 */
	private Date createDateEnd;

	/** 
	 * 获取类型 
	 * @return type 
	 *		类型 
	 */
	public String getType() {
		return type;
	}

	/** 
	 * 设置类型 
	 * @param type 
	 * 		类型 
	 */
	public void setType(String type) {
		if (BaseUtil.isEmpty(type)) {
			this.type = null;
		} else {
			this.type = type;
		}
	}

	/** 
	 * 获取开始日期 
	 * @return createDateStart 
	 *		开始日期 
	 */
	public Date getCreateDateStart() {
		return createDateStart;
	}

	/** 
	 * 设置开始日期 
	 * @param createDateStart 
	 * 		开始日期 
	 */
	public void setCreateDateStart(Date createDateStart) {
		this.createDateStart = createDateStart;
	}

	/** 
	 * 获取结束日期 
	 * @return createDateEnd 
	 *		结束日期 
	 */
	public Date getCreateDateEnd() {
		return createDateEnd;
	}

	/** 
	 * 设置结束日期 
	 * @param createDateEnd 
	 * 		结束日期 
	 */
	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	public LogQuery() {
		
		setOrderByField(" t.id desc ");
	}
}
