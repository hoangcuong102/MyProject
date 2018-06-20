var listItemObject = {};
var table;
var checkInfoHeader = true;
var recordId, deleteRowId, recordIdUpdate = 0;
var isSelectionInput = false;
var isChange = false;

$(document).ready(function() {
	// $("#tableData").addClass("hidden");
	//console.log(new Date());
	getListItem();
	//console.log(new Date());
	insertData("#countries", drawCountry());
	insertData("#countriesPrice", drawCountruiesPrice());
	$('#category').append($('<option>', {
		value : "",
		text : ""
	}));
	$.each(categoryObject, function(i, item) {
		$('#category').append($('<option>', {
			value : item.categoryCd,
			text : item.categoryCd
		}));
	});
	$('#confirmRedirectMenu').val(true);
	$(".left-side").hide();
	table = $('#resultTable').DataTable({
		"columnDefs" : [ {
			"targets" : [ 0, 5, 6, 7 ],
			"orderable" : false
		} ],
		"lengthMenu" : [ 10, 20, 50, 100 ],
		"sScrollY" : calcDataTableHeight(30),
		"bScrollCollapse" : true,
		"scrollX" : false,
		"order" : [ 1, 'asc' ],
		//ordering: false,
		"pageLength" : 20,
		"asStripeClasses": [],
		"aoColumns" : [
			{"sClass" : "t-center" },
			{"sClass" : "t-left padding-right-orderable", },
			{"sClass" : "t-left padding-right-orderable", },
			{"sClass" : "t-left padding-right-orderable", },
			{"sClass" : "hiddenBrand", },
			{"sClass" : "hiddenAddMemory", },
			{"sClass" : "hiddenNoUse", },
			{"sClass" : "t-center" }
		],
		"fnInitComplete" : function() {
			$('.dataTables_scrollBody').perfectScrollbar();
		},
		"dom" : '<"top"l>rt<"bottom"ip><"clear">'
	});
	
	$("#category").change(function(event) {
		var categoryCd = $("#category").val().trim();
		if (categoryCd == "04DESKTOP01" || categoryCd == "03LAPTOP" || categoryCd == "02LAPTOP" || categoryCd == "01MOBILE"){
			$(".td-brand").removeClass("brand-field");
			enableCheckbox(1);
		} else {
			$(".td-brand").addClass("brand-field");
			disableCheckbox(1);
		}
		isChange = true;
	});
	validateAlphanumberic();
	validateNumber1();
	setTabIndex();
	$(".btn-delete").css('background-color','');
	$(".btn-delete").attr("disabled",true);
	$(".btn-save").attr("disabled",true);
	
	$(".alphanumberic").change(function(event) {
		isChange = true;
	});
	$(".numberic").change(function(event) {
		isChange = true;
	});
	//console.log(new Date());
	//zoom page
	$(window).resize(function() {
		//console.log($(window).height());
		$('.dataTables_scrollBody').css('max-height', calcDataTableHeight(30));
	});
});

function getListItem() {
	loading_ajax();
	$.ajax({
		cache: false,
		url : contextPath + '/fab001/sab0011_01',
		type : "GET",
		data : "",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		success : function(response) {
			close_loading_ajax();
			listItemObject = response.listItem;
			drawData(listItemObject,0);
		},
		error : function(jqXhr, textStatus, errorThrown) {
			close_loading_ajax();
		}
	});
}

/**
 * Draw table after search
 */
function drawData(listData, page) {//console.log("start draw"+new Date());
	var dataSet = [];
	var length = listData.length;
	/*var listItemCd = [];*/
	table.clear();
	if (length > 0) {
		var rowId = 0;
		for (var i = 0; i < length; i++) {
			var index = i + 1;
			var dataRow = [];
			var select = '<input type="radio" name="item" value="female" onclick="selectRecord('+ rowId +')"/>';
			var itemCode = replaceNull(listData[i].itemCd);
			/*if (listItemCd.indexOf(itemCode) > -1){
				continue;
			}
			listItemCd.push(itemCode);*/
			var itemName = replaceNull(listData[i].itemName);
			var category = replaceNull(listData[i].categoryCd);
			var itemBrand = replaceNull(listData[i].itemBrand);
			var addMemory = replaceNull(listData[i].warningFg);
			var noUse = replaceNull(listData[i].noUseFg);
			var price = "";
			var priceList = createPriceList(listData, i);
			price = drawPriceEachCountry(priceList.text);
			i = priceList.flag;
			dataRow.push(select, itemCode, itemName, category, itemBrand, addMemory, noUse, price);
			dataSet.push(dataRow);
			rowId++;
		}
		table.rows.add(dataSet);
	}
	table.draw();
	$("#tableData").removeClass("hidden");
	setTabIndex();
	$('#resultTable').dataTable().fnPageChange(page);
	//console.log("end draw"+new Date());
}

/**
 * edit item
 * @param rowId
 * @returns
 */
function editItem(rowId){
	checkInfoHeader = checkHeader();
	if (isChange){
		showConfirmDialogUpdateRecord("IM-0002");
		isChange = false;
		return false;
	}
	
	removeError();
	recordId = rowId;
	var data = table.rows(rowId).data();
	var item = data[0];
	var categoryCd = item[3];
	$("#itemCode").val(item[1].trim());
	$("#itemName").val(item[2]);
	$("#category").val(item[3]);
	//	$("#itemBrand").val(item[4]);
	var priceList = $(item[7]);
	$("input.price-country").each(function(index) {
		var price = $(priceList[index]);
		$(this).val(price.text().trim());
	});
	enableCheckbox(0);
	if (item[6] == '1'){
		$("#noUse").prop("checked", true);
	} else {
		$("#noUse").prop("checked", false);
	}
	if (categoryCd == "04DESKTOP" || categoryCd == "02LAPTOP" || categoryCd == "03LAPTOP" || categoryCd == "01MOBILE"){
		$(".td-brand").removeClass("brand-field");
		$("#itemBrand").val(item[4]);
		enableCheckbox(1);
		if (item[5] == '1'){
			$("#addMemory").prop("checked", true);
		} else {
			$("#addMemory").prop("checked", false);
		}
	} else {
		$(".td-brand").addClass("brand-field");
		disableCheckbox(1);
	}
	// Button Update to Save
	//$(".btn-save").text("Save");
	$(".btn-save").removeAttr("onclick");
	$(".btn-save").attr("onclick", "doSave();");
	
	$("#addMemory, #noUse").change(function(event) {
		isChange = true;
	});
}

/**
 * event when selected record
 * @param rowId
 * @returns
 */
function selectRecord(rowId){
	if (recordId == undefined || recordId == null || recordId != rowId){
		recordIdUpdate = rowId;
		// Enable button delete
		$(".btn-delete").removeAttr("disabled");
		$(".btn-delete").attr("onclick", "doDelete("+ rowId +");");
		
		//$(".btn-save").text("Update");
		// Enable button Update
		$(".btn-save").removeAttr("disabled");
		//$(".btn-save").removeAttr("onclick");
		//$(".btn-save").attr("onclick", "editItem("+ rowId +");");
		editItem(rowId);
	}
}

/**
 * checkHeader
 * @returns false if infomation in header
 */
function checkHeader(){
	var flag = true;
    $("input.alphanumberic").each(function(index) {
    	var data = $(this).val().trim();
    	if (data!=undefined && data!=""){
    		flag = false;
    		return flag;
    	}
    });
    var category = $("#category").val().trim();
    if (category!=undefined && category!=""){
    	flag = false;
    	return flag;
    }
    $("input.numberic").each(function(index) {
    	var data = $(this).val().trim();
    	if (data!=undefined && data!="" && data!= "0.00"){
    		flag = false;
    		return flag;
    	}
    });
    return flag;
}

/**
 * remove infomation in header
 * @returns
 */
function removeInfoHeader(){
	$("input.alphanumberic").val("");
	$("#category").val("");
    $("input.numberic").val("");
    disableCheckbox(0);
    disableCheckbox(1);
}

/**
 * 
 * @returns
 */
function drawCountry() {
	var text = "";
	var widthDiv = 100/countryObject.length;
	for (i = 0; i < countryObject.length; i++) {
		text += '<div class="col-md-111" style="width:'+ widthDiv +'%">' + countryObject[i].countryCd
				+ '</div>';
	}
	return text;
}

function drawCountruiesPrice() {
	var text = "";
	for (i = 0; i < countryObject.length; i++) {
		text += '<div class="col-md-1"><div class="col-md-1a"><b>'
				+ countryObject[i].countryCd
				+ '</b></div><div class="col-md-1b"><input class="form-control text-right price-country numberic" type="text" id="price_'
				+ countryObject[i].countryCd + '" maxlength="9" value="0.00"/></div></div>';
	}
	return text;
}

/**
 * draw list price in table
 * 
 * @param data
 * @returns
 */
function drawPriceEachCountry(data) {
	var text = "";
	var widthDiv = 100/countryObject.length;

	for (var i = 0; i < countryObject.length; i++) {
		/*var unitPrice = replaceNull(data[i]);
		var price;
		if (unitPrice == ""){
			price = "0.00";
		} else {
			price = parseFloat(unitPrice).toFixed(2);
		}*/
		var unitPrice = convertNumberToString7v2(data[i]);
		text += '<div class="col-md-111 text-right display-content-middle" style="width:'+ widthDiv +'%">' + unitPrice + '</div>';
	}
	return text;
}

/**
 * create price list on row
 * @param data
 * @param index
 * @returns
 */
function createPriceList(data, index) {
	var text = [];
	var flag = index;
	for (var i = 0; i < countryObject.length; i++) {
		text[i] = "";
	}
	for (var i = index; i < (data.length); i++) {
		/*if(i >= data.length){
			break;
		}*/
		if(i == index){
			for (var j = 0; j < text.length; j++) {
				if (countryObject[j].countryCd==data[i].countryCd){
					text[j] = data[i].unitPrice;
					flag = i;
					break;
				}
			}
		} else {
			if (data[index].itemCd==data[i].itemCd && data[index].categoryCd==data[i].categoryCd){
				for (var j = 0; j < text.length; j++) {
					if (countryObject[j].countryCd==data[i].countryCd){
						text[j] = data[i].unitPrice;
						flag = i;
						break;
					}
				}
			} else{
				break;
			}
		}
	}
	var result = {};
	result.text = text;
	result.flag = flag;
	return result;
}

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
 * if item code selected enable check box No Use
 * if [Category]: "01DESKTOP,02LAPTOP,03LAPTOP,01MOBILE" enable check box Additional Memory]
 * @returns
 */
function enableCheckbox(type){
	//No use
	if (type==0){
		$("#noUse").removeAttr("disabled");
	}
	
	//Additional Memory
	if (type==1){
		$("#addMemory").removeAttr("disabled");
	}
}

/**
 * disable check box
 * @returns
 */
function disableCheckbox(type){
	//No use
	if (type==0){
		$("#noUse").attr("disabled",true);
		$("#noUse").prop("checked", false);
	}
	
	//Additional Memory
	if (type==1){
		$("#addMemory").attr("disabled",true);
		$("#addMemory").prop("checked", false);
	}
}

/**
 * set tab index
 * @returns
 */
function setTabIndex(){
	$("#itemCode").attr("tabindex", "1");
	$("#itemName").attr("tabindex", "2");
	$("#category").attr("tabindex", "3");
	$("#itemBrand").attr("tabindex", "4");
	$("#countriesPrice input").eq(0).attr("tabindex", "5");
	$("#addMemory").attr("tabindex", "6");
	$("#noUse").attr("tabindex", "7");
	$("input:radio").eq(0).attr("tabindex", "8");
	$(".btn-add").attr("tabindex", "9");
	$(".btn-save").attr("tabindex", "10");
	$(".btn-close").attr("tabindex", "11");
	$(".btn-delete").attr("tabindex", "12");
}

/**
 * validate infomation header
 * @param action add or save
 * @returns
 */
function validate(action){
	removeError();
	var flag = true;
    /*$("input.alphanumberic").each(function(index) {
    	var data = $(this).val().trim();
    	if (data==undefined || data==""){
    		flag = false;
    		$(this).append(generateHtmlError("EB-0003", ""));
    	}
    });*/
	var itemCd = $("#itemCode").val().trim();
    if (itemCd==undefined || itemCd==""){
    	flag = false;
    	$("#itemCode").addClass("border-error");
    	$("#error1").show();
    	$("#itemCdError").append(generateHtmlError("EB-0001", ""));
    }
    
    var itemName = $("#itemName").val().trim();
    if (itemName==undefined || itemName==""){
    	flag = false;
    	$("#itemName").addClass("border-error");
    	$("#error2").show();
    	$("#itemNameError").append(generateHtmlError("EB-0002", ""));
    }
    
    var category = $("#category").val().trim();
    if (category==undefined || category==""){
    	flag = false;
    	$("#category").addClass("border-error");
    	$("#error3").show();
    	$("#categoryError").append(generateHtmlError("EB-0003", ""));
    }
    
    var flagUnitPrice = true;
    $("input.numberic").each(function(index) {
    	var data = $(this).val().trim();
    	if (data==undefined || data=="" || data=="."){
    		flag = false;
    		flagUnitPrice = false;
    		$(this).addClass("border-error");
    	}
    });
    
    if (!flagUnitPrice){
    	$("#error4").show();
    	$("#unitPriceError").append(generateHtmlError("EB-0004", ""));
    }
    
    if (!flag){
    	return flag;
    }
    
    if (action == "add"){
    	if (checkItem(itemCd)){
    		flag = false;
    		$("#itemCode").addClass("border-error");
        	$("#error1").show();
        	$("#itemCdError").append(generateHtmlError("EB-0005", ""));
    	}
    }
    
    if (action == "save"){
    	if (!checkItem(itemCd)){
    		flag = false;
    		$("#itemCode").addClass("border-error");
        	$("#error1").show();
        	$("#itemCdError").append(generateHtmlError("EB-0006", ""));
    	}
    }
    return flag;
}

/**
 * remove all error
 * @returns
 */
function removeError(){
	$("#itemCode").removeClass("border-error");
	$("#itemName").removeClass("border-error");
	$("#category").removeClass("border-error");
	$("input.numberic").each(function(index) {
		$(this).removeClass("border-error");
	});
	$("#itemCdError").empty();
	$("#itemNameError").empty();
	$("#categoryError").empty();
	$("#unitPriceError").empty();
	$("#error1, #error2, #error3, #error4").hide();
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
	$("#itemName").unbind("keypress");
	$("#itemName").keypress(function(e){
		var key = e.keyCode;
		if (!checkAlphanumeric(String.fromCharCode(key), true)){
			//alert(String.fromCharCode(key));
			return false;
		}
	});
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
 * validateNumber unit price input
 * @returns
 */
function validateNumber(){
	$(".numberic").keypress(function(e){console.log(this.selectionStart);
		var key = e.keyCode;
	    if (!checkNumeric(String.fromCharCode(key))){
	      	//alert(String.fromCharCode(key));
	    	return false;
	    }
	    var input = $(this).val().trim();
		var flagDot = false;
		var flagMaxleng = false;
		if (input.indexOf(".") > -1){
			flagDot = true;
		}
	    if (String.fromCharCode(key) == "."){
	    	if (flagDot){
		    	return false;
		    } else {
		    	if (input.length > 6){
		    		return false;
		    	}
		    }
	    } else {
	    	if (flagDot){
		    	var indexDot = input.indexOf(".");
		    	if ((input.length - indexDot -1) > 1 ){
		    		return false;
		    	}
		    } else {
		    	if (input.length >= 6){
		    		return false;
		    	}
		    }
	    }
	    
	});
	
	$(".numberic").select(function(event) {
    	//console.log("a");
		isSelectionInput = true;
    });
}

function validateNumber1(){
	$(".numberic").keypress(function(e){console.log(this.selectionStart);
		var key = e.keyCode;
	    if (!checkNumeric(String.fromCharCode(key))){
	    	return false;
	    } else {
	    	if (isSelectionInput){
	    		if (String.fromCharCode(key) != "."){
	    			isSelectionInput = false;
	    			return true;
	    		}
	    	}
	    }
	    var input = $(this).val().trim();
		var flagDot = false;
		var flagMaxleng = false;
		if (input.indexOf(".") > -1){
			flagDot = true;
		}
	    if (String.fromCharCode(key) == "."){
	    	if (this.selectionStart == 0){
	    		return false;
	    	}
	    	if (flagDot){
		    	return false;
		    } else {
		    	if (input.length > 5){
		    		return false;
		    	}
		    }
	    } else {
	    	if (flagDot){
		    	var indexDot = input.indexOf(".");
		    	/*if ((input.length - indexDot -1) > 1 ){
		    		return false;
		    	}*/
		    	if (this.selectionStart > indexDot && (input.length - indexDot -1) > 1){
		    		return false;
		    	}
		    	if (this.selectionStart <= indexDot && (indexDot >= 5)){
		    		return false;
		    	}
		    } else {
		    	if (input.length >= 5){
		    		return false;
		    	}
		    }
	    }
	    
	});
	
	$(".numberic").select(function(event) {
    	//console.log("a");
		isSelectionInput = true;
    });
	
	$(".numberic").change(function(event) {
		var val = $(this).val().trim();
		val = val.replace(",", "");
		var indexDot = val.indexOf(".");
		if (indexDot < 0){
			val = val.substr(0,5);
		} else {
			var valInt, valDec;
			if (indexDot < 6){
				valInt = val.slice(0,indexDot);
				valDec = val.slice(indexDot,indexDot+3);
				val = valInt + valDec;
			} else {
				valInt = val.slice(0,5);
				valDec = val.slice(indexDot,indexDot+3);
				val = valInt + valDec;
			}
		}
		$(this).val(convertNumberToString7v2(val));
	});
	
	$('.numberic').bind("cut copy paste",function(e) {
		var val = e.originalEvent.clipboardData.getData('Text');
		var regex = /[^0-9.,]/g;
		if (regex.test(val)) {
			e.preventDefault();
		}
	});
}

/**
 * check numberic
 * @param character
 * @returns true if numberic
 */
function checkNumeric(character){
	var regex = /[^0-9.]/g;
	return !regex.test(character);
}

/**
 * Method will call when Click button yes on dialog confirm
 * @param idEvent Event Save/Delete
 * @param element Dialog confirm
 */
function doEventDialog01(idEvent, element){
	var info = table.page.info();
	var page = info.page;
	
	// In case add (insert)
	if (idEvent == "add"){
		var params = {};
		params.itemCd = $("#itemCode").val().trim();
		params.itemName = $("#itemName").val().trim();
		var categoryCd = $("#category").val().trim();
		var categoryName = "";
		var price = [];
		var country = [];
		var brand = $("#itemBrand").val().trim();

		$.each(categoryObject, function(i, item) {
			if (categoryObject[i].categoryCd == categoryCd){
				categoryName = categoryObject[i].categoryName;
			}
		});
		params.categoryCd = categoryCd;
		params.categoryName = categoryName;
		if (brand != undefined && brand != ""){
			params.itemBrand = brand;
		}
		
		if ($("#addMemory").attr("disabled") == undefined){
			if ($("#addMemory").is(':checked')){
				params.addMemory = "1";
			}
		}
		/*
		if ($("#noUse").attr("disabled") == undefined){
			if ($("#noUse").is(':checked')){
				params.noUse = "1";
			}
		}*/
		
		$("#countriesPrice input.numberic").each(function(index) {
	    	country.push(countryObject[index].countryCd);
	    	price.push(parseFloat($(this).val().trim().replace(",", "")));
	    });
		
		params.unitPrice = JSON.stringify({
			country: country,
			price : price
		});
		//refreshHeader();
		callAjax (params, "sab0011_03", "POST", page);
		isChange = false;
	}
	
	// In case delete
	if (idEvent == "delete" && deleteRowId != undefined){
		var data = table.rows(deleteRowId).data();
		var item = data[0];
		var itemCd = item[1].trim();
		var params = {};
		params.itemCd = itemCd;
		callAjax (params, "sab0011_04", "POST", page);
		isChange = false;
	}
	
	// In case save (update)
	if (idEvent == "save"){
		var params = {};
		params.itemCd = $("#itemCode").val().trim();
		params.itemName = $("#itemName").val().trim();
		var categoryCd = $("#category").val().trim();
		var categoryName = "";
		var price = [];
		var country = [];
		var brand = $("#itemBrand").val().trim();

		$.each(categoryObject, function(i, item) {
			if (categoryObject[i].categoryCd == categoryCd){
				categoryName = categoryObject[i].categoryName;
			}
		});
		params.categoryCd = categoryCd;
		params.categoryName = categoryName;
		if (brand != undefined && brand != ""){
			params.itemBrand = brand;
		}
		
		if ($("#addMemory").attr("disabled") == undefined){
			if ($("#addMemory").is(':checked')){
				params.addMemory = "1";
			}
		}
		
		if ($("#noUse").attr("disabled") == undefined){
			if ($("#noUse").is(':checked')){
				params.noUse = "1";
			}
		}
		
		$("#countriesPrice input.numberic").each(function(index) {
	    	country.push(countryObject[index].countryCd);
	    	price.push(parseFloat($(this).val().trim().replace(",", "")));
	    });
		
		params.unitPrice = JSON.stringify({
			country: country,
			price : price
		});
		//refreshHeader();
		callAjax (params, "sab0011_02", "POST", page);
		isChange = false;
	}
	
	// in case update a record
	if (idEvent == "updateRecord"){
		removeInfoHeader();
		editItem(recordIdUpdate);
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
 * check exist item
 * 
 * @param itemCd
 * @param categoryCd
 * @returns true If item code exist
 */
function checkItem(itemCd){
	var result = false;
	for (var i=0; i<listItemObject.length; i++){
		if (listItemObject[i].itemCd == itemCd){
			result = true;
			break;
		}
	}
	return result;
}

/**
 * insert item to item_mst
 * @returns
 */
function doAdd(){
	if (validate("add")){
		showConfirmDialog("add","","",{"position":"200"});
	}
}

/**
 * delete item_mst
 * @returns
 */
function doDelete(rowId){
	deleteRowId = rowId;
	showConfirmDialog("delete","","",{"position":"200"});
}

/**
 * save item_mst
 * @returns
 */
function doSave(){
	if (validate("save")){
		showConfirmDialog("save","","",{"position":"200"});
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
 * call ajax when click button add or save
 * @param params
 * @param actionName
 * @param methodType
 * @param page
 * @returns
 */
function callAjax(params, actionName, methodType, page) {
	loading_ajax();
    $.ajax({
    		cache: false,
            url : actionName,
            type : methodType,
            data: params,
            dataType : "json",
            success : function(response) {
            	close_loading_ajax();
            	listItemObject = response.listItem;
    			drawData(listItemObject, page);
    			refreshHeader();
    			$(".td-brand").addClass("brand-field");
    			recordId = -1;
            },
            error:function(jqXhr, textStatus, errorThrown){
                alert(textStatus);
                close_loading_ajax();
            }
    });
}

/**
 * remove all infomation in header
 * disabled button delete, save
 * @returns
 */
function refreshHeader(){
	$("input.alphanumberic").val("");
	$("#category").val("");
    $("input.numberic").val("0.00");
    $(".btn-delete").attr("disabled", true);
    //$(".btn-save").text("Update");
    $(".btn-save").attr("disabled", true);
    disableCheckbox(0);
    disableCheckbox(1);
}

/**
 * close modal UpdateRecord
 * 
 * @returns
 */
function doCloseConfirmUpdateRecord(){
	$("#modalConfirmUpdateRecord").modal("hide");
	$("input[type=radio]").eq(recordId).prop("checked", true);
	isChange = true;
}

/**
 * show modal UpdateRecord
 * 
 * @returns
 */
function showConfirmDialogUpdateRecord(idEvent){
	var contentConfirm = getMessageWithParams(idEvent,"");
	document.getElementById("contentConfirmUpdateRecord").innerHTML = contentConfirm;

	$('#buttonYesUpdateRecord').attr('onclick', "doYes('updateRecord')");
	$("#modalConfirmUpdateRecord").modal("show");
}