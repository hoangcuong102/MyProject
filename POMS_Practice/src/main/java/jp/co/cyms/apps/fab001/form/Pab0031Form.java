package jp.co.cyms.apps.fab001.form;

import java.util.List;

import jp.co.cyms.base.BaseAction;

/**
 * Active Item Master Pab0031Form
 * 
 * @author binhvh
 * @since 2018/01/03
 */
public class Pab0031Form extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String countryCd;
	protected String categoryCd;
	protected List<String> listAllItemCd;
	protected List<String> listActiveItemCd;
	protected List<String> listInactiveItemCd;

	public String getCountryCd() {
		return countryCd;
	}

	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}

	public String getCategoryCd() {
		return categoryCd;
	}

	public void setCategoryCd(String categoryCd) {
		this.categoryCd = categoryCd;
	}

	public List<String> getListAllItemCd() {
		return listAllItemCd;
	}

	public void setListAllItemCd(List<String> listAllItemCd) {
		this.listAllItemCd = listAllItemCd;
	}

	public List<String> getListActiveItemCd() {
		return listActiveItemCd;
	}

	public void setListActiveItemCd(List<String> listActiveItemCd) {
		this.listActiveItemCd = listActiveItemCd;
	}

	public List<String> getListInactiveItemCd() {
		return listInactiveItemCd;
	}

	public void setListInactiveItemCd(List<String> listInactiveItemCd) {
		this.listInactiveItemCd = listInactiveItemCd;
	}
}
