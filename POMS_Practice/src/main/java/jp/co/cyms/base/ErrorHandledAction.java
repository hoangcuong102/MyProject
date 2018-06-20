package jp.co.cyms.base;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

/**
 * @author luanvv
 *
 */
public abstract class ErrorHandledAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void setErrorResult() {
		HttpServletResponse response = ServletActionContext.getResponse();
		if(response==null){
			return;
		}
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	}
}
