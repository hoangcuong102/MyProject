package jp.co.cyms.apps.fab991.bl;

import java.util.List;

import jp.co.cyms.apps.fab991.bean.Pab9921Bean;
import jp.co.cyms.apps.fab991.dao.Pab9921Dao;
import jp.co.cyms.base.BaseLogic;

public class Pab9921BL extends BaseLogic {
	
/*
Get Category_CD 
*/
	public List<String> getListCategory_CD() throws Exception{
		Pab9921Dao dao = new Pab9921Dao();
		List<String> list = dao.getListCategory_CD();
		return list.size() > 0 ? list : null  ;
	}
	
/*
 * GET CategoryName*/
	public String getCategoryName(Pab9921Bean pab9921Bean) throws Exception{
		Pab9921Dao dao = new Pab9921Dao();
		return dao.getCategoryName(pab9921Bean);
	}
/*
 * Get List Item_CD*/
	public List<String> getListItem_CD(Pab9921Bean pab9921bean) throws Exception{
		Pab9921Dao dao = new Pab9921Dao();
		List<String> list = dao.getListItem_CD(pab9921bean);
		return list.size() > 0 ? list : null  ;
	}
	
// Get ItemName
	public Pab9921Bean getItemByItemCD_CategoryCD(Pab9921Bean pab9921Bean) throws Exception{
		Pab9921Dao dao = new Pab9921Dao();
		return dao.getItemByItemCD_CategoryCD(pab9921Bean);
	}
}
