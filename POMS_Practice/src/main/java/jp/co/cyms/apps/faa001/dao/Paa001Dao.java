package jp.co.cyms.apps.faa001.dao;

import jp.co.cyms.apps.faa001.bean.*;
import jp.co.cyms.base.BaseDao;

/**
 * 
 * @author longnd
 *
 */
public class Paa001Dao extends BaseDao {
    /**
     *
     */
    public Paa001Dao() {
        super();
    }

    /**
     * Authority when login system
     *
     * @param bean
     * @return UserBean : information of user login
     */
    public UserBean authorityUser(UserBean bean) {
        return (UserBean) this.queryObject("FAA001.select01", bean);
    }
}
