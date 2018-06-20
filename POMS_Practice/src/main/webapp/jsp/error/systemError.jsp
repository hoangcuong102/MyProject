<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="../common/common.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href='<s:url value="/"/>style/faa001/saa0012.css' />
<title><s:property value='getText("common_title")' /></title>
</head>
<body>
<div style="min-height: 620px;min-width: 1900px;">
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container-fluid wrapper main_center">
		<div class="main_flex row">
			<div class="left left-side col-sm-2">
				<s:include value="/jsp/common/menu_left.jsp" />
			</div>
			<div class="center right-side col-sm-10" style="min-height: 600px">
				<div class="row title-row">
					<div class="container-fluid">
						<div class="icon-menu menu_top_text_left">
							<span class="span_icon_menu"><img alt="icon computer"
								src="<s:url value="/"/>images/icon_menu.png"> </span><span
								class="span_text_menu">ERROR</span>
						</div>
						<div class="menu_top_buttom_right" style="display: none">
							<div class="btn-group">
								<a href="#" class="btn-delete btn-function-square btn-block">Delete</a>
							</div>
							<div class="btn-group">
								<a href="#" class="btn-new btn-function-square btn-block">New</a>
							</div>
						</div>
						<div style="clear: both"></div>
					</div>
				</div>
				<div class="content">

					<div style="height: 50px">
						<div></div>
						<div>
							<img src="../style/images/exception.png" width="30"> <b
								style="color: red; font-size: 20px; margin: 5px">System Error</b>
						</div>
					</div>
					<div>												
						<div></div>
						<div>
							<div>
								<s:property value='getText("infodialog_title_error")'/> : <s:property value="exception.message" />
							</div>
						</div>
					</div>
					<div>
						<div></div>
						<div>
							<div
								style="height: 400px; width: 900px; overflow: scroll; white-space: nowrap;">
								<s:property value="exception" />
								<br>
								<s:iterator value="exception.stackTrace" var="trace">
									<s:property value="#trace.className" />.<s:property
										value="#trace.methodName" />(<s:property
										value="#trace.fileName" />:<s:property
										value="#trace.lineNumber" />)
                                         			<br>
								</s:iterator>
							</div>
						</div>
					</div>					
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	</div>
</body>
</html>
