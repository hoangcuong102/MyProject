package jp.co.cyms.apps.fab991.bl;

import java.util.List;

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
}
