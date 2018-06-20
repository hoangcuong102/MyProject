<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="../common/common.jsp"></jsp:include>
<script src="<s:property value="contextPath" />/js/fab991/sab9921.js"
	type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href='<s:url value="/"/>style/fab991/sab9921.css' />
<script src="<s:url value="/"/>style/bootstrap3/js/bootstrap.js"
	type="text/javascript"></script>
</head>
<body>
	<div style="min-height: 620px; min-width: 1910px;">
		<div class="container-fluid">
			<div class="page-header banner">
				<div class="row">
					<div class="abc"></div>
				</div>
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<jsp:include page="../common/header.jsp"></jsp:include>
							<div class="col-md-10 padding-header-btn">
								<jsp:include page="../fab991/headerButton92.jsp"></jsp:include>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- content -->
			<div class="container-fluid">
				<div class="col-md-2 sidenav" id="mysidenav" >
					<s:include value="/jsp/common/menu_left.jsp" />
				</div>


				<div class="container-fluid col-md-10 main" >
					<div class="row title-row">
						<span class="span-icon-menu"><img alt="icon computer"
							src="<s:url value="/"/>images/icon_menu.png"> </span> <span
							class="current-screen-name">Item Master 2</span>
					</div>


					<form id="sab9921" class="form-horizontal">
						<div class="form-group">
							<label class="control-label col-md-2" for="email">Email:</label>
							<div class="col-md-6">
								<input type="email" class="form-control" id="email"
									placeholder="Enter email" name="email">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>


</body>
</html>