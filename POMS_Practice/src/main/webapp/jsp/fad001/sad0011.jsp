<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="../common/common.jsp"></jsp:include>
<script src="<s:property value="contextPath" />/js/fad001/sad0011.js"
	type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href='<s:url value="/"/>style/fad001/sad0011.css' />
<script src="<s:url value="/"/>style/bootstrap3/js/bootstrap.js"
	type="text/javascript"></script>
<title><s:property value='getText("common_title")' /></title>
</head>
<body class="cl-body">
	<div style="min-height: 620px; min-width: 1910px;">
		<div class="container-fluid header">
			<div class="row">
				<div class="col-xs-12 banner">
					<div class="row header-margin">
						<div class="col-xs-12 user-login-css">
							<jsp:include page="../common/header.jsp"></jsp:include>
							<div class="col-xs-10 padding-header-btn">
								<jsp:include page="../fad001/headerButtonSad0011.jsp"></jsp:include>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container-fluid wrapper main_center">
			<div class="main_flex row">
				<div class="left left-side col-xs-2"
					style="display: ${menuInfo!=null && menuInfo.size() > 0 ? 'block' : 'none'};min-width: 333px;">
					<s:include value="/jsp/common/menu_left.jsp" />
				</div>
				<div class="center right-side col-xs-12" style="min-height: 600px">
					<div class="row title-row">
						<div class="container-fluid">
							<div class="icon-menu menu_top_text_left">
								<span class="span_icon_menu"><img alt="icon computer"
									src="<s:url value="/"/>images/icon_menu.png"> </span><span
									class="span_text_menu"><s:property
										value="currentScreenName" /></span>
							</div>
							<div style="clear: both"></div>
						</div>
					</div>
					<div class="container-fluid col-xs-8" style="margin-left: 20px;">
						<div class="message">
							<s:actionerror />
						</div>
						<form id="formSad0011">
							<div class="col-xs-6" id="monthlySummary">
								<div class="row">
									<div class="col-xs-12 radio">
										<label class="radio-download"> <input type="radio"
											name="outputData" value="0" id="outputLeaseInvoiceData"
											checked="checked" /> PC Lease Monthly Summary
										</label>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 form-group text-right no-padding-right">
										<label
											class="control-label form-control no-border-form-control no-padding-right"><span
											style="color: red;">*&nbsp;</span>Year: </label>
									</div>
									<div class="col-xs-4">
										<select id="year" name="year"
											class="form-control style-select style-select-middle">
										</select>
									</div>
								</div>
								<div class="row" id="divMonth">
									<div class="col-xs-4 form-group text-right no-padding-right">
										<label
											class="control-label form-control no-border-form-control no-padding-right"><span
											style="color: red;">*&nbsp;</span>Month: </label>
									</div>
									<div class="col-xs-4">
										<select id="month" name="month"
											class="form-control style-select style-select-middle">
										</select>
									</div>
								</div>
							</div>
							<div class="col-xs-6" id="divYearly">
								<div class="row">
									<div class="col-xs-12 radio">
										<label class="radio-download"> <input type="radio"
											name="outputData" value="0" id="outputLeaseInvoiceDataYearly"
											/> PC Lease Invoice Data Download (Yearly)
										</label>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-4 form-group text-right no-padding-right">
										<label
											class="control-label form-control no-border-form-control no-padding-right"><span
											style="color: red;">*&nbsp;</span>Year: </label>
									</div>
									<div class="col-xs-4">
										<select id="yearly" name="year"
											class="form-control style-select style-select-middle">
										</select>
									</div>
								</div>
							</div>
							
							
							<div class="col-xs-12">
								<div class="row">
									<div class="col-xs-12 radio">
										<label class="radio-download"> <input type="radio"
											name="outputData" value="1" id="outputLeaseData" /> PC Lease
											Data Download
										</label>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-2 form-group text-right no-padding-right">
										<label
											class="control-label form-control no-border-form-control no-padding-right"><span
											style="color: red;"></span>Country Code: </label>
									</div>
									<div class="col-xs-2">
										<select id="country" name="country"
											class="form-control style-select">
										</select>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-2 form-group text-right no-padding-right">
										<label
											class="control-label form-control no-border-form-control no-padding-right"><span
											style="color: red;"></span>Company Code: </label>
									</div>
									<div class="col-xs-2">
										<select id="company" name="company"
											class="form-control style-select">
										</select>
									</div>
								</div>

								<div class="row">
									<div class="col-xs-offset-1 col-xs-11">
										<label class="checkbox status-active"><input
											type="checkbox" id="statusActive" name="statusActive">Only
											Lease Status = Active</label>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<jsp:include page="../common/footer.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>