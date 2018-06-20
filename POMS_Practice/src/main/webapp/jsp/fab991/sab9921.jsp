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
	
<script type="text/javascript">

function doUpload(type){
	if (validateFileUpload(type)){
		typeUpload = type;
		// set finame to input text
		if (type == 0){
			//$("#file-image").val( $("#imageData").val().trim() );
		}
		
		if (type == 1){
			//$("#file-pdf").val( $("#catalogPdf").val().trim() );
		}
		
		// show popup
		if (checkFirstUpload(type)){
			showConfirmDialog("upload","","",{"position":"200"});
		} else {
			//doOverwrite();
			showConfirmDialogOverwrite();
		}
	}
}
</script>
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
			
				<div class="col-md-2 sidenav" id="mysidenav" style="background: #f3f3f3;">
					<s:include value="/jsp/common/menu_left.jsp" />
				</div>

				
				<div class="container-fluid col-md-10">
					
						<div class="row title-row">
						<span class="span-icon-menu"><img alt="icon computer"
							src="<s:url value="/"/>images/icon_menu.png"> </span> <span
							class="current-screen-name">Item Master 2</span>
						</div>		
				
				<div class="col-md-10" >
					<form id="sab9921" class="form-horizontal">
						<div class="form-group">
							<label class="control-label col-md-2" for="email"><span style="color: red;">*&nbsp;</span>Category Code:</label>
							<div class="col-md-5">
								<div class="input-group dropdown">
									<input type="text" class="form-control dropdown-toggle alphanumberic" value="" id="categoryCdValue" name="categoryCdValue" maxlength="10"/>
                                        <ul class="dropdown-menu" id="categoryCd" style="width: 100%; overflow: auto;">
                                            <li><a href="#">01MOBILE</a></li>
                                            <li><a href="#" >02LAPTOP</a></li>
                                            <li><a href="#">03LAPTOP</a></li>
                                        </ul>
                                    <span role="button" class="input-group-addon dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="caret"></span></span>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-2" for="email"><span style="color: red;">*&nbsp;</span>Category Name:</label>
							<div class="col-md-6">
								<input type="text" id="categoryName" name="categoryName" class="form-control" value="" readonly=true/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-2" ><span style="color: red;">*&nbsp;</span>Item Code:</label>
							<div class="col-md-5">
								<div class="input-group dropdown">
									<input type="text" class="form-control dropdown-toggle alphanumberic" value="" id="itemCdValue" name="itemCdValue" maxlength="10"/>
                                        <ul class="dropdown-menu" id="categoryCd" style="width: 100%; overflow: auto;">
                                            <li><a href="#">DEMO1</a></li>
                                            <li><a href="#" >DEMO2</a></li>
                                            <li><a href="#">DEMO3</a></li>
                                        </ul>
                                    <span role="button" class="input-group-addon dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="caret"></span></span>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-2"><span style="color: red;">*&nbsp;</span>Item Name:</label>
							<div class="col-md-6">
								<input type="text" id="itemName" name="itemName" class="form-control" value="" readonly=true/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-2" style="margin-right: 15px !important;"><span style="color: red;">*&nbsp;</span>Image Data:</label>
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
						</div>
						
					</form>
				</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>