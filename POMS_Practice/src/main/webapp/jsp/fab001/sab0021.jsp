<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="../common/common.jsp"></jsp:include>
<script src="<s:property value="contextPath" />/js/fab001/sab0021.js"
    type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
    href='<s:url value="/"/>style/fab001/sab0021.css' />
    <%-- <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> --%>
    <script src="<s:url value="/"/>style/bootstrap3/js/bootstrap.js" type="text/javascript"></script>
<title><s:property value='getText("common_title")' /></title>
</head>
<body class="cl-body">
    <div style="min-height: 620px; min-width: 1910px;">
        <div class="container-fluid header">
			<div class="row">
				<div class="col-xs-12 banner">
					<div class="row">
						<div class="sysIdCss"></div>
					</div>
					<div class="row header-margin">
						<div class="col-xs-12 user-login-css">
							<jsp:include page="../common/header.jsp"></jsp:include>
							<div class="col-xs-10 padding-header-btn">
								<jsp:include page="../fab001/headerButtonSab0021.jsp"></jsp:include>
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
                <div class="center right-side col-xs-12"
                    style="min-height: 600px">
                    <div class="row title-row">
                        <div class="container-fluid">
                            <div class="icon-menu menu_top_text_left">
                                <span class="span_icon_menu"><img
                                    alt="icon computer"
                                    src="<s:url value="/"/>images/icon_menu.png">
                                </span><span class="span_text_menu"><s:property value="currentScreenName"/></span>
                            </div>
                            <div style="clear: both"></div>
                        </div>
                    </div>
                    <div class="container-fluid col-xs-10">
                    <form id="formSab0021">
                        <%-- <input type="hidden" id="checkInsert" name="checkInsert" value="${checkInsert}"/> --%>
                        <div class="row">
                            <div class="col-xs-2 form-group text-right no-padding-right">
                                <label class="control-label form-control no-border-form-control"><span style="color: red;">*&nbsp;</span>Category Code: </label>
                            </div>
                           <div class="col-xs-5">
                                <div class="input-group dropdown">
                                    <input type="text" class="form-control dropdown-toggle alphanumberic" value="" id="categoryCdValue" name="categoryCdValue" maxlength="10"/>
                                        <ul class="dropdown-menu" id="categoryCd" style="width: 100%; overflow: auto;">
                                            <s:iterator value="categoryCdList" var="categoryCd">
                                                <li><a href="#" data-value="<s:property value="categoryCd"/>"><s:property value="categoryCd"/></a></li>
                                            </s:iterator>
                                        </ul>
                                    <span role="button" class="input-group-addon dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="caret"></span></span>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-2 form-group text-right no-padding-right">
                                <label class="control-label form-control no-border-form-control">Category Name: </label>
                            </div>
                           <div class="col-xs-6">
                                <input type="text" id="categoryName" name="categoryName" class="form-control" value="" readonly=true/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-2 form-group text-right no-padding-right">
                                <label class="control-label form-control no-border-form-control"><span style="color: red;">*&nbsp;</span>Item Code: </label>
                            </div>
                           <div class="col-xs-5">
                                <div class="input-group dropdown">
                                    <input type="text" class="form-control dropdown-toggle alphanumberic" value="" id="itemCdValue" name="itemCdValue" maxlength="30"/>
                                        <ul class="dropdown-menu" id="itemCd" name="itemCd" style="width: 100%; overflow: auto;">
                                            <!-- <li><a href="#" data-value="DESKTOP01">DESKTOP01</a></li>
                                            <li><a href="#" data-value="DESKTOP02">DESKTOP02</a></li>
                                            <li><a href="#" data-value="DESKTOP03">DESKTOP03</a></li>
                                            <li><a href="#" data-value="DESKTOP04">DESKTOP04</a></li>
                                            <li><a href="#" data-value="LAPTOP01">LAPTOP01</a></li>
                                            <li><a href="#" data-value="LAPTOP02">LAPTOP02</a></li>
                                            <li><a href="#" data-value="LAPTOP03">LAPTOP03</a></li>
                                            <li><a href="#" data-value="LAPTOP04">LAPTOP04</a></li>
                                            <li><a href="#" data-value="LAPTOP05">LAPTOP05</a></li> -->
                                        </ul>
                                    <span role="button" class="input-group-addon dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="caret"></span></span>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-2 form-group text-right no-padding-right">
                                <label class="control-label form-control no-border-form-control">Item Name: </label>
                            </div>
                           <div class="col-xs-6">
                                <input type="text" id="itemName" name="itemName" class="form-control" value="" readonly=true/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-2 form-group text-right no-padding-right">
                                <label class="control-label form-control no-border-form-control">Image Data: </label>
                            </div>
                            <div class="col-xs-5">
                                <div class="form-group">
									<div class="input-group input-file" name="">
							    		<input type="text" class="form-control" placeholder='Choose a file...' id="file-image" />			
							            <span class="input-group-btn">
							        		<button class="btn btn-default btn-brower" id="btn-file-image" type="file">Browse</button>
							    		</span>
									</div>
								</div>
								<input type="file" id="imageData" name="imageData" class="form-control" accept="image/*" style="display: none;" onchange="doUpload(0)"/>
                            </div>
                            <input type="hidden" id="linkToImage" name="linkToImage" value="" />
                            <input type="hidden" id="imageName" name="imageName" value="" />
                            <input type="hidden" id="imageSize" name="imageSize" value="" />
                        </div>
                        <div class="row">
                            <div class="col-xs-2 form-group text-right no-padding-right">
                                <!-- <label class="control-label form-control no-border-form-control">Item Data: </label> -->
                            </div>
                           <div class="col-xs-4">
                                <div class="form-group">
                                    <div id="noImage" name="noImage" class="form-control text-center">
                                        <span class="middle-text-hidden"></span>
                                        <span class="middle-text" id="contentNoImage">No Image</span>
                                    </div>
                                    <img src="" id="imageBox" name="imageBox" style="display: none;"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-2 form-group text-right no-padding-right">
                                <label class="control-label form-control no-border-form-control">Catalog PDF: </label>
                            </div>
                           <div class="col-xs-5">    
                                <div class="form-group">
									<div class="input-group input-file" name="">
							    		<input type="text" class="form-control" placeholder='Choose a file...' id="file-pdf" />			
							            <span class="input-group-btn">
							        		<button class="btn btn-default btn-brower" id="btn-file-pdf" type="button">Browse</button>
							    		</span>
									</div>
								</div>
								<input type="file" id="catalogPdf" name="catalogPdf" class="form-control" style="display: none;" onchange="doUpload(1)"/>
                            </div>
                            <input type="hidden" id="linkToPdf" name="linkToPdf" value="" />
                            <input type="hidden" id="pdfName" name="pdfName" value="" />
                            <input type="hidden" id="pdfSize" name="pdfSize" value="" />
                        </div>
                        <div class="row">
                            <div class="col-xs-2 form-group text-right no-padding-right">
                                <!-- <label class="control-label form-control no-border-form-control">Catalog PDF: </label> -->
                            </div>
                           <div class="col-xs-5">
                                <div class="form-group">
                                    <a id="linkCatalogPdf" name="linkCatalogPdf" href="">
                                    	<span id="span-file-name"></span>
                                    	<span id="span-file-size"></span>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-2 form-group text-right no-padding-right">
                                <label class="control-label form-control no-border-form-control">Remark Text: </label>
                            </div>
                           <div class="col-xs-6">
                                <textarea id="remarkText" name="remarkText" rows="7" cols="" class="form-control" value="" maxlength="250"></textarea>
                            </div>
                        </div>
                    </div>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="../common/footer.jsp"></jsp:include>
        <!-- Modal confirm Overwrite-->
		<div class="modal fade" id="modalConfirmOverwrite" role="dialog" data-keyboard="false" data-backdrop="static">
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
		              		<p style="float:left"><img src="<s:url value="/"/>style/imgs/confirm.png" width="30" height="30"><span id="contentConfirmOverwrite" style="margin-left: 15px;"></span></p>
		              	</div>
		            </div>
					<div class="row">
						<div class="col-md-2"></div>
						<div class="col-md-4">
							<button type="button" class="btn btn-primary center-block btn-block" id="buttonYesOverwrite" data-toggle="modal" data-dismiss="modal">Yes</button>
						</div>
						<div class="col-md-4">
							<button type="button" class="btn btn-primary center-block btn-block btn-grey" id="buttonNoOverwrite" onclick="doCloseConfirmOverwrite();">No</button>
						</div>
					</div>
		          </form>
		        </div>
		      </div>
		    </div>
		</div>
		<!-- Modal confirm end-->
		<!-- Modal confirm Invalid-->
		<div class="modal fade" id="modalConfirmInvalid" role="dialog" data-keyboard="false" data-backdrop="static">
		    <div class="modal-dialog modal-sm">
		      <!-- Modal content-->
		      <div class="modal-content">
		        <div class="modal-header" style="padding:0px 10px;">
		          <button type="button" class="close" onclick="doCloseConfirmInvalid();">&times;</button>
		          <h4><span class="glyphicon glyphicon-remove-circle"></span>    Error</h4>
		        </div>
		        <div class="modal-body" >
		          <form role="form">
		            <div class="row">
		            	<div class="col-md-12 text-right form-group required no-padding-right">
		              		<p style="float:left"><img src="<s:url value="/"/>style/imgs/errorIcon.png" width="30" height="30"><span id="contentConfirmInvalid" style="margin-left: 15px;"></span></p>
		              	</div>
		            </div>
					<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-4">
							<button type="button" class="btn btn-primary center-block btn-block" id="buttonYesInvalid" data-toggle="modal" data-dismiss="modal" onclick="doCloseConfirmInvalid();">OK</button>
						</div>
						<div class="col-md-4">
						</div>
					</div>
		          </form>
		        </div>
		      </div>
		    </div>
		</div>
		<!-- Modal confirm end-->
		<!-- Modal confirm Valid-->
		<div class="modal fade" id="modalConfirmValid" role="dialog" data-keyboard="false" data-backdrop="static">
		    <div class="modal-dialog">
		      <!-- Modal content-->
		      <div class="modal-content" style="margin-right: 50px;  margin-left: 70px;">
		        <div class="modal-header" style="padding:0px 10px;">
		          <button type="button" class="close" onclick="doCloseConfirmValid();">&times;</button>
		          <h4><span class="glyphicon glyphicon-question-sign"></span>    Confirmation</h4>
		        </div>
		        <div class="modal-body" >
		          <form role="form">
		            <div class="row">
		            	<div class="col-md-12 text-center form-group required no-padding-right no-margin-bottom">
		              		<p style="float:left"><img src="<s:url value="/"/>style/imgs/confirm.png" width="30" height="30"><span id="contentConfirmValid" style="margin-left: 10px; margin-right: 10px;"></span></p>
		              	</div>
		            </div>
					<div class="row">
						<div class="col-md-2"></div>
						<div class="col-md-4">
							<button type="button" class="btn btn-primary center-block btn-block" id="buttonYesValid" data-toggle="modal" data-dismiss="modal">Yes</button>
						</div>
						<div class="col-md-4">
							<button type="button" class="btn btn-primary center-block btn-block btn-grey" id="buttonNoValid" onclick="doCloseConfirmValid();">No</button>
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