package jp.co.cyms.apps.fad001.dao;

import java.util.List;

import jp.co.cyms.apps.fad001.bean.ItemBean;
import jp.co.cyms.apps.fad001.bean.LockedCountryBean;
import jp.co.cyms.apps.fad001.bean.Pad0021Bean;
import jp.co.cyms.base.BaseDao;

/**
 * PC Stock Management DAO
 *
 * @author anhnt2
 * @since 2018/01/03
 */
public class Pad0021Dao extends BaseDao {
    /**
     * Install
     */
    public Pad0021Dao() {
        super();
    }
    
    /**
     * Insert database
     * @return number row insert
     */
	public void doInsert(Pad0021Bean pad0021Bean) throws Exception{
		this.update("FAD0021.doInsert", pad0021Bean);
	}
	
	/**
	 * Update database
	 * @return number row update
	 */
	public void doUpdate(Pad0021Bean pad0021Bean) throws Exception{
		this.update("FAD0021.doUpdate", pad0021Bean);
	}
    
	/**
	 * 
	 * @param pad0021Bean
	 * @throws Exception
	 */
	public void doUpdateCommonData(Pad0021Bean pad0021Bean) throws Exception{
		this.update("FAD0021.doUpdateCommonData", pad0021Bean);
	}
	
    /**
     * 
     * @param pad0021Bean
     * @throws Exception
     */
	public void doUpdateCategoryItemCd(Pad0021Bean pad0021Bean) throws Exception{
		this.update("FAD0021.doUpdateCategoryItemCd", pad0021Bean);
	}
	
    /**
     * Delete database
     * @return number row update
     */
	public void doDelete(Pad0021Bean pad0021Bean) throws Exception{
		this.update("FAD0021.doDelete", pad0021Bean);
	}
	
    /**
     * Close database
     * @return number row update
     */
	public void doClose(Pad0021Bean pad0021Bean) throws Exception{
		this.update("FAD0021.doClose", pad0021Bean);
	}
	
    /**
     * Check exist
     * @return total record
     */
	public boolean checkExist(Pad0021Bean pad0021Bean) throws Exception{
		int count = this.queryCount("FAD0021.checkExist", pad0021Bean);
		if(count > 0){
			return true;
		}
		return false;
	}
	
    /**
     * Check user enters the screen
     * @return total record
     */
	public boolean checkUserEnter(Pad0021Bean pad0021Bean) throws Exception{
		int count = this.queryCount("FAD0021.checkUserEnter", pad0021Bean);
		if(count > 0){
			return true;
		}
		return false;
	}
	
    /**
     * Check exist category cd
     * @return total record
     */
	public boolean checkExistCategoryCd(Pad0021Bean pad0021Bean) throws Exception{
		int count = this.queryCount("FAD0021.checkExistCategoryCd", pad0021Bean);
		if(count > 0){
			return true;
		}
		return false;
	}
	
    /**
     * Check exist item cd
     * @return total record
     */
	public boolean checkExistItemCd(Pad0021Bean pad0021Bean) throws Exception{
		int count = this.queryCount("FAD0021.checkExistItemCd", pad0021Bean);
		if(count > 0){
			return true;
		}
		return false;
	}
	
    /**
     * Check exist country cd
     * @return total record
     */
	public boolean checkExistCountryCd(Pad0021Bean pad0021Bean) throws Exception{
		int count = this.queryCount("FAD0021.checkExistCountryCd", pad0021Bean);
		if(count > 0){
			return true;
		}
		return false;
	}
    /**
     * Get list stock
     * @return List stock
     */
    @SuppressWarnings("unchecked")
    public List<Pad0021Bean> getListPad0021(){
        List<Pad0021Bean> list = null;
        list = (List<Pad0021Bean>) this.queryList("FAD0021.getListPad0021");
        return list;
    }
    
    /**
     * Get list item name (code)
     * @return List item name (code)
     */
    @SuppressWarnings("unchecked")
    public List<ItemBean> getListItemNameCode(){
        List<ItemBean> list = null;
        list = (List<ItemBean>) this.queryList("FAD0021.getListItemNameCode");
        return list;
    }
    
    /**
     * Update database
     */
    public void doUpdateCountryData(Pad0021Bean pad0021Bean) throws Exception{
    	this.update("FAD0021.doUpdateCountryData", pad0021Bean);
    }
    
    
    /**
     * Update database
     */
    public void doUpdateUnselectedCountryData(Pad0021Bean pad0021Bean) throws Exception{
    	this.update("FAD0021.doUpdateUnselectedCountryData", pad0021Bean);
    }
    
    /**
     * Update database
     * @return number row update
     */
    public void doUpdateCountryData2(Pad0021Bean pad0021Bean) throws Exception{
    	this.update("FAD0021.doUpdateCountryData2", pad0021Bean);
    }
    
    /**
     * Update database
     * @return number row update
     */
    public int getCountryStatus(Pad0021Bean pad0021Bean) throws Exception{
    	return this.queryCount("FAD0021.getCountryStatus", pad0021Bean);
    }
    
    /**
     * Check "country is ALREADY ENABLED by the same User"
     * @return number row update
     */
    public int checkAlready(Pad0021Bean pad0021Bean) throws Exception{
    	return this.queryCount("FAD0021.checkAlready", pad0021Bean);
    }
    
    /**
     * Check locked data
     * @return number of locked data
     */
    public int checkLockedData() throws Exception{
    	return this.queryCount("FAD0021.checkLockedData");
    }
    
    /**
     * Check locked data by another
     * @return number of locked data
     */
	public int checkLockedDataByAnother(String country) throws Exception{
		return this.queryCount("FAD0021.checkLockedDataByAnother", country);
	}
	
	/**
     * Check locked countries when init screen
     * @return number of locked data
     */
	public int countLockedInit(String updatedUser) throws Exception{
		return this.queryCount("FAD0021.countLockedInit", updatedUser);
	}
	
	/**
	 * Get list locked countries
	 * @return List locked countries
	 */
	@SuppressWarnings("unchecked")
	public List<LockedCountryBean> getLockedContries(String userId){
		List<LockedCountryBean> list = null;
		list = (List<LockedCountryBean>) this.queryList("FAD0021.getLockedContries", userId);
		return list;
	}
	
	/**
	 * Get Category and ItemCd list
	 * @return Category and ItemCd list
	 */
	@SuppressWarnings("unchecked")
	public List<ItemBean> getCategoryItemCd(){
		List<ItemBean> list = null;
		list = (List<ItemBean>) this.queryList("FAD0021.getCategoryItemCd");
		return list;
	}
	
	/**
	 * Get Country list
	 * @return Country list
	 */
	@SuppressWarnings("unchecked")
	public List<String> getCountries(){
		List<String> list = null;
		list = (List<String>) this.queryList("FAD0021.getCountries");
		return list;
	}

	 /**
     * Check exist stock
     * @return count of stock
     */
	public int checkExistStock(Pad0021Bean pad0021Bean){
        return this.queryCount("FAD0021.checkExistStock", pad0021Bean);
    }
}
