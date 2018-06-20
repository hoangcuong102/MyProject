package jp.co.cyms.apps.fad001.bean;

import java.io.Serializable;

import jp.co.cyms.base.BaseBean;
import jp.co.cyms.common.Constant;

public class ReportYearlyBean extends BaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5246575636910915245L;
	private String deptCd;
	private String deptName;
	private String companyCd;
	private String companyName;
	private String orderId;
	private String categoryCd;
	private String categoryName;
	private String addonCd;
	private String addonName;
	private String itemCd;
	private String itemName;

	private String billableQuantity01;
	private String billableQuantity02;
	private String billableQuantity03;
	private String billableQuantity04;
	private String billableQuantity05;
	private String billableQuantity06;
	private String billableQuantity07;
	private String billableQuantity08;
	private String billableQuantity09;
	private String billableQuantity10;
	private String billableQuantity11;
	private String billableQuantity12;

	private String deliverQuantity01;
	private String deliverQuantity02;
	private String deliverQuantity03;
	private String deliverQuantity04;
	private String deliverQuantity05;
	private String deliverQuantity06;
	private String deliverQuantity07;
	private String deliverQuantity08;
	private String deliverQuantity09;
	private String deliverQuantity10;
	private String deliverQuantity11;
	private String deliverQuantity12;

	private String billableAmount01;
	private String billableAmount02;
	private String billableAmount03;
	private String billableAmount04;
	private String billableAmount05;
	private String billableAmount06;
	private String billableAmount07;
	private String billableAmount08;
	private String billableAmount09;
	private String billableAmount10;
	private String billableAmount11;
	private String billableAmount12;

	private String deliverAmount01;
	private String deliverAmount02;
	private String deliverAmount03;
	private String deliverAmount04;
	private String deliverAmount05;
	private String deliverAmount06;
	private String deliverAmount07;
	private String deliverAmount08;
	private String deliverAmount09;
	private String deliverAmount10;
	private String deliverAmount11;
	private String deliverAmount12;
	
	private String costType;

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCategoryCd() {
		return categoryCd;
	}

	public void setCategoryCd(String categoryCd) {
		this.categoryCd = categoryCd;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getAddonCd() {
		return addonCd;
	}

	public void setAddonCd(String addonCd) {
		this.addonCd = addonCd;
	}

	public String getAddonName() {
		return addonName;
	}

	public void setAddonName(String addonName) {
		this.addonName = addonName;
	}

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

	public String getBillableQuantity01() {
		return billableQuantity01;
	}

	public void setBillableQuantity01(String billableQuantity01) {
		this.billableQuantity01 = billableQuantity01;
	}

	public String getBillableQuantity02() {
		return billableQuantity02;
	}

	public void setBillableQuantity02(String billableQuantity02) {
		this.billableQuantity02 = billableQuantity02;
	}

	public String getBillableQuantity03() {
		return billableQuantity03;
	}

	public void setBillableQuantity03(String billableQuantity03) {
		this.billableQuantity03 = billableQuantity03;
	}

	public String getBillableQuantity04() {
		return billableQuantity04;
	}

	public void setBillableQuantity04(String billableQuantity04) {
		this.billableQuantity04 = billableQuantity04;
	}

	public String getBillableQuantity05() {
		return billableQuantity05;
	}

	public void setBillableQuantity05(String billableQuantity05) {
		this.billableQuantity05 = billableQuantity05;
	}

	public String getBillableQuantity06() {
		return billableQuantity06;
	}

	public void setBillableQuantity06(String billableQuantity06) {
		this.billableQuantity06 = billableQuantity06;
	}

	public String getBillableQuantity07() {
		return billableQuantity07;
	}

	public void setBillableQuantity07(String billableQuantity07) {
		this.billableQuantity07 = billableQuantity07;
	}

	public String getBillableQuantity08() {
		return billableQuantity08;
	}

	public void setBillableQuantity08(String billableQuantity08) {
		this.billableQuantity08 = billableQuantity08;
	}

	public String getBillableQuantity09() {
		return billableQuantity09;
	}

	public void setBillableQuantity09(String billableQuantity09) {
		this.billableQuantity09 = billableQuantity09;
	}

	public String getBillableQuantity10() {
		return billableQuantity10;
	}

	public void setBillableQuantity10(String billableQuantity10) {
		this.billableQuantity10 = billableQuantity10;
	}

	public String getBillableQuantity11() {
		return billableQuantity11;
	}

	public void setBillableQuantity11(String billableQuantity11) {
		this.billableQuantity11 = billableQuantity11;
	}

	public String getBillableQuantity12() {
		return billableQuantity12;
	}

	public void setBillableQuantity12(String billableQuantity12) {
		this.billableQuantity12 = billableQuantity12;
	}

	public String getDeliverQuantity01() {
		return deliverQuantity01;
	}

	public void setDeliverQuantity01(String deliverQuantity01) {
		this.deliverQuantity01 = deliverQuantity01;
	}

	public String getDeliverQuantity02() {
		return deliverQuantity02;
	}

	public void setDeliverQuantity02(String deliverQuantity02) {
		this.deliverQuantity02 = deliverQuantity02;
	}

	public String getDeliverQuantity03() {
		return deliverQuantity03;
	}

	public void setDeliverQuantity03(String deliverQuantity03) {
		this.deliverQuantity03 = deliverQuantity03;
	}

	public String getDeliverQuantity04() {
		return deliverQuantity04;
	}

	public void setDeliverQuantity04(String deliverQuantity04) {
		this.deliverQuantity04 = deliverQuantity04;
	}

	public String getDeliverQuantity05() {
		return deliverQuantity05;
	}

	public void setDeliverQuantity05(String deliverQuantity05) {
		this.deliverQuantity05 = deliverQuantity05;
	}

	public String getDeliverQuantity06() {
		return deliverQuantity06;
	}

	public void setDeliverQuantity06(String deliverQuantity06) {
		this.deliverQuantity06 = deliverQuantity06;
	}

	public String getDeliverQuantity07() {
		return deliverQuantity07;
	}

	public void setDeliverQuantity07(String deliverQuantity07) {
		this.deliverQuantity07 = deliverQuantity07;
	}

	public String getDeliverQuantity08() {
		return deliverQuantity08;
	}

	public void setDeliverQuantity08(String deliverQuantity08) {
		this.deliverQuantity08 = deliverQuantity08;
	}

	public String getDeliverQuantity09() {
		return deliverQuantity09;
	}

	public void setDeliverQuantity09(String deliverQuantity09) {
		this.deliverQuantity09 = deliverQuantity09;
	}

	public String getDeliverQuantity10() {
		return deliverQuantity10;
	}

	public void setDeliverQuantity10(String deliverQuantity10) {
		this.deliverQuantity10 = deliverQuantity10;
	}

	public String getDeliverQuantity11() {
		return deliverQuantity11;
	}

	public void setDeliverQuantity11(String deliverQuantity11) {
		this.deliverQuantity11 = deliverQuantity11;
	}

	public String getDeliverQuantity12() {
		return deliverQuantity12;
	}

	public void setDeliverQuantity12(String deliverQuantity12) {
		this.deliverQuantity12 = deliverQuantity12;
	}

	public String getBillableAmount01() {
		return billableAmount01;
	}

	public void setBillableAmount01(String billableAmount01) {
		this.billableAmount01 = billableAmount01;
	}

	public String getBillableAmount02() {
		return billableAmount02;
	}

	public void setBillableAmount02(String billableAmount02) {
		this.billableAmount02 = billableAmount02;
	}

	public String getBillableAmount03() {
		return billableAmount03;
	}

	public void setBillableAmount03(String billableAmount03) {
		this.billableAmount03 = billableAmount03;
	}

	public String getBillableAmount04() {
		return billableAmount04;
	}

	public void setBillableAmount04(String billableAmount04) {
		this.billableAmount04 = billableAmount04;
	}

	public String getBillableAmount05() {
		return billableAmount05;
	}

	public void setBillableAmount05(String billableAmount05) {
		this.billableAmount05 = billableAmount05;
	}

	public String getBillableAmount06() {
		return billableAmount06;
	}

	public void setBillableAmount06(String billableAmount06) {
		this.billableAmount06 = billableAmount06;
	}

	public String getBillableAmount07() {
		return billableAmount07;
	}

	public void setBillableAmount07(String billableAmount07) {
		this.billableAmount07 = billableAmount07;
	}

	public String getBillableAmount08() {
		return billableAmount08;
	}

	public void setBillableAmount08(String billableAmount08) {
		this.billableAmount08 = billableAmount08;
	}

	public String getBillableAmount09() {
		return billableAmount09;
	}

	public void setBillableAmount09(String billableAmount09) {
		this.billableAmount09 = billableAmount09;
	}

	public String getBillableAmount10() {
		return billableAmount10;
	}

	public void setBillableAmount10(String billableAmount10) {
		this.billableAmount10 = billableAmount10;
	}

	public String getBillableAmount11() {
		return billableAmount11;
	}

	public void setBillableAmount11(String billableAmount11) {
		this.billableAmount11 = billableAmount11;
	}

	public String getBillableAmount12() {
		return billableAmount12;
	}

	public void setBillableAmount12(String billableAmount12) {
		this.billableAmount12 = billableAmount12;
	}

	public String getDeliverAmount01() {
		return deliverAmount01;
	}

	public void setDeliverAmount01(String deliverAmount01) {
		this.deliverAmount01 = deliverAmount01;
	}

	public String getDeliverAmount02() {
		return deliverAmount02;
	}

	public void setDeliverAmount02(String deliverAmount02) {
		this.deliverAmount02 = deliverAmount02;
	}

	public String getDeliverAmount03() {
		return deliverAmount03;
	}

	public void setDeliverAmount03(String deliverAmount03) {
		this.deliverAmount03 = deliverAmount03;
	}

	public String getDeliverAmount04() {
		return deliverAmount04;
	}

	public void setDeliverAmount04(String deliverAmount04) {
		this.deliverAmount04 = deliverAmount04;
	}

	public String getDeliverAmount05() {
		return deliverAmount05;
	}

	public void setDeliverAmount05(String deliverAmount05) {
		this.deliverAmount05 = deliverAmount05;
	}

	public String getDeliverAmount06() {
		return deliverAmount06;
	}

	public void setDeliverAmount06(String deliverAmount06) {
		this.deliverAmount06 = deliverAmount06;
	}

	public String getDeliverAmount07() {
		return deliverAmount07;
	}

	public void setDeliverAmount07(String deliverAmount07) {
		this.deliverAmount07 = deliverAmount07;
	}

	public String getDeliverAmount08() {
		return deliverAmount08;
	}

	public void setDeliverAmount08(String deliverAmount08) {
		this.deliverAmount08 = deliverAmount08;
	}

	public String getDeliverAmount09() {
		return deliverAmount09;
	}

	public void setDeliverAmount09(String deliverAmount09) {
		this.deliverAmount09 = deliverAmount09;
	}

	public String getDeliverAmount10() {
		return deliverAmount10;
	}

	public void setDeliverAmount10(String deliverAmount10) {
		this.deliverAmount10 = deliverAmount10;
	}

	public String getDeliverAmount11() {
		return deliverAmount11;
	}

	public void setDeliverAmount11(String deliverAmount11) {
		this.deliverAmount11 = deliverAmount11;
	}

	public String getDeliverAmount12() {
		return deliverAmount12;
	}

	public void setDeliverAmount12(String deliverAmount12) {
		this.deliverAmount12 = deliverAmount12;
	}

}
