package jp.co.cyms.base.interceptors;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import jp.co.cyms.apps.faa001.action.Paa0011Action;
import jp.co.cyms.apps.faa001.action.Paa0012Action;
import jp.co.cyms.apps.faa001.bean.MenuQueryParam;
import jp.co.cyms.apps.faa001.bean.UserBean;
import jp.co.cyms.apps.faa001.bl.PermissionValidator;
import jp.co.cyms.common.Constant;

import java.util.Map;

/**
 * Check permission when invoke action
 * @author longnd
 *
 */

@SuppressWarnings("serial")
public class PermissionInterceptor implements Interceptor {
	private ActionInvocation invocation;
	private PermissionValidator permissionValidator;

	@Override
	public void destroy() {

	}

	@Override
	public void init() {

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		this.invocation = invocation;
		if (isPassCheckAction()) {
			return invocation.invoke();
		} else {
			return Action.LOGIN;
		}
	}

	private PermissionValidator getPermissionValidator() {
		Map<String, Object> sessionAttributes = invocation.getInvocationContext().getSession();
		UserBean userInfo = (UserBean) sessionAttributes.get(Constant.SESSION_KEY_USER_INFO);
		String rawActionName = invocation.getInvocationContext().getName();
		String functionId = invocation.getProxy().getNamespace().substring(1);
		String actionName = rawActionName.split("_")[0];
		return new PermissionValidator(userInfo, new MenuQueryParam(functionId, actionName));
	}

	private boolean isLoginAction() {
		return invocation.getAction().getClass().equals(Paa0011Action.class)
				|| invocation.getAction().getClass().equals(Paa0012Action.class);
	}

	private boolean isPassCheckAction() throws Exception {
		if (isLoginAction()) {
			return true;
		}
		permissionValidator = getPermissionValidator();

		if (permissionValidator.check()) {
			return true;
		}
		return false;
	}
}
