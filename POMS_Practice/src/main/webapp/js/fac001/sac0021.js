var table;
var listData = [];
var selectedOrderId;
//Same height td in css (27px)
var heightRow = 27;
var descendingOrder = false;
var paramDownloadReport = {};

$(document).ready(
		function() {
			initButton();

			$('#confirmRedirectMenu').val(true);
			$(".left-side").hide();
			tableInit("#resultTable");

			setTabIndex();
			
			// Get userAuthority
			var userAuthority = $("#authority").val().trim();
			// If user authority is KDDI or Sankyu enter the screen
			// Show all order
			if (userAuthority === "2" || userAuthority === "3"){
				descendingOrder = true;
				// parameter call ajax
				var params = {};		
				// Set params
				params.companyCd = "";
				params.entryDateFrom = "";
				params.entryDateTo = "";
				params.nonDelivery = false;
				params.expiringOrders = false;
				params.descendingOrder = descendingOrder;
				paramDownloadReport = params;
				// Call ajax load data, draw table
				callAjax(params, "sac0021_01", "POST", 0, true);
			}
		});

/**
 * table init
 * 
 * @param idTable
 * @returns
 */
function tableInit(idTable) {
	table = $(idTable).DataTable({
		/*"columnDefs" : [ {
			"targets" : [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 ],
			"orderable" : false
		} ],*/
		//"lengthMenu" : [ 10, 20, 50, 100 ],
		"sScrollY" : calcDataTableHeight(18),
		"bScrollCollapse" : true,
		"scrollX" : false,
		ordering: false,
		paging: true,
		"asStripeClasses": [],
		"aoColumns" : [
			{"width" : "2%", "sClass" : "t-center" },
			{"width" : "5%", "sClass" : "t-left", },
			{"width" : "8%", "sClass" : "t-left", },
			{"width" : "6%", "sClass" : "t-right", },
			{"width" : "6%", "sClass" : "t-right", },
			{"width" : "5%", "sClass" : "t-center", },
			{"width" : "5%", "sClass" : "t-center", },
			{"width" : "7%", "sClass" : "t-center", },
			{"width" : "7%", "sClass" : "t-center", },	
			{"width" : "6%", "sClass" : "t-center", },
			{"width" : "10%", "sClass" : "t-center", },
			{"width" : "5%", "sClass" : "t-center", },
			{"width" : "10%", "sClass" : "t-center", },
			{"width" : "18%", "sClass" : "t-center", },
			{"width" : "0%", "sClass" : "hidden-column", }
		],
		"fnInitComplete" : function() {
			$('.dataTables_scrollBody').perfectScrollbar();
		},
		/*"dom" : '<"top"l>rt<"bottom"ip><"clear">',*/
		"dom": "<'row'<'col-xs-2'><'col-xs-8'><'col-xs-2'l>>" +
		"<'row'<'col-xs-12'tr>>" +
		"<'row margin-top-paging-table'<'col-xs-3'i><'col-xs-5'><'col-xs-4'p>>",
		"fnRowCallback" : function(nRow, aData, iDisplayIndex,
				iDisplayIndexFull) {
			// Set background grey when DELETE_FG = 1
			// Disabe button status, disable radio
			if (aData[14] == "1"){
				// Set background grey
				$(nRow).addClass("background-color-grey");
				// Disable radio
				$(nRow).find("td:first-child input[type=radio]").attr("disabled", true);
				// Disable button status
				$(nRow).find("td:nth-child(10)").find("div.padding-div-status button").each(function() {
		    		$(this).attr("disabled", true);
		    	});
				// Merge cells from SANKYU → Regist to KDDI → Lease
				if ($(nRow).find("td:nth-child(11)").attr("colspan") !== "4"){
					$(nRow).find("td:nth-child(11)").empty().attr("colspan", "4");
					$(nRow).find("td:nth-child(12)").remove();
					$(nRow).find("td:nth-child(12)").remove();
					$(nRow).find("td:nth-child(12)").remove();
					// Set Cancelled by [ORDER_DTL].UPDATED_USER to cell
					$(nRow).find("td:nth-child(11)").text("Cancelled by " + listData[iDisplayIndexFull][0].userCreateOrder);
				}
			}
			
			// Fix height to row in td
			var rowId = -1;
			// Forearch listData
			for (var i=0; i<listData[iDisplayIndexFull].length; i++){
				countDo = listData[iDisplayIndexFull][i].countRecordDeliver;
				if (i == 0 || (i != 0 && listData[iDisplayIndexFull][i].categoryCd != listData[iDisplayIndexFull][i-1].categoryCd)){
					rowId++;
					// If countRecordDeliver is number
					if (typeof countDo === "number" && countDo > 1){
						// Set height
						$(nRow).find("td:nth-child(2)").find("div.x4-data").eq(rowId).height(heightRow*countDo);
						$(nRow).find("td:nth-child(3)").find("div.x4-data").eq(rowId).height(heightRow*countDo);
						$(nRow).find("td:nth-child(4)").find("div.x4-data").eq(rowId).height(heightRow*countDo);
						$(nRow).find("td:nth-child(5)").find("div.x4-data").eq(rowId).height(heightRow*countDo);
						$(nRow).find("td:nth-child(6)").find("div.x4-data").eq(rowId).height(heightRow*countDo);
						$(nRow).find("td:nth-child(7)").find("div.x4-data").eq(rowId).height(heightRow*countDo);
						$(nRow).find("td:nth-child(8)").find("div.x4-data").eq(rowId).height(heightRow*countDo);
						$(nRow).find("td:nth-child(9)").find("div.x4-data").eq(rowId).height(heightRow*countDo);
					}
				}
			}
		}
	});
}

/**
 * if null => return ""
 * 
 * @param value
 * @returns
 */
function replaceNull(value) {
	return (value == null || value == "null" || value == undefined) ? "" : value;
}

/**
 * convert string to number
 * format number ##,###.##
 * 
 * @param value
 * @returns
 */
function convertAmt(value){
	var temp = "";
	if (value == null || value == "null" || value == undefined){
		return "0.00";
	} else {
		if (!isNaN(parseFloat(value))){
			return parseFloat(value).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,');
		} else {
			return "0.00";
		}
	}
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

function initButton(){
	$("#cancel").css("background-color", "");
	var widthDivButton = $(".btn-logout").parent().parent().outerWidth();
	var widthBtnKddiLog = $(".btn-kddi").parent().outerWidth() + $(".btn-log").parent().outerWidth();
	var widthBtnOther = $("#cancel").parent().outerWidth() + $(".btn-edit").parent().outerWidth() 
						+ $(".btn-download").parent().outerWidth() + $(".btn-clear").parent().outerWidth()
						+ $(".btn-search").parent().outerWidth() + $(".btn-close").parent().outerWidth()
						+ $(".btn-logout").parent().outerWidth();
	var marginLeftBtnKddi = (widthDivButton - widthBtnKddiLog - widthBtnOther)/2 - 30;
	$(".btn-kddi").parent().css("margin-left", marginLeftBtnKddi);
	$("#goToLog").width($(".btn-kddi").width());
}

/**
 * draw datatable
 * 
 * @returns
 */
function drawDataTable(listOrderDtl, userAuthority){
	var dataSet = [];
	var length = listOrderDtl.length;
	table.clear();
	if (length > 0) {
		for (var i = 0; i < length; i++) {
			var index = i + 1;
			var dataRow = [];
			// Column in table
			var checkBox = '';
			var orderId = '';
			var deptCd = '';
			var amtM = '';
			var amtO = '';
			var type = '';
			var qty = '';
			var lastUpdated = '';
			var updatedBy = '';
			var status = '';
			var sankyu = '';
			var sea = '';
			var kddiSg = '';
			var kddi = '';
			var kddiReceive = '';
			var kddiDeliverQty = '';
			var kddiDeliver = '';
			var kddiLease = '';
			var deleteFg = '';
			
			var startDivNoBorderTop = '<div class="row x4-data display-table"><div class="display-middle">';
			var startDivBorderTop = '<div class="row x4-data display-table"><div class="display-middle border-top-transparent">';
			var endDiv = '</div></div>';
			var startDivStatusNoBorderTop = '<div class="row x4-data display-table"><div class="display-middle padding-div-status">';
			var startDivStatusBorderTop = '<div class="row x4-data display-table"><div class="display-middle  padding-div-status border-top-transparent">';
			var startDivSankyu = '<div class="col-md-6 th-border-right display-table"><div class="display-middle no-border-top">';
			var startDivSankyuNoBorderRight = '<div class="col-md-6 th-border-right display-table no-border-right"><div class="display-middle no-border-top">';
			//var startDivKddi = '<div class="col-md-3 th-border-right display-table"><div class="display-middle no-border-top">';
			var startDivKddi = '<div class="col-md-3 th-border-right"><div class="display-table td-kddi"><div class="display-middle no-border-top">';
			//var startDivKddiNoBorderRight = '<div class="col-md-3 th-border-right display-table no-border-right"><div class="display-middle no-border-top">';
			var startDivKddiNoBorderRight = '<div class="col-md-3 th-border-right no-border-right"><div class="display-table td-kddi"><div class="display-middle no-border-top">';
			
			// Gerenate data
			for (var j=0; j<listOrderDtl[i].length; j++){
				var startDiv = startDivNoBorderTop;
				var startDivStatus = startDivStatusNoBorderTop;
				
				// If first row in td set border top
				if (j == 0){
					startDiv = startDivBorderTop;
					startDivStatus = startDivStatusBorderTop;
				}
				checkBox += startDiv + '<input type="radio" class="no-margin-top radio-order-dtl" name="orderDtl" value="other" onclick="editItem('+ i + ', ' + j +');"/>' + endDiv;
				orderId += startDiv + replaceNull(listOrderDtl[i][j].orderId) + endDiv;
				deptCd += startDiv + replaceNull(listOrderDtl[i][j].deptCd) + endDiv;
				amtM += startDiv + replaceNull(listOrderDtl[i][j].ttlMrc) + endDiv;
				amtO += startDiv + replaceNull(listOrderDtl[i][j].ttlOtc) + endDiv;
				type += startDiv + replaceNull(listOrderDtl[i][j].categoryAbbrev) + endDiv;
				qty += startDiv + replaceNull(listOrderDtl[i][j].quantity) + endDiv;
				lastUpdated += startDiv + replaceNull(listOrderDtl[i][j].updatedDtLocal) + endDiv;
				updatedBy += startDiv + replaceNull(listOrderDtl[i][j].updatedUser) + endDiv;
				
				// Button status
				// If userAuthority  = 1
				if (userAuthority == "1"){
					var button = '<button class="btn-reject" disabled="disabled"> < </button>' +
								 '<button class="btn-approve"disabled="disabled"> > </button>';
				} else {
					var button = '<button class="btn-reject"> < </button><button class="btn-approve"> > </button>';
				}
				status += startDivStatus + button + endDiv;
				
				sankyu = startDivSankyu + replaceNull(listOrderDtl[i][j].sankyuRegistDt) + endDiv;
				sankyu += startDivSankyuNoBorderRight + replaceNull(listOrderDtl[i][j].sankyuOrderDt) + endDiv;
				
				sea = replaceNull(listOrderDtl[i][j].seaRequestDt);
				kddiSg = startDivSankyu + replaceNull(listOrderDtl[i][j].sankyuRegistDt) + endDiv;
				kddiSg += startDivSankyuNoBorderRight + replaceNull(listOrderDtl[i][j].sankyuOrderDt) + endDiv;

				kddiReceive = startDivKddi + replaceNull(listOrderDtl[i][j].kddiReceiveDt) + endDiv + '</div>';
				kddiDeliverQty += startDiv + replaceNull(listOrderDtl[i][j].kddiDeliverQty) + endDiv;
				kddiDeliver += startDiv + replaceNull(listOrderDtl[i][j].kddiDeliverDt) + endDiv;
				kddiLease = startDivKddiNoBorderRight + replaceNull(listOrderDtl[i][j].kddiLeaseDt) + endDiv + '</div>';
				
				deleteFg = replaceNull(listOrderDtl[i][j].deleteFg);
			}
			
			kddi = kddiReceive 
					+ '<div class="col-md-2 th-border-right th-border-left">' + kddiDeliverQty + '</div>'
					+ '<div class="col-md-4 th-border-right th-border-left">' + kddiDeliver + '</div>'
					+ kddiLease;
			dataRow.push(checkBox, orderId, deptCd, amtM, amtO, type, qty, lastUpdated, updatedBy, status, sankyu, sea, kddiSg, kddi, deleteFg);
			dataSet.push(dataRow);
		}
		table.rows.add(dataSet);
	}
	table.draw();
	// Get width table header
	var widthHeader = $("div.dataTables_scrollHeadInner table").width();
	// Fixed width header
	$("div.dataTables_scrollHeadInner table").css('cssText', 'width: ' + widthHeader + 'px !important');
}

/**
 * draw datatable
 * 
 * @returns
 */
function drawDataTableNew(listOrderDtl, userAuthority, page){
	var dataSet = [];
	var length = listOrderDtl.length;
	table.clear();
	if (length > 0) {
		for (var i = 0; i < length; i++) {
			var index = i + 1;
			var dataRow = [];
			var orderId;
			// Column in table
			var checkBox = '';
			var orderIdTd = '';
			var deptCd = '';
			var amtM = '';
			var amtO = '';
			var type = '';
			var qty = '';
			var lastUpdated = '';
			var updatedBy = '';
			var status = '';
			var sankyu = '';
			var sea = '';
			var kddiSg = '';
			var kddi = '';
			var kddiReceive = '';
			var kddiDeliverQty = '';
			var kddiDeliver = '';
			var kddiLease = '';
			var deleteFg = '';
			var quantity = 0;
			var deliverQty = 0;
			
			var startDivNoBorderTop = '<div class="row x4-data display-content-middle category ">';
			var startDivBorderTop = '<div class="row x4-data display-content-middle table-border-top category">';
			
			var startDivNoBorderTop1 = '<div class="row x4-data display-content-middle">';
			var startDivBorderTop1 = '<div class="row x4-data display-content-middle table-border-top">';

			var endDiv = '</div>';
			var startDivStatusNoBorderTop = '<div class="row x4-data display-content-middle  ">';
			var startDivStatusBorderTop = '<div class="row x4-data display-content-middle table-border-top">';

			var startDivSankyu = '<div class="col-md-6 th-border-right display-content-middle">';
			var startDivSankyuNoBorderRight = '<div class="col-md-6 th-border-right display-content-middle no-border-right">';
			
			var startDivKddi = '<div class="col-md-3 th-border-right display-content-middle">';
			var startDivKddiNoBorderRight = '<div class="col-md-3 th-border-right display-content-middle no-border-right">';
			
			var startDivRightNoBorderTop = '<div class="row x4-data display-content-middle div-align-right-flex">';
			var startDivRightBorderTop = '<div class="row x4-data display-content-middle div-align-right-flex table-border-top">';
			
			var divEllipsis = '<div class="text-ellipsis">';
			
			// Gerenate data
			for (var j=0; j<listOrderDtl[i].length; j++){
				quantity += listOrderDtl[i][j].quantity;
				deliverQty += listOrderDtl[i][j].kddiDeliverQty;
				
				orderId = replaceNull(listOrderDtl[i][j].orderId);
				var startDiv = startDivBorderTop;
				var startDiv1 = startDivBorderTop1;
				var startDivStatus = startDivStatusBorderTop;
				var startDivRight = startDivRightBorderTop;
				
				// If first row in td set border top
				if (j == 0){
					startDiv = startDivNoBorderTop;
					startDiv1 = startDivNoBorderTop1;
					startDivStatus = startDivStatusNoBorderTop;
					startDivRight = startDivRightNoBorderTop;
				}
				checkBox = '<input type="radio" class="no-margin-top radio-order-dtl" name="orderDtl" value="other" ' +
						   'onclick="selectedRecord('+ i + ', \'' + orderId +'\');"/>';
				orderIdTd = orderId;
				deptCd = replaceNull(listOrderDtl[i][j].deptCd);
				amtM = convertAmt(listOrderDtl[i][j].ttlMrc);
				amtO = convertAmt(listOrderDtl[i][j].ttlOtc);
				
				if (j == 0 || (j != 0 && listOrderDtl[i][j].categoryCd != listOrderDtl[i][j-1].categoryCd)){
					//orderIdTd += startDiv + replaceNull(listOrderDtl[i][j].orderId) + endDiv;
					//deptCd += startDiv + replaceNull(listOrderDtl[i][j].deptCd) + endDiv;
					//amtM += startDivRight + convertAmt(listOrderDtl[i][j].ttlMrc) + endDiv;
					//amtO += startDivRight + convertAmt(listOrderDtl[i][j].ttlOtc) + endDiv;
					type += startDiv + divEllipsis + replaceNull(listOrderDtl[i][j].categoryName) + endDiv + endDiv;
					qty += startDivRight + replaceNull(listOrderDtl[i][j].quantity) + endDiv;
					lastUpdated += startDiv1 + replaceNull(listOrderDtl[i][j].lastUpdated) + endDiv;
					updatedBy += startDiv1 + replaceNull(listOrderDtl[i][j].updatedByUser) + endDiv;
				}
				
				// Button status
				// If userAuthority  = 1
				if (userAuthority == "1"){
					status = '<div class="padding-div-status">'+ createButtonStatus(orderId, false, false) +'</div>';
				} else if (userAuthority == "2"){ // KDDI
					// If  [ORDER_DTL].SEA_REQUEST_DT <> ""  and  [ORDER_DTL].KDDI_ACCEPT_DT = ""
					// Enable Approve
					if (replaceNull(listOrderDtl[i][j].seaRequestDt) != "" 
							&& replaceNull(listOrderDtl[i][j].kddiAcceptDt) == ""){
						status = '<div class="padding-div-status">'+ createButtonStatus(orderId, false, true) +'</div>';
					} // If  [ORDER_DTL].KDDI_ACCEPT_DT <> ""  and  [ORDER_DTL].KDDI_ORDER_DT = "" , Enable Reject, Approve
					else if (replaceNull(listOrderDtl[i][j].kddiAcceptDt) != "" 
								&& replaceNull(listOrderDtl[i][j].kddiOrderDt) == ""){
						status = '<div class="padding-div-status">'+ createButtonStatus(orderId, true, true) +'</div>';
					} // If  [ORDER_DTL].KDDI_ORDER_DT <> ""  and  [ORDER_DTL].KDDI_RECEIVE_DT = "", EnableReject Approve
					else if (replaceNull(listOrderDtl[i][j].kddiOrderDt) != "" 
								&& replaceNull(listOrderDtl[i][j].kddiReceiveDt) == ""){
						status = '<div class="padding-div-status">'+ createButtonStatus(orderId, true, true) +'</div>';
					} // If  [ORDER_DTL].KDDI_RECEIVE_DT <> ""  and  [ORDER_DTL].KDDI_DELIVER_DT = "", Enable Reject
					else if (replaceNull(listOrderDtl[i][j].kddiReceiveDt) != "" 
								&& replaceNull(listOrderDtl[i][j].kddiDeliverDt) == ""
								&& replaceNull(listOrderDtl[i][j].kddiDeliverDtDoNo) == ""){
						status = '<div class="padding-div-status">'+ createButtonStatus(orderId, true, false) +'</div>';
					} // If  ([ORDER_DTL].ITEM_QTY = [ORDER_DTL].KDDI_DELIVER_QTY)  and  [ORDER_DTL].KDDI_LEASE_DT <> "", Enable Approve
					else if (replaceNull(listOrderDtl[i][j].kddiDeliverDt) != "" 
								&& replaceNull(listOrderDtl[i][j].kddiLeaseDt) == "" ){
						if ( j == (listOrderDtl[i].length - 1) && quantity == deliverQty ){
							// Disabled [Approve] by #144 on GoogleSheet
							status = '<div class="padding-div-status">'+ createButtonStatus(orderId, false, false) +'</div>';
						} else {
							status = '<div class="padding-div-status">'+ createButtonStatus(orderId, false, false) +'</div>';
						}
						
					} else {
						status = '<div class="padding-div-status">'+ createButtonStatus(orderId, false, false) +'</div>';
					}
				} else if (userAuthority == "3"){ // Sankyu
					// If  [ORDER].SANKYU_ORDER_DT <> ""  and  [ORDER].SEA_REQUEST_DT = ""
					// Enable Reject, Appro
					if (replaceNull(listOrderDtl[i][j].sankyuOrderDt) != "" && replaceNull(listOrderDtl[i][j].seaRequestDt) == ""){
						status = '<div class="padding-div-status">'+ createButtonStatus(orderId, true, true) +'</div>';
					} else {
						status = '<div class="padding-div-status">'+ createButtonStatus(orderId, false, false) +'</div>';
					}
				}
				
				sankyu = startDivSankyu + replaceNull(listOrderDtl[i][j].sankyuRegistDt) + endDiv;
				sankyu += startDivSankyuNoBorderRight + replaceNull(listOrderDtl[i][j].sankyuOrderDt) + endDiv;
				
				sea = replaceNull(listOrderDtl[i][j].seaRequestDt);
				kddiSg = startDivSankyu + replaceNull(listOrderDtl[i][j].kddiAcceptDt) + endDiv;
				kddiSg += startDivSankyuNoBorderRight + replaceNull(listOrderDtl[i][j].kddiOrderDt) + endDiv;

				kddiReceive = startDivKddi + replaceNull(listOrderDtl[i][j].kddiReceiveDt) + endDiv + '</div>';
				kddiDeliverQty += startDiv + replaceNull(listOrderDtl[i][j].kddiDeliverQtyDoNo) + endDiv;
				kddiDeliver += startDiv + replaceNull(listOrderDtl[i][j].kddiDeliverDtDoNo) + endDiv;
				kddiLease = startDivKddiNoBorderRight + replaceNull(listOrderDtl[i][j].kddiLeaseDt) + endDiv + '</div>';
				
				deleteFg = replaceNull(listOrderDtl[i][j].deleteFg);
				
				if ( j == (listOrderDtl[i].length - 1) ){
					quantity = 0;
					deliverQty = 0;
				}
			}
			
			kddi = kddiReceive 
					+ '<div class="col-md-2 th-border-right th-border-left">' + kddiDeliverQty + '</div>'
					+ '<div class="col-md-4 th-border-right th-border-left">' + kddiDeliver + '</div>'
					+ kddiLease;
			dataRow.push(checkBox, orderIdTd, deptCd, amtM, amtO, type, qty, lastUpdated, updatedBy, status, sankyu, sea, kddiSg, kddi, deleteFg);
			dataSet.push(dataRow);
		}
		table.rows.add(dataSet);
	}
	// Draw table
	table.draw();
	$('#resultTable').dataTable().fnPageChange(page);
	// Get width table header
	var widthHeader = $("div.dataTables_scrollHeadInner table").width();
	// Fixed width header
	$("div.dataTables_scrollHeadInner table").css('cssText', 'width: ' + widthHeader + 'px !important');
	
	setTabIndex();
}

/**
 * create button status Reject, Approve
 * 
 * @param isEnableReject
 * @param isEnableApprove
 * @returns
 */
function createButtonStatus(orderId, isEnableReject, isEnableApprove){
	var btnReject = "";
	var btnApprove = "";
	
	// button reject 
	if (isEnableReject){
		btnReject = '<button class="btn-reject" onclick="doReject(\''+ orderId +'\')"> < </button>';
	} else {
		btnReject = '<button class="btn-reject" disabled="disabled"> < </button>';
	}
	
	// button approve 
	if (isEnableApprove){
		btnApprove = '<button class="btn-approve" onclick="doApprove(\''+ orderId +'\')"> > </button>';
	} else {
		btnApprove = '<button class="btn-approve" disabled="disabled"> > </button>';
	}
	
	return btnReject + btnApprove;
}

/**
 * action click button search
 * search and draw data in table
 * 
 * @returns
 */
function doSearch(){
	if (validate()){
		descendingOrder = false;
		// add event doClear for button [Clear]
		$(".btn-clear").attr("onclick", "doClear();");
		// parameter call ajax
		var params = {};
		var companyCd = $("#companyCd").val().trim();
		var entryDateFrom = $("#entryDateFrom").val().trim();
		var entryDateTo = $("#entryDateTo").val().trim();
		var nonDelivery = $("#nonDelivery").is(":checked");
		var expiringOrders = $("#expiringOrders").is(":checked");
		
		// Set params
		params.companyCd = companyCd;
		params.entryDateFrom = entryDateFrom;
		params.entryDateTo = entryDateTo;
		params.nonDelivery = nonDelivery;
		params.expiringOrders = expiringOrders;
		paramDownloadReport = params;
		// Call ajax load data
		callAjax(params, "sac0021_01", "POST", 0, true);
	}
}

/**
 * action click button clear
 * clear search fillter
 * search and draw data in table
 * 
 * @returns
 */
function doClear(){
	showConfirmDialog("clear","","",{"position":"200"});
}

/**
 * action click button delete
 * @returns
 */
function doCancel(orderId){
	selectedOrderId = orderId;
	showConfirmDialog("cancel","","",{"position":"200"});
}

/**
 * action click button Edit
 * @returns
 */
function doEdit(orderId){
	selectedOrderId = orderId;
	showConfirmDialog("edit","","",{"position":"200"});
}

/**
 * action click button download
 * @returns
 */
function doDownload(){
	//selectedOrderId = orderId;
	showConfirmDialog("download","","",{"position":"200"});
}

/**
 * action click button reject
 * @returns
 */
function doReject(orderId){
	selectedOrderId = orderId;
	showConfirmDialog("reject","","",{"position":"200"});
}

/**
 * action click button approve
 * @returns
 */
function doApprove(orderId){
	selectedOrderId = orderId;
	showConfirmDialog("approve","","",{"position":"200"});
}


/**
 * validate when click search
 * @returns
 */
function validate(){
	// Remove error
	removeError();
	var flag = true;
	var companyCd = $("#companyCd").val().trim();
	var entryDateFrom = $("#entryDateFrom").val().trim();
	var entryDateTo = $("#entryDateTo").val().trim();
	
	// Check company code
	/*if(companyCd == ""){
		flag = false;
		$("#companyError").append(generateHtmlError("OP-0001", ""));
	}*/
	
	var flagDate = true;
	// Check entry date from
	if (entryDateFrom != ""){
		if (!checkShortDate(entryDateFrom)){
			flag = false;
			flagDate = false;
			$("#fromError").append(generateHtmlError("OP-0002", ""));
		}
	}
	
	// Check entry date to
	if (entryDateTo != ""){
		if (!checkShortDate(entryDateTo)){
			flag = false;
			flagDate = false;
			$("#toError").append(generateHtmlError("OP-0002", ""));
		}
	}
	
	// Check flag
	if (!flagDate){
		showError();
		return flagDate;
	}
	
	// Check dateFrom is not empty, dateTo is empty
	if (entryDateFrom != "" && entryDateTo == ""){
		flag = false;
		$("#toError").append(generateHtmlError("OP-0002", ""));
	}
	
	// Check dateFrom is empty, dateTo is not empty
	if (entryDateFrom == "" && entryDateTo != ""){
		flag = false;
		$("#fromError").append(generateHtmlError("OP-0002", ""));
	}
	
	// Check entry date from, to is not empty
	if (entryDateFrom != "" && entryDateTo != ""){
		try{
		milisecEntryDateFrom = $.datepicker.parseDate('dd-M-y', entryDateFrom);
		milisecEntryDateTo = $.datepicker.parseDate('dd-M-y', entryDateTo);
		if (milisecEntryDateFrom > milisecEntryDateTo){
			flag = false;
			$("#toError").append(generateHtmlError("OP-0002", ""));
		}
		} catch (e){
			flag = false;
			$("#toError").append(generateHtmlError("OP-0002", ""));
		}
	}
	
	// Check flag
	if (!flag){
		showError();
	}
	return flag;
}

/**
 * show error when validate
 * @returns
 */
function showError(){
	// Show tr error
	$("#error-sac0021").show();
	// Get td company code, entry date
	var tdField = $("#itemData tbody tr:first-child td");
	// Get td error
	var tdError = $("#itemData tbody tr:last-child td");
	// Set width company code error
	tdError.eq(1).outerWidth(tdField.eq(1).outerWidth() + tdField.eq(2).outerWidth());
	// Set width entry date code error	
	tdError.eq(3).outerWidth(tdField.eq(4).outerWidth() + tdField.eq(5).outerWidth());
}

/**
 * remove error
 * @returns
 */
function removeError(){
	$("#error-sac0021").hide();
	$("#companyError").empty();
	$("#fromError").empty();
	$("#toError").empty();
}

/**
 * Method will call when Click button yes on dialog confirm
 * @param idEvent Event Save/Delete
 * @param element Dialog confirm
 */
function doEventDialog01(idEvent, element){
	// Page current datatable
	var page = table.page.info().page;
	
	// In case edit
	if (idEvent == "edit"){
		window.location.href = "sac0021_06?orderId=" + selectedOrderId;
		selectedOrderId = "";
	}
	
	// In case clear
	if (idEvent == "clear"){
		descendingOrder = false;
		// Clear search filters
		$("#entryDateFrom").val("");
		$("#entryDateTo").val("");
		$("#nonDelivery").prop("checked", false);
		$("#expiringOrders").prop("checked", false);
		
		var params = {};
		var companyCd = $("#companyCd").val().trim();
		
		// Set params
		params.companyCd = companyCd;
		params.entryDateFrom = "";
		params.entryDateTo = "";
		params.nonDelivery = false;
		params.expiringOrders = false;
		paramDownloadReport = params;
		// Call ajax load data
		callAjax(params, "sac0021_01", "POST", 0, false);
	}
	
	// In download
	if (idEvent == "download"){
		// parameter call ajax
		var companyCd = $("#companyCd").val().trim();
		var entryDateFrom = $("#entryDateFrom").val().trim();
		var entryDateTo = $("#entryDateTo").val().trim();
		var nonDelivery = $("#nonDelivery").is(":checked");
		var expiringOrders = $("#expiringOrders").is(":checked");
		
		// Download
		if (idEvent == "download"){
			var descOrder = false;
			if (paramDownloadReport.descendingOrder){
				descOrder = paramDownloadReport.descendingOrder;
			}
			// Call ajax and load data
			window.location.href = "sac0021_05?companyCd=" + paramDownloadReport.companyCd 
									+ "&entryDateFrom=" + paramDownloadReport.entryDateFrom 
									+ "&entryDateTo=" + paramDownloadReport.entryDateTo 
									+ "&nonDelivery=" + paramDownloadReport.nonDelivery 
									+ "&expiringOrders=" + paramDownloadReport.expiringOrders
									+ "&descendingOrder=" + descOrder;
		}
	}
	
	// In case delete or reject or approve
	if (idEvent == "cancel" || idEvent == "reject" || idEvent == "approve"){
		// check orderId is null, is empty
		if (selectedOrderId == null || selectedOrderId == undefined || selectedOrderId == ""){console.log("123");
			return;
		}
		// parameter call ajax
		var params = {};
		var companyCd = $("#companyCd").val().trim();
		var entryDateFrom = $("#entryDateFrom").val().trim();
		var entryDateTo = $("#entryDateTo").val().trim();
		var nonDelivery = $("#nonDelivery").is(":checked");
		var expiringOrders = $("#expiringOrders").is(":checked");
		
		// Set params
		params.companyCd = companyCd;
		params.entryDateFrom = entryDateFrom;
		params.entryDateTo = entryDateTo;
		params.nonDelivery = nonDelivery;
		params.expiringOrders = expiringOrders;
		params.orderId = selectedOrderId;
		params.descendingOrder = descendingOrder;
		
		// Delete order
		if (idEvent == "cancel"){
			// Call ajax and load data
			callAjax(params, "sac0021_02", "POST", page, false);
		}
		
		// Reject order
		if (idEvent == "reject"){
			// Call ajax and load data
			callAjax(params, "sac0021_03", "POST", page, false);
		}
		
		// Approve order
		if (idEvent == "approve"){
			// Call ajax and load data
			callAjax(params, "sac0021_04", "POST", page, false);
		}
		
		selectedOrderId = "";
	}
	
	// In case close
	if (idEvent == "close"){
		paramForRedirectMenu = ["faa001", "saa0012"];
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
	if (idEvent == "redirect") {
		messageId = "CA-0013";
	} else if (idEvent == "redirectMenu") {
		messageId = "CA-0002";
	} else if (idEvent == "cancel") {
		messageId = "OP-0003";
	} else if (idEvent == "edit") {
		messageId = "OP-0004";
	} else if (idEvent == "download") {
		messageId = "OP-0005";
	} else if (idEvent == "close") {
		messageId = "OP-0006";
	} else if (idEvent == "reject") {
		messageId = "OP-0007";
	} else if (idEvent == "approve") {
		messageId = "OP-0008";
	} else if (idEvent == "logout") {
		messageId = "CA-0001";
		var contentConfirmLogout = getMessageWithParams(messageId,"");
		document.getElementById("contentConfirmLogout").innerHTML = contentConfirmLogout;
		$('#buttonYesLogout').attr('onclick', "doYes('"+idEvent+"')");
		$('#buttonYesLogout').parent().addClass("padding-btn-modal");
		$('#buttonNoLogout').parent().addClass("padding-btn-modal");
		$("#modalConfirmLogout").modal("show");
		return;
	} else if (idEvent == "clear") {
		messageId = "OP-0009";
	}
	
	var contentConfirm = getMessageWithParams(messageId,"");
	document.getElementById("contentConfirm").innerHTML = /*messageId+" : "+*/ contentConfirm;
	if (idEvent == "close"){
		$("#contentConfirm").parent().parent().css("text-align", "center");
		//$("#contentConfirm").parent().parent().css("padding", "0 5px");
	}
	$('#buttonYes').attr('onclick', "doYes('"+idEvent+"')");
	$('#buttonYes').parent().addClass("padding-btn-modal");
	$('#buttonNo').parent().addClass("padding-btn-modal");
	$("#modalConfirm").modal("show");
}

/**
 * set tab index
 * 
 * @returns
 */
function setTabIndex(){
	$("input, select, a").attr('tabindex','-1');
	$("#companyCd").attr("tabindex", 1);
	$("#entryDateFrom").attr("tabindex", 2);
	$("#entryDateTo").attr("tabindex", 3);
	$("#nonDelivery").attr("tabindex", 4);
	$("#expiringOrders").attr("tabindex", 5);
	$("#cancel").attr("tabindex", 6);
	$("#goToKDDI").attr("tabindex", 7);
	$("#goToLog").attr("tabindex", 8);
	$(".btn-edit").attr("tabindex", 9);
	$(".btn-download").attr("tabindex", 10);
	$(".btn-clear").attr("tabindex", 11);
	$(".btn-search ").attr("tabindex", 12);
	$(".btn-close").attr("tabindex", 13);
	$('div button.btn-reject:enabled').eq(0).attr("tabindex", 14);
	$('div button.btn-approve:enabled').eq(0).attr("tabindex", 15);
	$(".btn-logout").attr("tabindex", 16);
	
	$("#companyCd").focus();
	$(".user-login-css, #resultTable").keydown(function(e){
		if($('.btn-logout').is(":focus") && (e.which || e.keyCode) == 9 && !event.shiftKey) {
			e.preventDefault();
			$('#companyCd').first().focus();
			return;
		}
	});
}

/**
 * 
 * @param currVal
 *            Date value dd/mm/yyyy
 * @returns return true if true format otherwise return false
 * 
 */
function checkShortDate(currVal) {
    //if (currVal == '') return false;

	// Declare Regex
	var rxDatePattern = /^(\d{1,2})(\/|-)([a-zA-Z]{3})(\/|-)(\d{2})$/;

	var dtArray = currVal.match(rxDatePattern); // is format OK?

	if (dtArray == null)
		return false;

	var dtDay = parseInt(dtArray[1]);
	var dtMonth = dtArray[3];
	var dtYear = parseInt(dtArray[5]);

	// need to change to lowerCase because switch is
	// case sensitive
	switch (dtMonth.toLowerCase()) {
	case 'jan':
		dtMonth = '01';
		break;
	case 'feb':
		dtMonth = '02';
		break;
	case 'mar':
		dtMonth = '03';
		break;
	case 'apr':
		dtMonth = '04';
		break;
	case 'may':
		dtMonth = '05';
		break;
	case 'jun':
		dtMonth = '06';
		break;
	case 'jul':
		dtMonth = '07';
		break;
	case 'aug':
		dtMonth = '08';
		break;
	case 'sep':
		dtMonth = '09';
		break;
	case 'oct':
		dtMonth = '10';
		break;
	case 'nov':
		dtMonth = '11';
		break;
	case 'dec':
		dtMonth = '12';
		break;
	}

	// convert date to number
	dtMonth = parseInt(dtMonth);

	if (isNaN(dtMonth))
		return false;
	else if (dtMonth < 1 || dtMonth > 12)
		return false;
	else if (dtDay < 1 || dtDay > 31)
		return false;
	else if ((dtMonth == 4 || dtMonth == 6 || dtMonth == 9 || dtMonth == 11)
			&& dtDay == 31)
		return false;
	else if (dtMonth == 2) {
		var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
		if (dtDay > 29 || (dtDay == 29 && !isleap))
			return false;
	}

	return true;
}

/**
 * call ajax when click search, clear
 * @param params
 * @param actionName
 * @param methodType
 * @returns
 */
function callAjax(params, actionName, methodType, page, showExpiringOrders) {
	// get user authority
	var userAuthority = $("#authority").val().trim();
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
            // Disabeld all button
            disabledButton();
            // Show table view
            if ($("#table-view").css("display") == "none"){
            	$("#table-view").show();
            }
            console.log(response);
            // Draw datatable
            if (response.listGroupOrderDtl != null && response.listGroupOrderDtl != undefined){
            	listData = response.listGroupOrderDtl;
            	drawDataTableNew(response.listGroupOrderDtl, response.userAuthority, page);
            	addButtonShowHideColumn();
            	// If the search has results enabled button download
            	// End userAuthority = 2 or 3
            	if (listData.length > 0 && (userAuthority === "2" || userAuthority === "3")){
            		// enabled button download
            		$(".btn-download").attr("disabled",false);
            		$(".btn-download").attr("onclick", "doDownload();");
            	} else {
            		// disabled button download
            		$(".btn-download").attr("disabled",true);
            		$(".btn-download").removeAttr("onclick");
            	}
            } else {
            	table.clear().draw();
            	addButtonShowHideColumn();
            }
            // Show expiring orders
            if (showExpiringOrders){
            	showConfirmExpiringOrders(response.listOrderIdExpiring);
            }
        },
        error:function(jqXhr, textStatus, errorThrown){
            alert(textStatus);
            close_loading_ajax();
        }
    });
}

/**
 * event selected record
 * 
 * @param orderId
 * @returns
 */
function selectedRecord(rowId, orderId){
	// Set disabled all button
	$("#cancel").attr("disabled",true);
	$("#cancel").removeAttr("onclick");
	$("#goToKDDI").attr("disabled",true);
	$("#goToKDDI").unbind("click");
	$("#goToLog").attr("disabled",true);
	$("#goToLog").unbind("click");
	$(".btn-edit").attr("disabled",true);
	$(".btn-edit").removeAttr("onclick");
	//$(".btn-download").attr("disabled",true);
	//$(".btn-download").removeAttr("onclick");
	
	var authority = $("#authority").val().trim();
	var rowData = table.rows(rowId).data()[0];
	var kddiReciveDt = $($(rowData[13])[0]).text().trim();
	
	// authority = 3 sankyu
	if (authority == "3"){
		// Check kddiReciveDt = "" 
		if (kddiReciveDt == ""){
			// enabled button edit
			$(".btn-edit").attr("disabled", false);
			$(".btn-edit").attr("onclick", "doEdit('"+ orderId +"');");
		}
		// Process button cancal
		// Number DO in ORDER_DO table has DO_SIGNBACK_FG = 1
		var countDoSignback = listData[rowId][0].countDoSignback;
		// If countDoSignback > 0 then enable button cancel
		if (countDoSignback != undefined && countDoSignback != null && countDoSignback === 0) {
			// enabled button cancel
			$("#cancel").attr("disabled", false);
			$("#cancel").attr("onclick", "doCancel('"+ orderId +"');");
		}
		
		// enabled button download
		//$(".btn-download").attr("disabled",false);
		//$(".btn-download").attr("onclick", "doDownload('"+ orderId +"');");
		
		// SANKYU Admin can access to KDDI Lease Information Entry screen as read-only.
		// enable button KDDI 
		// Check kddiReciveDt = "" 
		if (kddiReciveDt != undefined && kddiReciveDt != null && kddiReciveDt != ""){
			$("#goToKDDI").attr("disabled",false);
			$("#goToKDDI").click(function() {
				window.location.href = contextPath+"fac001/sac0022.action?orderId=" + orderId;
			});
		}
	}
	
	// authority = 2 kddi
	if (authority == "2"){
		/*if (kddiReciveDt == ""){
			// enabled button
			$("#cancel").attr("disabled",false);
			$("#cancel").attr("onclick", "doCancel('"+ orderId +"');");
		}*/
		// enabled button
		//$(".btn-download").attr("disabled",false);
		//$(".btn-download").attr("onclick", "doDownload('"+ orderId +"');");
		// Enabled button [KDDI]
		// Check kddiReciveDt = "" 
		if (kddiReciveDt != undefined && kddiReciveDt != null && kddiReciveDt != ""){
			$("#goToKDDI").attr("disabled",false);
			$("#goToKDDI").click(function() {
				window.location.href = contextPath+"fac001/sac0022.action?orderId=" + orderId;
			});
		}
		// Enabled button [Log]
		$("#goToLog").attr("disabled",false);
		$("#goToLog").click(function() {
			window.location.href = contextPath+"fac001/sac0023.action?orderId=" + orderId;
		});
	}
	
	// authority = 1 USER
	if (authority == "1"){
		var userId = $("#userId").val().trim();
		var userCreateOrder = listData[rowId][0].userCreateOrder;
		// Check kddiReciveDt = "" 
		if (kddiReciveDt == ""){
			// If userId == userCreateOrder
			if (userId === userCreateOrder && orderId === listData[rowId][0].orderId){
				$(".btn-edit").attr("disabled", false);
				$(".btn-edit").attr("onclick", "doEdit('"+ orderId +"');");
			}
		}
	}
	
}

/**
 * close modal expiring orders
 * @returns
 */
function doCloseConfirmExpiringOrders(){
	$("#modalConfirmExpiringOrders").modal("hide");
	setTabIndex();
}

/**
 * show modal expiring orders
 * @param listOrderIdExpiring
 * @returns
 */
function showConfirmExpiringOrders1(listOrderIdExpiring){
	if (listOrderIdExpiring != null && listOrderIdExpiring != undefined && listOrderIdExpiring != ""){
		if (listOrderIdExpiring.length > 0){
			var contentConfirm = getMessageWithParams("OP-0010","");
			contentConfirm += " ";
			for (var i=0; i<listOrderIdExpiring.length; i++){
				if (i == (listOrderIdExpiring.length-1) ){
					contentConfirm += listOrderIdExpiring[i];
					break;
				}
				contentConfirm += listOrderIdExpiring[i] + ", ";
			}
			document.getElementById("contentConfirmExpiringOrders").innerHTML = contentConfirm;
			$("#modalConfirmExpiringOrders").modal("show");
		}
	}
}

/**
 * show modal expiring orders
 * @param listOrderIdExpiring
 * @returns
 */
function showConfirmExpiringOrders(listOrderIdExpiring){
	// Check listOrderIdExpiring.length > 0 
	if (listOrderIdExpiring != null && listOrderIdExpiring != undefined && listOrderIdExpiring != ""){
		if (listOrderIdExpiring.length > 0){
			var contentConfirm = getMessageWithParams("OP-0010","");
			var contentOrderId = "";
			for (var i=0; i<listOrderIdExpiring.length; i++){
				contentOrderId += '<div class="text-center">';
				for (var j=0; j<5 && (i+j)<listOrderIdExpiring.length; j++){
					contentOrderId += '<span class="span-order-id">'+ listOrderIdExpiring[i+j] +'</span>';
				}
				i += 4;
				contentOrderId += '</div>';
			}
			document.getElementById("contentConfirmExpiringOrders").innerHTML = contentConfirm;
			$("#contentConfirmExpiringOrders").parent().parent().find("div").remove();
			$("#contentConfirmExpiringOrders").parent().parent().append(contentOrderId);
			$("#modalConfirmExpiringOrders").modal("show");
		}
	}
}

/**
 * disabled all button
 * 
 * @returns
 */
function disabledButton(){
	$("#cancel").attr("disabled",true);
	$("#cancel").removeAttr("onclick");
	$("#goToKDDI").attr("disabled",true);
	$("#goToKDDI").unbind("click");
	$("#goToLog").attr("disabled",true);
	$("#goToLog").unbind("click");
	$(".btn-edit").attr("disabled",true);
	$(".btn-edit").removeAttr("onclick");
	$(".btn-download").attr("disabled",true);
	$(".btn-download").removeAttr("onclick");
}

/**
 * Add button show hide column in table
 * 
 * @returns
 */
function addButtonShowHideColumn(){
	// Tag input toggle
	var inputToggle = '<input type="checkbox" id="showHide" data-toggle="toggle" '
					+ 'data-on="Show" data-off="Hide" data-size="small" data-width="80" data-offstyle="danger">';
	// Div table length
	var divTableLength = $("#resultTable_length");
	// Div parent for div table length
	var divParentTableLength = divTableLength.parent().parent();
	// Remove all children
	divParentTableLength.children().eq(0).empty();
	// Insert tag input to div
	divParentTableLength.children().eq(0).append(inputToggle);
	// Add toggle
	$('#showHide').bootstrapToggle();
	$('#showHide').next().find("label").addClass("display-content-middle");
	$('#showHide').next().find("label").eq(0).addClass("label-show-hide-left");
	$('#showHide').next().find("label").eq(1).addClass("label-show-hide-right");
	$('#showHide').next().find("span").addClass("span-show-hide");
	// Add event chang to #showHide
	$("#showHide").change(function(event) {
		event.preventDefault();
 
        // Get the column API object
        var column;

        for (var i=2; i<=8; i++){
        	// Get the column API object
        	column = table.column(i);
        	// Toggle the visibility
        	column.visible( ! column.visible(), false );
        }
        // Redraw table
        table.columns.adjust().draw( false );
	});
}