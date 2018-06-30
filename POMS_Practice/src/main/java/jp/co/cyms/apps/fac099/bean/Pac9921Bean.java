package jp.co.cyms.apps.fac099.bean;

import java.io.Serializable;
import java.util.Date;

import jp.co.cyms.base.BaseDBBean;

public class Pac9921Bean extends BaseDBBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String companyCode;
	private Date from;
	private Date to;
	private boolean nonDeliveryFg;
	private boolean expiringOrdersFg;
	
	
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}
	public boolean isNonDeliveryFg() {
		return nonDeliveryFg;
	}
	public void setNonDeliveryFg(boolean nonDeliveryFg) {
		this.nonDeliveryFg = nonDeliveryFg;
	}
	public boolean isExpiringOrdersFg() {
		return expiringOrdersFg;
	}
	public void setExpiringOrdersFg(boolean expiringOrdersFg) {
		this.expiringOrdersFg = expiringOrdersFg;
	}
		
}
