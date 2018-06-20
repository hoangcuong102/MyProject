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
    src="<s:property value="contextPath" />/js/common/jquery.dataTables.min.js"
    type="text/javascript"></script>
<script src="<s:property value="contextPath" />/js/fab001/sab0031.js"
    type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
    href='<s:url value="/"/>style/fab001/sab0031.css' />
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
                                <jsp:include page="../fab001/headerButton31.jsp"></jsp:include>
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
                                    <td class="col-md-44"><strong><span class="red-star">*&nbsp;</span>Country
                                        Code:</strong></td>
                                    <td class="col-md-45 item-name">
                                    	<select class="form-control" id="countriesSelect">
                                    		<option value=""></option>
                                    		<s:iterator value="countryList">
                                    			<option value="<s:property value="countryCd"/>"><s:property value="countryCd"/></option>
                                    		</s:iterator>
                                    	</select>
                                    </td>
                                    <td class="col-md-44"><strong><span class="red-star">*&nbsp;</span>Category:</strong>
                                    </td>
                                    <td class="col-md-45 item-name">
                                    	<select class="form-control" id="category">
                                    		<option value=""></option>
                                    		<s:iterator value="categoryList">
                                    			<option value="<s:property value="categoryCd"/>"><s:property value="categoryName"/></option>
                                    		</s:iterator>
                                    	</select>
                                    </td>
                                </tr>
                                <tr class="col-md-12" id="tr-error">
                                    <td class="col-md-44"></td>
                                    <td class="col-md-45 item-name td-error" id="td-error-country">
                                    </td>
                                    <td class="col-md-44"></td>
                                    <td class="col-md-45 item-name td-error" id="td-error-category">
                                    </td>
                                </tr>
                            </table>
                            <div class="row">
                                <br>
                            </div>
                            <!-- Body -->
                            <div class="row row-eq-height">
                                <!-- active -->
                                <div id="div-body-active">
                                	<div class="div-active-item">
                                    <label class="header-label">Active Items</label>
                                    </div>
                                    <div class="div-active-item border-div">
                                    
                                    <table class="order-column row-border hover data-table"
                                        style="table-layout: fixed;" id="activeItem">
                                        <thead>
                                            <tr>
                                                <th width="10%">#</th>
                                                <th width="90%">Active Items</th>
                                                <th class="hidden-column">Status</th>
                                                <th class="hidden-column">ItemCD</th>
                                            </tr>
                                        </thead>
                                        <tbody width="100%">
                                        	<s:iterator value="activeItem">
                                        		<tr>
                                        			<td><input class="row-check" type="checkbox" name="item" value="1" onclick=""/></td>
                                        			<td><s:property value="itemName"/></td>
                                        			<td><s:property value="activeFg"/></td>
                                        			<td><s:property value="itemCd"/></td>
                                        		</tr>
                                        	</s:iterator>
                                        </tbody>
                                    </table>
                                    </div>
                                </div>
                                <!-- end active -->
                                
                                <div class="text-center" id="div-action-left-all-item">
                                    <div class="vertical-align-middle-hidden"></div>
                                    <div class="vertical-align-middle btn-action">
                                        <div class="row">
                                            <button class="glyphicon glyphicon-play btn-t-u icon-flipped" id="t-left" onclick="allToActive();"></button>
                                        </div>
                                        <div class="row">
                                            <button class="glyphicon glyphicon-play btn-t-u" id="u-left" onclick="activeToAll();"></button>
                                        </div>
                                    </div>
                                </div>
                                
                                <!-- all item -->
                                <div id="div-body-all">
                                	<div class="div-all-item">
                                		<label class="header-label">All Items</label>
                                	</div>
                                    <div class="div-all-item border-div">
                                    
                                    <table class="order-column row-border hover data-table"
                                        style="table-layout: fixed;" id="allItem">
                                        <thead>
                                            <tr>
                                                <th width="10%">#</th>
                                                <th width="90%">All Items</th>
                                                <th class="hidden-column">Status</th>
                                                <th class="hidden-column">ItemCD</th>
                                            </tr>
                                        </thead>
                                        <tbody width="100%">
                                        	<s:iterator value="allItem">
                                        		<tr>
                                        			<td><input class="row-check" type="checkbox" name="item" value="1" onclick=""/></td>
                                        			<td><s:property value="itemName"/></td>
                                        			<td><s:property value="activeFg"/></td>
                                        			<td><s:property value="itemCd"/></td>
                                        		</tr>
                                        	</s:iterator>
                                        </tbody>
                                    </table>
                                    </div>
                                </div>
                                <!-- end all item -->
                                
                                <div class="text-center" id="div-action-right-all-item">
                                    <div class="vertical-align-middle-hidden"></div>
                                    <div class="vertical-align-middle btn-action">
                                        <div class="row">
                                            <button class="glyphicon glyphicon-play btn-t-u icon-flipped" id="t-right" onclick="inactiveToAll();"></button>
                                        </div>
                                        <div class="row">
                                            <button class="glyphicon glyphicon-play btn-t-u" id="u-right" onclick="allToInactive();"></button>
                                        </div>
                                    </div>
                                </div>
                                
                                <!-- inactive -->
                                <div id="div-body-inactive">
                                	<div class="div-inactive-item">
                                		<label  class="header-label">Inactive Items</label>
                                	</div>
                                    <div class="div-inactive-item border-div">
                                    
                                    <table class="order-column row-border hover data-table"
                                        style="table-layout: fixed;" id="inactiveItem">
                                        <thead>
                                            <tr>
                                                <th width="10%">#</th>
                                                <th width="90%">Inactive Items</th>
                                                <th class="hidden-column">Status</th>
                                                <th class="hidden-column">ItemCD</th>
                                            </tr>
                                        </thead>
                                        <tbody width="100%">
                                        	<s:iterator value="inactiveItem">
                                        		<tr>
                                        			<td><input class="row-check" type="checkbox" name="item" value="1" onclick=""/></td>
                                        			<td><s:property value="itemName"/></td>
                                        			<td><s:property value="activeFg"/></td>
                                        			<td><s:property value="itemCd"/></td>
                                        		</tr>
                                        	</s:iterator>
                                        </tbody>
                                    </table>
                                    </div>
                                </div>
                                <!-- end inactive -->
                            </div>
                            <!-- End Body -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../common/footer.jsp"></jsp:include>
        <!-- Modal confirm -->
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
						<!-- <div class="col-md-4">
							<button type="button" class="btn btn-primary center-block btn-block btn-grey" id="buttonNoPending" onclick="doCloseConfirmPending();">No</button>
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