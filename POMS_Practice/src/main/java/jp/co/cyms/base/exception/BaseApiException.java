/*
 * CLASS   : BaseApiException
 * PACKAGE : jp.co.cyms.base.exception
 * VERSION : 1.0
 * HISTORY : 2014-08-01　LSC　LU CREATE
 */
package jp.co.cyms.base.exception;

/**
 * BaseApiException
 * 
 * @author LSC LU
 * @since 2014/08/01
 */
@SuppressWarnings("serial")
public class BaseApiException extends Exception {

	/**
	 * BaseApiException
	 * 
	 * @param ex Exception
	 * @throws Exception decode abnormal
	 * @author LSC LU
	 * @since 2014/08/01
	 */
	public BaseApiException(Exception ex) {
	    super(ex);
	}
}
