/*
 * CLASS   : BaseInvalidAuthorityException
 * PACKAGE : jp.co.cyms.base.exception
 * VERSION : 1.0
 * HISTORY : 2014-08-01　LSC　LU CREATE
 */
package jp.co.cyms.base.exception;
import jp.co.cyms.apps.faa001.bean.UserBean;
import jp.co.cyms.common.Language;

/**
 * BaseInvalidAuthorityException
 *
 * @author LSC LU
 * @since 2014/08/01
 */
@SuppressWarnings("serial")
public class BaseInvalidAuthorityException extends Exception {

	/**
	 * BaseInvalidAuthorityException
	 *
	 * @param userInfo UserBean
	 * @param actionId String
	 * @throws Exception decode abnormal
	 */
	public BaseInvalidAuthorityException(UserBean userInfo, String actionId) throws Exception {
		super(Language.getMessage("error_exception_00001", new String[] {userInfo.getUserId(), actionId}));
	}
}
