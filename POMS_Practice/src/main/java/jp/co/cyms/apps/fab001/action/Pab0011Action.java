package jp.co.cyms.apps.fab001.action;

import java.util.List;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import jp.co.cyms.apps.fab001.bean.Pab0011Bean;
import jp.co.cyms.apps.fab001.bl.Pab0011BL;
import jp.co.cyms.apps.fab001.form.Pab0011Form;
import jp.co.cyms.apps.fad001.bean.Pad0021Bean;
import jp.co.cyms.apps.fad001.bl.Pad0021BL;
import jp.co.cyms.common.StringUtil;

/**
 * Item Master Pab0011Action
 * 
 * @author binhvh
 * @since 2017/12/25
 */
public class Pab0011Action extends Pab0011Form {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** listItem */
	private List<Pab0011Bean> listItem;

	public String execute() {
		LOG.info("*************execute Start*************", "");
		LOG.info("*************execute End*************", "");
		return SUCCESS;
	}

	/**
	 * get list item from item_mst
	 * 
	 * @return
	 * @throws Exception
	 */
	public String loadListItemData() throws Exception {
		LOG.info("*************loadListItemData Start*************", "");
		// Business logic
		Pab0011BL logic = new Pab0011BL();
		listItem = logic.getListItem();
		LOG.info("*************loadListItemData End*************", "");
		return SUCCESS;
	}

	/**
	 * action insert item_mst
	 * 
	 * @return
	 */
	public String doAdd() throws Exception {
		LOG.info("*************doAdd Start*************", "");
		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
		Pab0011BL logic = new Pab0011BL();

		Pab0011Bean paramBean = new Pab0011Bean();
		paramBean.setItemCd(this.itemCd);
		paramBean.setItemName(this.itemName);
		paramBean.setCategoryCd(this.categoryCd);
		paramBean.setCategoryName(this.categoryName);
		paramBean.setItemBrand(this.itemBrand);
		paramBean.setWarningFg(this.addMemory);

		// BaseDBBean
		paramBean.setUpdatedUser(super.getUpdatedUser());
		paramBean.setUpdatedDtLocal(super.getUpdatedDtLocal());
		paramBean.setTimeDifference(super.getTimeDifference());
        
		try {
			// Get list country, price
			List<String> country = logic.getCountry(this.unitPrice);
			List<Double> price = logic.getPrice(this.unitPrice);
	
			if (country == null || price == null || country.size() != price.size()) {
				return ERROR;
			}
		
			for (int i = 0; i < country.size(); i++) {
				paramBean.setCountryCd(country.get(i));
				paramBean.setUnitPrice(price.get(i));
				logic.insertItem(paramBean);
				
				// Cancel by about #160 on Google Sheet QA
				// Check item exist in stock
				/*if (!logic.checkStockExist(paramBean)){
					// If currently being edited by another user then set value ExclusiveFg is 1 else null.
					checkCurrentlyEdited(paramBean);
					logic.insertStock(paramBean);
				}*/
			}
			transactionManager.commit(transactionStatus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			transactionManager.rollback(transactionStatus);
			e.printStackTrace();
			LOG.info("*************Do rollback doAdd *************", "");
		}
		listItem = logic.getListItem();
		LOG.info("*************doAdd End*************", "");
		return SUCCESS;
	}
	
	/**
	 * update item_mst
	 * 
	 * @return
	 */
	public String doUpdate() throws Exception {
		LOG.info("*************doUpdate Start*************", "");
		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
		Pab0011BL logic = new Pab0011BL();

		Pab0011Bean paramBean = new Pab0011Bean();
		paramBean.setItemCd(this.itemCd);
		paramBean.setItemName(this.itemName);
		paramBean.setCategoryCd(this.categoryCd);
		paramBean.setCategoryName(this.categoryName);
		paramBean.setItemBrand(this.itemBrand);
		paramBean.setWarningFg(this.addMemory);
		paramBean.setNoUseFg(this.noUse);
		
		// BaseDBBean
		paramBean.setUpdatedUser(super.getUpdatedUser());
		paramBean.setUpdatedDtLocal(super.getUpdatedDtLocal());
		paramBean.setTimeDifference(super.getTimeDifference());
		
		// Get list country, price
		List<String> country = logic.getCountry(this.unitPrice);
		List<Double> price = logic.getPrice(this.unitPrice);
		
		if (country == null || price == null || country.size() != price.size()) {
			return ERROR;
		}

		try {
			for (int i = 0; i < country.size(); i++) {
				paramBean.setCountryCd(country.get(i));
				paramBean.setUnitPrice(price.get(i));
				
				// Check item exist in item_mst
				if (logic.checkItemExist(paramBean)){
					logic.updateItem(paramBean);
				} else {
					logic.insertItem(paramBean);
				}

				// If currently being edited by another user then set value ExclusiveFg is 1 else null.
				checkCurrentlyEdited(paramBean);
				
				// Check item exist in stock
				if (logic.checkStockExist(paramBean)){
					logic.updateStock(paramBean);
				} 
//				else {
//					logic.insertStock(paramBean);
//				}
			}
			transactionManager.commit(transactionStatus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			transactionManager.rollback(transactionStatus);
			e.printStackTrace();
			LOG.info("*************Do rollback doUpdate *************", "");
		}
		listItem = logic.getListItem();
		LOG.info("*************doUpdate End*************", "");
		return SUCCESS;
	}
	
	/**
	 * action delete item (set delete_fg = 1)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doDelete() throws Exception{
		LOG.info("*************doDelete Start*************", "");
		// Business logic
		Pab0011BL logic = new Pab0011BL();
		// Check parameter itemCd is null
		if (StringUtil.isEmpty(this.itemCd)){
			listItem = logic.getListItem();
			LOG.info("*************doDelete End*************", "");
			return SUCCESS;
		}
		// Get transaction
		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

		// ParamBean
		Pab0011Bean paramBean = new Pab0011Bean();
		paramBean.setItemCd(this.itemCd);
		
		// BaseDBBean
		paramBean.setUpdatedUser(super.getUpdatedUser());
		paramBean.setUpdatedDtLocal(super.getUpdatedDtLocal());
		paramBean.setTimeDifference(super.getTimeDifference());
		
		try {
			// Delete item in item_Mst
			logic.deleteItem(paramBean);
			
			// Set ACTIVE_FG = '0' when delete item
			// If item exist in active_mst
			if (logic.checkExistItemActiveMst(paramBean)){
				// Update active_mst
				logic.updateActiveMst(paramBean);
			}
			
			// Delete item in STOCK
			logic.deleteStockItem(paramBean);
			
			transactionManager.commit(transactionStatus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			transactionManager.rollback(transactionStatus);
			e.printStackTrace();
			LOG.info("*************Do rollback doDelete *************", "");
		}
		listItem = logic.getListItem();
		LOG.info("*************doDelete End*************", "");
		return SUCCESS;
	}

	public List<Pab0011Bean> getListItem() {
		return listItem;
	}

	public void setListItem(List<Pab0011Bean> listItem) {
		this.listItem = listItem;
	}
	
	/**
	 * If currently being edited by another user then set value ExclusiveFg is 1 else null.
	 * 
	 * @return
	 * @throws Exception
	 */
	private void checkCurrentlyEdited(Pab0011Bean paramBean) throws Exception {
		Pad0021BL logic = new Pad0021BL();
		Pad0021Bean pad0021Bean = new Pad0021Bean();
		pad0021Bean.setExclusiveFg(null);
		boolean checkExclusive = logic.checkUserEnter(pad0021Bean);
		if (checkExclusive) {
			paramBean.setExclusiveFg(null);
		} else {
			paramBean.setExclusiveFg("1");
			paramBean.setUpdatedUser(null);
		}
	}
}
