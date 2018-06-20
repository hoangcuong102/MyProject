<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href='<s:property value="contextPath" />/style/bootstrap3/css/bootstrap.css'/>
<link rel="stylesheet" type="text/css" href='<s:property value="contextPath" />/style/style.css'/>
<link rel="stylesheet" type="text/css" href='<s:property value="contextPath" />/style/faa001/saa0011.css' />
<script src="<s:property value="contextPath" />/js/common/jquery-1.9.1.js" type="text/javascript"></script>
<script src="<s:property value="contextPath" />/js/faa001/saa0011.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="../style/select2/select2.min.css" />
<script src="<s:url value="/"/>js/common/select2.min.js" type="text/javascript"></script>
<link rel="icon" href="../images/Fabicon.png">
<title><s:property value='getText("common_title")' /></title>
</head>
<body class="login-bg">
	<div id="pageLogin">
		<div class="header-wrapper">
			<div class="login-box">
				<div class="login-form">
					<form action='<s:property value="contextPath" />/faa001/saa0011_01.action' method="post" style="margin-top:292px;">
						<div class="message_space">
							<s:include value="/jsp/common/message.jsp" />
						</div>
						<div class="row">
							<div class="col-md-4 col-sm-4" style="margin-left: 19%; margin-top: 2.2%;">
							</div>
						</div>
						<div class="row" style="margin-top:10%;">
							<div class="col-md-4 col-sm-4" style="margin-left: 26%;">
								<s:textfield style="padding-left: 1%" name="userId" id="userId" cssClass="login-input" maxlength="10" placeholder="User Name" tabindex="1"></s:textfield>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4 col-sm-4" style="margin-top: 8.5%; margin-left: 26%;">
								<s:password style="padding-left: 1%" name="userPassword" id="userPassword" cssClass="login-input" placeholder="Password" maxlength="15" tabindex="2"></s:password>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4 col-sm-4" style="margin-top: 75px;margin-left: 19.5%">
								<input class="login-button" id="btn-submit" type="submit" value="" tabindex="3">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>