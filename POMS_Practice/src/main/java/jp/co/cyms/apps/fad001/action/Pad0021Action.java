package jp.co.cyms.apps.fad001.action;

import java.util.Calendar;
import java.util.List;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jp.co.cyms.apps.fad001.bean.ItemBean;
import jp.co.cyms.apps.fad001.bean.LockedCountryBean;
import jp.co.cyms.apps.fad001.bean.Pad0021Bean;
import jp.co.cyms.apps.fad001.bl.Pad0021BL;
import jp.co.cyms.apps.fad001.form.Pad0021Form;
import jp.co.cyms.common.DateFormat;
import jp.co.cyms.common.DateUtil;
import jp.co.cyms.common.NumberUtil;
import jp.co.cyms.common.StringUtil;
import jp.co.cyms.common.bean.CountryBean;
import jp.co.cyms.common.bl.CommonBL;

/**
 * PC Stock Management Action
 * 
 * @author anhnt2
 * @since 2018/01/03
 */
public class Pad0021Action extends Pad0021Form {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** List stock table */
	private List<Pad0021Bean> pad0021List;

	/** List item name (code) */
	private List<ItemBean> itemNameCodeList;

	/** Bussiness logic object. */
	Pad0021BL logic = new Pad0021BL();

	/** The param list. */
	private String paramList;

	/** Message Id */
	private String messageId;

	/** Error flag */
	private String errorFg;

	private boolean enterScreen = false;
	
	private boolean addFlag = false;

	/** Locked countries */
	private List<LockedCountryBean> lockedcountries;
	
	/**
	 * init
	 * 
	 * @return SUCCESS
	 */
	public String init() throws Exception{
		LOG.info("*************init Start*************", "");
		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
		try {
			String userId = getUserInfo().getUserId();
			List<ItemBean> itemBean = logic.getCategoryItemCd();
			List<String> countries = logic.getCountries();
			
			for (ItemBean item: itemBean) {
				for (String country: countries) {
					Pad0021Bean pad0021Bean = new Pad0021Bean();
					pad0021Bean.setCategoryCd(item.getCategoryCd());
					pad0021Bean.setItemCd(item.getItemCd());
					pad0021Bean.setCountryCd(country);
					
					if (logic.checkExistStock(pad0021Bean) == 0) {
						logic.doInsert(pad0021Bean);
					}
				}
			}
			
			int countLockedInit = logic.countLockedInit(userId);
			System.out.println(countLockedInit);
			if (countLockedInit > 1) {
				Pad0021Bean bean = new Pad0021Bean();
				bean.setUpdatedUser(userId);
				bean.setExclusiveFg(null);
				logic.doUpdateCountryData2(bean);
			}
			
			pad0021List = logic.getListPad0021();
			transactionManager.commit(transactionStatus);
		} catch (Exception e) {
			transactionManager.rollback(transactionStatus);
			e.printStackTrace();
			LOG.info("*************Do rollback init *************", "");
		}
		LOG.info("*************init End***************", "");
		return SUCCESS;
	}

	/**
	 * Load data for json
	 * 
	 * @return SUCCESS
	 */
	public String loadData() throws Exception {
		LOG.info("*************loadData Start*************", "");
		
		pad0021List = logic.getListPad0021();
		lockedcountries = logic.getLockedContries(this.getUserInfo().getUserId());
		itemNameCodeList = logic.getListItemNameCode();
		addFlag = logic.checkLockedData() == 0 ? true : false;
		
		if (pad0021List != null) {
			// Get user info
			userInfo = this.getUserInfo();
			Pad0021Bean pad0021Bean = new Pad0021Bean();
			pad0021Bean.setUpdatedUser(userInfo.getUserId());

			// If [STOCK].EXCLUSIVE_FG = ""
			/*pad0021Bean.setExclusiveFg(exclusiveFg);
			boolean checkExclusive = logic.checkUserEnter(pad0021Bean);*/

			// Set [STOCK].EXCLUSIVE_FG = "1"
			/*exclusiveFg = "1";
			pad0021Bean.setExclusiveFg(exclusiveFg);*/

			/*if (checkExclusive) {
				// Update [STOCK].EXCLUSIVE_FG = "1"
				TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
				TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
				try {
					logic.doClose(pad0021Bean);
					transactionManager.commit(transactionStatus);
				} catch (Exception e) {
					transactionManager.rollback(transactionStatus);
					e.printStackTrace();
					LOG.info("*************Do rollback doAdd *************", "");
				}
			}*/

			// If [STOCK].EXCLUSIVE_FG = "1"
			/*boolean isUserEnter = logic.checkUserEnter(pad0021Bean);

			// [STOCK].UPDATED_USER = (Current User)
			if (isUserEnter) {
				enterScreen = true;
			}*/
		} else {
			enterScreen = true;
		}
		LOG.info("*************loadData End***************", "");
		return SUCCESS;
	}

	/**
	 * Action insert or update stock
	 * 
	 * @return result
	 * @throws Exception
	 */
	public String doSave() throws Exception {
		LOG.info("*************doSave Start*************", "");
		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
		try {
			// Get paramList from Ajax
			String param = this.paramList;
			
			
			// Convert Param to JsonElement
			JsonElement jelement = new JsonParser().parse(param);
			// JsonObject created
			JsonObject jobject = jelement.getAsJsonObject();

			// Get object arr from jobject
			JsonArray jarray = jobject.getAsJsonArray("arr");
			if (jarray.size() > 0) {
				for (int i = 0; i < jarray.size(); i++) {
					JsonObject rowobject = (JsonObject) jarray.get(i);
					Pad0021Bean pad0021Bean = new Pad0021Bean();
					// Get list country. Insert or update by country
					if (convertJson(rowobject, "countryCd").size() > 0) {
						for (int x = 0; x < rowobject.get("countryCd").getAsJsonArray().size(); x++) {
							pad0021Bean.setCategoryCd(convertJson(rowobject, "categoryCd").get(x).getAsString());
							
							// [Add new], categoryCdFromDb has never been existed in DB
							if(convertJson(rowobject, "categoryCdFromDb") != null) {
								pad0021Bean.setCategoryCdFromDb(convertJson(rowobject, "categoryCdFromDb").get(x).getAsString());
							}
							pad0021Bean.setCountryCd(convertJson(rowobject, "countryCd").get(x).getAsString());
							pad0021Bean.setItemCd(convertJson(rowobject, "itemCd").get(x).getAsString().replace("(", "")
									.replace(")", ""));
							
							// [Add new], itemCdFromDb has never been existed in DB
							if(convertJson(rowobject, "itemCdFromDb") != null) {
								pad0021Bean.setItemCdFromDb(convertJson(rowobject, "itemCdFromDb").get(x).getAsString().replace("(", "")
										.replace(")", ""));
							}
							pad0021Bean.setQtyLoi1(convertJson(rowobject, "countryDate").get(x).getAsJsonArray().get(0).getAsString());
							pad0021Bean.setQtyLoi2(convertJson(rowobject, "countryDate").get(x).getAsJsonArray().get(1).getAsString());
							pad0021Bean.setQtyLoi3(convertJson(rowobject, "countryDate").get(x).getAsJsonArray().get(2).getAsString());
							pad0021Bean.setQtyLoi4(convertJson(rowobject, "countryDate").get(x).getAsJsonArray().get(3).getAsString());
							
							
							JsonArray jsonArrayQty = convertJson(rowobject, "qty").get(x).getAsJsonArray();
							// Array qty
							if (jsonArrayQty.size() > 0) {
								pad0021Bean.setQty1(jsonArrayQty.get(0).getAsString());
								pad0021Bean.setQty2(jsonArrayQty.get(1).getAsString());
								pad0021Bean.setQty3(jsonArrayQty.get(2).getAsString());
								pad0021Bean.setQty4(jsonArrayQty.get(3).getAsString());
							}
							
							pad0021Bean.setUpdatedUser(super.getUpdatedUser());
							pad0021Bean.setUpdatedDtLocal(super.getUpdatedDtLocal());
							pad0021Bean.setTimeDifference(super.getTimeDifference());
							
							exclusiveFg = null;
							pad0021Bean.setExclusiveFg(exclusiveFg);
							
							// check before save
							checkValidate(pad0021Bean);
							if ("CM-0012".equals(this.messageId)) {
								break;
							}
							if (this.messageId == null || "".equals(this.messageId)) {
								// Check exist data
								if (logic.checkExist(pad0021Bean)) {
									// If data has exist on DB: call Updated
									logic.doUpdate(pad0021Bean);
									logic.doUpdateCategoryItemCd(pad0021Bean);
								} else {
									// If data has not exist on DB: call
									// Inserted
									logic.doInsert(pad0021Bean);
								}
								logic.doUpdateCommonData(pad0021Bean);
							}
						}
					}
				}
			}
			transactionManager.commit(transactionStatus);
		} catch (Exception e) {
			transactionManager.rollback(transactionStatus);
			e.printStackTrace();
			LOG.info("*************Do rollback doAdd *************", "");
		}
		
		loadData();
		
		LOG.info("*************doSave End***************", "");
		return SUCCESS;
	}

	/**
	 * Action delete stock
	 * 
	 * @return result
	 * @throws Exception
	 */
	public String doDelete() throws Exception {
		LOG.info("*************doDelete Start*************", "");
		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
		try {
			// Get paramList from Ajax
			String param = this.paramList;
			// Convert Param to JsonElement
			JsonElement jelement = new JsonParser().parse(param);
			// JsonObject created
			JsonObject jobject = jelement.getAsJsonObject();

			// Get object arr from jobject
			JsonArray jarray = jobject.getAsJsonArray("arr");
			if (jarray.size() > 0) {
				for (int i = 0; i < jarray.size(); i++) {
					JsonObject rowobject = (JsonObject) jarray.get(i);
					Pad0021Bean pad0021Bean = new Pad0021Bean();
					// Get list country. Insert or update by country
					if (convertJson(rowobject, "countryCd").size() > 0) {
						for (int x = 0; x < rowobject.get("countryCd").getAsJsonArray().size(); x++) {
							pad0021Bean.setCountryCd(convertJson(rowobject, "countryCd").get(x).getAsString());
							pad0021Bean.setCategoryCd(convertJson(rowobject, "categoryCd").get(x).getAsString());
							pad0021Bean.setCategoryCdFromDb(convertJson(rowobject, "categoryCdFromDb").get(x).getAsString());
							pad0021Bean.setItemCd(convertJson(rowobject, "itemCd").get(x).getAsString().replace("(", "")
									.replace(")", ""));
							pad0021Bean.setItemCdFromDb(convertJson(rowobject, "itemCdFromDb").get(x).getAsString().replace("(", "")
									.replace(")", ""));
							if (this.messageId == null || "".equals(this.messageId)) {
								// Check exist data
								if (logic.checkExist(pad0021Bean)) {
									// If data has exist on DB: call delete
									// action
									logic.doDelete(pad0021Bean);
								} else {
									// If data has not exist on DB
									messageId = "CM-0002";
									super.addActionError("CM-0002");
								}
							}
						}
					}
				}
			}
			
			pad0021List = logic.getListPad0021();
			transactionManager.commit(transactionStatus);
		} catch (Exception e) {
			transactionManager.rollback(transactionStatus);
			e.printStackTrace();
			LOG.info("*************Do rollback doAdd *************", "");
		}
		// Reload data for json
		loadData();
		LOG.info("*************doDelete End***************", "");
		return SUCCESS;
	}

	/**
	 * Action close stock
	 * 
	 * @return result
	 * @throws Exception
	 */
	public String doClose() throws Exception {
		LOG.info("*************doClose Start*************", "");
		// Check currently being edited by another user
		Pad0021Bean pad0021BeanCheck = new Pad0021Bean();
		userInfo = this.getUserInfo();
		pad0021BeanCheck.setUpdatedUser(userInfo.getUserId());
		// Set [STOCK].EXCLUSIVE_FG = "1"
		exclusiveFg = "1";
		pad0021BeanCheck.setExclusiveFg(exclusiveFg);
		boolean isUserEnter = logic.checkUserEnter(pad0021BeanCheck);
		// If currently being edited by another user then don't do update database
		if (isUserEnter) {
			TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
			TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
			try {
				Pad0021Bean pad0021Bean = new Pad0021Bean();
				exclusiveFg = null;
				pad0021Bean.setExclusiveFg(exclusiveFg);
				pad0021Bean.setUpdatedUser(super.getUpdatedUser());
				logic.doClose(pad0021Bean);
				transactionManager.commit(transactionStatus);
			} catch (Exception e) {
				transactionManager.rollback(transactionStatus);
				e.printStackTrace();
				LOG.info("*************Do rollback doAdd *************", "");
			}
			LOG.info("*************doClose End***************", "");
		}
		return SUCCESS;
	}
	/**
	 * Action check locked data
	 * 
	 * @return result
	 * @throws Exception
	 */
	public String checkLockedData() throws Exception {
		LOG.info("*************checkLockedData Start*************", "");
		
		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
		try {
			errorFg = null;
			int count = logic.checkLockedData();
			if(count > 0) {
				errorFg = "locked";
			}
			
			transactionManager.commit(transactionStatus);
		} catch (Exception e) {
			transactionManager.rollback(transactionStatus);
			e.printStackTrace();
			LOG.info("*************Do rollback checkLockedData *************", "");
		}
		LOG.info("*************checkLockedData End***************", "");
		return SUCCESS;
	}
	
	/**
	 * Action check locked data by another country
	 * 
	 * @return result
	 * @throws Exception
	 */
	public String checkLockedDataByAnother() throws Exception {
		LOG.info("*************checkLockedDataByAnother Start*************", "");
		
			TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
			TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
			try {
				errorFg = null;
				int count = logic.checkLockedDataByAnother(super.getCountryCd());
				if(count > 0) {
					errorFg = "lockedByAnother";
				}

				transactionManager.commit(transactionStatus);
			} catch (Exception e) {
				transactionManager.rollback(transactionStatus);
				e.printStackTrace();
				LOG.info("*************Do rollback checkLockedDataByAnother *************", "");
			}
			LOG.info("*************checkLockedDataByAnother End***************", "");
		return SUCCESS;
	}

	/**
	 * Check validate
	 * 
	 * @param pad0021Bean
	 * @return true/false
	 */
	private void checkValidate(Pad0021Bean pad0021Bean) throws Exception {
		// Check empty item cd
		if (StringUtil.isEmpty(pad0021Bean.getItemCd())) {
			messageId = "CM-0010";
		} else if (pad0021Bean.getItemCd().length() > 30) {
			messageId = "CM-0000";
		}
//		// Check exist data in item table
//		else if (!StringUtil.isEmpty(pad0021Bean.getItemCdFromDb()) && !logic.checkExistItemCd(pad0021Bean)) {
//			// If data has not exist on DB
//			messageId = "CM-0002";
//		}
		// Check empty category cd
		if (StringUtil.isEmpty(pad0021Bean.getCategoryCd())) {
			messageId = "EB-0003";
		} else if (pad0021Bean.getCategoryCd().length() > 10) {
			messageId = "CM-0000";
		}
//		// Check exist data in category table
//		else if (!StringUtil.isEmpty(pad0021Bean.getCategoryCdFromDb()) && !logic.checkExistCategoryCd(pad0021Bean)) {
//			// If data has not exist on DB
//			messageId = "CM-0002";
//		}
		// Check empty country cd
		if (StringUtil.isEmpty(pad0021Bean.getCountryCd())) {
			messageId = "EB-0007";
		} else if (pad0021Bean.getCountryCd().length() > 2) {
			messageId = "CM-0000";
		}
		// Check exist data in country table
		else if (!StringUtil.isEmpty(pad0021Bean.getCountryCd()) && !logic.checkExistCountryCd(pad0021Bean)) {
			// If data has not exist on DB
			messageId = "CM-0002";
		}
		if (!StringUtil.isEmpty(pad0021Bean.getQty1())) {
			if (pad0021Bean.getQty1().length() > 3) {
				messageId = "CM-0000";
			}
			if (!NumberUtil.isNumeric(pad0021Bean.getQty1())) {
				messageId = "CM-0001";
			}
		}
		if (!StringUtil.isEmpty(pad0021Bean.getQty2())) {
			if (pad0021Bean.getQty2().length() > 3) {
				messageId = "CM-0000";
			}
			if (!NumberUtil.isNumeric(pad0021Bean.getQty2())) {
				messageId = "CM-0001";
			}
		}
		if (!StringUtil.isEmpty(pad0021Bean.getQty3())) {
			if (pad0021Bean.getQty3().length() > 3) {
				messageId = "CM-0000";
			}
			if (!NumberUtil.isNumeric(pad0021Bean.getQty3())) {
				messageId = "CM-0001";
			}
		}
		if (!StringUtil.isEmpty(pad0021Bean.getBalance())) {
			if (pad0021Bean.getBalance().length() > 2) {
				messageId = "CM-0000";
			}
			if (!NumberUtil.isNumeric(pad0021Bean.getBalance())) {
				messageId = "CM-0001";
			}
		}
		
		//  [END] if [LOI# - Qty] is not empty, [LOI# - Date] should not be empty too.
		if (!StringUtil.isNullOrEmpty(pad0021Bean.getQty1()) && NumberUtil.parseIntOrZero(pad0021Bean.getQty1()) > 0) {
			String dateQtyLoi1 = pad0021Bean.getQtyLoi1();
			if (StringUtil.isNullOrEmpty(DateUtil.formatDate(dateQtyLoi1, DateFormat.YYYYMMMDD, DateFormat.YYYYMMMDD))) {
				messageId = "CM-0012";
			} 
		}
		if (!StringUtil.isEmpty(pad0021Bean.getQty2()) && NumberUtil.parseIntOrZero(pad0021Bean.getQty2()) > 0) {
			String dateQtyLoi2 = pad0021Bean.getQtyLoi2();
			if (StringUtil.isNullOrEmpty(DateUtil.formatDate(dateQtyLoi2, DateFormat.YYYYMMMDD, DateFormat.YYYYMMMDD))) {
				messageId = "CM-0012";
			} 
		}
		if (!StringUtil.isEmpty(pad0021Bean.getQty3()) && NumberUtil.parseIntOrZero(pad0021Bean.getQty3()) > 0) {
			String dateQtyLoi3 = pad0021Bean.getQtyLoi3();
			if (StringUtil.isNullOrEmpty(DateUtil.formatDate(dateQtyLoi3, DateFormat.YYYYMMMDD, DateFormat.YYYYMMMDD))) {
				messageId = "CM-0012";
			} 
		}
		if (!StringUtil.isEmpty(pad0021Bean.getQty4()) && NumberUtil.parseIntOrZero(pad0021Bean.getQty4()) > 0) {
			String dateQtyLoi4 = pad0021Bean.getQtyLoi4();
			if (StringUtil.isNullOrEmpty(DateUtil.formatDate(dateQtyLoi4, DateFormat.YYYYMMMDD, DateFormat.YYYYMMMDD))) {
				messageId = "CM-0012";
			}
		}
	}
	
	/**
	 * Contact string DD-MMM to DD-MMM-YY
	 * 
	 * @param value with type is DD-MMM
	 * return String with type is DD-MMM-YY
	 */
	private String contactStringDate(String value) {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String strDate = value;
		if (!StringUtil.isEmpty(strDate)) {
			strDate = strDate + "-" + year;
		}
		return strDate;
	}

	/**
	 * Get object arr from JsonObject
	 * 
	 * @param JsonObject
	 * @param name
	 * @return JsonArray
	 */
	private JsonArray convertJson(JsonObject rowobject, String name) {
		JsonArray jsonArray = null;
		if(rowobject.get(name) != null) {
			jsonArray = rowobject.get(name).getAsJsonArray();
		}
		return jsonArray;
	}
	
	/**
	 * Check value is null or empty ? or 0
	 * 
	 * @param value
	 * @return boolean
	 */
	private static boolean isNullOrEmptyOrZero(String value) {
		if (value == null || "".equals(value) || "0".equals(value)) {
			return true;
		}
		return false;
	}

	/**
	 * Get list item name (code)
	 * 
	 * @return itemNameCodeList
	 */
	public List<ItemBean> getItemNameCodeList() {
		return itemNameCodeList;
	}

	/**
	 * Set list item name (code)
	 */
	public void setItemNameCodeList(List<ItemBean> itemNameCodeList) {
		this.itemNameCodeList = itemNameCodeList;
	}

	/**
	 * Get message
	 * 
	 * @return messageId
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * Set message
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	/**
	 * Get json
	 * 
	 * @return paramList
	 */
	public String getParamList() {
		return paramList;
	}

	/**
	 * Set json
	 */
	public void setParamList(String paramList) {
		this.paramList = paramList;
	}

	/**
	 * Get stock
	 * 
	 * @return list stock
	 */
	public List<Pad0021Bean> getPad0021List() {
		return pad0021List;
	}

	/**
	 * Set stock
	 * 
	 */
	public void setPad0021List(List<Pad0021Bean> pad0021List) {
		this.pad0021List = pad0021List;
	}

	/**
	 * Get check user enter screen
	 * 
	 */
	public boolean isEnterScreen() {
		return enterScreen;
	}

	/**
	 * Set check user enter screen
	 * 
	 */
	public void setEnterScreen(boolean enterScreen) {
		this.enterScreen = enterScreen;
	}

	/**
	 * Update Country data
	 * 
	 * @throws Exception
	 */
	public String doUpdateCountryData() throws Exception {
		
		LOG.info("*************doUpdateCountryData Start*************", "");
		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
		
		try {
			errorFg = null;
			Pad0021Bean pad0021Bean = new Pad0021Bean();
			if(super.countryCd != null && !super.countryCd.equals("")) {
				pad0021Bean.setCountryCd(super.countryCd);
			}
			
			pad0021Bean.setUpdatedUser(super.getUpdatedUser());
			pad0021Bean.setExclusiveFg(super.getExclusiveFg());
			
			if(super.getPreviousCountry() == null || super.getPreviousCountry().equals("")) {
				if(pad0021Bean.getExclusiveFg() == null || pad0021Bean.getExclusiveFg().equals("")) {
					pad0021Bean.setExclusiveFg("1");
					logic.doUpdateCountryData(pad0021Bean);
					
					Pad0021Bean selectedCountriesBean = new Pad0021Bean();
					selectedCountriesBean.setCountryCd(super.countryCd);
					selectedCountriesBean.setUpdatedUser(super.getUpdatedUser());
					selectedCountriesBean.setExclusiveFg(null);
					logic.doUpdateUnselectedCountryData(selectedCountriesBean);
				}
				
				int status = logic.getCountryStatus(pad0021Bean);
				if(status > 0) {
					errorFg = "true";
				}
				else {
					errorFg = "false";
				}
			}
			else {
				int checkAlready = logic.checkAlready(pad0021Bean);
				if(checkAlready > 0) {
					errorFg = "already";
				}
			}
			
			pad0021List = logic.getListPad0021();
			lockedcountries = logic.getLockedContries(this.getUserInfo().getUserId());
			itemNameCodeList = logic.getListItemNameCode();
			
			transactionManager.commit(transactionStatus);
		} catch (Exception e) {
			transactionManager.rollback(transactionStatus);
			e.printStackTrace();
			LOG.info("*************Do rollback doUpdateCountryData *************", "");
		}
		LOG.info("*************doUpdateCountryData End***************", "");
		return SUCCESS;
		
	}
	/**
	 * Update Country data For Add button
	 * 
	 * @throws Exception
	 */
	public String doUpdateCountryData2() throws Exception {

		LOG.info("*************doUpdateCountryData2 Start*************", "");
		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

		try {
			Pad0021Bean pad0021Bean = new Pad0021Bean();
			pad0021Bean.setUpdatedUser(super.getUpdatedUser());
			pad0021Bean.setExclusiveFg(super.getExclusiveFg());

			// If data has exist on DB: call Updated
			logic.doUpdateCountryData2(pad0021Bean);

			transactionManager.commit(transactionStatus);
		} catch (Exception e) {
			transactionManager.rollback(transactionStatus);
			e.printStackTrace();
			LOG.info("*************Do rollback doUpdateCountryData2 *************", "");
		}
		LOG.info("*************doUpdateCountryData2 End***************", "");
		return SUCCESS;

	}

	/**
	 * @return the errorFg
	 */
	public String getErrorFg() {
		return errorFg;
	}

	/**
	 * @param errorFg the errorFg to set
	 */
	public void setErrorFg(String errorFg) {
		this.errorFg = errorFg;
	}

	/**
	 * @return the addFlag
	 */
	public boolean isAddFlag() {
		return addFlag;
	}

	/**
	 * @param addFlag the addFlag to set
	 */
	public void setAddFlag(boolean addFlag) {
		this.addFlag = addFlag;
	}

	
	/**
	 * @return the lockedcountries
	 */
	public List<LockedCountryBean> getLockedcountries() {
		return lockedcountries;
	}

	/**
	 * @param lockedcountries the lockedcountries to set
	 */
	public void setLockedcountries(List<LockedCountryBean> lockedcountries) {
		this.lockedcountries = lockedcountries;
	}

}
