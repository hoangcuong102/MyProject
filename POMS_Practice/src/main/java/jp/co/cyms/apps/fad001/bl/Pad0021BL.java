package jp.co.cyms.apps.fad001.bl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import jp.co.cyms.apps.fad001.bean.ItemBean;
import jp.co.cyms.apps.fad001.bean.LockedCountryBean;
import jp.co.cyms.apps.fad001.bean.Pad0021Bean;
import jp.co.cyms.apps.fad001.dao.Pad0021Dao;
import jp.co.cyms.base.BaseLogic;
import jp.co.cyms.common.DateFormat;
import jp.co.cyms.common.DateUtil;

/**
 * PC Stock Management BL
 * 
 * @author anhnt2
 * @since 2018/01/03
 */
@Service
public class Pad0021BL extends BaseLogic {

	Pad0021Dao dao = new Pad0021Dao();

	/**
	 * Insert Stock.
	 *
	 * @param pad0021Bean
	 * @return the int
	 * @throws Exception
	 */
	public void doInsert(Pad0021Bean pad0021Bean) throws Exception {
		dao.doInsert(pad0021Bean);

	}
	
	/**
	 * Update Stock.
	 *
	 * @param pad0021Bean
	 * @return the int
	 * @throws Exception
	 */
	public void doUpdate(Pad0021Bean pad0021Bean) throws Exception {
		dao.doUpdate(pad0021Bean);
	}
	
	/**
	 * Update Stock.
	 *
	 * @param pad0021Bean
	 * @throws Exception
	 */
	public void doUpdateCommonData(Pad0021Bean pad0021Bean) throws Exception {
		dao.doUpdateCommonData(pad0021Bean);
	}

	/**
	 * Update Stock.
	 *
	 * @param pad0021Bean
	 * @throws Exception
	 */
	public void doUpdateCategoryItemCd(Pad0021Bean pad0021Bean) throws Exception {
		dao.doUpdateCategoryItemCd(pad0021Bean);
	}

	/**
	 * Delete Stock.
	 *
	 * @param pad0021Bean
	 * @return the int
	 * @throws Exception
	 */
	public void doDelete(Pad0021Bean pad0021Bean) throws Exception {
		dao.doDelete(pad0021Bean);

	}

	/**
	 * Close Stock.
	 *
	 * @return the int
	 * @throws Exception
	 */
	public void doClose(Pad0021Bean pad0021Bean) throws Exception {
		dao.doClose(pad0021Bean);

	}

	/**
	 * Check exist.
	 *
	 * @param pad0021Bean
	 * @return true, if successful
	 * @throws Exception
	 */
	public boolean checkExist(Pad0021Bean pad0021Bean) throws Exception {
		return dao.checkExist(pad0021Bean);
	}

	/**
	 * Check user enters the screen.
	 *
	 * @param pad0021Bean
	 * @return true, if successful
	 * @throws Exception
	 */
	public boolean checkUserEnter(Pad0021Bean pad0021Bean) throws Exception {
		return dao.checkUserEnter(pad0021Bean);
	}

	/**
	 * Check exist category cd.
	 *
	 * @param pad0021Bean
	 * @return true, if successful
	 * @throws Exception
	 */
	public boolean checkExistCategoryCd(Pad0021Bean pad0021Bean) throws Exception {
		return dao.checkExistCategoryCd(pad0021Bean);
	}

	/**
	 * Check exist item cd.
	 *
	 * @param pad0021Bean
	 * @return true, if successful
	 * @throws Exception
	 */
	public boolean checkExistItemCd(Pad0021Bean pad0021Bean) throws Exception {
		return dao.checkExistItemCd(pad0021Bean);
	}

	/**
	 * Check exist country cd.
	 *
	 * @param pad0021Bean
	 * @return true, if successful
	 * @throws Exception
	 */
	public boolean checkExistCountryCd(Pad0021Bean pad0021Bean) throws Exception {
		return dao.checkExistCountryCd(pad0021Bean);
	}

	/**
	 * Get list stock
	 * 
	 * @return list stock
	 * @throws Exception
	 */
	public List<Pad0021Bean> getListPad0021() throws Exception {

		List<Pad0021Bean> list = dao.getListPad0021();
		List<Pad0021Bean> convertedList = null;

		if (list.size() > 0) {
			convertedList = convertMmDdtoMmmDd(list);
		}
		return convertedList;
	}

	/**
	 * Get list item name (code)
	 * 
	 * @return list item name (code)
	 */
	public List<ItemBean> getListItemNameCode() {
		List<ItemBean> list = dao.getListItemNameCode();
		if (list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * Update country data.
	 *
	 * @param pad0021Bean
	 * @throws Exception
	 */
	public void doUpdateCountryData(Pad0021Bean pad0021Bean) throws Exception {
		dao.doUpdateCountryData(pad0021Bean);
	}

	/**
	 * Update unselected countries.
	 *
	 * @param pad0021Bean
	 * @throws Exception
	 */
	public void doUpdateUnselectedCountryData(Pad0021Bean pad0021Bean) throws Exception {
		dao.doUpdateUnselectedCountryData(pad0021Bean);
	}

	/**
	 * Update country data.
	 *
	 * @param pad0021Bean
	 * @return the int
	 * @throws Exception
	 */
	public void doUpdateCountryData2(Pad0021Bean pad0021Bean) throws Exception {
		dao.doUpdateCountryData2(pad0021Bean);

	}

	/**
	 * Get Updated country status.
	 *
	 * @param pad0021Bean
	 * @return the int
	 * @throws Exception
	 */
	public int getCountryStatus(Pad0021Bean pad0021Bean) throws Exception {
		return dao.getCountryStatus(pad0021Bean);

	}

	/**
	 * Check "country is ALREADY ENABLED by the same User"
	 * 
	 * @param pad0021Bean
	 * @return
	 */
	public int checkAlready(Pad0021Bean pad0021Bean) throws Exception {
		return dao.checkAlready(pad0021Bean);
	}
	
	/**
	 * Check locked data
	 * 
	 * @param pad0021Bean
	 * @return
	 */
	public int checkLockedData() throws Exception {
		return dao.checkLockedData();
	}

	/**
	 * Check locked data by another country
	 * 
	 * @param pad0021Bean
	 * @return
	 */
	public int checkLockedDataByAnother(String country) throws Exception {
		return dao.checkLockedDataByAnother(country);
	}

	private List<Pad0021Bean> convertMmDdtoMmmDd(List<Pad0021Bean> list) throws Exception {
		int size = list.size();
		for (int i = 0; i < size; i++) {
			if (list.get(i).getQtyLoi1() != null) {
				list.get(i).setQtyLoi1(convertToMmmDd(list.get(i).getQtyLoi1().substring(0, 10)));
			}
			if (list.get(i).getQtyLoi2() != null) {
				list.get(i).setQtyLoi2(convertToMmmDd(list.get(i).getQtyLoi2().substring(0, 10)));
			}
			if (list.get(i).getQtyLoi3() != null) {
				list.get(i).setQtyLoi3(convertToMmmDd(list.get(i).getQtyLoi3().substring(0, 10)));
			}
			if (list.get(i).getQtyLoi4() != null) {
				list.get(i).setQtyLoi4(convertToMmmDd(list.get(i).getQtyLoi4().substring(0, 10)));
			}
		}
		return list;
	}

	private String convertToMmmDd(String dt) throws Exception {

		Date d = DateUtil.parseStrToDate(dt, DateFormat.YYYYMMDD2);
		SimpleDateFormat df = new SimpleDateFormat("YYYY-MMM-dd");
		return df.format(d);
	}
	
	/**
     * Check locked countries when init screen
     * @return number of locked data
     */
	public int countLockedInit(String updatedUser) throws Exception{
		return dao.countLockedInit(updatedUser);
	}
	
	/**
	 * Get list locked countries
	 * 
	 * @return list locked countries
	 */
	public List<LockedCountryBean> getLockedContries(String userId) {
		List<LockedCountryBean> list = dao.getLockedContries(userId);
		if (list.size() > 0) {
			return list;
		}
		return null;
	}
	
	/**
	 * Get list locked countries
	 * 
	 * @return list locked countries
	 */
	public List<ItemBean> getCategoryItemCd() {
		List<ItemBean> list = dao.getCategoryItemCd();
		if (list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * Get Country list
	 * 
	 * @return Country list
	 */
	public List<String> getCountries() {
		List<String> list = dao.getCountries();
		if (list.size() > 0) {
			return list;
		}
		return null;
	}
	
	/**
	 * Check exist stock
	 * 
	 * @return count stock
	 */
	public int checkExistStock(Pad0021Bean pad0021Bean) {
		return dao.checkExistStock(pad0021Bean);
	}
}
