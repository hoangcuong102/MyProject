package jp.co.cyms.common.bean;

import java.io.Serializable;

import jp.co.cyms.base.BaseDBBean;

public class ErrorBean extends BaseDBBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String key;
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
