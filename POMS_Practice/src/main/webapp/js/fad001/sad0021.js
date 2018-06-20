/**
 * JS file sad0021 (PC Stock Management)
 */
var paramList = [];
var resultTable;
var itemObject = {};
var pad0021List = {};
var enterScreen = false;
var isOnChangeScreen = false;
var tableWidthGlobal = 0;
// Position to update Country data
var positionUpdate = -1;
var isSwitched = false;
var errorFg = null;
// Postion of qty in stock
var postionQty = 4;
var isSelectedCountry = false;
var addFlag = false;
var addButtonFlag = false;
var spanFlag = true;

$(document).ready(function() {
	disableButtonDelete();
	contextPath = $('#contextPath').val();
	sad0021("", contextPath + 'fad001/loadDataAction', 'GET');
	// If the screen is locked by another user
//	if (!enterScreen) {
//		showConfirmDialog("redirect","","",{"position":"200"});
//	}
	//else {
		loadData();
		enableElement();
	//}
		
	enableCountry();
	
	$(".entryDateFrom").change(function () {
		var value = $(this).val().trim();
		$(this).next().val(value);
		var entryDate;
		
		if(!(typeof value === "undefined" || value === null || value === "null" || value === "")){
			var dateArray = value.split("-");
			var entryDate = replaceNull(dateArray[1] + '-' + dateArray[2]);
			$(this).val(entryDate);
		}
	 });
	
	$('button.btn-add').on('click', function(){
		addFlag = true;
	})
	
	disabledAddButton(addButtonFlag);
	
	$('.combobox-dropdown span').not('.display-disabled').click(function(){
		$('.combobox-dropdown span').prop('disabled', false);
	})
	$('.combobox-dropdown span.display-disabled').click(function(){
		$('.combobox-dropdown span.display-disabled').prop('disabled', true).prop("onclick", null);
	})
});

function doUpdateCountryData(object) {
	if(addFlag == true){
		return;
	}
	
	$('.btn-add').attr("disabled", "disabled").on('click');
	var countryData = {};
	countryData.previousCountry = null;

	for (var i = 0; i <pad0021List.length; i++){
		if(pad0021List[i].countryCd == $(object).find('a').text()){
			countryData.exclusiveFg = pad0021List[i].exclusiveFg;
			break;
		}
	}
	if ($('.dataTables_scrollHead table th.data-table-header').not('.display-disabled').length == 1
			&& isSwitched == false
			&& errorFg != "switch"){
		countryData.previousCountry = $('.dataTables_scrollHead table th.data-table-header').not('.display-disabled').find('a').text();
	}

	countryData.countryCd = $(object).find('a').text();
	countryData.updatedUser = userInfo.userId;
	if (countryData.countryCd != countryData.previousCountry) {
		callAjaxUpdateCountryData(countryData, i, 'doUpdateCountryDataAction','POST');
	}
}

function getDatePicker(position) {
	var item = $('.dataTables_scrollHeadInner > table').dataTable().find('th').eq(3+position).find('input.entryDateFull');
	
	var dates = [];
	for (var i = 0; i < item.length; i++){
		dates.push($(item).eq(i).val());
	}
	
	return dates;
}

/**
 * Display data
 */
function loadData() {
	$('#confirmRedirectMenu').val("true");
	$(".left-side").hide();
	createTable();//initDatatable();
	drawTbody();
	$( window ).resize(function() {
		configFixedColumn();
	});
	fixHeightTable();
	configFixedColumn();
	tabIndex();
	resultTable.on( 'draw', function () {
		tabIndex();
	});
	checkboxChange();
}

/**
 * Disable delete button
 */
function disableButtonDelete() {
	$(".btn-delete").css('background-color','');
    $(".btn-delete").attr("disabled", true);
}

/**
 * Disable element of form$(".btn-delete").attr("disabled", false);
 */
function disableElement() {
	$(".btn-save").css('background-color','');
    $(".btn-save").attr("disabled", true);
    
    $("#resultTable_wrapper").find("input, span").prop("disabled", true);
}

/**
 * Enable element of form
 */
function enableElement() {
	$(".btn-save").attr("disabled", false);
    
    $("#resultTable_wrapper").find("input, span").prop("disabled", false);
    
    $('.dataTables_scrollHead table th.data-table-header').addClass('display-disabled');
	$('.dataTables_scrollBody table div.div-loi-qty-left').addClass('display-disabled');
	$('.combobox-dropdown > *').addClass('display-disabled');
	
	$('.dataTables_scrollHead table th.data-table-header.display-disabled').find('input').attr("disabled", true);
	$('.dataTables_scrollHead table th.data-table-header:not(.display-disabled)').find('input').attr("disabled", false);
	$('.dataTables_scrollBody table div.div-loi-qty-left.display-disabled').find('input').attr("disabled", true);
	$('.dataTables_scrollBody table div.div-loi-qty-left:not(.display-disabled').find('input').attr("disabled", false);
	
	$('.dataTables_scrollHead table th.data-table-header').on('click change', function () {
		isSelectedCountry = true;
		$('.dataTables_scrollHead table th.data-table-header.display-disabled').find('input').attr("disabled", true);
		$('.dataTables_scrollHead table th.data-table-header:not(.display-disabled)').find('input').attr("disabled", false);
	})
	$('.dataTables_scrollBody table div.div-loi-qty-left').on('click change', function () {
		$('.dataTables_scrollBody table div.div-loi-qty-left.display-disabled').find('input').attr("disabled", true);
		$('.dataTables_scrollBody table div.div-loi-qty-left:not(.display-disabled').find('input').attr("disabled", false);
	});
}


/**
 * Even change in check box
 */
function checkboxChange() {
	$('input:checkbox').change(function () {
		// Get index check box
		var rows = $("#resultTable").dataTable().fnGetNodes();
		// Count element of check box
		var lenghOriginal = $("#resultTable").dataTable().fnGetNodes().length;
		// Get index of check box is checked
		var indexClone = $('input:checkbox').index(this);
		// Get index original of check box
		var indexOriginal = indexClone - lenghOriginal;
		// Get element is type check box
		var checkBox = $(rows[indexOriginal]).find("input:checkbox");
		// Set check (true/false) for original
		checkBox.prop('checked',$(this).prop( "checked" ));
		// Disable (true/false) of button delete
	    if ($("input:checked").length > 0) {
	        $(".btn-delete").attr("disabled", false);
	    }
	    else {
	    	$(".btn-delete").css('background-color','');
	        $(".btn-delete").attr("disabled", true);
	    }
	 });
}

/**
 * Call when first load form
 * 
 * @param params
 *            data
 * @param actionName
 *            action name
 * @param methodType
 *            type
 */
function sad0021(params, actionName, methodType) {
	loading_ajax();
	$.ajax({
		cache: false,
		url : actionName,
		type : methodType,
		data : params,
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		async : false,
		success : function(response) {
			close_loading_ajax();
			itemObject = response.itemNameCodeList;
			pad0021List = response.pad0021List;
			enterScreen = response.enterScreen;
			addButtonFlag = response.addFlag;
			lockedcountries = response.lockedcountries;
		},
		error : function(jqXhr, textStatus, errorThrown) {
			close_loading_ajax();
		}
	});
}

/**
 * draw table
 * 
 * @returns
 */
function createTable(){
	var widthMain = $("#view-main").width();
	var widthNo = 0.03;
	var widthCategory = 0.10;
	var widthItem = 0.12;
	var widthCountry = 0.2;
	var widthTable = widthNo + widthCategory + widthItem + widthCountry*countryObject.length;
	tableWidthGlobal = widthTable;
	$("#resultTable").css("width", parseInt(widthTable*100) + "%");
	widthNo = parseInt((widthNo/widthTable)*100);
	widthCategory = parseInt((widthCategory/widthTable)*100);
	widthItem = parseInt((widthItem/widthTable)*100);
	//widthCountry = (100 - widthNo - widthCategory - widthItem)/countryObject.length; // parseInt((widthMain*widthCountry/widthTable)*100);
	widthCountry = parseInt((widthCountry/widthTable)*100);
	var thead = $("#resultTable thead");
	var tr = $("<tr></tr>");
	tr.append('<th width="'+ widthNo +'%" rowspan="2">#</th>');
	tr.append('<th width="'+ widthCategory +'%" rowspan="2">Category</th>');
	tr.append('<th width="'+ widthItem +'%" rowspan="2">Item Name</th>');
	for (var i=0; i<countryObject.length; i++){
		tr.append('<th width="'+ widthCountry +'%">'+ countryObject[i].countryCd +'</th>');
	}
	var tr2 = $("<tr></tr>");
	tr2.append('<th width="'+ widthNo +'%">#</th>');
	tr2.append('<th width="'+ widthCategory +'%">Category</th>');
	tr2.append('<th width="'+ widthItem +'%">Item Name</th>');
	for (var j=0; j<countryObject.length; j++){
		//tr2.append('<th width="'+ widthCountry +'%">'+ countryObject[j].countryCd +'</th>');
		var thCountry = $('<th class="data-table-header" id="data-table-header"></th>');
		var countryTop = '<div class="row div-no-margin display-content-middle div-country" onclick="doUpdateCountryData(this);"><div class="temp"><a href="#" style="text-decoration: underline;">'+ countryObject[j].countryCd +'</a>';
//		var divCountryTop = $(countryTop);
		var lockedCountry = "";
		if(lockedcountries != null && lockedcountries != undefined) {
			for(var t = 0; t < lockedcountries.length; t++) {
				if(countryObject[j].countryCd == lockedcountries[t].countryCd) {
					lockedCountry = '<div class="lock-country">Currently locked by &lt;'+ lockedcountries[t].lockedUserName +'&gt;</div></div></div>';
					break;
				}
			}
		}
		
		var divCountryTop = $(countryTop + lockedCountry);
		var divCountryBottom = $('<div class="row div-no-margin"></div>');
		var divQty = $('<div class="div-float-left div-phy display-content-middle div-qty">Stock</div>'
					 + '<div class="div-float-left div-loi display-content-middle div-qty">L1</div>'
					 + '<div class="div-float-left div-loi display-content-middle div-qty">L2</div>'
					 + '<div class="div-float-left div-loi display-content-middle div-qty">L3</div>'
					 + '<div class="div-float-left div-loi display-content-middle div-qty">Order</div>');
		var divCountryDate = $('<div class="row div-no-margin"></div>');
		var dateLoi1Value = "",
			dateLoi1Full = "",
			dateLoi2Value = "",
			dateLoi2Full = "",
			dateLoi3Value = "",
			dateLoi3Full = "",
			dateLoi4Value = "",
			dateLoi4Full = "";
		if (pad0021List && pad0021List.length && j < pad0021List.length) {
			if (typeof pad0021List[j].qtyLoi1 === "string") {
				dateLoi1Full = pad0021List[j].qtyLoi1.substring(0, 11);
				dateLoi1Value = pad0021List[j].qtyLoi1.substring(5, 11);
			}
			if (typeof pad0021List[j].qtyLoi2 === "string") {
				dateLoi2Full = pad0021List[j].qtyLoi2.substring(0, 11);
				dateLoi2Value = pad0021List[j].qtyLoi2.substring(5, 11);
			}
			if (typeof pad0021List[j].qtyLoi3 === "string") {
				dateLoi3Full = pad0021List[j].qtyLoi3.substring(0, 11);
				dateLoi3Value = pad0021List[j].qtyLoi3.substring(5, 11);
			}
			if (typeof pad0021List[j].qtyLoi4 === "string") {
				dateLoi4Full = pad0021List[j].qtyLoi4.substring(0, 11);
				dateLoi4Value = pad0021List[j].qtyLoi4.substring(5, 11);
			}
		}
		var qtyLoi1 = '<div class="input-group input-group-from" style="display: flex;position: sticky;">'
			+ '<input type="text" class="form-control datepicker ipt-entry-date text-center input-loi background-loi1 entryDateFrom" onclick="openDatePicker1(this)" readonly="true" placeholder="" value="'+ dateLoi1Value +'" maxlength="10" tabindex="2" />'
			+ '<input type="hidden" class="entryDateFull" value="'+ dateLoi1Full +'" />'
			+'</div>';
		var qtyLoi2 = '<div class="input-group input-group-from" style="display: flex;position: sticky;">'
			+ '<input type="text" class="form-control datepicker ipt-entry-date text-center input-loi background-loi1 entryDateFrom" onclick="openDatePicker1(this)" readonly="true" placeholder="" value="'+ dateLoi2Value +'" maxlength="10" tabindex="2" />'
			+ '<input type="hidden" class="entryDateFull" value="'+ dateLoi2Full +'" />'
			+'</div>';
		var qtyLoi3 = '<div class="input-group input-group-from" style="display: flex;position: sticky;">'
			+ '<input type="text" class="form-control datepicker ipt-entry-date text-center input-loi background-loi1 entryDateFrom" onclick="openDatePicker1(this)" readonly="true" placeholder="" value="'+ dateLoi3Value +'" maxlength="10" tabindex="2" />'
			+ '<input type="hidden" class="entryDateFull" value="'+ dateLoi3Full +'" />'
			+'</div>';
		var qtyLoi4 = '<div class="input-group input-group-from" style="display: flex;position: sticky;">'
			+ '<input type="text" class="form-control datepicker ipt-entry-date text-center input-loi background-loi1 entryDateFrom" onclick="openDatePicker1(this)" readonly="true" placeholder="" value="'+ dateLoi4Value +'" maxlength="10" tabindex="2" />'
			+ '<input type="hidden" class="entryDateFull" value="'+ dateLoi4Full +'" />'
			+'</div>';
		var divDate = $('<div class="div-float-left div-phy display-content-middle div-date display-content-middle-date">' + qtyLoi1 +'</div>'
					 + '<div class="div-float-left div-loi display-content-middle div-date display-content-middle-date">' + qtyLoi2 +'</div>'
					 + '<div class="div-float-left div-loi display-content-middle div-date display-content-middle-date">'+ qtyLoi3 +'</div>'
					 + '<div class="div-float-left div-loi display-content-middle div-date display-content-middle-date">' + qtyLoi4 +'</div>'
					 + '<div class="div-float-left div-loi display-content-middle div-date background-loi3 display-content-middle-order"></div>');
//		divCountryTop.append(divLockedCountry);
		thCountry.append(divCountryTop);
		thCountry.append(divCountryBottom.append(divQty));
		thCountry.append(divCountryDate.append(divDate));
		tr2.append(thCountry);
	}
	thead.append(tr2);
	createCssFixedWidthTable();
}

/**
 * create combox for category and Item Name (Item Code)
 * 
 */
function createTrBody(stock, isDuplicate) {
	var trBody = $("<tr></tr>");
	trBody.append('<td width="" ><input type="checkbox"></td>');
	var comboboxCategory1 = $("<select></select>");
	comboboxCategory1.addClass('combobox');
	
	// add category
	$.each(categoryObject, function(i, item) {
		comboboxCategory1.append($('<option>', {
			value : item.categoryCd,
			text : item.categoryName
		}));
	});
	trBody.append($('<td width=""></td>').append(createComboboxCategory(categoryObject, stock, isDuplicate)));
	
	// add item
	var comboboxItem = $("<select></select>");
	comboboxItem.addClass('combobox');
	trBody.append($('<td width=""></td>').append(createComboboxItem(itemObject, stock)));

	// add country
	for (var i = 0; i <countryObject.length; i++){
		// TODO: tại sao là i*6
		var postionQtySub = postionQty + i*9;
		var qty1Value = "";
		var qty2Value = "";
		var qty3Value = "";
		var qty4Value = "";
		var qtyLoi2Value = "";
		var qtyLoi3Value = "";
		var qtyLoi4Value = "";
		var balanceValue = "";
		if (stock) {
			// PHY
			if (stock[postionQtySub]) {
				qty1Value = stock[postionQtySub];
				if (qty1Value == 0 || qty1Value === "0") {
					qty1Value = ""
				}
			}
			// LOI1 Qty
			if (stock[postionQtySub + 2]) {
				qty2Value = stock[postionQtySub + 2];
				if (qty2Value == 0 || qty2Value === "0") {
					qty2Value = ""
				}
			}
			// LOI1 Date
			if (stock[postionQtySub + 3]) {
				qtyLoi2Value = stock[postionQtySub + 3];
			}
			// LOI2 Qty
			if (stock[postionQtySub + 4]) {
				qty3Value = stock[postionQtySub + 4];
				if (qty3Value === 0 || qty3Value === "0") {
					qty3Value = ""
				}
			}
			// LOI2 Date
			if (stock[postionQtySub + 5]) {
				qtyLoi3Value = stock[postionQtySub + 5];
			}
			// LOI3 Qty
			if (stock[postionQtySub + 6]) {
				qty4Value = stock[postionQtySub + 6];
				if (qty4Value == 0 || qty4Value === "0") {
					qty4Value = ""
				}
			}
			// LOI3 Date
			if (stock[postionQtySub + 7]) {
				qtyLoi4Value = stock[postionQtySub + 7];
			}
			// Balance
			if (stock[postionQtySub + 8]) {
				balanceValue = stock[postionQtySub + 8];
			}
		}
		var qty1 = '<div class="div-float-left div-phy-qty div-value">' + 
		'<div class="div-loi-qty-left color-value'+checkIsNull(stock, isDuplicate, 1, qty1Value)+'">' + 
		'<input type="text" maxlength="2" onkeypress="return isNumberSad0021(event);" onchange="changeScreen(this);" class="input-value copy-paste'+'" value="'+qty1Value+'"/>' +
		'</div>' +
		'</div>';
		var qty2 = '<div class="div-float-left div-loi-qty div-value">' +
		'<div class="div-loi-qty-left color-value'+checkIsNull(stock, isDuplicate, 2, qty2Value)+'">' +
		'<input type="text" maxlength="2" onkeypress="return isNumberSad0021(event);" onchange="changeScreen(this);" class="input-value copy-paste'+'" value="'+isNull(qty2Value)+'"/>' +
		'</div>' +
		'</div>';
	
		var qty3 = '<div class="div-float-left div-loi-qty div-value">' +
		'<div class="div-loi-qty-left color-value'+checkIsNull(stock, isDuplicate, 3, qty3Value)+'">' +
		'<input type="text" maxlength="2" onkeypress="return isNumberSad0021(event);" onchange="changeScreen(this);" class="input-value copy-paste'+'" value="'+isNull(qty3Value)+'"/>' +
		'</div>' +
		'</div>';
		
		var qty4 = '<div class="div-float-left div-loi-qty div-value">' +
		'<div class="div-loi-qty-left color-value'+checkIsNull(stock, isDuplicate, 4, qty4Value)+'">' +
		'<input type="text" maxlength="2" onkeypress="return isNumberSad0021(event);" onchange="changeScreen(this);" class="input-value copy-paste'+'" value="'+isNull(qty4Value)+'"/>' +
		'</div>' +
		'</div>';
		
		var balance = '<div class="div-float-left div-loi-qty div-value">' +
		'<div class="div-loi-qty-left color-value">' +
		'<input type="text" maxlength="2" onkeypress="return isNumberSad0021(event);" onchange="changeScreen(this);" class="background-loi3" value="'+isNull(balanceValue)+'"/>' +
		'</div>' +
		'</div>';
		trBody.append('<td width="" class="data-table-body">'+ qty1 + qty2 + qty3 + qty4 + balance +'</td>');
	}
//	if($('.dataTables_scrollHead table th.data-table-header').not('.display-disabled').find('a').length <= 0){
//		
//	}
	
	return trBody;
}

/**
 * combobox category
 * 
 * @returns
 */
function createComboboxCategory(categoryObject, stock, isDuplicate){
	var combobox = $('<div class="input-group dropdown combobox-dropdown"></div>');
	var categoryCd = "";
	var categoryName = "";
	if (stock) {
		if (stock[0]) {
			categoryCd = stock[0];
		}
		if (stock[1]) {
			categoryName = stock[1];
		}
	}
	if(!isDuplicate) {
		combobox.append('<input type="text" maxlength="30" class="dropdown-toggle combobox-input comboboxCategoryInputName" value="'+categoryName+'" readonly/>');
	} else {
		combobox.append('<input type="text" maxlength="30" class="dropdown-toggle combobox-input comboboxCategoryInputName light-grey-color" value="'+categoryName+'" readonly/>');
	}
		
	combobox.append('<input type="text" maxlength="10" class="dropdown-toggle combobox-input comboboxCategoryInput" value="'+categoryCd+'" readonly style="display:none"/>');
	combobox.append('<input type="text" maxlength="10" class="comboboxCategoryInputFromDB" value="'+categoryCd+'" readonly style="display:none"/>');
	var ul = $('<ul class="dropdown-menu combobox-ul comboboxCategory"></ul>');
	$.each(categoryObject, function(i, item) {
		ul.append('<li><a data-value="'+ item.categoryCd +'">'+ item.categoryName +'</a></li>');
	});
	combobox.append(ul);
	combobox.append('<span role="button" class="input-group-addon dropdown-toggle combobox-span" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" onclick="showCombobox(this)"><span class="caret"></span></span>');
	return combobox;
}

/**
 * combobox Item
 * 
 * @returns
 */
function createComboboxItem(itemObject, stock){
	var combobox = $('<div class="input-group dropdown combobox-dropdown"></div>');
	var itemCode = "";
	var itemName = "";
	if (stock) {
		if (stock[2]) {
			itemCode = '(' + stock[2] + ')';
		}
		itemName = stock[3];
	}
	combobox.append('<div data-tip = "'+itemName+'"><input type="text" maxlength="50" class="dropdown-toggle combobox-input comboboxItemInputName" value="'+itemName+'" readonly/></div>');
	combobox.append('<input type="text" maxlength="30" class="dropdown-toggle combobox-input comboboxItemInputCd" value="'+itemCode+'" style="display:none" readonly/>');
	combobox.append('<input type="text" maxlength="30" class="comboboxItemInputCdFromDB" value="'+itemCode+'" style="display:none" readonly />');
	if (stock) {
		var ul = $('<ul class="dropdown-menu combobox-ul comboboxItem"></ul>');
		$.each(itemObject, function(i, item) {
			if (stock[0] == item.categoryCd) {
	    		var span = '<span class="row spanItemName">' + item.itemName + '</span>' +
	    		   '<span class="row spanItemCd">('+ item.itemCd +')</span>';
	    		ul.append('<li><a data-value="'+ item.itemCd +'">'+ span +'</a></li>');
			}
		});
		combobox.append(ul);
	}
	combobox.append('<span role="button" class="input-group-addon dropdown-toggle combobox-span" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" onclick="showCombobox(this)"><span class="caret"></span></span>');
	return combobox;
}

/**
 * show combobox in table
 * 
 * @param element
 * @returns
 */
function showCombobox(element){
	// Redraw comboboxItem
	var ulCombobox = $(element).closest("div.combobox-dropdown").find("ul");
	// If the comboboxItem is redraw
	if (ulCombobox.attr("class").indexOf("comboboxItem") > -1){
		var categoryCd = $(element).closest("td").prev().find("div.combobox-dropdown").find("input.comboboxCategoryInput").val();
		var itemCdOld = $(element).closest("div.combobox-dropdown").find("input.comboboxItemInputCd").val().replace(/[\(\)]/g, "");
		ulCombobox.empty();
    	$.each(itemObject, function(i, item) {
    		// Load list category by category selected
    		if (categoryCd == item.categoryCd) {
    			if (!checkItemSelected(item.itemCd) || item.itemCd === itemCdOld){
		    		var span = '<span class="row spanItemName">' + item.itemName + '</span>' +
		    		   '<span class="row spanItemCd">('+ item.itemCd +')</span>';
		    		ulCombobox.append('<li><a data-value="'+ item.itemCd +'">'+ span +'</a></li>');
		    		isAdd = true;
    			}
    		}
    	});
    	addEventSelectOneComboboxItem(ulCombobox);
	}
	
	// Fixed position show combobox
	var DTFC_LeftBodyWrapper = 0;
	if ($(".DTFC_LeftBodyWrapper").offset()) {
		DTFC_LeftBodyWrapper = $(".DTFC_LeftBodyWrapper").offset().top;
	}
	var offsetTopDTFCBody = DTFC_LeftBodyWrapper + $(".DTFC_LeftBodyWrapper").height();
	var td = $(element).closest("td");
	var offsetTopCombobox = td.offset().top + td.height() + td.find(".combobox-ul").outerHeight();
	var drop = $(element).closest("div.combobox-dropdown");
	if (offsetTopCombobox > offsetTopDTFCBody){
		drop.removeClass("dropdown");
		drop.addClass("dropup");
	} else {
		drop.removeClass("dropup");
		drop.addClass("dropdown");
	}
}

/**
 * addEventSelectComboboxCategory
 * 
 */
function addEventSelectComboboxCategory(isDuplicate){
	
	$('.comboboxCategory a').click(function () {
        var categoryCd = $(this).attr('data-value');
        var categoryName = $(this).text();
        var comboboxCategoryInputOld = $(this).closest('.combobox-dropdown').find('input.comboboxCategoryInput').val();
        // Finds parent of combobox-dropdown
        var $row = $(this).closest('.combobox-dropdown').parent().parent();
        // Clear Item Name (Item Code) when category on change
        if ($(this).closest('.combobox-dropdown').find('input.comboboxCategoryInput').val() != categoryCd) {
        	// Clear clone Item Name (Item Code)
            $(this).closest('.combobox-dropdown').parent().parent().find('input.comboboxItemInputCd').val("");
            $(this).closest('.combobox-dropdown').parent().parent().find('input.comboboxItemInputName').val("");
            
    		//Clear originals Item Name (Item Code)
    		updateDataForDatatableOriginal('input.comboboxItemInputCd', $row.index(), "", true);
    		updateDataForDatatableOriginal('input.comboboxItemInputName', $row.index(), "", false);
            isOnChangeScreen = true;
        }
        
        // Set value for cloned elements
        $(this).closest('.combobox-dropdown').find('input.comboboxCategoryInput').val(categoryCd);
        $(this).closest('.combobox-dropdown').find('input.comboboxCategoryInputName').val(categoryName);

        
        // Finds the 3nd <td> element
        var $tds = $row.find("td:nth-child(3)");
        
		// Set value for originals elements
		updateDataForDatatableOriginal('input.comboboxCategoryInput', $row.index(), categoryCd, true);
		updateDataForDatatableOriginal('input.comboboxCategoryInputName', $row.index(), categoryName, false);

		// If the selected category is the same as the previous row, the text should be in light grey color.
		// isDuplicate use for After [Add] new row, and select "Category" value, the "Category" value color should be black
		if (comboboxCategoryInputOld != categoryCd && isDuplicate !=null) {
		    if (checkDuplicateInList(categoryCd)) {
		    	$(this).closest('.combobox-dropdown').find('input.comboboxCategoryInputName').addClass('light-grey-color');
		    } else {
		    	$(this).closest('.combobox-dropdown').find('input.comboboxCategoryInputName').removeClass('light-grey-color');
		    }
		}
        // Visits every single <td> element (Item Name (Item Code))
	    $.each($tds, function() {
	    	// Finds comboboxItem element
	    	$(this).find('ul.comboboxItem').remove();
	    	var combobox = $(this).find('div.combobox-dropdown');
	    	var ul = $('<ul class="dropdown-menu combobox-ul comboboxItem"></ul>');
	    	var isAdd = false;
	    	$.each(itemObject, function(i, item) {
	    		// Load list category by category selected
	    		if (categoryCd == item.categoryCd) {
		    		var span = '<span class="row spanItemName">' + item.itemName + '</span>' +
		    		   '<span class="row spanItemCd">('+ item.itemCd +')</span>';
		    		ul.append('<li><a data-value="'+ item.itemCd +'">'+ span +'</a></li>');
		    		isAdd = true;
	    		}
	    	});
	    	//if (isAdd) {
	    		combobox.append(ul);
	    		addEventSelectComboboxItem();
	    	//}     
	    });
		refreshColorTable(comboboxCategoryInputOld);
    });
}

/**
 * addEventSelectComboboxItem
 * 
 * @returns
 */
function addEventSelectComboboxItem(){
	$('.comboboxItem a').click(function () {
		isOnChangeScreen = true;
        var itemCd = $(this).find("span.spanItemCd").text().trim();
        var itemName = $(this).find("span.spanItemName").text().trim();
        $(this).closest('.combobox-dropdown').find('input.comboboxItemInputCd').val(itemCd);
        $(this).closest('.combobox-dropdown').find('input.comboboxItemInputName').val(itemName);
        // Finds parent of combobox-dropdown
        var $row = $(this).closest('.combobox-dropdown').parent().parent();
		// Set value for originals elements
		updateDataForDatatableOriginal('input.comboboxItemInputCd', $row.index(), itemCd, false);
		updateDataForDatatableOriginal('input.comboboxItemInputName', $row.index(), itemName, false);
    });
}

/**
 * addEventClickInputCombobox
 * 
 */
function addEventClickInputCombobox(){
	$(".combobox-input").css("cursor", "pointer");
	$(".combobox-input").unbind("click");
	$(".combobox-input").click(function () {
		$(this).closest("div.combobox-dropdown").find("span.combobox-span").click();
	});
}

/**
 * init datatable
 * 
 * @returns
 */
function initDatatable(){
	resultTable = $('#resultTable').DataTable({
		"ordering": false,
		"scrollX": true,
		"sScrollY" : calcDataTableHeight(70),
		"scrollCollapse" : true,
		'bLengthChange' : true,
		"paging": false,
		"searching": false,
		"fixedColumn": true,
		"info": false,
		fixedColumns:   {
            leftColumns: 3
        },
        "asStripeClasses": [],
        "fnInitComplete" : function() {
        	fixedMarginLeftScrollX();
			$('.dataTables_scrollBody').perfectScrollbar({
				
			});
		},
		"dom": "<'row'<'col-xs-2'l><'col-xs-8'><'col-xs-2'f>>" +
		"<'row'<'col-xs-12'tr>>" +
		"<'row'<'col-xs-3'i><'col-xs-5'><'col-xs-4'p>>"
	});
}

/**
 * configFixedColumn
 * 
 * @returns
 */
function configFixedColumn(){
	// Set height DTFC_LeftWrapper
	$("div.DTFC_LeftWrapper div.DTFC_LeftHeadWrapper").css("height",$("div.dataTables_scrollHead").height());
	// var heightBodyTable =
	// $("div.dataTables_scrollBody").height();console.log("scrollBody" +
	// heightBodyTable);
	// $("div.DTFC_LeftBodyWrapper").css("height",
	// heightBodyTable);console.log("DTFCW" +
	// $("div.DTFC_LeftBodyWrapper").css("height"));
	// $("div.DTFC_LeftBodyLiner").css("height",
	// heightBodyTable);console.log("DTFCL" +
	// $("div.DTFC_LeftBodyLiner").css("height"));
	// $("div.DTFC_LeftBodyLiner").css("max-height", heightBodyTable);
	
	// Set max-height dataTables_scrollBody
	$("div.dataTables_scrollBody").css("max-height", calcDataTableHeight(6));
	var marginLeftScrollX = $("div.DTFC_LeftWrapper div.DTFC_LeftHeadWrapper").width();
	fixedMarginLeftScrollX();
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
    				margin-left: "+ (marginLeftBefor + 2) +"px !important;\
    				margin-right: 18px !important;\
    			}\
    			.heightTable{\
    				height: "+ (calcDataTableHeight(6)) +"px !important;\
    			}\
        		.maxHeightTable{\
        			max-height: "+ (calcDataTableHeight(6)) +"px !important;\
    			}")
    	.appendTo("head");
    	
    	// bottom: "+ (bottom) +"px !important;\
}

/**
 * Delete screen
 */
function doDelete(){
	showConfirmDialog("delete","","",{"position":"200"});
}

/**
 * Save screen
 */
function doSave(){

	var result = $('.dataTables_scrollHead table th.data-table-header').not('.display-disabled').find('a').length;
	if (result != 1 && result != countryObject.length) {
//		$("#contentMessage").text("CM-0008 : "+ getMessageWithParams("CM-0013"));
//		$("#modalInfo").modal("show");
		return;
	}
	if (findDuplicatesInTable()) {
		$("#contentMessage").text("CM-0013 : "+ getMessageWithParams("CM-0013"));
		$("#modalInfo").modal("show");
	} else {
		showConfirmDialog("save","","",{"position":"200"});
	}
}
/**
 * Close screen
 */
function doCloseSad0021(){
	// If user only view
	if (!enterScreen) {
		window.location = contextPath + 'faa001/saa0012';
	}
	else { // If user editable 
		showConfirmDialog("close","","",{"position":"200"});
	}
}

/**
 * Method will call when Click button yes on dialog confirm
 * 
 * @param idEvent
 *            Event Download/Close
 * @param element
 *            Dialog confirm
 */
function doEventDialog01(idEvent, element){
	var info = "";
	var page = "";
	var params = {};

	if (resultTable) {
		info = resultTable.page.info();
		page = info.page;
	}
	switch (idEvent) {
	case 'redirect':
		// Allow the user to go into screen as read-only
		loadData();
		disableElement();
		break;
	case 'close':
		window.location = contextPath + 'fad001/doCloseAction';
		break;
	case 'save':
//		if(feature == "updateCountryData") {
		var enableCountries = $('.dataTables_scrollHead table th.data-table-header').not('.display-disabled').find('a');

		if(enableCountries.length == 1) {
			getSelectedCountry(params);
		}
		else if(enableCountries.length == countryObject.length) {
			getAllSelectedRows(params);
		}

		if(enableCountries.length == 1) {
			var lock = checkLockForSave(params);
			if(lock == true){
				var data = {};
				data.countryCd = enableCountries.text();
				var lock = callAjaxCheckLockedDataByAnother(data, 'checkLockedDataByAnotherAction', 'get');
				if (lock) {
					$("#contentMessage").text(getMessageWithParams("CM-0017"));
					$("#modalInfo").modal("show");
					return;
				}
			}
		}

		callAjaxDataSad0021(params,'doSaveAction','POST',page);

//		$('.btn-add').prop('disabled', false);
//		feature = null;
		break;
	case 'delete':
		getSelectedRows(params);
		callAjaxDataSad0021(params,'doDeleteAction','POST',page);
		break;
	case 'switch':
		positionUpdate = -1;
		isSwitched = true;
		errorFg = "switch";
		doUpdateCountryData($('.dataTables_scrollHead table th.data-table-header').eq(element));
		isSwitched = false;
		break;
	default:
		window.location = contextPath + 'faa001/saa0012';
	break;
	}
}

/**
 * showConfirmDialog
 * 
 */
function showConfirmDialog(idEvent, listButton, param, paramSettingDialog) {
	paramForRedirectMenu = param;
	var messageId = "";
	var modal = "#modalConfirm";
	if (idEvent == "redirect") {
		messageId = "CM-0003";
		$('#buttonYes').attr('onclick', "doYes('"+idEvent+"')");
		$('#buttonNo').attr('onclick', "doEventDialog01('')");
		$('.close').attr('onclick', "doEventDialog01('')");
	} else if (idEvent == "redirectMenu") {
		messageId = "CA-0002";
	} else if (idEvent == "delete") {
		messageId = "IM2-0004";
	} else if (idEvent == "save") {
		if(isSelectedCountry == false){
			$("#contentMessage").text(getMessageWithParams("CM-0008"));
			$("#modalInfo").modal("show");
			return;
		} else {
			messageId = "IM2-0002";
		}
	} else if (idEvent == "close") {
		messageId = "IM2-0003";
	} else if (idEvent == "upload") {
		messageId = "IM2-0001";
	} else if (idEvent == "overwrite") {
		messageId = "IM2-0005";
	} else if (idEvent == "logout") {
		messageId = "CA-0001";
		var contentConfirmLogout = getMessageWithParams(messageId,"");
		document.getElementById("contentConfirmLogout").innerHTML = contentConfirmLogout;
		$('#buttonYesLogout').attr('onclick', "doYes('"+idEvent+"')");
		$("#modalConfirmLogout").modal("show");
		return;
	} else if (idEvent == "switch") {
		messageId = "CM-0015";
	} else {
		messageId = "CM-0004";
		var title = "Confirm";
		modal = "#modalInfo";
		var contentConfirm = getMessageWithParams(messageId,"");
		document.getElementById("titleModal").innerHTML = title;
		document.getElementById("contentMessage").innerHTML = messageId+" : "+ contentConfirm;
		$("#modalInfo_image").attr("src","../style/imgs/confirm.png");
	}
	var contentConfirm = getMessageWithParams(messageId,"");
	document.getElementById("contentConfirm").innerHTML = /* messageId+" : "+ */ contentConfirm;
	
	// In case close
	if (idEvent == "close"){
		$("#contentConfirm").parent().parent().css("text-align", "center");
	}
	if (idEvent !== "redirect") {
			$('#buttonYes').attr('onclick', "doYes('"+idEvent+"','','"+param+"')");
	}
	if (idEvent === "redirectMenu") {
		$('#buttonYes').removeAttr('onclick');
		$('#buttonYes').attr('onclick', "doYes('"+idEvent+"')");
	}
	$(modal).modal("show");
	
}

/**
 * getSelectedRows
 * 
 */
function getSelectedRows(params) {
	paramList = [];
	var categoryCd=[], categoryCdFromDb=[], itemCd=[], itemCdFromDb=[], countryCd=[], qty=[];
	// Get all data in datatable
	var rows = $("#resultTable").dataTable().fnGetNodes();
	$(rows).each(function(){
		var tds=$(this).find('input[type=text]');
		var index = $(this).index();
		var checkBox = $(this).find("input:checkbox");
		if ($(checkBox).is(':checked')) {
			// List country
	    	for (var i = 0; i < countryObject.length; i++){
	    		var qtySub = [];
	        	// Category CD
	    		categoryCd.push(tds[1].value);
	            categoryCdFromDb.push(tds[2].value);
	            
	            // Item Code
//	            if (tds[3].value) {
//	            	tds[3].value = tds[3].value;
//	            }
	            itemCd.push(tds[4].value);
	            itemCdFromDb.push(tds[5].value);
	            
	            // Country
	    		countryCd.push(countryObject[i].countryCd);
	    		
	            // Qty
	    		var startQty = 3 + i;
	    		/**
				 * [Process for selected checkbox in Datatable when use
				 * FixedColumns] Because FixedColumns are cloned elements, not
				 * the originals (which are hidden using the column visibility
				 * options of DataTables). When the clones are updated your
				 * checked boxes nodes are deleted and then replaced when the
				 * originals (note that this is with FixedColumns, not
				 * DataTables on its own), thus the issue - the originals
				 * haven't been checked the clones were
				 */
	    		// Get qty by index selected chechbox
	    		var tdQty = $(this).find("td:eq("+startQty+")");
	    		// Get all input
	    		var qtyList = $('input', tdQty);
	    		qtyList.each(function(index, qty){
	    			qtySub.push(qty.value);
	    		});
	    		qty.push(qtySub);
	    	}
		}
	});
	var obj = {categoryCd: categoryCd, categoryCdFromDb: categoryCdFromDb, itemCd: itemCd, itemCdFromDb: itemCdFromDb, countryCd: countryCd, qty: qty};
	paramList.push(obj);
	params.paramList = JSON.stringify({arr:paramList});
}

/**
 * Get all when save
 * 
 */
function getAllSelectedRows(params) {
	paramList = [];
	var categoryCd=[], categoryCdFromDb=[], itemCd=[], itemCdFromDb=[], countryCd=[], qty=[], countryDate=[];
	// Get all data in datatable
	var rows = $("#resultTable").dataTable().fnGetNodes();
	$(rows).each(function(){
		if($(this).find('.div-loi-qty-left').hasClass('display-disabled')){
			return true;
		}

		var tds=$(this).find('input[type=text]');
		var index = $(this).index();
		var checkBox = $(this).find("input:checkbox");
		// List country
		for (var i = 0; i < countryObject.length; i++){
			var qtySub = [];
			var dateSub = [];
            // Category CD
			categoryCd.push(tds[1].value);
			categoryCdFromDb.push(tds[2].value);

			// Item Code
//			if (tds[3].value) {
//				tds[3].value = tds[3].value;
//			}
			itemCd.push(tds[4].value);
			itemCdFromDb.push(tds[5].value);

			// Country
			countryCd.push(countryObject[i].countryCd);

			// Qty
			var startQty = 3 + i;
			/**
			 * [Process for selected checkbox in Datatable when use
			 * FixedColumns] Because FixedColumns are cloned elements, not
			 * the originals (which are hidden using the column visibility
			 * options of DataTables). When the clones are updated your
			 * checked boxes nodes are deleted and then replaced when the
			 * originals (note that this is with FixedColumns, not
			 * DataTables on its own), thus the issue - the originals
			 * haven't been checked the clones were
			 */
			
			// Get date by index
			var tdDate = $('.dataTables_scrollHead .data-table-header').eq(i).find('.input-loi');
			// Get all input
			for(var j = 0; j < tdDate.size(); j++){
				dateSub.push(tdDate.eq(j).next().val());
			}
			countryDate.push(dateSub);
			
			// Get qty by index
			var tdQty = $(this).find("td:eq("+startQty+")");
			// Get all input
			var qtyList = $('input', tdQty);
			qtyList.each(function(index, qty){
				qtySub.push(qty.value);
			});
			qty.push(qtySub);
		}
	});
	var obj = {categoryCd: categoryCd, categoryCdFromDb: categoryCdFromDb, itemCd: itemCd, itemCdFromDb: itemCdFromDb, countryCd: countryCd, countryDate: countryDate, qty: qty};
	paramList.push(obj);
	params.paramList = JSON.stringify({arr:paramList});
}

/**
 * Get selected country data when save
 * 
 */
function getSelectedCountry(params) {
	paramList = [];
	var categoryCd=[], categoryCdFromDb=[], itemCd=[], itemCdFromDb=[], countryCd=[], countryDate=[], qty=[];
	// Get all data in datatable
	var rows = $("#resultTable").dataTable().fnGetNodes();

	$(rows).each(function(){
		var tds=$(this).find('input[type=text]');
		var index = $(this).index();

		var qtySub=[];
		// Category CD
		categoryCd.push(tds[1].value);
		categoryCdFromDb.push(tds[2].value);

		// Item Code
//		if (tds[3].value) {
//			tds[3].value = tds[3].value;
//		}
		itemCd.push(tds[4].value);
		itemCdFromDb.push(tds[5].value);

		// Country
		var position = getEnablePosition($('.dataTables_scrollHead table th.data-table-header').not('.display-disabled').find('a').text());
		countryCd.push(countryObject[position].countryCd);

		// Qty
		var startQty = 3 + position;
		/**
		 * [Process for selected checkbox in Datatable when use
		 * FixedColumns] Because FixedColumns are cloned elements, not
		 * the originals (which are hidden using the column visibility
		 * options of DataTables). When the clones are updated your
		 * checked boxes nodes are deleted and then replaced when the
		 * originals (note that this is with FixedColumns, not
		 * DataTables on its own), thus the issue - the originals
		 * haven't been checked the clones were
		 */
		var temp=[], dateSub=[];
		temp = getDatePicker(position);
		for(var j = 0; j < temp.length; j++){
			dateSub.push(temp[j]);
		}
		countryDate.push(dateSub);
		
		// Get qty by index selected chechbox
		var tdQty = $(this).find("td:eq("+startQty+")");
		// Get all input
		var qtyList = $('input', tdQty);
		qtyList.each(function(index, qty){
			qtySub.push(qty.value);
		});
		qty.push(qtySub);
	});
	
	var obj = {categoryCd: categoryCd, categoryCdFromDb: categoryCdFromDb, itemCd: itemCd, itemCdFromDb: itemCdFromDb, countryCd: countryCd, countryDate: countryDate, qty: qty};
	paramList.push(obj);
	params.paramList = JSON.stringify({arr:paramList});
}

/**
 * Delete selected rows.
 */
function deleteSelectedRows() {
	// Get all data in datatable
	var rows = $("#resultTable").dataTable().fnGetNodes();
	$(rows).each(function(){
		var tds=$(this).find('input[type=text]');
		var index = $(this).index();
		var checkBox = $(this).find("input:checkbox");
		if ($(checkBox).is(':checked')) {
			$("#resultTable").dataTable().fnDeleteRow(index);
		}
	});
}
/**
 * call popup when edit data
 */
$(document).on('click', '#resultTable', function(event) {
	var params = {};
	getSelectedRows(params);
	var rows = $("#resultTable").dataTable().fnGetNodes();  
	var index = $(rows[0].rowIndex);
	var td = $(rows[0]).find("td:eq(3)");
	var qtyList = $('input', td);
	qtyList.each(function(index, qty){
	});
	
});
/**
 * Call when insert button click
 * 
 * @param params
 *            data
 * @param actionName
 *            action name
 * @param methodType
 *            type
 */
function callAjaxDataSad0021(params, actionName, methodType,page) {
	loading_ajax();
    $.ajax({
    		cache: false,
            url : actionName,
            type : methodType,
            data: params,
            dataType : "json",
            success : function(response) {
            	close_loading_ajax();
            	pad0021List = response.pad0021List;
            	addButtonFlag = response.addFlag;
            	var messageId = response.messageId;
            	
            	if (messageId != null && messageId != "") {
            		if (messageId == 'CM-0002') {
            			deleteSelectedRows();
            		}
            		else {
                		showErrorDialog(messageId);
            		}
            		disableButtonDelete();
				}
            	else {
            		disabledAddButton(addButtonFlag);
            	}
            	// Don't reload table when message is exist
            	if (actionName == 'doDeleteAction' && !messageId) {
            		
            		// Reload data for json
        			itemObject = response.itemNameCodeList;
        			
        			if(pad0021List == null || pad0021List.length == 0){
        				location.reload();
        				return;
        			}
        			// Delete the rows
        			resultTable.rows().remove().draw();
        			drawTbodyForDelete(actionName);
        			if($("input:checkbox").is(':checked') == false
        					|| pad0021List.length == 0) {
        				disableButtonDelete();
        			}
            	}
            	
            	if (actionName == 'doSaveAction' && !messageId) {
            		location.reload();
            		addFlag = false;
            		isSelectedCountry = false;
            	}
            },
            error:function(jqXhr, textStatus, errorThrown){
    			// Delete the rows
    			resultTable.rows().remove().draw();
        		drawTbody();
                close_loading_ajax();
            }
    });
}

/**
 * [Add] button will add a new row into table.
 * 
 */
function doAdd(stock, isDuplicate) {
	
	$('.dataTables_scrollHead table th.data-table-header').removeClass('display-disabled');
	$('.dataTables_scrollBody table div.div-loi-qty-left').addClass('display-disabled');
	$(".dataTables_empty").parent().remove();
	isSelectedCountry = true;
	$('.btn-add').attr("disabled", true).on('click');
	
	var tbody = $("#resultTable tbody");
	// create content for tag <tr>
	var trBody = createTrBody(stock, isDuplicate);
	tbody.append(trBody);
	
	$('input:checkbox').attr("disabled", true).on('click');
	
	if (resultTable) {
		// Reload the table data when add new row
		resultTable.row.add(trBody).draw();
		configFixedColumn();
	}

	// add even when combox selected
	addEventSelectComboboxCategory(isDuplicate);
	addEventSelectComboboxItem();
	addEventClickInputCombobox();
	checkboxChange();
	isCopyPasteCut();

}

/**
 * [Add] button will add a new row into table.
 * 
 */
function doAddButton(stock, isDuplicate) {
	if (callAjaxCheckLockedData('checkLockedDataAction', 'get')) {
		$("#contentMessage").text(getMessageWithParams("CM-0016"));
		$("#modalInfo").modal("show");
	} 
	else {
		var countryData = {};
		countryData.updatedUser = userInfo.userId;
		countryData.exclusiveFg = "1";
		callAjaxUpdateCountryData2(countryData, 'doUpdateCountryData2Action','POST');
//		feature = "addNew";
		doAdd(stock, isDuplicate);
	}
}

function doAddNewItem (stock, isDuplicate) {
	$(".dataTables_empty").parent().remove();
	var tbody = $("#resultTable tbody");
	// create content for tag <tr>
	var trBody = createTrBody(stock, isDuplicate);
	tbody.append(trBody);
	if (resultTable) {
		// Reload the table data when add new row
		resultTable.row.add(trBody).draw();
		configFixedColumn();
	}

	// add even when combox selected
	addEventSelectComboboxCategory(isDuplicate);
	addEventSelectComboboxItem();
	addEventClickInputCombobox();
	checkboxChange();
	isCopyPasteCut();
}

/**
 * Use “reduce” an array into a single object
 * 
 * *Array Stock: 0: categoryCd 01: categoryName 02: itemCd 03: itemName 04-07:
 * Qty 1 - 4 by country with display order(1) 08-11: Qty 1 - 4 by country with
 * display order(2) 12-15: Qty 1 - 4 by country with display order(3) 16-19: Qty
 * 1 - 4 by country with display order(4) 16-19: Qty 1 - 4 by country with
 * display order(5) 20-23: Qty 1 - 4 by country with display order(6) 24-27: Qty
 * 1 - 4 by country with display order(7) 28-31: Qty 1 - 4 by country with
 * display order(8) ...... n->n+3: Qty 1 - 4 by country with display order(n)
 */
Array.prototype.groupBy = function(prop) {
	  return this.reduce(function(groups, item) {
		  var val = item[prop];
		  groups[val] = groups[val] || [];
		  // don not push when categoryCd and itemCd and itemName is exist
		  if (typeof groups[val] == 'undefined' || groups[val].length <= 0) {
			  // Add category cd
			  groups[val].push(item.categoryCd);
			  // Add category name
			  groups[val].push(item.categoryName);
			  // Add item cd
			  groups[val].push(item.itemCd);
			  // Add item name
			  groups[val].push(item.itemName);
		  }
		  // Add qty
		  groups[val].push(item.qty1);
		  groups[val].push(changeDDMMMYYToDDMMM(item.qtyLoi1));
		  groups[val].push(item.qty2);
		  groups[val].push(changeDDMMMYYToDDMMM(item.qtyLoi2));
		  groups[val].push(item.qty3);
		  groups[val].push(changeDDMMMYYToDDMMM(item.qtyLoi3));
		  groups[val].push(item.qty4);
		  groups[val].push(changeDDMMMYYToDDMMM(item.qtyLoi4));
		  groups[val].push(item.balance);
		  return groups;
	  }, {});
}

/**
 * If count Category only 1 then remove class light-grey-color
 * 
 */
function refreshColorTable(val) {
	// Get list category input clone
	var inputs = $("div.DTFC_LeftWrapper .data-table tbody input.comboboxCategoryInput");
	// Get list category input name clone
	var inputNames = $("div.DTFC_LeftWrapper .data-table tbody input.comboboxCategoryInputName");
	var count = 0;
	var countNext = 0;
	var indexNext = 0;
	// Count number category code
	$.each(inputs, function(i, item) {
		// Check is duplicate and class not light-grey-color
		if (val == $(item).val() && !$(inputNames[i]).hasClass("light-grey-color")) {
			count++;
		}
		// Check is duplicate and class is light-grey-color
		if (val == $(item).val() && $(inputNames[i]).hasClass("light-grey-color")) {
			if (countNext == 0) {
				indexNext = i;
			}
			countNext++;
		}
	});
	// Remove class in clone
	if (count == 0 && countNext > 0) {
		$(inputNames[indexNext]).removeClass('light-grey-color');
	}
}
/**
 * If the selected category is the same as the previous row, the text should be in light grey color.
 * Check duplicate in list.
 * Return true when duplicate
 * 
 */
function checkDuplicateInList(val) {
	var rows = $("#resultTable").dataTable().fnGetNodes();
	var count = 0;
	$.each(rows, function(i, item) {
		var valueInput = $(item).find('input.comboboxCategoryInput').val();
		if (valueInput == val) {
			count++;
		}
	});
	if (count > 1) {
		return true;
	}
	return false;
	
}
/**
 * Draw table body.
 * 
 */
function drawTbody(action) {
	if (pad0021List && pad0021List.length) {
		var pad0021ListDuplicate = [];
		var pad0021ListPrivate = [];
		var pad0021ListGroupByItemCd = pad0021List.groupBy('itemCd');
		$.each(pad0021ListGroupByItemCd, function(key, stock) {
			pad0021ListPrivate.push(stock);
		});
		for (var i = 0; i < pad0021ListPrivate.length -1; i++) {
			if (pad0021ListPrivate[i + 1][0] == pad0021ListPrivate[i][0]) {
				pad0021ListDuplicate.push(i + 1);
			}
		}
		var index = 0;
		$.each(pad0021ListGroupByItemCd, function(key, stock) {
			var isDuplicate = false;
			for (var i = 0; i < pad0021ListDuplicate.length; i++) {
				if (index == pad0021ListDuplicate[i]) {
					isDuplicate = true;
				}
			}
			doAdd(stock, isDuplicate);
			index++;
		});
		
	}
	if (!action) {
		initDatatable();
	}
	configFixedColumn();
}

/**
 * Draw table body after Deleting
 * 
 */
function drawTbodyForDelete(action) {
	if (pad0021List && pad0021List.length) {
		var pad0021ListDuplicate = [];
		var pad0021ListPrivate = [];
		var pad0021ListGroupByItemCd = pad0021List.groupBy('itemCd');
	    $.each(pad0021ListGroupByItemCd, function(key, stock) {
	    	pad0021ListPrivate.push(stock);
	    });
	    for (var i = 0; i < pad0021ListPrivate.length -1; i++) {
	        if (pad0021ListPrivate[i + 1][0] == pad0021ListPrivate[i][0]) {
	    		pad0021ListDuplicate.push(i + 1);
	        }
	    }
    	var index = 0;
	    $.each(pad0021ListGroupByItemCd, function(key, stock) {
	    	var isDuplicate = false;
	    	for (var i = 0; i < pad0021ListDuplicate.length; i++) {
		    	if (index == pad0021ListDuplicate[i]) {
		    		isDuplicate = true;
		    	}
	    	}
	    	doAddForDelete(stock, isDuplicate);
	    	index++;
	    });

	}
	if (!action) {
		initDatatable();
	}
	configFixedColumn();
}

/**
 * [Add] button will add a new row into table.
 * 
 */
function doAddForDelete(stock, isDuplicate) {

	isSelectedCountry = true;

	var tbody = $("#resultTable tbody");
	// create content for tag <tr>
	var trBody = createTrBody(stock, isDuplicate);
	tbody.append(trBody);

	if (resultTable) {
		// Reload the table data when add new row
		resultTable.row.add(trBody).draw();
		configFixedColumn();
	}

//	$('.dataTables_scrollHead table th.data-table-header').removeClass('display-disabled');
	$('.dataTables_scrollBody table div.div-loi-qty-left').addClass('display-disabled');
	$(".dataTables_empty").parent().remove();

	// add even when combox selected
	addEventSelectComboboxCategory(isDuplicate);
	addEventSelectComboboxItem();
	addEventClickInputCombobox();
	checkboxChange();
	isCopyPasteCut();

	if($('.dataTables_scrollHead table th.data-table-header').not('.display-disabled').length == 1){
		$('.combobox-dropdown > *').removeClass('display-disabled').attr("disabled", false);
	}
	else {
		$('.combobox-dropdown > *').addClass('display-disabled').attr("disabled", true).on('click');
	}
}

/**
 * When a new row is added, [Value 1], [Value 2], [Value 3] and [Value 4] values
 * are "0" in light grey text (by default).
 * 
 */
function checkIsNull(isAddNew, isDuplicate, index, val) {
	if (val != 0) {
		return index;
	}
	return  "0" + index;
}

/**
 * Order is "0" that becomes "" (by default).
 * 
 */
function isNull(val) {
	if (val == 0) {
		return "";
	}
	return  val;
}

/**
 * When a new row is added, [Value 1], [Value 2], [Value 3] and [Value 4] values
 * are "0" in light grey text (by default).
 * 
 */
function checkIsNullForDate(isAddNew, isDuplicate, index, val) {
	// [isAddNew] When a new row is added, [Value 1], [Value 2], [Value 3] and
	// [Value 4] values are "0" in light grey text (by default).
	// [isDuplicate] If [Category] (row n+1) is the same as [Category] (row n),
	// [Category] (row n+1) is in light grey text
	if((isAddNew && !isDuplicate) || val) {
		return index;
	}
	return "0" + index;
}

/**
 * Update value for originals elements
 * 
 */
function updateDataForDatatableOriginal(name, index, value, isDrawComboboxItem) {
	// Get all data in datatable originals
	var rows = $("#resultTable").dataTable().fnGetNodes();
	// Set value for originals elements
	$(rows[index]).find(name).val(value);
	// Set comboboxItem for originals elements
	if (isDrawComboboxItem){
		// remove combobox item
		$(rows[index]).find('.comboboxItem').remove();
		// Span in combobox (Get postion 2)
		var span = $(rows[index]).find('.combobox-span')[1];
		// ul in combobox
		var ul = $('<ul class="dropdown-menu combobox-ul comboboxItem"></ul>');
		$.each(itemObject, function(i, item) {
			if (value == item.categoryCd) {
	    		var span = '<span class="row spanItemName">' + item.itemName + '</span>' +
	    		   '<span class="row spanItemCd">('+ item.itemCd +')</span>';
	    		ul.append('<li><a data-value="'+ item.itemCd +'">'+ span +'</a></li>');
			}
		});
		// add ul before span
		$(span).before(ul);
	}
}

/**
 * Check change screen
 * 
 * @return true: change
 */
function changeScreen(val) {
	isOnChangeScreen = true;
	
	// Check value input type number for change
	if (!$.isNumeric($(val).val())) {
		$(val).val($(val).val().replace(/\D/g, ''));
	}
	changeColor(val);
}

/**
 * Change color
 * 
 */
function changeColor(val) {
	//	By default, the values will be "0" and in light grey color. If the value is not "0", 
	//	Column 1 (Physical Stock): Color should be red.
	//	Column 2 (LOI1): Color should be black.
	//	Column 3 (LOI2): Color should be black.
	//	Column 4 (LOI3): Color should be white.
	var nameClass = $(val).attr('class');
	var replaceInsert = "0";
	if (nameClass) {
		if ($(val).val() == 0) {
			var position = nameClass.length - 1;
			var changeName = [nameClass.slice(0, position), replaceInsert, nameClass.slice(position)].join('');
			$(val).removeClass(nameClass);
			$(val).addClass(changeName);
		} else {
			var changeName = nameClass.replace(replaceInsert, "");
			$(val).removeClass(nameClass);
			$(val).addClass(changeName);
		}
	}
}

/**
 * Check value input type number
 * 
 */
function isNumberSad0021(event){
	if (!$.isNumeric(event.key)) {
		return false;
	}
	return true;
}

/**
 * Check value input type number for copy, paste, cut
 * 
 */
function isCopyPasteCut() {
   $('.copy-paste').bind("paste",function(e) {
	   var val = e.originalEvent.clipboardData.getData('Text');
	   if (!$.isNumeric(val)) {
		   e.preventDefault();
	   }
   });
}

/**
 * fixed height table frozen
 * 
 * @returns
 */
function fixHeightTable(){
	// Set height table body
	if ($("#resultTable").height() > calcDataTableHeight(6)){
		// $("div.dataTables_scrollBody").css("height",calcDataTableHeight(6));
	}
	// Set height table forzen (3 column first table body)
	// $("div.DTFC_LeftBodyWrapper").css("height",calcDataTableHeight(6));
	// $("div.DTFC_LeftBodyLiner").css("height",calcDataTableHeight(6));
	// $("div.DTFC_LeftBodyLiner").css("max-height",calcDataTableHeight(6));
	// Get height DTFC_LeftBody
	// var heightDTFC = $("div.DTFC_LeftBodyWrapper").height();
	$("div.DTFC_LeftBodyWrapper").addClass("heightTable");
	$("div.DTFC_LeftBodyLiner").addClass("heightTable");
	$("div.DTFC_LeftBodyLiner").addClass("maxHeightTable")
}

function tabIndex() {
	/*$("div.DTFC_LeftWrapper .data-table tbody input[type=checkbox]").attr("tabindex", "1");
	$("div.DTFC_LeftWrapper .data-table tbody .comboboxCategoryInputName").attr("tabindex", "2");
	$("div.DTFC_LeftWrapper .data-table tbody .comboboxCategoryInputName").attr("tabindex", "2");*/
}

String.prototype.replaceAt=function(index, replacement) {
    return this.substr(0, index) + replacement+ this.substr(index + replacement.length);
}

function createCssFixedWidthTable(){
	var body = $("body");
	var styleCss = $("<style>").prop("type", "text/css")
	.html("\
			.dataTables_scrollHeadInner table {\
				width: "+ (tableWidthGlobal*100) +"% !important;\
			}\
			.dataTables_scrollBody .order-column {\
				width: "+ (tableWidthGlobal*100) +"% !important;\
			}");
	body.prepend(styleCss);
}

/**
 * Convert date of database (2000-12-30 00:00:00) to 30-Dec
 * 
 * @returns date with format DD-MMM
 */
function changeDDMMMYYToDDMMM(obj) {
	if (obj) {
		// format like '28-Dec-2018'
		var regx = /^\d{2}\.[A-Z][a-z]{2}\.\d{4}$/g,
		// input date
		dateStr = obj,
		// day
		d,
		// month
		m;
	
		if (!regx.test(dateStr)) {
			dateStr = dateStr.replace(/[-,\/]/g, "");
		}

		m = parseInt(dateStr.substr(4, 2));
		d = parseInt(dateStr.substr(6, 2));
		if ((d >= 1 && d <= 31) && (m >= 1 && m <= 12)) {
			obj = formatDateTo_ddMMM(d.toString(), (m - 1).toString());
			return obj;
		}
	}
}

/**
 * Date validation
 * 
 * @returns
 */
function changeValidDateDDMMM(obj) {
	if (obj.value) {
		// format like '28-Dec'
		var regx1_1 = /^\d{2}\-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)/i,
		// format like '28.Dec'
		regx1_2 = /^\d{2}\.(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)/i,
		// format like '28/Dec'
		regx1_3 = /^\d{2}\/(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)/i,
		// format like '2812'
		regx2 = /^\d{4}$/g,
		// format like '28/12'
		regx3 = /^\d{2}\/\d{2}/g,
		// format like '28-12'
		regx4 = /^\d{2}-\d{2}/g,
		// input date
		dateStr = obj.value,
		// day
		d,
		// month
		m,
		// year
		y;
		if (regx1_1.test(dateStr)) {
			obj.value = obj.value.toUpperCase();
			return false;
		}
		if (regx1_2.test(dateStr)) {
			obj.value = dateStr.replace(".", "-").toUpperCase();
			return false;
		}
		if (regx1_3.test(dateStr)) {
			obj.value = dateStr.replace("/", "-").toUpperCase();
			return false;
		}
		if (!regx1_1.test(dateStr)) {
			if (regx2.test(dateStr) || regx3.test(dateStr) || regx4.test(dateStr)) {
				dateStr = dateStr.replace(/[-,\/]/g, "");
			} else {
				$("#contentMessage").text("CM-0011 : "+ getMessageWithParams("CM-0011"));
				$("#modalInfo").modal("show");
				return false;
			}
		}
	
		d = parseInt(dateStr.substr(0, 2));
		m = parseInt(dateStr.substr(2, 2));
	    y = (new Date()).getFullYear();
	    // Create list of days of a month [assume there is no leap year by default]
	    var ListofDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
	    if (m == 1 || m > 2) {
	        if (d > ListofDays[m - 1]) {
	        	$("#contentMessage").text("CM-0011 : "+ getMessageWithParams("CM-0011"));
	    		$("#modalInfo").modal("show");
	            return false;
	        }
	    }
	    if (m == 2) {
	        var lyear = false;
	        if ((!(y % 4) && y % 100) || !(y % 400)) {
	            lyear = true;
	        }
	        if ((lyear == false) && (d >= 29)) {
	        	$("#contentMessage").text("CM-0011 : "+ getMessageWithParams("CM-0011"));
	    		$("#modalInfo").modal("show");
	            return false;
	        }
	        if ((lyear == true) && (d > 29)) {
	        	$("#contentMessage").text("CM-0011 : "+ getMessageWithParams("CM-0011"));
	    		$("#modalInfo").modal("show");
	            return false;
	        }
	    }
		if ((d >= 1 && d <= 31) && (m >= 1 && m <= 12)) {
			obj.value = formatDateTo_ddMMM(d.toString(), (m - 1).toString());
		} else {
			$("#contentMessage").text("CM-0011 : "+ getMessageWithParams("CM-0011"));
			$("#modalInfo").modal("show");
		}
	}
}

function formatDateTo_ddMMM(d, m) {
	var month = [ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" ];
	return lpad(d, '0', 2) + "-" + month[m].toUpperCase();
}

function findDuplicatesInTable() {
	var rows = $("#resultTable").dataTable().fnGetNodes();
	var count = 0;
	$.each(rows, function(i, item) {
		var categoryCd = $(item).find('input.comboboxCategoryInput').val();
		var itemCd = $(item).find('input.comboboxItemInputCd').val();
		$.each(rows, function(iSub, itemSub) {
			var categoryCdSub = $(itemSub).find('input.comboboxCategoryInput').val();
			var itemCdSub = $(itemSub).find('input.comboboxItemInputCd').val();
			if (iSub !== i && categoryCd === categoryCdSub && itemCd === itemCdSub) {
				count++;
			}
		});
	});
	if (count > 1) {
		return true;
	}
	return false;
}

/**
 * check item selected on all combobox item
 * 
 * @param itemCd
 * @returns true if selected
 */
function checkItemSelected(itemCd){
	var flag = false;
	$(".comboboxItemInputCd").each(function(index){
		var valueInputItemCd = $(this).val().replace(/[\(\)]/g, "");
		if (itemCd === valueInputItemCd){
			flag = true;
			return;
		}
	});
	return flag;
}

/**
 * addEventSelectOneComboboxItem
 * add event select for one combobox item
 * 
 * @returns
 */
function addEventSelectOneComboboxItem(element){
	$(element).find("a").click(function () {
		isOnChangeScreen = true;
        var itemCd = $(this).find("span.spanItemCd").text().trim();
        var itemName = $(this).find("span.spanItemName").text().trim();
        $(this).closest('.combobox-dropdown').find('input.comboboxItemInputCd').val(itemCd);
        $(this).closest('.combobox-dropdown').find('input.comboboxItemInputName').val(itemName);
        // Finds parent of combobox-dropdown
        var $row = $(this).closest('.combobox-dropdown').parent().parent();
		// Set value for originals elements
		updateDataForDatatableOriginal('input.comboboxItemInputCd', $row.index(), itemCd, false);
		updateDataForDatatableOriginal('input.comboboxItemInputName', $row.index(), itemName, false);
    });
}

/**
 * Open date picker 1
 */
function openDatePicker1(button){
	$(button).datepicker({
		dateFormat : 'yy-M-dd',
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
	var a = $(button).datepicker("show");
}

/**
 * When User clicks on a Country, it is enabled
 * 
 * @returns
 */

function enableCountry2(i){

	$('.dataTables_scrollHead table th.data-table-header').eq(i).removeClass('display-disabled');
	$('.dataTables_scrollHead table th.data-table-header:eq('+i+') *').attr("disabled", false);
	
	var temp = $('.dataTables_scrollBody table tr');
	var row = $('.dataTables_scrollBody table tr').length;
	for(var j = 0; j < row; j++){
		temp.eq(j).find('div.div-loi-qty-left').eq(i*5).removeClass('display-disabled');
		temp.eq(j).find('div.div-loi-qty-left').eq(i*5+1).removeClass('display-disabled');
		temp.eq(j).find('div.div-loi-qty-left').eq(i*5+2).removeClass('display-disabled');
		temp.eq(j).find('div.div-loi-qty-left').eq(i*5+3).removeClass('display-disabled');
		temp.eq(j).find('div.div-loi-qty-left').eq(i*5+4).removeClass('display-disabled');
	}
	$('.dataTables_scrollBody table div.div-loi-qty-left.display-disabled').find('input').attr("disabled", true);
	$('.dataTables_scrollBody table div.div-loi-qty-left:not(.display-disabled').find('input').attr("disabled", false);
	
	if($('.dataTables_scrollHead table th.data-table-header').not('.display-disabled').length == 1){
		$('.combobox-dropdown > *').removeClass('display-disabled').attr("disabled", false);
	}
	else {
		$('.combobox-dropdown > *').addClass('display-disabled').attr("disabled", true).on('click');
	}
}

function enableCountry() {

	if (pad0021List && pad0021List.length && countryObject && countryObject.length) {
		var length = countryObject.length;
		for (var i = 0; i <length && i< pad0021List.length; i++){
			var j = 0;
			for (; j < pad0021List.length; j++){
				if(pad0021List[j].countryCd == countryObject[i].countryCd
						&& ( pad0021List[i].exclusiveFg != 1 
								|| pad0021List[i].updatedUser != userInfo.userId) ){
					break;
				}
			}
			if(j == pad0021List.length){
				enableCountry2(i);
			}
		}
	}
}

function callAjaxUpdateCountryData(params, pos, actionName, methodType) {
	loading_ajax();
	$.ajax({
		cache: false,
		url : actionName,
		type : methodType,
		data: params,
		dataType : "json",
		success : function(response) {
			close_loading_ajax();
			pad0021List = response.pad0021List;
			errorFg = response.errorFg;
			lockedcountries = response.lockedcountries;
			itemObject = response.itemNameCodeList;
			
			if(errorFg == "already"){
				isSwitched = false;
				showConfirmDialog("switch","",pos,{"position":"200"});
			}
			else if (errorFg == "false") {
				isSwitched = false;
				$("#contentMessage").text(getMessageWithParams("CM-0014"));
				$("#modalInfo").modal("show");
				
				var enablePosition = getEnablePosition($('.dataTables_scrollHead table th.data-table-header').not('.display-disabled').find('a').text());
				if (enablePosition >= 0 && enablePosition < countryObject.length){
					reloadTable(enablePosition);
				}
			} 
			else if (errorFg == "true"){
				isSwitched = false;
				reloadTable(pos);
				positionUpdate = pos;
			}
			
		},
		error:function(jqXhr, textStatus, errorThrown){
			close_loading_ajax();
		}
	});
}

function callAjaxUpdateCountryData2(params, actionName, methodType) {
	loading_ajax();
    $.ajax({
    		cache: false,
            url : actionName,
            type : methodType,
            data: params,
            dataType : "json",
            success : function(response) {
            	close_loading_ajax();
    			errorFg = response.errorFg;
            },
            error:function(jqXhr, textStatus, errorThrown){
                close_loading_ajax();
            }
    });
}

function callAjaxCheckLockedDataByAnother(params, actionName, methodType) {
	var flag = false;
	loading_ajax();
	$.ajax({
		cache: false,
		url : actionName,
		type : methodType,
		data: params,
		dataType : "json",
		async: false,
		success : function(response) {
			close_loading_ajax();
			errorFg = response.errorFg;
			
			if(errorFg === "lockedByAnother"){
				flag = true;
			}
			else {
				flag = false;
			}
		},
		error:function(jqXhr, textStatus, errorThrown){
			close_loading_ajax();
			flag = false;
		}
	});
	return flag;
}

function callAjaxCheckLockedData(actionName, methodType) {
	var flag = false;
	loading_ajax();
    $.ajax({
    		cache: false,
            url : actionName,
            type : methodType,
            dataType : "json",
            async: false,
            success : function(response) {
            	close_loading_ajax();
    			errorFg = response.errorFg;
    			
    			if(errorFg === "locked"){
    				flag = true;
    			}
    			else {
    				flag = false;
				}
            },
            error:function(jqXhr, textStatus, errorThrown){
                close_loading_ajax();
                flag = false;
            }
    });
    return flag;
}

function disabledAddButton(addFlag){
	if (addFlag) {
		$('.btn-add').attr("disabled", false);
	} else {
		$('.btn-add').attr("disabled", true).on('click');
	}
}

function disabledSaveButton(addFlag){
	var length = $('.dataTables_scrollHead table th.data-table-header').not('.display-disabled').find('a').length;
	if (length > 0) {
		$('.dataTables_scrollHead table th.data-table-header').not('.display-disabled').find('a').attr("disabled", false);
	} else {
		$('.dataTables_scrollHead table th.data-table-header').not('.display-disabled').find('a').attr("disabled", true).on('click');
	}
}

function replaceNull(value) {
	return (typeof value === "undefined" || value === "" || value === null || value === "null") ? "" : value.toString().replace("null", "").replace("undefined", "");
}

function getEnablePosition(country){
	var position = -1;
	for (var i = 0; i <pad0021List.length; i++){
		if(pad0021List[i].countryCd == country){
			position = i;
			break;
		}
	}
	
	return position;
}

function checkLockForSave(params){
	var data = JSON.parse(params.paramList).arr[0];
	for(var i = 0; i < data.itemCd.length; i++){
		if(data.categoryCd[0] != data.categoryCdFromDb[0] || data.itemCd[0] != data.itemCdFromDb[0]){
			return true;
		}
	}
	return false;
}

function reloadTable(pos){
	// Delete the rows
	$('#view-main *').remove();
	var tempTable = $('<table class="order-column row-border hover data-table" id="resultTable" >' +
    						'<thead></thead>' +
    						'<tbody></tbody>' +
    					'</table>');
	$('#view-main').append(tempTable);
	resultTable = undefined;
	
	loadData();
	$(".entryDateFrom").change(function () {
		var value = $(this).val().trim();
		$(this).next().val(value);
		var entryDate;
		
		if(!(typeof value === "undefined" || value === null || value === "null" || value === "")){
			var dateArray = value.split("-");
			var entryDate = replaceNull(dateArray[1] + '-' + dateArray[2]);
			$(this).val(entryDate);
		}
	 });
	
	$('.dataTables_scrollHead table th.data-table-header').addClass('display-disabled');
	$('.dataTables_scrollBody table div.div-loi-qty-left').addClass('display-disabled');
	enableCountry2(pos);
}

