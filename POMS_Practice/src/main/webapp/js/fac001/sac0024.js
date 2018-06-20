/**
 * Javascript file screen [SAC0024] Delivery Order Entry/Update
 */
var listOrderDo = [];
var table, selectedOrderId, selectedDoNo, type;
var isChange = false;

$(document).ready(function() {
	$(".btn-delete").css("background-color", "");
	$('#confirmRedirectMenu').val(true);
	$(".left-side").hide();
	// Init Datatable
	initDatatable();
	// Init Screen
	initScreen();
	// Add onchange on input upload file signback
	$('#uploadFile').change(function(event) {
		showConfirmDialog("signedUpload","","",{"position":"200"});
	});
	// Set tabindex
	setTabIndex();
	
	$(".ipt-delivery-date").change(function(event) {
		isChange = true;
	});
});

/**
 * init screen SAC0024
 * 
 * @returns
 */
function initScreen(){
	// Call ajax load data
	loading_ajax();
    $.ajax({
        cache: false,
        url : "sac0024_01",
        type : "GET",
        data: {orderId: $("#orderId").val().trim()},
        dataType : "json",
        success : function(response) {
            close_loading_ajax();
            // Draw datatable
            if (response.listPac0024Bean != null && response.listPac0024Bean != undefined){
            	// Set value to listOrderDo
            	listOrderDo = response.listPac0024Bean;
            	// Draw datatable
            	drawData(response.listPac0024Bean, response.userAuthority);
            } else {
            	table.clear().draw();
            }

        	// Set tabindex
        	setTabIndex();
        	
			// SANKYU Admin can access to this screen as read-only. Only enable "Generated->View" and "Signed->View" link labels and [Close] button.
			// authority = 3 sankyu
			if (response.userAuthority && response.userAuthority == "3") {
				$(".btn-generate-new").attr("disabled", true);
				$(".btn-save").attr("disabled", true);
				$(".ipt-delivery-date").attr("disabled", true);
				$(".btn-delivery-date").attr("disabled", true);
			}/* else {
				$(".btn-generate-new").attr("disabled", false);
				$(".btn-save").attr("disabled", false);
				$(".ipt-delivery-date").attr("disabled", false);
				$(".btn-delivery-date").attr("disabled", false);
			}*/
			
			// Generate New Flag
            var generateNewFlag = response.generateNewFlag;
        	// Check generateNewFlag if is false disabled button Generate New
        	if (generateNewFlag != undefined && generateNewFlag != null){
        		if (!generateNewFlag){
        			$("#generateNew").attr("disabled", true);
        		} else {
        			$("#generateNew").attr("disabled", false);
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
 * Init datatable
 * 
 * @returns
 */
function initDatatable(){
	table = $('#resultTable').DataTable({
		"sScrollY" : calcDataTableHeight(13),
		"bScrollCollapse" : true,
		"scrollX" : false,
		ordering: false,
		paging: false,
		searching: false,
		"asStripeClasses": [],
		"fnInitComplete" : function() {
			$('.dataTables_scrollBody').perfectScrollbar();
		},
		"dom" : '<"top"l>rt<"bottom"ip><"clear">',
	});
}

/**
 * Draw datatable
 * 
 * @param listData
 * @returns
 */
function drawData(listData, userAuthority) {
	var dataSet = [];
	var length = listData.length;
	table.clear();
	if (length > 0) {
		for (var i = 0; i < length; i++) {
			var index = i + 1;
			var dataRow = [];
			var radio = createRadioButton(listData[i], i, userAuthority);
			var timestamp = createInputReadonly(listData[i].timestamp, false);
			var doNo = createInputReadonly(listData[i].doNo, true);
			var generated = createGenerated(listData[i], userAuthority);
			var signed = createSinged(listData[i], userAuthority);
			var deliveryDate = createDelivertDate(listData[i].deliverDt, listData[i].doGeneratedFg);
			dataRow.push(radio, timestamp, doNo, generated, signed, deliveryDate);
			dataSet.push(dataRow);
		}
		table.rows.add(dataSet);
	}
	table.draw();
	$(".ipt-delivery-date").change(function(event) {
		isChange = true;
	});
}

/**
 * create content column Generated
 * 
 * @param orderDo
 * @returns
 */
function createGenerated(orderDo, userAuthority){
	var result = '';
	var divBorderRight = '<div class="col-xs-6 td-border-right display-content-middle">';
	var divBorderLeft = '<div class="col-xs-6 td-border-left display-content-middle">';
	var endDiv = '</div>';
	var textShow, isDisabledLeft, isDisabledRight, doActionLeft, doActionLeft;
	// Check GeneratedFg = 1
	// After [Generate New]
	if (orderDo.doGeneratedFg != "1"){
		textShow = "Generate";
		isDisabledLeft = false;
		isDisabledRight = true;
		doActionLeft = "doGeneratedGenerate('"+ orderDo.orderId +"', '"+ orderDo.doNo +"');";
		doActionRight = "";
	} else {
		if (orderDo.doSignbackFg == "1"){
			textShow = "View";
			isDisabledLeft = false;
			isDisabledRight = true;
			doActionLeft = "doGeneratedView('"+ orderDo.orderId +"', '"+ orderDo.doNo +"');";
			doActionRight = "";
		} else {
			textShow = "View";
			isDisabledLeft = false;
			isDisabledRight = false;
			doActionLeft = "doGeneratedView('"+ orderDo.orderId +"', '"+ orderDo.doNo +"');";
			doActionRight = "doGeneratedDelete('"+ orderDo.orderId +"', '"+ orderDo.doNo +"');";
		}
	}
	// SANKYU Admin can access to this screen as read-only. Only enable "Generated->View" and "Signed->View" link labels and [Close] button.
	// authority = 3 sankyu
	if (userAuthority && userAuthority == "3") {
		isDisabledRight = true;
		if (textShow == "Generate") {
			isDisabledLeft = true;
		}
	}
	// div generated left
	var divLeft = divBorderRight + createLinkLabel(textShow, isDisabledLeft, doActionLeft, "generatedLeft") + endDiv;
	// div generated right
	var divRight = divBorderLeft + createLinkLabel("Delete", isDisabledRight, doActionRight, "generatedRight") + endDiv;
	result = divLeft + divRight;
	return result;
}

/**
 * create content column Singed
 * 
 * @param orderDo
 * @returns
 */
function createSinged(orderDo, userAuthority){
	var result = '';
	var divBorderRight = '<div class="col-xs-6 td-border-right display-content-middle">';
	var divBorderLeft = '<div class="col-xs-6 td-border-left display-content-middle">';
	var endDiv = '</div>';
	var textShow, isDisabledLeft, isDisabledRight, doActionLeft, doActionLeft;
	// Check GeneratedFg = 1
	// After [Generate New]
	if (orderDo.doGeneratedFg != "1"){
		textShow = "Upload";
		isDisabledLeft = true;
		isDisabledRight = true;
		doActionLeft = "";
		doActionRight = "";
	} else {
		if (orderDo.doSignbackFg == "1"){
			textShow = "View";
			isDisabledLeft = false;
			isDisabledRight = false;
			doActionLeft = "doSignedView('"+ orderDo.orderId +"', '"+ orderDo.doNo +"');";
			doActionRight = "doSignedDelete('"+ orderDo.orderId +"', '"+ orderDo.doNo +"');";
		} else {
			textShow = "Upload";
			isDisabledLeft = false;
			isDisabledRight = true;
			doActionLeft = "doSignedUpload('"+ orderDo.orderId +"', '"+ orderDo.doNo +"');";
			doActionRight = "";
		}
	}
	// SANKYU Admin can access to this screen as read-only. Only enable "Generated->View" and "Signed->View" link labels and [Close] button.
	// authority = 3 sankyu
	if (userAuthority && userAuthority == "3") {
		isDisabledRight = true;
		if (textShow == "Upload") {
			isDisabledLeft = true;
		}
	}
	// div signed left
	var divLeft = divBorderRight + createLinkLabel(textShow, isDisabledLeft, doActionLeft, "signedLeft") + endDiv;
	// div signed right
	var divRight = divBorderLeft + createLinkLabel("Delete", isDisabledRight, doActionRight, "signedRight") + endDiv;
	result = divLeft + divRight;
	return result;
}

/**
 * create input[type=radio]
 * 
 * @param orderId
 * @param DONo
 * @returns
 */
function createRadioButton(orderDo, rowId, userAuthority){
	// tag input radio
	var radio = "";/*'<input type="radio" name="selectionDO" '
				+ 'onclick="selectedRecord(\''+ orderDo.orderId +'\', \''+ orderDo.doNo + '\', ' + rowId + ')">';*/
	if (orderDo.doSignbackFg == "1" || (userAuthority && userAuthority == "3")){
		radio = '<input type="radio" name="selectionDO" disabled="disabled">';
	} else {
		radio = '<input type="radio" name="selectionDO" '
			+ 'onclick="selectedRecord(\''+ orderDo.orderId +'\', \''+ orderDo.doNo + '\', ' + rowId + ')">';
	}
	return radio;
}
/**
 * create input raedonly in column Timestamp, D/O no
 * 
 * @param textShow
 * @returns
 */
function createInputReadonly(textShow, isDoNo){
	// Tag input
	var input = '';
	if (isDoNo){
		input = '<input type="text" class="input-readonly ipt-do-no" readonly="true" value="'+ replaceNull(textShow) +'">';
	}
	else{
		input = '<input type="text" class="input-readonly" readonly="true" value="'+ replaceNull(textShow) +'">';
	}
	return input;
}

/**
 * create link label in column Generated, Signed
 * 
 * @param textShow
 * @param isDisabled
 * @param doAction
 * @returns
 */
function createLinkLabel(textShow, isDisabled, doAction, classCss){
	// Tag a
	var a = '';
	if (!isDisabled){
		a = '<a class="link-label '+ classCss +'" onclick="'+ doAction +'">'+ textShow +'</a>';
	} else {
		a = '<a class="link-label-disabled">'+ textShow +'</a>';
	}
	return a;
}
/**
 * create content Delivery Date column
 * 
 * @returns
 */
function createDelivertDate(value, doGeneratedFg){
	var date = '', onclickInput= '', onclickButton = '', inputDisabled = '', buttonDisabled = '';
	if (value != null && value != undefined){
		date = value;
	}
	if (doGeneratedFg != null && doGeneratedFg != undefined){
		if (doGeneratedFg != "1"){
			onclickInput = 'onclick="openDatePicker1(this)"';
			onclickButton = 'onclick="openDatePicker(this)"';
			inputDisabled = "";
			buttonDisabled = "";
		} else  {
			onclickInput = "";
			onclickButton = "";
			inputDisabled = " input-readonly";
			buttonDisabled = ' disabled="disabled"';
		}
	} else {
		onclickInput = 'onclick="openDatePicker1(this)"';
		onclickButton = 'onclick="openDatePicker(this)"';
		inputDisabled = "";
		buttonDisabled = "";
	}
	var text = '';
	text += '<div class="input-group input-group-from div-delivery-date">';
	text += '<input type="text" class="datepicker ipt-delivery-date text-center'+ inputDisabled +'" id="" '+ onclickInput +' readonly="true" placeholder="DD-MMM-YY" maxlength="9"/ value="'+ date +'">';
	text += '<span class="input-group-btn span-delivery-date">';
	text += '<button id="" class="btn btn-default btn-delivery-date" type="button" '+ onclickButton +' '+ buttonDisabled +'>';
	text += '<span class="glyphicon glyphicon-calendar"></span>';
	text += '</button>';
	text += '</span>';
	text += '</div>';
	return text;
}

/**
 * if null => return ""
 * 
 * @param value
 * @returns
 */
function replaceNull(value) {
	return (value === null || value === "null") ? "" : value;
}


/**
 * Generated (View)
 * 
 * @param orderId
 * @param DONo
 * @returns
 */
function doGeneratedView(orderId, DONo) {
	window.location.href = "doDownloadAction?orderId=" + orderId 
						   + "&doNo=" + DONo + "&type=generated";
}

/**
 * Generated (Delete)
 * 
 * @param orderId
 * @param DONo
 * @returns
 */
function doGeneratedDelete(orderId, DONo) {
	// Set value selected orderId, DoNo
	selectedOrderId = orderId;
	selectedDoNo = DONo;
	
	showConfirmDialog("generatedDelete","","",{"position":"200"});
}

/**
 * Generated (Generate)
 * 
 * @param orderId
 * @param DONo
 * @returns
 */
function doGeneratedGenerate(orderId, DONo) {
	// Set value selected orderId, DoNo
	selectedOrderId = orderId;
	selectedDoNo = DONo;
	
	showConfirmDialog("generatedGenerate","","",{"position":"200"});
}

/**
 * Signed (View)
 * 
 * @param orderId
 * @param DONo
 * @returns
 */
function doSignedView(orderId, DONo) {
	window.location.href = "doDownloadAction?orderId=" + orderId 
	   					   + "&doNo=" + DONo + "&type=signed";
}

/**
 * Signed (Upload)
 * 
 * @param orderId
 * @param DONo
 * @returns
 */
function doSignedUpload(orderId, DONo) {
	// Open Browse File
	$('#uploadFile').val("");
	$("#uploadFile").click();
	
	// Set value selected orderId, DoNo
	selectedOrderId = orderId;
	selectedDoNo = DONo;
	
	//showConfirmDialog("signedUpload","","",{"position":"200"});
}

/**
 * Signed (Delete)
 * 
 * @param orderId
 * @param DONo
 * @returns
 */
function doSignedDelete(orderId, DONo) {
	// Set value selected orderId, DoNo
	selectedOrderId = orderId;
	selectedDoNo = DONo;
	
	showConfirmDialog("signedDelete","","",{"position":"200"});
}

/**
 * Action click button [Generate New]
 * 
 * @param orderId
 * @returns
 */
function doGenerateNew(orderId){
	// Set value selected orderId, DoNo
	selectedOrderId = orderId;
	
	showConfirmDialog("generateNew","","",{"position":"200"});
}

/**
 * Action click button [Save]
 * 
 * @param orderId
 * @returns
 */
function doSave(){
	showConfirmDialog("save","","",{"position":"200"});
	/*var params = {};
	params = JSON.stringify({
		listOrderDo
	});console.log(params);
	loading_ajax();
    $.ajax({
        cache: false,
        url : "doSaveAction",
        type : "POST",
        data: params,
        dataType : "json",
        traditional: true,
        success : function(response) {
            close_loading_ajax();
            console.log(response);
        },
        error:function(jqXhr, textStatus, errorThrown){
            alert(textStatus);
            close_loading_ajax();
        }
    });*/
}

/**
 * Delete record set [ORDER_DO].DELETE_FG = "1"
 * 
 * @param orderId
 * @param DONo
 * @returns
 */
function doDelete(orderId, DONo){
	// Set value selected orderId, DoNo
	selectedOrderId = orderId;
	selectedDoNo = DONo;
	
	showConfirmDialog("delete","","",{"position":"200"});
}

/**
 * call ajax
 * 
 * @param params
 * @param actionName
 * @param methodType
 * @returns
 */
function callAjax(params, actionName, methodType, page) {
	// Call ajax load data
	loading_ajax();
    $.ajax({
        cache: false,
        url : actionName,
        type : methodType,
        data: params,
        dataType : "json",
        success : function(response) {
            close_loading_ajax();
            // Draw datatable
            if (response.listPac0024Bean != null && response.listPac0024Bean != undefined){
            	// Set value to listOrderDo
            	listOrderDo = response.listPac0024Bean;
            	// Draw datatable
            	drawData(response.listPac0024Bean);
            	$(".btn-delete").attr("disabled", true);
            } else {
            	table.clear().draw();
            }
            // Generate New Flag
            var generateNewFlag = response.generateNewFlag;
        	// Check generateNewFlag if is false disabled button Generate New
        	if (generateNewFlag != undefined && generateNewFlag != null){
        		if (!generateNewFlag){
        			$("#generateNew").attr("disabled", true);
        		} else {
        			$("#generateNew").attr("disabled", false);
        		}
        	}
        	
        	// Set tabindex
        	setTabIndex();
        },
        error:function(jqXhr, textStatus, errorThrown){
            alert(textStatus);
            close_loading_ajax();
        }
    });
}

/**
 * call ajax SignedUpload
 * 
 * @param params
 * @param actionName
 * @param methodType
 * @returns
 */
function callAjaxSignedUpload(params, actionName, methodType, page) {
	// Call ajax load data
	loading_ajax();
    $.ajax({
        cache: false,
        url : actionName,
        type : methodType,
        data: params,
        dataType : "json",
        processData:false,
        contentType: false,
        enctype: "multipart/form-data",
        success : function(response) {
            close_loading_ajax();
            // Draw datatable
            if (response.listPac0024Bean != null && response.listPac0024Bean != undefined){
            	// Set value to listOrderDo
            	listOrderDo = response.listPac0024Bean;
            	// Draw datatable
            	drawData(response.listPac0024Bean);
            	$(".btn-delete").attr("disabled", true);
            } else {
            	table.clear().draw();
            }
            // Generate New Flag
            var generateNewFlag = response.generateNewFlag;
        	// Check generateNewFlag if is false disabled button Generate New
        	if (generateNewFlag != undefined && generateNewFlag != null){
        		if (!generateNewFlag){
        			$("#generateNew").attr("disabled", true);
        		} else {
        			$("#generateNew").attr("disabled", false);
        		}
        	}
        	
        	// Set tabindex
        	setTabIndex();
        },
        error:function(jqXhr, textStatus, errorThrown){
            alert(textStatus);
            close_loading_ajax();
        }
    });
}

/**
 * Method will call when Click button yes on dialog confirm
 * @param idEvent Event Save/Delete
 * @param element Dialog confirm
 */
function doEventDialog01(idEvent, element) {
	// In case Generate new
	if (idEvent == "generateNew"){
		// Parameter call ajax
		var params = {};
		params.orderId = selectedOrderId;

		// Call ajax delete and load data
		callAjax(params, "doGenerateNewAction", "POST", 0);
		// Reset
		selectedOrderId = "";
		selectedDoNo = "";
		isChange = true;
	}
	
	// In case Delete record or generatedDelete or generatedGenerate or signedDelete
	if (idEvent == "delete" || idEvent == "generatedDelete" 
		|| idEvent == "generatedGenerate" || idEvent == "signedDelete") {
		var action = "";
		// Parameter call ajax
		var params = {};
		params.orderId = selectedOrderId;
		params.doNo = selectedDoNo;
		
		// In case Delete record when click button [Delete]
		if (idEvent == "delete"){
			action = "doDeleteAction";
		}
		
		// In case Delete DO when click Generated (Delete)
		if (idEvent == "generatedDelete"){
			action = "doGeneratedDeleteAction";
			params.type = "generated";
		}
		
		// In case Delete DO when click Generated (Delete)
		if (idEvent == "generatedGenerate"){
			action = "doGeneratedGenerateAction";
		}
		
		// In case Delete Signed when click Signed (Delete)
		if (idEvent == "signedDelete"){
			action = "doSignedDeleteAction";
		}
		
		// Call ajax and load data
		callAjax(params, action, "POST", 0);
		// Reset
		selectedOrderId = "";
		selectedDoNo = "";
		isChange = true;
	}
	
	// In case Upload Signed when click Signed (Upload)
	if (idEvent == "signedUpload") {
		var action = "doSignedUploadAction";
		// Parameter call ajax
		var formData = new FormData();
		formData.append("orderId", selectedOrderId);
		formData.append("doNo", selectedDoNo);

		
		var fileUpload = $('#uploadFile').prop('files')[0];
		if (fileUpload != null && fileUpload != undefined && fileUpload != ""){
			formData.append("fileUpload", fileUpload);
		} else {
			return;
		}

		// Call ajax Upload file and load data
		callAjaxSignedUpload(formData, action, "POST", 0);
		// Reset
		selectedOrderId = "";
		selectedDoNo = "";
		$('#uploadFile').val("");
		isChange = true;
	}
	
	// In case save when button [Save]
	if (idEvent == "save"){
		var params = {};
		// Create parameter list orderDo
		var jsonParamListOrderDo = createParamDoSave();
		var orderId = $("#orderId").val().trim();
		
		// Set value parameter
		params.orderId = orderId;
		params.jsonParams = JSON.stringify(jsonParamListOrderDo);

		// Call ajax and load data
		callAjax(params, "doSaveAction", "POST", 0);
		
		// Reset
		selectedOrderId = "";
		selectedDoNo = "";
		isChange = false;
	}
	
	// In case close
	if (idEvent == "close") {
		/*$("#redirectFunctionId").val('fac001');
		$("#redirectScreenId").val('sac0022');
		$("form[id=form_menu]").submit();*/
		var orderId = $("#orderId").val().trim();
		window.location.href = "fac001/sac0022.action?orderId=" + orderId;
	}
}

/**
 * show dialog
 * 
 * @param idEvent
 * @param listButton
 * @param param
 * @param paramSettingDialog
 * @returns
 */
function showConfirmDialog(idEvent, listButton, param, paramSettingDialog) {
	paramForRedirectMenu = param;
	var messageId = "";
	if (idEvent == "redirect") {
		messageId = "CA-0013";
	} else if (idEvent == "redirectMenu") {
		messageId = "CA-0002";
	} else if (idEvent == "delete") {
		messageId = "DO-0001";
	} else if (idEvent == "close") {
		messageId = "DO-0003";
	} else if (idEvent == "logout") {
		messageId = "CA-0001";
		var contentConfirmLogout = getMessageWithParams(messageId,"");
		document.getElementById("contentConfirmLogout").innerHTML = contentConfirmLogout;
		$('#buttonYesLogout').attr('onclick', "doYes('"+idEvent+"')");
		$("#modalConfirmLogout").modal("show");
		return;
	} else if (idEvent == "generateNew") {
		messageId = "DO-0004";
	} else if (idEvent == "save") {
		messageId = "DO-0002";
	} else if (idEvent == "generatedDelete") {
		messageId = "DO-0005";
	} else if (idEvent == "generatedGenerate") {
		messageId = "DO-0004";
	} else if (idEvent == "signedUpload") {
		messageId = "DO-0006";
	} else if (idEvent == "signedDelete") {
		messageId = "DO-0005";
	}
	
	var contentConfirm = getMessageWithParams(messageId,"");
	document.getElementById("contentConfirm").innerHTML = /*messageId+" : "+*/ contentConfirm;
	if (idEvent == "close"){
		$("#contentConfirm").parent().parent().css("text-align", "center");
	}
	$('#buttonYes').attr('onclick', "doYes('"+idEvent+"')");
	$("#modalConfirm").modal("show");
}

/**
 * Open date picker
 */
function openDatePicker(button){
	var input = $(button).parent().parent().find("input");
	$(input).datepicker({
		dateFormat : 'dd-M-y',
		showOn: 'focus',
        showButtonPanel: true,
        closeText: 'Clear', // Text to show for "close" button
        onClose: function () {
            var event = arguments.callee.caller.caller.arguments[0];
            // If "Clear" gets clicked, then really clear it
            if ($(event.delegateTarget).hasClass('ui-datepicker-close')) {
                $(this).val('');
            }
        }
	});
	$(input).datepicker("show");
}

/**
 * Open date picker 1
 */
function openDatePicker1(button){
	$(button).datepicker({
		dateFormat : 'dd-M-y',
		showOn: 'focus',
        showButtonPanel: true,
        closeText: 'Clear', // Text to show for "close" button
        onClose: function () {
            var event = arguments.callee.caller.caller.arguments[0];
            // If "Clear" gets clicked, then really clear it
            if ($(event.delegateTarget).hasClass('ui-datepicker-close')) {
            	$(this).val('');
            }
        }
	});
	$(button).datepicker("show");
}

/**
 * event selected record
 * 
 * @param orderId
 * @param DONo
 * @returns
 */
function selectedRecord(orderId, DONo, rowId){
	$(".btn-delete").attr("disabled", true);
	if (listOrderDo[rowId].doSignbackFg == null || listOrderDo[rowId].doSignbackFg == undefined || listOrderDo[rowId].doSignbackFg == ""){
		// Enable button [Delete]
		$(".btn-delete").attr("disabled", false);
		$(".btn-delete").attr("onclick", "");
		$(".btn-delete").attr("onclick", "doDelete('"+ orderId +"', '"+ DONo +"');");
	}
}

/**
 * create parameter call ajax when click button [Save]
 * 
 * @returns
 */
function createParamDoSave(){
	var param = [];
	var orderId = $("#orderId").val().trim();
	$(".ipt-do-no").each(function(index) {
		var orderNo = {};
		orderNo.orderId = orderId;
		orderNo.doNo = $(this).val().trim();
		orderNo.deliverDt = $(".ipt-delivery-date").eq(index).val().trim();
		param.push(orderNo);
	});
	return param;
}

/**
 * Close confirm dialog
 */
function doCloseConfirm() {
	$('#uploadFile').val("");
	$('#modalConfirm').modal('hide');
}

/**
 * set tab index
 * 
 * @returns
 */
function setTabIndex(){
	$("input, select, a").attr('tabindex','-1');
	$("#orderId").attr("tabindex", 0);
	$(".ipt-delivery-date").attr("tabindex", 1);
	$(".btn-delete").attr("tabindex", 2);
	$("#generateNew").attr("tabindex", 3);
	$("#doSave").attr("tabindex", 4);
	$(".btn-close").attr("tabindex", 5);	
	$(".generatedLeft").attr("tabindex", 6);
	$(".generatedRight").attr("tabindex", 7);
	$(".signedLeft").attr("tabindex", 8);
	$(".signedRight").attr("tabindex", 9);
	$(".btn-logout").attr("tabindex", 10);
	$("#orderId").first().focus();
	$(".ipt-delivery-date").focus(function(event) {
		$(this).click();
	});
	$(".user-login-css").keydown(function(e){
		if($('.btn-logout').is(":focus") && (e.which || e.keyCode) == 9 && !event.shiftKey) {
			e.preventDefault();
			$(".ipt-delivery-date").eq(0).first().focus();
		}
	});
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

function createOrderDo(){
	var orderNo = {
		orderId: "18-002-SG",
		timestamp: "21-Jan-18 08:02:23",
		doNo: "DO-180123-01",
		seqNo: "01",
		customerCompanyCd: "SG",
		doSignbackFg: "",
		deliverDt: "",
		deleteFg: ""
	};
	listOrderDo.push(orderNo);
}