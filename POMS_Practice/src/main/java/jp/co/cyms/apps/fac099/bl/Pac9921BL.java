package jp.co.cyms.apps.fac099.bl;

import java.util.ArrayList;
import java.util.List;

import jp.co.cyms.apps.faa001.bean.UserBean;
import jp.co.cyms.apps.fac099.bean.OrderProgressBean;
import jp.co.cyms.apps.fac099.bean.Pac9921Bean;
import jp.co.cyms.apps.fac099.dao.Pac9921DAO;

public class Pac9921BL {
	// get return list Company Code
	public List<String> getCompanyCode(UserBean userbean) {
		List<String> list = new ArrayList<>();
		if (userbean.getUserAuthorityCd() != "1")
			list = new Pac9921DAO().getCompanyCode();
		else 
			list.add(userbean.getUserCompanyCd());
		return list;
	}
	
	public List<OrderProgressBean> getOrderProgress(Pac9921Bean condition, UserBean userbean) {
		List<OrderProgressBean> list = new ArrayList<>();
		if(condition == null) condition = new Pac9921Bean();
		if (userbean.getUserAuthorityCd() == "1") {
			condition.setCompanyCode(userbean.getUserCompanyCd());
			list = new Pac9921DAO().getOrderProgressList(condition);
		}
		else
			list = new Pac9921DAO().getOrderProgressList(condition);
		// TODO: Get OrderProgressBean
		// TODO: Check USER_AUTHORITY
		return list;
	}
}