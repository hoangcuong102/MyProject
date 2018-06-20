package jp.co.cyms.apps.fac001.bean;

import java.io.Serializable;
import java.util.List;

import jp.co.cyms.base.BaseDBBean;

public class CompanyBean extends BaseDBBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String companyCd;
	private String companyName;
	private String attnToName;
	private String ccName;
	private String countryCd;
	private List<DepartmentBean> listDepartment;

	public String getCompanyCd() {
		return companyCd;
	}

	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCountryCd() {
		return countryCd;
	}

	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}

	public List<DepartmentBean> getListDepartment() {
		return listDepartment;
	}

	public void setListDepartment(List<DepartmentBean> listDepartment) {
		this.listDepartment = listDepartment;
	}

	public String getAttnToName() {
		return attnToName;
	}

	public void setAttnToName(String attnToName) {
		this.attnToName = attnToName;
	}

	public String getCcName() {
		return ccName;
	}

	public void setCcName(String ccName) {
		this.ccName = ccName;
	}

}
