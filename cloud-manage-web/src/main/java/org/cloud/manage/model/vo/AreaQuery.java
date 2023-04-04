package org.cloud.manage.model.vo;

/**
 * 区域查询对象
 * @since
 * 		v1.0
 * @version
 * 		v1.0, 2021-06-08 17:20:09
 * @author Cloud
 */
public class AreaQuery extends BaseQuery {

    /**
     * 级别
     */
    private Integer level;

    /**
     * 父ID
     */
    private Long    parentId;
    
    /**
     * 是否有效
     */
    private int     isValid = -1;
    
    /**
     * 名称
     */
    private String  name;
    
    
    /**
     * 获取是否有效
     * @return
     */
    public int getIsValid() {
		return isValid;
	}

    /**
     * 设置是否有效
     * @param isValid
     */
	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

	/**
     * 获取级别
     * 
     * @return 级别
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置级别
     * 
     * @param level 级别
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取父ID
     * 
     * @return 父ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父ID
     * 
     * @param parentId 父ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

	/** 
	 * 获取名称
	 * @return name 
	 *		名称
	 */
	public String getName() {
		return name;
	}

	/** 
	 * 设置名称
	 * @param name 
	 * 		名称
	 */
	public void setName(String name) {
		this.name = name;
	}

}
