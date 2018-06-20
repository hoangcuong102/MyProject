package jp.co.cyms.common.bl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.cyms.base.BaseLogic;
import jp.co.cyms.common.bean.CategoryBean;
import jp.co.cyms.common.bean.ConfigBean;
import jp.co.cyms.common.bean.CountryBean;
import jp.co.cyms.common.bean.ErrorBean;
import jp.co.cyms.common.dao.CommonDao;
import jp.co.cyms.common.properties.ErrorProperties;

/**
 * 
 * @author longnd
 *
 */
public class CommonBL extends BaseLogic {
	/**
	 * get configuration form CONFIG table
	 * @return
	 */
	public ConfigBean getConfig() {
		return new CommonDao().getConfig();
	}

	/**
	 * get all message from: class ErrorProperties and file
	 * properties/error.properties
	 * 
	 * @return
	 * @throws IOException
	 */
	public List<ErrorBean> getAllMessages() throws IOException {
		List<ErrorProperties.ExceptionKey> errorEnums = Arrays.asList(ErrorProperties.ExceptionKey.values());
		List<ErrorBean> listMessages = new ArrayList<ErrorBean>();
		for (ErrorProperties.ExceptionKey data : errorEnums) {
			data.name();
			ErrorBean errorBean = new ErrorBean();
			errorBean.setKey(data.name().replace("_", "-"));
			errorBean.setValue(ErrorProperties.get(data));
			listMessages.add(errorBean);
		}
		return listMessages;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<CountryBean> getCountries(){
		return new CommonDao().getCountries();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<CategoryBean> getListCategory(){
		return new CommonDao().getListCategory();
	}
}
