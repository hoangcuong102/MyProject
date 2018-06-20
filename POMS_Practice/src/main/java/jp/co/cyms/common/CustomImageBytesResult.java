package jp.co.cyms.common;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;

import jp.co.cyms.common.action.ImageAction;

public class CustomImageBytesResult implements Result {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void execute(ActionInvocation invocation) throws Exception {

		ImageAction action = (ImageAction) invocation.getAction();
		HttpServletResponse response = ServletActionContext.getResponse();
		// Always set status ok
		response.setStatus(HttpStatus.SC_OK);
		if (action.getCustomImageInBytes() != null) {
			response.setContentType(action.getCustomContentType());
			response.getOutputStream().write(action.getCustomImageInBytes());
			response.getOutputStream().flush();
		}
	}
}