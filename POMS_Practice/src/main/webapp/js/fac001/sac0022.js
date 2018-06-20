var listItemObject = [];
var listEditedItemIndex = [];
var listEditedItemObject = [];
var table;
var allDeliveryDateFilled = true;
var kddiClickDo = false;

$(document).ready(function() {
	// $("#tableData").addClass("hidden");
	getListItem();
	$('#confirmRedirectMenu').val(true);
	/*$("#deliveryOrder").click(function() {
		location.href = contextPath+"fac001/sac0024.action?orderId="+$("#orderNo").val();
	});*/

	$(".left-side").hide();
	initDatatable();
	drawData(listItemObject);
	$('.content').on('focusout', 'input[type="text"]', function() {
		var res = this.id.split("_");
		var index = res[1];
		if ($.inArray(index, listEditedItemIndex) < 0) {
			listEditedItemIndex.push(index);
		}
	});

	$('.content').on('keypress', '.alphanumberic', function(e) {
		var key = e.keyCode;
		if (!checkAlphanumeric(String.fromCharCode(key), false)){
			return false;
		}
	});
	table.on( 'draw', function () {
		configFixedColumn();
		isCopyPasteCut();
		addEventChangeStartDate();
	});

	$("#datepicker").on("change",function(e){
		
	})
});

/**
 * init datatable
 * 
 * @returns
 */
function initDatatable(){
	table = $('#resultTable').DataTable({
		"ordering": false,
		"scrollX": true,
		"sScrollY" : calcDataTableHeight(13),
		"scrollCollapse" : true,
		'bLengthChange' : true,
		"paging": true,
		"searching": false,
		"fixedColumn": true,
		"info": true,
		"lengthMenu": [10, 25, 50, 100],
		"iDisplayLength": 25,
		fixedColumns:   {
            leftColumns: 3
        },
        "asStripeClasses": [],
        "aoColumns" : [
			{"sClass" : "t-left td-readonly", },
			{"sClass" : "t-left td-readonly", },
			{"sClass" : "t-center td-readonly", },
			{"sClass" : "t-left no-padding-left", },
			{"sClass" : "t-left no-padding-left", },
			{"sClass" : "t-left no-padding-left", },	
			{"sClass" : "t-center td-readonly", },
			{"sClass" : "t-center td-readonly", },
			{"sClass" : "t-right td-readonly", },
			{"sClass" : "t-center", },
			{"sClass" : "t-left no-padding-left", },
			{"sClass" : "t-left no-padding-left", }
		],
        "fnInitComplete" : function() {
			fixedMarginLeftScrollX();
			$('.dataTables_scrollBody').perfectScrollbar();
			removeClassOnThead("td-readonly");
		},
		"dom": "<'row'<'col-xs-2'><'col-xs-8'><'col-xs-2'l>>" +
		"<'row'<'col-xs-12'tr>>" +
		"<'row margin-footer-datatable'<'col-xs-3'i><'col-xs-5'><'col-xs-4'p>>"
	});
}


/**
 * fixedMarginLeftScrollX
 * 
 */
function fixedMarginLeftScrollX(){
	var marginLeftBefor = $("#resultTable thead tr").find("th").eq(0).outerWidth()
					+ $("#resultTable thead tr").find("th").eq(1).outerWidth()
					+ $("#resultTable thead tr").find("th").eq(2).outerWidth();
	var bottom = 0;
	var heightDatatableScrollBody = $("div.dataTables_scrollBody").outerHeight();
	var heightTableBody = $("div.dataTables_scrollBody #resultTable").outerHeight();
	bottom = heightDatatableScrollBody - heightTableBody;
	if (bottom < 0){
		bottom = 0;
	}
	$("head style").remove();
	$("<style>").prop("type", "text/css")
    	.html("\
    			.ps-scrollbar-x-rail {\
    				margin-left: "+ (marginLeftBefor) +"px !important;\
    				margin-right: 2px !important;\}")
    	.appendTo("head");
}

/**
 * configFixedColumn
 * 
 * @returns
 */
function configFixedColumn() {
	$("div.DTFC_LeftWrapper div.DTFC_LeftBodyWrapper").css("height",$("div.dataTables_scrollBody").height());
	$("div.DTFC_LeftWrapper div.DTFC_LeftBodyWrapper div.DTFC_LeftBodyLiner").css("max-height",$("div.dataTables_scrollBody").height());
	$("div.DTFC_LeftWrapper div.DTFC_LeftBodyWrapper div.DTFC_LeftBodyLiner").css("height",$("div.dataTables_scrollBody").height());
	$("div.DTFC_LeftWrapper").css("width",$("div.DTFC_LeftWrapper table.data-table").width());
	$("div.DTFC_LeftBodyLiner").css("width",$("div.DTFC_LeftWrapper table.data-table").width() + 17);
}

function getListItem() {
	
	 loading_ajax(); 
	 $.ajax({
		 cache:false,
		 url : contextPath + 'fac001/sac0022_01', 
		 type : "GET", 
		 data : { orderId : $("#orderNo").val() }, 
		 dataType : "json", 
		 contentType : "application/json;charset=utf-8", 
		 success : function(response) { 
			 close_loading_ajax();
			 listItemObject = response.lstOrderLease;
			 drawData(listItemObject); 
			 configFixedColumn();
			 
			// SANKYU Admin can access to this screen as read-only. Only enable [D/O] and [Close] buttons.
			// authority = 3 sankyu
			if (response.userAuthority && response.userAuthority == "3") {
				$("#deliverSave").attr("disabled", true);
			} else {
				$("#deliverSave").attr("disabled", false);
			}
		 }, 
		 error : function(jqXhr, textStatus, errorThrown) { 
			 close_loading_ajax(); 
		 } 
	});

}
/**
 * Draw table after search
 */
function drawData(listData) {
	var dataSet = [];
	var length = listData.length;
	table.clear();
	if (length > 0) {
		for (var i = 0; i < length; i++) {
			var dataRow = [];

			var itemCd = replaceNull(
					inputLabelAndHidden(listData[i].itemCd, listData[i].displayOrder, "itemCd_" + i, "displayOrder_" + i));
			var itemName = replaceNull(listData[i].itemName);
			var categoryAbbrev = replaceNull(
					inputLabelAndHidden(listData[i].categoryName, listData[i].categoryCd, "categoryAbbrev_" + i, "categoryCd_" + i));

			var leaseNo = replaceNull(inputText(listData[i].leaseNo, "leaseNo_" + i, false));
			var serialNo = replaceNull(inputText(listData[i].serialNo, "serialNo_" + i, listData[i].isDisabled >= 1 ? true:false));
			var assetNo = replaceNull(inputText(listData[i].assetNo, "assetNo_" + i, false));
			// Start Date
			var deliverDt = replaceNull(listData[i].deliverDt);
			var deliverDtCell = replaceNull(inputTextDate(deliverDt, "deliverDt_" + i))
			var endDt;
			//deliverDt = '11-JAN-11';
			//var endDt = replaceNull(listData[i].endDt);
			if (deliverDt !== "") {
				//※ [End Date] is calculated as: [Delivery Date (Start Date)] + 4 Years
				/*var res = deliverDt.split("-");
				var newYear = parseInt(res[2]) + 4;
				endDt = res[0] + "-" + res[1] + "-" + newYear;*/
//				endDt = createInputReadonly(getEndDt(deliverDt), "endDt_" + i);
				endDt = createInputReadonly(replaceNull(listData[i].endDt), "endDt_" + i);
			} else {
				endDt = createInputReadonly("", "endDt_" + i);
				allDeliveryDateFilled = false;
			}
			
			var leaseFee = convertNumberToString7v2(listData[i].leaseFee);

			var warrantyDt = replaceNull(inputTextDate(listData[i].warrantyDt, "warrantyDt_" + i));
			var officeLicNo = replaceNull(inputText(listData[i].officeLicNo, "officeLicNo_" + i, false));
			var accessLicNo = replaceNull(inputText(listData[i].accessLicNo, "accessLicNo_" + i, false));

			dataRow.push(itemCd, itemName, categoryAbbrev, leaseNo, serialNo,
					assetNo, deliverDtCell, endDt, leaseFee, warrantyDt,
					officeLicNo, accessLicNo);
			dataSet.push(dataRow);
		}
		table.rows.add(dataSet);
	}
	table.draw();
	$("#tableData").removeClass("hidden");
	$('.dataTables_scrollBody > table tr').find('td:eq(6) > input').removeAttr('readonly');
	$('.dataTables_scrollBody > table tr').find('td:eq(6) > input').removeClass('td-readonly');
	$('.dataTables_scrollBody > table tr').find('td:eq(6) > input').addClass('enable-date');
	$('[id^=deliverDt_]').addClass('start-date');
	$('[id^=deliverDt_]').next().addClass('end-date-picker');
}

/**
 * 
 * @returns
 */
function inputText(data, id, isDisalbed) {
	var type = "";
	var text = "";
	if (id != null && id != undefined){
		var temp = id.split("_")[0];
		if (temp === "leaseNo" || temp === "serialNo"){
			type = temp + "Validate";
		}
	}
	text += '<div class="col-sm-12">';
	if (isDisalbed) {
		text += '<input type="text" readonly="true" class="form-control none-border padding-input-text copy-paste-cut input-cursor-not-allowed '+ type +'" id="' + id 
				+ '" class="form-control" maxlength="30" value="' + data + '">';
	} else {
		text += '<input type="text" class="none-border padding-input-text '+ type +'" id="' + id 
				+ '" class="form-control " maxlength="30" value="' + data + '">';
	}
	text += '</div>';
	return text;
}

/**
 * 
 * @returns
 */
function inputTextDate(data, id) {
	var text = "";
	
	text += '<div class="input-group input-group-from div-delivery-date">';
	text += '<input type="text" class="datepicker ipt-delivery-date text-center" id="' + id + '" onclick="openDatePicker1(this)" readonly="true" placeholder="DD-MMM-YY" maxlength="9" value="' + data + '">';
	text += '<span class="input-group-btn span-delivery-date">';
		text += '<button id="" class="btn btn-default btn-delivery-date" type="button" onclick="openDatePicker(this)">';
			text += '<span class="glyphicon glyphicon-calendar">';
				text += '</span>';
					text += '</button>';
						text += '</span>';
						text += '</div>';
	return text;
}

/**
 * 
 * @returns
 */
function inputLabelAndHidden(data1, data2, id1, id2) {
	var text = "";
	text += '<div class="col-sm-12">';
	text += '<label id="' + id1 + '" class="input-label-hidden text-ellipsis">' + data1 + '</label>';
	text += '<input type="hidden" id="' + id2 + '" value="' + data2 + '">';
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
	return (typeof value === "undefined" || value === null || value === "null") ? "" : value.toString().replace("null", "").replace("undefined", "");
}

function doEventDialog01(idEvent, element){
	// In case close
	if (idEvent == "close"){
		$("#redirectFunctionId").val('fac001');
		$("#redirectScreenId").val('sac0021');
		$("form[id=form_menu]").submit();
	}
}

function EditedItem(orderId, itemCd, categoryCd, displayOrder, 
					leaseNo, serialNo, assetNo, warrantyDt,
					officeLicNo, accessLicNo, deliverDt, endDt) {
	this.orderId = orderId;
	this.itemCd = itemCd;
	this.categoryCd = categoryCd;
	this.displayOrder = displayOrder;
	this.leaseNo = leaseNo;
	this.serialNo = serialNo;
	this.assetNo = assetNo;
	this.warrantyDt = warrantyDt;
	this.officeLicNo = officeLicNo;
	this.accessLicNo = accessLicNo;
	this.deliverDt = deliverDt;
	this.endDt = endDt;
}

function doSave(){
	if (checkSaveInput()) {
		kddiClickDo = false;
		showConfirmDialog("save", "", "", {
			"position" : "200"
		});
	}
}

/**
 * validate before save
 * 
 * @returns
 */
function checkSaveInput(){
	$("input").removeClass("border-error");
	var result = true;
	// Validate 3 field Lease No, Serial No, Wanranty end date
	var errorList = ["", "", ""];
	$(".leaseNoValidate").each(function(index, element){
		var warrantyDt = $(element).closest("tr").find("td").eq(9).find(".ipt-delivery-date");
		var leaseNo = $(element).closest("tr").find("td").eq(4).find(".serialNoValidate");
		
		// Check LeaseNo is not empty or warranty date not empty
		if ($(element).val().trim() !== "" || warrantyDt.val().trim() !== ""){
			if (leaseNo.val().trim() === "" || leaseNo.val().trim() === null || leaseNo.val().trim() === undefined){
				leaseNo.addClass("border-error");
				if (result){
					errorList[0] = "[Serial No]";
				}
				result = false;
			}
		}
	});
	
	if (!result) {
		showErrorDialog('LIE-0003', "", errorList);
		return result;
	}
	
	return result;
	/*// Validate 3 field Lease No, Serial No, Wanranty end date
	 var errorList = ["", "", ""];
	$("[id^=leaseNo_]").each(function(index, element){
		var countNotEmpty = 0, leaseFg = true, serialFg = true, warrantyFg = true;
		// Check LeaseNo is empty
		if ($(element).val() === ""){
			$(element).addClass("border-error");
			countNotEmpty++;
			leaseFg = false;
		}
		// Check SerialNo is empty
		if ($("[id=serialNo_"+index+"]").val() === ""){
			$("[id=serialNo_"+index+"]").addClass("border-error");
			countNotEmpty++;
			serialFg = false;
		}
		// Check WarrantyDate is empty
		if ($("[id=warrantyDt_"+index+"]").val() === ""){
			$("[id=warrantyDt_"+index+"]").addClass("border-error");
			countNotEmpty++;
			warrantyFg = false;
			
		}
		// if LeaseNo, SerialNo, WarrantyDate is empty
		if (countNotEmpty === 3){
			$(element).removeClass("border-error");
			$("[id=serialNo_"+index+"]").removeClass("border-error");
			$("[id=warrantyDt_"+index+"]").removeClass("border-error");
		} else if (countNotEmpty > 0 && countNotEmpty < 3){
			if (!leaseFg){
				errorList[0] = "[Lease No]";
			}
			if (!serialFg){
				errorList[1] = "[Serial No]";
			}
			if (!warrantyFg){
				errorList[2] = "[Warranty End Date]";
			}
			result = false;
		}
	});
	
	if (!result) {
		showErrorDialog('LIE-0003', "", errorList);
		return result;
	}
	return result;*/
	
	/*//Check lease no mandatory only if all delivery date filled
	if (allDeliveryDateFilled) {
		$("[id^=leaseNo_]").each(function(index, element){
			if ($(element).val() === "") {
				$(element).addClass("border-error");

				result = false;
			} else {
				$(element).removeClass("border-error");
			}
		});
	}
	
	if (!result) {
		showErrorDialog('LIE-0001');
		return result;
	}
	
	//Check waranty date is DD-MMM-YY
	$("[id^=warrantyDt_]").each(function(index, element){
		if (($(element).val() !== "") && !isDate($(element).val())) {
			$(element).addClass("border-error");

			result = false;
		} else {
			$(element).removeClass("border-error");
		}
	});
	
	if (!result) {
		showErrorDialog('LIE-0002');
	}
	
	return result;*/
}

/**
 * Method will call when Click button yes on dialog confirm
 * @param idEvent Event Save/Delete
 * @param element Dialog confirm
 */
function doEventDialog01(idEvent, element){
	// In case save
	if (idEvent == "save"){
		for (var i = 0; i < listEditedItemIndex.length; i++) {
			var index = listEditedItemIndex[i];
			listEditedItemObject.push(new EditedItem($("#orderNo").val(),
					$("#itemCd_" + index).text(),
					$("#categoryCd_" + index).val(),
					$("#displayOrder_" + index).val(),
					$("#leaseNo_" + index).val(),
					$("#serialNo_" + index).val(),
					$("#assetNo_" + index).val(),
					$("#warrantyDt_" + index).val(),
					$("#officeLicNo_" + index).val(),
					$("#accessLicNo_" + index).val(),
					$("#deliverDt_" + index).val(),
					$("#endDt_" + index).val()));
		}
		
		loading_ajax(); 
		 $.ajax({
			 cache:false,
			 url : contextPath + 'fac001/sac0022_02', 
			 type : "POST", 
			 data : { listEditedItemObject : JSON.stringify({arr:listEditedItemObject}),
				 	  orderId : $("#orderNo").val().trim()
			 		}, 
			 dataType : "json", 
			 //contentType : "application/json;charset=utf-8", 
			 success : function(response) { 
				 close_loading_ajax();
			 }, 
			 error : function(jqXhr, textStatus, errorThrown) { 
				 close_loading_ajax(); 
			 } 
		});
		// Check kddiClickDo = true
		 // If kddi click [D/O] button
		if (kddiClickDo){
			window.location.href = contextPath+"fac001/sac0024.action?orderId="+$("#orderNo").val();
		}
	}
	
	// In case close
	if (idEvent == "close"){
		paramForRedirectMenu = ["fac001", "sac0021"];
		$("#redirectFunctionId").val(paramForRedirectMenu[0]);
		$("#redirectScreenId").val(paramForRedirectMenu[1]);
		$("form[id=form_menu]").submit();
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
	if (idEvent == "save") {
		messageId = "IM2-0002";
	} else if (idEvent == "close") {
		messageId = "OP-0011";
	} else if (idEvent == "logout") {
		messageId = "CA-0001";
		var contentConfirmLogout = getMessageWithParams(messageId,"");
		document.getElementById("contentConfirmLogout").innerHTML = contentConfirmLogout;
		$('#buttonYesLogout').attr('onclick', "doYes('"+idEvent+"')");
		$("#modalConfirmLogout").modal("show");
		return;
	} else if (idEvent == "redirectMenu") {
		messageId = "CA-0002";
	} else if (idEvent == "redirect") {
		messageId = "CA-0013";
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
 * Check date DD-MMM-YY
 * @param dateInput
 * @returns
 */
function isDate(dateInput) {

    if (dateInput == '') return false;

    //Declare Regex  
    var rxDatePattern = /^(\d{1,2})(\/|-)(?:(\d{1,2})|(jan)|(feb)|(mar)|(apr)|(may)|(jun)|(jul)|(aug)|(sep)|(oct)|(nov)|(dec))(\/|-)(\d{2})$/i;

    var dtArray = dateInput.match(rxDatePattern);

    if (dtArray == null) return false;

    var dtDay = parseInt(dtArray[1]);
    var dtMonth = parseInt(dtArray[3]);
    var dtYear = parseInt(dtArray[17]);

    if (isNaN(dtMonth)) {
        for (var i = 4; i <= 15; i++) {
            if ((dtArray[i])) {
                dtMonth = i - 3;
                break;
            }
        }
    }

    if (dtMonth < 1 || dtMonth > 12) return false;
    else if (dtDay < 1 || dtDay > 31) return false;
    else if ((dtMonth == 4 || dtMonth == 6 || dtMonth == 9 || dtMonth == 11) && dtDay == 31) return false;
    else if (dtMonth == 2) {
        var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
        if (dtDay > 29 || (dtDay == 29 && !isleap)) return false;
    }

    return true;
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
 * remove class on thead
 * 
 * @param className
 * @returns
 */
function removeClassOnThead(className){
	$(".data-table thead tr th").each(function(index) {
		$(this).removeClass(className);
	});
}

/**
 * Check value input type number for copy, paste, cut
 * 
 */
function isCopyPasteCut() {
   $('.copy-paste-cut').bind("cut copy paste",function(e) {
	   var val = e.originalEvent.clipboardData.getData('Text');console.log(val);
	   if (!checkAlphanumeric(val, false)) {
		   e.preventDefault();
	   }
   });
}

/**
 * show dialog error
 * 
 * @param messageId
 * @param params
 * @returns
 */
function showErrorDialog(messageId,params, errorList) {
	var message = getMessageWithParams(messageId,params);
	if (errorList.length === 3){
		if (errorList[0] !== ""){
			message += " " + errorList[0];
		}
		if (errorList[1] !== ""){
			if (errorList[0] !== ""){
				message += ",";
			}
			message += " " + errorList[1];
		}
		if (errorList[2] !== ""){
			if (errorList[0] !== "" || errorList[1] !== ""){
				message += ",";
			}
			message += " " + errorList[2];
		}
	}
	message += ".";
	var title = "<span class='glyphicon glyphicon-remove-circle'></span>  Error";
	document.getElementById("titleModal").innerHTML = title;
	document.getElementById("contentMessage").innerHTML = message;
	$("#modalInfo_image").attr("src","../style/imgs/errorIcon.png");
	$("#modalInfo").modal("show");
}

/**
 * event when click [D/O]  button
 * 
 * @returns
 */
function doDO(){
	// Get user authority
	var userAuthority = $("#userAuthority").val().trim();
	// If userAuthority <> 1
	if (userAuthority && userAuthority !== "1"){
		// If userAuthority = "3"
		if (userAuthority === "3"){
			window.location.href = contextPath+"fac001/sac0024.action?orderId="+$("#orderNo").val();
		}
		// If userAuthority = "2"
		if (userAuthority === "2"){
			if (checkSaveInput()) {
				kddiClickDo = true;
				showConfirmDialog("save", "", "", {"position" : "200"});
			}
		}
	}
}

/**
 * create input readonly
 * 
 * @param data
 * @param id
 * @returns
 */
function createInputReadonly(data, id){
	var text = '';
	text += '<input type="text" readonly="true" class="text-center td-readonly none-border" id="'+ id +'" value="'+ data +'" placeholder="DD-MMM-YY">';
	return text;
}

function doClose(){
	showConfirmDialog("close","",["fac001", "sac0021"],{"position":"200"})
}

function getEndDt(deliverDt){
	var startDt, endDt;
	startDt = new Date(deliverDt);
	
	if (typeof startDt.getMonth === 'function') {
		var year = startDt.getFullYear();
		var month = startDt.getMonth();
		var date = startDt.getDate();
		
		var endDate = new Date(year+4, month, date-1).toString();
		endDt = endDate.substring(8,10)+'-'+endDate.substring(4,7)+'-'+endDate.substring(13,15);
	}
	return endDt;
}

function addEventChangeStartDate(){
	$("[id^=deliverDt_]").change(function(event){
		var inputStartDate = $(this);
		var startDate = inputStartDate.val();
		var index = parseInt(inputStartDate.attr("id").replace(/[^\d]*/i, ""));
		var endDate;
		
		if(typeof startDate === "undefined" || startDate === null || startDate === "null" || startDate === ""){
			endDate = replaceNull(null);
		} 
		else {
			endDate = getEndDt(startDate);
		}
		$("#endDt_" + index).val(replaceNull(endDate));
	});
}