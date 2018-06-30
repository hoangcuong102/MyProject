package jp.co.cyms.apps.fab991.action;

import java.util.List;

import jp.co.cyms.apps.fab991.bean.Pab9931CategoryBean;
import jp.co.cyms.apps.fab991.bean.Pab9931CountryBean;
import jp.co.cyms.apps.fab991.bean.Pab9931ItemBean;
import jp.co.cyms.apps.fab991.bl.Pab9931BL;
import jp.co.cyms.apps.fab991.form.Pab9931Form;
import jp.co.cyms.common.Constant;


public class Pab9931Action extends Pab9931Form {
	/**
	 * 
	 */
	private static final long serialVersionUID = 975390418282394459L;
	
	private List<Pab9931CountryBean> listCountry;
	private List<Pab9931CategoryBean> listCategory;
	private List<Pab9931ItemBean> listAllItem;
	private List<Pab9931ItemBean> listItemActive;
	private List<Pab9931ItemBean> listItemInactive;
	
	//page load -- get list all country and category for ComboBox
	@Override
	public String execute() throws Exception {
		LOG.info("*************execute Start SAB9931*************", "");
		Pab9931BL bl = new Pab9931BL();
		listCountry = bl.getAllCountry();
		listCategory = bl.getAllCategory();
		LOG.info("*************execute End SAB9931*************", "");
		return SUCCESS;
	}

	//call ajax when category or country in ComboBox change
	public String DataItem() throws Exception {
		LOG.info("*************execute Start getDataItem SAB9931*************", "");
		Pab9931ItemBean item = new Pab9931ItemBean(getCategoryCD(),getCountryCD(),"","","");
		Pab9931BL bl = new Pab9931BL();
		listAllItem = bl.getAllItem(item);
		listItemActive = bl.getItemActive(item);
		listItemInactive = bl.getItemInactive(item);
		LOG.info("*************execute End getDataItem SAB9931*************", "");
		return SUCCESS;
	}
	
	//call ajax when click button SAVE to save data
	public String saveDataItem() throws Exception {
		LOG.info("*************execute Start SaveData SAB9931*************", "");
		Pab9931BL bl = new Pab9931BL();

		//list new
		List<String> listItemActiveCD = getListActiveItemCD();
		List<String> listItemInactiveCD = getListInactiveItemCD();
		
		if(listItemActiveCD == null || listItemActiveCD.size() == 0 || listItemActiveCD.get(0).trim().equals("")) {
			bl.deleteItem(new Pab9931ItemBean(getCategoryCD(),getCountryCD(),"","","1"));
		}else {
			//save data for list active item
			//+keep item old no changed
			Pab9931ItemBean itemAc = new Pab9931ItemBean(getCategoryCD(), getCountryCD(), "", "", "1");
			for (int i = 0; i < listItemActiveCD.size(); i++) {
				itemAc.setItemCD(listItemActiveCD.get(i));
				if(bl.isExist(itemAc)) {
					bl.updateItemToX(itemAc);
					listItemActiveCD.remove(listItemActiveCD.get(i));
					i--;
				}
			}
			//+delete other item
			bl.deleteItem(new Pab9931ItemBean(getCategoryCD(),getCountryCD(),"","","1"));
			bl.updateItem(new Pab9931ItemBean(getCategoryCD(),getCountryCD(),"","","1"));
			//+insert new item
			for (int i = 0; i < listItemActiveCD.size(); i++) {
				Pab9931ItemBean itemBean = new Pab9931ItemBean(getCategoryCD(), getCountryCD(), listItemActiveCD.get(i), "", "1");
				itemBean.setUpdatedUser(super.getUpdatedUser());
				itemBean.setUpdatedDtLocal(super.getUpdatedDtLocal());
				itemBean.setTimeDifference(super.getTimeDifference());
				itemBean.setExclusiveFg(Constant.EXCLUSIVE_FG);
				bl.insertItem(itemBean);
			}
		}
		
		if(listItemInactiveCD == null || listItemInactiveCD.size() == 0|| listItemInactiveCD.get(0).equals("")) {
			bl.deleteItem(new Pab9931ItemBean(getCategoryCD(),getCountryCD(),"","","0"));
		}else {
			//save data for list active item
			//+keep item old no changed
			Pab9931ItemBean itemInac = new Pab9931ItemBean(getCategoryCD(), getCountryCD(), "", "", "0");
			for (int i = 0; i < listItemInactiveCD.size(); i++) {
				itemInac.setItemCD(listItemInactiveCD.get(i));
				if(bl.isExist(itemInac)) {
					bl.updateItemToX(itemInac);
					listItemInactiveCD.remove(listItemInactiveCD.get(i));
					i--;
				}
			}
			//+delete other item
			bl.deleteItem(new Pab9931ItemBean(getCategoryCD(),getCountryCD(),"","","0"));
			bl.updateItem(new Pab9931ItemBean(getCategoryCD(),getCountryCD(),"","","0"));
			//+insert new item
			for (int i = 0; i < listItemInactiveCD.size(); i++) {
				Pab9931ItemBean itemBean = new Pab9931ItemBean(getCategoryCD(), getCountryCD(), listItemInactiveCD.get(i), "", "0");
				itemBean.setUpdatedUser(super.getUpdatedUser());
				itemBean.setUpdatedDtLocal(super.getUpdatedDtLocal());
				itemBean.setTimeDifference(super.getTimeDifference());
				itemBean.setExclusiveFg(Constant.EXCLUSIVE_FG);
				bl.insertItem(itemBean);
			}
		}
		
		LOG.info("*************execute End SaveData SAB9931*************", "");
		
		return SUCCESS;
	}
	
	public List<Pab9931CountryBean> getListCountry() {
		return listCountry;
	}

	public void setListCountry(List<Pab9931CountryBean> listCountry) {
		this.listCountry = listCountry;
	}

	public List<Pab9931CategoryBean> getListCategory() {
		return listCategory;
	}

	public void setListCategory(List<Pab9931CategoryBean> listCategory) {
		this.listCategory = listCategory;
	}

	public List<Pab9931ItemBean> getListAllItem() {
		return listAllItem;
	}

	public void setListAllItem(List<Pab9931ItemBean> listAllItem) {
		this.listAllItem = listAllItem;
	}

	public List<Pab9931ItemBean> getListItemActive() {
		return listItemActive;
	}

	public void setListItemActive(List<Pab9931ItemBean> listItemActive) {
		this.listItemActive = listItemActive;
	}

	public List<Pab9931ItemBean> getListItemInactive() {
		return listItemInactive;
	}

	public void setListItemInactive(List<Pab9931ItemBean> listItemInactive) {
		this.listItemInactive = listItemInactive;
	}

	
}
