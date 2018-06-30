/**
 * 
 */
var messageObject = {};
var userInfo ;
var configObject;
var ischanged = false;
var category_old;
var category_common;
var isUpload = false;

function resetVarible(){
	isUpload = false;
	ischanged = false;
	category_old = '';
}
$(document).ready(function (){
	// get UserInfo and All Message Error
	//
	//alert(configObject.pdfLimit);
	
	$('#confirmRedirectMenu').val("true");
	$(".left-side").hide();
	
	fixHeightDropdown($('#categoryCd'));
	$('#categoryCd a').click(function(){
		resetFieldData();
		removeError();
		$(".btn_delete").prop({
			  disabled: false
		});
		var category_cd = $(this).attr('data-value');
		$('#categoryCdValue').val(category_cd);
		category_old = category_cd;
		getItem_CD(category_cd);
		
	});
	
	// autocomplete for Category_CD
	var category_cd = getdataInDropdown($('#categoryCd'));
	category_common = category_cd;
	$('#categoryCdValue').autocomplete({
		source: category_cd,
		select: function(event,ui){
			resetFieldData();
			removeError();
			$(".btn_delete").prop({
				  disabled: false
			});
			category_old = ui.item.value;
			getItem_CD(ui.item.value);
		}
	});
	
	getUserInfo_MessErr();
	setTabIndex();
	eventChooseFile();
	
	$('#remarkText').change(function(){
		ischanged = true;
	});
	
	$("#categoryCdValue").change(function(event) {
	    	checkCategoryExists($(this).val().trim())
	});

});

function checkCategoryExists(cateValue){
	if(category_common.indexOf(cateValue) >= 0){
		category_old = cateValue;
		showDialog('categoryValid',''+cateValue);
	}else{
		showError('IM2-0009');
		$("#categoryCdValue").val(category_old);
	}
}


function getItem_CD(categoryCode){
	var itemcdList=[];
	var params={};
	params.category_cd = categoryCode;
	loading_ajax();
	$.ajax({
		cache: false,
		url: "sab9921_01",
		type: "GET",
		data: params,
		dataType: "json",
		contentType: "application/json; charset=utf-8",
		success: function(response){
			close_loading_ajax();
			itemcdList = response.listItemCd;		
			$('#categoryName').val(response.pab9921Bean.category_name);
			$('#itemCd').empty();
			for(var i=0;i<itemcdList.length;i++){
				var li = '<li><a href="#" data-value="'+itemcdList[i]+'">'+itemcdList[i]+'</a></li>'
				$('#itemCd').append(li);
			}
			fixHeightDropdown($('#itemCd'));
			$('#itemCd a').click(function(){
				resetFieldData1();
				removeError();
				var itemCD = $(this).attr('data-value');
				
				$('#itemCdValue').val(itemCD);
				getItemName(itemCD, categoryCode);
			});
			
			// autocomplete for Item_CD
			
			$('#itemCdValue').autocomplete({
				source: itemcdList,
				select: function(event,ui){
					resetFieldData1();
					removeError();
					getItemName(ui.item.value, categoryCode );
				}
			});
		},
		error: function(jqXhr, textStatus, errorThrown){
			alert(textStatus);
			close_loading_ajax();
		}
	});
	
	
	
	
}

function getItemName(itemcode,categoryCode){
	var params ={};
	params.item_cd = itemcode;
	params.category_cd = categoryCode;
	loading_ajax();
	$.ajax({
		cache: false,
		url: "sab9921_02",
		type: "GET",
		data: params,
		dataType: "json",
		contentType: "application/json; charset=utf-8",
		success: function(response){
			 close_loading_ajax();
			$('#itemName').val(response.pab9921Bean.item_name);
			var imageBase64=response.imageBase64;
			if(imageBase64 != undefined && imageBase64 != null ){
				$("#noImage").hide();
				$("#imageBox").show();
				$('#imageBox').attr('src', 'data:image/png;base64,'+imageBase64);
				$('#linkToImage').val(response.pab9921Bean.link_to_image);
				$('#imageName').val(response.pab9921Bean.image_name);
				$('#imageSize').val(response.pab9921Bean.image_size);
			}
			
			var pdf = response.pab9921Bean.link_to_pdf;
			if(pdf != undefined && pdf != null){
				$('#linkToPdf').val(pdf);
				$('#pdfName').val(response.pab9921Bean.pdf_name);
				$('#pdfSize').val(response.pab9921Bean.pdf_size);
				$('#pdf_name_file').show();
				
			}else{
				$('#pdf_name_file').hide();
			}
			$('#pdf_name_file').attr('onclick','checkFileExisted("'+response.pab9921Bean.pdf_name+'")');
			$('#spanfilename').text(response.pab9921Bean.pdf_name);
			$('#spanpdfSize').text(' ('+response.pab9921Bean.pdf_size+')');
			
			if(response.pab9921Bean.remark == null){
				$('#remarkText').val('');
			}else{
				$('#remarkText').val(response.pab9921Bean.remark);
			}
			
		},
		error: function(jqXhr, textStatus, errorThrown){
			alert(textStatus);
			close_loading_ajax();
		}
	});
}


function getdataInDropdown(element){
	var result = [];
	element.find('li').each(function(index){
		result.push($(this).text());
	});
	
	return result;
}

function fixHeightDropdown(element){
	if(element.height() > 200){
		element.height(200);
	}
}

function setTabIndex(){
	$('#categoryCdValue').attr('tabindex', '10');
	$('#itemCdValue').attr('tabindex', '2');
	$('#remarkText').attr('tabindex', '3');
	$('#btn-file-image').attr('tabindex', '4');
	$('#btn-file-pdf').attr('tabindex', '5');
	$('.btn-save').attr('tabindex', '6');
	$('.btn-close').attr('tabindex', '7');
	$('.btn_delete').attr('tabindex', '8');
	$('.myBody').keydown(function(event){
		if($('#btn-file-image').is(':focus') && event.which == 13){
			event.preventDefault();
			$('#imageData').click();
			
		}
		
		if($('#btn-file-pdf').is(':focus') && event.which == 13){
			event.preventDefault();
			$('#pdfData').click();
			
		}
		
		
	});
	
	
	
}

function checkOnInput(){
	var valueCate = $('#categoryCdValue').val();
	var listCate = getdataInDropdown($('#categoryCd'));
	var isExited = false;
	if(listCate.indexOf(valueCate) >= 0){
		isExited = true;
		showDialog('categoryValid', '');
		return true;
	}
	if(!isExited){
		showError('IM2-0009');
		$('#categoryCdValue').val(category_old);
		return false;
	}	
}

function eventChooseFile(){
	$('#file-image').mousedown(function(){
		$('#imageData').click();
		return false;
	});
	$('#btn-file-image').click(function(){
		$('#imageData').click();
		return false;
	});
	
	$('#file-pdf').mousedown(function(){
		$('#pdfData').click();
		return false;
	});
	
	$('#btn-file-pdf').click(function(){
		$('#pdfData').click();
		return false;
	});
}

function resetFieldData(){
	$('#sab9921')[0].reset();
	$('#noImage').show();
	$('#imageBox').hide();
	$('#imageBox').attr('src','');
	$('#pdf_name_file').attr('onclick','');
	$("#span-file-name").text("");
    $("#span-file-size").text("");
	
}

function resetFieldData1(){
	
	$("#itemCdValue").val("");
	$("#itemName").val("");
	$("#remarkText").val("");
	$("#imageData").val("");
	$("#file-image").val("");
	$("#catalogPdf").val("");
    $("#noImage").show();
    $("#imageBox").attr("src", "");
    $("#imageBox").hide();
    $("#linkCatalogPdf").removeAttr("href");
    $("#linkCatalogPdf").removeAttr("onclick");
    $("#span-file-name").text("");
    $("#span-file-size").text("");
    $("#file-pdf").val("");
    $("#linkToImage").val("");
	$("#imageName").val("");
	$("#imageSize").val("");
	$("#linkToPdf").val("");
	$("#pdfName").val("");
	$("#pdfSize").val("");
}

function createHTMLError(errorCode,params,id){
	var message = getMesssageErrorFollowingErrorCode(errorCode);
	var html='';
	html += '<ul class="cdms_ul_error" id="'+ id +'">';
	html += '<li>';
	if (params!=undefined && params!=""){
		message += params; 
	}
	html += message;
	html += '</li>';
	html += '</ul>';
	return html;
}

function getMesssageErrorFollowingErrorCode(errorCode){
	var mess='';
	$.each(messageObject, function(index, value){
		if(value.key == errorCode){		
			mess = value.value;
		}
	});
	
	return mess;
}

function removeError(){
	$(".cdms_ul_error").remove();
	$("#categoryCdValue").removeClass("input-error");
	$("#categoryCd").next().removeClass("btn-error");
	$("#itemCdValue").removeClass("input-error");
	$("#itemCd").next().removeClass("btn-error");
	$("#file-image").removeClass("input-error");
	$("#btn-file-image").removeClass("input-error");
	$("#file-pdf").removeClass("input-error");
	$("#btn-file-pdf").removeClass("input-error");
}

function validateFileUpload(type){
	var flag = true;
	removeError();
	if($('#categoryCdValue').val() == ''){
		flag = false;
		$('#categoryCdValue').parent().parent().append(createHTMLError('EB-0003','',''));
		$("#categoryCdValue").addClass("input-error");
		$("#categoryCd").next().addClass("btn-error");
	}
	
	if($('#itemCdValue').val() == ''){
		flag = false;
		$('#itemCdValue').parent().parent().append(createHTMLError('EB-0001','',''));
		$("#itemCdValue").addClass("input-error");
		$("#itemCd").next().addClass("btn-error");
	}
	if(!flag)
		return false;
	
	if(type==0){
		
		var imageData = $('#imageData')[0].files[0];
		if(!checkExtentionFile(0, imageData.name)){
			$('#imageData').after(createHTMLError('IM2-0006','','imageDataError'));
			$("#file-image").addClass("input-error");
			$("#btn-file-image").addClass("input-error");
			return false;
		}
		// imageLimit is 1MB
		if(!checkSize(imageData.size, configObject.imageLimit)){
			$('#imageData').after(createHTMLError('IM2-0008',' '+configObject.imageLimit,'imageDataError'));
			$("#file-image").addClass("input-error");
			$("#btn-file-image").addClass("input-error");
			return false;
		}
		
	}
	
	if(type == 1){
		
		var pdfData = $('#pdfData')[0].files[0];
		if(!checkExtentionFile(1, pdfData.name)){
			$('#pdfData').after(createHTMLError('IM2-0007','','imageDataError'));
			$("#file-pdf").addClass("input-error");
			$("#btn-file-pdf").addClass("input-error");
			return false;
		}
		// pdfLimit is 10MB
		if(!checkSize(pdfData.size, configObject.pdfLimit)){
			$('#pdfData').after(createHTMLError('IM2-0008',' '+configObject.pdfLimit,'imageDataError'));
			$("#file-pdf").addClass("input-error");
			$("#btn-file-pdf").addClass("input-error");
			return false;
		}
	}
	return flag;
}

function checkSize(imageSize, maxSize) {
    if (parseFloat(maxSize) * 1024 * 1024 < imageSize)
        return false;
    return true;
}

function checkExtentionFile(type, nameFile){
	if(type == 0){
		var arr = nameFile.split('.');
		var ext = arr[arr.length-1];
		
		if(ext.trim() == 'png' || ext.trim() == 'jpg'){
			
			//$('#imageData').parent().parent().append(createHTMLError('IM2-0006','',''));
			return true;
		}
	}else if(type == 1){
		var arr = nameFile.split('.');
		var ext = arr[arr.length-1];
		if(ext == 'pdf'){
			//$('#pdfData').parent().parent().append(createHTMLError('IM2-0007','',''));
			return true;
		}
	}
	
	return false;
}

function doUpload(type){
	if(validateFileUpload(type)){
		if(type == 0){
			if(isFirstUpload(0)){
				showDialog('uploadFile', type);
			}else{
				showDialog('uploadOverideFile', type);
			}
		}else if(type == 1){
			if(isFirstUpload(1)){
				showDialog('uploadFile', type);
			}else{
				showDialog('uploadOverideFile', type);
			}
		}
		
	}
}

function showDialog(event, type){
	if(event == 'uploadFile'){
		var mess = getMesssageErrorFollowingErrorCode('IM2-0001');
		$('#contentConfirmValid').text(mess);
		$('#modalConfirmValid').modal();
		$('#buttonYesValid').attr('onclick', 'doYes1("'+event+'",'+type+')');
	}else if(event == 'uploadOverideFile'){
		var mess = getMesssageErrorFollowingErrorCode('IM2-0001');
		$('#contentConfirmValid').text(mess);
		
		$('#modalConfirmValid').modal();
		$('#buttonYesValid').attr('onclick', 'doYes1("'+event+'",'+type+')');
//		mess = getMesssageErrorFollowingErrorCode('IM2-0005');
//		$('#contentConfirmOverwrite').text(mess);
//		$('#modalConfirmOverwrite').modal();
	}else if(event == 'delete'){
		var mess = getMesssageErrorFollowingErrorCode('IM2-0004');
		$('#contentConfirmValid').text(mess);
		$('#modalConfirmValid').modal();
		$('#buttonYesValid').attr('onclick', 'doYes1("'+event+'","'+type+'")');
	}else if(event == 'categoryValid'){
		var mess = getMesssageErrorFollowingErrorCode('IM2-0010');
		$('#contentConfirmValid').text(mess);
		$('#modalConfirmValid').modal();
		$('#buttonYesValid').attr('onclick', 'doYes1("'+event+'","'+type+'")');
	}else if(event == 'close'){
		var mess = getMesssageErrorFollowingErrorCode('DO-0003');
		$('#contentConfirmValid').text(mess);
		$('#modalConfirmValid').modal();
		$('#buttonYesValid').attr('onclick', 'doYes1("'+event+'","'+type+'")');
//		$('#buttonNoValid').attr('onclick', 'doCloseChanged("'+type+'")');
	}else if(event == 'save'){
		var mess = getMesssageErrorFollowingErrorCode('IM2-0002');
		$('#contentConfirmValid').text(mess);
		$('#modalConfirmValid').modal();
		$('#buttonYesValid').attr('onclick', 'doYes1("'+event+'","'+type+'")');
	}
}

//function doCloseChanged(type){
//	$("#redirectFunctionId").val(type.functionID);
//	$("#redirectScreenId").val(type.screenId);
//	$("form[id=form_menu]").submit();
//}

function showError(eventCode){
	var mess = getMesssageErrorFollowingErrorCode(eventCode);
	$('#contentConfirmInvalid').text(mess);
	$('#modalConfirmInvalid').modal();
}

function doYes1(event,type){
	if(event == 'uploadOverideFile'){
	
		mess = getMesssageErrorFollowingErrorCode('IM2-0005');
		$('#contentConfirmOverwrite').text(mess);
		
		$('#modalConfirmOverwrite').modal();
		$('#buttonYesOverwrite').attr('onclick','doOverride('+type+')');
	}else if(event == 'uploadFile'){
		ischanged = true;
		isUpload = true;
		uploadFile(type);
	}else if(event == 'categoryValid'){
		resetFieldData();
		$("#categoryCdValue").val(type);
		
		getItem_CD(type);
		$('#modalConfirmValid').hide();
	}else if(event == 'close'){
		$("#redirectFunctionId").val(type.functionID);
		$("#redirectScreenId").val(type.screenId);
		$("form[id=form_menu]").submit();
	}else if(event == 'delete'){
		console.log(type);
		callAjaxDelete(type);
	}else if(event == 'save'){
		save();
	}
	
}

function doDelete(){
	if (validateClickSave()){
		
	
		var item_cd = $("#itemCdValue").val();
		
		showDialog("delete",''+item_cd);
	}
}

function callAjaxDelete(params){
	loading_ajax();
	$.ajax({
		cache: false,
		url: "deleteItem",
		data: 'item_cd='+params,
		dataType: "json",
		processData:false,
        contentType: false,
        success: function(response){
        	close_loading_ajax();
        	console.log(response);
        	resetFieldData();
        },
        error : function(jqXhr, textStatus, errorThrown) {
        	alert(textStatus);
            close_loading_ajax();
		}
	
	});
}

function doOverride(type){
	ischanged = true;
	uploadFile(type);
	isUpload = true;
}

function uploadFile(type){
	ischanged = true;
	var image = {};
	if(type == 0){
		var imageData = $('#imageData')[0].files[0];
		var imageName = imageData.name;
		var imageSize = (imageData.size/1024/1024).toFixed(2);
		displayImage(imageData);
		$("#linkToImage").val(configObject.imageDir + "\\" + imageName);
		$("#imageName").val(imageName);
		$("#imageSize").val(imageSize + "MB");
	}
	
	if(type == 1){
		var pdfData = $('#pdfData')[0].files[0];
		
		var pdfSize = (pdfData.size/1024/1024).toFixed(2);
		var itemCd = $("#itemCdValue").val().trim();
		var pdfName= itemCd + '.'+ pdfData.name.split('.').pop();
		$('#pdf_name_file').show();
		$('#pdf_name_file').removeAttr("href");
		$('#pdf_name_file').attr("onclick", "downloadPdf(this)");
		$("#spanfilename").text(itemCd + ".pdf");
		$("#spanpdfSize").text(' ('+pdfSize+')MB');
		$("#linkToPdf").val(configObject.imageDir + "\\" + pdfName);
		$("#pdfName").val(pdfName);
		$("#pdfSize").val(pdfSize + "MB");
	}
}

function displayImage(image){
	$("#imageBox").attr("width", $("#noImage").innerWidth());
	$("#noImage").hide();
	$("#imageBox").show();
	var fr = new FileReader();

    fr.onload = function(ev2) {
        console.dir(ev2);
        $('#imageBox').attr('src', ev2.target.result);
    };

    fr.readAsDataURL(image);
    return;
}

function isFirstUpload(type){
	if(type == 0){
		var linkToImage = $('#linkToImage').val().trim();
		var imageName = $("#imageName").val().trim();
		var imageSize = $("#imageSize").val().trim();
		if (linkToImage != "" && imageName != "" && imageSize != ""){
			return false;
		}
	}
	
	if(type == 1){
		var linkToPdf = $('#linkToPdf').val().trim();
		var pdfName = $("#pdfName").val().trim();
		var pdfSize = $("#pdfSize").val().trim();
		if (linkToPdf != "" && pdfName != "" && pdfSize != ""){
			return false;
		}
	}
	return true;
}



function validateClickSave(){
	//$(".cdms_ul_error").remove();
	removeError();
	var flag = true;
	// check category code, item code is empty
	if ($("#categoryCdValue").val() == ""){
		flag = false;
		$('#categoryCdValue').parent().parent().append(createHTMLError("EB-0003", "", ""));
		$("#categoryCdValue").addClass("input-error");
		$("#categoryCd").next().addClass("btn-error");
	}
	if ($("#itemCdValue").val() == ""){
		flag = false;
		$('#itemCdValue').parent().parent().append(createHTMLError("EB-0001", "", ""));
		$("#itemCdValue").addClass("input-error");
		$("#itemCd").next().addClass("btn-error");
	}
	return flag;
}

function doCloseConfirmInvalid(){
	
	$("#modalConfirmInvalid").modal('hide');
}

function doCloseConfirmValid(){
	$('#modalConfirmValid').modal('hide');
}

function doCloseConfirmOverwrite(){
	$('#modalConfirmOverwrite').modal('hide');
}

function doClose(){
	var params ={};
	params.functionID = 'faa001';
	params.screenID = 'saa0012';
	if(!ischanged){
		loading_ajax();
		$("#redirectFunctionId").val(params.functionID);
		$("#redirectScreenId").val(params.screenID);
		close_loading_ajax();
		$("form[id=form_menu]").submit();
		
	}
	showDialog("close", params);
}

function doSave(){
	var params={};
	if(validateClickSave()){
//		params.category_cd = $('#categoryCdValue').val();
//		params.item_cd = $("#itemCd").val();
		showDialog('save','');
		
	}
	
}

function save(){
	
	var formData = new FormData();
	formData.append('category_cd', $("#categoryCdValue").val().trim());
	formData.append('category_name', $("#categoryName").val().trim());
	formData.append('item_cd', $("#itemCdValue").val().trim());
	formData.append('item_name', $("#itemName").val().trim());
	formData.append('remark', $("#remarkText").val().trim());
	
	var fileImage = $('#imageData')[0].files[0];
	if(fileImage != undefined && fileImage != null ){
		
		var name = $("#itemCdValue").val() + '.'+fileImage.name.split('.').pop().toLowerCase();
		formData.append('image_name', name);
		formData.append('fileImage',  $('#imageData')[0].files[0]);
	}else{
		formData.append('link_to_image', $("#linkToImage").val().trim());
		formData.append('image_name', $("#imageName").val().trim());
		formData.append('image_size',  $("#imageSize").val().trim());
	}
	
	var pdfData = $('#pdfData')[0].files[0];
	if (pdfData != undefined && pdfData != null ){
		var fileName = $("#itemCdValue").val().trim() + "." + pdfData.name.split('.').pop().toLowerCase();
		formData.append('filepdf', pdfData);
		formData.append('pdf_name', fileName);
		
	} else {
		formData.append('link_to_pdf', $("#linkToPdf").val().trim());
		formData.append('pdf_name', $("#pdfName").val().trim());
		formData.append('pdf_size', $("#pdfSize").val().trim());
		
	}
//	var params = {};
//	var cate = $("#categoryCdValue").val().trim();
//	var cateName = $("#categoryName").val().trim();
//	var itemcd = $("#itemCdValue").val().trim();
//	var itemName = $("#itemName").val().trim();
//	var imageData = $('#imageData')[0].files[0];
//	var pdfData = $('#pdfData')[0].files[0];
//	var linktoimage = $("#linkToImage").val().trim();
//	var imageName = $("#imageName").val().trim();
//	var imageSize = $("#imageSize").val().trim();
//	var linktopdf = $("#linkToPdf").val().trim();
//	var pdfName = $("#pdfName").val().trim();
//	var pdfSize = $("#pdfSize").val().trim();
//	var remark = $("#remarkText").val().trim();
//	params.category_cd = cate;
//	params.category_name = cateName;
//	params.item_cd = itemcd;
//	params.item_name = itemName;
//	params.link_to_image = linktoimage;
//	params.image_name = imageName;
//	params.image_size = imageSize;
//	params.link_to_pdf = linktopdf;
//	params.pdf_name = pdfName;
//	params.pdf_size = pdfSize;
//	params.remark = remark;
//	params.fileImage = imageData;
//	params.filepdf = pdfData;
//	
//	console.log(params);
	callAjaxToSave(formData);
}

function callAjaxToSave(formData){
	loading_ajax();
	$.ajax({
		cache: false,
		url: 'sab9921_6',
		type: "POST",
		data: formData,
		dataType: "json",
		processData:false,
        contentType: false,
		success: function(response){
			close_loading_ajax();
			console.log(response);
			resetFieldData();
			$(".btn_delete").prop({
				  disabled: true
			});
			
		},
		error:function(jqXhr, textStatus, errorThrown){
			close_loading_ajax();
	          alert(textStatus);
	          
	    }
	});
}

function checkFileExisted(pdfName){
	var params = {};
	params.pdf_name = pdfName;
	loading_ajax();
	$.ajax({
		cache: false,
		url: 'checkFile',
		type: "POST",
		data: params,
		dataType: "json",
		success: function(response){
			console.log(response);
			var check = response.checkExisted;
			if(check != null && check != undefined ){
				if(check){
					close_loading_ajax();
					window.location.href = 'download?pdf_name='+pdfName;
				}else{
					close_loading_ajax();
					showError('CUONG-0001');
				}
			}
			
		},
		error:function(jqXhr, textStatus, errorThrown){
	          alert(textStatus);
	          close_loading_ajax();
	    }
	});
}


function getUserInfo_MessErr(){
	loading_ajax();
	$.ajax({
		url : 'getInfoUser_MessErr',
		type: "GET",
		dataType: "json",
		data: '',
		contentType : "application/json; charset=utf-8",
		success: function(response){
			close_loading_ajax();
			messageObject = response.listAllMessages;
			userInfo = response.userInfo;
			configObject = response.configInfo;
		},
		error : function(jqXhr, textStatus, errorThrown) {
			close_loading_ajax();
		}
	});
}
