package jp.co.cyms.apps.fac001.bean;

import java.io.Serializable;

import jp.co.cyms.base.BaseDBBean;

public class DepartmentBean extends BaseDBBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String deptCd;
	private String deptName;
	private String companyCd;

	public String getDeptCd() {
		return deptCd;
	}

	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getCompanyCd() {
		return companyCd;
	}

	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}
}
