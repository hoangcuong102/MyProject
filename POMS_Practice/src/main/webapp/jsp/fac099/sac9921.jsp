<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="../common/common.jsp"></jsp:include>
<script
	src="<s:property value="contextPath" />/js/common/bootstrap-datepicker.js"
	type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href='<s:property value="contextPath" />/style/fac099/sac9921.css' />
<link rel="stylesheet" type="text/css"
	href='<s:property value="contextPath" />/style/bootstrap-datepicker.css' />
<title><s:property value='getText("common_title")' /></title>
<link rel="icon" href="../images/Fabicon.png">
</head>

<body class="cl-body">
	<!-- keep display ratio in low-res display -->
	<div style="min-height: 620px; min-width: 1900px;">
		<!-- Header -->
		<div class="container-fluid header">
			<div class="row">
				<div class="col-xs-12 banner">
					<div class="row header-margin">
						<div class="col-xs-12 user-login-css">
							<jsp:include page="../common/header.jsp"></jsp:include>
							<div class="col-xs-10 padding-header-btn">
								<jsp:include page="../fac099/headerButton21.jsp"></jsp:include>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container-fluid wrapper main_center">
			<div class="main_flex row">
				<!-- Left Menu -->
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
					<div class="message" style="display: none;">
						<s:actionerror />
					</div>
					<div class="content">
						<!-- Filter -->
						<div class="container-fluid">
							<div class="form-inline row">
								<!-- Company Code -->
								<div class="col-md-2 form-group">
									<label class="text-stylish-tool-bar"> Company Code: <s:if
											test="%{userInfo.userAuthorityCd != 1}">
											<select class="form-control" id="form_cc" tabindex="1">
												<option value=""></option>
												<s:iterator var="code" value="company_code">
													<option value="<s:property value="code" />"><s:property
															value="code" /></option>
												</s:iterator>
											</select>
										</s:if> <s:else>
											<select class="form-control" id="form_cc" disabled="disabled"
												tabindex="1">
												<s:iterator var="code" value="company_code">
													<option value="<s:property value="code" />"><s:property
															value="code" /></option>
												</s:iterator>
											</select>
										</s:else>
									</label>
								</div>
								<!-- <div class="col-md-1 form-group"></div> -->
								<div class="col-md-3 form-group">
									<label class="text-stylish-tool-bar"> Entry Date: </label> <label
										class="text-slylish-tool-bar"> From: </label>
									<div class='input-group date' id='datetimepickerF'>
										<input type='text' class="form-control"
											placeholder="DD/MM/YYYY" tabindex="2" /> <span
											class="input-group-addon"> <span
											class="glyphicon glyphicon-calendar"></span>
										</span>
									</div>
								</div>
								<div class="col-md-2 form-group">
									<label class="text-slylish-tool-bar"> To: </label>
									<div class='input-group date' id='datetimepickerT'>
										<input type='text' class="form-control"
											placeholder="DD/MM/YYYY" tabindex="3" /> <span
											class="input-group-addon"> <span
											class="glyphicon glyphicon-calendar"></span>
										</span>
									</div>
								</div>
								<div class="col-md-2 form-group">
									<div class="checkbox">
										<label><input type="checkbox" tabindex="4">
											Only Non-Delivery </label>
									</div>
								</div>
								<div class="col-md-1 form-group">
									<div class="checkbox">
										<label><input type="checkbox" tabindex="5">
											Expiring Orders </label>
									</div>
								</div>
								<div class="col-md-2 text-right">
									<button type="submit" class="btn btn-primary">Search</button>
									<button type="button" class="btn btn-primary">Clear</button>
								</div>
							</div>
							<hr>
						</div>
						<!-- Data table -->
						<table id="data_demo" class="display pageResize table-boder">
							<thead class="">
								<tr class="">
									<th class="text-center white-bg"></th>
									<th class="text-center white-bg"></th>
									<th class="text-center white-bg"></th>
									<th class="text-center white-bg"></th>
									<th class="text-center white-bg"></th>
									<th class="text-center white-bg"></th>
									<th class="text-center white-bg"></th>
									<th class="text-center white-bg"></th>
									<th class="text-center white-bg"></th>
									<th class="text-center white-bg"></th>
									<th class="text-center light-blue-bg" colspan="2">Sankyu</th>
									<th class="text-center light-green-bg">SEA</th>
									<th class="text-center pink-bg" colspan="2">KDDI SG</th>
									<th class="text-center yellow-bg" colspan="4">KDDI</th>
								</tr>
								<tr class="">
									<th class="text-center light-blue-bg w-1"><label><input
											type="radio" name="optradio"></label></th>
									<th class="text-center light-blue-bg w-3">Order</th>
									<th class="text-center light-blue-bg w-10">Dept Code</th>
									<th class="text-center light-blue-bg w-3">Amt(M)</th>
									<th class="text-center light-blue-bg w-3">Amt(O)</th>
									<th class="text-center light-blue-bg w-4">Type</th>
									<th class="text-center light-blue-bg w-3">Qty</th>
									<th class="text-center light-blue-bg w-4">Last Updated</th>
									<th class="text-center light-blue-bg w-4">Updated By</th>
									<th class="text-center light-blue-bg w-4">Status</th>
									<th class="text-center light-blue-bg w-4">Regist</th>
									<th class="text-center light-blue-bg w-4">Order</th>
									<th class="text-center light-green-bg w-4">Request</th>
									<th class="text-center pink-bg w-4">Accept</th>
									<th class="text-center pink-bg w-4">Order</th>
									<th class="text-center yellow-bg w-4">Receive</th>
									<th class="text-center yellow-bg w-8">Deliver</th>
									<th class="text-center yellow-bg w-4">Lease</th>
								</tr>
							</thead>
							<tbody class="">
								<tr class="">
									<td class="text-center "><label><input
											type="radio" name="optradio"></label></td>
									<td class="text-center ">YY-NNN</td>
									<td class="text-center ">XXXXXX1</td>
									<td class="text-center ">9,999.99</td>
									<td class="text-center ">9,999.99</td>
									<td class="text-center ">DP</td>
									<td class="text-center ">99</td>
									<td class="text-center ">DD-MMM-YY</td>
									<td class="text-center ">XXXXXX1</td>
									<td class="text-center ">"B" "N"</td>
									<td class="text-center ">dd-mmm</td>
									<td class="text-center ">dd-mmm</td>
									<td class="text-center ">dd-mmm</td>
									<td class="text-center ">dd-mmm</td>
									<td class="text-center ">dd-mmm</td>
									<td class="text-center ">dd-mmm</td>
									<td class="text-center ">
										<div style="display: flex; justify-content: space-around;">
											<div class="text-center">99</div>
											<div class="text-center">dd-mmm</div>
										</div>
									</td>
									<td class="text-center ">dd-mmm</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<jsp:include page="../common/footer.jsp"></jsp:include>
		</div>
	</div>
	<script src="<s:property value="contextPath" />/js/fac099/sac9921.js"
		type="text/javascript"></script>
</body>
</html>