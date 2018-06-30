package jp.co.cyms.apps.fab991.bean;

import java.io.Serializable;

import jp.co.cyms.base.BaseDBBean;

public class Pab9931CategoryBean extends BaseDBBean implements Serializable {

	/**
	 * created by DuyNK
	 * 21/06/2018 
	 */
	private static final long serialVersionUID = 8880471752126128484L;
	
	private String categoryCD;
	private String categoryName;
	
	public Pab9931CategoryBean() {
		// TODO Auto-generated constructor stub
	}

	public Pab9931CategoryBean(String categoryCD, String categoryName) {
		super();
		this.categoryCD = categoryCD;
		this.categoryName = categoryName;
	}

	public String getCategoryCD() {
		return categoryCD;
	}

	public void setCategoryCD(String categoryCD) {
		this.categoryCD = categoryCD;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
