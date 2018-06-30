package jp.co.cyms.apps.fac099.form;

import java.util.List;

import jp.co.cyms.apps.fac099.bean.OrderProgressBean;
import jp.co.cyms.apps.fac099.bean.Pac9921Bean;
import jp.co.cyms.base.BaseAction;

public class Pac9921Form extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected List<String> company_code;
	
	protected List<OrderProgressBean> data;

	protected Pac9921Bean pac9921Bean;
	
	public List<String> getCompany_code() {
		return company_code;
	}
}
