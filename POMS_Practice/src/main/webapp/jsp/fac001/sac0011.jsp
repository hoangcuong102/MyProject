<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<jsp:include page="../common/common.jsp"></jsp:include>
<script src="<s:property value="contextPath" />/js/fac001/sac0011.js"
	type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href='<s:url value="/"/>style/fac001/sac0011.css' />
<title><s:property value='getText("common_title")' /></title>
</head>
<body class="cl-body">
	<div style="min-height: 620px; min-width: 1900px;">
		<div class="container-fluid header">
			<div class="row">
				<div class="col-xs-12 banner">
					<div class="row header-margin">
						<div class="col-xs-12 user-login-css">
							<jsp:include page="../common/header.jsp"></jsp:include>
							<div class="col-xs-10 padding-header-btn">
								<jsp:include page="../fac001/headerButton11.jsp"></jsp:include>
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
							<div class="icon-menu menu_top_text_left zoom120">
								<span class="span_icon_menu"><img alt="icon computer"
									src="<s:url value="/"/>images/icon_menu.png"> </span><span
									class="span_text_menu">PC Order Entry</span>
							</div>
							<div style="clear: both"></div>
						</div>
					</div>
					<div class="content">
						<div class="container-ful" id="tableData">
							<table id="itemData" class="table-td-white">
								<tr class="col-md-12">
									<td class="col-md-44"><span style="color: red">* </span><strong>Company
											Code:</strong></td>
									<td class="col-md-45 item-name" id="errorCompany">
										<select class="form-control" id="companies"></select>
										<!-- <ul class="cdms_ul_error"><li></li></ul> -->
									</td>
									<td class="col-md-2 text-right padding-dept"><span style="color: red">*
									</span><strong>Department:</strong></td>
									<td class="col-md-45 item-name" id="errorDepartment">
										<%-- <select class="form-control" id="departments"></select> --%><!-- <ul class="cdms_ul_error"><li></li></ul> -->
										<div class="div-relative">
											<div class="row form-control display-content-middle div-dept">
												<div class="dept-left">
													<span id="deptCd-span"></span>
													<br>
													<span id="deptName-span"></span>
												</div>
												<div class="dept-right"></div>
											</div>
											<select class="form-control" id="departments"></select>
										</div>
									</td>
									<td class="col-md-44"><strong>Order No:</strong></td>
									<td class="col-md-45 item-name"><fieldset disabled>
											<input type="text" style="width: 156px;" class="form-control"
												id="orderId" readonly>
										</fieldset></td>
									<td class="col-md-44"><strong>Quotation No:</strong></td>
									<td class="col-md-45 item-name"><fieldset disabled>
											<input type="text" style="color: black; width: 300px;"
												class="form-control" id="quotationNo" readonly>
										</fieldset></td>
								</tr>
								<tr class="col-md-12" id="error-sac0011">
									<td class="col-md-44"></td>
									<td class="col-md-45 item-name" id="companyError"><ul class="cdms_ul_error"><li></li></ul></td>
<!-- 									<td class="col-md-2 text-right"></td> -->
									<td class="col-md-45 item-name" id="departmentError" colspan="2"><ul class="cdms_ul_error"><li></li></ul></td>
									<td class="col-md-44"></td>
									<td class="col-md-45 item-name"></td>
									<td class="col-md-44"></td>
									<td class="col-md-45 item-name"></td>
								</tr>
							</table>
							<div id="mainData"></div>
							<div class="col-xs-12"></div>
						</div>
					</div>
					<br>
					<div class="col-md-12 line-01"></div>
					<div class="col-md-12 line-02"></div>
					<div class="col-md-3">
						<strong class="color-black"> Remark </strong><br>
						<textarea rows="9" cols="59" id="remark">

							</textarea>
					</div>
					<div class="col-md-6">
						<div class="text-right">
							<strong class="color-black">Total Amount</strong>
						</div>
						<div class="red-star strong">
							Note: <br>
							<div style="padding-left: 10px">
								The following software is installed in all PCs. <br> 
								Sankyu SEA separately charge these software usage fee to each companies. <br> 
								1)&nbsp;&nbsp;FireEye Endpoint Protection (Anti-Virus/ Anti Cyber Attack Software) <br> 
								2)&nbsp;&nbsp;ISM CloudOne - Inventory/Vunerability/PC Remote Control Management Software <br> 
								3)&nbsp;&nbsp;ISM CloudOne - PC Operation Log Collection Software
							</div>
						</div>

					</div>
					<div class="col-md-3 strong">
						<div class="col-md-9 amount color-black">One Time Charge</div>
						<div class="col-md-3"></div>
						<div class="col-md-9">
							<input type="text"
								class="form-control one-time-charge text-right color-black"
								id="totalOneTomeCharge" readonly>
						</div>
						<div class="col-md-3 text-left color-black SGD">SGD</div>
						<div class="col-md-9 amount color-black">Monthly Recurring Fee (Lease)</div>
						<div class="col-md-3"></div>
						<div class="col-md-9">
							<input type="text" style="background-color: #F5AB9B;"
								class="form-control text-right color-black" id="monthlyRecurring" readonly>
						</div>
						 <div class="col-md-3 text-left color-black SGD">SGD</div>
						<!--<div class="col-md-9 amount">FireEye Endpoint Protection</div>
						<div class="col-md-3"></div>
						<div class="col-md-9">
							<input type="text" style="background-color: #F5AB9B;"
								class="form-control text-right color-black" id="fireEyeEdgeXSoftware"
								readonly>
						</div>
						<div class="col-md-3 text-left color-black SGD">SGD</div>
						<div class="col-md-9 amount color-black">ISM CloudOne PC Monitoring Software</div>
						<div class="col-md-3"></div>
						<div class="col-md-9">
							<input type="text" style="background-color: #F5AB9B;"
								class="form-control text-right color-black" id="pcMonitoringSoftware"
								readonly>
						</div>
						<div class="col-md-3 text-left color-black SGD">SGD</div> -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<s:form action="sac0011_04.action" id="formDownLoad">
		<input type="hidden" name="orderId" id="orderIdDowload">
		<input type="hidden" name="filePath" id="filePath">
		<input type="hidden" name="fileNameDownload" id="fileNameDownload">
	</s:form>
	<s:form action="sac0011_04.action" id="formDownLoad_2">
		<input type="hidden" name="orderId" id="orderIdDowload_2">
		<input type="hidden" name="filePath" id="filePath_2">
		<input type="hidden" name="fileNameDownload" id="fileNameDownload_2">
	</s:form>
	<s:form action="sac0011_08.action" id="formDownLoad_pdf">
		<input type="hidden" name="fileNameDownload" id="fileNameDownload_pdf">
	</s:form>
	</div>
		<script>
		/**
		flagEdit = ${flagEdit};
		****/
			flagEdit = ${flagEdit};
			/*******/
			orderId = '${orderId}';
		</script>

	<jsp:include page="popupSac0011.jsp"></jsp:include>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	</div>

</body>
</html>