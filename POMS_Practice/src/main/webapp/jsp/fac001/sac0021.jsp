<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<jsp:include page="../common/common.jsp"></jsp:include>
<script src="<s:property value="contextPath" />/js/fac001/bootstrap-toggle.min.js"
	type="text/javascript"></script>
<script src="<s:property value="contextPath" />/js/fac001/sac0021.js"
	type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href='<s:url value="/"/>style/fac001/bootstrap-toggle.min.css' />
<link rel="stylesheet" type="text/css"
	href='<s:url value="/"/>style/fac001/sac0021.css' />
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
								<jsp:include page="../fac001/headerButton21.jsp"></jsp:include>
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
									class="span_text_menu"><s:property value="currentScreenName"/></span>
							</div>
							<div style="clear: both"></div>
						</div>
					</div>
				   <div class="message">
				      <s:actionerror/>
				   </div>
					<div class="content">
						<div class="container-ful" id="tableData">
							<input type="hidden" value="<s:property value="userAuthority"/>" id="authority"/>
							<table id="itemData" class="table-td-white">
								<tr class="col-md-12 strong">
									<td class="col-md-44-a">
                           <%-- <span class="red-star">*&nbsp;</span>  --%>
										Company Code:
									</td>
									<td class="col-md-45 item-name">
										<s:if test="userInfo.userAuthorityCd == 1">
											<select class="form-control color-black" id="companyCd" disabled="disabled">
												<option value="<s:property value="companyCd"/>"><s:property value="companyCd"/></option>
											</select>
										</s:if>
										<s:else>
											<select class="form-control color-black" id="companyCd">
												<option value=""></option>
												<s:iterator value="listCompanyCd">
													<option value="<s:property/>"><s:property/></option>
												</s:iterator>
											</select>
										</s:else>
									</td>
									<td class="col-md-44-a">Entry Date:</td>
									<td class="col-md-44">From:</td>
									<td class="col-md-45 item-name">
										<div class="input-group input-group-from">
											<input type="text"
												class="form-control datepicker ipt-entry-date text-center"
												id="entryDateFrom" onclick="openDatePicker1(this)"
												readonly="true" placeholder="DD-MMM-YY" maxlength="10"/>
											<span class="input-group-btn">
												<button id="btn-entryDateFrom" class="btn btn-default btn-entry-date"
													type="button" onclick="openDatePicker(this)">
													<span class="glyphicon glyphicon-calendar"></span>
												</button>
											</span>
										</div>
									</td>
									<td class="col-md-44">To:</td>
									<td class="col-md-45 item-name">
										<div class="input-group input-group-from">
											<input type="text"
												class="form-control datepicker ipt-entry-date text-center"
												id="entryDateTo" onclick="openDatePicker1(this)"
												readonly="true" placeholder="DD-MMM-YY" maxlength="10"/>
											<span class="input-group-btn">
												<button id="btn-entryDateTo" class="btn btn-default btn-entry-date"
													type="button" onclick="openDatePicker(this)">
													<span class="glyphicon glyphicon-calendar"></span>
												</button>
											</span>
										</div>
									</td>
									<td class="col-md-non-delivery">
										<div class="checkbox col-md-12">
											<label>
												<input type="checkbox" id="nonDelivery"> Only Non-Delivery
											</label>
										</div>

									</td>
									<td class="col-md-expiring">
										<div class="checkbox col-md-12">
											<label>
												<input type="checkbox" id="expiringOrders"> Expiring Orders
											</label>
										</div>
									</td>
									<td class="col-md-2 text-left padding-btn-clear-search">
										<div class="col-md-1-button vertical-bottom">
											<button type="button" class="btn-search btn btn-primary" onclick="doSearch();">Search</button>
										</div>
										<div class="col-md-1-button">
											<button type="button" class="btn-clear btn btn-primary">Clear</button>
										</div>
									</td>
								</tr>
								<tr class="col-md-12" id="error-sac0021">
									<td class="col-md-44-a"></td>
									<td class="col-md-45 item-name" colspan="2" id="companyError">
									</td>
									<td class="col-md-44"></td>
									<td class="col-md-45 item-name" colspan="2" id="fromError">
									</td>
									<td class="col-md-45 item-name" id="toError">
									</td>
									<td class="col-md-2"></td>
									<td class="col-md-2 text-left"></td>
								</tr>
							</table>
							<div class="col-md-12 line">
								<div class="line-01"></div>
								<div class="line-02"></div>
								<div class="line-03"></div>
								<div class="line-04"></div>
							</div>
							<div class="" id="table-view" style="display: none;">
								<table class="order-column row-border hover data-table"
									style="table-layout: fixed;" id="resultTable" width="100%">
									<thead class="th-none">
										<tr>
											<th width="2%" class="hidden-header"></th>
											<th width="5%" class="hidden-header"></th>
											<th width="8%" class="hidden-header"></th>
											<th width="6%" class="hidden-header"></th>
											<th width="6%" class="hidden-header"></th>
											<th width="7%" class="hidden-header"></th>
											<th width="3%" class="hidden-header"></th>
											<th width="7%" class="hidden-header"></th>
											<th width="7%" class="hidden-header"></th>
											<th width="6%" class="hidden-header"></th>
											<th width="10%" class="border-top border-left">Sankyu</th>
											<th width="5%" class="border-top header-sea">SEA</th>
											<th width="10%" class="border-top header-kddi-sg">KDDI SG</th>
											<th width="18%" class="border-top header-kddi">KDDI</th>
											<th width="0%" class="hidden-column">DeleteFg</th>
										</tr>
										<tr>
											<th width="2%" class="border-top">#</th>
											<th width="5%" class="border-top">Order</th>
											<th width="8%" class="border-top">Department</th>
											<th width="6%" class="border-top">Amt(M)</th>
											<th width="6%" class="border-top">Amt(O)</th>
											<th width="7%" class="border-top">Category</th>
											<th width="3%" class="border-top">Qty</th>
											<th width="7%" class="border-top">Last Updated</th>
											<th width="7%" class="border-top">Updated By</th>
											<th width="6%" class="border-top">Status</th>
											<th width="10%">
												<div class="col-md-6 th-border-right display-content-middle">Regist</div>
												<div class="col-md-6 th-non-border-right display-content-middle">Order</div>
											</th>
											<th width="5%" class="header-sea">Request</th>
											<th width="10%" class="header-kddi-sg">
												<div class="col-md-6 th-border-right display-content-middle">Accept</div>
												<div class="col-md-6 th-non-border-right display-content-middle">Order</div>
											</th>
											<th width="18%" class="header-kddi">
												<div class="col-md-3 th-border-right display-content-middle">Receive</div>
												<div class="col-md-6 th-border-right th-border-left display-content-middle">Deliver</div>
												<div class="col-md-3 th-non-border-right display-content-middle">Lease</div>
											</th>
											<th width="0%" class="hidden-column">DeleteFg</th>
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
			<!-- Modal confirm expiring orders-->
			<div class="modal fade" id="modalConfirmExpiringOrders" role="dialog" data-keyboard="false" data-backdrop="static">
			    <div class="modal-dialog">
			      <!-- Modal content-->
			      <div class="modal-content" style="margin-right: 50px;  margin-left: 70px;">
			        <div class="modal-header" style="padding:0px 10px;">
			          <button type="button" class="close" onclick="doCloseConfirmExpiringOrders();">&times;</button>
			          <h4><span class="glyphicon glyphicon-question-sign"></span>    Confirmation</h4>
			        </div>
			        <div class="modal-body" >
			          <form role="form">
			            <div class="row">
			            	<div class="col-md-12 text-left form-group required no-padding-right">
			              		<p class="p-message"><img src="<s:url value="/"/>style/imgs/confirm.png" width="30" height="30"><span id="contentConfirmExpiringOrders" style="margin-left: 15px;"></span></p>
			              	</div>
			            </div>
						<div class="row">
							<div class="col-md-4"></div>
							<div class="col-md-4">
								<button type="button" class="btn btn-primary center-block btn-block" id="buttonYesExpiringOrders" data-toggle="modal" data-dismiss="modal" onclick="doCloseConfirmExpiringOrders();">OK</button>
							</div>
							<!-- <div class="col-md-4">
								<button type="button" class="btn btn-primary center-block btn-block btn-grey" id="buttonNoExpiringOrders" onclick="doCloseConfirmOverwrite();">No</button>
							</div> -->
						</div>
			          </form>
			        </div>
			      </div>
			    </div>
			</div>
			<!-- Modal confirm end-->
		</div>

</body>
</html>