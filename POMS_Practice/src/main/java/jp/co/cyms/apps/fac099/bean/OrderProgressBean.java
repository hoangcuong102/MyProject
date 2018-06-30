package jp.co.cyms.apps.fac099.bean;

import java.util.Date;

public class OrderProgressBean {
	
	private String ORDER_ID;
	private String DEPT_CD;
	private Double TTL_MRC;
	private Double TTL_OTC;
	private String CATEGORY_ABBREV;
	private Integer ITEM_QTY;
	private Date UPDATED_DT_LOCAL;
	private String UPDATED_USER;
	private Date SANKYU_REGIST_DT;
	private Date SANKYU_ORDER_DT;
	private Date SEA_REQUEST_DT;
	private Date KDDI_ACCEPT_DT;
	private Date KDDI_ORDER_DT;
	private Date KDDI_RECEIVE_DT;
	private Integer Total;
	private Date DELIVER_DT;
	private Date KDDI_LEASE_DT;
	private String DELIVER_FG;
	
	public String getDELIVER_FG() {
		return DELIVER_FG;
	}
	public void setDELIVER_FG(String dELIVER_FG) {
		DELIVER_FG = dELIVER_FG;
	}
	public String getORDER_ID() {
		return ORDER_ID;
	}
	public void setORDER_ID(String oRDER_ID) {
		ORDER_ID = oRDER_ID;
	}
	public String getDEPT_CD() {
		return DEPT_CD;
	}
	public void setDEPT_CD(String dEPT_CD) {
		DEPT_CD = dEPT_CD;
	}
	public Double getTTL_MRC() {
		return TTL_MRC;
	}
	public void setTTL_MRC(Double tTL_MRC) {
		TTL_MRC = tTL_MRC;
	}
	public Double getTTL_OTC() {
		return TTL_OTC;
	}
	public void setTTL_OTC(Double tTL_OTC) {
		TTL_OTC = tTL_OTC;
	}
	public String getCATEGORY_ABBREV() {
		return CATEGORY_ABBREV;
	}
	public void setCATEGORY_ABBREV(String cATEGORY_ABBREV) {
		CATEGORY_ABBREV = cATEGORY_ABBREV;
	}
	public Integer getITEM_QTY() {
		return ITEM_QTY;
	}
	public void setITEM_QTY(Integer iTEM_QTY) {
		ITEM_QTY = iTEM_QTY;
	}
	public Date getUPDATED_DT_LOCAL() {
		return UPDATED_DT_LOCAL;
	}
	public void setUPDATED_DT_LOCAL(Date uPDATED_DT_LOCAL) {
		UPDATED_DT_LOCAL = uPDATED_DT_LOCAL;
	}
	public String getUPDATED_USER() {
		return UPDATED_USER;
	}
	public void setUPDATED_USER(String uPDATED_USER) {
		UPDATED_USER = uPDATED_USER;
	}
	public Date getSANKYU_REGIST_DT() {
		return SANKYU_REGIST_DT;
	}
	public void setSANKYU_REGIST_DT(Date sANKYU_REGIST_DT) {
		SANKYU_REGIST_DT = sANKYU_REGIST_DT;
	}
	public Date getSANKYU_ORDER_DT() {
		return SANKYU_ORDER_DT;
	}
	public void setSANKYU_ORDER_DT(Date sANKYU_ORDER_DT) {
		SANKYU_ORDER_DT = sANKYU_ORDER_DT;
	}
	public Date getSEA_REQUEST_DT() {
		return SEA_REQUEST_DT;
	}
	public void setSEA_REQUEST_DT(Date sEA_REQUEST_DT) {
		SEA_REQUEST_DT = sEA_REQUEST_DT;
	}
	public Date getKDDI_ACCEPT_DT() {
		return KDDI_ACCEPT_DT;
	}
	public void setKDDI_ACCEPT_DT(Date kDDI_ACCEPT_DT) {
		KDDI_ACCEPT_DT = kDDI_ACCEPT_DT;
	}
	public Date getKDDI_ORDER_DT() {
		return KDDI_ORDER_DT;
	}
	public void setKDDI_ORDER_DT(Date kDDI_ORDER_DT) {
		KDDI_ORDER_DT = kDDI_ORDER_DT;
	}
	public Date getKDDI_RECEIVE_DT() {
		return KDDI_RECEIVE_DT;
	}
	public void setKDDI_RECEIVE_DT(Date kDDI_RECEIVE_DT) {
		KDDI_RECEIVE_DT = kDDI_RECEIVE_DT;
	}
	public Integer getTotal() {
		return Total;
	}
	public void setTotal(Integer total) {
		Total = total;
	}
	public Date getDELIVER_DT() {
		return DELIVER_DT;
	}
	public void setDELIVER_DT(Date dELIVER_DT) {
		DELIVER_DT = dELIVER_DT;
	}
	public Date getKDDI_LEASE_DT() {
		return KDDI_LEASE_DT;
	}
	public void setKDDI_LEASE_DT(Date kDDI_LEASE_DT) {
		KDDI_LEASE_DT = kDDI_LEASE_DT;
	}
	
}
