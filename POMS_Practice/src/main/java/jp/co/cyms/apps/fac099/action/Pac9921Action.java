package jp.co.cyms.apps.fac099.action;

import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import jp.co.cyms.apps.fac099.bean.OrderProgressBean;
import jp.co.cyms.apps.fac099.bean.Pac9921Bean;
import jp.co.cyms.apps.fac099.bl.Pac9921BL;
import jp.co.cyms.apps.fac099.form.Pac9921Form;

public class Pac9921Action extends Pac9921Form {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** company code */
	protected String companyCd;

	/** entry date from */
	protected String entryDateFrom;

	/** entry date to */
	protected String entryDateTo;

	/** only Non-Delivery */
	protected boolean nonDelivery;

	/** expiring orders */
	protected boolean expiringOrders;
	
	
	public String execute() {
		company_code = new Pac9921BL().getCompanyCode(getUserInfo());
		return ActionSupport.SUCCESS;
	}
	
	public String tableData() {
		this.pac9921Bean = new Pac9921Bean();
		pac9921Bean.setCompanyCode(this.companyCd);
		data = new Pac9921BL().getOrderProgress(pac9921Bean, getUserInfo());
		return ActionSupport.SUCCESS;
	}
	
	public List<OrderProgressBean> getData() {
		return data;
	}

	public void setPac9921Bean(Pac9921Bean pac9921Bean) {
		this.pac9921Bean = pac9921Bean;
	}

	public String getCompanyCd() {
		return companyCd;
	}

	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

	public String getEntryDateFrom() {
		return entryDateFrom;
	}

	public void setEntryDateFrom(String entryDateFrom) {
		this.entryDateFrom = entryDateFrom;
	}

	public String getEntryDateTo() {
		return entryDateTo;
	}

	public void setEntryDateTo(String entryDateTo) {
		this.entryDateTo = entryDateTo;
	}

	public boolean isNonDelivery() {
		return nonDelivery;
	}

	public void setNonDelivery(boolean nonDelivery) {
		this.nonDelivery = nonDelivery;
	}

	public boolean isExpiringOrders() {
		return expiringOrders;
	}

	public void setExpiringOrders(boolean expiringOrders) {
		this.expiringOrders = expiringOrders;
	}
}
