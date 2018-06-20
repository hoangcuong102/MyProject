<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<jsp:include page="../common/common.jsp"></jsp:include>
<script src="<s:property value="contextPath" />/js/fab001/sab0011.js"
	type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href='<s:url value="/"/>style/fab001/sab0011.css' />
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
								<jsp:include page="../fab001/headerButton11.jsp"></jsp:include>
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
					<div class="content">
						<div class="container-ful" id="tableData">
							<table id="itemData" class="table-td-white">
								<tr class="col-md-12">
									<td class="col-md-44"><span class="color-red">*&nbsp;</span>Item Code:
									</td>
									<td class="col-md-45 item-code"><input
										class="form-control alphanumberic" name="itemCode" id="itemCode" validate=true maxlength="30"/></td>
									<td><div class="checkbox text-left padding-left">
											<label> <input type="checkbox" id="addMemory" name="addMemory" disabled="disabled"/> Warning for Additional Memory
											</label><br> <label> <input type="checkbox" id="noUse" name="noUse" disabled="disabled"/>
												No Use
											</label>
										</div></td>
								</tr>
								<!-- <tr class="col-md-12" id="error1" style="display: none;">
									<td class="col-md-44">
									</td>
									<td class="col-md-45 item-code itemError" id="itemCdError">
									</td>
									<td>
									</td>
								</tr> -->
								<tr class="col-md-12" id="error1" style="display: none;">
									<td class="col-md-44">
									</td>
									<td class="col-md-45 item-name" id="itemCdError">
									</td>
									<td class="col-md-44 padding-left">
									</td>
								</tr>
								<tr class="col-md-12">
									<td class="col-md-44"><span class="color-red">*&nbsp;</span>Item Name:
									</td>
									<td class="col-md-45 item-name"><input
										class="form-control alphanumberic" name="itemName" id="itemName" validate=true maxlength="50"/></td>
								</tr>
								<tr class="col-md-12" id="error2" style="display: none;">
									<td class="col-md-44">
									</td>
									<td class="col-md-45 item-name" id="itemNameError">
									</td>
								</tr>
								<tr class="col-md-12">
									<td class="col-md-44"><span class="color-red">*&nbsp;</span>Category:
									</td>
									<td class="col-md-45 item-name"><select
										class="form-control" id="category">
									</select></td>
									<td class="col-md-44 padding-left brand-field td-brand"><span class="<%-- color-red --%>">&nbsp;Brand:</span>
									</td>
									<td class="col-md-45 brand-field td-brand"><input class="form-control alphanumberic"
										name="itemBrand" id="itemBrand" maxlength="10"/></td>
								</tr>
								<tr class="col-md-12" id="error3" style="display: none;">
									<td class="col-md-44">
									</td>
									<td class="col-md-45 item-name" id="categoryError">
									</td>
									<td class="col-md-44 padding-left">
									</td>
									<td class="col-md-45" id="brandError">
									</td>
								</tr>
								<tr class="col-md-12">
									<td class="col-md-44 margin-price"><span class="color-red">*&nbsp;</span>Unit Price (SGD):
									</td>
									<td class="col-md-11" id="countriesPrice"></td>
								</tr>
								<tr class="col-md-12" id="error4" style="display: none;">
									<td class="col-md-44">
									</td>
									<td class="col-md-45 item-name" id="unitPriceError">
									</td>
									<td class="col-md-44 padding-left">
									</td>
								</tr>
							</table>
							<table class="order-column row-border hover data-table"
								style="table-layout: fixed;" id="resultTable">
								<thead>
									<tr>
										<th width="3.5%">#</th>
										<th width="15%">Item Code</th>
										<th width="24%">Item Name</th>
										<th width="7%">Category</th>
										<th class="hiddenBrand">Brand</th>
										<th class="hiddenAddMemory">Add Memory</th>
										<th class="hiddenNoUse">No Use</th>
										<th width="50%">
											<div class="col-md-12 col-md-122">Fee (SGD)</div>
											<div class="col-md-12" id="countries"></div>
										</th>
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
		<!-- Modal update record confirm -->
		<div class="modal fade" id="modalConfirmUpdateRecord" role="dialog" data-keyboard="false" data-backdrop="static">
		    <div class="modal-dialog">
		      <!-- Modal content-->
		      <div class="modal-content" style="margin-right: 50px;  margin-left: 70px;">
		        <div class="modal-header" style="padding:0px 10px;">
		          <button type="button" class="close" onclick="doCloseConfirmOverwrite();">&times;</button>
		          <h4><span class="glyphicon glyphicon-question-sign"></span>    Confirmation</h4>
		        </div>
		        <div class="modal-body" >
		          <form role="form">
		            <div class="row">
		            	<div class="col-md-12 text-right form-group required no-padding-right">
		              		<p style="float:left; padding-left: 15px;"><img src="<s:url value="/"/>style/imgs/confirm.png" width="30" height="30">
		              			<span id="contentConfirmUpdateRecord" style="margin-left: 15px;"></span>
		              		</p>
		              	</div>
		            </div>
					<div class="row">
						<div class="col-md-2"></div>
						<div class="col-md-4">
							<button type="button" class="btn btn-primary center-block btn-block" id="buttonYesUpdateRecord" data-toggle="modal" data-dismiss="modal">Yes</button>
						</div>
						<div class="col-md-4">
							<button type="button" class="btn btn-primary center-block btn-block btn-grey" id="buttonNoUpdateRecord" onclick="doCloseConfirmUpdateRecord();">No</button>
						</div>
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