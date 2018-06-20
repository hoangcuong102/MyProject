package jp.co.cyms.base.interceptors;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import jp.co.cyms.apps.faa001.action.Paa0011Action;
import jp.co.cyms.apps.faa001.bean.UserBean;
import jp.co.cyms.common.Constant;

public class AuthenticationInterceptor implements Interceptor {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static final String LOGIN_REQUIRED_STATUS = "login-required";
	private static final Logger log = Logger.getLogger(AuthenticationInterceptor.class);
	@Override
	public void destroy() {

	}

	@Override
	public void init() {

	}

	public String intercept(ActionInvocation invocation) throws Exception {

		Map<String, Object> sessionAttributes = invocation.getInvocationContext().getSession();

		UserBean userInfo = (UserBean) sessionAttributes.get(Constant.SESSION_KEY_USER_INFO);
		if (userInfo == null) {
			if (invocation.getAction().getClass().equals(Paa0011Action.class)) {
				return invocation.invoke();
			}
			if(isMenuRedirectAction(invocation)){
				return Action.LOGIN;
			}
			if(isAjaxAction(invocation)){
				return doAuthenticationJson(invocation);
			}
			setSaveUrl(sessionAttributes);
			return Action.LOGIN;
		} else {
			if (invocation.getAction().getClass().equals(Paa0011Action.class) && !isLogoutAction(invocation)) {
				log.fatal("****************USER IS SIGNED IN ALREADY !******************");
				return "homeAction";
			}
			return invocation.invoke();
		}
	}
	private boolean isMenuRedirectAction(ActionInvocation invocation){
		String actionName = invocation.getInvocationContext().getName();
		return "saa0012_01".equals(actionName);
	}
	private void setSaveUrl(Map<String, Object> session){
		HttpServletRequest request = ServletActionContext.getRequest();
		String queryString = request.getQueryString();
		session.put("savedUrl", request.getRequestURI()+(queryString==null?"":("?"+queryString)));
	}
	private boolean isLogoutAction(ActionInvocation invocation){
		String actionName = invocation.getInvocationContext().getName();
		return "saa0011_99".equals(actionName);
	}
	private boolean isAjaxAction(ActionInvocation invocation){
		String actionName = invocation.getInvocationContext().getName();
		return isAjaxMethod(actionName);
	}
	private boolean isAjaxMethod(String actionName){
		String pattern= "^.+_\\d+$";
		return actionName.matches(pattern);
	}
	private String doAuthenticationJson(ActionInvocation invocation) throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().print(LOGIN_REQUIRED_STATUS);
        response.setContentType("application/json");
        return Action.NONE;
	}
}
