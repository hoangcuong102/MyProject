<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title><s:property value='label.page_title' /></title>
<%-- <%@ include file="../include/htmlheader.inc"%> --%>
<script src="../js/SAA0012.js" type="text/javascript"></script>
</head>
<body>
	<div id="page">
		<div id="content">
			<table class="main-table">
				<tr>
					<td id="left" valign="top"><%-- <%@ include
							file="../include/menu.inc"%> --%></td>
					<td id="gap-div"></td>
					<td id="right" valign="top">

						<div class="simpale-err-msg">
							Sorry! The data you want to operate has been changed by another
							person.<br/> Please click <a
								href="../<s:property value="exception.programId"/>/init.action">here</a>
							to operate again.
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>