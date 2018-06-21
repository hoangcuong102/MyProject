package jp.co.cyms.apps.fab991.dao;

import java.util.List;

import jp.co.cyms.base.BaseDao;

public class Pab9921Dao extends BaseDao {
	
	public Pab9921Dao() {
		// TODO Auto-generated constructor stub
		
	}	
	
//	Get list category_cd
	@SuppressWarnings("unchecked")
	public List<String> getListCategory_CD() throws Exception{
		List<String> list = null;
		list = (List<String>) this.queryList("FAB9921.select01");
		
		return list;
	}
	
	public static void main(String[] agvs) throws Exception {
		Pab9921Dao dao = new Pab9921Dao();
		dao.getListCategory_CD();
	}
}
