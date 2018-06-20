package jp.co.cyms.apps.fac001.form;

import java.util.List;

import jp.co.cyms.apps.fac001.bean.Pac0022Bean;
import jp.co.cyms.base.BaseAction;

public class Pac0022Form extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected List<Pac0022Bean> listOrderLease;

	public List<Pac0022Bean> getListOrderLease() {
		return listOrderLease;
	}

	public void setListOrderLease(List<Pac0022Bean> listOrderLease) {
		this.listOrderLease = listOrderLease;
	}
	
}
