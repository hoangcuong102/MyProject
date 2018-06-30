<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
	<title>Screen sab9931</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<jsp:include page="../common/common.jsp"></jsp:include>
	<script
	    src="<s:property value="contextPath" />/js/common/jquery.dataTables.min.js"
	    type="text/javascript"></script>
	<script src="<s:property value="contextPath" />/js/fab991/sab9931.js"
	    type="text/javascript"></script>
	<link rel="stylesheet" type="text/css"
	    href='<s:url value="/"/>style/fab991/sab9931.css' />
</head>
<body class="cl-body">
	<div style="min-height: 620px; min-width: 1910px;">
	<!-- Start header -->
		<div class="container-fluid header">
            <div class="row">
                <div class="col-xs-12 banner">
                    <div class="row header-margin">
                        <div class="col-xs-12 user-login-css">
                            <jsp:include page="../common/header.jsp"></jsp:include>
                            <div class="col-xs-10 padding-header-btn">
                                <jsp:include page="../fab991/headerButton9931.jsp"></jsp:include>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
	<!-- End header -->

	<!-- Start Body -->
		<div class="container-fluid wrapper main_center">
			<div class="main_flex row">
				<!-- Start menu left -- use old code -->
					<div class="left left-side col-xs-2"
						style="display: ${menuInfo!=null && menuInfo.size() > 0 ? 'block' : 'none'};min-width: 333px;">
						<s:include value="/jsp/common/menu_left.jsp" />
					</div>
				<!-- End Start menu left -->
				<!-- Start menu right -- use old code -->
					<div class="center right-side col-xs-12" style="min-height: 600px;">
						<!-- Start header body right -- use old code -->
							<div class="row title-row">
		                        <div class="container-fluid">
		                            <div class="icon-menu menu_top_text_left">
		                                <span class="span_icon_menu"><img alt="icon menu"
		                                    src="<s:url value="/"/>images/icon_menu.png"> </span><span
		                                    class="span_text_menu"><s:property value="currentScreenName"/></span>
		                            </div>
		                            <div style="clear: both"></div>
		                        </div>
		                    </div>
						<!-- End header body right-->
						<!-- Start content -- my code -->
							<div class="content font">
								<div class="container-fluid pl-3">
									<div class="row"> 
										<div class="col-lg-3">
											<!-- Start List Country -->
												<table>
												  <tr>
												    <td>
												    	<strong><label for="countryCD" style="width: 14rem;"><span style="color:red;">* </span>Country Code: </label></strong>
											    	</td>
												    <td>
												    	<select class="form-control width-5" id="countryCD" onchange="resetData()">
													    	<option value=""></option>
													    	<s:iterator value="listCountry">
													    		<option value="<s:property value="countryCD"/>"><s:property value="countryCD"/></option>
													    	</s:iterator>
												    	</select>
											    	</td>
												  </tr>
												  <tr>
												    <td> </td>
												    <td><strong style="color:red;font-size:11px;" id="countryError"></strong></td>
												  </tr>
												</table>
										    <!-- End List Country -->
										</div>
										<div class="col-lg-3">
											<!-- Start List Category -->						    
										    	<table>
												  <tr>
												    <td style="width: 14rem;">
												    	<strong><label for="categoryCD" style="width: 13rem;"><span style="color:red;">* </span>Category: </label></strong>
											    	</td>
												    <td>
												    	<select class="form-control width-10" id="categoryCD" onchange="resetData()">
													    	<option value=""></option>
													    	<s:iterator value="listCategory">
													    		<option value="<s:property value="categoryCD"/>"><s:property value="categoryName"/></option>
													    	</s:iterator>
													    </select>
											    	</td>
												  </tr>
												  <tr>
												    <td> </td>
												    <td><strong style="color:red;font-size:11px;" id="categoryError"></strong></td>
												  </tr>
												</table>
											<!-- End List Category -->
										</div>
									</div>
								</div>
								
								<!-- Start Body Item -->
									<div class="container-fluid plr-75">
										<div class="pt-3">
										<!-- Start Active Item -->
											<div class="w-30">
												<span class="title"><strong>Active Items</strong></span>
												<div class="table-item">
													<table class="data-table" id="activeItem">
													</table>
												</div>
											</div>
										<!-- End Active Item -->
											<div class="w-5">
												<button type="button" class="iconUp" onclick="allToActive()">
												  <span class="glyphicon glyphicon-triangle-left icon"></span>
												</button><br>
												<button type="button" class="iconDown" onclick="activeToAll()">
												  <span class="glyphicon glyphicon-triangle-right" ></span>
												</button>
											</div>
										<!-- Start All Item -->
											<div class="w-30">
												<strong><span class="title">All Items</span></strong><br>
												<div class="table-item">
													<table class="data-table" id="allItem">
													</table>
												</div>
											</div>
										<!-- End All Item -->
											<div class="w-5">											
												<button type="button" class="iconUp" onclick="inactiveToAll()">
												  <span class="glyphicon glyphicon-triangle-left icon"></span>
												</button><br>
												<button type="button" class="iconDown" onclick="allToInactive()">
												  <span class="glyphicon glyphicon-triangle-right" ></span>
												</button>
											</div>
										<!-- Start Inactive Item -->
											<div class="w-30">
												<strong><span class="title">Inactive Items</span></strong><br>
												<div class="table-item">
													<table class="data-table" id="inactiveItem"> 
													</table>
												</div>
											</div>
										<!-- End Inactive Item -->
										</div>
									</div>
								<!-- End Body Item -->
								
							</div>
						<!-- End content -->
					</div>
				<!-- End menu right -->
				
			</div>
		</div>
	<!-- End Body -->
	<!-- Start footer -- use old code -->
		<jsp:include page="../common/footer.jsp"></jsp:include>
	<!-- End footer -->
    <!-- Start Modal confirm -- use old code -->
		<div class="modal fade" id="modalConfirmPending" role="dialog" data-keyboard="false" data-backdrop="static">
		    <div class="modal-dialog">
		      <!-- Modal content-->
		      <div class="modal-content" style="margin-right: 50px;  margin-left: 70px;">
		        <div class="modal-header" style="padding:0px 10px;">
		          <button type="button" class="close" onclick="doCloseConfirmPending();">&times;</button>
		          <h4><span class="glyphicon glyphicon-question-sign"></span>    Confirmation</h4>
		        </div>
		        <div class="modal-body" >
		          <form role="form">
		            <div class="row">
		            	<div class="col-md-12 text-right form-group required no-padding-right" style="padding:0px 10px 0px 15px;">
		              		<p style="float:left"><img src="<s:url value="/"/>style/imgs/confirm.png" width="30" height="30"><span id="contentConfirmPending" style="margin-left: 15px;"></span></p>
		              	</div>
		            </div>
					<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-4">
							<button type="button" class="btn btn-primary center-block btn-block" id="buttonYesPending" data-toggle="modal" data-dismiss="modal" onclick="doCloseConfirmPending();">Yes</button>
						</div>
					</div>
		          </form>
		        </div>
		      </div>
		    </div>
		</div>
	<!-- End Modal confirm-->
	</div>
</body>
</html>