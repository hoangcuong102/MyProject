var tableActiveItem;
var tableAllItem;
var tableInactiveItem;
var listActiveItem = [];
var listAllItem = [];
var listInactiveItem = [];
var selectAllItem = [];
var selectActiveItem = [];
var selectInactiveItem = [];
var isChange = false;
var isAllItem = false;

$(document).ready(function() {

	$('#confirmRedirectMenu').val(true);
	$(".left-side").hide();

	tableActiveItem = tableInit("#activeItem");
	tableAllItem = tableInit("#allItem");
	tableInactiveItem = tableInit("#inactiveItem");

	$("#countriesSelect").change(function() {
		removeError();
    	var countryCd = $("#countriesSelect").val().trim();
    	var categoryCd = $("#category").val().trim();
    	getListItem(countryCd, categoryCd);
    });
	$("#category").change(function() {
		removeError();
    	var countryCd = $("#countriesSelect").val().trim();
    	var categoryCd = $("#category").val().trim();
    	getListItem(countryCd, categoryCd);
    });
	
	fixHeightDivBody();
	setTabIndex();
});

/**
 * Item object
 * 
 * @param itemCd
 * @param itemName
 * @param active
 * @returns
 */
function Item(itemCd, itemName, active) {
	this.itemCd = itemCd;
	this.itemName = itemName;
	this.active = active;
}

/**
 * find item in listData
 * 
 * @param listData
 * @param itemCd
 * @returns item
 */
function findItem(listData, itemCd) {
	var itemReturn;
	$.each(listData, function(i, item) {
		var code = item.itemCd + "";
		if (code == itemCd) {
			itemReturn = item;
			listData.splice(i,1);
			return false;
		}
	});
	return itemReturn;
}

/**
 * Move item from all item to active item
 * 
 * @returns
 */
function allToActive() {
	// If selectAllItem is not empty
	if (selectAllItem.length > 0){
		for (var i = 0; i < selectAllItem.length; i++) {
			var item = findItem(listAllItem, selectAllItem[i]);
			if (typeof item != 'undefined') {
				//listAllItem.splice(item, 1);
				listActiveItem.push(item);
			}
	
		}
		selectAllItem = [];
		selectActiveItem = [];
		drawDatatable(tableActiveItem, listActiveItem, 1);
		drawDatatable(tableAllItem, listAllItem, null);
		isChange = true;
	}
}

/**
 * Move item from all item to inactive item
 * 
 * @returns
 */
function allToInactive() {
	// If selectAllItem is not empty
	if (selectAllItem.length > 0){
		for (var i = 0; i < selectAllItem.length; i++) {
			var item = findItem(listAllItem, selectAllItem[i]);
			if (typeof item != 'undefined') {
				//listAllItem.splice(item, 1);
				listInactiveItem.push(item);
			}
	
		}
		selectAllItem = [];
		selectInactiveItem = [];
		drawDatatable(tableInactiveItem, listInactiveItem, 0);
		drawDatatable(tableAllItem, listAllItem, null);
		isChange = true;
	}
}

/**
 * Move item from all active to all item
 * 
 * @returns
 */
function activeToAll() {
	// If selectActiveItem is not empty
	if (selectActiveItem.length > 0){
		for (var i = 0; i < selectActiveItem.length; i++) {
			var item = findItem(listActiveItem, selectActiveItem[i]);
			if (typeof item != 'undefined') {
				//listActiveItem.splice(item, 1);
				listAllItem.push(item);
			}
	
		}
		selectAllItem = [];
		selectActiveItem = [];
		drawDatatable(tableActiveItem, listActiveItem, 1);
		drawDatatable(tableAllItem, listAllItem, null);
		isChange = true;
	}
}

/**
 * Move item from inactive item to all item
 * 
 * @returns
 */
function inactiveToAll() {
	// If selectActiveItem is not empty
	if (selectInactiveItem.length > 0){
		for (var i = 0; i < selectInactiveItem.length; i++) {
			var item = findItem(listInactiveItem, selectInactiveItem[i]);
			if (typeof item != 'undefined') {
				//listInactiveItem.splice(item, 1);
				listAllItem.push(item);
			}
	
		}
		selectAllItem = [];
		selectInactiveItem = [];
		drawDatatable(tableInactiveItem, listInactiveItem, 0);
		drawDatatable(tableAllItem, listAllItem, null);
		isChange = true;
	}
}

/**
 * Init datatable
 * 
 * @param idTable
 * @returns table
 */
function tableInit(idTable) {
	var table = $(idTable).DataTable({
		"columnDefs" : [ {
			"targets" : [ 0, 1 ],
			"orderable" : false
		} ],
		scrollY : calcDataTableHeight(10),
		scrollCollapse : true,
		paging : false,
		ordering: false,
		"asStripeClasses": [],
		"aoColumns" : [
			{"width" : "10%", "sClass" : "t-center width-10" },
			{"width" : "90%", "sClass" : "t-left width-90" },
			{"width" : "0%", "sClass" : "t-left hidden-column" },
			{"width" : "0%", "sClass" : "t-left hidden-column" }
		],
		"fnInitComplete" : function() {
			$('.dataTables_scrollBody').perfectScrollbar();
		},
		"fnDrawCallback": function ( oSettings ) {
		    $(oSettings.nTHead).hide();
		},
		"dom" : '<"top"l>rt<"bottom"p><"clear">'
	});
	return table;
}

/**
 * edit Item
 */
function editItem(itemCode, itemName, activeFg) {
	if (activeFg == null){
		if (findSelect(itemCode, selectAllItem) === false) {
			selectAllItem.push(itemCode);
		}
	}
	
	if (activeFg == 1){
		if (findSelect(itemCode, selectActiveItem) === false) {
			selectActiveItem.push(itemCode);
		}
	}
	
	if (activeFg == 0){
		if (findSelect(itemCode, selectInactiveItem) === false) {
			selectInactiveItem.push(itemCode);
		}
	}
}

// false if don't has in list
function findSelect(code, selectItem) {
	var check = false;
	for (var i = 0; i < selectItem.length; i++) {
		if (selectItem[i] === code) {
			selectItem.splice(i, 1);
			check = true;
			break;
		}

	}
	return check;
}

/**
 * insert data
 * 
 * @param target
 * @param data
 * @returns
 */
function insertData(target, data) {
	$(target).html(data);
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
 * add class row-checked when selected row
 * 
 * @returns
 */
function eventRowChecked() {
	$(".row-check").on("change", function() {
		var isChecked = $(this).prop("checked");
		if (isChecked) {
			$(this).closest("tr").find("td").each(function() {
				$(this).addClass("row-checked");
			});
		} else {
			$(this).closest("tr").find("td").each(function() {
				$(this).removeClass("row-checked");
			});
		}
	});
}

/**
 * fix left margin div-active-item
 * @returns
 */
function fixLeftMargin(){
	var marginLeft = Math.ceil(parseFloat($("#div-body-first").css("margin-left"))/2);
	$("#div-body-first").css("margin-left", marginLeft);
}

/**
 * fixHeightDivBody
 * @returns
 */
function fixHeightDivBody(){
	$("div.dataTables_scroll div.dataTables_scrollBody").css("height", calcDataTableHeight(10));
	$("div.dataTables_scroll div.dataTables_scrollBody").css("max-height", calcDataTableHeight(10));
}

/**
 * get all item, active item, inactive item
 * 
 * @param countryCd
 * @param categoryCd
 * @returns
 */
function getListItem(countryCd, categoryCd){
	if (countryCd == undefined || countryCd == null || countryCd == "" ||
			categoryCd == undefined || categoryCd == null || categoryCd == ""){
		return false;
	}
	callAjaxGetItem(countryCd, categoryCd);
}

/**
 * empty list selectItem
 * 
 * @returns
 */
function resetSelectItem(){
	selectAllItem = [];
	selectActiveItem = [];
	selectInactiveItem = [];
	isChange = false;
	isAllItem = false;
}

/**
 * call ajax when click button save
 * @param params
 * @param actionName
 * @param methodType
 * @param page
 * @returns
 */
function callAjaxSaveItem(params, actionName, methodType, page){
	loading_ajax();
    $.ajax({
    		cache: false,
            url : actionName,
            type : methodType,
            data: params,
            dataType : "json",
            traditional: true,
            success : function(response) {
            	close_loading_ajax();
            	listActiveItem = [];
            	listAllItem = [];
            	listInactiveItem = [];

            	if (response.allItem != null && response.allItem != undefined){
            		listAllItem = response.allItem;
            	}
            	drawDatatable(tableAllItem, response.allItem, null);
            	
            	if (response.activeItem != null && response.activeItem != undefined){
            		listActiveItem = response.activeItem;
            	}
            	drawDatatable(tableActiveItem, response.activeItem, 1);
            	
            	if (response.inactiveItem != null && response.inactiveItem != undefined){
            		listInactiveItem = response.inactiveItem;
            	}
            	drawDatatable(tableInactiveItem, response.inactiveItem, 0);
            	
            	resetSelectItem();
            },
            error:function(jqXhr, textStatus, errorThrown){
                alert(textStatus);
                close_loading_ajax();
            }
    });
}

/**
 * call ajax
 * get all item, active item, inactive item
 * 
 * @param countryCd
 * @param categoryCd
 * @returns
 */
function callAjaxGetItem(countryCd, categoryCd){
	var params = {};
	params.countryCd = countryCd;
	params.categoryCd = categoryCd;
	loading_ajax();
    $.ajax({
    		cache: false,
            url : "sab0031_01",
            type : "POST",
            data: params,
            dataType : "json",
            success : function(response) {
            	close_loading_ajax();
            	
            	listAllItem = [];
            	if (response.allItem != null && response.allItem != undefined){
            		listAllItem = response.allItem;
            	}
            	drawDatatable(tableAllItem, response.allItem, null);
            	
            	listActiveItem = [];
            	if (response.activeItem != null && response.activeItem != undefined){
            		listActiveItem = response.activeItem;
            	}
            	drawDatatable(tableActiveItem, response.activeItem, 1);
            	
            	listInactiveItem = [];
            	if (response.inactiveItem != null && response.inactiveItem != undefined){
            		listInactiveItem = response.inactiveItem;
            	}
            	drawDatatable(tableInactiveItem, response.inactiveItem, 0);
            	
            	resetSelectItem();
            },
            error:function(jqXhr, textStatus, errorThrown){
                alert(textStatus);
                close_loading_ajax();
            }
    });
}

/**
 * draw table
 * 
 * @param tableId
 * @param listItem
 * @returns
 */
function drawDatatable(table, listData, activeFg){
	table.clear();
	if (listData == undefined || listData == null){
		table.draw();
		return false;
	}
	var dataSet = [];
	var length = listData.length;
	if (length > 0) {
		for (var i = 0; i < length; i++) {
			var index = i + 1;
			var dataRow = [];
			var itemName = replaceNull(listData[i].itemName);
			//var activeFg = replaceNull(listData[i].activeFg);
			var itemCode = replaceNull(listData[i].itemCd);
			var checkbox = '<input class="row-check" type="checkbox" name="item" value="female" onclick="editItem(\''
				+ itemCode + '\',\'' + itemName + '\',' + activeFg + ');">';
			dataRow.push(checkbox, '<span class="text-right">' + itemName + '</span>', activeFg, itemCode);
			dataSet.push(dataRow);
		}
		table.rows.add(dataSet);
	}
	table.draw();
}

/**
 * validate when click button save
 * @returns
 */
function validate(){
	removeError();
	var flag = true;
	var countryCd = $("#countriesSelect").val().trim();
	var category = $("#category").val().trim();
	if (countryCd == undefined || countryCd == null || countryCd == ""){
		/*$("#countriesSelect").addClass("border-error");
		$("#countriesSelect").parent().append(generateHtmlError("EB-0007", ""));*/
		showError("country");
		flag = false;
	}
	
	if (category == undefined || category == null || category == ""){
		/*$("#category").addClass("border-error");
		$("#category").parent().append(generateHtmlError("EB-0003", ""));*/
		showError("category");
		flag = false;
	}
	return flag;
}

/**
 * remove all error
 * 
 * @returns
 */
function removeError(){
	$("#countriesSelect").removeClass("border-error");
	$("#category").removeClass("border-error");
	$(".td-error").empty();
	$("#tr-error").hide();
	$("#countriesSelect").css("width", "auto");
	$("#countriesSelect").parent().css("width", "auto");
	$("#category").parent().css("width", "auto");
}

/**
 * show error when field is blank
 * field = cuontry or category
 * @param field
 * @returns
 */
function showError(field){
	$("#tr-error").show();
	if (field == "country"){
		$("#countriesSelect").addClass("border-error");
		$("#td-error-country").append(generateHtmlError("EB-0007", ""));
	}
	
	if (field == "category"){
		$("#category").addClass("border-error");
		$("#td-error-category").append(generateHtmlError("EB-0003", ""));
	}
	
	$("#td-error-country").css("width", "auto");
	$("#td-error-category").css("width", "auto");
	
	var widthCountry = $("#countriesSelect").parent().css("width");
	var widthCategory = $("#category").parent().css("width");
	var widthCountryError = $("#td-error-country").css("width");
	var widthCategoryError = $("#td-error-category").css("width");
	
	if (parseInt(widthCountry) < parseInt(widthCountryError)){
		$("#countriesSelect").parent().css("width", widthCountryError);
	} else {
		$("#td-error-country").css("width", widthCountry);
	}
	
	if (parseInt(widthCategory) < parseInt(widthCategoryError)){
		$("#category").parent().css("width", widthCategoryError);
	} else {
		$("#td-error-category").css("width", widthCategory);
	}
}
/**
 * save item
 * @returns
 */
function doSave(){
	if (validate()){
		showConfirmDialog("save","","",{"position":"200"});
	}
}

/**
 * close screen
 * @returns
 */
function doClose(){
	if (listAllItem.length == 0){
		if (isChange){
			showConfirmDialog("close","","",{"position":"200"});
		} else {
			doEventDialog01("close", "");
		}
	} else {
		var contentConfirm = getMessageWithParams("IM3-0001","");
		document.getElementById("contentConfirmPending").innerHTML = contentConfirm;
		$("#modalConfirmPending").modal("show");
	}
}
/**
 * Method will call when Click button yes on dialog confirm
 * @param idEvent Event Save
 * @param element Dialog confirm
 */
function doEventDialog01(idEvent, element){
	// In case save
	if (idEvent == "save"){
		var params = {};
		var countryCd = $("#countriesSelect").val().trim();
    	var categoryCd = $("#category").val().trim();
    	var listAllItemCd = [];
		var listActiveItemCd = [];
		var listInactiveItemCd = [];
		
		for (var i=0; i<listAllItem.length; i++){
			listAllItemCd.push(listAllItem[i].itemCd);
		}
		for (var i=0; i<listActiveItem.length; i++){
			listActiveItemCd.push(listActiveItem[i].itemCd);
		}
		for (var i=0; i<listInactiveItem.length; i++){
			listInactiveItemCd.push(listInactiveItem[i].itemCd);
		}
		
		params.countryCd = countryCd;
		params.categoryCd = categoryCd;
		params.listAllItemCd = listAllItemCd;
		params.listActiveItemCd = listActiveItemCd;
		params.listInactiveItemCd= listInactiveItemCd;
		callAjaxSaveItem(params, "sab0031_02", "POST", "");
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
 * showConfirmDialog
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
		messageId = "IM2-0004";
	} else if (idEvent == "save") {
		messageId = "IM2-0002";
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
	} else if (idEvent == "updateRecord") {
		messageId = "IM-0002";
	} else if (idEvent == "add") {
		messageId = "IM-0001";
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
 * close popup pending item
 * @returns
 */
function doCloseConfirmPending(){
	$("#modalConfirmPending").modal("hide");
}

/**
 * set tab index
 * @returns
 */
function setTabIndex(){
	$("#countriesSelect").attr("tabindex", "1");
	$("#category").attr("tabindex", "2");
	$(".btn-save").attr("tabindex", "3");
	$(".btn-close").attr("tabindex", "4");
	
	$("#t-left").attr("tabindex", "5");
	$("#u-left").attr("tabindex", "6");
	$("#t-right").attr("tabindex", "7");
	$("#u-right").attr("tabindex", "8");
	$(".btn-logout").attr("tabindex", "9");
	$("#countriesSelect").first().focus();
	
	// Set again first focus for menu screen
	$('.header').keydown(function(e){
		if($('.btn-logout').is(":focus") && (e.which || e.keyCode) == 9) {
			e.preventDefault();
			$("#countriesSelect").first().focus();
		}

		if($('.btn-logout').is(":focus") && (e.which || e.keyCode) == 13) {
			e.preventDefault();
			$(".btn-logout").click();
	    }
	});
}