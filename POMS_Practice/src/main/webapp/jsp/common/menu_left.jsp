<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<form id="form_menu" action='<s:url value="/"/>faa001/saa0012_01.action'>
	<s:hidden name="redirectFunctionId" />
	<s:hidden name="redirectScreenId" />
</form>
<div id="menu" style="min-height: 65vh; margin-top: 5px;">
	<s:iterator value="menuInfo" status="rowstatus">
		<s:if test="orderDisplay==0">
			</ul>
			<h6 class="h6_menu_left">
				<span><s:property value="menuName" /></span>
			</h6>
			<ul>
		</s:if>

		<s:if test="orderDisplay > 0">
			<li
				class="menu_link menu_link_<s:property value='#rowstatus.count'/>"
				screen_id="<s:property value="screenId"/>"
				function_id="<s:property value="functionId"/>"><span>&nbsp;&nbsp;&rsaquo;</span>
				<span><s:property value="menuName" /></span></li>
		</s:if>
		<s:if test="orderDisplay == -1">
			<li class="space"><span></span></li>
		</s:if>
	</s:iterator>
</div>
<s:hidden id="menuDisplay"
	value="%{menuInfo!=null && menuInfo.size() > 0}" />

