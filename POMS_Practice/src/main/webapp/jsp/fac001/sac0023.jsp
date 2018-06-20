<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<jsp:include page="../common/common.jsp"></jsp:include>
<script src="<s:property value="contextPath" />/js/fac001/sac0023.js"
	type="text/javascript"></script>
<script
	src="<s:property value="contextPath" />/js/common/dataTables.fixedColumns.min.js"
	type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href='<s:url value="/"/>style/fac001/sac0023.css' />
<title><s:property value='getText("common_title")' /></title>
</head>
<body class="cl-body">
	<div style="min-height: 620px; min-width: 1368px;">
		<div class="container-fluid header">
			<div class="row">
				<div class="col-xs-12 banner">
					<div class="row header-margin">
						<div class="col-xs-12 user-login-css">
							<jsp:include page="../common/header.jsp"></jsp:include>
							<div class="col-xs-10 padding-header-btn">
								<jsp:include page="../fac001/headerButton23.jsp"></jsp:include>
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
									class="span_text_menu"> Order Log</span>
							</div>
							<div style="clear: both"></div>
						</div>
					</div>
					<div class="content">
						<div class="container-ful" id="tableData">
							<table id="itemData" class="table-td-white">
								<tr class="col-md-12">
									<td class="col-md-44 font-bold order-id-label">Order ID:</td>
									<td class="col-md-45 item-name"><fieldset disabled>
											<input id="orderId" type="text" class="form-control color-black input-order-id" value="${orderId}" readonly>
										</fieldset></td>
									<td class="col-md-44 font-bold">Quotation No:</td>
									<td class="col-md-45 item-name"><fieldset disabled>
											<input id="quotationNo" type="text" class="form-control input-quotation"
												readonly="true" value="">
										</fieldset></td>
								</tr>
							</table>

							<div class="col-md-7" id="div-table-result" onresize="divResize()">
								<table class="order-column row-border hover data-table"
									style="table-layout: fixed;" id="resultTable" width="100%">
									<thead>
										<tr>
											<th width="17%">Updated At</th>
											<th width="13%">Updated By</th>
											<th width="24%">From</th>
											<th width="10%">Status</th>
											<th width="15%">Type</th>
											<th width="8%">Quantity</th>
										</tr>
									</thead>
									<tbody width="100%">
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="../common/footer.jsp"></jsp:include>
		</div>
</body>
</html>