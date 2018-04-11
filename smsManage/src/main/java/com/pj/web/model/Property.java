package com.pj.web.model;

public class Property {
	private Integer id;

	private String parentPath;

	private String propertyName;

	private String propertyKey;

	private String propertyValue;

	private Integer propertyIndex;

	private Integer status;
	
	private Integer disable;

	private String memo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getParentPath() {
		return parentPath;
	}

	public void setParentPath(String parentPath) {
		this.parentPath = parentPath == null ? null : parentPath.trim();
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName == null ? null : propertyName.trim();
	}

	public String getPropertyKey() {
		return propertyKey;
	}

	public void setPropertyKey(String propertyKey) {
		this.propertyKey = propertyKey == null ? null : propertyKey.trim();
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue == null ? null : propertyValue
				.trim();
	}

	public Integer getPropertyIndex() {
		return propertyIndex;
	}

	public void setPropertyIndex(Integer propertyIndex) {
		this.propertyIndex = propertyIndex;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDisable() {
		return disable;
	}

	public void setDisable(Integer disable) {
		this.disable = disable;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo == null ? null : memo.trim();
	}

}