package jp.co.cyms.apps.fab001.action;

import java.util.List;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import jp.co.cyms.apps.fab001.bean.Pab0031Bean;
import jp.co.cyms.apps.fab001.bl.Pab0031BL;
import jp.co.cyms.apps.fab001.form.Pab0031Form;
import jp.co.cyms.common.Constant;
import jp.co.cyms.common.StringUtil;
import jp.co.cyms.common.bean.CategoryBean;
import jp.co.cyms.common.bean.CountryBean;

/**
 * Active Item Master Pab0031Action
 * 
 * @author binhvh
 * @since 2018/01/03
 */
public class Pab0031Action extends Pab0031Form {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** list country */
	private List<CountryBean> countryList;

	/** list category */
	private List<CategoryBean> categoryList;

	/** list all item */
	private List<Pab0031Bean> allItem;

	/** list active item */
	private List<Pab0031Bean> activeItem;

	/** list inactive item */
	private List<Pab0031Bean> inactiveItem;

	/**
	 * Method execute when init sab0031 screen
	 * 
	 * @throws Exception
	 * @return result
	 */
	@Override
	public String execute() throws Exception {
		LOG.info("*************execute Start*************", "");
		Pab0031BL logic = new Pab0031BL();
		countryList = logic.getListCountry();
		categoryList = logic.getListCategory();
		LOG.info("*************execute End*************", "");
		return SUCCESS;
	}

	/**
	 * action get all item, active item, inactive item
	 * 
	 * @throws Exception
	 * @return
	 */
	public String loadItem() throws Exception {
		LOG.info("*************loadItem Start*************", "");
		if (StringUtil.isEmpty(this.countryCd) || StringUtil.isEmpty(this.categoryCd)) {
			LOG.info("*************loadItem End*************", "");
			return ERROR;
		}

		Pab0031BL logic = new Pab0031BL();
		Pab0031Bean paramBean = new Pab0031Bean();
		paramBean.setCountryCd(this.countryCd);
		paramBean.setCategoryCd(this.categoryCd);

		// get all item
		allItem = logic.getAllItem(paramBean);

		// get active item
		activeItem = logic.getActiveItem(paramBean);

		// get inactive item
		inactiveItem = logic.getInactiveItem(paramBean);

		LOG.info("*************getItem Start*************", "");
		return SUCCESS;
	}

	/**
	 * action insert update active_mst
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doSave() throws Exception {
		LOG.info("*************doSave Start*************", "");
		// check countryCd, categoryCd is null
		if (StringUtil.isEmpty(this.countryCd) || StringUtil.isEmpty(this.categoryCd)) {
			LOG.info("*************doSave End*************", "");
			return ERROR;
		}
		
		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
		Pab0031BL logic = new Pab0031BL();
		Pab0031Bean paramBean = new Pab0031Bean();
		
		// set value to bean
		paramBean.setCountryCd(this.countryCd);
		paramBean.setCategoryCd(this.categoryCd);
		// BaseDBBean
		paramBean.setUpdatedUser(super.getUpdatedUser());
		paramBean.setUpdatedDtLocal(super.getUpdatedDtLocal());
		paramBean.setTimeDifference(super.getTimeDifference());
		paramBean.setExclusiveFg(Constant.EXCLUSIVE_FG);
		
		try {
			// delete item in list all item
			if (this.listAllItemCd != null && this.listAllItemCd.size() > 0) {
				for (int i=0; i < this.listAllItemCd.size(); i++){
					paramBean.setItemCd(listAllItemCd.get(i));
					if (logic.checkItemExist(paramBean)){
						// delete item in active_mst
						logic.deleteItem(paramBean);
					}
				}
			}
			
			// insert or update list active item
			if (this.listActiveItemCd != null && this.listActiveItemCd.size() > 0) {
				for (int i=0; i < this.listActiveItemCd.size(); i++){
					paramBean.setItemCd(listActiveItemCd.get(i));
					paramBean.setActiveFg("1");
					if (logic.checkItemExist(paramBean)){
						// update active_mst
						logic.updateItem(paramBean);
					} else {
						// insert active_mst
						logic.insertItem(paramBean);
					}
				}
			}
			
			// insert or update list inactive item
			if (this.listInactiveItemCd != null && this.listInactiveItemCd.size() > 0) {
				for (int i=0; i < this.listInactiveItemCd.size(); i++){
					paramBean.setItemCd(listInactiveItemCd.get(i));
					paramBean.setActiveFg("0");
					if (logic.checkItemExist(paramBean)){
						// update active_mst
						logic.updateItem(paramBean);
					} else {
						// insert active_mst
						logic.insertItem(paramBean);
					}
				}
			}
			transactionManager.commit(transactionStatus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
            transactionManager.rollback(transactionStatus);
            e.printStackTrace();
            LOG.info("*************Do rollback doSave *************", "");
		}
		// get all item
		allItem = logic.getAllItem(paramBean);
		
		// get active item
		activeItem = logic.getActiveItem(paramBean);
		
		// get inactive item
		inactiveItem = logic.getInactiveItem(paramBean);
		System.out.println(this.listActiveItemCd);
		System.out.println(this.listInactiveItemCd);
		LOG.info("*************doSave End*************", "");
		return SUCCESS;
	}

	public List<CountryBean> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<CountryBean> countryList) {
		this.countryList = countryList;
	}

	public List<CategoryBean> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<CategoryBean> categoryList) {
		this.categoryList = categoryList;
	}

	public List<Pab0031Bean> getAllItem() {
		return allItem;
	}

	public void setAllItem(List<Pab0031Bean> allItem) {
		this.allItem = allItem;
	}

	public List<Pab0031Bean> getActiveItem() {
		return activeItem;
	}

	public void setActiveItem(List<Pab0031Bean> activeItem) {
		this.activeItem = activeItem;
	}

	public List<Pab0031Bean> getInactiveItem() {
		return inactiveItem;
	}

	public void setInactiveItem(List<Pab0031Bean> inactiveItem) {
		this.inactiveItem = inactiveItem;
	}
}
