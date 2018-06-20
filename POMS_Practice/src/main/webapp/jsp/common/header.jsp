<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<input type="hidden" id="contextPath" value="<s:url value="/"/>" />
<input type="hidden" id="confirmRedirectMenu" value="" />
<input type="hidden" id="userId" value="<s:property value="userInfo.userId" />" />
<div class="col-xs-2 border-right">
	<img src="<s:url value="/"/>images/icon_computer.png" class="images-header">
	Logged as:
	<s:property value="userInfo.userName" />
</div>