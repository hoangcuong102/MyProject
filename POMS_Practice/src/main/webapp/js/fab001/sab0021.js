/**
 * JS file sab0021
 */
var typeUpload = "";
var isChange = false;
var isFirstUpload;
var isUploadImage = false;
var isUploadPdf = false;
var categoryMaster = [];
var categoryCdOld = "";
$(document).ready(function () {
	configInputFile();
	
	$('#confirmRedirectMenu').val("true");
	$(".left-side").hide();
	
    setHeightCombobox($("#categoryCd"));
    $('#categoryCd a').click(function () {
    	resetFieldData();
        var categoryCd = $(this).attr('data-value');
        $(this).closest('.dropdown').find('input#categoryCdValue').val(categoryCd);
        createComboboxItem(categoryCd);
        isChange = false;
        $(".btn-delete").attr("disabled",false);
        categoryCdOld = categoryCd;
    });

    var categoryCd = getData("categoryCd");
    categoryMaster = categoryCd;
    //console.log(categoryCd);
    $("#categoryCdValue").autocomplete({
        source: categoryCd,
        select: function (e, ui) {
        	resetFieldData();
        	$("#categoryCdValue").val(ui.item.value);
        	createComboboxItem(ui.item.value);
        	isChange = false;
        	categoryCdOld = categoryCd;
        },

        change: function (e, ui) {
        }
    });

    setTabIndex();
    validateAlphanumberic();
    
    $("#remarkText").change(function() {
    	console.log($(this).val());
    	isChange = true;
    });
    $(".btn-delete").css('background-color','');
    $(".btn-delete").attr("disabled",true);
    
    //$("#categoryCdValue").change(checkCategoryExists($(this).val().trim()));
    $("#categoryCdValue").change(function(event) {
    	checkCategoryExists($(this).val().trim())
	});
    
    categoryCdOld = $("#categoryCdValue").val().trim();
});

/**
 * Create combobox Item, input  auto-complete
 * @param categoryCd
 * @returns
 */
function createComboboxItem(categoryCd) {
    var itemCdList = [];
    var params = {};
    params.categoryCd = categoryCd;
    $("#itemCd").empty();
    $("#itemCdValue").autocomplete({
        source: null
    });

    // call ajax get categoryName, itemCdList
    loading_ajax();
    $.ajax({
        cache: false,
        url: "sab0021_1",
        type: "GET",
        data: params,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            close_loading_ajax();
            itemCdList = response.itemCdList;
            $("#categoryName").val(response.pab0021Bean.categoryName);
            $("#categoryName").attr("readonly", true);

            for (var i = 0; i < itemCdList.length; i++) {
                var li = "<li><a href='#' data-value='" + itemCdList[i] + "'>" + itemCdList[i] + "</a></li>";
                $("#itemCd").append(li);
            }

            $('#itemCd a').click(function () {
                //console.log($(this).attr('data-value'));
                resetFieldData1();
                $(this).closest('.dropdown').find('input#itemCdValue').val($(this).attr('data-value'));
                fillDataToForm($("#categoryCdValue").val().trim(), $("#itemCdValue").val().trim());
                isChange = false;
            });

            //var itemCd = getData("itemCd");
            //console.log(itemCd);
            $("#itemCdValue").autocomplete({
                source: itemCdList,
                select: function (e, ui) {
                    //alert("selected!");
                	resetFieldData1();
                	fillDataToForm($("#categoryCdValue").val().trim(), ui.item.value);
                	isChange = false;
                },

                change: function (e, ui) {
                    //alert("changed!");
                }
            });
        },
        error: function (jqXhr, textStatus, errorThrown) {
            alert(textStatus);
            close_loading_ajax();
        }
    });
}

/**
 * Set value into field form formSab0021
 * @param pab0021Bean
 * @returns
 */
function setValueToForm(response) {
	var pab0021Bean = response.pab0021Bean;
	var imageBase64 = response.imageBase64;
	
	// xu ly anh tai ve nua
	if (imageBase64!=undefined && imageBase64!=null){
		$("#noImage").hide();
		$("#imageBox").show();
		$("#imageBox").attr("src", "data:image/png;base64," + imageBase64);
	} else {
		$("#noImage").show();
		$("#imageBox").hide();
	}
	
    $("#itemName").val(pab0021Bean.itemName);
    $("#itemName").attr("readonly", true);
    if (pab0021Bean.linkToImage != "" && pab0021Bean.linkToImage != "null" && pab0021Bean.linkToImage != null){
	    $("#linkToImage").val(pab0021Bean.linkToImage);
		$("#imageName").val(pab0021Bean.imageName);
		$("#imageSize").val(pab0021Bean.imageSize);
    }
	if (pab0021Bean.linkToPdf != "" && pab0021Bean.linkToPdf != "null" && pab0021Bean.linkToPdf != null){
		$("#linkToPdf").val(pab0021Bean.linkToPdf);
		$("#pdfName").val(pab0021Bean.pdfName);
		$("#pdfSize").val(pab0021Bean.pdfSize);
	    //$("#linkCatalogPdf").attr("href", "sab0021_6?pdfName=" + pab0021Bean.pdfName);
	    //$("#linkCatalogPdf").text(pab0021Bean.pdfName);
		$("#linkCatalogPdf").removeAttr("href");
		$("#linkCatalogPdf").attr("onclick", "downloadFileCatalog('"+ pab0021Bean.pdfName +"');");
	    $("#span-file-name").text(pab0021Bean.pdfName);
	    $("#span-file-size").text(" (" + pab0021Bean.pdfSize + ")");
	}
    
    $("#remarkText").val(pab0021Bean.remark);
}

/**
 * when selected itemCd
 * fill data to formSab0021
 * @param categoryCd
 * @param itemCd
 * @returns
 */
function fillDataToForm(categoryCd, itemCd) {
    var params = {};
    params.categoryCd = categoryCd;
    params.itemCd = itemCd;

    // call ajax get categoryName, itemCdList
    loading_ajax();
    $.ajax({
        cache: false,
        url: "sab0021_2",
        type: "GET",
        data: params,
        //async: false,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            close_loading_ajax();
            var pab0021Bean = response.pab0021Bean;
            setValueToForm(response);
        },
        error: function (jqXhr, textStatus, errorThrown) {
            alert(textStatus);
            close_loading_ajax();
        }
    });
}

/**
 * get all data from combobox
 * @returns array data
 */
function getData(element) {
    var result = [];
    $("#" + element).find('li').each(function (index) {
        result.push($(this).find("a").text());
    });
    return result;
}

/**
 * set height combobox 200px if height dropMenu > 200px
 * @returns
 */
function setHeightCombobox(element) {
    if (element.height() > 200)
        element.height(200);
}

/**
 * reset formSab0021
 * @returns
 */
function resetFieldData() {
    $("#formSab0021")[0].reset();
    $("#noImage").show();
    $("#imageBox").attr("src", "");
    $("#imageBox").hide();
    //$("#linkCatalogPdf").text("");
    $("#linkCatalogPdf").removeAttr("href");
    $("#linkCatalogPdf").removeAttr("onclick");
    $("#span-file-name").text("");
    $("#span-file-size").text("");
    $("#linkToImage").val("");
	$("#imageName").val("");
	$("#imageSize").val("");
	$("#linkToPdf").val("");
	$("#pdfName").val("");
	$("#pdfSize").val("");
    //$(".cdms_ul_error").remove();
	removeError();
}

/**
 * reset formSab0021
 * @returns
 */
function resetFieldData1() {
    //$("#formSab0021")[0].reset();
	$("#itemCdValue").val("");
	$("#itemName").val("");
	$("#remarkText").val("");
	$("#imageData").val("");
	$("#file-image").val("");
	$("#catalogPdf").val("");
    $("#noImage").show();
    $("#imageBox").attr("src", "");
    $("#imageBox").hide();
    //$("#linkCatalogPdf").text("");
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
    //$(".cdms_ul_error").remove();
    removeError();
}

/**
 * doUpload file
 * type = 0 upload image
 * type = 1 upload pdf
 */
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

function doOverwrite(){
	showConfirmDialog("overwrite","","",{"position":"200"});
}

/**
 * check first upload
 * type = 0 upload image
 * type = 1 upload pdf
 */
function checkFirstUpload(type){
	if (type == 0){
		var linkToImage = $("#linkToImage").val().trim();
		var imageName = $("#imageName").val().trim();
		var imageSize = $("#imageSize").val().trim();
		if (linkToImage != "" && imageName != "" && imageSize != ""){
			return false;
		}
	}
	
	if (type == 1){
		var linkToPdf = $("#linkToPdf").val().trim();
		var pdfName = $("#pdfName").val().trim();
		var pdfSize = $("#pdfSize").val().trim();
		if (linkToPdf != "" && pdfName != "" && pdfSize != ""){
			return false;
		}
	}
	
	return true;
}

/**
 * upload file
 * type = 0 upload image
 * type = 1 upload pdf
 */
function uploadFile(type){
	isChange = true;
	var formData = new FormData();
	if (type == 0){
		var fileImage = $('#imageData').prop('files')[0];
		var fileName = $("#itemCdValue").val().trim() + "." + fileImage.name.split('.').pop();
		var fileSize = (fileImage.size/1024/1024).toFixed(2);
		formData.append("fileImage", fileImage);
		formData.append("dir", configObject.imageDir);
		formData.append("imageName", fileName.toUpperCase());
		viewImage();
		$("#linkToImage").val(configObject.imageDir + "\\" + fileName);
		$("#imageName").val(fileName);
		$("#imageSize").val(fileSize + "MB");
		isUploadImage = true;
	}

	if (type == 1){
		var filePdf = $('#catalogPdf').prop('files')[0];
		var fileName = $("#itemCdValue").val().trim() + "." + filePdf.name.split('.').pop();
		var fileSize = (filePdf.size/1024/1024).toFixed(2);
		var itemCd = $("#itemCdValue").val().trim();
		formData.append("filePdf", filePdf);
		formData.append("dir", configObject.pdfDir);
		formData.append("pdfName", fileName.toUpperCase());
		$("#linkCatalogPdf").removeAttr("href");
		$("#linkCatalogPdf").attr("onclick", "downloadFromInputFile(this)");
		$("#span-file-name").text(itemCd + ".pdf");
		$("#span-file-size").text(" (" + fileSize + "MB)");
		
		$("#linkToPdf").val(configObject.imageDir + "\\" + fileName);
		$("#pdfName").val(fileName);
		$("#pdfSize").val(fileSize + "MB");
		isUploadPdf = true;
	}
}

function viewImage(){
	$("#imageBox").attr("width", $("#noImage").innerWidth());
	$("#noImage").hide();
	$("#imageBox").show();
	var f = $('#imageData').prop('files')[0];
    var fr = new FileReader();

    fr.onload = function(ev2) {
        console.dir(ev2);
        $('#imageBox').attr('src', ev2.target.result);
    };

    fr.readAsDataURL(f);
    return;
}

/**
 * check extension file upload
 * type = 0 file image
 * type = 1 file pdf
 */
function checkExtension (file, type){
	//var extension = file.name.split('.').pop();
	var extension = file.split('.').pop();
	extension = extension.toLowerCase();
	if (type == 0){
		if (extension == "png" || extension == "jpg"){
			return true;
		}
	}

	if (type == 1){
		if (extension == "pdf"){
			return true;
		}
	}
	return false;
}

/**
 * check size file upload
 */
function checkSize (file, maxSize){
	// convert maxSize to int
	// MB -> byte
	var _maxSize = parseFloat(maxSize.replace(/[^.\d]/gi, "")) * 1024 * 1024;
	if (file.size > _maxSize)
		return false;
	return true;
}

/**
 * validate file upload
 * type = 0 upload image
 * type = 1 upload pdf
 */
function validateFileUpload (type){
	//$(".cdms_ul_error").remove();
	removeError();
	var flag = true;
	// check category code, item code is empty
	if ($("#categoryCdValue").val() == ""){
		flag = false;
		$('#categoryCdValue').parent().parent().append(generateHtmlError("EB-0003", "", ""));
		$("#categoryCdValue").addClass("input-error");
		$("#categoryCd").next().addClass("btn-error");
	}
	if ($("#itemCdValue").val() == ""){
		flag = false;
		$('#itemCdValue').parent().parent().append(generateHtmlError("EB-0001", "", ""));
		$("#itemCdValue").addClass("input-error");
		$("#itemCd").next().addClass("btn-error");
	}
	if (!flag){
		return flag;
	}
	
	if (type == 0){
		$("#imageDataError").remove();
		//removeError();
		var imageData = $('#imageData').prop('files')[0];
		if (!checkExtension ($('#imageData').val(), 0)){
			$('#imageData').after(generateHtmlError("IM2-0006", "", "imageDataError"));
			$("#file-image").addClass("input-error");
			$("#btn-file-image").addClass("input-error");
			return false;
		}
		if (!checkSize (imageData, configObject.imageLimit)){
			$('#imageData').after(generateHtmlError("IM2-0008", configObject.imageLimit, "imageDataError"));
			$("#file-image").addClass("input-error");
			$("#btn-file-image").addClass("input-error");
			return false;
		}
	}

	if (type == 1){
		$("#catalogPdfError").remove();
		//removeError();
		var catalogPdf = $('#catalogPdf').prop('files')[0];
		if (!checkExtension ($('#catalogPdf').val(), 1)){
			$('#catalogPdf').after(generateHtmlError("IM2-0007", "", "imageDataError"));
			$("#file-pdf").addClass("input-error");
			$("#btn-file-pdf").addClass("input-error");
			return false;
		}
		if (!checkSize (catalogPdf, configObject.pdfLimit)){
			$('#catalogPdf').after(generateHtmlError("IM2-0008", configObject.pdfLimit, "imageDataError"));
			$("#file-pdf").addClass("input-error");
			$("#btn-file-pdf").addClass("input-error");
			return false;
		}
	}
	return flag;
}

/**
 * delete item_dtl_mst
 * @returns
 */
function doDelete(){
	if (validateClickSave()){
		showConfirmDialog("delete","","",{"position":"200"})
	}
}

/**
 * save item_dtl_mst
 * @returns
 */
function doSave(){
	if (validateClickSave()){
		showConfirmDialog("save","","",{"position":"200"})
	}
}

/**
 * close current screen
 * @returns
 */
function doClose(){
	if (isChange){
		showConfirmDialog("close","",["faa001", "saa0012"],{"position":"200"})
	} else {
		doEventDialog01("close", "");
	}
}

/**
 * validate when click Save
 * @returns
 */
function validateClickSave(){
	//$(".cdms_ul_error").remove();
	removeError();
	var flag = true;
	// check category code, item code is empty
	if ($("#categoryCdValue").val() == ""){
		flag = false;
		$('#categoryCdValue').parent().parent().append(generateHtmlError("EB-0003", "", ""));
		$("#categoryCdValue").addClass("input-error");
		$("#categoryCd").next().addClass("btn-error");
	}
	if ($("#itemCdValue").val() == ""){
		flag = false;
		$('#itemCdValue').parent().parent().append(generateHtmlError("EB-0001", "", ""));
		$("#itemCdValue").addClass("input-error");
		$("#itemCd").next().addClass("btn-error");
	}
	return flag;
}

function generateHtmlError (errorCode, params, id){
	var message = getMessageWithParams(errorCode,"");
	var html = "";
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

/**
 * Method will call when Click button yes on dialog confirm
 * @param idEvent Event Save/Delete
 * @param element Dialog confirm
 */
function doEventDialog01(idEvent, element){
	// In case Category Code valid
	if (idEvent == "categoryValid"){
		var categoryCd = $("#categoryCdValue").val().trim();
		resetFieldData();
    	createComboboxItem(categoryCd);
    	isChange = false;
    	categoryCdOld = categoryCd;
    	$("#categoryCdValue").val(categoryCd);
	}
	// In case Item Code valid
	if (idEvent == "itemValid"){
		
	}
	// In case first upload
	if (idEvent == "upload"){
		uploadFile(typeUpload);
	}
	// In case Subsequent Upload
	if (idEvent == "overwrite"){
		uploadFile(typeUpload);
	}
	// In case delete
	if (idEvent == "delete"){
		var formData = new FormData();
		formData.append("itemCd", $("#itemCdValue").val().trim());
		callAjaxDeleteItem(formData);
		$(".btn-delete").attr("disabled",true);
	}
	// In case save
	if (idEvent == "save"){
		var formData = new FormData();
		formData.append("categoryCd", $("#categoryCdValue").val().trim());
		formData.append("categoryName", $("#categoryName").val().trim());
		formData.append("itemCd", $("#itemCdValue").val().trim());
		formData.append("itemName", $("#itemName").val().trim());
		formData.append("remark", $("#remarkText").val().trim());
		
		var fileImage = $('#imageData').prop('files')[0];
		//var fileImageName = $("#file-image").val().trim();
		if (fileImage != undefined && fileImage != null && isUploadImage){
			var fileName = $("#itemCdValue").val().trim() + "." + fileImage.name.split('.').pop().toLowerCase();
			formData.append("fileImage", fileImage);
			formData.append("imageName", fileName);
		} else {
			formData.append("linkToImage", $("#linkToImage").val().trim());
			formData.append("imageName", $("#imageName").val().trim());
			formData.append("imageSize", $("#imageSize").val().trim());
		}
		
		var filePdf = $('#catalogPdf').prop('files')[0];
		if (filePdf != undefined && filePdf != null && isUploadPdf){
			var fileName = $("#itemCdValue").val().trim() + "." + filePdf.name.split('.').pop().toLowerCase();
			formData.append("filePdf", filePdf);
			formData.append("pdfName", fileName);
		} else {
			formData.append("linkToPdf", $("#linkToPdf").val().trim());
			formData.append("pdfName", $("#pdfName").val().trim());
			formData.append("pdfSize", $("#pdfSize").val().trim());
		}
		
		loading_ajax();
	    $.ajax({
	        cache: false,
	        url : "sab0021_3",
	        type : "POST",
	        data: formData,
	        dataType : "json",
	        //contentType: "application/json; charset=utf-8",
	        processData:false,
	        contentType: false,
	        //async: false,
	        enctype: "multipart/form-data",
	        success : function(response) {
	            close_loading_ajax();
	            console.log(response);
	            resetFieldData();
	            $("#itemCd").empty();
	            $("#itemCdValue").autocomplete({
	                source: null
	            });
	        },
	        error:function(jqXhr, textStatus, errorThrown){
	            alert(textStatus);
	            close_loading_ajax();
	        }
	    });
	    isChange = false;
	    isUploadImage = false;
	    isUploadPdf = false;
	    $(".btn-delete").attr("disabled",true);
	}
	// In case close
	if (idEvent == "close"){
		paramForRedirectMenu = ["faa001", "saa0012"];
		$("#redirectFunctionId").val(paramForRedirectMenu[0]);
		$("#redirectScreenId").val(paramForRedirectMenu[1]);
		$("form[id=form_menu]").submit();
	}
}

function showConfirmDialog(idEvent, listButton, param, paramSettingDialog) {
	paramForRedirectMenu = param;
	var messageId = "";
	if (idEvent == "redirect") {
		messageId = "CA-0013";
	} else if (idEvent == "redirectMenu") {
		messageId = "CA-0002";
	} else if (idEvent == "delete") {
		messageId = "IM2-0004";
	} else if (idEvent == "save") {
		messageId = "IM2-0002";
	} else if (idEvent == "close") {
		messageId = "IM2-0003";
	} else if (idEvent == "upload") {
		messageId = "IM2-0001";
	} else if (idEvent == "overwrite") {
		messageId = "IM2-0005";
	} else if (idEvent == "categoryValid") {
		messageId = "IM2-0010";
	} else if (idEvent == "itemValid") {
		messageId = "IM2-0012";
	} else if (idEvent == "logout") {
		messageId = "CA-0001";
		var contentConfirmLogout = getMessageWithParams(messageId,"");
		document.getElementById("contentConfirmLogout").innerHTML = contentConfirmLogout;
		$('#buttonYesLogout').attr('onclick', "doYes('"+idEvent+"')");
		$("#modalConfirmLogout").modal("show");
		return;
	}
	
	var contentConfirm = getMessageWithParams(messageId,"");
	document.getElementById("contentConfirm").innerHTML = /*messageId+" : "+*/ contentConfirm;
	if (idEvent == "close"){
		$("#contentConfirm").parent().parent().css("text-align", "center");
		//$("#contentConfirm").parent().parent().css("padding", "0 5px");
	}
	$('#buttonYes').attr('onclick', "doYes('"+idEvent+"')");
	$("#modalConfirm").modal("show");
}

/**
 * show modal Overwrite
 * 
 * @returns
 */
function showConfirmDialogOverwrite(){
	var contentConfirm = getMessageWithParams("IM2-0001","");
	document.getElementById("contentConfirmOverwrite").innerHTML = /*messageId+" : "+*/ contentConfirm;

	$('#buttonYesOverwrite').attr('onclick', "doOverwrite()");
	$("#modalConfirmOverwrite").modal("show");
}

/**
 * close modal Overwrite
 * 
 * @returns
 */
function doCloseConfirmOverwrite(){
	$("#modalConfirmOverwrite").modal("hide");
}

/**
 * close modal file not found
 * 
 * @returns
 */
function doCloseConfirmFileNotFound(){
	$("#modalConfirmFileNotFound").modal("hide");
}


/**
 * call ajax delete item_mst
 * @param params
 * @returns
 */
function callAjaxDeleteItem(params){
	loading_ajax();
    $.ajax({
        cache: false,
        url : "sab0021_5",
        type : "POST",
        data: params,
        dataType : "json",
        //contentType: "application/json; charset=utf-8",
        processData:false,
        contentType: false,
        //async: false,
        success : function(response) {
            close_loading_ajax();
            console.log(response);
            resetFieldData();
            $("#itemCd").empty();
            $("#itemCdValue").autocomplete({
                source: null
            });
        },
        error:function(jqXhr, textStatus, errorThrown){
            alert(textStatus);
            close_loading_ajax();
        }
    });
}

function setTabIndex() {
	$("#categoryCdValue").attr('tabindex','1');
	$("#itemCdValue").attr('tabindex','2');
	$("#remarkText").attr('tabindex','3');
	$("#btn-file-image").attr('tabindex','4');
	$("#btn-file-pdf").attr('tabindex','5');
	$(".btn-save").attr('tabindex','6');
	$(".btn-close").attr('tabindex','7');
	$(".btn-delete1").attr('tabindex','8');
	//set action when keycode = 13 (Enter)
	$('.cl-body').keydown(function(e){
		if($('#btn-file-image').is(":focus") && (e.which || e.keyCode) == 13) {
			e.preventDefault();
			$('#imageData').click();
		}
		if($('#btn-file-pdf').is(":focus") && (e.which || e.keyCode) == 13) {
			e.preventDefault();
			$('#catalogPdf').click();
		}
	});
}

function configInputFile(){
	// input file image
	$("#file-image").css("cursor","pointer");
	$("#file-image").mousedown(function() {
		$("#imageData").click();
		return false;
	});
	$("#btn-file-image").click(function() {
		$("#imageData").click();
		return false;
	});
	
	// catalog pdf
	$("#file-pdf").css("cursor","pointer");
	$("#file-pdf").mousedown(function() {
		$("#catalogPdf").click();
		return false;
	});
	$("#btn-file-pdf").click(function() {
		$("#catalogPdf").click();
		return false;
	});
	
	/*$('#remarkText').perfectScrollbar();*/
}

/**
 * remove all error
 * @returns
 */
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

/**
 * Download file catalog when click linkLabel catalog
 * 
 * @param catalogName
 * @returns
 */
function downloadFileCatalog(catalogName){
	var params = {};
	params.pdfName = catalogName;
	loading_ajax();
    $.ajax({
        cache: false,
        url : "sab0021_7",
        type : "POST",
        data: params,
        dataType : "json",
        success : function(response) {
            close_loading_ajax();
            console.log(response);
            if (response.fileExist != null && response.fileExist != undefined ){
            	if (response.fileExist){
            		window.location.href = "sab0021_6?pdfName=" + catalogName;
            	} else {
            		document.getElementById("contentConfirmFileNotFound").innerHTML = "File not found.";
            		$("#modalConfirmFileNotFound").modal("show");
            	}
            }
        },
        error:function(jqXhr, textStatus, errorThrown){
            alert(textStatus);
            close_loading_ajax();
        }
    });
}

/**
 * download file after seleted file on catalog pdf
 * @returns
 */
function downloadFromInputFile(element){
    var filename = $('#catalogPdf').val();

    if (filename == "" || filename == null) {
        alert('Error');
    }else {
        var file = document.getElementById('catalogPdf').files[0];
        var filename = document.getElementById('catalogPdf').files[0].name;
        filename = $("#itemCdValue").val().trim() + ".PDF";
        var blob = new Blob([file]);
        var url  = URL.createObjectURL(blob);

        $(element).attr({ 'download': filename, 'href': url});  
        filename = "";
    }
}

/**
 * validateAlphanumberic all input
 * @returns
 */
function validateAlphanumberic(){
	$(".alphanumberic").keypress(function(e){
		var key = e.keyCode;
		if (!checkAlphanumeric(String.fromCharCode(key), false)){
			//alert(String.fromCharCode(key));
			return false;
		}
	});
	/*$("#remarkText").keypress(function(e){
		var key = e.keyCode;
		if (key == 13){
			return true;
		}
		if (!checkAlphanumeric(String.fromCharCode(key), true)){
			//alert((key));
			return false;
		}
	});*/
}

/**
 * check alphanumberic
 * @param character
 * @returns true if alphanumberic
 */
function checkAlphanumeric(character, space){
	var regex = /[^a-zA-Z0-9]/g;
	if (space){
		regex = /[^a-zA-Z0-9 ]/g;
	}
	return !regex.test(character);
}

/**
 * close modal Invalid
 * 
 * @returns
 */
function doCloseConfirmInvalid(){
	$("#categoryCdValue").val(categoryCdOld);
	$("#modalConfirmInvalid").modal("hide");
}

/**
 * show modal Invalid
 * 
 * @returns
 */
function showConfirmDialogInvalid(idEvent){
	var messageId = "";
	if (idEvent == "categoryInvalid") {
		messageId = "IM2-0009";
	} else if (idEvent == "itemInvalid") {
		messageId = "IM2-0011";
	} else {
		return;
	}
	var contentConfirm = getMessageWithParams(messageId,"");
	document.getElementById("contentConfirmInvalid").innerHTML = contentConfirm;

	$("#modalConfirmInvalid").modal("show");
}

/**
 * close modal Valid
 * 
 * @returns
 */
function doCloseConfirmValid(){
	$("#categoryCdValue").val(categoryCdOld);
	$("#modalConfirmValid").modal("hide");
}

/**
 * show modal Invalid
 * 
 * @returns
 */
function showConfirmDialogValid(idEvent){
	var messageId = "";
	if (idEvent == "categoryValid") {
		messageId = "IM2-0010";
	} else if (idEvent == "itemValid") {
		messageId = "IM2-0012";
	} else {
		return;
	}
	var contentConfirm = getMessageWithParams(messageId,"");
	document.getElementById("contentConfirmValid").innerHTML = contentConfirm;
	$('#buttonYesValid').attr('onclick', "doYes('"+idEvent+"')");
	$("#modalConfirmValid").modal("show");
}

/**
 * check categoryCd exists
 * 
 * @param categoryCd
 * @returns
 */
function checkCategoryExists(categoryCd){
	if (categoryMaster.indexOf(categoryCd) >= 0 ){
		showConfirmDialogValid("categoryValid","","",{"position":"200"});
	} else {
		showConfirmDialogInvalid("categoryInvalid");
	}
}