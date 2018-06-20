package jp.co.cyms.apps.fac001.bean;

import java.io.Serializable;

import jp.co.cyms.base.BaseDBBean;

public class ItemBean extends BaseDBBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String itemCd;
	private String itemName;
	private String categoryCd;
	private String itemBrand;
	private Float unitPrice;
	private String countryCd;
	private String warningFg;
	private String imgName;
	private String pdfName;
	private String addonCd;
	private String leaseType;
	private Integer addonConfig;
	private Integer qtyInStock;
	private String existInStock;

	public String getItemCd() {
		return itemCd;
	}

	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getPdfName() {
		return pdfName;
	}

	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}

	public String getCategoryCd() {
		return categoryCd;
	}

	public void setCategoryCd(String categoryCd) {
		this.categoryCd = categoryCd;
	}

	public String getItemBrand() {
		return itemBrand;
	}

	public void setItemBrand(String itemBrand) {
		this.itemBrand = itemBrand;
	}

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getCountryCd() {
		return countryCd;
	}

	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}

	public String getWarningFg() {
		return warningFg;
	}

	public void setWarningFg(String warningFg) {
		this.warningFg = warningFg;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getAddonCd() {
		return addonCd;
	}

	public void setAddonCd(String addonCd) {
		this.addonCd = addonCd;
	}

	public String getLeaseType() {
		return leaseType;
	}

	public void setLeaseType(String leaseType) {
		this.leaseType = leaseType;
	}

	public Integer getAddonConfig() {
		return addonConfig;
	}

	public void setAddonConfig(Integer addonConfig) {
		this.addonConfig = addonConfig;
	}

	public Integer getQtyInStock() {
		return qtyInStock;
	}

	public void setQtyInStock(Integer qtyInStock) {
		this.qtyInStock = qtyInStock;
	}

	public String getExistInStock() {
		return existInStock;
	}

	public void setExistInStock(String existInStock) {
		this.existInStock = existInStock;
	}

}
